/**
 * 
 */
package com.athene.sybxjr.domain.yskp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.athene.data.domain.AbstractEntity;

/**
 * @author zhaochf
 *
 */
@Entity
@Table(name = "YSKP_INSPREFILE")
public class Prefile extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224537924990966933L;
	
	@Id
	private String medicalNum;
	
	private String medicalType;
	
	private String treatDate;
	
	private String conditionDescription;
	
	private String inHosDiagnosisName;
	
	private String inHosDoctorCode;
	
	private String inHosDoctorName;
	
	private String endemicArea;
	
	private String treatDeptCode;
	
	private String treatDeptName;
	
	private String hospitalizedNum;
	
	private String bunkId;
	
	private String credentialType;
	
	private String credentialNum;
	
	private String IDCard;
	
	private String name;
	
	private String gender;
	
	private String BOD;
	
	private String race;
	
	private String homeAddress;
	
	private String contactsName;
	
	private String contactsMobile;
	
	private String companyName;
	
	private String clientStatus;
	
	private String guardianName;
	
	private String guardianIdType;
	
	private String guardianIdNo;
	
	private String updateBy;
	
	private String remark;
	
	private String currMedicalCost;
	
	private String idDueDay;
	
	private String guardianMobile;
	
	private String guardianIDDueDay;
	
	private String email;
	
	private String thirdCode;
	
	private String normInHosDiagnosisCode;
	
	private String normInHosDiagnosisName;
	
	private String ticketsFrom;
	
	@Transient
	private List<Diagnosis> diagnosisLists = null;

	
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


	/**
	 * @return the treatDate
	 */
	public String getTreatDate() {
		return treatDate;
	}


	/**
	 * @param treatDate the treatDate to set
	 */
	public void setTreatDate(String treatDate) {
		this.treatDate = treatDate;
	}


	/**
	 * @return the conditionDescription
	 */
	public String getConditionDescription() {
		return conditionDescription;
	}


	/**
	 * @param conditionDescription the conditionDescription to set
	 */
	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}


	/**
	 * @return the inHosDiagnosisName
	 */
	public String getInHosDiagnosisName() {
		return inHosDiagnosisName;
	}


	/**
	 * @param inHosDiagnosisName the inHosDiagnosisName to set
	 */
	public void setInHosDiagnosisName(String inHosDiagnosisName) {
		this.inHosDiagnosisName = inHosDiagnosisName;
	}


	/**
	 * @return the inHosDoctorCode
	 */
	public String getInHosDoctorCode() {
		return inHosDoctorCode;
	}


	/**
	 * @param inHosDoctorCode the inHosDoctorCode to set
	 */
	public void setInHosDoctorCode(String inHosDoctorCode) {
		this.inHosDoctorCode = inHosDoctorCode;
	}


	/**
	 * @return the inHosDoctorName
	 */
	public String getInHosDoctorName() {
		return inHosDoctorName;
	}


	/**
	 * @param inHosDoctorName the inHosDoctorName to set
	 */
	public void setInHosDoctorName(String inHosDoctorName) {
		this.inHosDoctorName = inHosDoctorName;
	}


	/**
	 * @return the endemicArea
	 */
	public String getEndemicArea() {
		return endemicArea;
	}


	/**
	 * @param endemicArea the endemicArea to set
	 */
	public void setEndemicArea(String endemicArea) {
		this.endemicArea = endemicArea;
	}


	/**
	 * @return the treatDeptCode
	 */
	public String getTreatDeptCode() {
		return treatDeptCode;
	}


	/**
	 * @param treatDeptCode the treatDeptCode to set
	 */
	public void setTreatDeptCode(String treatDeptCode) {
		this.treatDeptCode = treatDeptCode;
	}


	/**
	 * @return the treatDeptName
	 */
	public String getTreatDeptName() {
		return treatDeptName;
	}


	/**
	 * @param treatDeptName the treatDeptName to set
	 */
	public void setTreatDeptName(String treatDeptName) {
		this.treatDeptName = treatDeptName;
	}


	/**
	 * @return the hospitalizedNum
	 */
	public String getHospitalizedNum() {
		return hospitalizedNum;
	}


	/**
	 * @param hospitalizedNum the hospitalizedNum to set
	 */
	public void setHospitalizedNum(String hospitalizedNum) {
		this.hospitalizedNum = hospitalizedNum;
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
	 * @return the credentialType
	 */
	public String getCredentialType() {
		return credentialType;
	}


	/**
	 * @param credentialType the credentialType to set
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
	 * @param credentialNum the credentialNum to set
	 */
	public void setCredentialNum(String credentialNum) {
		this.credentialNum = credentialNum;
	}


	/**
	 * @return the iDCard
	 */
	public String getIDCard() {
		return IDCard;
	}


	/**
	 * @param iDCard the iDCard to set
	 */
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the bOD
	 */
	public String getBOD() {
		return BOD;
	}


	/**
	 * @param bOD the bOD to set
	 */
	public void setBOD(String bOD) {
		BOD = bOD;
	}


	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}


	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}


	/**
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}


	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}


	/**
	 * @return the contactsName
	 */
	public String getContactsName() {
		return contactsName;
	}


	/**
	 * @param contactsName the contactsName to set
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}


	/**
	 * @return the contactsMobile
	 */
	public String getContactsMobile() {
		return contactsMobile;
	}


	/**
	 * @param contactsMobile the contactsMobile to set
	 */
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}


	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * @return the clientStatus
	 */
	public String getClientStatus() {
		return clientStatus;
	}


	/**
	 * @param clientStatus the clientStatus to set
	 */
	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}


	/**
	 * @return the guardianName
	 */
	public String getGuardianName() {
		return guardianName;
	}


	/**
	 * @param guardianName the guardianName to set
	 */
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}


	/**
	 * @return the guardianIdType
	 */
	public String getGuardianIdType() {
		return guardianIdType;
	}


	/**
	 * @param guardianIdType the guardianIdType to set
	 */
	public void setGuardianIdType(String guardianIdType) {
		this.guardianIdType = guardianIdType;
	}


	/**
	 * @return the guardianIdNo
	 */
	public String getGuardianIdNo() {
		return guardianIdNo;
	}


	/**
	 * @param guardianIdNo the guardianIdNo to set
	 */
	public void setGuardianIdNo(String guardianIdNo) {
		this.guardianIdNo = guardianIdNo;
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
	 * @return the currMedicalCost
	 */
	public String getCurrMedicalCost() {
		return currMedicalCost;
	}


	/**
	 * @param currMedicalCost the currMedicalCost to set
	 */
	public void setCurrMedicalCost(String currMedicalCost) {
		this.currMedicalCost = currMedicalCost;
	}


	/**
	 * @return the idDueDay
	 */
	public String getIdDueDay() {
		return idDueDay;
	}


	/**
	 * @param idDueDay the idDueDay to set
	 */
	public void setIdDueDay(String idDueDay) {
		this.idDueDay = idDueDay;
	}


	/**
	 * @return the guardianMobile
	 */
	public String getGuardianMobile() {
		return guardianMobile;
	}


	/**
	 * @param guardianMobile the guardianMobile to set
	 */
	public void setGuardianMobile(String guardianMobile) {
		this.guardianMobile = guardianMobile;
	}


	/**
	 * @return the guardianIDDueDay
	 */
	public String getGuardianIDDueDay() {
		return guardianIDDueDay;
	}


	/**
	 * @param guardianIDDueDay the guardianIDDueDay to set
	 */
	public void setGuardianIDDueDay(String guardianIDDueDay) {
		this.guardianIDDueDay = guardianIDDueDay;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the thirdCode
	 */
	public String getThirdCode() {
		return thirdCode;
	}


	/**
	 * @param thirdCode the thirdCode to set
	 */
	public void setThirdCode(String thirdCode) {
		this.thirdCode = thirdCode;
	}


	/**
	 * @return the normInHosDiagnosisCode
	 */
	public String getNormInHosDiagnosisCode() {
		return normInHosDiagnosisCode;
	}


	/**
	 * @param normInHosDiagnosisCode the normInHosDiagnosisCode to set
	 */
	public void setNormInHosDiagnosisCode(String normInHosDiagnosisCode) {
		this.normInHosDiagnosisCode = normInHosDiagnosisCode;
	}


	/**
	 * @return the normInHosDiagnosisName
	 */
	public String getNormInHosDiagnosisName() {
		return normInHosDiagnosisName;
	}


	/**
	 * @param normInHosDiagnosisName the normInHosDiagnosisName to set
	 */
	public void setNormInHosDiagnosisName(String normInHosDiagnosisName) {
		this.normInHosDiagnosisName = normInHosDiagnosisName;
	}


	/**
	 * @return the ticketsFrom
	 */
	public String getTicketsFrom() {
		return ticketsFrom;
	}


	/**
	 * @param ticketsFrom the ticketsFrom to set
	 */
	public void setTicketsFrom(String ticketsFrom) {
		this.ticketsFrom = ticketsFrom;
	}


	/**
	 * @return the diagnosisLists
	 */
	public List<Diagnosis> getDiagnosisLists() {
		return diagnosisLists;
	}


	/**
	 * @param diagnosisLists the diagnosisLists to set
	 */
	public void setDiagnosisLists(List<Diagnosis> diagnosisLists) {
		this.diagnosisLists = diagnosisLists;
	}

}
