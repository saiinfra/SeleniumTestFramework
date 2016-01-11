package com.salesforce.template;

import java.util.List;

import org.junit.runner.Result;

import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestResponse;

public class FirstCustPostTemplate extends TestPostProcessingTemplate {

	public FirstCustPostTemplate(TestInfoResponse testInfoResponse, Result result, String metadatLogId) {
		this.result = result;
		this.testInfoResponse = testInfoResponse;
		this.metadatLogId = metadatLogId;
	}

	@Override
	public void doPostProcessing(TestResponse tResponse) {
		// TODO Auto-generated method stub
		doPostProcessing1(tResponse);
	}

}
