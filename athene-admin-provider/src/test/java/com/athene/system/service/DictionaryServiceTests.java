package com.athene.system.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.athene.AbstractTests;
import com.athene.system.domain.Dictionary;
import com.athene.system.domain.Dictionary.DictionaryId;
import com.athene.system.domain.DictionaryCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryServiceTests extends AbstractTests {
	
	@Autowired
	private DictionaryService dictionaryService;

//	@Test
	public void test1SaveCategory() {
		getDictionaryCategories().forEach((category) -> {
			dictionaryService.insertCategory(category);
		});
	}
	
//	@Test
	public void test2GetCategoryChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryChildren("#");
		assertThat(1, equalTo(categories.size()));
	}
	
//	@Test
	public void test3GetCategroyAllChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryAllChildren("#");
		assertThat(2, equalTo(categories.size()));
	}
	
	@Test
	public void test4SaveDictionaries() {
		Dictionary dictionary = new Dictionary(new DictionaryId("1", "SYS-0001"));
		dictionary.setName("是");
		dictionary.setDescription(dictionary.getName());
		dictionary.setCreatedBy("admin");
		dictionary.setLastModifiedBy(dictionary.getCreatedBy());
		dictionaryService.saveDictionary(dictionary);
		
		dictionary = new Dictionary(new DictionaryId("0", "SYS-0001"));
		dictionary.setName("否");
		dictionary.setDescription(dictionary.getName());
		dictionary.setCreatedBy("admin");
		dictionary.setLastModifiedBy(dictionary.getCreatedBy());
		dictionaryService.saveDictionary(dictionary);
	}
	
	@Test
	public void test5GetDictionaries() {
		List<Dictionary> dictionaries = dictionaryService.getDictionaries("SYS-0001");
		assertThat(2, equalTo(dictionaries.size()));
		
		logger.debug("Test get dictionaries again...");
		dictionaries = dictionaryService.getDictionaries("SYS-0001");
		assertThat(2, equalTo(dictionaries.size()));
	}
	

	private List<DictionaryCategory> getDictionaryCategories() {
		List<DictionaryCategory> categories = new ArrayList<DictionaryCategory>();
		
		DictionaryCategory entity = new DictionaryCategory("SYS-0001");
		entity.setParentId("#");
		entity.setName("系统");
		entity.setDescription("系统内置字典");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("SYS-0001001");
		entity.setParentId("SYS-0001");
		entity.setName("布尔");
		entity.setDescription("布尔型字典, 1=是(true), 0=否(false)");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		return categories;

	}

}
