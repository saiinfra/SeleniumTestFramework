package com.test;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class MyRunListner extends RunListener {
	private int numberOfTestClass;
	private int testExecuted;
	private int testFailed;
	private long begin;

	public MyRunListner(int numberOfTestClass) {
		this.setBegin(System.currentTimeMillis());
		this.numberOfTestClass = numberOfTestClass;
	}

	public void testStarted(Description description) throws Exception {
		this.testExecuted += 1;
	}

	public void testFailure(Failure failure) throws Exception {
		this.testFailed += 1;
	}

	/**
	 * @return the numberOfTestClass
	 */
	public int getNumberOfTestClass() {
		return numberOfTestClass;
	}

	/**
	 * @param numberOfTestClass
	 *            the numberOfTestClass to set
	 */
	public void setNumberOfTestClass(int numberOfTestClass) {
		this.numberOfTestClass = numberOfTestClass;
	}

	/**
	 * @return the testExecuted
	 */
	public int getTestExecuted() {
		return testExecuted;
	}

	/**
	 * @param testExecuted
	 *            the testExecuted to set
	 */
	public void setTestExecuted(int testExecuted) {
		this.testExecuted = testExecuted;
	}

	/**
	 * @return the testFailed
	 */
	public int getTestFailed() {
		return testFailed;
	}

	/**
	 * @param testFailed
	 *            the testFailed to set
	 */
	public void setTestFailed(int testFailed) {
		this.testFailed = testFailed;
	}

	/**
	 * @return the begin
	 */
	public long getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(long begin) {
		this.begin = begin;
	}
}