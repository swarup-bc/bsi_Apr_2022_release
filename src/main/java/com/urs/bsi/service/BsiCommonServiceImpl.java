package com.urs.bsi.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urs.bsi.model.SpreadingDocumentInformation;
import com.urs.bsi.model.SpreadingRequestData;
import com.urs.bsi.model.SpreadingResponseData;
import com.urs.bsi.model.SpreadingResponseFailure;
import com.urs.bsi.model.vo.spreadingreq.DocumentInformation;
import com.urs.bsi.model.vo.spreadingreq.SpreadingRequestVo;
import com.urs.bsi.model.vo.spreadingreq.SpreadingResponseVo;
import com.urs.bsi.repository.SpreadingDocumentInformationRepository;
import com.urs.bsi.repository.SpreadingRequestDataRepository;
import com.urs.bsi.repository.SpreadingResponseDataRepository;
import com.urs.bsi.repository.SpreadingResponseFailureRepository;
import com.urs.bsi.util.BsiFileUtility;

@Service
public class BsiCommonServiceImpl implements BsiCommonService {
	
	private static Logger logger = LoggerFactory.getLogger(BsiCommonServiceImpl.class);
	
	@Autowired
	private SpreadingRequestDataRepository spreadingRequestDataRepository;

	@Autowired
	private SpreadingDocumentInformationRepository spreadingDocumentInformationRepository;
	
	
	@Autowired
	private SpreadingResponseDataRepository spreadingResponseDataRepository;
	
	@Autowired
	private SpreadingResponseFailureRepository spreadingResponseFailureRepository;
	

	
	
	@Transactional
	public SpreadingRequestData persistSpreadingRequestData(SpreadingRequestVo spreadingRequestVo, 
			String clientAppId, String requestTimestamp, String correlationId) throws Exception {
		
		SpreadingRequestData spreadingRequestData = new SpreadingRequestData();
		spreadingRequestData.setGeneral_comments(spreadingRequestVo.getSpreading_request_data().getGeneral_comments());
		spreadingRequestData.setRespreading_comment(spreadingRequestVo.getRespreading_information().getRespreading_comment());
		spreadingRequestData.setRespreading_indicator(spreadingRequestVo.getRespreading_information().getRespreading_indicator());
		
		spreadingRequestData.setClient_app_id(clientAppId);
		spreadingRequestData.setRequest_timestamp(requestTimestamp);
		spreadingRequestData.setCorrelation_id(correlationId);
		try {
			
			// copy record_identifier_information
			BeanUtils.copyProperties(spreadingRequestData, spreadingRequestVo.getRecord_identifier_information());
			
			// copy company_info
			BeanUtils.copyProperties(spreadingRequestData, spreadingRequestVo.getCompany_info());
			spreadingRequestData.setAccount_number_company(spreadingRequestVo.getCompany_info().getAccount_number());
			spreadingRequestData.setAddress_line1_company(spreadingRequestVo.getCompany_info().getAddress_line1());
			spreadingRequestData.setState_company(spreadingRequestVo.getCompany_info().getState());
			spreadingRequestData.setCity_company(spreadingRequestVo.getCompany_info().getCity());
			spreadingRequestData.setCountry_company(spreadingRequestVo.getCompany_info().getCountry());
			
			//copy customer_info
			if(null!=spreadingRequestVo.getCustomer_info()) {
				BeanUtils.copyProperties(spreadingRequestData, spreadingRequestVo.getCustomer_info());
				spreadingRequestData.setState_customer(spreadingRequestVo.getCompany_info().getState());
				spreadingRequestData.setCity_customer(spreadingRequestVo.getCompany_info().getCity());
				spreadingRequestData.setCity_customer(spreadingRequestVo.getCompany_info().getCountry());
			}
			// copy app_info
			BeanUtils.copyProperties(spreadingRequestData, spreadingRequestVo.getApp_info());
			spreadingRequestData.setProspect_id(spreadingRequestVo.getApp_info().getProspect_id());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			logger.error("Exception in copying property: ", e1);
			throw e1;
		}
		try {
			spreadingRequestData.setCreated_datetime(BsiFileUtility.getCurrentTimeMMDDYYYYHHMMSS());
			spreadingRequestDataRepository.save(spreadingRequestData);
			
			List<SpreadingDocumentInformation> spreadingDocInfoList = new ArrayList<SpreadingDocumentInformation>();
			List<DocumentInformation> documentInfoVos = spreadingRequestVo.getDocument_information();
			documentInfoVos.forEach(itm -> {
				SpreadingDocumentInformation spreadingDocInfo = new SpreadingDocumentInformation();
				try {
					BeanUtils.copyProperties(spreadingDocInfo, itm);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				spreadingDocInfo.setRefSrdId(spreadingRequestData);
				spreadingDocInfoList.add(spreadingDocInfo);
			});
			spreadingDocumentInformationRepository.saveAll(spreadingDocInfoList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in persisting spreading request data: ", e);
			throw e;
		}
		return spreadingRequestData;
	}
	
	@Override
	@Transactional
	public SpreadingResponseData persistSpreadingResponse(SpreadingResponseVo spreadingResponseVo, 
			SpreadingRequestData spreadingRequestData, SpreadingRequestVo spreadingRequestVo) {
		
		SpreadingResponseData responseData = new SpreadingResponseData();
		responseData.setRefSrdId(spreadingRequestData);
		try {
			BeanUtils.copyProperties(responseData, spreadingResponseVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		spreadingResponseDataRepository.save(responseData);
		
		if (!spreadingResponseVo.getResponse_code().equals("200")) {
			ObjectMapper mapper = new ObjectMapper();
			SpreadingResponseFailure responseFailure = new SpreadingResponseFailure();
			responseFailure.setRefSprId(responseData);
			try {
				String jsonString = mapper.writeValueAsString(spreadingRequestVo);
				responseFailure.setInput_json(jsonString);
			} catch (IOException e) {
				e.printStackTrace();
			}
			spreadingResponseFailureRepository.save(responseFailure);
		}
		
		return responseData;
	}

}
