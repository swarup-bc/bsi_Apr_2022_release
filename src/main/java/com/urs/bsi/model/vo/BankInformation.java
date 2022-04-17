package com.urs.bsi.model.vo;

public class BankInformation {

	private String routing_number;
	private String full_account_and_routing_number_extracted;
	private String bank_name;
	private String account_number;
	public String getRouting_number() {
		return routing_number;
	}
	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}
	public String getFull_account_and_routing_number_extracted() {
		return full_account_and_routing_number_extracted;
	}
	public void setFull_account_and_routing_number_extracted(String full_account_and_routing_number_extracted) {
		this.full_account_and_routing_number_extracted = full_account_and_routing_number_extracted;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	
}
