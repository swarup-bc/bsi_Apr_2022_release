package com.urs.bsi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_spreading_adata")
public class SpreadingAccountData implements BaseEntity {

	@Id
	private Long id  ;
	private String businessUnit ;
	private String accountNumber ;
	private String accountHolderName ;
	private String secondaryAccountHolderName ;
	private String accountType ;
	private String accountOwnership ;
	private String bankName ;
	private String routingNo ;
	private BigDecimal currentBalance ;
	private String asOfDate ;
	private String taxPaymentIndicator ;
	private Integer totalCountOfOverUtilization ;
	private Integer interestServicingDays ;
	private String drawingPower ;

	
	@Column(name="ADDRESS_LINE_1")
	private String addressLine1 ;
	
	@Column(name="ADDRESS_LINE_2")
	private String addressLine2 ;
	
	@Column(name="ADDRESS_LINE_3")
	private String addressLine3 ;
	private String city ;
	private String state ;
	private String zip ;
	private String country ;
	private String countryCode ;
	private Long historyId;
	
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}
	
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getSecondaryAccountHolderName() {
		return secondaryAccountHolderName;
	}
	public void setSecondaryAccountHolderName(String secondaryAccountHolderName) {
		this.secondaryAccountHolderName = secondaryAccountHolderName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountOwnership() {
		return accountOwnership;
	}
	public void setAccountOwnership(String accountOwnership) {
		this.accountOwnership = accountOwnership;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRoutingNo() {
		return routingNo;
	}
	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}
	
	public String getDrawingPower() {
		return drawingPower;
	}
	public void setDrawingPower(String drawingPower) {
		this.drawingPower = drawingPower;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}
	public String getTaxPaymentIndicator() {
		return taxPaymentIndicator;
	}
	public void setTaxPaymentIndicator(String taxPaymentIndicator) {
		this.taxPaymentIndicator = taxPaymentIndicator;
	}
	public Integer getTotalCountOfOverUtilization() {
		return totalCountOfOverUtilization;
	}
	public void setTotalCountOfOverUtilization(Integer totalCountOfOverUtilization) {
		this.totalCountOfOverUtilization = totalCountOfOverUtilization;
	}
	public Integer getInterestServicingDays() {
		return interestServicingDays;
	}
	public void setInterestServicingDays(Integer interestServicingDays) {
		this.interestServicingDays = interestServicingDays;
	}
}
