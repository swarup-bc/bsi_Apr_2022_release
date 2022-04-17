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
@Table(name="SPREADING_RESPONSE_FAILURE")
public class SpreadingResponseFailure {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SRF_ID INT")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="REF_SPR_ID",referencedColumnName="SP_RESPONSE_ID")
	private SpreadingResponseData refSprId;

	private String correlation_id;
	private String input_json;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SpreadingResponseData getRefSprId() {
		return refSprId;
	}
	public void setRefSprId(SpreadingResponseData refSprId) {
		this.refSprId = refSprId;
	}
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	public String getInput_json() {
		return input_json;
	}
	public void setInput_json(String input_json) {
		this.input_json = input_json;
	}
	
}
