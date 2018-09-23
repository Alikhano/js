package ru.alikhano.cyberlife.model.enums;

public enum PaymentStatus {
	
	UNPAID("unpaid"),
	PAID("paid");
	
	private final String statusPayment;

	private PaymentStatus(String statusPayment) {
		this.statusPayment = statusPayment;
	}

	public String getStatusPayment() {
		return statusPayment;
	}

	
	

}
