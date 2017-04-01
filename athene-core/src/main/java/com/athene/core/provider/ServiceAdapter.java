/**
 * 
 */
package com.athene.core.provider;

import java.io.Serializable;

import org.springframework.util.ClassUtils;

import com.athene.provider.api.Service;

import Ice.Communicator;
import Ice.Identity;
import Ice.ObjectAdapter;
import Ice.Util;

/**
 * @author zhaochf
 *
 */
public class ServiceAdapter implements IceBox.Service, Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ObjectAdapter adapter;
	
	/**
	 * Provider instance	
	 */
	private final Service service;
	
	/**
	 * Provider instance id
	 */
	private final Identity serviceId;

	/**
	 * 
	 */
	public ServiceAdapter(Service service) {
		this.service = service;
		this.serviceId = Util.stringToIdentity(ClassUtils.getShortName(service.getClass()));
	}

	/* (non-Javadoc)
	 * @see IceBox.Service#start(java.lang.String, Ice.Communicator, java.lang.String[])
	 */
	@Override
	public void start(String name, Communicator communicator, String[] args) {
		
		adapter = communicator.createObjectAdapter(name);
		adapter.add(service, serviceId);
		adapter.activate();
	}
	
	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @return the serviceId
	 */
	public Identity getServiceId() {
		return serviceId;
	}

	/* (non-Javadoc)
	 * @see IceBox.Service#stop()
	 */
	@Override
	public void stop() {
		adapter.remove(serviceId);
		adapter.destroy();
	}
}
