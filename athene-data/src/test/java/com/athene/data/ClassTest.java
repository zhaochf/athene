/**
 * 
 */
package com.athene.data;

import org.hibernate.mapping.Map;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaochf
 *
 */
public class ClassTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassTest.class);
	
	@Test
	public void testClass() {
		LOGGER.debug("result: " + Map.class.isAssignableFrom(Map.class));
	}

}
