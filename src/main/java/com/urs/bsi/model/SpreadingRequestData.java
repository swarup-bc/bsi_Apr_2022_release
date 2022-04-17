package com.urs.bsi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="SPREADING_REQUEST_DATA")
public class SpreadingRequestData implements BaseEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SRD_ID")
	private Long id;
	
	private String client_app_id;
	private String correlation_id;
	private String general_comments;
	private String respreading_indicator;
	private String respreading_comment;
	private String request_date;
	private String client_platform;
	private String owning_system_name;
	private String request_timestamp;
	private String company_id;
	private String corporate_id;
	private String account_number_company;
	private String region;
	private String ticker_symbol;
	private String state_company;
	private String count_of_documents;
	private String city_company;
	private String address_line1_company;
	private String company_name;
	private String country_company;
	private String industry_code_classification;
	private String industry_code;
	private String customer_id;
	private String country_customer;
	private String state_customer;
	private String account_number_customer;
	private String product_type;
	private String city_customer;
	private String address_line1_customer;
	private String customer_name;
	private String process_control_number;
	private String prospect_id;
	private String channel_indicator;
	private String market_code;
	private String usecase_id;
	private String requesting_bu;
	private String created_datetime;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="refSrdId")
	@Fetch(value=FetchMode.SUBSELECT)
	private List<SpreadingDocumentInformation> spreadingDocumentInformationList;
	
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
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	public String getGeneral_comments() {
		return general_comments;
	}
	public void setGeneral_comments(String general_comments) {
		this.general_comments = general_comments;
	}
	public String getRespreading_indicator() {
		return respreading_indicator;
	}
	public void setRespreading_indicator(String respreading_indicator) {
		this.respreading_indicator = respreading_indicator;
	}
	public String getRespreading_comment() {
		return respreading_comment;
	}
	public void setRespreading_comment(String respreading_comment) {
		this.respreading_comment = respreading_comment;
	}
	
	public String getClient_platform() {
		return client_platform;
	}
	public void setClient_platform(String client_platform) {
		this.client_platform = client_platform;
	}
	public String getOwning_system_name() {
		return owning_system_name;
	}
	public void setOwning_system_name(String owning_system_name) {
		this.owning_system_name = owning_system_name;
	}
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCorporate_id() {
		return corporate_id;
	}
	public void setCorporate_id(String corporate_id) {
		this.corporate_id = corporate_id;
	}
	public String getAccount_number_company() {
		return account_number_company;
	}
	public void setAccount_number_company(String account_number_company) {
		this.account_number_company = account_number_company;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTicker_symbol() {
		return ticker_symbol;
	}
	public void setTicker_symbol(String ticker_symbol) {
		this.ticker_symbol = ticker_symbol;
	}
	public String getState_company() {
		return state_company;
	}
	public void setState_company(String state_company) {
		this.state_company = state_company;
	}
	public String getCount_of_documents() {
		return count_of_documents;
	}
	public void setCount_of_documents(String count_of_documents) {
		this.count_of_documents = count_of_documents;
	}
	public String getCity_company() {
		return city_company;
	}
	public void setCity_company(String city_company) {
		this.city_company = city_company;
	}
	public String getAddress_line1_company() {
		return address_line1_company;
	}
	public void setAddress_line1_company(String address_line1_company) {
		this.address_line1_company = address_line1_company;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCountry_company() {
		return country_company;
	}
	public void setCountry_company(String country_company) {
		this.country_company = country_company;
	}
	public String getIndustry_code_classification() {
		return industry_code_classification;
	}
	public void setIndustry_code_classification(String industry_code_classification) {
		this.industry_code_classification = industry_code_classification;
	}
	public String getIndustry_code() {
		return industry_code;
	}
	public void setIndustry_code(String industry_code) {
		this.industry_code = industry_code;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCountry_customer() {
		return country_customer;
	}
	public void setCountry_customer(String country_customer) {
		this.country_customer = country_customer;
	}
	public String getState_customer() {
		return state_customer;
	}
	public void setState_customer(String state_customer) {
		this.state_customer = state_customer;
	}
	public String getAccount_number_customer() {
		return account_number_customer;
	}
	public void setAccount_number_customer(String account_number_customer) {
		this.account_number_customer = account_number_customer;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getCity_customer() {
		return city_customer;
	}
	public void setCity_customer(String city_customer) {
		this.city_customer = city_customer;
	}
	public String getAddress_line1_customer() {
		return address_line1_customer;
	}
	public void setAddress_line1_customer(String address_line1_customer) {
		this.address_line1_customer = address_line1_customer;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
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
	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getRequest_timestamp() {
		return request_timestamp;
	}
	public void setRequest_timestamp(String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}
	public List<SpreadingDocumentInformation> getSpreadingDocumentInformationList() {
		return spreadingDocumentInformationList;
	}
	public void setSpreadingDocumentInformationList(List<SpreadingDocumentInformation> spreadingDocumentInformationList) {
		this.spreadingDocumentInformationList = spreadingDocumentInformationList;
	}
	public String getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(String created_datetime) {
		this.created_datetime = created_datetime;
	}
}
