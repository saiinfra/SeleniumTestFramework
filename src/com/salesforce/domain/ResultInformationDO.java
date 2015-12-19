package com.salesforce.domain;

public class ResultInformationDO {
	
	private String testcasename;
	private String status;
	private String type;
	private Double time;
	public String getTestcasename() {
		return testcasename;
	}
	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TestCasename : "+this.testcasename+" "+"Status :"+this.status+" "+"Type : "+this.type+" "+"Time"+""+this.time;
	}
	

}
