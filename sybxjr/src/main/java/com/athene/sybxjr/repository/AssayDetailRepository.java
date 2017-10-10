/**
 * 
 */
package com.athene.sybxjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.athene.sybxjr.domain.yskp.AssayDetail;

/**
 * @author zhaochf
 *
 */
public interface AssayDetailRepository extends JpaRepository<AssayDetail, String> {

	@Query("select a from AssayDetail a where a.labFlow = :labFlow")
	List<AssayDetail> getAssayDetails(@Param("labFlow") String labFlow);
}
