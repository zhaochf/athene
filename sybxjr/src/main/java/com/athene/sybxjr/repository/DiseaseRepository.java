package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, String> {

	@Query("select d from Disease d where d.hospitalRecordId = :hospitalRecordId")
	List<Disease> getDiseases(@Param("hospitalRecordId") String hospitalRecordId);
}
