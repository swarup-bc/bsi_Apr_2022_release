package com.urs.bsi.model.vo;

public class FGToURSResponseVo<T> {
	
	private String client_app_id;
	private String response_id;
	private String service_platform;
	private String service_app_id;
	private String response_timeStamp;
	private String response_code;
	private String client_platform_code;
	private String response_message;
	
	public String getResponse_message() {
		return response_message;
	}
	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}
	public String getClient_platform_code() {
		return client_platform_code;
	}
	public void setClient_platform_code(String client_platform_code) {
		this.client_platform_code = client_platform_code;
	}
	public String getClient_app_id() {
		return client_app_id;
	}
	public void setClient_app_id(String client_app_id) {
		this.client_app_id = client_app_id;
	}
	public String getResponse_id() {
		return response_id;
	}
	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}
	public String getService_platform() {
		return service_platform;
	}
	public void setService_platform(String service_platform) {
		this.service_platform = service_platform;
	}
	public String getService_app_id() {
		return service_app_id;
	}
	public void setService_app_id(String service_app_id) {
		this.service_app_id = service_app_id;
	}
	public String getResponse_timeStamp() {
		return response_timeStamp;
	}
	public void setResponse_timeStamp(String response_timeStamp) {
		this.response_timeStamp = response_timeStamp;
	}
	public String getResponse_code() {
		return response_code;
	}
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
	
}
