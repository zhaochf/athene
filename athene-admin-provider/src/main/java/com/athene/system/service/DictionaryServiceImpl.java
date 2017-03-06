/**
 * 
 */
package com.athene.system.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.athene.system.domain.Dictionary;
import com.athene.system.domain.DictionaryCategory;
import com.athene.system.repository.DictionaryCategoryRepository;
import com.athene.system.repository.DictionaryRepository;

/**
 * @author zhaochf
 *
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private DictionaryCategoryRepository categoryRepository;

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#saveDictionary(com.athene.system.domain.Dictionary)
	 */
	@Override
	public void saveDictionary(Dictionary dictionary) {
		Optional.of(dictionary).ifPresent((entity) -> {
			dictionaryRepository.save(entity);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#deleteDictionaries(java.lang.String[])
	 */
	@Override
	public void deleteDictionaries(String... dictionaryIds) {
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
	@Override
	public void deleteDictionaries(String categoryId) {
		Optional.of(categoryId).ifPresent((id) -> {
			dictionaryRepository.deleteByCategoryId(id);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#getDictionaries(java.lang.String)
	 */
	@Override
	public Page<Dictionary> getDictionaries(String categoryId, Pageable pageable) {
		if (Optional.of(categoryId).isPresent()) {
			return dictionaryRepository.findByCategoryId(categoryId, pageable);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#saveCategory(com.athene.system.domain.DictionaryCategory)
	 */
	@Override
	public void saveCategory(DictionaryCategory category) {
		Optional.of(category).ifPresent((entity) -> {
			categoryRepository.save(entity);
		});
	}

	/* (non-Javadoc)
	 * @see com.athene.system.service.DictionaryService#deleteCategory(java.lang.String[])
	 */
	@Override
	public void deleteCategory(String... categoryIds) {
		Optional.of(categoryIds).ifPresent((ids) -> {
			List<DictionaryCategory> dictionaries = Collections.synchronizedList(Collections.emptyList());
			Arrays.asList(ids).forEach((id) -> {
				dictionaries.add(new DictionaryCategory(id));
				dictionaryRepository.deleteByCategoryId(id);
			});
			categoryRepository.deleteInBatch(dictionaries);
		});
		
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
	 * @see com.athene.system.service.DictionaryService#getCategorys(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<DictionaryCategory> getCategorys(String parentId, Pageable pageable) {
		if (Optional.of(parentId).isPresent()) {
			return categoryRepository.findByParentId(parentId, pageable);
		}
		return null;
	}

}
