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
import com.athene.system.domain.DictionaryCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryServiceTests extends AbstractTests {
	
	@Autowired
	private DictionaryService dictionaryService;
	
//	@Test
	public void test0Deletecategory() {
		dictionaryService.deleteCategory("#");
	}

	@Test
	public void test1SaveCategory() {
		getDictionaryCategories().forEach((category) -> {
			dictionaryService.insertCategory(category);
		});
	}
	
	@Test
	public void test2GetCategoryChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryChildren("#");
		assertThat(2, equalTo(categories.size()));
	}
	
	@Test
	public void test3GetCategroyAllChildren() {
		List<DictionaryCategory> categories = dictionaryService.getCategoryAllChildren("1001");
		assertThat(5, equalTo(categories.size()));
	}

	
	private List<DictionaryCategory> getDictionaryCategories() {
		List<DictionaryCategory> categories = new ArrayList<DictionaryCategory>();
		
		DictionaryCategory entity = new DictionaryCategory("1001");
		entity.setParentId("#");
		entity.setName("Fruit");
		entity.setDescription("Fruit");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1002");
		entity.setParentId("1001");
		entity.setName("Red");
		entity.setDescription("Red");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1003");
		entity.setParentId("1002");
		entity.setName("Cherry");
		entity.setDescription("Cherry");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1004");
		entity.setParentId("1002");
		entity.setName("Apple");
		entity.setDescription("Apple");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1005");
		entity.setParentId("1001");
		entity.setName("Yellow");
		entity.setDescription("Yellow");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1006");
		entity.setParentId("1005");
		entity.setName("Banana");
		entity.setDescription("Banana");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1007");
		entity.setParentId("#");
		entity.setName("Meat");
		entity.setDescription("Meat");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		entity = new DictionaryCategory("1008");
		entity.setParentId("1007");
		entity.setName("Pork");
		entity.setDescription("Pork");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());
		categories.add(entity);
		
		return categories;

	}

}
