package com.salesforce.domain;

/**
 * 
 * @author MetadataLogDO Filling MetadataLog__c Object Data
 *
 */
public class TestMetadataLogDO {

	private String id;
	private String name;
	private String status;
	private String message;
	private String testinformation;
	private Double totalTests;
	private Double totalSuccess;
	private Double totalFailures;
	private Double totalTimes;

	public TestMetadataLogDO() {
		// TODO Auto-generated constructor stub
	}

	public TestMetadataLogDO(String id, String name, String status,
			String message, String testinformation, Double totalTests,
			Double totalSuccess, Double totalFailures ,Double totalTimes) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.message = message;
		this.testinformation = testinformation;
		this.totalTests = totalTests;
		this.totalSuccess = totalSuccess;
		this.totalFailures = totalFailures;
		this.totalTimes=this.totalTimes;
	}

	public String getTestinformation() {
		return testinformation;
	}

	public void setTestinformation(String testinformation) {
		this.testinformation = testinformation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Double getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(Double totalTests) {
		this.totalTests = totalTests;
	}

	public Double getTotalSuccess() {
		return totalSuccess;
	}

	public void setTotalSuccess(Double totalSuccess) {
		this.totalSuccess = totalSuccess;
	}

	public Double getTotalFailures() {
		return totalFailures;
	}

	public void setTotalFailures(Double totalFailures) {
		this.totalFailures = totalFailures;
	}

	public Double getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(Double totalTimes) {
		this.totalTimes = totalTimes;
	}

	


}