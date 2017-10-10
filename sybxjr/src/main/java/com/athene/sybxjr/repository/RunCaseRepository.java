package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.RunCase;

public interface RunCaseRepository extends JpaRepository<RunCase, String> {

	@Query("select r from RunCase r where r.medicalNum = :medicalNum")
	List<RunCase> getRunCases(@Param("medicalNum") String medicalNum);
	
}
