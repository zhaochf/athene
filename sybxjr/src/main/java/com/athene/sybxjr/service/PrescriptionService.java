/**
 * 
 */
package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.domain.yskp.Prescription;
import com.athene.sybxjr.repository.PrescriptionRepository;

/**
 * @author zhaochf
 *
 */
@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;

	public List<Prescription> getPrescriptions(QueryForm form) {

		Page<Prescription> prescriptions = prescriptionRepository.getPrescriptions(form.getMedicalNum(),
				new PageRequest(form.getPageNumber(), form.getSizeNumber()));
		
		prescriptions.forEach((Prescription prescription) -> {
			prescription.setTotal(String.valueOf(prescriptions.getTotalElements()));
			
		});
		
		return prescriptions.getContent();
	}
}
