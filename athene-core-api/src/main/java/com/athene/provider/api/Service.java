/**
 * 
 */
package com.athene.provider.api;

import java.io.Serializable;

import Ice.Object;

/**
 * All interfaces extends this interface
 *  
 * @author zhaochf
 *
 */
public interface Service extends Object, Serializable {

	public static final String serviceDefaultId = "Ice::Object";
	
}
