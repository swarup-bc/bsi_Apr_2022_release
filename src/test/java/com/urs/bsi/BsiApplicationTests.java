package com.urs.bsi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.urs.bsi.model.SpreadingDocumentInformation;
import com.urs.bsi.model.SpreadingRequestData;
import com.urs.bsi.repository.SpreadingDocumentInformationRepository;
import com.urs.bsi.repository.SpreadingRequestDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BsiApplicationTests {
	
	
	@Autowired
	private SpreadingRequestDataRepository spreadingRequestDataRepository;

	@Autowired
	private SpreadingDocumentInformationRepository spreadingDocumentInformationRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	public void contextLoads() {
		
		SpreadingRequestData data = new SpreadingRequestData();
		data.setClient_app_id("amreshs");
		data.setCorrelation_id("xbbl98l_01");
		//data.setSe10("SE1234567");
		SpreadingDocumentInformation spd = new SpreadingDocumentInformation();
		spd.setComments("comments from amresh");
		spd.setDocument_id("doc123");
		spreadingRequestDataRepository.save(data);
		spd.setRefSrdId(data);
		spreadingDocumentInformationRepository.save(spd);
	}

}
