package com.urs.bsi.model.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerDataVo {
	
	private String process_control_number;
	private String business_id;
	private String case_id;
	private String request_limit;
	private String spread_date;
	private String account_business_unit_code;
	private String container_type;
	private String source_of_origin;
	private String company_name;
	private String doing_business_as_name;
	private String consumer_name;
	private String re_spread_indicator;
	private List<RequestedAccount> requested_account;
	private List<AccountDataVo> account_data;
	
	@JsonIgnore
	private String usecaseId;
	
	public String getUsecaseId() {
		return usecaseId;
	}
	public void setUsecaseId(String usecaseId) {
		this.usecaseId = usecaseId;
	}
	public String getRe_spread_indicator() {
		return re_spread_indicator;
	}
	public void setRe_spread_indicator(String re_spread_indicator) {
		this.re_spread_indicator = re_spread_indicator;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDoing_business_as_name() {
		return doing_business_as_name;
	}
	public void setDoing_business_as_name(String doing_business_as_name) {
		this.doing_business_as_name = doing_business_as_name;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public String getProcess_control_number() {
		return process_control_number;
	}
	public void setProcess_control_number(String process_control_number) {
		this.process_control_number = process_control_number;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getCase_id() {
		return case_id;
	}
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}
	public String getRequest_limit() {
		return request_limit;
	}
	public void setRequest_limit(String request_limit) {
		this.request_limit = request_limit;
	}
	public String getSpread_date() {
		return spread_date;
	}
	public void setSpread_date(String spread_date) {
		this.spread_date = spread_date;
	}
	public String getAccount_business_unit_code() {
		return account_business_unit_code;
	}
	public void setAccount_business_unit_code(String account_business_unit_code) {
		this.account_business_unit_code = account_business_unit_code;
	}
	public String getContainer_type() {
		return container_type;
	}
	public void setContainer_type(String container_type) {
		this.container_type = container_type;
	}
	public String getSource_of_origin() {
		return source_of_origin;
	}
	public void setSource_of_origin(String source_of_origin) {
		this.source_of_origin = source_of_origin;
	}
	
	public List<RequestedAccount> getRequested_account() {
		return requested_account;
	}
	public void setRequested_account(List<RequestedAccount> requested_account) {
		this.requested_account = requested_account;
	}
	public List<AccountDataVo> getAccount_data() {
		return account_data;
	}
	public void setAccount_data(List<AccountDataVo> account_data) {
		this.account_data = account_data;
	}
	

}
