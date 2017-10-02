package com.cyl.admin.room;

import java.sql.Date;

public class Room {
	
	private String idRoom;
	
	private String category;
	
	private int floor;
	
	private int size;
	
	private String remark;
	
	private Date addTime;
	
	public Room() {
		
	}
	
	public Room(String idRoom){
		this.idRoom = idRoom;
	}
	
	/**
	 * 
	 * @param idRoom idRoom
	 * @param category category
	 * @param floor floor
	 * @param size size
	 * @param remark remark
	 * @param addTime addTime
	 */
	public Room(String idRoom, String category, int floor, int size, String remark, Date addTime) {
		super();
		this.idRoom = idRoom;
		this.category = category;
		this.floor = floor;
		this.size = size;
		this.remark = remark;
		this.addTime = addTime;
	}
	

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRoom == null) ? 0 : idRoom.hashCode());
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
		Room other = (Room) obj;
		if (idRoom == null) {
			if (other.idRoom != null)
				return false;
		} else if (!idRoom.equals(other.idRoom))
			return false;
		return true;
	}
	
	
	
}
