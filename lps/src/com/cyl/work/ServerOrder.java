package com.cyl.work;

import java.sql.Timestamp;

public class ServerOrder implements Cloneable {
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

	private Integer id;

	private String idRoom;

	private String idOrder;

	private String status;

	private Timestamp startTime;

	private Timestamp endTime;

	private Integer pay;

	private Integer realPay;

	private Timestamp payTime;

	private String orderRemark;

	private String payPath;

	private String pledgeName;

	private Integer pledgePrice;

	private Timestamp addTime;

	private String clockCatagory;

	public ServerOrder() {
	}

	public ServerOrder(String idUser, Integer id, String idRoom, String idOrder, String status, Timestamp startTime,
			Timestamp endTime, Integer pay, Integer realPay, Timestamp payTime, String orderRemark, String payPath,
			String pledgeName, Integer pledgePrice, Timestamp addTime, String clockCatagory) {
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
	
	public ServerOrder(Builder b) {
		super();
		this.idUser = b.idUser;
		this.id = b.id;
		this.idRoom = b.idRoom;
		this.idOrder = b.idOrder;
		this.status = b.status;
		this.startTime = b.startTime;
		this.endTime = b.endTime;
		this.pay = b.pay;
		this.realPay = b.realPay;
		this.payTime = b.payTime;
		this.orderRemark = b.orderRemark;
		this.payPath = b.payPath;
		this.pledgeName = b.pledgeName;
		this.pledgePrice = b.pledgePrice;
		this.addTime = b.addTime;
		this.clockCatagory = b.clockCatagory;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Integer getRealPay() {
		return realPay;
	}

	public void setRealPay(Integer realPay) {
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

	public Integer getPledgePrice() {
		return pledgePrice;
	}

	public void setPledgePrice(Integer pledgePrice) {
		this.pledgePrice = pledgePrice;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + ((idOrder == null) ? 0 : idOrder.hashCode());
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
		ServerOrder other = (ServerOrder) obj;
		if (idOrder == null) {
			if (other.idOrder != null)
				return false;
		} else if (!idOrder.equals(other.idOrder))
			return false;
		return true;
	}

	@Override
	public ServerOrder clone() {
		ServerOrder c = null;
		try {
			c = (ServerOrder) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		c.idUser = idUser + "";

		c.idRoom = "" + idRoom;

		c.idOrder = "" + idOrder;

		c.status = "" + status;

		c.startTime = (Timestamp) startTime.clone();

		c.endTime = (Timestamp) endTime.clone();

		c.payTime = (Timestamp) payTime.clone();

		c.orderRemark = "" + orderRemark;

		c.payPath = "" + payPath;

		c.pledgeName = "" + pledgeName;

		c.addTime = (Timestamp) addTime.clone();

		c.clockCatagory = "" + clockCatagory;

		return c;
	}
	
	
	
	@Override
	public String toString() {
		return "ServerOrder [idUser=" + idUser + ", id=" + id + ", idRoom=" + idRoom + ", idOrder=" + idOrder
				+ ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", pay=" + pay
				+ ", realPay=" + realPay + ", payTime=" + payTime + ", orderRemark=" + orderRemark + ", payPath="
				+ payPath + ", pledgeName=" + pledgeName + ", pledgePrice=" + pledgePrice + ", addTime=" + addTime
				+ ", clockCatagory=" + clockCatagory + "]";
	}



	public static class Builder{
		
		private String idUser;
		
		public Builder idUser(String idUser){
			this.idUser = idUser;
			return this;
		}

		private Integer id;
		
		public Builder id(Integer id){
			this.id = id;
			return this;
		}

		private String idRoom;
		
		public Builder idRoom(String idRoom){
			this.idRoom = idRoom;
			return this;
		}
		

		private String idOrder;

		public Builder idOrder(String idOrder){
			this.idOrder = idOrder;
			return this;
		}
		private String status;
		public Builder status(String status){
			this.status = status;
			return this;
		}

		private Timestamp startTime;

		public Builder startTime(Timestamp startTime){
			this.startTime = startTime;
			return this;
		}
		private Timestamp endTime;
		public Builder endTime(Timestamp endTime){
			this.endTime = endTime;
			return this;
		}

		private Integer pay;
		public Builder pay(Integer pay){
			this.pay = pay;
			return this;
		}

		private Integer realPay;
		public Builder realPay(Integer realPay){
			this.realPay = realPay;
			return this;
		}

		private Timestamp payTime;
		public Builder payTime(Timestamp payTime){
			this.payTime = payTime;
			return this;
		}

		private String orderRemark;
		public Builder orderRemark(String orderRemark){
			this.orderRemark = orderRemark;
			return this;
		}

		private String payPath;
		public Builder payPath(String payPath){
			this.payPath = payPath;
			return this;
		}

		private String pledgeName;
		public Builder pledgeName(String pledgeName){
			this.pledgeName = pledgeName;
			return this;
		}

		private Integer pledgePrice;
		public Builder pledgePrice(Integer pledgePrice){
			this.pledgePrice = pledgePrice;
			return this;
		}

		private Timestamp addTime;
		public Builder addTime(Timestamp addTime){
			this.addTime = addTime;
			return this;
		}

		private String clockCatagory;
		
		public Builder clockCatagory(String clockCatagory){
			this.clockCatagory = clockCatagory;
			return this;
		}
		
		public Builder(String idOrder) {
			this.idOrder = idOrder;
		}
		
		public ServerOrder build(){
			return new ServerOrder(this);
		}

		@Override
		public String toString() {
			return "Builder [idUser=" + idUser + ", id=" + id + ", idRoom=" + idRoom + ", idOrder=" + idOrder
					+ ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", pay=" + pay
					+ ", realPay=" + realPay + ", payTime=" + payTime + ", orderRemark=" + orderRemark + ", payPath="
					+ payPath + ", pledgeName=" + pledgeName + ", pledgePrice=" + pledgePrice + ", addTime=" + addTime
					+ ", clockCatagory=" + clockCatagory + "]";
		}
		
		
	}

}
