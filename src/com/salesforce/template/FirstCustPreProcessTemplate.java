package com.salesforce.template;

import java.util.List;

import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestResponse;

public class FirstCustPreProcessTemplate extends TestPreProcessingTemplate{

	@Override
	public List<TestInfoResponse> doPreProcessing(String inputTokens, TestResponse tResponse) {
		return doPreProcessing1(inputTokens, tResponse);
	}
}
