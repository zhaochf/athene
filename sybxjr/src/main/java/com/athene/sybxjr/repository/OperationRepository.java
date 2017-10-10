package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.Operation;

public interface OperationRepository extends JpaRepository<Operation, String> {

	@Query("select o from Operation o where o.hospitalRecordId = :hospitalRecordId")
	List<Operation> getOperations(@Param("hospitalRecordId") String hospitalRecordId);
}
