package com.salesforce.domain;

public class STestCaseDObj {

	String application;
	String module;
	String title;
	String testScriptId;
	String testScriptName;
	String status;
	String path;
	String mappingClassName;

	public STestCaseDObj() {
		super();
	}
	
	public STestCaseDObj(String testScriptId, String mappingClassName) {
		this.testScriptId = testScriptId;
		this.mappingClassName = mappingClassName;
	}

	public STestCaseDObj(String application, String module, String title, String testScriptId, String testScriptName,
			String status, String path, String mappingClassName) {
		this.application = application;
		this.module = module;
		this.title = title;
		this.testScriptId = testScriptId;
		this.testScriptName = testScriptName;
		this.status = status;
		this.path = path;
		this.mappingClassName = mappingClassName;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString() {
		return "application: " + this.application + " module: " + this.module + " title: " + this.title
				+ " testScriptId: " + this.testScriptId + " testScriptName: " + this.testScriptName + " status: "
				+ this.status + " path: " + this.path + " MappingClassName: " + this.mappingClassName;
	}

}
