package com.urs.bsi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urs_io_json_details")
public class URSIoJsonDetails {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="URS_IO_JSON_ID")
	private Long ursTOJsonId;
	
	private String correlationId ;
	private String toEntity ;
	private String fromEntity;
	private int retryCount;
	private String requestJson ; 
	private String responseJson ; 
	private String responseCode;
	private String createdDate;
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getUrsTOJsonId() {
		return ursTOJsonId;
	}
	public void setUrsTOJsonId(Long ursTOJsonId) {
		this.ursTOJsonId = ursTOJsonId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getToEntity() {
		return toEntity;
	}
	public void setToEntity(String toEntity) {
		this.toEntity = toEntity;
	}
	public String getFromEntity() {
		return fromEntity;
	}
	public void setFromEntity(String fromEntity) {
		this.fromEntity = fromEntity;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
}
