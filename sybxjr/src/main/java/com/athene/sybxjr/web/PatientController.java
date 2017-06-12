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
import com.athene.sybxjr.domain.AssayResult;
import com.athene.sybxjr.domain.Prefile;
import com.athene.sybxjr.domain.Prescription;
import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.domain.Result;
import com.athene.sybxjr.domain.RunCase;
import com.athene.sybxjr.domain.Settle;
import com.athene.sybxjr.domain.SettleCancel;
import com.athene.sybxjr.security.MessageProcessor;
import com.athene.sybxjr.service.AssayResultService;
import com.athene.sybxjr.service.PrefileService;
import com.athene.sybxjr.service.PrescriptionService;
import com.athene.sybxjr.service.RunCaseService;
import com.athene.sybxjr.service.SettleCancelService;
import com.athene.sybxjr.service.SettleService;

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
	
	@Autowired
	private PrescriptionService prescriptionService;

	@Autowired
	private SettleService settleService;
	
	@Autowired
	private SettleCancelService settleCancelService;
	
	@Autowired
	private RunCaseService runCaseService;
	
	@Autowired
	private AssayResultService assayResultService;
	
	@RequestMapping(path = "/insPrefile")
	@ResponseBody
	public Result insPrefile(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<Prefile> data = prefileService.getPrefiles(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@RequestMapping(path = "/prescription")
	@ResponseBody
	public Result prescription(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<Prescription> data = prescriptionService.getPrescriptions(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@RequestMapping(path = "/settle")
	@ResponseBody
	public Result settle(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<Settle> data = settleService.getSettles(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@RequestMapping(path = "/settleCancel")
	@ResponseBody
	public Result settleCancel(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<SettleCancel> data = settleCancelService.getSettleCancels(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@RequestMapping(path = "/runCase")
	@ResponseBody
	public Result runCase(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<RunCase> data = runCaseService.getRunCases(form);
		json = JSON.toJSONString(data);
		logger.info(String.format("The plaintext data is: %s", json));
		return new Result(messageProcessor.encrypt(json));
	}
	
	@RequestMapping(path = "/assayResult")
	@ResponseBody
	public Result assayResult(@RequestParam(name = "req", required = false) String requestParam) {
		Assert.hasText(requestParam, "请求参数为空。");
		String json = getJson(requestParam);
		QueryForm form = JSON.parseObject(json, QueryForm.class);
		List<AssayResult> data = assayResultService.getAssayResults(form);
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
