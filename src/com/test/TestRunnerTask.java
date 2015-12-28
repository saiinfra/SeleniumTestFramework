package com.test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestRunnerTask implements Runnable {
	String args;

	public TestRunnerTask(String args) {
		this.args = args;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		String errors = null;
		boolean errorFlag = false;
		try {
			TestRunnerService testRunnerService = new TestRunnerService();
			testRunnerService.init(args);

		} catch (Exception e) {
			errorFlag = true;
			StringWriter lerrors = new StringWriter();
			e.printStackTrace(new PrintWriter(lerrors));
			errors = lerrors.toString();
		} finally {
			if (errorFlag) {
				System.out.println("");
			} else {
				System.out.println("");
			}
		}

	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

}
