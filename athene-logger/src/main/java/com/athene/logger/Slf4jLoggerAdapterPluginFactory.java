/**
 * 
 */
package com.athene.logger;

import Ice.Communicator;
import Ice.LoggerPlugin;
import Ice.Plugin;
import Ice.PluginFactory;

/**
 * @author zhaochf
 *
 */
public class Slf4jLoggerAdapterPluginFactory implements PluginFactory {

	/* (non-Javadoc)
	 * @see Ice.PluginFactory#create(Ice.Communicator, java.lang.String, java.lang.String[])
	 */
	@Override
	public Plugin create(Communicator communicator, String name, String[] args) {

		LoggerPlugin loggerPlugin = new LoggerPlugin(communicator, new Slf4jLoggerAdapter("system"));
		return loggerPlugin;
	}

}
