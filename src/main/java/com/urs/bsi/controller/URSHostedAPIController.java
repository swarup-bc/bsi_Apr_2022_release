package com.urs.bsi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urs.bsi.model.SpreadingRequestData;
import com.urs.bsi.model.SpreadingResponseData;
import com.urs.bsi.model.URSIoJsonDetails;
import com.urs.bsi.model.vo.spreadingreq.DocumentInformation;
import com.urs.bsi.model.vo.spreadingreq.SpreadingRequestVo;
import com.urs.bsi.model.vo.spreadingreq.SpreadingResponseVo;
import com.urs.bsi.repository.URSIoJsonDetailsRepository;
import com.urs.bsi.service.BsiCommonService;
import com.urs.bsi.util.BsiFileUtility;
import com.urs.bsi.util.SendEmail;

@Controller
public class URSHostedAPIController {
	
	@Autowired
	private BsiCommonService bsiCommonService;
	
	private static Logger logger = LoggerFactory.getLogger(URSHostedAPIController.class.getName());
	
	@Value("${environment}")
	private String environment;
	
	@RequestMapping(method = RequestMethod.POST, value = "/bank-statement-spreading-request-eig", produces = "application/json")
	public ResponseEntity<SpreadingResponseVo> spreadingRequestDataEIG(
			@RequestBody(required=true) SpreadingRequestVo spreadingRequestVo,
			@RequestHeader("client_app_id") String clientAppId,
			@RequestHeader("request_timestamp") String requestTimestamp,
			@RequestHeader("correlation_id") String correlationId) throws IOException {
		logger.info("spreading-request-data-EIG post API started :: ");
	    ResponseEntity<SpreadingResponseVo> responseEntity = null;
	    SpreadingResponseVo responseVo = null;
	    SpreadingRequestData spreadingReqData = null;
	    try {
	    	
	    	ObjectMapper om=new ObjectMapper();	
			String requestJson = om.writeValueAsString(spreadingRequestVo);
			logger.info("Incoming request from EIG for correlation_id-"+ correlationId + "--"+requestJson);
			
	    	if(isRequiredFieldMissing(spreadingRequestVo, clientAppId, requestTimestamp, correlationId)) {
	    		logger.info("Required field missing-"+correlationId);
	    		responseVo = createResponse(clientAppId, correlationId,spreadingRequestVo.getApp_info().getProcess_control_number(),"005");
				responseEntity = new ResponseEntity<SpreadingResponseVo>(responseVo, HttpStatus.INTERNAL_SERVER_ERROR);
	    	}else if(!isDateValid(spreadingRequestVo.getRecord_identifier_information().getRequest_date(),"MM/dd/yyyy") 
	    			|| !areAllDocumentTypeBKSTA(spreadingRequestVo.getDocument_information())) {
	    		logger.info("Request date invalid-"+correlationId);
	    		responseVo = createResponse(clientAppId, correlationId,spreadingRequestVo.getApp_info().getProcess_control_number(),"006");
				responseEntity = new ResponseEntity<SpreadingResponseVo>(responseVo, HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	else {
	    		spreadingReqData = bsiCommonService.persistSpreadingRequestData(spreadingRequestVo, clientAppId, requestTimestamp, correlationId);
	    		responseVo = createResponse(clientAppId, correlationId,spreadingReqData.getProcess_control_number(), "000");
	    		responseEntity = new ResponseEntity<SpreadingResponseVo>(responseVo, HttpStatus.OK);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in spreading-request-data-EIG", e);
			responseVo = createResponse(clientAppId, correlationId,spreadingReqData.getProcess_control_number(),"100");
			responseEntity = new ResponseEntity<SpreadingResponseVo>(responseVo, HttpStatus.INTERNAL_SERVER_ERROR);
			try {
	    		StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
	    		SendEmail.sendmail(environment+" : EIG Upstream MAIN API 1000 Error - "+correlationId, 
	    			"AxpSupport.BankSpreading@blucognition.com", exceptionAsString);
		    }catch(Exception ex) {
		    	ex.printStackTrace();
		    	logger.error("error - ",e);
		    }
		}
	    
	    try {
	    	SpreadingResponseData spreadingResponseData =  bsiCommonService.persistSpreadingResponse(responseVo, spreadingReqData,spreadingRequestVo);
			ObjectMapper om=new ObjectMapper();	
			String reqEntity = om.writeValueAsString(spreadingRequestVo);
			om=new ObjectMapper();
			String resEntity = om.writeValueAsString(responseVo);
			saveFgJsonToTbl(correlationId,reqEntity,resEntity,responseVo.getResponse_code());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	    if(!"TEST".equalsIgnoreCase(environment)) {
		    try {
		    		SendEmail.sendmail("AMEX XTRACT ??? "+environment+" - NEW CASE ARRIVED ??? PCN #"+spreadingRequestVo.getApp_info().getProcess_control_number()+" , RESPONSE CODE -"+responseVo.getResponse_code(), 
		    			"AxpSupport.BankSpreading@blucognition.com", "Bank Statements Spreading Request Received.");
		    		logger.info("email sent PCN #"+spreadingRequestVo.getApp_info().getProcess_control_number()+" , RESPONSE CODE -"+responseVo.getResponse_code());
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	logger.error("error - ",e);
		    }
	    }
	    return responseEntity;
	}
	
	private boolean areAllDocumentTypeBKSTA(List<DocumentInformation> diList) {
		for(DocumentInformation  di : diList) {
			if(!"BKSTA".equalsIgnoreCase(di.getDocument_type())) {
				return false;
			}
		}
		return true;
	}

	private boolean isDateValid(String date,String dateFromat) {
		if(date == null)
			return false;
		  
		  if(dateFromat == null)
			  return false;
		  
		  SimpleDateFormat format = new SimpleDateFormat(dateFromat);
		  format.setLenient(false);
		  
		  try {
			  format.parse(date.trim());
		  } catch (ParseException pe) {
			  pe.printStackTrace();
			  return false;
		  }
		  
		return true;
	}
	
	private boolean isRequiredFieldMissing(SpreadingRequestVo spreadingRequestVo, String clientAppId,
			String requestTimestamp, String correlationId) {
		if(null==spreadingRequestVo.getDocument_information() || spreadingRequestVo.getDocument_information().size()==0) return true;
		
		for(DocumentInformation  di : spreadingRequestVo.getDocument_information()) {
			if(!StringUtils.hasText(di.getDocument_id()) || !StringUtils.hasText(di.getDocument_resource_id())
					|| !StringUtils.hasText(di.getDocument_type()) || !StringUtils.hasText(di.getDocument_url())
					|| !StringUtils.hasText(di.getFiles_resource_id()) 	) {
				return true;
			}
		}
		
		if(!"US".equalsIgnoreCase(spreadingRequestVo.getApp_info().getMarket_code())) { 
				if ("GCP".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) 
				&& ("PFG".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) 
						|| "RLI".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id())
						|| "NA".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id())
						)) {
					//contine;					
				}
				else
					return true;
		}
			
		if("US".equalsIgnoreCase(spreadingRequestVo.getApp_info().getMarket_code())){ 
				if ("NA".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) 
				&& ("GCP".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) || "OPEN".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()))) {
					//contine;
				}
				else	
					return true;
		}
		
