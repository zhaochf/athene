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
@Table(name = "YSKP_DISEASE")
public class Disease extends AbstractEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159303730680180123L;

	@Id
	private String diseaseCode;
	
	private String diseaseName;
	
	private String hospitalRecordId;
	

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the diseaseCode
	 */
	public String getDiseaseCode() {
		return diseaseCode;
	}


	/**
	 * @param diseaseCode the diseaseCode to set
	 */
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}


	/**
	 * @return the diseaseName
	 */
	public String getDiseaseName() {
		return diseaseName;
	}


	/**
	 * @param diseaseName the diseaseName to set
	 */
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}


	/**
	 * @return the hospitalRecordId
	 */
	public String getHospitalRecordId() {
		return hospitalRecordId;
	}


	/**
	 * @param hospitalRecordId the hospitalRecordId to set
	 */
	public void setHospitalRecordId(String hospitalRecordId) {
		this.hospitalRecordId = hospitalRecordId;
	}
	
	

}
