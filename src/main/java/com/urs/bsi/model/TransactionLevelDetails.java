package com.urs.bsi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urs.bsi.model.vo.BaseVo;

@Entity
@Table(name="STAGING_TXN_LEVEL_DETAILS")
public class TransactionLevelDetails implements BaseEntity, BaseVo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TXN_LEVEL_ID")
	private Long id;
	
	private String se10;
	private String contractNbr;
	private String amortDate; 
	private String instantDecisionDate;
	private String accountNum;
	private String txnId;
	private String description;
	private String checkNum;
	private String txnDate;
	private String txnAmount;
	private String currency;
	private String debitCredit;
	private String availableBalance;
	private String batchId;
	private Date createdDate;
	
	private Long totalCount;
	
	public String getSe10() {
		return se10;
	}
	public void setSe10(String se10) {
		this.se10 = se10;
	}
	public String getContractNbr() {
		return contractNbr;
	}
	public void setContractNbr(String contractNbr) {
		this.contractNbr = contractNbr;
	}
	public String getInstantDecisionDate() {
		return instantDecisionDate;
	}
	public void setInstantDecisionDate(String instantDecisionDate) {
		this.instantDecisionDate = instantDecisionDate;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getAmortDate() {
		return amortDate;
	}
	public void setAmortDate(String amortDate) {
		this.amortDate = amortDate;
	}
	public String getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDebitCredit() {
		return debitCredit;
	}
	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBatchId() {
		return batchId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
