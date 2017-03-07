/**
 * 
 */
package com.athene.system.service;

import java.util.List;

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
	 * @return
	 */
	@Transactional
	public Dictionary saveDictionary(Dictionary dictionary);
	
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
	 * @return
	 */
	@Transactional
	public DictionaryCategory saveCategory(DictionaryCategory category);
	
	/**
	 * Insert dictionary category
	 * 
	 * @param category
	 * @return
	 */
	@Transactional
	public DictionaryCategory insertCategory(DictionaryCategory category);
	
	/**
	 * Delete dictionary category
	 * 
	 * @param categoryId
	 */
	@Transactional
	public void deleteCategory(String categoryId);
	
	/**
	 * Get dictionary category
	 * 
	 * @param categoryId
	 * @return
	 */
	public DictionaryCategory getCategory(String categoryId);
	
	
	/**
	 * Get dictionary category children by category id
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<DictionaryCategory> getCategoryChildren(String categoryId);
	
	/**
	 * Get dictionary category all children by category id
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<DictionaryCategory> getCategoryAllChildren(String categoryId);
	
}
