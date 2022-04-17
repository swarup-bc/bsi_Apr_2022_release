package com.urs.bsi.model.vo;

public class RequesterInformation {

	private String system_name;
	private String date_timestamp;
	private String correlation_id;
	public String getSystem_name() {
		return system_name;
	}
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}
	public String getDate_timestamp() {
		return date_timestamp;
	}
	public void setDate_timestamp(String date_timestamp) {
		this.date_timestamp = date_timestamp;
	}
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	
}
