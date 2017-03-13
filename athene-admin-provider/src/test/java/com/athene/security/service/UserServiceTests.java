/**
 * 
 */
package com.athene.security.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.athene.AbstractTests;
import com.athene.security.domain.User;

/**
 * @author zhaochf
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTests extends AbstractTests {

	@Autowired
	private UserService userService;
	
	@Test
	public void test01Save() {
		User user = new User("ac100ffc-5ac7-19fa-815a-c72a03e20000");
		user.setUsername("zhaochf");
		user.setPassword("123456");
		user.setFirstName("zhao");
		user.setLastName("chunfeng");
		user.setBirthday(LocalDate.of(1981, 4, 23));
		user.setGender('1');
		user.setMobilePhone("13811987990");
		user.setEmail("zhaochunfeng@zgcfinance.com.cn");
		user.setEnabled(true);
		user.setLocked(false);
		user.setCreatedBy("admin");
		user.setLastModifiedBy(user.getCreatedBy());
		
		userService.saveUser(user);
	}
	
	@Test
	public void test02GetUser() {
		logger.debug("Get user by username!");
		User user = userService.getUser("zhaochf");
		assertThat("ac100ffc-5ac7-19fa-815a-c72a03e20000", equalTo(user.getId()));
		
		logger.debug("Get user by mobile phone!");
		user = userService.getUser("13811987990");
		assertThat("ac100ffc-5ac7-19fa-815a-c72a03e20000", equalTo(user.getId()));
		
		logger.debug("Get user by email!");
		user = userService.getUser("zhaochunfeng@zgcfinance.com.cn");
		assertThat("ac100ffc-5ac7-19fa-815a-c72a03e20000", equalTo(user.getId()));
	}
}
