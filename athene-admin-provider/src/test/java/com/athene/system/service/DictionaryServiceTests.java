package com.athene.system.service;

import java.time.ZonedDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.athene.AbstractTests;
import com.athene.system.domain.DictionaryCategory;

public class DictionaryServiceTests extends AbstractTests {
	
	@Autowired
	private DictionaryService dictionaryService;

	@Test
	public void test01SaveCategory() {
		
		dictionaryService.saveCategory(getDictionaryCategory());
	}


	private DictionaryCategory getDictionaryCategory() {
		DictionaryCategory entity = new DictionaryCategory("1001");
		entity.setParentId("#");
		entity.setName("业务字典");
		entity.setLeftLimit(2);
		entity.setRightLimit(3);
		entity.setLevel(2);
		entity.setIsLeaf(true);
		entity.setOrderNumber(1);
		entity.setDescription("业务字典");
		entity.setCreatedBy("admin");
		entity.setCreatedDate(ZonedDateTime.now());
		entity.setLastModifiedBy("admin");
		entity.setLastModifiedDate(ZonedDateTime.now());

		return entity;

	}

}
