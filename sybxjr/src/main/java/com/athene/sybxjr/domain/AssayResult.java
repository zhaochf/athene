package com.athene.sybxjr.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.athene.data.domain.AbstractEntity;

@Entity
@Table(name = "V_ASSAYRESULT")
public class AssayResult extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3136487237281057220L;

	private String medicalNum;
	
	private String billNum;
	
	private String labNum;
	
	@Id
	private String labFlow;
	
	private String serviceClassification;
	
	private String categoryName;
	
	private String findings;
	
	private String result;
	
	private String patName;
	
	private String gender;
	
	private String patType;
	
	private String patAge;
	
	private String ageUnit;
	
	private String dlagNose;
	
	private String deptNum;
	
	private String deptName;
	
	private String applyDoctorCode;
	
	private String applyDoctorName;
	
	private String bunkId;
	
	private String applyDate;
	
	private String reportDate;
	
	private String confirmDate;
	
	private String reportDoctorCode;
	
	private String reportDoctorName;
	
	private String confirmDoctorCode;
	
	private String confirmDoctorName;
	
	private String chargeType;
	
	private String sampleType;
	
	private String remark;
	
	private String instrna;
	
	private String instrKind;
	
	private String instrKindName;
	
	private String insertFlag;
	
	@Transient
	private List<AssayDetail> assayDetail;
	
	@Transient
	private String total;
	
	
	@Override
	public Serializable getId() {
		return null;
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
	 * @return the billNum
	 */
	public String getBillNum() {
		return billNum;
	}


	/**
	 * @param billNum the billNum to set
	 */
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}


	/**
	 * @return the labNum
	 */
	public String getLabNum() {
		return labNum;
	}


	/**
	 * @param labNum the labNum to set
	 */
	public void setLabNum(String labNum) {
		this.labNum = labNum;
	}


	/**
	 * @return the labFlow
	 */
	public String getLabFlow() {
		return labFlow;
	}


	/**
	 * @param labFlow the labFlow to set
	 */
	public void setLabFlow(String labFlow) {
		this.labFlow = labFlow;
	}


	/**
	 * @return the serviceClassification
	 */
	public String getServiceClassification() {
		return serviceClassification;
	}


	/**
	 * @param serviceClassification the serviceClassification to set
	 */
	public void setServiceClassification(String serviceClassification) {
		this.serviceClassification = serviceClassification;
	}


	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}


	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	/**
	 * @return the findings
	 */
	public String getFindings() {
		return findings;
	}


	/**
	 * @param findings the findings to set
	 */
	public void setFindings(String findings) {
		this.findings = findings;
	}


	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}


	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}


	/**
	 * @return the patName
	 */
	public String getPatName() {
		return patName;
	}


	/**
	 * @param patName the patName to set
	 */
	public void setPatName(String patName) {
		this.patName = patName;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the patType
	 */
	public String getPatType() {
		return patType;
	}


	/**
	 * @param patType the patType to set
	 */
	public void setPatType(String patType) {
		this.patType = patType;
	}


	/**
	 * @return the patAge
	 */
	public String getPatAge() {
		return patAge;
	}


	/**
	 * @param patAge the patAge to set
	 */
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}


	/**
	 * @return the ageUnit
	 */
	public String getAgeUnit() {
		return ageUnit;
	}


	/**
	 * @param ageUnit the ageUnit to set
	 */
	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}


	/**
	 * @return the dlagNose
	 */
	public String getDlagNose() {
		return dlagNose;
	}


	/**
	 * @param dlagNose the dlagNose to set
	 */
	public void setDlagNose(String dlagNose) {
		this.dlagNose = dlagNose;
	}


	/**
	 * @return the deptNum
	 */
	public String getDeptNum() {
		return deptNum;
	}


	/**
	 * @param deptNum the deptNum to set
	 */
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}


	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}


	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	/**
	 * @return the applyDoctorCode
	 */
	public String getApplyDoctorCode() {
		return applyDoctorCode;
	}


	/**
	 * @param applyDoctorCode the applyDoctorCode to set
	 */
	public void setApplyDoctorCode(String applyDoctorCode) {
		this.applyDoctorCode = applyDoctorCode;
	}


	/**
	 * @return the applyDoctorName
	 */
	public String getApplyDoctorName() {
		return applyDoctorName;
	}


	/**
	 * @param applyDoctorName the applyDoctorName to set
	 */
	public void setApplyDoctorName(String applyDoctorName) {
		this.applyDoctorName = applyDoctorName;
	}


	/**
	 * @return the bunkId
	 */
	public String getBunkId() {
		return bunkId;
	}


	/**
	 * @param bunkId the bunkId to set
	 */
	public void setBunkId(String bunkId) {
		this.bunkId = bunkId;
	}


	/**
	 * @return the applyDate
	 */
	public String getApplyDate() {
		return applyDate;
	}


	/**
	 * @param applyDate the applyDate to set
	 */
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}


	/**
	 * @return the reportDate
	 */
	public String getReportDate() {
		return reportDate;
	}


	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}


	/**
	 * @return the confirmDate
	 */
	public String getConfirmDate() {
		return confirmDate;
	}


	/**
	 * @param confirmDate the confirmDate to set
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}


	/**
	 * @return the reportDoctorCode
	 */
	public String getReportDoctorCode() {
		return reportDoctorCode;
	}


	/**
	 * @param reportDoctorCode the reportDoctorCode to set
	 */
	public void setReportDoctorCode(String reportDoctorCode) {
		this.reportDoctorCode = reportDoctorCode;
	}


	/**
	 * @return the reportDoctorName
	 */
	public String getReportDoctorName() {
		return reportDoctorName;
	}


	/**
	 * @param reportDoctorName the reportDoctorName to set
	 */
	public void setReportDoctorName(String reportDoctorName) {
		this.reportDoctorName = reportDoctorName;
	}


	/**
	 * @return the confirmDoctorCode
	 */
	public String getConfirmDoctorCode() {
		return confirmDoctorCode;
	}


	/**
	 * @param confirmDoctorCode the confirmDoctorCode to set
	 */
	public void setConfirmDoctorCode(String confirmDoctorCode) {
		this.confirmDoctorCode = confirmDoctorCode;
	}


	/**
	 * @return the confirmDoctorName
	 */
	public String getConfirmDoctorName() {
		return confirmDoctorName;
	}


	/**
	 * @param confirmDoctorName the confirmDoctorName to set
	 */
	public void setConfirmDoctorName(String confirmDoctorName) {
		this.confirmDoctorName = confirmDoctorName;
	}


	/**
	 * @return the chargeType
	 */
	public String getChargeType() {
		return chargeType;
	}


	/**
	 * @param chargeType the chargeType to set
	 */
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	/**
	 * @return the sampleType
	 */
	public String getSampleType() {
		return sampleType;
	}


	/**
	 * @param sampleType the sampleType to set
	 */
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the instrna
	 */
	public String getInstrna() {
		return instrna;
	}


	/**
	 * @param instrna the instrna to set
	 */
	public void setInstrna(String instrna) {
		this.instrna = instrna;
	}


	/**
	 * @return the instrKind
	 */
	public String getInstrKind() {
		return instrKind;
	}


	/**
	 * @param instrKind the instrKind to set
	 */
	public void setInstrKind(String instrKind) {
		this.instrKind = instrKind;
	}


	/**
	 * @return the instrKindName
	 */
	public String getInstrKindName() {
		return instrKindName;
	}


	/**
	 * @param instrKindName the instrKindName to set
	 */
	public void setInstrKindName(String instrKindName) {
		this.instrKindName = instrKindName;
	}


	/**
	 * @return the insertFlag
	 */
	public String getInsertFlag() {
		return insertFlag;
	}


	/**
	 * @param insertFlag the insertFlag to set
	 */
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}


	/**
	 * @return the assayDetail
	 */
	public List<AssayDetail> getAssayDetail() {
		return assayDetail;
	}


	/**
	 * @param assayDetail the assayDetail to set
	 */
	public void setAssayDetail(List<AssayDetail> assayDetail) {
		this.assayDetail = assayDetail;
	}
	
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	
}
