package com.urs.bsi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STAGING_SUMMARY_LEVEL_DETAILS")
public class SummaryLevelDetails implements BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUMMARY_ID")
	private Long id;
	
	private String se10;;
	private String contractnbr;
	private String amortDate;
	private String instantDecisionDate;
	private String accountNum;
	private String acctholdername;
	private String accttype;
	private String acctownership;
	private String nameofbank;
	private String bankAddress;
	private String bankCity;
	private String bankState;
	private String bankZip;
	private String currentBalance;
	private String startdate;
	private String enddate;
	private String openbalance;
	private String closingbalance;
	private String totalDollarDeposits;
	private String totalNumDeposits;
	private String totalDollarWithdrawals;
	private String totalNumWithdrawals;
	private String totalNumofcheckreturns;
	private String totalNumInwardCheckReturn;
	private String totalDollarInwardCheckReturn;
	private String totalNumOutwardCheckReturn;
	private String totalDollarOutwardCheckReturn;
	private String countEcsorEmiMonthly;
	private String amountEcsorEmiMonthly;
	private String routeNum;
	private String transactionallevelSpreadingDone;
	private String nativevsNonnative;
	private String checksum;
	private String summaryAndTransactionMatch;
	private String batchId;
	private Date createdDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSe10() {
		return se10;
	}
	public void setSe10(String se10) {
		this.se10 = se10;
	}
	public String getContractnbr() {
		return contractnbr;
	}
	public void setContractnbr(String contractnbr) {
		this.contractnbr = contractnbr;
	}
	public String getAmortDate() {
		return amortDate;
	}
	public void setAmortDate(String amortDate) {
		this.amortDate = amortDate;
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
	public String getAcctholdername() {
		return acctholdername;
	}
	public void setAcctholdername(String acctholdername) {
		this.acctholdername = acctholdername;
	}
	public String getAccttype() {
		return accttype;
	}
	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}
	public String getAcctownership() {
		return acctownership;
	}
	public void setAcctownership(String acctownership) {
		this.acctownership = acctownership;
	}
	public String getNameofbank() {
		return nameofbank;
	}
	public void setNameofbank(String nameofbank) {
		this.nameofbank = nameofbank;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankState() {
		return bankState;
	}
	public void setBankState(String bankState) {
		this.bankState = bankState;
	}
	public String getBankZip() {
		return bankZip;
	}
	public void setBankZip(String bankZip) {
		this.bankZip = bankZip;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getOpenbalance() {
		return openbalance;
	}
	public void setOpenbalance(String openbalance) {
		this.openbalance = openbalance;
	}
	public String getClosingbalance() {
		return closingbalance;
	}
	public void setClosingbalance(String closingbalance) {
		this.closingbalance = closingbalance;
	}
	public String getTotalDollarDeposits() {
		return totalDollarDeposits;
	}
	public void setTotalDollarDeposits(String totalDollarDeposits) {
		this.totalDollarDeposits = totalDollarDeposits;
	}
	public String getTotalNumDeposits() {
		return totalNumDeposits;
	}
	public void setTotalNumDeposits(String totalNumDeposits) {
		this.totalNumDeposits = totalNumDeposits;
	}
	public String getTotalDollarWithdrawals() {
		return totalDollarWithdrawals;
	}
	public void setTotalDollarWithdrawals(String totalDollarWithdrawals) {
		this.totalDollarWithdrawals = totalDollarWithdrawals;
	}
	public String getTotalNumWithdrawals() {
		return totalNumWithdrawals;
	}
	public void setTotalNumWithdrawals(String totalNumWithdrawals) {
		this.totalNumWithdrawals = totalNumWithdrawals;
	}
	public String getTotalNumofcheckreturns() {
		return totalNumofcheckreturns;
	}
	public void setTotalNumofcheckreturns(String totalNumofcheckreturns) {
		this.totalNumofcheckreturns = totalNumofcheckreturns;
	}
	public String getTotalNumInwardCheckReturn() {
		return totalNumInwardCheckReturn;
	}
	public void setTotalNumInwardCheckReturn(String totalNumInwardCheckReturn) {
		this.totalNumInwardCheckReturn = totalNumInwardCheckReturn;
	}
	public String getTotalDollarInwardCheckReturn() {
		return totalDollarInwardCheckReturn;
	}
	public void setTotalDollarInwardCheckReturn(String totalDollarInwardCheckReturn) {
		this.totalDollarInwardCheckReturn = totalDollarInwardCheckReturn;
	}
	public String getTotalNumOutwardCheckReturn() {
		return totalNumOutwardCheckReturn;
	}
	public void setTotalNumOutwardCheckReturn(String totalNumOutwardCheckReturn) {
		this.totalNumOutwardCheckReturn = totalNumOutwardCheckReturn;
	}
	public String getTotalDollarOutwardCheckReturn() {
		return totalDollarOutwardCheckReturn;
	}
	public void setTotalDollarOutwardCheckReturn(String totalDollarOutwardCheckReturn) {
		this.totalDollarOutwardCheckReturn = totalDollarOutwardCheckReturn;
	}
	public String getCountEcsorEmiMonthly() {
		return countEcsorEmiMonthly;
	}
	public void setCountEcsorEmiMonthly(String countEcsorEmiMonthly) {
		this.countEcsorEmiMonthly = countEcsorEmiMonthly;
	}
	public String getAmountEcsorEmiMonthly() {
		return amountEcsorEmiMonthly;
	}
	public void setAmountEcsorEmiMonthly(String amountEcsorEmiMonthly) {
		this.amountEcsorEmiMonthly = amountEcsorEmiMonthly;
	}
	public String getRouteNum() {
		return routeNum;
	}
	public void setRouteNum(String routeNum) {
		this.routeNum = routeNum;
	}
	public String getTransactionallevelSpreadingDone() {
		return transactionallevelSpreadingDone;
	}
	public void setTransactionallevelSpreadingDone(String transactionallevelSpreadingDone) {
		this.transactionallevelSpreadingDone = transactionallevelSpreadingDone;
	}
	public String getNativevsNonnative() {
		return nativevsNonnative;
	}
	public void setNativevsNonnative(String nativevsNonnative) {
		this.nativevsNonnative = nativevsNonnative;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getSummaryAndTransactionMatch() {
		return summaryAndTransactionMatch;
	}
	public void setSummaryAndTransactionMatch(String summaryAndTransactionMatch) {
		this.summaryAndTransactionMatch = summaryAndTransactionMatch;
	}
	public String getBatchId() {
		return batchId;
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
