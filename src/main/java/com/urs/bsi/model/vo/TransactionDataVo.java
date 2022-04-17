package com.urs.bsi.model.vo;

public class TransactionDataVo {
	private String transaction_id;
	private String description;
	private String transaction_amount;
	private String posted_order;
	private String transaction_date;
	private String transaction_type;
	private String available_balance_amount;
	private String check_number;
	private String transaction_amount_currency_code;
	
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getPosted_order() {
		return posted_order;
	}
	public void setPosted_order(String posted_order) {
		this.posted_order = posted_order;
	}
	
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	public String getAvailable_balance_amount() {
		return available_balance_amount;
	}
	public void setAvailable_balance_amount(String available_balance_amount) {
		this.available_balance_amount = available_balance_amount;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getTransaction_amount_currency_code() {
		return transaction_amount_currency_code;
	}
	public void setTransaction_amount_currency_code(String transaction_amount_currency_code) {
		this.transaction_amount_currency_code = transaction_amount_currency_code;
	}
	
}
