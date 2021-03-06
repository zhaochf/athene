/**
 * 
 */
package com.athene.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.security.domain.User;

/**
 * @author zhaochf
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("select u from User u where u.username = :key or u.mobilePhone = :key or u.email = :key")
	public User findByKey(@Param("key") String key);

	/**
	 * 
	 * @param queryKey
	 * @param pageable
	 * @return
	 */
	@Query("select u from User u where u.username like %:queryKey% or u.firstName like %:queryKey% or u.lastName like %:queryKey% or u.mobilePhone like %:queryKey% or u.email like %:queryKey%")
	public Page<User> findByQueryKey(@Param("queryKey") String queryKey, Pageable pageable);
}
