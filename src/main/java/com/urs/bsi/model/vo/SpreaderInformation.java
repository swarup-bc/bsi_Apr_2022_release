package com.urs.bsi.model.vo;

import java.util.List;

public class SpreaderInformation {

	private String spreader_name;
	private List<SpreaderId> spreader_id;
	public String getSpreader_name() {
		return spreader_name;
	}
	public void setSpreader_name(String spreader_name) {
		this.spreader_name = spreader_name;
	}
	public List<SpreaderId> getSpreader_id() {
		return spreader_id;
	}
	public void setSpreader_id(List<SpreaderId> spreader_id) {
		this.spreader_id = spreader_id;
	}
	
}
