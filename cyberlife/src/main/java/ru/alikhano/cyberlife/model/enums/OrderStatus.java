package ru.alikhano.cyberlife.model.enums;

public enum OrderStatus {
	
	AWAITSDEL("awaits delivery"),
	AWAITSPICK("delivered, awaits pickup"),
	RECIEVED("delivered and recieved");
	
	private final String statusOrder;

	private OrderStatus(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	public String getStatusOrder() {
		return statusOrder;
	}

	
	

}
