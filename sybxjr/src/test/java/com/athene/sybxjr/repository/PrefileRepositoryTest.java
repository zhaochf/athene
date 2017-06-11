package com.athene.sybxjr.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import com.alibaba.fastjson.JSON;
import com.athene.sybxjr.AbstractTests;
import com.athene.sybxjr.domain.Prefile;

public class PrefileRepositoryTest extends AbstractTests {

	@Autowired
	private PrefileRepository repository;
	
	@Transactional
	@Commit
	public void testSave() {
		
		String json = "{\"medicalNum\": \"10076226\",\"medicalType\": \"21\",\"treatDate\": \"20170119000000\",\"conditionDescription\": \"肿瘤\",\"inHosDiagnosisCode\": \"NOCODE\",\"inHosDiagnosisName\": \"肿瘤\",\"inHosDoctorCode\": \"\",\"inHosDoctorName\": \"\",\"endemicArea\": \"\",\"treatDeptCode\": \"\",\"treatDeptName\": \"\",\"hospitalizedNum\": \"500785875\",\"bunkId\": \"\",\"credentialType\": \"01\",\"credentialNum\": \"***************\",\"IDCard\": \"***************\",\"name\": \"王**\",\"gender\": \"1\",\"BOD\": \"19360518\",\"race\": \"1\",\"homeAddress\": \"\",\"contactsName\": \"\",\"contactsMobile\": \"\",\"companyName\": \"\",\"clientStatus\": \"10\",\"guardianName\": \"\",\"guardianIdType\": \"\",\"guardianIdNo\": \"\",\"updateBy\": \"住院收费\",\"inHosMedDiagnosisCode\": \"\",\"inHosMedDiagnosisName\": \"\",\"remark\": \"\",\"socialNumber\": \"\",\"isInSocialSecurityFlg\": \"\",\"socialSecurityNm\": \"\",\"currMedicalCost\": \"0.00\",\"idDueDay\": \"20101210\",\"guardianMobile\": \"\",\"guardianIDDueDay\": \"\",\"email\": \"\",\"admissionDate\": \"20170119\",\"admissionDeptCode\": \"020701\",\"admissionDeptName\": \"肿瘤科\",\"thirdCode\": \"4\"}";
		
		Prefile prefile = JSON.parseObject(json, Prefile.class);
		repository.saveAndFlush(prefile);
	}
	
	@Test
	public void testGet() {
		List<Prefile> data = repository.getPrefiles("10076224", "", "", "", "", "", "");
		System.out.println(JSON.toJSONString(data));
	}
}
