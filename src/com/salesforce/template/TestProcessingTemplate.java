package com.salesforce.template;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.file.FileSearch;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.util.Constants;
import com.shell.ExecShellScript;

public abstract class TestProcessingTemplate {
	Result result = null;
	TestInfoResponse testInfoResponse;

	public abstract Result doProcessing();

	public Result doProcessing1() {
		if (testInfoResponse != null && testInfoResponse.getMappingClassName() != ""
				&& !testInfoResponse.getStatus().equals(Constants.Modified)) {
			String fileFoundStr = FileSearch.searchCheckoutPath(testInfoResponse.getMappingClassName() + ".java");
			System.out.println("fileFoundStr  : " + fileFoundStr);
			if (fileFoundStr.equals("Found") && (testInfoResponse.getMappingClassName() != null
					|| !testInfoResponse.getMappingClassName().isEmpty())) {
				try {
					//ExecShellScript.copyFile(fileFoundStr);
					String compileJava=testInfoResponse.getMappingClassName() + ".java";
					ExecShellScript.compile(compileJava);
					result = executeTest("com.test." + testInfoResponse.getMappingClassName());
				} catch (Exception e) {
					e.printStackTrace();
					result = null;
				}
			} else {
				System.out.println("No test case written yet");
			}
		} else {
			// error case
			result = null;
		}
		return result;
	}

	private void init() {
		String inputTokens = "a0361000005ZnOy~00D61000000fBw41~T-0000000001";
		// doPostProcessing1(inputTokens);
	}

	private Result executeTest(String testCase) {
		System.out.println("testcasename" + testCase);
		result = null;
		if (testCase != null) {
			try {
				Class<?> myTestToRunTab = Class.forName(testCase);
				result = JUnitCore.runClasses(myTestToRunTab);
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	public TestInfoResponse getTestInfoResponse() {
		return testInfoResponse;
	}

	public void setTestInfoResponse(TestInfoResponse testInfoResponse) {
		this.testInfoResponse = testInfoResponse;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
