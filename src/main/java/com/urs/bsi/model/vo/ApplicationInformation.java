package com.urs.bsi.model.vo;

public class ApplicationInformation {

	private String process_control_number;
	private BusinessInformation business_information;
	public String getProcess_control_number() {
		return process_control_number;
	}
	public void setProcess_control_number(String process_control_number) {
		this.process_control_number = process_control_number;
	}
	public BusinessInformation getBusiness_information() {
		return business_information;
	}
	public void setBusiness_information(BusinessInformation business_information) {
		this.business_information = business_information;
	}
	
}
