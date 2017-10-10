package com.athene.sybxjr.domain.yskp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.athene.data.domain.AbstractEntity;

@Entity
@Table(name = "YSKP_ASSAYDETAIL")
public class AssayDetail extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1058777888484109715L;

	private String labFlow;
	
	@Id
	private String itemNum;
	
	private String itemName;
	
	private String numVal;
	
	private String txtVal;
	
	private String pnFlag;
	
	private String additionVal;
	
	private String reference;
	
	private String insertFlag;
	
	private String method1;
	
	private String method2;
	
	private String xynd1;
	
	private String xynd2;

	private String nynd1;
	
	private String nynd2;
	
	private String medcode;
	
	private String sensitivity;
	
	private String bacteriostatDiameter;
	
	@Override
	public Serializable getId() {
		return getItemNum();
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
	 * @return the itemNum
	 */
	public String getItemNum() {
		return itemNum;
	}

	/**
	 * @param itemNum the itemNum to set
	 */
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the numVal
	 */
	public String getNumVal() {
		return numVal;
	}

	/**
	 * @param numVal the numVal to set
	 */
	public void setNumVal(String numVal) {
		this.numVal = numVal;
	}

	/**
	 * @return the txtVal
	 */
	public String getTxtVal() {
		return txtVal;
	}

	/**
	 * @param txtVal the txtVal to set
	 */
	public void setTxtVal(String txtVal) {
		this.txtVal = txtVal;
	}

	/**
	 * @return the pnFlag
	 */
	public String getPnFlag() {
		return pnFlag;
	}

	/**
	 * @param pnFlag the pnFlag to set
	 */
	public void setPnFlag(String pnFlag) {
		this.pnFlag = pnFlag;
	}

	/**
	 * @return the additionVal
	 */
	public String getAdditionVal() {
		return additionVal;
	}

	/**
	 * @param additionVal the additionVal to set
	 */
	public void setAdditionVal(String additionVal) {
		this.additionVal = additionVal;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
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
	 * @return the method1
	 */
	public String getMethod1() {
		return method1;
	}

	/**
	 * @param method1 the method1 to set
	 */
	public void setMethod1(String method1) {
		this.method1 = method1;
	}

	/**
	 * @return the method2
	 */
	public String getMethod2() {
		return method2;
	}

	/**
	 * @param method2 the method2 to set
	 */
	public void setMethod2(String method2) {
		this.method2 = method2;
	}

	/**
	 * @return the xynd1
	 */
	public String getXynd1() {
		return xynd1;
	}

	/**
	 * @param xynd1 the xynd1 to set
	 */
	public void setXynd1(String xynd1) {
		this.xynd1 = xynd1;
	}

	/**
	 * @return the xynd2
	 */
	public String getXynd2() {
		return xynd2;
	}

	/**
	 * @param xynd2 the xynd2 to set
	 */
	public void setXynd2(String xynd2) {
		this.xynd2 = xynd2;
	}

	/**
	 * @return the nynd1
	 */
	public String getNynd1() {
		return nynd1;
	}

	/**
	 * @param nynd1 the nynd1 to set
	 */
	public void setNynd1(String nynd1) {
		this.nynd1 = nynd1;
	}

	/**
	 * @return the nynd2
	 */
	public String getNynd2() {
		return nynd2;
	}

	/**
	 * @param nynd2 the nynd2 to set
	 */
	public void setNynd2(String nynd2) {
		this.nynd2 = nynd2;
	}

	/**
	 * @return the medcode
	 */
	public String getMedcode() {
		return medcode;
	}

	/**
	 * @param medcode the medcode to set
	 */
	public void setMedcode(String medcode) {
		this.medcode = medcode;
	}

	/**
	 * @return the sensitivity
	 */
	public String getSensitivity() {
		return sensitivity;
	}

	/**
	 * @param sensitivity the sensitivity to set
	 */
	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}

	/**
	 * @return the bacteriostatDiameter
	 */
	public String getBacteriostatDiameter() {
		return bacteriostatDiameter;
	}

	/**
	 * @param bacteriostatDiameter the bacteriostatDiameter to set
	 */
	public void setBacteriostatDiameter(String bacteriostatDiameter) {
		this.bacteriostatDiameter = bacteriostatDiameter;
	}

}
