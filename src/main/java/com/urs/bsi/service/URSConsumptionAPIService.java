package com.urs.bsi.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.urs.bsi.model.vo.CLToURSResponseVo;
import com.urs.bsi.model.vo.DocumentRejectionResponseVo;
import com.urs.bsi.model.vo.FGToURSResponseVo;

import io.swagger.models.Info;

public interface URSConsumptionAPIService {


	public FGToURSResponseVo consumeFGApi(String se10) throws JsonProcessingException, UnsupportedEncodingException;
	
	public CLToURSResponseVo postSpreadingStatusToCL(String se10);
	
	public String postRejectionJson(String json) throws RestClientException, URISyntaxException;
	
}
