package com.athene.sybxjr.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

	@Query("select p from Prescription p where p.medicalNum = :medicalNum")
	Page<Prescription> getPrescriptions(@Param("medicalNum") String medicalNum, Pageable pageable);
}
