/**
 * 
 */
package com.athene.sybxjr.domain;

/**
 * @author zhaochf
 *
 */
public class Result {

	private String code = "1";
	
	private String message = "请求成功";
	
	private Object data;

	public Result() {
	}

	public Result(Object data) {
		super();
		this.data = data;
	}
	
	public Result(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
}
