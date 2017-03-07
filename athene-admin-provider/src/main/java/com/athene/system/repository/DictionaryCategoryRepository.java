/**
 * 
 */
package com.athene.system.repository;

import org.springframework.data.jpa.repository.TreeJpaRepository;

import com.athene.system.domain.DictionaryCategory;

/**
 * @author zhaochf
 *
 */
public interface DictionaryCategoryRepository extends TreeJpaRepository<DictionaryCategory, String> {

}
