package ru.alikhano.cyberlife.dto;

public class CustomLogicException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CustomLogicException(String message) {
		this.message = message;
	}
	
	public CustomLogicException() {}

	public String getErrMessage() {
		return message;
	}

	public void setErrMessage(String message) {
		this.message = message;
	}
}
