/**
 * 
 */
package com.athene.core;


import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.athene.core.provider.ServiceManager;

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
		Options options = new Options();
		CmdLineParser parser = new CmdLineParser(options);
		try {
			parser.parseArgument(args);
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
			
			return new ServiceManager(applicationContext, communicator(), args).run();
		} catch (CmdLineException e) {
			printUsage(parser);
			return 1;
		}
	}

	private static void printUsage(CmdLineParser parser) {
		final String usage = "Usage: com.athene.core.Server [options...] arguments...";
		System.out.println(usage);
		parser.printUsage(System.out);
	}
	
	private static class Options {
		
		@Option(name = "-config", required = true, usage = "The application config class is required(com.xxx.AppConfig)")
		private String appConfig;
	}
	
	
}
