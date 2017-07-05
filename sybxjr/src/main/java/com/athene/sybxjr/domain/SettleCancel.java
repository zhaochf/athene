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
@Table(name = "V_SETTLECANCEL")
public class SettleCancel extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14369769588002296L;

	@Id
	private String medicalNum;
	
	private String invoiceNO;
	
	private String isRefund;
	
	private String refundReason;
	
	private String revokeDate;
	
	private String updateBy;
	
	private String medicalType;

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		return getMedicalNum();
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

	/**
	 * @return the invoiceNO
	 */
	public String getInvoiceNO() {
		return invoiceNO;
	}

	/**
	 * @param invoiceNO the invoiceNO to set
	 */
	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	/**
	 * @return the isRefund
	 */
	public String getIsRefund() {
		return isRefund;
	}

	/**
	 * @param isRefund the isRefund to set
	 */
	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
	
	/**
	 * @return the refundReason
	 */
	public String getRefundReason() {
		return refundReason;
	}

	/**
	 * @param refundReason the refundReason to set
	 */
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	/**
	 * @return the revokeDate
	 */
	public String getRevokeDate() {
		return revokeDate;
	}

	/**
	 * @param revokeDate the revokeDate to set
	 */
	public void setRevokeDate(String revokeDate) {
		this.revokeDate = revokeDate;
	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the medicalType
	 */
	public String getMedicalType() {
		return medicalType;
	}

	/**
	 * @param medicalType the medicalType to set
	 */
	public void setMedicalType(String medicalType) {
		this.medicalType = medicalType;
	}
	
	
}
