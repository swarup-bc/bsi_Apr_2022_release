package com.urs.bsi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_bank_statement_summary_level_data")
public class SpreadingSummaryData  implements BaseEntity {
	
	@Id
	private Long id;
	private Long historyId;
	private String accountNumber;
	private String startDate ;
	private String endDate ;
	private String statementPeriod ;
	private String currency;
	private String unitOfCurrency ;
	private String numberOfMonths ;
	private BigDecimal openBalance ;
	private BigDecimal closingBalance ;
	private BigDecimal totalDeposits ;
	private Integer countDeposits ;
	private BigDecimal totalWithdrawals ;
	private Integer countWithdrawals ;
	private Integer totalNumberOfCheckReturns ;
	private Integer totalNumberInwardCheckReturn ;
	private BigDecimal totalDollarInwardCheckReturn ;
	private Integer totalNumberOutwardCheckReturn ;
	private BigDecimal totalDollarOutwardCheckReturn ;
	private BigDecimal countEcsOrEmi ;
	private BigDecimal amountEcsOrEmi ;
	private BigDecimal minimumBalance ;
	private BigDecimal check_sum ;
	private Long spreadingAdataId;
	
	public Long getSpreading_adata_id() {
		return spreadingAdataId;
	}

	public void setSpreading_adata_id(Long spreading_adata_id) {
		this.spreadingAdataId = spreading_adata_id;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatementPeriod() {
		return statementPeriod;
	}
	public void setStatementPeriod(String statementPeriod) {
		this.statementPeriod = statementPeriod;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUnitOfCurrency() {
		return unitOfCurrency;
	}
	public void setUnitOfCurrency(String unitOfCurrency) {
		this.unitOfCurrency = unitOfCurrency;
	}
	public String getNumberOfMonths() {
		return numberOfMonths;
	}
	public void setNumberOfMonths(String numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}
	public BigDecimal getOpenBalance() {
		return openBalance;
	}
	public void setOpenBalance(BigDecimal openBalance) {
		this.openBalance = openBalance;
	}
	public BigDecimal getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}
	public BigDecimal getTotalDeposits() {
		return totalDeposits;
	}
	public void setTotalDeposits(BigDecimal totalDeposits) {
		this.totalDeposits = totalDeposits;
	}
	public Integer getCountDeposits() {
		return countDeposits;
	}
	public void setCountDeposits(Integer countDeposits) {
		this.countDeposits = countDeposits;
	}
	public BigDecimal getTotalWithdrawals() {
		return totalWithdrawals;
	}
	public void setTotalWithdrawals(BigDecimal totalWithdrawals) {
		this.totalWithdrawals = totalWithdrawals;
	}
	public Integer getCountWithdrawals() {
		return countWithdrawals;
	}
	public void setCountWithdrawals(Integer countWithdrawals) {
		this.countWithdrawals = countWithdrawals;
	}
	public Integer getTotalNumberOfCheckReturns() {
		return totalNumberOfCheckReturns;
	}
	public void setTotalNumberOfCheckReturns(Integer totalNumberOfCheckReturns) {
		this.totalNumberOfCheckReturns = totalNumberOfCheckReturns;
	}
	public Integer getTotalNumberInwardCheckReturn() {
		return totalNumberInwardCheckReturn;
	}
	public void setTotalNumberInwardCheckReturn(Integer totalNumberInwardCheckReturn) {
		this.totalNumberInwardCheckReturn = totalNumberInwardCheckReturn;
	}
	public BigDecimal getTotalDollarInwardCheckReturn() {
		return totalDollarInwardCheckReturn;
	}
	public void setTotalDollarInwardCheckReturn(BigDecimal totalDollarInwardCheckReturn) {
		this.totalDollarInwardCheckReturn = totalDollarInwardCheckReturn;
	}
	public Integer getTotalNumberOutwardCheckReturn() {
		return totalNumberOutwardCheckReturn;
	}
	public void setTotalNumberOutwardCheckReturn(Integer totalNumberOutwardCheckReturn) {
		this.totalNumberOutwardCheckReturn = totalNumberOutwardCheckReturn;
	}
	public BigDecimal getTotalDollarOutwardCheckReturn() {
		return totalDollarOutwardCheckReturn;
	}
	public void setTotalDollarOutwardCheckReturn(BigDecimal totalDollarOutwardCheckReturn) {
		this.totalDollarOutwardCheckReturn = totalDollarOutwardCheckReturn;
	}
	public BigDecimal getCountEcsOrEmi() {
		return countEcsOrEmi;
	}
	public void setCountEcsOrEmi(BigDecimal countEcsOrEmi) {
		this.countEcsOrEmi = countEcsOrEmi;
	}
	public BigDecimal getAmountEcsOrEmi() {
		return amountEcsOrEmi;
	}
	public void setAmountEcsOrEmi(BigDecimal amountEcsOrEmi) {
		this.amountEcsOrEmi = amountEcsOrEmi;
	}
	public BigDecimal getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(BigDecimal minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
	public BigDecimal getCheck_sum() {
		return check_sum;
	}
	public void setCheck_sum(BigDecimal check_sum) {
		this.check_sum = check_sum;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
