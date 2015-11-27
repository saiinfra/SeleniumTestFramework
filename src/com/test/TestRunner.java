package com.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Arg1: " + args[0]);
			// TestCasesMap map = new TestCasesMap();
			TestCasesMap map = new TestCasesMap();
			
			String value = (map.getMap()).get(args[0]);
			if (value != null) {
				Result result = null;
				try {
					result = JUnitCore.runClasses(Class.forName(value));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Failure failure : result.getFailures()) {
					System.out.println(failure.toString());
				}
				System.out.println(result.wasSuccessful());
			}
		} else {
			System.out.println(" cannot be blank: ");
		}

	}
}
