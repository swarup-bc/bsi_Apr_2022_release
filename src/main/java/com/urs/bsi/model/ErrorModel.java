package com.urs.bsi.model;

public class ErrorModel extends ResponseModel{
	
	private Integer errorCode;
	private String message;
	
	public ErrorModel(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
