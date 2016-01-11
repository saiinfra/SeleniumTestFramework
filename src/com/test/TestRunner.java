package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.runner.Result;

import com.salesforce.domain.ResultInformationDO;
import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestMetadataLogDO;
import com.salesforce.domain.TestResponse;
import com.salesforce.template.FirstCustPostTemplate;
import com.salesforce.template.FirstCustPreProcessTemplate;
import com.salesforce.template.FirstCustProcessTemplate;
import com.salesforce.util.AppUtil;
import com.salesforce.util.SFoAuthHandle;

public class TestRunner {

	private static ResultInformationDO resultInformationDO = null;
	private static TestMetadataLogDO metadataLogDO = null;
	private static String testsuitename;
	private static String testInformationId;
	private static String testcase;
	private static String orgId;
	private static String testInfoId;
	private static String testInfoName;

	private static ArrayList<Integer> failureList = new ArrayList<>();
	private static ArrayList<Integer> noofTestList = new ArrayList<>();
	private static ArrayList<Double> totalTimeList = new ArrayList<>();

	private static double sum1 = 0.0;
	private static double sum2 = 0.0;
	private static double sum3 = 0.0;

	private static SFoAuthHandle sfHandle = null;

	public TestRunner() {
		System.out.println("path: " + AppUtil.getCurrentPath());
	}

	public static void main(String[] args) {
		//String arg = args[0];
		// testId~OrgId~TestInfoName
		// String arg = "a0361000005ZnOy~00D61000000fBw41~T-0000000001";
		// String arg = "a0361000005aMqp~00D61000000fBw43~T_0000000007";
		//String arg = "a0361000005aRN6AAM~00D61000000fBw43~T_0000000018";
		//String arg = "a0361000005aWPoAAM~00D61000000fBw4~T_0000000027";
		//String arg = "a0361000005aWH3AAM~00D61000000fBw4~T_0000000027";
		String arg = "a0361000005aYgnAAE~00D61000000fBw4~T_0000000029";
		
		if (arg == null) { 
			arg = "test"; 
		}
		 
		init(arg);
	}

	private static void init(String inputTokens) {
		TestResponse tResponse = new TestResponse();
		// pre-process
		FirstCustPreProcessTemplate preProcessTemplate = new FirstCustPreProcessTemplate();
		List<TestInfoResponse> testInfoResponselist = preProcessTemplate.doPreProcessing(inputTokens, tResponse);

		if ((testInfoResponselist == null)) {
			// post processing
			FirstCustPostTemplate firstCustPostTemplate = new FirstCustPostTemplate(null, null, null);
			firstCustPostTemplate.doPostProcessing(tResponse);
		} else {

			String metadatLogId = SFDomainUtil.createEmptyMetadataLogId();
			if (tResponse.isMappingFileExist()) {
				for (Iterator iterator = testInfoResponselist.iterator(); iterator.hasNext();) {
					TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
					// processing
					FirstCustProcessTemplate processTemplate = new FirstCustProcessTemplate(testInfoResponse);
					Result result = processTemplate.doProcessing();

					// post processing
					FirstCustPostTemplate firstCustPostTemplate = new FirstCustPostTemplate(testInfoResponse, result,
							metadatLogId);
					firstCustPostTemplate.doPostProcessing(tResponse);
				}
			}
			else{
				for (Iterator iterator = testInfoResponselist.iterator(); iterator.hasNext();) {
					TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
					// processing
					FirstCustProcessTemplate postProcessTemplate = new FirstCustProcessTemplate(testInfoResponse);
					Result result = postProcessTemplate.doProcessing();

					// post processing
					FirstCustPostTemplate firstCustPostTemplate = new FirstCustPostTemplate(testInfoResponse, result,
							metadatLogId);
					firstCustPostTemplate.doPostProcessing(tResponse);
				}
				
			}
		}
	}

}
