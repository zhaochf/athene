package com.athene.sybxjr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athene.sybxjr.domain.QueryForm;
import com.athene.sybxjr.domain.yskp.Medicare;
import com.athene.sybxjr.domain.yskp.Settle;
import com.athene.sybxjr.repository.MedicareRepository;
import com.athene.sybxjr.repository.SettleRepository;

@Service
public class SettleService {

	@Autowired
	private SettleRepository settleRepository;
	
	private MedicareRepository medicareRepository;
	
	public List<Settle> getSettles(QueryForm form) {
		
		List<Settle> settles = settleRepository.getSettles(form.getMedicalNum());
		settles.forEach((Settle settle) -> {
			
			List<Medicare> medicares = medicareRepository.getMedicares(settle.getBillNum());
			settle.setMedicareList(medicares);
		});
		
		return settles;
	}
}
