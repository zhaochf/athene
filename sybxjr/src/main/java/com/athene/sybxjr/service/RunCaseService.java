package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.domain.yskp.Disease;
import com.athene.sybxjr.domain.yskp.Operation;
import com.athene.sybxjr.domain.yskp.RunCase;
import com.athene.sybxjr.repository.DiseaseRepository;
import com.athene.sybxjr.repository.OperationRepository;
import com.athene.sybxjr.repository.RunCaseRepository;

@Service
public class RunCaseService {

	@Autowired
	private RunCaseRepository runCaseRepository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	public List<RunCase> getRunCases(QueryForm form) {
		List<RunCase> runCases = runCaseRepository.getRunCases(form.getMedicalNum());
		runCases.forEach((RunCase runCase) -> {
			List<Disease> diseases = diseaseRepository.getDiseases(runCase.getHospitalRecordId());
			List<Operation> operations = operationRepository.getOperations(runCase.getHospitalRecordId());
			
			runCase.setDiseaseList(diseases);
			runCase.setOperationList(operations);
		});
		
		return runCases;
	}
}
