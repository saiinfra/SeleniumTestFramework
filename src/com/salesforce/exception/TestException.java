package com.salesforce.exception;

public class TestException extends Exception {

	private static final long serialVersionUID = 1997753363232807009L;

	public TestException() {
	}

	public TestException(String message) {
		super(message);
	}

	public TestException(Throwable cause) {
		super(cause);
	}

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
