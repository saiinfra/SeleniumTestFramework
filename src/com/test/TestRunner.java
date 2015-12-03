package com.test;

import java.util.StringTokenizer;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner {
	
	private static String testcasename;
	private static String metadataLogId;
	private static String userID;
	private static String password;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);
			String delim = "~";
			StringTokenizer st = new StringTokenizer(args[0], delim);
			if (st.hasMoreTokens()) {
				 testcasename = st.nextToken();
				 metadataLogId=st.nextToken();
				 userID=st.nextToken();
				 password=st.nextToken();
			}
			// TestCasesMap map = new TestCasesMap();
			TestCasesMap map = new TestCasesMap();
			// create salesforce domain object in java
			// attr: test_status, desc
			
			
			String value = (map.getMap()).get(testcasename);
			
			
			try {
				if (value != null) {
					Result result = JUnitCore.runClasses(Class.forName(value));
					for (Failure failure : result.getFailures()) {
						System.out.println(failure.toString());
						// prepare domain object;
						throw (new Exception(failure.toString())); 
					}
					System.out.println(result.wasSuccessful());
				}
				else {
					//Salesforce update code
					System.out.println("failure");
					throw (new Exception("Error")); 
				}
			} catch (Exception e) {
				System.out.println("Ready to record error: "+e.getMessage());
			}
			finally{
				// update into salesforce
			}
		} else {
			System.out.println(" cannot be blank: ");
		}

	}
}
