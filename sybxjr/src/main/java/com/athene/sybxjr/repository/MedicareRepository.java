package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.Medicare;

public interface MedicareRepository extends JpaRepository<Medicare, String> {

	@Query("select m from Medicare m where m.billNum = :billNum")
	List<Medicare> getMedicares(@Param("billNum") String billNum);
}
