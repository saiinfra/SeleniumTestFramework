package com.salesforce.template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.salesforce.domain.ResultInformationDO;
import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestMetadataLogDO;
import com.salesforce.domain.TestResponse;
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.ds.TestMetadataLogDAO;
import com.salesforce.ds.TestScriptsDAO;
import com.salesforce.ds.TestScriptsResultsDAO;
import com.salesforce.factory.Factory;
import com.salesforce.util.Constants;
import com.salesforce.util.SalesForceUtil;

public abstract class TestPostProcessingTemplate {
	Result result = null;
	TestInfoResponse testInfoResponse;
	String metadatLogId;
	ResultInformationDO junitOutput;
	private static ArrayList<Integer> failureList = new ArrayList<>();
	private static ArrayList<Integer> noofTestList = new ArrayList<>();
	private static ArrayList<Double> totalTimeList = new ArrayList<>();
	
	public abstract void doPostProcessing(TestResponse tResponse);

	public void doPostProcessing1(TestResponse tResponse) {
		// TODO Auto-generated method stub
		String mappingClassName = null;
		if(this.testInfoResponse == null){
			mappingClassName = null; 
		}
		else{
			mappingClassName = this.testInfoResponse.getMappingClassName();
		}
		junitOutput = resultProcessing(mappingClassName, result, tResponse);
		updateTestScriptExecutionResults();
		computeResults();
	}

	private void updateForEmptyTestCases() {

	}

	private String updateTestScriptExecutionResults() {
		TestScriptsResultsDAO testScriptsResultsDAO = (TestScriptsResultsDAO) Factory
				.getObjectInstance("TestScriptsResultsDAO");
		TestScriptsDAO testScriptsDAO = new TestScriptsDAO();
		if (getTestInfoResponse() != null) {
			List<Object> testscript = testScriptsDAO.findByScriptName(getTestInfoResponse().getTestScriptId(),
					SalesForceUtil.getSFHandle());
			for (Iterator<Object> iterator = testscript.iterator(); iterator.hasNext();) {
				TestScriptsDO object = (TestScriptsDO) iterator.next();
				String testScriptId = object.getId();
				testScriptsResultsDAO.insert(junitOutput, SalesForceUtil.getSFHandle(), testScriptId,
						getTestInfoResponse().getSfTestInfoParentId(), metadatLogId);
			}
		}
		return metadatLogId;
	}

	private ResultInformationDO resultProcessing(String testcasename, Result result, TestResponse tResponse) {
		double time = 0.0;
		int numberOfTest = 0;
		int numberOfTestFail = 0;
		int numberOfTestIgnore = 0;

		ResultInformationDO resultInformationDO = new ResultInformationDO();
		if (result == null) {
			failureList.add(new Integer(numberOfTestFail));
			noofTestList.add(new Integer(numberOfTest));
			totalTimeList.add(Double.valueOf(time / 1000.0D));
			if(getTestInfoResponse().getStatus().equals(Constants.Modified)){
				resultInformationDO.setType("This test case cannot be executed since status is modified");
			}
			if(testcasename == null){
				resultInformationDO.setType("There are no test case case to be executed");
			}
			else if( (testcasename != null) && testcasename.isEmpty()){
				resultInformationDO.setType("Empty Test cases cannot be executed");
			}
			
		} else {
			time = result.getRunTime();
			numberOfTest = result.getRunCount();
			numberOfTestFail = result.getFailureCount();
			numberOfTestIgnore = result.getIgnoreCount();

			if(!tResponse.isMappingFileExist()){
				resultInformationDO.setType("Automate java test scripts are created. please update to execute.");
			}
			if (numberOfTestFail > 0) {

				for (Failure failure : result.getFailures()) {
					resultInformationDO.setType(failure.toString());
					System.out.println(failure.toString());
				}
				resultInformationDO.setStatus("failure");
				resultInformationDO.setTestcasename(testcasename);
				resultInformationDO.setTime(Double.valueOf(time / 1000.0D));
			} else {

				resultInformationDO.setStatus("success");
				resultInformationDO.setTestcasename(testcasename);
				resultInformationDO.setTime(Double.valueOf(time / 1000.0D));

			}
			failureList.add(new Integer(numberOfTestFail));
			noofTestList.add(new Integer(numberOfTest));
			totalTimeList.add(Double.valueOf(time / 1000.0D));
		}
		return resultInformationDO;
	}

	public void computeResults() {
		// Total Analysis
		double totalFailures = 0.0;
		double noOfTests = 0.0;
		double totalExecTime = 0.0;
		double ToatlSucess = 0.0;
		for (Iterator<Integer> iterator = failureList.iterator(); iterator.hasNext();) {
			double fail = ((Integer) iterator.next()).doubleValue();
			Double d = new Double(fail);
			totalFailures = totalFailures + d;

		}
		// total tests

		for (Iterator<Integer> iterator = noofTestList.iterator(); iterator.hasNext();) {
			double testCount = ((Integer) iterator.next()).doubleValue();
			noOfTests = noOfTests + testCount;

		}

		// total time
		for (Iterator<Double> iterator = totalTimeList.iterator(); iterator.hasNext();) {
			Double time = (Double) iterator.next();
			totalExecTime = totalExecTime + time;

		}
		System.out.println("No of Test Fails :" + totalFailures);
		System.out.println("No of Test  :" + noOfTests);
		ToatlSucess = noOfTests - totalFailures;
		System.out.println("No of Test success :" + ToatlSucess);
		// update into MetadataLog Summary
		TestMetadataLogDO testMetadataLogDO = SFDomainUtil.createTestMetadataLog(metadatLogId, totalFailures, noOfTests,
				ToatlSucess, totalExecTime, getTestInfoResponse().getSfTestInfoParentId());
		TestMetadataLogDAO testMetadataLogDAO = (TestMetadataLogDAO) Factory.getObjectInstance("TestMetadataLogDAO");
		testMetadataLogDAO.update(testMetadataLogDO, SalesForceUtil.getSFHandle(),
				getTestInfoResponse().getSfTestInfoParentId());
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public TestInfoResponse getTestInfoResponse() {
		return testInfoResponse;
	}

	public void setTestInfoResponse(TestInfoResponse testInfoResponse) {
		this.testInfoResponse = testInfoResponse;
	}

	public String getMetadatLogId() {
		return metadatLogId;
	}

	public void setMetadatLogId(String metadatLogId) {
		this.metadatLogId = metadatLogId;
	}

}
