package ru.alikhano.cyberlife.DTO;

public class CustomException extends Exception{
	
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CustomException(String message) {
		this.message = message;
	}
	
	public CustomException() {
		
	}

	public String getErrMessage() {
		return message;
	}

	public void setErrMessage(String message) {
		this.message = message;
	}

	
}
