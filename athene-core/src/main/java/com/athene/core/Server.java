/**
 * 
 */
package com.athene.core;

import com.athene.core.provider.ServiceManagerTemp;

import Ice.Application;
import Ice.InitializationData;
import Ice.Util;

/**
 * @author zhaochf
 *
 */
public final class Server extends Application {

	private static final String ICE_ADMIN_DELAY_CREATION_NAME = "Ice.Admin.DelayCreation";
	private static final String ICE_ADMIN_DELAY_CREATION_VALUE = "1";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InitializationData initializationData = new InitializationData();
		initializationData.properties = Util.createProperties();
		initializationData.properties.setProperty(ICE_ADMIN_DELAY_CREATION_NAME, ICE_ADMIN_DELAY_CREATION_VALUE);
		
		Server server = new Server();
		System.exit(server.main("IceBox.AtheneServer", args, initializationData));
	}

	@Override
	public int run(String[] args) {
		return new ServiceManagerTemp(communicator(), args).run();
	}

}
