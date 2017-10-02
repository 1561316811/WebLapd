package com.cyl.admin;

import java.sql.Date;

public class Admin {
	
	private String idAdmin;
	
	private String password;
	
	private  Date addTime; 
	
	public Admin(String idAdmin){
		this.idAdmin = idAdmin;
	}
	
	public Admin(String idAdmin, String password, Date addTime) {
		
		this.idAdmin = idAdmin;
		this.password = password;
		this.addTime = addTime;
	}

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Date getAddTime() {
		return addTime;
	}
	
	String getPassword(){
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAdmin == null) ? 0 : idAdmin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (idAdmin == null) {
			if (other.idAdmin != null)
				return false;
		} else if (!idAdmin.equals(other.idAdmin))
			return false;
		return true;
	}
	
	
	
	
}
