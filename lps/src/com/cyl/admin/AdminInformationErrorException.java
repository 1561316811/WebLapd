package com.cyl.admin;

public class AdminInformationErrorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public AdminInformationErrorException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
