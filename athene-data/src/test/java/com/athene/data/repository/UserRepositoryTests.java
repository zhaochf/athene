/**
 * 
 */
package com.athene.data.repository;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.annotation.Commit;

import com.athene.data.IntegrationTests;
import com.athene.data.domain.User;

/**
 * @author zhaochf
 *
 */
public class UserRepositoryTests extends IntegrationTests {

	@Autowired
	private UserRepository repository;
	
	@Test
	@Commit
	public void testSave() {
		User user = new User();
		user.setUsername("sunying");
		user.setAge(30);
		user.setBirthday(LocalDate.of(1981, 4, 23));
		
		repository.save(user);
	}
	
//	@Test
	public void testFindUsers() {
		List<Map<String, Object>> users = repository.findUsers();
		assertThat(true, is(not(users.isEmpty())));
	}
	
	@Test
	public void testFindUsersByPage() {
		
		Order order = new Order(Direction.DESC, "username");
		Pageable pageable = new PageRequest(1, 20, new Sort(order));
		Page<Map<String, Object>> page = repository.findUsersByPage(pageable);
		assertThat(page.getTotalElements(), greaterThan(1L));
	}
}
