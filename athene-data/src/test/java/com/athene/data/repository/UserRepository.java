/**
 * 
 */
package com.athene.data.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.athene.data.domain.User;

/**
 * @author zhaochf
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value="SELECT * FROM T_SYS_USER", nativeQuery=true)
	public List<Map<String, Object>> findUsers();
	
	@Query(value="SELECT * FROM T_SYS_USER", nativeQuery=true)
	public Page<Map<String, Object>> findUsersByPage(Pageable pageable);
}
