/**
 * 
 */
package com.athene.logger;

import org.slf4j.LoggerFactory;

import Ice.Logger;

/**
 * @author zhaochf
 *
 */
public class Slf4jLoggerAdapter implements Logger {
	
	private final org.slf4j.Logger logger;

	/**
	 * @param logger
	 */
	public Slf4jLoggerAdapter(String name) {
		
		logger = LoggerFactory.getLogger(name);
	}

	/**
	 * @param logger
	 */
	public Slf4jLoggerAdapter(Class<?> clazz) {
		
		logger = LoggerFactory.getLogger(clazz);
	}



	/* (non-Javadoc)
	 * @see Ice.Logger#print(java.lang.String)
	 */
	@Override
	public void print(String message) {

		logger.info(message);
	}

	/* (non-Javadoc)
	 * @see Ice.Logger#trace(java.lang.String, java.lang.String)
	 */
	@Override
	public void trace(String category, String message) {

		logger.trace(String.format("%s: %s", category, message));
	}

	/* (non-Javadoc)
	 * @see Ice.Logger#warning(java.lang.String)
	 */
	@Override
	public void warning(String message) {

		logger.warn(message);
	}

	/* (non-Javadoc)
	 * @see Ice.Logger#error(java.lang.String)
	 */
	@Override
	public void error(String message) {

		logger.error(message);
	}

	/* (non-Javadoc)
	 * @see Ice.Logger#getPrefix()
	 */
	@Override
	public String getPrefix() {
		
		return logger.getName();
	}

	/* (non-Javadoc)
	 * @see Ice.Logger#cloneWithPrefix(java.lang.String)
	 */
	@Override
	public Logger cloneWithPrefix(String prefix) {
		return new Slf4jLoggerAdapter(prefix);
	}

}
