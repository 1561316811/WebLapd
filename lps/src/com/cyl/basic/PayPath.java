package com.cyl.basic;

public class PayPath {

	private String payPath;
	private int number;
	
	public PayPath() {
		
	}
	
	public PayPath( int number, String payPath) {
		this.payPath = payPath;
		this.number = number;
	}
	

	public int getNumber() {
		return number;
	}

	public String getPayPath() {
		return payPath;
	}

 	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayPath other = (PayPath) obj;
		if (payPath == null) {
			if (other.payPath != null)
				return false;
		} else if (!payPath.equals(other.payPath))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((payPath == null) ? 0 : payPath.hashCode());
		return result;
	}
	
		
}
