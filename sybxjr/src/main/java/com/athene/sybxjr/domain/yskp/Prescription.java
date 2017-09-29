/**
 * 
 */
package com.athene.sybxjr.domain.yskp;

import java.io.Serializable;

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
@Table(name = "YSKP_PRESCRIPTION")
public class Prescription extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3754095713059025182L;
	
	@Id
	private String recipeSerialNum;
	
	private String medicalNum;
	
	private String listCat;
	
	private String medicalItemCat;
	
	private String recipeNum;
	
	private String recipeDate;
	
	private String hospitalChargeCode;
	
	private String hospitalChargeName;
	
	private String productName;
	
	private String englishName;
	
	private String centreChargeCode;
	
	private String medicareFeeitemName;
	
	private String price;
	
	private String quantity;
	
	private String money;
	
	private String hosBearMoney;
	
	private String formulation;
	
	private String spec;
	
	private String standardUnit;
	
	private String herbFuFangSign;
	
	private String totalSelfFundFlg;
	
	private String extraRecipeFlg;
	
	private String usage;
	
	private String perQuantity;
	
	private String frequency;
	
	private String exeDays;
	
	private String combination;
	
	private String functions;
	
	private String packetNum;
	
	private String contraindication;
	
	private String isRestricted;
	
	private String restrictions;
	
	private String isBasicMedicine;
	
	private String nationalMedicineId;
	
	private String regId;
	
	private String brand;
	
	private String origion;
	
	private String manufacturer;
	
	private String examinationResult;
	
	private String referenceRanges;
	
	private String examinationHint;
	
	private String deptNum;
	
	private String deptName;
	
	private String doctorCode;
	
	private String doctorName;
	
	private String updateBy;
	
	private String orderDate;
	
	private String orderExecutedDate;
	
	private String selfPayRatio;
	
	private String medlimitedPrice;
	
	private String chargeLevel;
	
	private String medicalType;
	
	private String invoiceNO;
	
	@Transient
	private String total;
	
	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		return getRecipeSerialNum();
	}

	/**
	 * @return the recipeSerialNum
	 */
	public String getRecipeSerialNum() {
		return recipeSerialNum;
	}

	/**
	 * @param recipeSerialNum the recipeSerialNum to set
	 */
	public void setRecipeSerialNum(String recipeSerialNum) {
		this.recipeSerialNum = recipeSerialNum;
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
	 * @return the listCat
	 */
	public String getListCat() {
		return listCat;
	}

	/**
	 * @param listCat the listCat to set
	 */
	public void setListCat(String listCat) {
		this.listCat = listCat;
	}

	/**
	 * @return the medicalItemCat
	 */
	public String getMedicalItemCat() {
		return medicalItemCat;
	}

	/**
	 * @param medicalItemCat the medicalItemCat to set
	 */
	public void setMedicalItemCat(String medicalItemCat) {
		this.medicalItemCat = medicalItemCat;
	}

	/**
	 * @return the recipeNum
	 */
	public String getRecipeNum() {
		return recipeNum;
	}

	/**
	 * @param recipeNum the recipeNum to set
	 */
	public void setRecipeNum(String recipeNum) {
		this.recipeNum = recipeNum;
	}

	/**
	 * @return the recipeDate
	 */
	public String getRecipeDate() {
		return recipeDate;
	}

	/**
	 * @param recipeDate the recipeDate to set
	 */
	public void setRecipeDate(String recipeDate) {
		this.recipeDate = recipeDate;
	}

	/**
	 * @return the hospitalChargeCode
	 */
	public String getHospitalChargeCode() {
		return hospitalChargeCode;
	}

	/**
	 * @param hospitalChargeCode the hospitalChargeCode to set
	 */
	public void setHospitalChargeCode(String hospitalChargeCode) {
		this.hospitalChargeCode = hospitalChargeCode;
	}

	/**
	 * @return the hospitalChargeName
	 */
	public String getHospitalChargeName() {
		return hospitalChargeName;
	}

	/**
	 * @param hospitalChargeName the hospitalChargeName to set
	 */
	public void setHospitalChargeName(String hospitalChargeName) {
		this.hospitalChargeName = hospitalChargeName;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * @return the centreChargeCode
	 */
	public String getCentreChargeCode() {
		return centreChargeCode;
	}

	/**
	 * @param centreChargeCode the centreChargeCode to set
	 */
	public void setCentreChargeCode(String centreChargeCode) {
		this.centreChargeCode = centreChargeCode;
	}

	/**
	 * @return the medicareFeeitemName
	 */
	public String getMedicareFeeitemName() {
		return medicareFeeitemName;
	}

	/**
	 * @param medicareFeeitemName the medicareFeeitemName to set
	 */
	public void setMedicareFeeitemName(String medicareFeeitemName) {
		this.medicareFeeitemName = medicareFeeitemName;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the hosBearMoney
	 */
	public String getHosBearMoney() {
		return hosBearMoney;
	}

	/**
	 * @param hosBearMoney the hosBearMoney to set
	 */
	public void setHosBearMoney(String hosBearMoney) {
		this.hosBearMoney = hosBearMoney;
	}

	/**
	 * @return the formulation
	 */
	public String getFormulation() {
		return formulation;
	}

	/**
	 * @param formulation the formulation to set
	 */
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	/**
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return the standardUnit
	 */
	public String getStandardUnit() {
		return standardUnit;
	}

	/**
	 * @param standardUnit the standardUnit to set
	 */
	public void setStandardUnit(String standardUnit) {
		this.standardUnit = standardUnit;
	}

	/**
	 * @return the herbFuFangSign
	 */
	public String getHerbFuFangSign() {
		return herbFuFangSign;
	}

	/**
	 * @param herbFuFangSign the herbFuFangSign to set
	 */
	public void setHerbFuFangSign(String herbFuFangSign) {
		this.herbFuFangSign = herbFuFangSign;
	}

	/**
	 * @return the totalSelfFundFlg
	 */
	public String getTotalSelfFundFlg() {
		return totalSelfFundFlg;
	}

	/**
	 * @param totalSelfFundFlg the totalSelfFundFlg to set
	 */
	public void setTotalSelfFundFlg(String totalSelfFundFlg) {
		this.totalSelfFundFlg = totalSelfFundFlg;
	}

	/**
	 * @return the extraRecipeFlg
	 */
	public String getExtraRecipeFlg() {
		return extraRecipeFlg;
	}

	/**
	 * @param extraRecipeFlg the extraRecipeFlg to set
	 */
	public void setExtraRecipeFlg(String extraRecipeFlg) {
		this.extraRecipeFlg = extraRecipeFlg;
	}

	/**
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}

	/**
	 * @return the perQuantity
	 */
	public String getPerQuantity() {
		return perQuantity;
	}

	/**
	 * @param perQuantity the perQuantity to set
	 */
	public void setPerQuantity(String perQuantity) {
		this.perQuantity = perQuantity;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the exeDays
	 */
	public String getExeDays() {
		return exeDays;
	}

	/**
	 * @param exeDays the exeDays to set
	 */
	public void setExeDays(String exeDays) {
		this.exeDays = exeDays;
	}

	/**
	 * @return the combination
	 */
	public String getCombination() {
		return combination;
	}

	/**
	 * @param combination the combination to set
	 */
	public void setCombination(String combination) {
		this.combination = combination;
	}

	/**
	 * @return the functions
	 */
	public String getFunctions() {
		return functions;
	}

	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(String functions) {
		this.functions = functions;
	}

	/**
	 * @return the packetNum
	 */
	public String getPacketNum() {
		return packetNum;
	}

	/**
	 * @param packetNum the packetNum to set
	 */
	public void setPacketNum(String packetNum) {
		this.packetNum = packetNum;
	}

	/**
	 * @return the contraindication
	 */
	public String getContraindication() {
		return contraindication;
	}

	/**
	 * @param contraindication the contraindication to set
	 */
	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	/**
	 * @return the isRestricted
	 */
	public String getIsRestricted() {
		return isRestricted;
	}

	/**
	 * @param isRestricted the isRestricted to set
	 */
	public void setIsRestricted(String isRestricted) {
		this.isRestricted = isRestricted;
	}

	/**
	 * @return the restrictions
	 */
	public String getRestrictions() {
		return restrictions;
	}

	/**
	 * @param restrictions the restrictions to set
	 */
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	/**
	 * @return the isBasicMedicine
	 */
	public String getIsBasicMedicine() {
		return isBasicMedicine;
	}

	/**
	 * @param isBasicMedicine the isBasicMedicine to set
	 */
	public void setIsBasicMedicine(String isBasicMedicine) {
		this.isBasicMedicine = isBasicMedicine;
	}

	/**
	 * @return the nationalMedicineId
	 */
	public String getNationalMedicineId() {
		return nationalMedicineId;
	}

	/**
	 * @param nationalMedicineId the nationalMedicineId to set
	 */
	public void setNationalMedicineId(String nationalMedicineId) {
		this.nationalMedicineId = nationalMedicineId;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the origion
	 */
	public String getOrigion() {
		return origion;
	}

	/**
	 * @param origion the origion to set
	 */
	public void setOrigion(String origion) {
		this.origion = origion;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the examinationResult
	 */
	public String getExaminationResult() {
		return examinationResult;
	}

	/**
	 * @param examinationResult the examinationResult to set
	 */
	public void setExaminationResult(String examinationResult) {
		this.examinationResult = examinationResult;
	}

	/**
	 * @return the referenceRanges
	 */
	public String getReferenceRanges() {
		return referenceRanges;
	}

	/**
	 * @param referenceRanges the referenceRanges to set
	 */
	public void setReferenceRanges(String referenceRanges) {
		this.referenceRanges = referenceRanges;
	}

	/**
	 * @return the examinationHint
	 */
	public String getExaminationHint() {
		return examinationHint;
	}

	/**
	 * @param examinationHint the examinationHint to set
	 */
	public void setExaminationHint(String examinationHint) {
		this.examinationHint = examinationHint;
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
	 * @return the doctorCode
	 */
	public String getDoctorCode() {
		return doctorCode;
	}

	/**
	 * @param doctorCode the doctorCode to set
	 */
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderExecutedDate
	 */
	public String getOrderExecutedDate() {
		return orderExecutedDate;
	}

	/**
	 * @param orderExecutedDate the orderExecutedDate to set
	 */
	public void setOrderExecutedDate(String orderExecutedDate) {
		this.orderExecutedDate = orderExecutedDate;
	}

	/**
	 * @return the selfPayRatio
	 */
	public String getSelfPayRatio() {
		return selfPayRatio;
	}

	/**
	 * @param selfPayRatio the selfPayRatio to set
	 */
	public void setSelfPayRatio(String selfPayRatio) {
		this.selfPayRatio = selfPayRatio;
	}

	/**
	 * @return the medlimitedPrice
	 */
	public String getMedlimitedPrice() {
		return medlimitedPrice;
	}

	/**
	 * @param medlimitedPrice the medlimitedPrice to set
	 */
	public void setMedlimitedPrice(String medlimitedPrice) {
		this.medlimitedPrice = medlimitedPrice;
	}

	/**
	 * @return the chargeLevel
	 */
	public String getChargeLevel() {
		return chargeLevel;
	}

	/**
	 * @param chargeLevel the chargeLevel to set
	 */
	public void setChargeLevel(String chargeLevel) {
		this.chargeLevel = chargeLevel;
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
