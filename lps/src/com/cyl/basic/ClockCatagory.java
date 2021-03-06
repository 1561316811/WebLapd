package com.cyl.basic;

/**
 * 上钟类型的对象
 * @author 0001
 *
 */
public class ClockCatagory {
	
	private int number;
	private String name;
	
	public ClockCatagory() {
		
	}
	

	public ClockCatagory(int number, String name) {
		this.number = number;
		this.name = name;
	}
	
	public int getNumber(){
		return this.number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ClockCatagory other = (ClockCatagory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
