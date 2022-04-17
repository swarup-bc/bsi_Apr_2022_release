package com.urs.bsi.model;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	
	protected HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
