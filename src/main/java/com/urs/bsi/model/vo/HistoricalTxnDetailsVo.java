package com.urs.bsi.model.vo;

import java.util.Date;

public class HistoricalTxnDetailsVo {

	private Long ttid;
	private String docname;
	private String se10;
	private String contract_nbr;
	private String amort_date;
	private String instant_decision_date;
	private String account_number;
	private String txn_id;
	private String description;
	private String check_number;
	private String txn_date;
	private String txn_amount;
	private String currency;
	private String debit_credit;
	private String available_balance;
	private String category;
	private Date ttime;
	public Long getTtid() {
		return ttid;
	}
	public void setTtid(Long ttid) {
		this.ttid = ttid;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getSe10() {
		return se10;
	}
	public void setSe10(String se10) {
		this.se10 = se10;
	}
	public String getContract_nbr() {
		return contract_nbr;
	}
	public void setContract_nbr(String contract_nbr) {
		this.contract_nbr = contract_nbr;
	}
	public String getAmort_date() {
		return amort_date;
	}
	public void setAmort_date(String amort_date) {
		this.amort_date = amort_date;
	}
	public String getInstant_decision_date() {
		return instant_decision_date;
	}
	public void setInstant_decision_date(String instant_decision_date) {
		this.instant_decision_date = instant_decision_date;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getTxn_date() {
		return txn_date;
	}
	public void setTxn_date(String txn_date) {
		this.txn_date = txn_date;
	}
	public String getTxn_amount() {
		return txn_amount;
	}
	public void setTxn_amount(String txn_amount) {
		this.txn_amount = txn_amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDebit_credit() {
		return debit_credit;
	}
	public void setDebit_credit(String debit_credit) {
		this.debit_credit = debit_credit;
	}
	public String getAvailable_balance() {
		return available_balance;
	}
	public void setAvailable_balance(String available_balance) {
		this.available_balance = available_balance;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getTtime() {
		return ttime;
	}
	public void setTtime(Date ttime) {
		this.ttime = ttime;
	}
	
}
