package com.test;

public class TestRunner {

	public static void main(String[] args) {

		if (args.length > 0) {
			String param = args[0];
			System.out.println("Arg1: " + param);

			Runnable task;
			try {
				task = new TestRunnerTask("FailureTest~a0361000005ZnOy");
                 task.run();
				//Thread t = new Thread(task);
				//t.start();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
}
