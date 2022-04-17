package com.urs.bsi.model.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SpreadingMasterDataVo  {
	
	private Long id;
	
	private String customerId;
	
	private String caseId;
	
	private String businessId;
		
	private String requestedLimit;
	
	private String se10;
	
	private String containerType;
	
	private String dataSource;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date spreadingDoneOn;
	
	private String applicationId;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getRequestedLimit() {
		return requestedLimit;
	}

	public void setRequestedLimit(String requestedLimit) {
		this.requestedLimit = requestedLimit;
	}

	public String getSe10() {
		return se10;
	}

	public void setSe10(String se10) {
		this.se10 = se10;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Date getSpreadingDoneOn() {
		return spreadingDoneOn;
	}

	public void setSpreadingDoneOn(Date spreadingDoneOn) {
		this.spreadingDoneOn = spreadingDoneOn;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}
