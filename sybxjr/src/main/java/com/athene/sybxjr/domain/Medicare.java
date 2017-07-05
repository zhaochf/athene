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
@Table(name = "V_MEDICARE")
public class Medicare extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2634953714348671997L;

	@Id
	private String medicareId;
	
	private String billNum;
	
	private String selfCareAmount;
	
	private String selfAmount;
	
	private String inInsureMoney;
	
	private String medicareFundCost;
	
	private String medicarePayLine;
	
	private String perBearMoney;
	
	private String hosBearMoney;
	
	private String fundMoney;
	
	private String accountFundMoney;
	
	private String priorBurdenMoney;
	
	private String sectionCoordinatePayMoney;
	
	private String overCappingPayMoney;
	
	private String civilServantFundMoney;
	
	private String seriousFundMoney;
	
	private String civilSubsidy;
	
	private String cashMoney;
	
	private String bigPayMoney;
	
	private String bigSelfPayMoney;
	
	private String otherFundMoney;
	

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public Serializable getId() {
		return getMedicareId();
	}


	/**
	 * @return the medicareId
	 */
	public String getMedicareId() {
		return medicareId;
	}


	/**
	 * @param medicareId the medicareId to set
	 */
	public void setMedicareId(String medicareId) {
		this.medicareId = medicareId;
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
	 * @return the selfCareAmount
	 */
	public String getSelfCareAmount() {
		return selfCareAmount;
	}


	/**
	 * @param selfCareAmount the selfCareAmount to set
	 */
	public void setSelfCareAmount(String selfCareAmount) {
		this.selfCareAmount = selfCareAmount;
	}


	/**
	 * @return the selfAmount
	 */
	public String getSelfAmount() {
		return selfAmount;
	}


	/**
	 * @param selfAmount the selfAmount to set
	 */
	public void setSelfAmount(String selfAmount) {
		this.selfAmount = selfAmount;
	}


	/**
	 * @return the inInsureMoney
	 */
	public String getInInsureMoney() {
		return inInsureMoney;
	}


	/**
	 * @param inInsureMoney the inInsureMoney to set
	 */
	public void setInInsureMoney(String inInsureMoney) {
		this.inInsureMoney = inInsureMoney;
	}


	/**
	 * @return the medicareFundCost
	 */
	public String getMedicareFundCost() {
		return medicareFundCost;
	}


	/**
	 * @param medicareFundCost the medicareFundCost to set
	 */
	public void setMedicareFundCost(String medicareFundCost) {
		this.medicareFundCost = medicareFundCost;
	}


	/**
	 * @return the medicarePayLine
	 */
	public String getMedicarePayLine() {
		return medicarePayLine;
	}


	/**
	 * @param medicarePayLine the medicarePayLine to set
	 */
	public void setMedicarePayLine(String medicarePayLine) {
		this.medicarePayLine = medicarePayLine;
	}


	/**
	 * @return the perBearMoney
	 */
	public String getPerBearMoney() {
		return perBearMoney;
	}


	/**
	 * @param perBearMoney the perBearMoney to set
	 */
	public void setPerBearMoney(String perBearMoney) {
		this.perBearMoney = perBearMoney;
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
	 * @return the fundMoney
	 */
	public String getFundMoney() {
		return fundMoney;
	}


	/**
	 * @param fundMoney the fundMoney to set
	 */
	public void setFundMoney(String fundMoney) {
		this.fundMoney = fundMoney;
	}


	/**
	 * @return the accountFundMoney
	 */
	public String getAccountFundMoney() {
		return accountFundMoney;
	}


	/**
	 * @param accountFundMoney the accountFundMoney to set
	 */
	public void setAccountFundMoney(String accountFundMoney) {
		this.accountFundMoney = accountFundMoney;
	}


	/**
	 * @return the priorBurdenMoney
	 */
	public String getPriorBurdenMoney() {
		return priorBurdenMoney;
	}


	/**
	 * @param priorBurdenMoney the priorBurdenMoney to set
	 */
	public void setPriorBurdenMoney(String priorBurdenMoney) {
		this.priorBurdenMoney = priorBurdenMoney;
	}


	/**
	 * @return the sectionCoordinatePayMoney
	 */
	public String getSectionCoordinatePayMoney() {
		return sectionCoordinatePayMoney;
	}


	/**
	 * @param sectionCoordinatePayMoney the sectionCoordinatePayMoney to set
	 */
	public void setSectionCoordinatePayMoney(String sectionCoordinatePayMoney) {
		this.sectionCoordinatePayMoney = sectionCoordinatePayMoney;
	}


	/**
	 * @return the overCappingPayMoney
	 */
	public String getOverCappingPayMoney() {
		return overCappingPayMoney;
	}


	/**
	 * @param overCappingPayMoney the overCappingPayMoney to set
	 */
	public void setOverCappingPayMoney(String overCappingPayMoney) {
		this.overCappingPayMoney = overCappingPayMoney;
	}


	/**
	 * @return the civilServantFundMoney
	 */
	public String getCivilServantFundMoney() {
		return civilServantFundMoney;
	}


	/**
	 * @param civilServantFundMoney the civilServantFundMoney to set
	 */
	public void setCivilServantFundMoney(String civilServantFundMoney) {
		this.civilServantFundMoney = civilServantFundMoney;
	}


	/**
	 * @return the seriousFundMoney
	 */
	public String getSeriousFundMoney() {
		return seriousFundMoney;
	}


	/**
	 * @param seriousFundMoney the seriousFundMoney to set
	 */
	public void setSeriousFundMoney(String seriousFundMoney) {
		this.seriousFundMoney = seriousFundMoney;
	}


	/**
	 * @return the civilSubsidy
	 */
	public String getCivilSubsidy() {
		return civilSubsidy;
	}


	/**
	 * @param civilSubsidy the civilSubsidy to set
	 */
	public void setCivilSubsidy(String civilSubsidy) {
		this.civilSubsidy = civilSubsidy;
	}


	/**
	 * @return the cashMoney
	 */
	public String getCashMoney() {
		return cashMoney;
	}


	/**
	 * @param cashMoney the cashMoney to set
	 */
	public void setCashMoney(String cashMoney) {
		this.cashMoney = cashMoney;
	}

	/**
	 * @return the bigPayMoney
	 */
	public String getBigPayMoney() {
		return bigPayMoney;
	}


	/**
	 * @param bigPayMoney the bigPayMoney to set
	 */
	public void setBigPayMoney(String bigPayMoney) {
		this.bigPayMoney = bigPayMoney;
	}


	/**
	 * @return the bigSelfPayMoney
	 */
	public String getBigSelfPayMoney() {
		return bigSelfPayMoney;
	}


	/**
	 * @param bigSelfPayMoney the bigSelfPayMoney to set
	 */
	public void setBigSelfPayMoney(String bigSelfPayMoney) {
		this.bigSelfPayMoney = bigSelfPayMoney;
	}


	/**
	 * @return the otherFundMoney
	 */
	public String getOtherFundMoney() {
		return otherFundMoney;
	}


	/**
	 * @param otherFundMoney the otherFundMoney to set
	 */
	public void setOtherFundMoney(String otherFundMoney) {
		this.otherFundMoney = otherFundMoney;
	}
}
