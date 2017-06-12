package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.SettleCancel;

public interface SettleCancelRepository extends JpaRepository<SettleCancel, String> {

	@Query("select s from SettleCancel s where s.medicalNum = :medicalNum and invoiceNO = :invoiceNO")
	List<SettleCancel> getSettleCancels(@Param("medicalNum") String medicalNum, @Param("invoiceNO") String invoiceNO);

	@Query("select s from SettleCancel s where s.medicalNum = :medicalNum and invoiceNO = :invoiceNO and s.revokeDate >= :beginTime and s.revokeDate <= :endTime")
	List<SettleCancel> getSettleCancels(@Param("medicalNum") String medicalNum, @Param("invoiceNO") String invoiceNO,
			@Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
