package com.mycompany.action;

public enum ResultType {

	SUCCESS("OK"), FAILED("ERROR");

	private String message = null;

	private ResultType(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
