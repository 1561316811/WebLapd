package com.cyl.user;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public UserNotFoundException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
