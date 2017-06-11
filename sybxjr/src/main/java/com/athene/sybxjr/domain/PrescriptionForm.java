package com.athene.sybxjr.domain;

public class PrescriptionForm {

	private String medicalNum;
	
	private String page;
	
	private String perpage;

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
	
	
}
