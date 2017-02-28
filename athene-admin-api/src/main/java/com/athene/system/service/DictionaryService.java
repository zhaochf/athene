/**
 * 
 */
package com.athene.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athene.system.domain.Dictionary;
import com.athene.system.domain.DictionaryCategory;

/**
 * Dictionary service
 * 
 * @author zhaochf
 *
 */
@Service
public interface DictionaryService {
	
	/**
	 * Save dictionary
	 * 
	 * @param dictionary
	 */
	@Transactional
	public void saveDictionary(Dictionary dictionary);
	
	/**
	 * Delete dictionaries
	 * 
	 * @param dictionaryIds
	 */
	@Transactional
	public void deleteDictionaries(String... dictionaryIds);
	
	
	/**
	 * Delete dictionaries by category id
	 * 
	 * @param categoryId
	 */
	@Transactional
	public void deleteDictionaries(String categoryId);
	
	/**
	 * Get dictionaries by category id
	 * 
	 * @param categoryId
	 * @param pageable
	 * 
	 * @return
	 */
	public Page<Dictionary> getDictionaries(String categoryId, Pageable pageable);
	

	/**
	 * Save dictionary category
	 * 
	 * @param category
	 */
	@Transactional
	public void saveCategory(DictionaryCategory category);
	
	/**
	 * Delete dictionary category
	 * 
	 * @param categoryId
	 */
	@Transactional
	public void deleteCategory(String... categoryIds);
	
	/**
	 * Get dictionary category
	 * 
	 * @param categoryId
	 * @return
	 */
	public DictionaryCategory getCategory(String categoryId);
	
	
	/**
	 * Get dictionary categories by parent id
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	public Page<DictionaryCategory> getCategorys(String parentId, Pageable pageable);
	
}
