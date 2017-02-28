/**
 * 
 */
package com.athene.security.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.athene.security.domain.User;
import com.athene.security.repository.UserRepository;
import com.athene.security.service.UserService;

/**
 * @author zhaochf
 *
 */
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#saveUser(com.athene.security.domain.User)
	 */
	@Override
	public void saveUser(User user) {

		Optional.of(user).ifPresent((entity) -> {
			userRepository.save(entity);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.security.service.UserService#deleteUsers(java.lang.String[])
	 */
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
	@Override
	public User getUser(String userId) {
		
		if (Optional.of(userId).isPresent()) {
			return userRepository.findOne(userId);
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
