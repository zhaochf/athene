/**
 * 
 */
package com.athene.sybxjr.domain;

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
@Table(name = "V_RUNCASE")
public class RunCase extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4310927437173277484L;

	private String medicalNum;
	
	@Id
	private String hospitalRecordId;
	
	private String inHospitalNum;
	
	private String cheifComplaint;
	
	private String historyPresentIllness;
	
	private String pastDiseaseHistory;
	
	private String personalHistory;
	
	private String obstetricalHistory;
	
	private String menstruationHistory;
	
	private String familyHistory;
	
	@Transient
	private List<Disease> diseaseList;
	
	@Transient
	private List<Operation> operationList;
	
	private String diagnosisTreatment;
	
	private String attendingPhysician;
	
	private String dischargeStatus;
	
	private String dischargeOrder;
	
	private String medicalAbstract;
	
	private String physicalExamination;
	
	private String juniorCollege;
	
	private String auxiliaryExamination;
	
	private String isfile;
	
	private String dischargeDiagnosis;
	
	private String admittingDiagnosis;

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		return getHospitalRecordId();
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

	/**
	 * @return the inHospitalNum
	 */
	public String getInHospitalNum() {
		return inHospitalNum;
	}

	/**
	 * @param inHospitalNum the inHospitalNum to set
	 */
	public void setInHospitalNum(String inHospitalNum) {
		this.inHospitalNum = inHospitalNum;
	}

	/**
	 * @return the cheifComplaint
	 */
	public String getCheifComplaint() {
		return cheifComplaint;
	}

	/**
	 * @param cheifComplaint the cheifComplaint to set
	 */
	public void setCheifComplaint(String cheifComplaint) {
		this.cheifComplaint = cheifComplaint;
	}

	/**
	 * @return the historyPresentIllness
	 */
	public String getHistoryPresentIllness() {
		return historyPresentIllness;
	}

	/**
	 * @param historyPresentIllness the historyPresentIllness to set
	 */
	public void setHistoryPresentIllness(String historyPresentIllness) {
		this.historyPresentIllness = historyPresentIllness;
	}

	/**
	 * @return the pastDiseaseHistory
	 */
	public String getPastDiseaseHistory() {
		return pastDiseaseHistory;
	}

	/**
	 * @param pastDiseaseHistory the pastDiseaseHistory to set
	 */
	public void setPastDiseaseHistory(String pastDiseaseHistory) {
		this.pastDiseaseHistory = pastDiseaseHistory;
	}

	/**
	 * @return the personalHistory
	 */
	public String getPersonalHistory() {
		return personalHistory;
	}

	/**
	 * @param personalHistory the personalHistory to set
	 */
	public void setPersonalHistory(String personalHistory) {
		this.personalHistory = personalHistory;
	}

	/**
	 * @return the obstetricalHistory
	 */
	public String getObstetricalHistory() {
		return obstetricalHistory;
	}

	/**
	 * @param obstetricalHistory the obstetricalHistory to set
	 */
	public void setObstetricalHistory(String obstetricalHistory) {
		this.obstetricalHistory = obstetricalHistory;
	}

	/**
	 * @return the menstruationHistory
	 */
	public String getMenstruationHistory() {
		return menstruationHistory;
	}

	/**
	 * @param menstruationHistory the menstruationHistory to set
	 */
	public void setMenstruationHistory(String menstruationHistory) {
		this.menstruationHistory = menstruationHistory;
	}

	/**
	 * @return the familyHistory
	 */
	public String getFamilyHistory() {
		return familyHistory;
	}

	/**
	 * @param familyHistory the familyHistory to set
	 */
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	/**
	 * @return the diseaseList
	 */
	public List<Disease> getDiseaseList() {
		return diseaseList;
	}

	/**
	 * @param diseaseList the diseaseList to set
	 */
	public void setDiseaseList(List<Disease> diseaseList) {
		this.diseaseList = diseaseList;
	}

	/**
	 * @return the operationList
	 */
	public List<Operation> getOperationList() {
		return operationList;
	}

	/**
	 * @param operationList the operationList to set
	 */
	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	/**
	 * @return the diagnosisTreatment
	 */
	public String getDiagnosisTreatment() {
		return diagnosisTreatment;
	}

	/**
	 * @param diagnosisTreatment the diagnosisTreatment to set
	 */
	public void setDiagnosisTreatment(String diagnosisTreatment) {
		this.diagnosisTreatment = diagnosisTreatment;
	}

	/**
	 * @return the attendingPhysician
	 */
	public String getAttendingPhysician() {
		return attendingPhysician;
	}

	/**
	 * @param attendingPhysician the attendingPhysician to set
	 */
	public void setAttendingPhysician(String attendingPhysician) {
		this.attendingPhysician = attendingPhysician;
	}

	/**
	 * @return the dischargeStatus
	 */
	public String getDischargeStatus() {
		return dischargeStatus;
	}

	/**
	 * @param dischargeStatus the dischargeStatus to set
	 */
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	/**
	 * @return the dischargeOrder
	 */
	public String getDischargeOrder() {
		return dischargeOrder;
	}

	/**
	 * @param dischargeOrder the dischargeOrder to set
	 */
	public void setDischargeOrder(String dischargeOrder) {
		this.dischargeOrder = dischargeOrder;
	}

	/**
	 * @return the medicalAbstract
	 */
	public String getMedicalAbstract() {
		return medicalAbstract;
	}

	/**
	 * @param medicalAbstract the medicalAbstract to set
	 */
	public void setMedicalAbstract(String medicalAbstract) {
		this.medicalAbstract = medicalAbstract;
	}

	/**
	 * @return the physicalExamination
	 */
	public String getPhysicalExamination() {
		return physicalExamination;
	}

	/**
	 * @param physicalExamination the physicalExamination to set
	 */
	public void setPhysicalExamination(String physicalExamination) {
		this.physicalExamination = physicalExamination;
	}

	/**
	 * @return the juniorCollege
	 */
	public String getJuniorCollege() {
		return juniorCollege;
	}

	/**
	 * @param juniorCollege the juniorCollege to set
	 */
	public void setJuniorCollege(String juniorCollege) {
		this.juniorCollege = juniorCollege;
	}

	/**
	 * @return the auxiliaryExamination
	 */
	public String getAuxiliaryExamination() {
		return auxiliaryExamination;
	}

	/**
	 * @param auxiliaryExamination the auxiliaryExamination to set
	 */
	public void setAuxiliaryExamination(String auxiliaryExamination) {
		this.auxiliaryExamination = auxiliaryExamination;
	}

	/**
	 * @return the isfile
	 */
	public String getIsfile() {
		return isfile;
	}

	/**
	 * @param isfile the isfile to set
	 */
	public void setIsfile(String isfile) {
		this.isfile = isfile;
	}

	/**
	 * @return the dischargeDiagnosis
	 */
	public String getDischargeDiagnosis() {
		return dischargeDiagnosis;
	}

	/**
	 * @param dischargeDiagnosis the dischargeDiagnosis to set
	 */
	public void setDischargeDiagnosis(String dischargeDiagnosis) {
		this.dischargeDiagnosis = dischargeDiagnosis;
	}

	/**
	 * @return the admittingDiagnosis
	 */
	public String getAdmittingDiagnosis() {
		return admittingDiagnosis;
	}

	/**
	 * @param admittingDiagnosis the admittingDiagnosis to set
	 */
	public void setAdmittingDiagnosis(String admittingDiagnosis) {
		this.admittingDiagnosis = admittingDiagnosis;
	}

	
}
