package com.cyl.user;

public class UserInformationErrorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public UserInformationErrorException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
