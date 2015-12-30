package com.salesforce.template;

import java.util.List;

import org.junit.runner.Result;

import com.salesforce.domain.STestCaseDObj;
import com.salesforce.domain.TestInfoResponse;

public class FirstCustProcessTemplate extends TestProcessingTemplate{
	
	public FirstCustProcessTemplate(TestInfoResponse testInfoResponse){
		this.testInfoResponse = testInfoResponse;
	}
	
	@Override
	public Result doProcessing() {
		// TODO Auto-generated method stub
		return doProcessing1();
	}
	
}
