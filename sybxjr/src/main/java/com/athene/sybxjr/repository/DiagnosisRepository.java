/**
 * 
 */
package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.Diagnosis;

/**
 * @author zhaochf
 *
 */
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String> {

	@Query("select d from Diagnosis d where d.medicalNum = :medicalNum")
	List<Diagnosis> getDiagnosises(@Param("medicalNum") String medicalNum);
}
