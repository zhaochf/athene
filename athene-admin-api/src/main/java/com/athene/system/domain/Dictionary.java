/**
 * 
 */
package com.athene.system.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.athene.data.domain.AbstractEntity;
import com.athene.data.domain.CompositeId;

/**
 * @author zhaochf
 *
 */
@Entity
@Table(name = "T_SYSTEM_DICTIONARY")
@IdClass(Dictionary.DictionaryId.class)
public class Dictionary extends AbstractEntity {

	@Id
	private String id;

	@Id
	@Column(name = "CATEGORY_ID")
	private String categoryId;

	@Column(name = "NAME")
	private String name;

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

	public static class DictionaryId extends CompositeId {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String id;

		private String categoryId;
		
		/**
		 * @param id
		 * @param categoryId
		 */
		public DictionaryId(String id, String categoryId) {
			super();
			this.id = id;
			this.categoryId = categoryId;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the categoryId
		 */
		public String getCategoryId() {
			return categoryId;
		}

		/**
		 * @param categoryId the categoryId to set
		 */
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
	}

	/**
	 * 
	 */
	public Dictionary() {
	}

	/**
	 * @param id
	 */
	public Dictionary(DictionaryId dictionaryId) {
		this.id = dictionaryId.id;
		this.categoryId = dictionaryId.categoryId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param createdDate
	 *            the createdDate to set
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
	 * @param lastModifiedBy
	 *            the lastModifiedBy to set
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
	 * @param lastModifiedDate
	 *            the lastModifiedDate to set
	 */
	public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
