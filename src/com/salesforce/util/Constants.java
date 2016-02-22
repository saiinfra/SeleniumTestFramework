package com.salesforce.util;

public class Constants {

	public static final String USERID = "skrishna@developertest.com";
	public static final String PASSWORD = "infrascape4yUUoQQTYo60qjmO9it17p9M9";
	public static final String INSTANCE_URL = "https://na34.salesforce.com";
	// public static final String TestInformationID="a0361000005ZnOy";
	// public static final String TestScript="a0461000002iNyU";
	public static final String COMPLETED_STATUS = "Completed";
	public static String MappingFileType = ".xls";
	private static String CheckoutFolder = "checkout";
	public static String MappingFolderName = "testcases";
	public static String DirSeperator = "/";
	public static String JavaSourcePath = "src/com/test";
	public static String JavaSourcePath1 = "src/com/test1";

	public static String BinDir = "build/classes";
	public static String Space = " ";
	public static String PackagePath = "com.test.";
	public static String GitTestProjectURL = "https://github.com/saiinfra/CustomerTestProject.git";
	public static String CheckoutPath1 = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder;
	public static String JavaSrcSearchPath = AppUtil.getCurrentPath() + Constants.DirSeperator + JavaSourcePath;

	public static String CheckoutPath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder
			+ Constants.DirSeperator + MappingFolderName;
	public static String MappingFilePath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder
			+ Constants.DirSeperator + MappingFolderName;
	public static String CheckoutFilePath = AppUtil.getCurrentPath() + Constants.DirSeperator
			+ Constants.CheckoutFolder;
	public static String CheckoutMappingFilePath = CheckoutPath + Constants.DirSeperator + MappingFolderName;
	public static String SearchPath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder;

	public static String Modified = "Modified";
	public static String TempRepoUserName = "skrishna@infrascape.com";
	public static String TempRepoPassword = "Yarragsa@01";
	public static String TempRepoURL = "https://github.com/saiinfra/CustomerTestProject.git";
	public static String DummyTestCase = "DummyTestCase.java";
	public static String ReadyForExecution = "Ready for Execution";
}
