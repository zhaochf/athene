/**
 * 
 */
package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.athene.sybxjr.domain.Diagnosis;
import com.athene.sybxjr.domain.Prefile;
import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.repository.DiagnosisRepository;
import com.athene.sybxjr.repository.PrefileRepository;

/**
 * @author zhaochf
 *
 */
@Service
public class PrefileService {

	@Autowired
	private PrefileRepository prefileRepository;

	@Autowired
	private DiagnosisRepository diagnosisRepository;
	
	public List<Prefile> getPrefiles(QueryForm form) {
		List<Prefile> prefiles = prefileRepository.getPrefiles(form.getMedicalNum(), form.getHospitalizedNum(),
				form.getCredentialType(), form.getCredentialNum(), form.getName(), form.getTreatBeginDate(),
				form.getTreatEndDate());
		if (CollectionUtils.isEmpty(prefiles)) {
			return prefiles;
		}
		
		for (Prefile prefile : prefiles) {
			List<Diagnosis> diagnosises = diagnosisRepository.getDiagnosises(prefile.getMedicalNum());
			prefile.setDiagnosisLists(diagnosises);
		}
		return prefiles;
	}
}
