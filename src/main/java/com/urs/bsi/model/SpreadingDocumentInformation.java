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
@Table(name="SPREADING_DOCUMENT_INFORMATION")
public class SpreadingDocumentInformation implements BaseEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DOC_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="REF_SRD_ID",referencedColumnName="SRD_ID")
	private SpreadingRequestData refSrdId;
	
	private String document_name;
	private String document_id;
	private String files_resource_id;
	private String document_resource_id;
	private String document_url;
	private String document_type;
	private String comments;
	private String respreading_instruction;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SpreadingRequestData getRefSrdId() {
		return refSrdId;
	}
	public void setRefSrdId(SpreadingRequestData refSrdId) {
		this.refSrdId = refSrdId;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	public String getDocument_id() {
		return document_id;
	}
	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}
	public String getFiles_resource_id() {
		return files_resource_id;
	}
	public void setFiles_resource_id(String files_resource_id) {
		this.files_resource_id = files_resource_id;
	}
	public String getDocument_resource_id() {
		return document_resource_id;
	}
	public void setDocument_resource_id(String document_resource_id) {
		this.document_resource_id = document_resource_id;
	}
	public String getDocument_url() {
		return document_url;
	}
	public void setDocument_url(String document_url) {
		this.document_url = document_url;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRespreading_instruction() {
		return respreading_instruction;
	}
	public void setRespreading_instruction(String respreading_instruction) {
		this.respreading_instruction = respreading_instruction;
	}
	
}
