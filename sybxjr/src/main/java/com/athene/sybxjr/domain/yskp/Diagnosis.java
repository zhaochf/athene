/**
 * 
 */
package com.athene.sybxjr.domain.yskp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.athene.data.domain.AbstractEntity;

/**
 * @author zhaochf
 *
 */
@Entity
@Table(name = "YSKP_DIAGNOSISLISTS")
public class Diagnosis extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6510793142403956737L;
	
	@Id
	private String diagnosisCode;
	
	private String diagnosisName;
	
	private String medicalNum;

	@Override
	public Serializable getId() {
		return diagnosisCode;
	}

	/**
	 * @return the diagnosisCode
	 */
	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	/**
	 * @param diagnosisCode the diagnosisCode to set
	 */
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}

	/**
	 * @return the diagnosisName
	 */
	public String getDiagnosisName() {
		return diagnosisName;
	}

	/**
	 * @param diagnosisName the diagnosisName to set
	 */
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	/**
	 * @return the medicalNum
	 */
	public String getMedicalNum() {
		return medicalNum;
	}

	/**
	 * @param medicalNum the medicalNum to set
	 */
	public void setMedicalNum(String medicalNum) {
		this.medicalNum = medicalNum;
	}
	
}
