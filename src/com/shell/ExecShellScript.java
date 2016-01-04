package com.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.salesforce.util.AppUtil;
import com.salesforce.util.Constants;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class ExecShellScript {

	public static void main(String[] args) {

	}

	public static void checkOutSrc(String repoURL) {
		try {
			String execString = AppUtil.getCurrentPath() + Constants.DirSeperator + "checkOutSourceFiles.sh" + Constants.Space
					+ Constants.CheckoutPath + Constants.Space + repoURL;

			System.out.println("execString: "+execString);
			Process proc = Runtime.getRuntime()
					.exec(execString);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			/*while (read.ready()) {
				System.out.println(read.readLine());
			}*/
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void copyFile(String sourceFileWithPath) {
		try {
			String targetPath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.JavaSourcePath;
			String execString = AppUtil.getCurrentPath() + Constants.DirSeperator + "copyFilesScript.sh" + Constants.Space
					+ sourceFileWithPath + Constants.Space + targetPath;
			Process proc = Runtime.getRuntime().exec(execString);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				System.out.println(read.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void compile(String fileName) {
		try {
			String srcPath = AppUtil.getCurrentPath() +Constants.DirSeperator+ Constants.JavaSourcePath +Constants.DirSeperator;
			// String targetPath =
			// "/home/infra3/eclipse_workspace/selenium/s1/Selenium_Test/build/classes";
			String targetPath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.BinDir;
			String fileName1 = srcPath + fileName;
			String execString=AppUtil.getCurrentPath() + Constants.DirSeperator + "compile.sh"
					+ Constants.Space + targetPath + Constants.Space + fileName1;
			Process proc = Runtime.getRuntime().exec(execString);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				System.out.println(read.readLine());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void runTestCase(String fileName) {
		try {
			fileName = Constants.PackagePath + fileName;
			Process proc = Runtime.getRuntime()
					.exec(AppUtil.getCurrentPath() + Constants.Space + "run.sh" + Constants.Space + fileName);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				System.out.println(read.readLine());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void checkInMappingFile(String fileName) {
		try {
			String execFilePath = AppUtil.getCurrentPath();
			fileName = "com.test." + fileName;
			Process proc = Runtime.getRuntime().exec(execFilePath + "run.sh " + fileName);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			while (read.ready()) {
				System.out.println(read.readLine());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void checkOutMappingFile(String fileName, String repoURL, String checkoutPath) {
		try {
			String arg1 = fileName;
			String arg2 = repoURL;
			String arg3 = checkoutPath;

			String execString = AppUtil.getCurrentPath() + Constants.DirSeperator + "checkOutMappingFile.sh " + arg1
					+ Constants.Space + arg2 + Constants.Space + arg3;
			System.out.println("execString: "+execString);
			Process proc = Runtime.getRuntime().exec(execString);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			/*
			 * while (read.ready()) { System.out.println(read.readLine()); }
			 */
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void testGit() {
		try {
			String execString = AppUtil.getCurrentPath() + Constants.DirSeperator + "gitconfig.sh";
			System.out.println("execString: "+execString);
			Process proc = Runtime.getRuntime().exec(execString);
			BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			try {
				proc.waitFor();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			/*
			 * while (read.ready()) { System.out.println(read.readLine()); }
			 */
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
