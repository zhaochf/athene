package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.domain.yskp.SettleCancel;
import com.athene.sybxjr.repository.SettleCancelRepository;

@Service
public class SettleCancelService {

	@Autowired
	private SettleCancelRepository cancelRepository;
	
	public List<SettleCancel> getSettleCancels(QueryForm form) {
		if (StringUtils.isEmpty(form.getBeginTime()) || StringUtils.isEmpty(form.getEndTime())) {
			return cancelRepository.getSettleCancels(form.getMedicalNum(), form.getInvoiceNO());
		}
		return cancelRepository.getSettleCancels(form.getMedicalNum(), form.getInvoiceNO(), form.getBeginTime(), form.getEndTime());
	}
}
