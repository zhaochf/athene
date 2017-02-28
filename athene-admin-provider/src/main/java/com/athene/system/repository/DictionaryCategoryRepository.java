/**
 * 
 */
package com.athene.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.athene.system.domain.DictionaryCategory;

/**
 * @author zhaochf
 *
 */
public interface DictionaryCategoryRepository extends JpaRepository<DictionaryCategory, String> {

	/**
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	Page<DictionaryCategory> findByParentId(String parentId, Pageable pageable);
}
