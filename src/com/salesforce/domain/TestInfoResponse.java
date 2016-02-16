package com.salesforce.domain;

import org.junit.runner.Result;

public class TestInfoResponse {

	String sfTestInfoParentId;
	String sfTestInfoScriptRecordId;
	String application;
	String module;
	String title;
	String testScriptId;
	String testScriptName;
	String status;
	String path;
	String mappingClassName;
	String testData;
	Result result;
	boolean excelRecordExists=false;

	public TestInfoResponse() {
		super();
	}

	public TestInfoResponse(String sfTestInfoParentId, String sfTestInfoScriptRecordId, String testScriptId,
			String mappingClassName) {
		this.sfTestInfoParentId = sfTestInfoParentId;
		this.sfTestInfoScriptRecordId = sfTestInfoScriptRecordId;
		this.testScriptId = testScriptId;
		this.mappingClassName = mappingClassName;
		this.result = result;
	}

	public TestInfoResponse(String sfTestInfoParentId, String sfTestInfoScriptRecordId, String application,
			String module, String title, String testScriptId, String testScriptName, String status, String path,
			String mappingClassName) {
		this.sfTestInfoParentId = sfTestInfoParentId;
		this.sfTestInfoScriptRecordId = sfTestInfoScriptRecordId;
		this.application = application;
		this.module = module;
		this.title = title;
		this.testScriptId = testScriptId;
		this.testScriptName = testScriptName;
		this.status = status;
		this.path = path;
		this.mappingClassName = mappingClassName;
		this.testData=testData;
		
	}
	
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTestScriptName() {
		return testScriptName;
	}

	public void setTestScriptName(String testScriptName) {
		this.testScriptName = testScriptName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestScriptId() {
		return testScriptId;
	}

	public void setTestScriptId(String testScriptId) {
		this.testScriptId = testScriptId;
	}

	public String getMappingClassName() {
		return mappingClassName;
	}

	public void setMappingClassName(String mappingClassName) {
		this.mappingClassName = mappingClassName;
	}

	public String toString() {
		return "application: " + this.application + " module: " + this.module + " title: " + this.title
				+ " testScriptId: " + this.testScriptId + " testScriptName: " + this.testScriptName + " status: "
				+ this.status + " MappingClassName: " + this.mappingClassName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getSfTestInfoParentId() {
		return sfTestInfoParentId;
	}

	public void setSfTestInfoParentId(String sfTestInfoParentId) {
		this.sfTestInfoParentId = sfTestInfoParentId;
	}

	public String getSfTestInfoScriptRecordId() {
		return sfTestInfoScriptRecordId;
	}

	public void setSfTestInfoScriptRecordId(String sfTestInfoScriptRecordId) {
		this.sfTestInfoScriptRecordId = sfTestInfoScriptRecordId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isExcelRecordExists() {
		return excelRecordExists;
	}

	public void setExcelRecordExists(boolean excelRecordExists) {
		this.excelRecordExists = excelRecordExists;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}
	

}
