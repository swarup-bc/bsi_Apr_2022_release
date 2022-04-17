package com.urs.bsi.model.vo.spreadingreq;

public class AppInfo {
	
	private String process_control_number;
	
	private String prospect_id;
	private String channel_indicator;
	private String market_code;
	private String usecase_id;
	private String requesting_bu;
	public String getProcess_control_number() {
		return process_control_number;
	}
	public void setProcess_control_number(String process_control_number) {
		this.process_control_number = process_control_number;
	}
	public String getProspect_id() {
		return prospect_id;
	}
	public void setProspect_id(String prospect_id) {
		this.prospect_id = prospect_id;
	}
	public String getChannel_indicator() {
		return channel_indicator;
	}
	public void setChannel_indicator(String channel_indicator) {
		this.channel_indicator = channel_indicator;
	}
	public String getMarket_code() {
		return market_code;
	}
	public void setMarket_code(String market_code) {
		this.market_code = market_code;
	}
	public String getUsecase_id() {
		return usecase_id;
	}
	public void setUsecase_id(String usecase_id) {
		this.usecase_id = usecase_id;
	}
	public String getRequesting_bu() {
		return requesting_bu;
	}
	public void setRequesting_bu(String requesting_bu) {
		this.requesting_bu = requesting_bu;
	}

}
