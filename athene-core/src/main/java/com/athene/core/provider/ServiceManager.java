/**
 * 
 */
package com.athene.core.provider;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import com.athene.provider.api.Service;

import Ice.AsyncResult;
import Ice.Callback;
import Ice.Communicator;
import Ice.CommunicatorDestroyedException;
import Ice.Current;
import Ice.LocalException;
import Ice.Logger;
import IceBox.AlreadyStartedException;
import IceBox.AlreadyStoppedException;
import IceBox.FailureException;
import IceBox.NoSuchServiceException;
import IceBox.ServiceObserverPrx;
import IceBox.ServiceObserverPrxHelper;
import IceBox.SliceChecksums;
import IceBox._ServiceManagerDisp;

/**
 * @author zhaochf
 *
 */
public class ServiceManager extends _ServiceManagerDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667086745368861487L;
	
	/**
	 * Spring application context
	 * 
	 */
	private final ApplicationContext applicationContext;
	
	private final Communicator communicator;
	
	/**
	 * Managed services
	 * The key is service name, value is service adapter instance
	 */
	private final Map<String, ServiceInfo> serviceInfos = Collections.synchronizedMap(new HashMap<String, ServiceInfo>());
	
	/**
	 * Pending status changes for service
	 */
	private boolean pendingStatusChanges = false;
	
	/**
	 * The observer service proxy set
	 */
	private Set<ServiceObserverPrx> observers = Collections.synchronizedSet(new HashSet<ServiceObserverPrx>());
	
	private int traceServiceObserver = 0;
	
	private Callback observerCallback = new Callback() {
		
		@Override
		public void completed(AsyncResult asyncResult) {
			try {
				asyncResult.throwLocalException();
			} catch (LocalException e) {
				ServiceObserverPrx observer = ServiceObserverPrxHelper.uncheckedCast(asyncResult.getProxy());
				synchronized (ServiceManager.this) {
					if (ServiceManager.this.observers.remove(observer)) {
						if (ServiceManager.this.traceServiceObserver > 0 && !(e instanceof CommunicatorDestroyedException)) {
							//
				            // CommunicatorDestroyedException may occur during shutdown. The observer notification has
				            // been sent, but the communicator was destroyed before the reply was received. We do not
				            // log a message for this exception.
				            //
							ServiceManager.this.logger.trace("IceBox.ServiceObserver", "Removed service observer " + 
									ServiceManager.this.communicator.proxyToString(observer) +
									"\nafter catching " + e.toString());
						}
					}
				}
			}
		}
	};
	
	private Logger logger;
	

	/**
	 * @param applicationContext
	 */
	public ServiceManager(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/* (non-Javadoc)
	 * @see IceBox._ServiceManagerOperations#getSliceChecksums(Ice.Current)
	 */
	@Override
	public Map<String, String> getSliceChecksums(Current __current) {
		return SliceChecksums.checksums;
	}

	/* (non-Javadoc)
	 * @see IceBox._ServiceManagerOperations#startService(java.lang.String, Ice.Current)
	 */
	@Override
	public void startService(String service, Current __current) throws AlreadyStartedException, NoSuchServiceException {

		ServiceInfo serviceInfo = null;
		synchronized (this) {
			serviceInfo = this.serviceInfos.get(service);
			if (serviceInfo == null) {
				throw new NoSuchServiceException();
			}
			if (serviceInfo.status == Status.started) {
				throw new AlreadyStartedException();
			}
			serviceInfo.status = Status.starting;
			this.pendingStatusChanges = true;
		}
		
		boolean started = false;
		try {

			serviceInfo.serviceAdapter.start(service, serviceInfo.communicator, serviceInfo.args);
			started = true;
		} catch (Exception e) {
			this.logger.warning(String.format("starting service %s failure, failure exception: %s ", serviceInfo.serviceName, getExceptionStackTrace(e)));
		}
		
		synchronized (this) {
			if (started) {
				serviceInfo.status = Status.started;
				serviceStartedManage(serviceInfo, this.observers);
			} else {
				serviceInfo.status = Status.stopped;
			}
			this.pendingStatusChanges = false;
			notifyAll();
		}
	}

	/* (non-Javadoc)
	 * @see IceBox._ServiceManagerOperations#stopService(java.lang.String, Ice.Current)
	 */
	@Override
	public void stopService(String service, Current __current) throws AlreadyStoppedException, NoSuchServiceException {
		ServiceInfo serviceInfo = null;
		synchronized (this) {
			serviceInfo = serviceInfos.get(service);
			if (serviceInfo == null) {
				throw new NoSuchServiceException();
			}
			if (serviceInfo.status == Status.stopped) {
				throw new AlreadyStoppedException();
			}
			serviceInfo.status = Status.stopping;
			this.pendingStatusChanges = true;
		}
		
		boolean stopped = false;
		try {
			serviceInfo.serviceAdapter.stop();
			stopped = true;
		} catch (Exception e) {
			logger.error(String.format("Stoping service %s failure, failure exception: %s", serviceInfo.serviceName, getExceptionStackTrace(e)));
		}
		
		synchronized (this) {
			if (stopped) {
				serviceInfo.status = Status.stopped;
				// Notice service observer to stoped service for admin client  
				serviceStopedManage(serviceInfo, observers);
			} else {
				serviceInfo.status = Status.started;
			}
			this.pendingStatusChanges = false;
			notifyAll();
		}
		
	}

	/* (non-Javadoc)
	 * @see IceBox._ServiceManagerOperations#addObserver(IceBox.ServiceObserverPrx, Ice.Current)
	 */
	@Override
	public void addObserver(ServiceObserverPrx observer, Current __current) {
		//
        // Null observers and duplicate registrations are ignored
        //
		List<String> activeServices = new LinkedList<String>();
		synchronized (this) {
			if (observer != null && this.observers.add(observer)) {
				if (this.traceServiceObserver > 0) {
					logger.trace("IceBox.ServiceObserver", "Added service observer " + this.communicator.proxyToString(observer));
				}
				for (ServiceInfo serviceInfo : this.serviceInfos.values()) {
					if (serviceInfo.status ==  Status.started) {
						activeServices.add(serviceInfo.serviceName);
					}
				}
			}
		}
		
		if (!CollectionUtils.isEmpty(activeServices)) {
			observer.begin_servicesStarted(activeServices.toArray(new String[0]), this.observerCallback);
		}
	}

	/* (non-Javadoc)
	 * @see IceBox._ServiceManagerOperations#shutdown(Ice.Current)
	 */
	@Override
	public void shutdown(Current __current) {
		
		this.communicator.shutdown();
	}
	
	public int run() {
		
	}

	private enum Status {
		stopping, stopped, starting, started
	}
	
	private final class ServiceInfo {
		
		private final String serviceName;
		
		private final Class<?> serviceType;
		
		private final ServiceAdapter serviceAdapter;
		
		private final Communicator communicator;
		
		private final String[] args;
		
		private Status status;

		/**
		 * 
		 */
		public ServiceInfo(String serviceName, String serviceType, Communicator communicator, String[] args) {
			this.serviceName = serviceName;
			try {
				this.serviceType = ClassUtils.getDefaultClassLoader().loadClass(serviceType);
			} catch (ClassNotFoundException e) {
				throw new FailureException("Service class " + serviceType + " cann't load!");
			}
			
			Object service = applicationContext.getBean(this.serviceType);
			if (service == null) {
				throw new FailureException("Cann't get bean instance from spring application context with the type: " + serviceType + " !");
			}
			
			this.serviceAdapter = new ServiceAdapter((Service) service);
			this.communicator = communicator;
			this.args = args;
			this.status = Status.stopped;
		}
	}
	
	private void serviceStartedManage(ServiceInfo serviceInfo, Set<ServiceObserverPrx> observers) {
		if (CollectionUtils.isEmpty(observers)) {
			return;
		}
		observers.forEach((observer) -> {
			observer.begin_servicesStarted(new String[]{serviceInfo.serviceName}, this.observerCallback);
		});
	}
	
	private void serviceStopedManage(ServiceInfo serviceInfo, Set<ServiceObserverPrx> observers) {
		if (CollectionUtils.isEmpty(observers)) {
			return;
		}
		observers.forEach((observer) -> {
			observer.begin_servicesStopped(new String[]{serviceInfo.serviceName}, this.observerCallback);
		});
	}
	
	private String getExceptionStackTrace(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		throwable.printStackTrace(printWriter);
		printWriter.flush();
		return stringWriter.toString();
	}
}
