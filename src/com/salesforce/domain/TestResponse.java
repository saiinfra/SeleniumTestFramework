package com.salesforce.domain;

import java.util.List;

public class TestResponse {

	List<TestInfoResponse> testInfoResponseList;
	boolean areTestClassesModified;
	boolean doesMappingFileExist;
	TestInformationDO testInformationDO;
	
	String orgId;

	public List<TestInfoResponse> getTestInfoResponseList() {
		return testInfoResponseList;
	}

	public void setTestInfoResponseList(List<TestInfoResponse> testInfoResponseList) {
		this.testInfoResponseList = testInfoResponseList;
	}

	public boolean isAreTestClassesModified() {
		return areTestClassesModified;
	}

	public void setAreTestClassesModified(boolean areTestClassesModified) {
		this.areTestClassesModified = areTestClassesModified;
	}

	public boolean isDoesMappingFileExist() {
		return doesMappingFileExist;
	}

	public void setDoesMappingFileExist(boolean doesMappingFileExist) {
		this.doesMappingFileExist = doesMappingFileExist;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public TestInformationDO getTestInformationDO() {
		return testInformationDO;
	}

	public void setTestInformationDO(TestInformationDO testInformationDO) {
		this.testInformationDO = testInformationDO;
	}


	
}
