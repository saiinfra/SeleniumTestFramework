package com.test;

import java.util.HashMap;

public class TestCasesMap {

	public HashMap<String, String> map= new HashMap<String, String>();
	
	public TestCasesMap(){
		super();
		map.put("SampleAddTest", "com.test.SampleAddTest");
		map.put("TestJunit", "com.test.TestJunit");
		map.put("SFReleaseActive", "com.test.SFReleaseActive");
		map.put("SalesforceLoginJava", "com.test.SalesforceLoginJava");
		map.put("SeleniumTest", "com.test.SeleniumTest");
	}
	
	public HashMap<String, String> getMap(){
		return map;
	}
}
