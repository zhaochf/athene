/**
 * 
 */
package com.athene.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.athene.security.domain.User;

/**
 * User service
 * 
 * 
 * @author zhaochf
 *
 */
public interface UserService {

	/**
	 * Save user
	 * 
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * Delete users with user ids
	 * @param userids
	 */
	public void deleteUsers(String... userids);
	
	/**
	 * Get user with user id
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(String userId);
	
	
	/**
	 * Get users with key for page
	 * 
	 * @param key
	 * @param pageable
	 * @return
	 */
	public Page<User> getUsers(String key, Pageable pageable);
}
