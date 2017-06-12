/**
 * 
 */
package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.Prefile;

/**
 * @author zhaochf
 *
 */
public interface PrefileRepository extends JpaRepository<Prefile, String> {

	/**
	 * 
	 * @param medicalNum
	 * @param hospitalizedNum
	 * @param credentialType
	 * @param credentialNum
	 * @param treatBeginDate
	 * @param treatEndDate
	 * @return
	 */
	@Query("select p from Prefile p where p.medicalNum = :medicalNum or (p.hospitalizedNum = :hospitalizedNum and p.treatDate >= :treatBeginDate and p.treatDate <= :treatEndDate) "
			+ " or (p.hospitalizedNum = :hospitalizedNum and p.name = :name and p.treatDate >= :treatBeginDate and p.treatDate <= :treatEndDate) "
			+ " or (p.credentialType = :credentialType and p.credentialNum = :credentialNum and p.treatDate >= :treatBeginDate and p.treatDate <= :treatEndDate)")
	List<Prefile> getPrefiles(@Param("medicalNum") String medicalNum, @Param("hospitalizedNum") String hospitalizedNum, @Param("credentialType") String credentialType, @Param("credentialNum") String credentialNum, 
			@Param("name") String name, @Param("treatBeginDate") String treatBeginDate, @Param("treatEndDate") String treatEndDate);
}
