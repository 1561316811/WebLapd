package com.cyl.work;

import java.sql.Timestamp;

public class ServerOrder {
	// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
	// `id` INT NOT NULL COMMENT '员工工作编号',
	// `idRoom` VARCHAR(10) NOT NULL COMMENT '所在房间编号',
	// `idOrder` VARCHAR(45) NOT NULL COMMENT '订单编号',
	// `status` INT NOT NULL DEFAULT 0 COMMENT '订单状态',
	// `startTime` DATETIME NULL COMMENT '此服务单开始时间',
	// `endTime` DATETIME NULL COMMENT '此服务单结束时间',
	// `pay` INT NULL COMMENT '应付金额',
	// `realPay` INT NULL COMMENT '实付金额',
	// `payTime` DATETIME NULL,
	// `orderRemark` VARCHAR(255) NULL COMMENT '订单描述',
	// `payPath` VARCHAR(10) NULL COMMENT '支付途径',
	// `pledgeName` VARCHAR(20) NULL,
	// `pledgePrice` INT NULL,

	private String idUser;

	private int id;

	private String idRoom;

	private String idOrder;

	private String status;

	private Timestamp startTime;
	
	private Timestamp endTime;
	
	private int pay;
	
	private int realPay;
	
	private Timestamp payTime;
	
	private String orderRemark;
	
	private String payPath;
	
	private String pledgeName;
	
	private int pledgePrice;
	
	private Timestamp addTime;
	
	private String clockCatagory;

	public ServerOrder() {
	}
	
	

	public ServerOrder(String idUser, int id, String idRoom, String idOrder, String status, Timestamp startTime,
			Timestamp endTime, int pay, int realPay, Timestamp payTime, String orderRemark, String payPath, String pledgeName,
			int pledgePrice, Timestamp addTime, String clockCatagory) {
		super();
		this.idUser = idUser;
		this.id = id;
		this.idRoom = idRoom;
		this.idOrder = idOrder;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pay = pay;
		this.realPay = realPay;
		this.payTime = payTime;
		this.orderRemark = orderRemark;
		this.payPath = payPath;
		this.pledgeName = pledgeName;
		this.pledgePrice = pledgePrice;
		this.addTime = addTime;
		this.clockCatagory = clockCatagory;
	}



	public String getClockCatagory() {
		return clockCatagory;
	}

	public void setClockCatagory(String clockCatagory) {
		this.clockCatagory = clockCatagory;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public Timestamp getAddTime() {
		return addTime;
	}
	
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getRealPay() {
		return realPay;
	}

	public void setRealPay(int realPay) {
		this.realPay = realPay;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getPayPath() {
		return payPath;
	}

	public void setPayPath(String payPath) {
		this.payPath = payPath;
	}

	public String getPledgeName() {
		return pledgeName;
	}

	public void setPledgeName(String pledgeName) {
		this.pledgeName = pledgeName;
	}

	public int getPledgePrice() {
		return pledgePrice;
	}

	public void setPledgePrice(int pledgePrice) {
		this.pledgePrice = pledgePrice;
	}

}
