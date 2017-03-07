package com.athene.system.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.athene.AbstractTests;
import com.athene.system.domain.DictionaryCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryServiceTests extends AbstractTests {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Test
	public void test0Deletecategory() {
		dictionaryService.deleteCategory("1001");
	}

//	@Test
	public void test1SaveCategory() {
		
		dictionaryService.saveCategory(getDictionaryCategory());
	}
	
//	@Test
	public void test2GetCategoryChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryChildren("#");
		assertThat(1, equalTo(categories.size()));
	}
	
//	@Test
	public void test3GetCategroyAllChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryAllChildren("#");
		assertThat(8, equalTo(categories.size()));
	}

	
	private DictionaryCategory getDictionaryCategory() {
		DictionaryCategory entity = new DictionaryCategory("1008");
		entity.setParentId("1006");
		entity.setName("Pork");
		entity.setDescription("Pork");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());

		return entity;

	}

}
