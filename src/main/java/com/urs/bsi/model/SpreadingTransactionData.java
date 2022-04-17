package com.urs.bsi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_customer_txn_data")
public class SpreadingTransactionData  implements BaseEntity {
	
	@Id
	private Long id;
	
	private Long historyId;
	
	private long summaryLevelDataId;
	
	@Column(name="TRANSACTION_ID")
	private String transactionId ;
	
	@Column(name="Txn_date")
	private String transactionDate ;
	
	@Column(name="Description")
	private String description ;
	
	@Column(name="Check_no")
	private String checkNumber ;
	
	@Column(name="Txn_amt")
	private BigDecimal transactionAmount ;
	
	@Column(name="currency")
	private String transactionCurrencyCode ;
		
	@Column(name="POSTED_ORDER")
	private String postedOrder ;
	
	@Column(name="AVAILABLE_BALANCE")
	private BigDecimal availableBalance ;
	
	@Column(name="Type")
	private String creditOrDebit ;

	public long getSummaryLevelDataId() {
		return summaryLevelDataId;
	}

	public void setSummaryLevelDataId(long summaryLevelDataId) {
		this.summaryLevelDataId = summaryLevelDataId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}

	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}

	public String getPostedOrder() {
		return postedOrder;
	}

	public void setPostedOrder(String postedOrder) {
		this.postedOrder = postedOrder;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCreditOrDebit() {
		return creditOrDebit;
	}

	public void setCreditOrDebit(String creditOrDebit) {
		this.creditOrDebit = creditOrDebit;
	}

	
}
