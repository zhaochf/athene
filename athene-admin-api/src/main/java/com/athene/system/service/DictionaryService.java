/**
 * 
 */
package com.athene.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.athene.system.domain.Dictionary;
import com.athene.system.domain.Dictionary.DictionaryId;
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
	public Dictionary saveDictionary(Dictionary dictionary);
	
	/**
	 * Delete dictionaries
	 * 
	 * @param dictionaryIds
	 */
	public void deleteDictionaries(DictionaryId... dictionaryIds);
	
	
	/**
	 * Delete dictionaries by category id
	 * 
	 * @param categoryId
	 */
	public void deleteDictionaries(String categoryId);
	
	/**
	 * Get dictionaries by category id
	 * 
	 * @param categoryId
	 * 
	 * @return
	 */
	public List<Dictionary> getDictionaries(String categoryId);
	
	/**
	 * Save dictionary category
	 * 
	 * @param category
	 * @return
	 */
	public DictionaryCategory saveCategory(DictionaryCategory category);
	
	/**
	 * Insert dictionary category
	 * 
	 * @param category
	 * @return
	 */
	public DictionaryCategory insertCategory(DictionaryCategory category);
	
	/**
	 * Delete dictionary category
	 * 
	 * @param categoryId
	 */
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
