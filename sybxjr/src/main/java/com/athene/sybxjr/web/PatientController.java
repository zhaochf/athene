/**
 * 
 */
package com.athene.sybxjr.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.athene.sybxjr.domain.Prefile;
import com.athene.sybxjr.domain.PrefileForm;
import com.athene.sybxjr.domain.Result;
import com.athene.sybxjr.security.MessageProcessor;
import com.athene.sybxjr.service.PrefileService;

/**
 * @author zhaochf
 *
 */
@Controller
@RequestMapping("/ins")
public class PatientController {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private MessageProcessor messageProcessor;
	
	@Autowired
	private PrefileService prefileService;

	@RequestMapping(path = "/insPrefile")
	@ResponseBody
	public Result insPrefile(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		PrefileForm form = JSON.parseObject(json, PrefileForm.class);
		List<Prefile> data = prefileService.getPrefiles(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@ExceptionHandler
	@ResponseBody
	public Result handleException(Exception e) {
		return new Result("2", e.getMessage());
	}
	
	private String getJson(String requestParam) {
		logger.info(String.format("The request param ciphertext is: %s", requestParam));
		
		Assert.hasText(requestParam, "请求参数为空。");
		String json = messageProcessor.decrypt(requestParam);
		
		logger.info(String.format("The request param plaintext is: %s", json));
		return json;
	}
	
}
