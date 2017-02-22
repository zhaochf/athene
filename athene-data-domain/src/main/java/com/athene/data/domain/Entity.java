/**
 * 
 */
package com.athene.data.domain;

import java.io.Serializable;

/**
 * @author zhaochf
 *
 */
public interface Entity {

	/**
	 * get entity id
	 * @return
	 */
	Serializable getId();
	
	/**
	 * the id is composite primary key
	 * @return
	 */
	boolean isCompositeId();
	
}
