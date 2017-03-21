/**
 * 
 */
package com.athene.security.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.athene.security.domain.User;
import com.athene.security.repository.UserRepository;

/**
 * @author zhaochf
 *
 */
@Service
@CacheConfig(cacheNames = {"users"})
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#saveUser(com.athene.security.domain.User)
	 */
	@Caching(put = {
			@CachePut(key = "#user.username"),
			@CachePut(key = "#user.mobilePhone"),
			@CachePut(key = "#user.email")
	})
	@Transactional
	@Override
	public User saveUser(User user) {
		
		if (Optional.of(user).isPresent()) {
			return userRepository.save(user);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#deleteUsers(java.lang.String[])
	 */
	@CacheEvict(allEntries=true)
	@Transactional
	@Override
	public void deleteUsers(String... userIds) {

		Optional.of(userIds).ifPresent((ids) -> {
			List<User> users = Collections.synchronizedList(Collections.emptyList());
			Arrays.asList(ids).forEach((id) -> {
				users.add(new User(id));
			});
			userRepository.deleteInBatch(users);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#getUser(java.lang.String)
	 */
	@Cacheable(key = "#key")
	@Override
	public User getUser(String key) {
		if (Optional.of(key).isPresent()) {
			return userRepository.findByKey(key);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#getUsers(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> getUsers(String queryKey, Pageable pageable) {
		
		if (Optional.of(queryKey).isPresent()) {
			return userRepository.findByQueryKey(queryKey, pageable);
		}
		return null;
	}
}
