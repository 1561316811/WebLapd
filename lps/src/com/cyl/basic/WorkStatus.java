package com.cyl.basic;

public class WorkStatus {

	private int number;
	private String workStatus;
	
	public WorkStatus() {
		
	}
	
	public WorkStatus(int number, String workStatus){
		
		this.workStatus = workStatus;
		this.number = number;
		
	}
	
 	public int getNumber() {
		return number;
	}

	public String getWorkStatus() {
		return workStatus;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkStatus other = (WorkStatus) obj;
		if (workStatus == null) {
			if (other.workStatus != null)
				return false;
		} else if (!workStatus.equals(other.workStatus))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workStatus == null) ? 0 : workStatus.hashCode());
		return result;
	}
	
		
}
