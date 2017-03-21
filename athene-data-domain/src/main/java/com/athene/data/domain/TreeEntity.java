/**
 * 
 */
package com.athene.data.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author zhaochf
 *
 */
@MappedSuperclass
public abstract class TreeEntity extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4965279231057210613L;

	/**
	 * Defined root id is '#' 
	 */
	public static final String ROOT_ID = "#";

	@Column(name = "PARENT_ID")
	private String parentId;
	
	@Column(name = "LEFT_LIMIT")
	private Integer leftLimit;
	
	@Column(name = "RIGHT_LIMIT")
	private Integer rightLimit;
	
	@Column(name = "LEVEL")
	private Integer level;
	
	@Column(name = "IS_LEAF")
	private Boolean isLeaf = true;
	
	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the leftLimit
	 */
	public Integer getLeftLimit() {
		return leftLimit;
	}

	/**
	 * @param leftLimit the leftLimit to set
	 */
	public void setLeftLimit(Integer leftLimit) {
		this.leftLimit = leftLimit;
	}

	/**
	 * @return the rightLimit
	 */
	public Integer getRightLimit() {
		return rightLimit;
	}

	/**
	 * @param rightLimit the rightLimit to set
	 */
	public void setRightLimit(Integer rightLimit) {
		this.rightLimit = rightLimit;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the isLeaf
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the orderNumber
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
