/**
 * 
 */
package com.athene.system.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.athene.system.domain.Dictionary;
import com.athene.system.domain.Dictionary.DictionaryId;
import com.athene.system.domain.DictionaryCategory;
import com.athene.system.repository.DictionaryCategoryRepository;
import com.athene.system.repository.DictionaryRepository;

/**
 * @author zhaochf
 *
 */
@Service
@CacheConfig(cacheNames = {"dictionaries"})
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private DictionaryCategoryRepository categoryRepository;

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#saveDictionary(com.athene.system.domain.Dictionary)
	 */
	@CacheEvict(key = "#dictionary.categoryId")
	@Override
	public Dictionary saveDictionary(Dictionary dictionary) {
		if (Optional.of(dictionary).isPresent()) {
			return dictionaryRepository.save(dictionary);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#deleteDictionaries(java.lang.String[])
	 */
	@CacheEvict(allEntries=true)
	@Override
	public void deleteDictionaries(DictionaryId... dictionaryIds) {
		Optional.of(dictionaryIds).ifPresent((ids) -> {
			List<Dictionary> dictionaries = Collections.synchronizedList(Collections.emptyList());
			Arrays.asList(ids).forEach((id) -> {
				dictionaries.add(new Dictionary(id));
			});
			dictionaryRepository.deleteInBatch(dictionaries);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#deleteDictionaries(java.lang.String)
	 */
	@CacheEvict(key = "#categoryId")
	@Override
	public void deleteDictionaries(String categoryId) {
		Optional.of(categoryId).ifPresent((id) -> {
			dictionaryRepository.deleteByCategoryId(id);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#getDictionaries(java.lang.String)
	 */
	@Cacheable(key = "#categoryId")
	@Override
	public List<Dictionary> getDictionaries(String categoryId) {
		if (Optional.of(categoryId).isPresent()) {
			return dictionaryRepository.findByCategoryId(categoryId);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#saveCategory(com.athene.system.domain.DictionaryCategory)
	 */
	@Override
	public DictionaryCategory saveCategory(DictionaryCategory category) {
		if (Optional.of(category).isPresent()) {
			return categoryRepository.save(category);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#insertCategory(com.athene.system.domain.DictionaryCategory)
	 */
	@Override
	public DictionaryCategory insertCategory(DictionaryCategory category) {
		
		if (Optional.of(category).isPresent()) {
			return categoryRepository.insertNode(category);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#deleteCategory(java.lang.String)
	 */
	@Override
	public void deleteCategory(String categoryId) {
		
		if (Optional.of(categoryId).isPresent()) {
			List<DictionaryCategory> categories = categoryRepository.findAllChildren(categoryId);
			Optional.of(categories).ifPresent(entities -> {
				entities.stream().peek(entity -> {
					deleteDictionaries(entity.getId());
				});
			});
			categoryRepository.deleteNode(categoryId);
		}
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#getCategory(java.lang.String)
	 */
	@Override
	public DictionaryCategory getCategory(String categoryId) {
		
		if (Optional.of(categoryId).isPresent()) {
			return categoryRepository.findOne(categoryId);
		}
		return null;
	}

	

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#getCategoryChildren(java.lang.String)
	 */
	@Override
	public List<DictionaryCategory> getCategoryChildren(String categoryId) {
		if (Optional.of(categoryId).isPresent()) {
			return categoryRepository.findChildren(categoryId);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#getCategoryAllChildren(java.lang.String)
	 */
	@Override
	public List<DictionaryCategory> getCategoryAllChildren(String categoryId) {
		if (Optional.of(categoryId).isPresent()) {
			return categoryRepository.findAllChildren(categoryId);
		}
		return null;
	}

	
}
