package com.urs.bsi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Tbl_spreading_mdata")
public class SpreadingMasterData  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id	;
	
	private Long historyId;
	
	private String customerId;
	
	private String caseId;
	
	private String businessId;
		
	private String requestedLimit;
	
	@Column(name="SE10")
	private String se10;
	
	private String containerType;
	
	private String dataSource;
	
	private String spreadingDoneOn;
	
	private String applicationId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
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

	public String getSpreadingDoneOn() {
		return spreadingDoneOn;
	}

	public void setSpreadingDoneOn(String spreadingDoneOn) {
		this.spreadingDoneOn = spreadingDoneOn;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	
}
