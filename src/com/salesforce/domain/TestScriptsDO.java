package com.salesforce.domain;

public class TestScriptsDO {

	String id;
	String testScritName;
	String actualResult;
	String expectedResult;
	String Step;
	String testData;
	String testInformation;
	String tests;
	String testSteps;
	String times;

	public TestScriptsDO(String id, String testScritName, String actualResult,
			String expectedResult, String Step, String testData,
			String testInformation, String tests, String testSteps, String times) {

		this.id = id;
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

	public void setTimes(String times) {
		this.times = times;
	}

}
