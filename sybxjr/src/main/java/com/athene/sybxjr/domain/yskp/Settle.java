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
@Table(name = "YSKP_SETTLE")
public class Settle extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6151832059161119150L;

	private String medicalNum;
	
	private String hospitalizedNum;
	
	@Id
	private String billNum;
	
	private String invoiceNO;
	
	private String medicalType;
	
	private String settleDate;
	
	private String dischDate;
	
	private String dischCause;
	
	private String dischDiagnosisCode;
	
	private String dischDiagnosisName;
	
	private String complication;
	
	private String wardshipStartDate;
	
	private String wardshipEndDate;
	
	private String billType;
	
	private String diagnosisCode1;
	
	private String diagnosisName1;
	
	private String diagnosisCode2;
	
	private String diagnosisName2;
	
	private String diagnosisCode3;
	
	private String diagnosisName3;
	
	private String diagnosisCode4;
	
	private String diagnosisName4;
	
	private String hospitalDay;
	
	private String sumMoney;
	
	private String updateBy;
	
	@Transient
	private List<Medicare> medicareList;
	
	private String medicarestr;
	
	private String dischMedDiagnosisCode;
	
	private String dischMedDiagnosisName;
	
	private String normDischDiagnosisCode;
	
	private String normDischDiagnosisName;
	

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		return getBillNum();
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
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}


	/**
	 * @param settleDate the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}


	/**
	 * @return the dischDate
	 */
	public String getDischDate() {
		return dischDate;
	}


	/**
	 * @param dischDate the dischDate to set
	 */
	public void setDischDate(String dischDate) {
		this.dischDate = dischDate;
	}


	/**
	 * @return the dischCause
	 */
	public String getDischCause() {
		return dischCause;
	}


	/**
	 * @param dischCause the dischCause to set
	 */
	public void setDischCause(String dischCause) {
		this.dischCause = dischCause;
	}


	/**
	 * @return the dischDiagnosisCode
	 */
	public String getDischDiagnosisCode() {
		return dischDiagnosisCode;
	}


	/**
	 * @param dischDiagnosisCode the dischDiagnosisCode to set
	 */
	public void setDischDiagnosisCode(String dischDiagnosisCode) {
		this.dischDiagnosisCode = dischDiagnosisCode;
	}


	/**
	 * @return the dischDiagnosisName
	 */
	public String getDischDiagnosisName() {
		return dischDiagnosisName;
	}


	/**
	 * @param dischDiagnosisName the dischDiagnosisName to set
	 */
	public void setDischDiagnosisName(String dischDiagnosisName) {
		this.dischDiagnosisName = dischDiagnosisName;
	}


	/**
	 * @return the complication
	 */
	public String getComplication() {
		return complication;
	}


	/**
	 * @param complication the complication to set
	 */
	public void setComplication(String complication) {
		this.complication = complication;
	}


	/**
	 * @return the wardshipStartDate
	 */
	public String getWardshipStartDate() {
		return wardshipStartDate;
	}


	/**
	 * @param wardshipStartDate the wardshipStartDate to set
	 */
	public void setWardshipStartDate(String wardshipStartDate) {
		this.wardshipStartDate = wardshipStartDate;
	}


	/**
	 * @return the wardshipEndDate
	 */
	public String getWardshipEndDate() {
		return wardshipEndDate;
	}


	/**
	 * @param wardshipEndDate the wardshipEndDate to set
	 */
	public void setWardshipEndDate(String wardshipEndDate) {
		this.wardshipEndDate = wardshipEndDate;
	}


	/**
	 * @return the billType
	 */
	public String getBillType() {
		return billType;
	}


	/**
	 * @param billType the billType to set
	 */
	public void setBillType(String billType) {
		this.billType = billType;
	}


	/**
	 * @return the diagnosisCode1
	 */
	public String getDiagnosisCode1() {
		return diagnosisCode1;
	}


	/**
	 * @param diagnosisCode1 the diagnosisCode1 to set
	 */
	public void setDiagnosisCode1(String diagnosisCode1) {
		this.diagnosisCode1 = diagnosisCode1;
	}


	/**
	 * @return the diagnosisName1
	 */
	public String getDiagnosisName1() {
		return diagnosisName1;
	}


	/**
	 * @param diagnosisName1 the diagnosisName1 to set
	 */
	public void setDiagnosisName1(String diagnosisName1) {
		this.diagnosisName1 = diagnosisName1;
	}


	/**
	 * @return the diagnosisCode2
	 */
	public String getDiagnosisCode2() {
		return diagnosisCode2;
	}


	/**
	 * @param diagnosisCode2 the diagnosisCode2 to set
	 */
	public void setDiagnosisCode2(String diagnosisCode2) {
		this.diagnosisCode2 = diagnosisCode2;
	}


	/**
	 * @return the diagnosisName2
	 */
	public String getDiagnosisName2() {
		return diagnosisName2;
	}


	/**
	 * @param diagnosisName2 the diagnosisName2 to set
	 */
	public void setDiagnosisName2(String diagnosisName2) {
		this.diagnosisName2 = diagnosisName2;
	}


	/**
	 * @return the diagnosisCode3
	 */
	public String getDiagnosisCode3() {
		return diagnosisCode3;
	}


	/**
	 * @param diagnosisCode3 the diagnosisCode3 to set
	 */
	public void setDiagnosisCode3(String diagnosisCode3) {
		this.diagnosisCode3 = diagnosisCode3;
	}


	/**
	 * @return the diagnosisName3
	 */
	public String getDiagnosisName3() {
		return diagnosisName3;
	}


	/**
	 * @param diagnosisName3 the diagnosisName3 to set
	 */
	public void setDiagnosisName3(String diagnosisName3) {
		this.diagnosisName3 = diagnosisName3;
	}


	/**
	 * @return the diagnosisCode4
	 */
	public String getDiagnosisCode4() {
		return diagnosisCode4;
	}


	/**
	 * @param diagnosisCode4 the diagnosisCode4 to set
	 */
	public void setDiagnosisCode4(String diagnosisCode4) {
		this.diagnosisCode4 = diagnosisCode4;
	}


	/**
	 * @return the diagnosisName4
	 */
	public String getDiagnosisName4() {
		return diagnosisName4;
	}


	/**
	 * @param diagnosisName4 the diagnosisName4 to set
	 */
	public void setDiagnosisName4(String diagnosisName4) {
		this.diagnosisName4 = diagnosisName4;
	}


	/**
	 * @return the hospitalDay
	 */
	public String getHospitalDay() {
		return hospitalDay;
	}


	/**
	 * @param hospitalDay the hospitalDay to set
	 */
	public void setHospitalDay(String hospitalDay) {
		this.hospitalDay = hospitalDay;
	}


	/**
	 * @return the sumMoney
	 */
	public String getSumMoney() {
		return sumMoney;
	}


	/**
	 * @param sumMoney the sumMoney to set
	 */
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
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
	 * @return the medicareList
	 */
	public List<?> getMedicareList() {
		return medicareList;
	}


	/**
	 * @param medicareList the medicareList to set
	 */
	public void setMedicareList(List<Medicare> medicareList) {
		this.medicareList = medicareList;
	}

	/**
	 * @return the medicarestr
	 */
	public String getMedicarestr() {
		return medicarestr;
	}


	/**
	 * @param medicarestr the medicarestr to set
	 */
	public void setMedicarestr(String medicarestr) {
		this.medicarestr = medicarestr;
	}


	/**
	 * @return the dischMedDiagnosisCode
	 */
	public String getDischMedDiagnosisCode() {
		return dischMedDiagnosisCode;
	}


	/**
	 * @param dischMedDiagnosisCode the dischMedDiagnosisCode to set
	 */
	public void setDischMedDiagnosisCode(String dischMedDiagnosisCode) {
		this.dischMedDiagnosisCode = dischMedDiagnosisCode;
	}


	/**
	 * @return the dischMedDiagnosisName
	 */
	public String getDischMedDiagnosisName() {
		return dischMedDiagnosisName;
	}


	/**
	 * @param dischMedDiagnosisName the dischMedDiagnosisName to set
	 */
	public void setDischMedDiagnosisName(String dischMedDiagnosisName) {
		this.dischMedDiagnosisName = dischMedDiagnosisName;
	}


	/**
	 * @return the normDischDiagnosisCode
	 */
	public String getNormDischDiagnosisCode() {
		return normDischDiagnosisCode;
	}


	/**
	 * @param normDischDiagnosisCode the normDischDiagnosisCode to set
	 */
	public void setNormDischDiagnosisCode(String normDischDiagnosisCode) {
		this.normDischDiagnosisCode = normDischDiagnosisCode;
	}


	/**
	 * @return the normDischDiagnosisName
	 */
	public String getNormDischDiagnosisName() {
		return normDischDiagnosisName;
	}


	/**
	 * @param normDischDiagnosisName the normDischDiagnosisName to set
	 */
	public void setNormDischDiagnosisName(String normDischDiagnosisName) {
		this.normDischDiagnosisName = normDischDiagnosisName;
	}
	
}