//		if("US".equalsIgnoreCase(spreadingRequestVo.getApp_info().getMarket_code())){ 
//			if ("NA".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) 
//			&& ("GCP".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) || "OPEN".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()))
//			&& (!StringUtils.hasText(spreadingRequestVo.getCompany_info().getAddress_line1())
//					|| !StringUtils.hasText(spreadingRequestVo.getCompany_info().getCity())
//					|| !StringUtils.hasText(spreadingRequestVo.getCompany_info().getState() )
//					)) 				
//				return true;
//		}
				
		
		boolean isMissing =  !StringUtils.hasText(clientAppId) || !StringUtils.hasText(requestTimestamp) || !StringUtils.hasText(correlationId)
			|| !StringUtils.hasText(spreadingRequestVo.getRespreading_information().getRespreading_indicator())
			|| !StringUtils.hasText(spreadingRequestVo.getRecord_identifier_information().getRequest_timestamp())
			|| !StringUtils.hasText(spreadingRequestVo.getRecord_identifier_information().getRequest_date())
			|| (!StringUtils.hasText(spreadingRequestVo.getRecord_identifier_information().getOwning_system_name())
					|| !"EIG".equalsIgnoreCase(spreadingRequestVo.getRecord_identifier_information().getOwning_system_name()))
			|| (!StringUtils.hasText(spreadingRequestVo.getRecord_identifier_information().getClient_platform()) 
					|| !"EIG".equalsIgnoreCase(spreadingRequestVo.getRecord_identifier_information().getClient_platform()))
			|| !StringUtils.hasText(spreadingRequestVo.getCompany_info().getCountry() )
