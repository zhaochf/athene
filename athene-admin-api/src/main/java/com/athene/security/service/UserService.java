/**
 * 
 */
package com.athene.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athene.security.domain.User;

/**
 * User service
 * 
 * 
 * @author zhaochf
 *
 */
@Service
public interface UserService {

	/**
	 * Save user
	 * 
	 * @param user
	 * 
	 * @return 
	 */
	@Transactional
	public User saveUser(User user);
	
	/**
	 * Delete users  by user ids
	 * @param userIds
	 */
	@Transactional
	public void deleteUsers(String... userIds);
	
	/**
	 * Get user by key
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(String key);
	
	
	/**
	 * Get users  by query key for page
	 * 
	 * @param queryKey
	 * @param pageable
	 * @return
	 */
	public Page<User> getUsers(String queryKey, Pageable pageable);
}
