package com.cyl.sql;

public class PrimaryKeyValueExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public PrimaryKeyValueExistException() {
	}
	
	public PrimaryKeyValueExistException(String msg) {
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}
}
