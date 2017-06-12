package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.Settle;

public interface SettleRepository extends JpaRepository<Settle, String> {

	@Query("select s from Settle s where s.medicalNum = :medicalNum")
	List<Settle> getSettles(@Param("medicalNum") String medicalNum);
}