//			|| ("OPEN".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) 
//					&& ("PFG".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) || "RLI".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) )
//					&& !StringUtils.hasText(spreadingRequestVo.getCompany_info().getAddress_line1()  ))
//			|| ("OPEN".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) 
//					&& ("PFG".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) || "RLI".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) )
//					&& !StringUtils.hasText(spreadingRequestVo.getCompany_info().getState() ))
			|| !StringUtils.hasText(spreadingRequestVo.getCompany_info().getCompany_name() )
			|| !StringUtils.hasText(spreadingRequestVo.getCompany_info().getCount_of_documents() )
			|| ("OPEN".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) 
					&& ("PFG".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) || "RLI".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) )
					&& !StringUtils.hasText(spreadingRequestVo.getCompany_info().getAccount_number() ))
			|| ("GCP".equalsIgnoreCase(spreadingRequestVo.getApp_info().getRequesting_bu()) 
					&& ("PFG".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) || "RLI".equalsIgnoreCase(spreadingRequestVo.getApp_info().getUsecase_id()) )
					&& !StringUtils.hasText(spreadingRequestVo.getCompany_info().getCorporate_id() ))
			|| !StringUtils.hasText(spreadingRequestVo.getApp_info().getChannel_indicator() )
			|| !StringUtils.hasText(spreadingRequestVo.getApp_info().getRequesting_bu())
			|| !StringUtils.hasText(spreadingRequestVo.getApp_info().getUsecase_id() )
			|| !StringUtils.hasText(spreadingRequestVo.getApp_info().getMarket_code() )
			|| !StringUtils.hasText(spreadingRequestVo.getApp_info().getProcess_control_number() );
			return isMissing;
	}
		
	private SpreadingResponseVo createResponse(String clientAppId, String correlationId, String pcn, String responseCode) {
		SpreadingResponseVo response = new SpreadingResponseVo();
		response.setProcess_control_number(pcn);
		response.setClient_app_id(clientAppId);
		response.setResponse_id(correlationId);
		response.setResponse_timestamp(new SimpleDateFormat("dd-MM-yy hh:mm:ss").format(new Date()));
		response.setResponse_code(responseCode);
		return response;
	}
	
	@Autowired
	private URSIoJsonDetailsRepository ursIoJsonDetailsRepository;
	
	private void saveFgJsonToTbl(String corrlnId, String request, String response,String resCode) {
		URSIoJsonDetails usrIoJsonDetails=new URSIoJsonDetails();
		usrIoJsonDetails.setCorrelationId(corrlnId);
		usrIoJsonDetails.setFromEntity("EIG");
		usrIoJsonDetails.setToEntity("URS");
		usrIoJsonDetails.setRequestJson(request);
		usrIoJsonDetails.setResponseJson(response);
		usrIoJsonDetails.setResponseCode(resCode);
		usrIoJsonDetails.setCreatedDate(BsiFileUtility.getCurrentTimeMMDDYYYYHHMMSS());
		logger.info("Inserting data in JSON tbl");
		ursIoJsonDetailsRepository.save(usrIoJsonDetails);
		logger.info("Data inserted successfully JSON tbl");
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/upload-bank-statements", produces = "application/json")
//	public ResponseEntity<String> mapCustomerExcelDatatoDB(@RequestParam("file") MultipartFile[] bankStmts) throws IOException {
//		logger.info("File upload API 'upload-bank-statements' started for file:: "+bankStmts.length);
//	    String retVal = "successfully uploaded # of file(s): "+bankStmts.length;
//	    ResponseEntity<String> responseEntity = null;
//	    try {
////	    	bsiFileUploadService.saveCustomerSpreadingDataToDB(reapExcelDataFile);
//	    	responseEntity = new ResponseEntity<String>(retVal, HttpStatus.OK);
//	    	logger.info("File upload API 'upload-bank-statements' completed for # of file(s):: "+bankStmts.length);
//		} catch (Exception e) {
//			logger.error("Error in file upload process", e);
//			responseEntity = new ResponseEntity<String>("Exception in uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	    return responseEntity;
//	}
	
}
