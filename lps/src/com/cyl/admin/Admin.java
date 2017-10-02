package com.cyl.admin;

public class Admin {
	
	private String idAdmin;
	
	private String password;
	
	public Admin(String idAdmin) {
		
		this.idAdmin = idAdmin;
		
	}

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
