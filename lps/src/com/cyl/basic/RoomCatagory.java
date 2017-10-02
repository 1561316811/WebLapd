package com.cyl.basic;

public class RoomCatagory {
	
	private Integer number;
	private String name;
	
	public RoomCatagory() {
	}
	
	public RoomCatagory(String name){
		this.name = name;
	}
	
	public RoomCatagory(int number, String name){
		this.name = name;
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getNumber(){
		return this.getNumber();
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
		RoomCatagory other = (RoomCatagory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
