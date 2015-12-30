package com.salesforce.template;

import java.util.List;

import com.salesforce.domain.TestInfoResponse;

public class FirstCustPreProcessTemplate extends TestPreProcessingTemplate{

	@Override
	public List<TestInfoResponse> doPreProcessing(String inputTokens) {
		return doPreProcessing1(inputTokens);
	}
}
