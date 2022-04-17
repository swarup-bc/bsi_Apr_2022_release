package com.urs.bsi.model.vo.spreadingreq;

import java.util.List;

public class SpreadingRequestVo {
	
	private RespreadingInformation respreading_information;
	
	private RecordIdentifierInformation record_identifier_information;
	
	private CompanyInfo company_info;
	
	private CustomerInfo customer_info;
	
	private List<DocumentInformation> document_information;
	
	private AppInfo app_info;

	private SpreadingRequestData spreading_request_data;
	

	public SpreadingRequestData getSpreading_request_data() {
		return spreading_request_data;
	}

	public void setSpreading_request_data(SpreadingRequestData spreading_request_data) {
		this.spreading_request_data = spreading_request_data;
	}

	public RespreadingInformation getRespreading_information() {
		return respreading_information;
	}

	public void setRespreading_information(RespreadingInformation respreading_information) {
		this.respreading_information = respreading_information;
	}

	public RecordIdentifierInformation getRecord_identifier_information() {
		return record_identifier_information;
	}

	public void setRecord_identifier_information(RecordIdentifierInformation record_identifier_information) {
		this.record_identifier_information = record_identifier_information;
	}

	public CompanyInfo getCompany_info() {
		return company_info;
	}

	public void setCompany_info(CompanyInfo company_info) {
		this.company_info = company_info;
	}

	public CustomerInfo getCustomer_info() {
		return customer_info;
	}

	public void setCustomer_info(CustomerInfo customer_info) {
		this.customer_info = customer_info;
	}

	public List<DocumentInformation> getDocument_information() {
		return document_information;
	}

	public void setDocument_information(List<DocumentInformation> document_information) {
		this.document_information = document_information;
	}

	public AppInfo getApp_info() {
		return app_info;
	}

	public void setApp_info(AppInfo app_info) {
		this.app_info = app_info;
	}
}
