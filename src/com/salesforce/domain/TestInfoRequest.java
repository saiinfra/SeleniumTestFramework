package com.salesforce.domain;

public class TestInfoRequest {

	String testInfoId;
	String testInfoName;
	String orgId;
	String gitRepoURL;
	
	public TestInfoRequest (){
		super();
	}
	public TestInfoRequest (String testInfoId, String testInfoName, String orgId){
		this.testInfoId = testInfoId;
		this.testInfoName = testInfoName;
		this.orgId = orgId;
	}
	
	public TestInfoRequest (String testInfoId, String testInfoName){
		this.testInfoId = testInfoId;
		this.testInfoName = testInfoName;
	}

	public String getTestInfoId() {
		return testInfoId;
	}

	public void setTestInfoId(String testInfoId) {
		this.testInfoId = testInfoId;
	}

	public String getTestInfoName() {
		return testInfoName;
	}

	public void setTestInfoName(String testInfoName) {
		this.testInfoName = testInfoName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getGitRepoURL() {
		return gitRepoURL;
	}
	public void setGitRepoURL(String gitRepoURL) {
		this.gitRepoURL = gitRepoURL;
	}
	
}
