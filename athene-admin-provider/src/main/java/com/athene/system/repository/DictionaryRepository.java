/**
 * 
 */
package com.athene.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.system.domain.Dictionary;

/**
 * @author zhaochf
 *
 */
public interface DictionaryRepository extends JpaRepository<Dictionary, String> {

	/**
	 * 
	 * @param categoryId
	 */
	@Modifying
	@Query("delete from Dictionary d where d.categoryId = :categoryId")
	public void deleteByCategoryId(@Param("categoryId") String categoryId); 
	
	/**
	 * Find by category id
	 * 
	 * @param categoryId
	 * @param pageable
	 */
	@Query("select d from Dictionary d where d.categoryId = :categoryId")
	public Page<Dictionary> findByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);
}
