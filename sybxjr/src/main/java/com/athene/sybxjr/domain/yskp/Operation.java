/**
 * 
 */
package com.athene.sybxjr.domain;

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
@Table(name = "V_OPERATION")
public class Operation extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3672603719749198488L;

	@Id
	private String operationCode;

	private String operationName;
	
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
	 * @return the operationCode
	 */
	public String getOperationCode() {
		return operationCode;
	}

	/**
	 * @param operationCode the operationCode to set
	 */
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	/**
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
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
