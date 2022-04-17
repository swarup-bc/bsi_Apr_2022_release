package com.urs.bsi.model.vo;

public class SpreadingStatusInformation {

	private SpreaderInformation spreader_information;
	private String spread_type;
	private String spread_status;
	private String spread_start;
	private String spread_error_code;
	private String spread_end;
	private String spread_case_id;
	private String overall_spread_notes;
	private DocumentInformation document_information; 
	private BankInformation bank_information;
	
	public SpreaderInformation getSpreader_information() {
		return spreader_information;
	}
	public void setSpreader_information(SpreaderInformation spreader_information) {
		this.spreader_information = spreader_information;
	}
	public String getSpread_type() {
		return spread_type;
	}
	public void setSpread_type(String spread_type) {
		this.spread_type = spread_type;
	}
	public String getSpread_status() {
		return spread_status;
	}
	public void setSpread_status(String spread_status) {
		this.spread_status = spread_status;
	}
	public String getSpread_start() {
		return spread_start;
	}
	public void setSpread_start(String spread_start) {
		this.spread_start = spread_start;
	}
	public String getSpread_error_code() {
		return spread_error_code;
	}
	public void setSpread_error_code(String spread_error_code) {
		this.spread_error_code = spread_error_code;
	}
	public String getSpread_end() {
		return spread_end;
	}
	public void setSpread_end(String spread_end) {
		this.spread_end = spread_end;
	}
	public String getSpread_case_id() {
		return spread_case_id;
	}
	public void setSpread_case_id(String spread_case_id) {
		this.spread_case_id = spread_case_id;
	}
	public String getOverall_spread_notes() {
		return overall_spread_notes;
	}
	public void setOverall_spread_notes(String overall_spread_notes) {
		this.overall_spread_notes = overall_spread_notes;
	}
	public DocumentInformation getDocument_information() {
		return document_information;
	}
	public void setDocument_information(DocumentInformation document_information) {
		this.document_information = document_information;
	}
	public BankInformation getBank_information() {
		return bank_information;
	}
	public void setBank_information(BankInformation bank_information) {
		this.bank_information = bank_information;
	}
	
}
