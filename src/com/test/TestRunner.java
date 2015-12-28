package com.test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.factory.Factory;
import com.salesforce.domain.ResultInformationDO;
import com.salesforce.domain.TestMetadataLogDO;
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.ds.TestInformationDAO;
import com.salesforce.ds.TestMetadataLogDAO;
import com.salesforce.ds.TestScriptsDAO;
import com.salesforce.ds.TestScriptsResultsDAO;
import com.util.Constants;
import com.util.SFoAuthHandle;

public class TestRunner {

	private static ResultInformationDO resultInformationDO = null;
	private static TestMetadataLogDO metadataLogDO = null;
	private static String testsuitename;
	private static String testInformationId;
	private static ArrayList<Integer> failureList = new ArrayList<>();
	private static ArrayList<Integer> noofTestList = new ArrayList<>();
	private static ArrayList<Double> totalTimeList = new ArrayList<>();

	private static double sum1 = 0.0;
	private static double sum2 = 0.0;
	private static double sum3 = 0.0;

	private static SFoAuthHandle sfHandle = null;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);

			// Tokenizing the TestParameter Which is Sent from Salesforce.

			StringTokenizer st = new StringTokenizer(args[0], "~");

			if (st.hasMoreTokens()) {
				String testsuitename = st.nextToken();

				setTestsuitename(testsuitename);

				String testInformationId = st.nextToken();

				setTestInformationId(testInformationId);
			}

			// TestCase Mapping
			TestCasesMap map = new TestCasesMap();
			Result result = null;
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString();
			String filePath = path + "/reports";
			String reportFileName = "myReport";
			List<String> list = (map.getMap()).get(getTestsuitename());
			int i = 0;
			for (Iterator<String> iterator = list.iterator(); iterator
					.hasNext();) {
				i++;
				String testClass = (String) iterator.next();
				try {
					Class tClass = Class.forName(testClass);
					result = JUnitCore.runClasses(tClass);

					// Results Processing

					ResultInformationDO junitOutput = resultProcessing(
							tClass.getName(), result);

					sfHandle = new SFoAuthHandle(Constants.USERID,
							Constants.PASSWORD, Constants.INSTANCE_URL, "");

					TestScriptsResultsDAO testScriptsResultsDAO = (TestScriptsResultsDAO) Factory
							.getObjectInstance("TestScriptsResultsDAO");

					List<Object> testInformationlist = findTestInformation(
							Constants.TestInformationID, sfHandle);
					System.out.println("Size is " + testInformationlist.size());
					if (testInformationlist.size() > 0) {

						// updating testscript results
						testScriptsResultsDAO.insert(junitOutput, sfHandle);

					}

					StringBuffer myContent = getResultContent(tClass.getName(),
							result, 1);

					System.out.println(myContent.toString());
					writeReportFile(filePath + "/" + reportFileName + i
							+ ".htm", myContent);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			// total failures

			for (Iterator<Integer> iterator = failureList.iterator(); iterator
					.hasNext();) {
				double fail = ((Integer) iterator.next()).doubleValue();
				Double d = new Double(fail);
				sum1 = sum1 + fail;

			}
			// total tests

			for (Iterator<Integer> iterator = noofTestList.iterator(); iterator
					.hasNext();) {
				double string = ((Integer) iterator.next()).doubleValue();
				sum2 = sum2 + string;

			}

			// total time
			for (Iterator<Double> iterator = totalTimeList.iterator(); iterator
					.hasNext();) {
				Double string = (Double) iterator.next();
				sum3 = sum3 + string;

			}

			/*
			 * Double TotalFailures = getTotalFailures(); // Double TotalTests
			 * =getTotalTests(); Double TotallTime = getTotalTime();
			 * 
			 * // System.out.println("toatl tests :" + TotalTests);
			 * System.out.println("toatl failures :" + TotalFailures);
			 * System.out.println("toatl time :" + TotallTime);
			 */

			System.out.println("No of Test Fails :" + sum1);
			System.out.println("No of Test  :" + sum2);
			Double ToatlSucess = sum2 - sum1;
			System.out.println("No of Test success :" + ToatlSucess);

			TestMetadataLogDO testMetadataLogDO = createTestMetadataLog(sum1,
					sum2, ToatlSucess, sum3);

			// finding no of testscripts for testinformation

			TestScriptsDAO testScriptsDAO = (TestScriptsDAO) Factory
					.getObjectInstance("TestScriptsDAO");

			List<Object> testscriptlist = testScriptsDAO
					.findByTestInformationId(getTestInformationId(), sfHandle);

			for (Iterator iterator = testscriptlist.iterator(); iterator
					.hasNext();) {
				TestScriptsDO testScriptsDO = (TestScriptsDO) iterator.next();
				System.out.println("TestScript Name :"
						+ testScriptsDO.getTestScritName());
				System.out.println("ID :" + testScriptsDO.getId());

			}

			// Creating MetadataLog with Summary Details
			TestMetadataLogDAO testMetadataLogDAO = (TestMetadataLogDAO) Factory
					.getObjectInstance("TestMetadataLogDAO");
			testMetadataLogDAO.insert(testMetadataLogDO, sfHandle);
		} else {
			System.out.println(" cannot be blank: ");
		}

	}

	private static StringBuffer getResultContent(String fileName,
			Result result, int numberOfTestFiles) {
		int numberOfTest = result.getRunCount();
		int numberOfTestFail = result.getFailureCount();
		int numberOfTestIgnore = result.getIgnoreCount();

		int numberOfTestSuccess = numberOfTest - numberOfTestFail
				- numberOfTestIgnore;
		int successPercent = (numberOfTest != 0) ? numberOfTestSuccess * 100
				/ numberOfTest : 0;
		double time = result.getRunTime();
		StringBuffer myContent = new StringBuffer(
				"<h1>Junit Report</h1><h2>Result</h2><table border=\"1\"><tr><th>File Name</th><th>Test Files</th><th>Tests</th><th>Success</th><th>Failure</th><th>Failure_Details</th><th>Ignore</th>");
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			// myContent.append("<th>Failure</th><th>Failure_Details</th><th>Ignore</th>");
		} else if ((numberOfTestFail <= 0) || (numberOfTestIgnore <= 0)) {

		}
		myContent.append("<th>Test Time (seconds)</th></tr><tr");
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append(" style=\"color:red\" ");
		}
		myContent.append("><td>");
		myContent.append(fileName);
		myContent.append("</td><td>");
		myContent.append(numberOfTestFiles);
		myContent.append("</td><td>");
		myContent.append(numberOfTest);
		myContent.append("</td><td>");
		myContent.append(successPercent);
		myContent.append("%</td><td>");
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append(numberOfTestFail);
			myContent.append("</td><td>");

			for (Failure failure : result.getFailures()) {
				myContent.append(failure.toString());
				myContent.append("</td><td>");
			}
			myContent.append(numberOfTestIgnore);
			myContent.append("</td><td>");
		}

		myContent.append(Double.valueOf(time / 1000.0D));
		myContent.append("</td></tr></table>");
		return myContent;
	}

	private static void writeReportFile(String fileName,
			StringBuffer reportContent) {
		FileWriter myFileWriter = null;
		try {
			myFileWriter = new FileWriter(fileName);
			myFileWriter.write(reportContent.toString());
		} catch (IOException e) {

		} finally {
			if (myFileWriter != null) {
				try {
					myFileWriter.close();
				} catch (IOException e) {

				}
			}
		}
	}

	private static ResultInformationDO resultProcessing(String testcasename,
			Result result) {
		int numberOfTest = result.getRunCount();
		int numberOfTestFail = result.getFailureCount();
		int numberOfTestIgnore = result.getIgnoreCount();
		double time = result.getRunTime();
		resultInformationDO = new ResultInformationDO();
		if (numberOfTestFail > 0) {

			for (Failure failure : result.getFailures()) {
				resultInformationDO.setType(failure.toString());
				System.out.println(failure.toString());
			}
			resultInformationDO.setStatus("failure");
			resultInformationDO.setTestcasename(testcasename);
			resultInformationDO.setTime(Double.valueOf(time / 1000.0D));
		} else {

			resultInformationDO.setStatus("success");
			resultInformationDO.setTestcasename(testcasename);
			resultInformationDO.setTime(Double.valueOf(time / 1000.0D));

		}

		failureList.add(new Integer(numberOfTestFail));
		noofTestList.add(new Integer(numberOfTest));
		totalTimeList.add(Double.valueOf(time / 1000.0D));
		return resultInformationDO;

	}

	private static TestMetadataLogDO createTestMetadataLog(Double fails,
			Double tests, Double sucess, Double totalTime) {

		metadataLogDO = new TestMetadataLogDO();

		metadataLogDO.setStatus(Constants.COMPLETED_STATUS);
		metadataLogDO.setTestinformation(Constants.TestInformationID);
		metadataLogDO.setTotalTests(tests);
		metadataLogDO.setTotalFailures(fails);
		metadataLogDO.setTotalSuccess(sucess);
		metadataLogDO.setTotalTimes(totalTime);

		return metadataLogDO;

	}

	private static Double getTotalFailures() {

		for (Iterator iterator = failureList.iterator(); iterator.hasNext();) {
			double toatlfail = ((Integer) iterator.next()).doubleValue();
			Double d = new Double(toatlfail);

			sum1 = sum1 + d;

		}
		return sum1;

	}

	private static Double getTotalTime() {

		for (Iterator iterator = noofTestList.iterator(); iterator.hasNext();) {
			Double string = (Double) iterator.next();
			sum3 = sum3 + string;

		}
		return sum3;

	}

	private static Double getTotalTests() {

		for (Iterator iterator = totalTimeList.iterator(); iterator.hasNext();) {
			double totaltests = ((Integer) iterator.next()).doubleValue();
			Double d = new Double(totaltests);
			sum2 = sum2 + d;

		}
		return sum2;

	}

	private static List<Object> findTestInformation(String testinformationid,
			SFoAuthHandle sfHandle) {

		TestInformationDAO testInformationDAO = new TestInformationDAO();
		List<Object> list = testInformationDAO.findById(testinformationid,
				sfHandle);
		return list;
	}

	public static String getTestsuitename() {
		return testsuitename;
	}

	public static void setTestsuitename(String testsuitename) {
		TestRunner.testsuitename = testsuitename;
	}

	public static String getTestInformationId() {
		return testInformationId;
	}

	public static void setTestInformationId(String testInformationId) {
		TestRunner.testInformationId = testInformationId;
	}

}
