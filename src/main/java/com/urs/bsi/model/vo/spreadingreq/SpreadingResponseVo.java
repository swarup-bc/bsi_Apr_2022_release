package com.urs.bsi.model.vo.spreadingreq;

public class SpreadingResponseVo {
	
	private String process_control_number;
	private String client_app_id;
	private String response_id;
	private String response_timestamp;
	private String response_code;
	
	public String getProcess_control_number() {
		return process_control_number;
	}
	public void setProcess_control_number(String process_control_number) {
		this.process_control_number = process_control_number;
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
	public String getResponse_timestamp() {
		return response_timestamp;
	}
	public void setResponse_timestamp(String response_timestamp) {
		this.response_timestamp = response_timestamp;
	}
	public String getResponse_code() {
		return response_code;
	}
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

}
