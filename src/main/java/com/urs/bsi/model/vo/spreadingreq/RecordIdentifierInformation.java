package com.urs.bsi.model.vo.spreadingreq;

import java.sql.Timestamp;

public class RecordIdentifierInformation {
	
	private String request_date;
	
	private String client_platform;
	
	private String owning_system_name;
	
	private String request_timestamp;

	public String getRequest_date() {
		return request_date;
	}

	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}

	public String getClient_platform() {
		return client_platform;
	}

	public void setClient_platform(String client_platform) {
		this.client_platform = client_platform;
	}

	public String getOwning_system_name() {
		return owning_system_name;
	}

	public void setOwning_system_name(String owning_system_name) {
		this.owning_system_name = owning_system_name;
	}

	public String getRequest_timestamp() {
		return request_timestamp;
	}

	public void setRequest_timestamp(String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}
}
