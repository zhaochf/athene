/**
 * 
 */
package com.athene;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhaochf
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public abstract class AbstractTests {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ApplicationContext applicationContext;
}
