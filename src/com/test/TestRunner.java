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

	private static String serverURL;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);
			String delim = "~";
			StringTokenizer st = new StringTokenizer(args[0], delim);
			if (st.hasMoreTokens()) {
				testcasename = st.nextToken();
				metadataLogId = st.nextToken();
				userID = st.nextToken();
				password = st.nextToken();
				serverURL = st.nextToken();

			}
			// TestCasesMap map = new TestCasesMap();
			TestCasesMap map = new TestCasesMap();
			// create salesforce domain object in java
			// attr: test_status, desc

			ErrorBean errorBean = new ErrorBean();

			String value = (map.getMap()).get(testcasename);

			try {
				if (value != null) {
					Result result = JUnitCore.runClasses(Class.forName(value));
					for (Failure failure : result.getFailures()) {
						// System.out.println(failure.toString());
						// prepare domain object;

						throw (new Exception(failure.toString()));
					}
					errorBean.setDescription("Test case :" + testcasename + " "
							+ "sucessfully Processed");
					System.out.println("Test case :" + testcasename + " "
							+ "sucessfully Processed");
					errorBean.setStatus(result.wasSuccessful());

					System.out.println(result.wasSuccessful());
				} else {

					throw (new Exception("Test case not Found"));
				}
			} catch (Exception e) {
				errorBean.setStatus(false);
				errorBean.setDescription("Test case failure" +" "+ e.getMessage());
				System.out.println("Ready to record error: " + e.getMessage());
			} finally {

				// update into salesforce

				TestMetadataLogDO testMetadataLogDO = null;

				SFoAuthHandle sfHandle = new SFoAuthHandle(userID, password,
						serverURL, "");

				try {

					// Get Meta data Log details
					testMetadataLogDO = RDAppService.findTestMetadataLog(
							metadataLogId, sfHandle);

					// updating metadataLog to Completing state
					RDAppService.updateTestMetadataLogStatus(testMetadataLogDO,
							"completed", errorBean.getDescription(), sfHandle);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}
}
