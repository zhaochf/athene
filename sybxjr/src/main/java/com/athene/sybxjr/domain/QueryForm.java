/**
 * 
 */
package com.athene.sybxjr.domain;

import org.springframework.util.StringUtils;

/**
 * @author zhaochf
 *
 */
public class QueryForm {

	private String medicalNum;

	private String hospitalizedNum;

	private String credentialType;

	private String credentialNum;

	private String name;

	private String treatBeginDate;

	private String treatEndDate;
	
	private String page;

	private String perpage;

	private String invoiceNO;
	
	private String beginTime;
	
	private String endTime;
	
	/**
	 * @return the medicalNum
	 */
	public String getMedicalNum() {
		return medicalNum;
	}

	/**
	 * @param medicalNum
	 *            the medicalNum to set
	 */
	public void setMedicalNum(String medicalNum) {
		this.medicalNum = medicalNum;
	}

	/**
	 * @return the hospitalizedNum
	 */
	public String getHospitalizedNum() {
		return hospitalizedNum;
	}

	/**
	 * @param hospitalizedNum
	 *            the hospitalizedNum to set
	 */
	public void setHospitalizedNum(String hospitalizedNum) {
		this.hospitalizedNum = hospitalizedNum;
	}

	/**
	 * @return the credentialType
	 */
	public String getCredentialType() {
		return credentialType;
	}

	/**
	 * @param credentialType
	 *            the credentialType to set
	 */
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	/**
	 * @return the credentialNum
	 */
	public String getCredentialNum() {
		return credentialNum;
	}

	/**
	 * @param credentialNum
	 *            the credentialNum to set
	 */
	public void setCredentialNum(String credentialNum) {
		this.credentialNum = credentialNum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the treatBeginDate
	 */
	public String getTreatBeginDate() {
		return treatBeginDate;
	}

	/**
	 * @param treatBeginDate
	 *            the treatBeginDate to set
	 */
	public void setTreatBeginDate(String treatBeginDate) {
		this.treatBeginDate = treatBeginDate;
	}

	/**
	 * @return the treatEndDate
	 */
	public String getTreatEndDate() {
		return treatEndDate;
	}

	/**
	 * @param treatEndDate
	 *            the treatEndDate to set
	 */
	public void setTreatEndDate(String treatEndDate) {
		this.treatEndDate = treatEndDate;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the perpage
	 */
	public String getPerpage() {
		return perpage;
	}

	/**
	 * @param perpage the perpage to set
	 */
	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}

	public int getPageNumber() {
		return StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
	}
	
	public int getSizeNumber() {
		return StringUtils.isEmpty(perpage) ? 100 : Integer.parseInt(perpage);
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
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
