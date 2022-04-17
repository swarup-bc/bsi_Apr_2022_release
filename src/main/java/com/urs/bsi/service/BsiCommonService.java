package com.urs.bsi.service;

import com.urs.bsi.model.SpreadingRequestData;
import com.urs.bsi.model.SpreadingResponseData;
import com.urs.bsi.model.vo.spreadingreq.SpreadingRequestVo;
import com.urs.bsi.model.vo.spreadingreq.SpreadingResponseVo;

public interface BsiCommonService {

	
	public SpreadingRequestData persistSpreadingRequestData(SpreadingRequestVo spreadingRequestVo, 
			String clientAppId, String requestTimestamp, String correlationId) throws Exception;

	SpreadingResponseData persistSpreadingResponse(SpreadingResponseVo spreadingResponseVo, SpreadingRequestData spreadingReqData, SpreadingRequestVo spreadingRequestVo);

}