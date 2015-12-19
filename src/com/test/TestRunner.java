package com.test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.salesforce.domain.ResultInformationDO;
import com.salesforce.domain.TestMetadataLogDO;
import com.salesforce.ds.TestInformationDAO;
import com.salesforce.ds.TestMetadataLogDAO;
import com.salesforce.ds.TestScriptsResultsDAO;
import com.util.Constants;
import com.util.SFoAuthHandle;

public class TestRunner {

	private static ResultInformationDO resultInformationDO = null;
	private static TestMetadataLogDO metadataLogDO = null;
	private static String testsuitename;
	private static String testInformationId;

	private static SFoAuthHandle sfHandle = null;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);

			/*StringTokenizer st = new StringTokenizer(args[0], "~");

			if (st.hasMoreTokens()) {
				String testsuitename = st.nextToken();

				setTestsuitename(testsuitename);

				String testInformationId = st.nextToken();

				setTestInformationId(testInformationId);
			}*/

			// TestCasesMap map = new TestCasesMap();
			TestCasesMap map = new TestCasesMap();
			Result result = null;
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString();
			String filePath = path + "/reports";
			String reportFileName = "myReport";
			List<String> list = (map.getMap()).get(args[0]);
			int i = 0;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				i++;
				String testClass = (String) iterator.next();
				try {
					Class tClass = Class.forName(testClass);
					result = JUnitCore.runClasses(tClass);

					// find TestInfomation

					ResultInformationDO junitOutput = resultProcessing(tClass.getName(), result);

					sfHandle = new SFoAuthHandle(Constants.USERID, Constants.PASSWORD, Constants.INSTANCE_URL, "");
					TestScriptsResultsDAO testScriptsResultsDAO = new TestScriptsResultsDAO();

					List<Object> testInformationlist = findTestInformation(Constants.TestInformationID, sfHandle);
					System.out.println("Size is " + testInformationlist.size());
					if (testInformationlist.size() > 0) {
						// updating testscript results
						testScriptsResultsDAO.insert(junitOutput, sfHandle);
						TestMetadataLogDO testMetadataLogDO = createTestMetadataLog(tClass.getName(), result);

						// Creating MetadataLog with Summary Details
						TestMetadataLogDAO testMetadataLogDAO = new TestMetadataLogDAO();
						testMetadataLogDAO.insert(testMetadataLogDO, sfHandle);

					}

					System.out.println(junitOutput.toString());

					StringBuffer myContent = getResultContent(tClass.getName(), result, 1);
					writeReportFile(filePath + "/" + reportFileName + i + ".htm", myContent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println(" cannot be blank: ");
		}

	}

	private static StringBuffer getResultContent(String fileName, Result result, int numberOfTestFiles) {
		int numberOfTest = result.getRunCount();
		int numberOfTestFail = result.getFailureCount();
		int numberOfTestIgnore = result.getIgnoreCount();

		int numberOfTestSuccess = numberOfTest - numberOfTestFail - numberOfTestIgnore;
		int successPercent = (numberOfTest != 0) ? numberOfTestSuccess * 100 / numberOfTest : 0;
		double time = result.getRunTime();
		StringBuffer myContent = new StringBuffer(
				"<h1>Junit Report</h1><h2>Result</h2><table border=\"1\"><tr><th>File Name</th><th>Test Files</th><th>Tests</th><th>Success</th>");
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append("<th>Failure</th><th>Failure_Details</th><th>Ignore</th>");
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

	private static void writeReportFile(String fileName, StringBuffer reportContent) {
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

	private static ResultInformationDO resultProcessing(String testcasename, Result result) {
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

		return resultInformationDO;

	}

	private static TestMetadataLogDO createTestMetadataLog(String testcasename, Result result) {
		int numberOfTest = result.getRunCount();
		int numberOfTestFail = result.getFailureCount();
		int numberOfTestIgnore = result.getIgnoreCount();
		double time = result.getRunTime();
		metadataLogDO = new TestMetadataLogDO();
		if (numberOfTestFail > 0) {

			for (Failure failure : result.getFailures()) {
				metadataLogDO.setMessage(failure.toString());
				System.out.println(failure.toString());
			}

		} else {

			metadataLogDO.setMessage("Test Case Successfully Executed");

		}
		metadataLogDO.setStatus(Constants.COMPLETED_STATUS);
		metadataLogDO.setTestinformation(Constants.TestInformationID);
		metadataLogDO.setName(testcasename);
		return metadataLogDO;

	}

	private static List<Object> findTestInformation(String testinformationid, SFoAuthHandle sfHandle) {

		TestInformationDAO testInformationDAO = new TestInformationDAO();
		List<Object> list = testInformationDAO.findById(testinformationid, sfHandle);
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
