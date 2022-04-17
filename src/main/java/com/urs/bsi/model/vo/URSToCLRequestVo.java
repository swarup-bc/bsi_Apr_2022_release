package com.urs.bsi.model.vo;

public class URSToCLRequestVo {

	private SpreadingStatusInformation spreading_status_information;
	private RequesterInformation requestor_information;
	private ApplicationInformation application_information;
	
	public SpreadingStatusInformation getSpreading_status_information() {
		return spreading_status_information;
	}
	public void setSpreading_status_information(SpreadingStatusInformation spreading_status_information) {
		this.spreading_status_information = spreading_status_information;
	}
	public RequesterInformation getRequestor_information() {
		return requestor_information;
	}
	public void setRequestor_information(RequesterInformation requestor_information) {
		this.requestor_information = requestor_information;
	}
	public ApplicationInformation getApplication_information() {
		return application_information;
	}
	public void setApplication_information(ApplicationInformation application_information) {
		this.application_information = application_information;
	}
	
}
