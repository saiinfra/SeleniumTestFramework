package com.salesforce.domain;

public class TestScriptsDO {

	String id;
	String testScritId;
	String testScritName;
	String actualResult;
	String expectedResult;
	String Step;
	String testData;
	String testInformation;
	String tests;
	String testSteps;
	String times;
	String status;
	String sciptStatus;
	String testScriptType;
	String loginType;

	public TestScriptsDO(String id, String testScritName, String actualResult,
			String expectedResult, String Step, String testData,
			String testInformation, String tests, String testSteps, String times) {

		this.id = id;
		this.testScritId = testScritName;
		this.testScritName = testScritName;
		this.actualResult = actualResult;
		this.expectedResult = expectedResult;
		this.expectedResult = expectedResult;
		this.Step = Step;
		this.testData = testData;
		this.testInformation = testInformation;
		this.tests = tests;
		this.testSteps = testSteps;
		this.times = times;

	}

	public TestScriptsDO(String id, String testScritName) {
		this.id = id;
		this.testScritName = testScritName;
		this.testScritId = testScritName;
	}

	public TestScriptsDO(String id, String testScritName, String testSteps,
			String sciptStatus, String testScriptType, String loginType) {
		this.id = id;
		this.testScritName = testScritName;
		this.testScritId = testScritName;
		this.testSteps = testSteps;
		this.sciptStatus = sciptStatus;
		this.testScriptType = testScriptType;
		this.loginType = loginType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestScritName() {
		return testScritName;
	}

	public void setTestScritName(String testScritName) {
		this.testScritName = testScritName;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getStep() {
		return Step;
	}

	public void setStep(String step) {
		Step = step;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}

	public String getTestInformation() {
		return testInformation;
	}

	public void setTestInformation(String testInformation) {
		this.testInformation = testInformation;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(String testSteps) {
		this.testSteps = testSteps;
	}

	public String getTimes() {
		return times;
	}

	public String getTestScritId() {
		return testScritId;
	}

	public void setTestScritId(String testScritId) {
		this.testScritId = testScritId;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSciptStatus() {
		return sciptStatus;
	}

	public void setSciptStatus(String sciptStatus) {
		this.sciptStatus = sciptStatus;
	}

	public String getTestScriptType() {
		return testScriptType;
	}

	public void setTestScriptType(String testScriptType) {
		this.testScriptType = testScriptType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	
	
}
