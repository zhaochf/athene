/**
 * 
 */
package com.athene.core.provider;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.athene.provider.api.Service;

import Ice.AsyncResult;
import Ice.Callback;
import Ice.Communicator;
import Ice.CommunicatorDestroyedException;
import Ice.Current;
import Ice.Identity;
import Ice.InitializationData;
import Ice.LocalException;
import Ice.Logger;
import Ice.ObjectAdapter;
import Ice.ObjectAdapterDeactivatedException;
import Ice.Properties;
import Ice.StringSeqHolder;
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

	private static final String SHARED_COMMUNICATOR = "IceBox.SharedCommunicator.";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667086745368861487L;
	
	private static final String USE_SHARED_COMMUNICATOR = "IceBox.UseSharedCommunicator.";
	
	/**
	 * Spring application context
	 * 
	 */
	private final ApplicationContext applicationContext;
	
	/**
	 * ServiceManager's communicator
	 */
	private final Communicator communicator;
	
	/**
	 * Service usered shared communicator
	 */
	private Communicator sharedCommunicator;
	
	/**
	 * Service admin enabled
	 */
	private final boolean adminEnabled;
	
	/**
	 * Service admin facet filters
	 */
	private Set<String> adminFacetFilters = Collections.synchronizedSet(new HashSet<>());
	
	/**
	 * Service start args
	 */
	private final String[] args;
	
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
	
	private final Logger logger;
	

	/**
	 * @param applicationContext
	 */
	public ServiceManager(ApplicationContext applicationContext, Communicator communicator, String[] args) {
		this.applicationContext = applicationContext;
		this.communicator = communicator;
		this.args = args;
		this.logger = communicator.getLogger();
		
		Properties properties = this.communicator.getProperties();
		if (properties.getProperty("Ice.Admin.Enabled").isEmpty()) {
			this.adminEnabled = !properties.getProperty("Ice.Admin.Endpoints").isEmpty();
		} else {
			this.adminEnabled = properties.getPropertyAsInt("Ice.Admin.Enabled") > 0;
		}
		
		if (this.adminEnabled) {
			String[] facetFilter = properties.getPropertyAsList("Ice.Admin.Facets");
			if (facetFilter != null && facetFilter.length > 0) {
				this.adminFacetFilters.addAll(Arrays.asList(facetFilter));
			}
		}
		
		this.traceServiceObserver = properties.getPropertyAsInt("IceBox.Trace.ServiceObserver");
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
		
		try {
			
			Properties properties = this.communicator.getProperties();
			
			// Create service manager adapter
			ObjectAdapter adapter = createServiceManagerAdapter(properties);
			
			// Resolve config service infos 
			resolveConfigServiceInfos(properties);
			
			// Resolve config use shared communicator
			resovleUseSharedCommunicator(properties);
			
			// Start all service
			for (ServiceInfo serviceInfo : this.serviceInfos.values()) {
				start(serviceInfo);
			}
			
			//
            // We may want to notify external scripts that the services
            // have started. This is done by defining the property:
            //
            // IceBox.PrintServicesReady=bundleName
            //
            // Where bundleName is whatever you choose to call this set of
            // services. It will be echoed back as "bundleName ready".
            //
            // This must be done after start() has been invoked on the
            // services.
            //
			String bundleName = properties.getProperty("IceBox.PrintServicesReady");
			if (!StringUtils.isEmpty(bundleName)) {
				this.logger.print(String.format("%s ready", bundleName));
			}
			//
            // Don't move after the adapter activation. This allows
            // applications to wait for the service manager to be
            // reachable before sending a signal to shutdown the
            // IceBox.
            //
			Ice.Application.shutdownOnInterrupt();
			//
            // Register "this" as a facet to the Admin object and
            // create Admin object
            //
			try {
				this.communicator.addAdminFacet(this, "IceBox.ServiceManager");
				this.communicator.getAdmin();
			} catch (Exception e) {
				//
                // Expected if the communicator has been shutdown.
                //
			}
			//
            // Start request dispatching after we've started the services.
            //
			if (adapter != null) {
				try {
					adapter.activate();
				} catch (Exception e) {
					//
                    // Expected if the communicator has been shutdown.
                    //
				}
			}
			
			this.communicator.waitForShutdown();
			Ice.Application.defaultInterrupt();
		} catch (Exception e) {
			this.logger.error(getExceptionStackTrace(e));
			return 1;
		} finally {
			//
            // Invoke stop() on the services.
            //
			stopAll();
		}
		
		return 0;
	}

	/**
	 * Start service
	 * 
	 * @param serviceInfo
	 */
	synchronized private void start(ServiceInfo serviceInfo) {
		
		if (serviceInfo == null) {
			return;
		}
		
		if (this.communicator.getProperties().getPropertyAsInt(String.format("%s%s", USE_SHARED_COMMUNICATOR, serviceInfo.serviceName)) > 0) {
			assert(this.sharedCommunicator != null);
			serviceInfo.communicator = this.sharedCommunicator;
		} else {
			InitializationData initializationData = new InitializationData();
			initializationData.properties = createServiceProperties(serviceInfo.serviceName);
			StringSeqHolder serviceArgs = new StringSeqHolder(serviceInfo.args);
			if (serviceArgs.value.length > 0) {
				//
                // Create the service properties with the given service arguments. This should
                // read the service config file if it's specified with --Ice.Config.
                //
				initializationData.properties = Ice.Util.createProperties(serviceArgs, initializationData.properties);
				//
                // Next, parse the service "<service>.*" command line options (the Ice command
                // line options were parsed by the createProperties above)
                //
				serviceArgs.value = initializationData.properties.parseCommandLineOptions(serviceInfo.serviceName, serviceArgs.value);
			}
			//
            // Clone the logger to assign a new prefix. If one of the built-in loggers is configured
            // don't set any logger.
            //
			if (initializationData.properties.getProperty("Ice.LogFile").length() == 0 && 
					(initializationData.properties.getPropertyAsInt("Ice.UseSyslog") <= 0 || 
					System.getProperty("os.name").startsWith("Windows"))) {
				initializationData.logger = this.logger.cloneWithPrefix(initializationData.properties.getProperty("Ice.ProgramName"));
			}
			final String serviceFacetNamePrefix = String.format("IceBox.Service.%s.", serviceInfo.serviceName);
			boolean addFacets = configureAdmin(initializationData.properties, serviceFacetNamePrefix);
			//
			// Remaining command line options are passed to the communicator. This is
			// necessary for Ice plug-in properties (e.g.: IceSSL).
			//
			serviceInfo.communicator = Ice.Util.initialize(serviceArgs, initializationData);
			serviceInfo.args = serviceArgs.value;
			
			if (addFacets) {
				addAllAdminFacet(serviceInfo.communicator.findAllAdminFacets().entrySet(), serviceFacetNamePrefix);
			}
			
			serviceInfo.start();
		}
	}
	
	synchronized private void stopAll() {
		//
        // First wait for any active startService/stopService calls to complete.
        //
		while (this.pendingStatusChanges) {
			try {
				wait();
			} catch (InterruptedException e) {
				// do nothing
			}
		}
		
		//
        // For each service, we call stop on the service and flush its database environment to
        // the disk. Services are stopped in the reverse order of the order they were started.
        //
		for (ServiceInfo serviceInfo : this.serviceInfos.values()) {
			if (serviceInfo.status == Status.started) {
				try {
					serviceInfo.stop();
					serviceStopedManage(serviceInfo, observers);
				} catch (Exception e) {
					this.logger.warning(String.format("ServiceManager: exception while stopping service %s :\n%s", serviceInfo.serviceName, getExceptionStackTrace(e)));
				}
			}
			if (serviceInfo.communicator != null) {
				destroyServiceCommunicator(serviceInfo.serviceName, serviceInfo.communicator);
			}
			this.serviceInfos.clear();
		}
		
		if (this.sharedCommunicator != null) {
			removeAdminfacets(SHARED_COMMUNICATOR);
			try {
				this.sharedCommunicator.destroy();
			} catch (Exception e) {
				this.logger.warning(String.format("ServiceManager: exception while destroying shared communicator:\n", getExceptionStackTrace(e)));
			}
		}
	}
	
	private void destroyServiceCommunicator(String serviceName, Communicator communicator) {
		try {
			communicator.shutdown();
			communicator.waitForShutdown();
		} catch (CommunicatorDestroyedException e) {
			//
            // Ignore, the service might have already destroyed
            // the communicator for its own reasons.
            //
		} catch (Exception e) {
			this.logger.warning(String.format("ServiceManager: exception in shutting down communicator for service %s \n%s", serviceName, getExceptionStackTrace(e), args));
		}
		
		removeAdminfacets(String.format("IceBox.Service.%s.", serviceName));
		
		try {
			communicator.destroy();
		} catch (Exception e) {
			this.logger.warning(String.format("ServiceManager: exception in destroying communicator for service %s \n%s", serviceName, getExceptionStackTrace(e)));
		}
	}
	
	private void addAllAdminFacet(Set<Entry<String, Ice.Object>> facets, String prefix) {
		if (CollectionUtils.isEmpty(facets)) {
			return;
		}
		
		for (Entry<String, Ice.Object> facet : facets) {
			// Add all facets created on shared communicator to the IceBox communicator
	        // but renamed <prefix>.<facet-name>, except for the Process facet which is
	        // never added.
			if (!"Process".equals(facet.getKey())) {
				this.communicator.addAdminFacet(facet.getValue(), String.format("%s%s", prefix, facet.getKey()));
			}
		}
	}
	
	private void removeAdminfacets(String prefix) {
		try {
			Set<String> facets = this.communicator.findAllAdminFacets().keySet();
			for(String facet : facets) {
				this.communicator.removeAdminFacet(facet);
			}
		} catch (CommunicatorDestroyedException e) {
			// Do nothing
		} catch (ObjectAdapterDeactivatedException e) {
			// Do nothing
		}
	}
	

	/**
	 * Resolve config use shared communicator
	 * 
	 * @param properties
	 */
	private void resovleUseSharedCommunicator(Properties properties) {
		if (StringUtils.isEmpty(properties.getPropertiesForPrefix(USE_SHARED_COMMUNICATOR))) {
			return;
		}
		
		InitializationData initializationData = new InitializationData();
		initializationData.properties = createServiceProperties("SharedCommunicator");
		boolean addFacets = configureAdmin(initializationData.properties, SHARED_COMMUNICATOR);
		
		this.sharedCommunicator = Ice.Util.initialize(initializationData);
		if (addFacets) {
			addAllAdminFacet(this.sharedCommunicator.findAllAdminFacets().entrySet(), SHARED_COMMUNICATOR);
		}
		
		for (Map.Entry<String, ServiceInfo> serviceInfoMapping : this.serviceInfos.entrySet()) {
			String serviceName =  serviceInfoMapping.getKey();
			ServiceInfo serviceInfo = serviceInfoMapping.getValue();
			if (properties.getPropertyAsInt(String.format("%s.%s", USE_SHARED_COMMUNICATOR, serviceName)) <= 0) {
				continue;
			}
			serviceInfo.communicator = sharedCommunicator;
			//
	        // Load the service properties using the shared communicator properties as
	        // the default properties.
	        //
			StringSeqHolder serviceArgs = new StringSeqHolder(serviceInfo.args);
			Properties serviceProperties = Ice.Util.createProperties(serviceArgs, initializationData.properties);
			serviceInfo.args = serviceArgs.value;

	        //
	        // Remove properties from the shared property set that a service explicitly clears.
	        //
			Map<String, String> allProperties = initializationData.properties.getPropertiesForPrefix("");
			for (String key : allProperties.keySet()) {
				if (StringUtils.isEmpty(serviceProperties.getProperty(key))) {
					initializationData.properties.setProperty(key, "");
				}
			}
			//
	        // Add the service properties to the shared communicator properties.
	        //
			for (Map.Entry<String, String> property : serviceProperties.getPropertiesForPrefix("").entrySet()) {
				initializationData.properties.setProperty(property.getKey(), property.getValue());
			}
			//
	        // Parse <service>.* command line options (the Ice command line options
	        // were parsed by the call to createProperties above).
	        //
			serviceInfo.args = initializationData.properties.parseCommandLineOptions(serviceName, serviceInfo.args);
		}
	}
	
	/**
	 * Create an object adapter. Services probably should NOT share
	 * this object adapter, as the endpoint(s) for this object adapter
	 * will most likely need to be firewalled for security reasons.
	 * 
	 * @param properties
	 */
	private ObjectAdapter createServiceManagerAdapter(Properties properties) {
		ObjectAdapter adapter = null;
		if (!StringUtils.isEmpty(properties.getProperty("IceBox.ServiceManager.Endpoints"))) {
			adapter = this.communicator.createObjectAdapter("IceBox.ServiceManager");
			
			Identity identity = new Identity();
			identity.category = properties.getPropertyWithDefault("IceBox.InstanceName", "IceBox");
			identity.name = "ServiceManager";
			adapter.add(this, identity);
		}
		return adapter;
	}
	
	/**
	 * Resolve config service infos
	 * 
	 * Parse the property set with the prefix "IceBox.Service.". These
	 * properties should have the following format:
	 * IceBox.Service.Foo=Package.Foo [args]
	 * We parse the service properties specified in IceBox.LoadOrder
	 * first, then the ones from remaining services.
	 * 
	 * @param properties
	 */
	private void resolveConfigServiceInfos(Properties properties) {
		final String prefix = "IceBox.Service.";
		Map<String, String> services = properties.getPropertiesForPrefix(prefix);
		String[] loadOrder = properties.getPropertyAsList("IceBox.LoadOrder");
		if (loadOrder != null && loadOrder.length > 1) {
			for (String name : loadOrder) {
				if (StringUtils.isEmpty(name)) {
					continue;
				}
				String key = String.format("%s%s", prefix, name);
				String serviceType = services.get(key);
				if (StringUtils.isEmpty(serviceType)) {
					throw new FailureException(String.format("ServiceManager: no service definition for '%s'", name));
				}
				
				addServiceInfo(name, serviceType);
				services.remove(key);
			}
		}
		
		for (Entry<String, String> config : services.entrySet()) {
			String name = config.getKey().substring(prefix.length());
			String serviceType = config.getValue();
			addServiceInfo(name, serviceType);
		}
	}
	
	/**
	 * Create service properties
	 * 
	 * @param serviceName
	 * @return
	 */
	private Properties createServiceProperties(String serviceName) {
		
		Properties result  = null;
		Properties communicatorProperties = this.communicator.getProperties();
		if (communicatorProperties.getPropertyAsInt("IceBox.InheritProperties") > 0) {
			result = communicatorProperties._clone();
			// Set admin property to empty value
			for (String property : result.getPropertiesForPrefix("Ice.Admin.").keySet()) {
				result.setProperty(property, "");
			}
		} else {
			result = Ice.Util.createProperties();
		}
		
		String programName = communicatorProperties.getProperty("Ice.ProgramName");
		if (StringUtils.isEmpty(programName)) {
			result.setProperty("Ice.ProgramName", serviceName);
		} else {
			result.setProperty("Ice.ProgramName", String.format("$s-%s", programName, serviceName));
		}
		
		return result;
	}
	
	private boolean configureAdmin(Properties properties, String prefix) {
		if (this.adminEnabled && properties.getProperty("Ice.Admin.Enabled").isEmpty()) {
			List<String> facetNames = new LinkedList<String>();
			for (String filter : this.adminFacetFilters) {
				if (filter.startsWith(prefix)) {
					facetNames.add(filter.substring(prefix.length()));
				}
			}
			if (this.adminFacetFilters.isEmpty() || !facetNames.isEmpty()) {
				properties.setProperty("Ice.Admin.Enabled", "1");
				if (!facetNames.isEmpty()) {
					properties.setProperty("Ice.Admin.Facets", IceUtilInternal.StringUtil.joinString(facetNames, " "));
				}
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Add service info with config service name and type
	 * 
	 * @param name
	 * @param serviceType
	 */
	private void addServiceInfo(String name, String serviceType) {
		this.serviceInfos.put(name, new ServiceInfo(name, serviceType, this.communicator, this.args));
	}

	private enum Status {
		stopping, stopped, starting, started
	}
	
	private final class ServiceInfo {
		
		private final String serviceName;
		
		private final Class<?> serviceType;
		
		private final ServiceAdapter serviceAdapter;
		
		private Communicator communicator;
		
		private String[] args;
		
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
		
		synchronized public void start() {
			this.serviceAdapter.start(serviceName, communicator, args);
			this.status = Status.started;
		}
		
		synchronized public void stop() {
			this.serviceAdapter.stop();
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
