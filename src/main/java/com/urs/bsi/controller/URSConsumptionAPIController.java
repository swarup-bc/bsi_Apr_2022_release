package com.urs.bsi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.urs.bsi.model.ErrorModel;
import com.urs.bsi.model.vo.FGToURSResponseVo;
import com.urs.bsi.model.vo.RejectionResponseVo;
import com.urs.bsi.service.URSConsumptionAPIService;

import io.swagger.annotations.ApiOperation;

@Controller
public class URSConsumptionAPIController {
	
	@Autowired
	URSConsumptionAPIService ursConsumptionAPIService;
	
	private static Logger logger = LoggerFactory.getLogger(URSConsumptionAPIController.class);

	
	@ApiOperation(value="",notes="")
	@RequestMapping(method=RequestMethod.POST,value="/submit-spreading-details-to-financial-gateway",produces="application/json")
	public ResponseEntity<FGToURSResponseVo>  consumeFGPostAPI(@RequestParam(value="caseId",required=true) String caseId) throws IOException {

		FGToURSResponseVo fgResponseModel=ursConsumptionAPIService.consumeFGApi(caseId);
	    	if(null==fgResponseModel){
	    		logger.error("Error calling TPDG API. - caseId - "+caseId);
	    		FGToURSResponseVo fgErrorVo = new FGToURSResponseVo();
	    		fgErrorVo.setResponse_code("500");
	    		fgErrorVo.setResponse_message("Error calling TPDG API.");
	    		return new ResponseEntity<FGToURSResponseVo>(fgErrorVo,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}else
	    		return new ResponseEntity<FGToURSResponseVo>(fgResponseModel,HttpStatus.OK);
	}

	
	@ApiOperation(value="",notes="")
	@RequestMapping(method=RequestMethod.POST,value="/post-document-rejection-eig",produces="application/json")
	public ResponseEntity<RejectionResponseVo>  postRejection(@RequestBody String json) throws IOException {
		try {
			String res = ursConsumptionAPIService.postRejectionJson(json); 
			RejectionResponseVo rv = new RejectionResponseVo();
			String[] response = res.split("~");
			rv.setCode(response[0]);
			rv.setMessage(response[1]);
			if("200".equals(response[0])){
				logger.info("post-document-rejection-eig - Response 200 - "+rv.getMessage());
				return new ResponseEntity<RejectionResponseVo>(rv,HttpStatus.OK);
	    	}
			if("500".equals(response[0])) {
				logger.error("post-document-rejection-eig - Response 500 - "+rv.getMessage());
	    		ErrorModel err=new ErrorModel(500,"Error in postRejection");
	    		return new ResponseEntity(err,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}else {
	    		logger.error("post-document-rejection-eig - Other error - "+rv.getMessage());
	    		ErrorModel err=new ErrorModel(Integer.parseInt(rv.getCode()),rv.getMessage());
	    		return new ResponseEntity(err,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    		
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("post-document-rejection-eig - Exception - "+e.getMessage());
		}
		
		return null;
	}
}
