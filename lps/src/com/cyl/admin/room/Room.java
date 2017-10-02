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
	
	
	
//	`idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
//	  `catagory` VARCHAR(45) NULL COMMENT '房间名字',
//	  `floor` INT NULL COMMENT '房间楼层',
//	  `size` INT NULL COMMENT '房间可容纳客人的人数',
//	  `remark` VARCHAR(255) NULL COMMENT '房间描述',
//	  PRIMARY KEY (`idRoom`))
}
