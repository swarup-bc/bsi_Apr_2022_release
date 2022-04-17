package com.urs.bsi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SPREADING_RESPONSE_DATA")
public class SpreadingResponseData {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SP_RESPONSE_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="REF_SRD_ID",referencedColumnName="SRD_ID")
	private SpreadingRequestData refSrdId;
	
	private String client_app_id;
	private String response_id;
	private String response_timestamp;
	private String response_code;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClient_app_id() {
		return client_app_id;
	}
	public void setClient_app_id(String client_app_id) {
		this.client_app_id = client_app_id;
	}
	public String getResponse_id() {
		return response_id;
	}
	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}
	public String getResponse_timestamp() {
		return response_timestamp;
	}
	public void setResponse_timestamp(String response_timestamp) {
		this.response_timestamp = response_timestamp;
	}
	public String getResponse_code() {
		return response_code;
	}
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
	public SpreadingRequestData getRefSrdId() {
		return refSrdId;
	}
	public void setRefSrdId(SpreadingRequestData refSrdId) {
		this.refSrdId = refSrdId;
	}
}
