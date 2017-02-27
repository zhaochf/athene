/**
 * 
 */
package com.athene.system.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.athene.data.domain.AbstractEntity;

/**
 * @author zhaochf
 *
 */
@Entity
@Table(name = "T_SYSTEM_DICTIONARY_CATEGORY")
public class DictionaryCategory extends AbstractEntity {
	
	/**
	 * Defined root id is '#' 
	 */
	public static final String ROOT_ID = "#";

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "PARENT_ID")
	private String parentId;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "LEFT_LIMIT")
	private Integer leftLimit;
	
	@Column(name = "RIGHT_LIMIT")
	private Integer rightLimit;
	
	@Column(name = "LEVEL")
	private Integer level;
	
	@Column(name = "IS_LEAF")
	private Boolean isLeaf;
	
	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private ZonedDateTime createdDate;

	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;

	@Column(name = "LAST_MODIFIED_DATE")
	private ZonedDateTime lastModifiedDate;

	

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}
	

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public ZonedDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
}
