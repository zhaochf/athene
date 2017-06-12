package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.athene.sybxjr.domain.AssayDetail;
import com.athene.sybxjr.domain.AssayResult;
import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.repository.AssayDetailRepository;
import com.athene.sybxjr.repository.AssayResultRepository;

@Service
public class AssayResultService {

	@Autowired
	private AssayResultRepository assayResultRepository;

	@Autowired
	private AssayDetailRepository assayDetailRepository;

	public List<AssayResult> getAssayResults(QueryForm form) {
		Page<AssayResult> assayResults = assayResultRepository.getAssayResults(form.getMedicalNum(),
				new PageRequest(form.getPageNumber(), form.getPageNumber()));
		
		assayResults.forEach((AssayResult assayResult) -> {
			List<AssayDetail> assayDetails = assayDetailRepository.getAssayDetails(assayResult.getLabFlow());
			assayResult.setAssayDetail(assayDetails);
			assayResult.setTotal(String.valueOf(assayResults.getTotalElements()));
		});

		return assayResults.getContent();
	}
}
