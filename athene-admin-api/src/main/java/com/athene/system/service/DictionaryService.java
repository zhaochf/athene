/**
 * 
 */
package com.athene.system.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.athene.system.domain.Dictionary;
import com.athene.system.domain.DictionaryCategory;

/**
 * Dictionary service
 * 
 * @author zhaochf
 *
 */
public interface DictionaryService {

	/**
	 * Save dictionary
	 * 
	 * @param dictionary
	 */
	public void saveDictionary(Dictionary dictionary);
	
	/**
	 * Delete dictionaries
	 * 
	 * @param dictionaryIds
	 */
	public void deleteDictionaries(String... dictionaryIds);
	
	
	/**
	 * Delete dictionaries for category id
	 * 
	 * @param categoryId
	 */
	public void deleteDictionaries(String categoryId);
	
	/**
	 * Get dictionaries for category id
	 * 
	 * @param categoryId
	 * @return
	 */
	public Page<Dictionary> getDictionaries(String categoryId);
	

	/**
	 * Save dictionary category
	 * 
	 * @param category
	 */
	public void saveCategory(DictionaryCategory category);
	
	/**
	 * Delete dictionary category
	 * 
	 * @param categoryId
	 */
	public void deleteCategory(String... categoryId);
	
	/**
	 * Get dictionary category
	 * 
	 * @param categoryId
	 * @return
	 */
	public DictionaryCategory getCategory(String categoryId);
	
	
	/**
	 * Get dictionary categories for parent id
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	public List<DictionaryCategory> getCategorys(String parentId, Pageable pageable);
	
}
