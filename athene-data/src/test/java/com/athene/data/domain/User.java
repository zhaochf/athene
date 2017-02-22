/**
 * 
 */
package com.athene.data.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * @author zhaochf
 *
 */
@Entity
@Table(name="T_SYS_USER")
public class User extends AbstractEntity {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator",
	        parameters = { @Parameter( name = "uuid_gen_strategy_class",
	                value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
	private String id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="BIRTHDAY")
	private LocalDate birthday;

	/* (non-Javadoc)
	 * @see com.athene.data.domain.AbstractEntity#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
