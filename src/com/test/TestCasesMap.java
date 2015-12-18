package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCasesMap {

	public HashMap<String, List<String>> map= new HashMap<String, List<String>>();
	
	public TestCasesMap(){
		super();
		List<String> list = new ArrayList<String>();
		list.add("com.test.SeleniumCITest");
		list.add("com.test.SeleniumTest");
		map.put("SuccessTest", list);
		list = new ArrayList<String>();
		list.add("com.test.RackspaceContactAutomation");
		list.add("com.test.SampleAddTest");
		map.put("FailureTest", list);
	}
	
	public HashMap<String, List<String>> getMap(){
		return map;
	}
}
