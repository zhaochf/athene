package com.athene.sybxjr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.AssayResult;

public interface AssayResultRepository extends JpaRepository<AssayResult, String> {

	@Query("select a from AssayResult a where a.medicalNum = :medicalNum")
	Page<AssayResult> getAssayResults(@Param("medicalNum") String medicalNum, Pageable pageable);
}
