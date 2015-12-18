package com.test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);
			// TestCasesMap map = new TestCasesMap();
			TestCasesMap map = new TestCasesMap();
			Result result = null;
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString();
			String filePath = path+"/reports";
			String reportFileName = "myReport";
			List<String> list = (map.getMap()).get(args[0]);
			int i=0;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				i++;
				String testClass = (String) iterator.next();
				try {
					Class tClass = Class.forName(testClass);
					result = JUnitCore.runClasses(tClass);
					StringBuffer myContent = getResultContent(tClass.getName(), result, 1);
					writeReportFile(filePath + "/" + reportFileName+i+".htm", myContent);
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
}
