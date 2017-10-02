package com.cyl.basic;

public class OrderStatus {

	private int number;
	private String orderStatus;
	
	public OrderStatus() {
		
	}
	
	public OrderStatus(int number, String orderStatus){
		
		this.orderStatus = orderStatus;
		this.number = number;
		
	}
	
 	public int getNumber() {
		return number;
	}

	public String getOrderStatus() {
		return orderStatus;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStatus other = (OrderStatus) obj;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		return result;
	}
	
		
}
