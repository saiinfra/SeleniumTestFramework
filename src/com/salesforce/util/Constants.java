package com.salesforce.util;

public class Constants {

	public static final String USERID = "skrishna@developertest.com";
	public static final String PASSWORD = "infrascape4YKyRvo9lxSLmmr20xAQ72zhI";
	public static final String INSTANCE_URL = "https://na34.salesforce.com";
	//public static final String TestInformationID="a0361000005ZnOy";
	//public static final String TestScript="a0461000002iNyU";
	public static final String COMPLETED_STATUS="Completed";
	public static String MappingFileType = ".xls";
	private static String CheckoutFolder = "checkout";
	public static String MappingFolderName = "testcases";
	public static String DirSeperator = "/";
	public static String JavaSourcePath = "src/com/test";
	public static String BinDir = "bin";
	public static String Space = " ";
	public static String PackagePath = "com.test.";
	//public static String GitTestProjectURL="https://github.com/saiinfra/CustomerTestProject.git";
	public static String CheckoutPath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder+ Constants.DirSeperator+MappingFolderName;
	public static String CheckoutFilePath = AppUtil.getCurrentPath() + Constants.DirSeperator + Constants.CheckoutFolder;
	public static String CheckoutMappingFilePath = CheckoutPath + Constants.DirSeperator + MappingFolderName;
	public static String SearchPath = AppUtil.getCurrentPath()+Constants.DirSeperator+Constants.CheckoutFolder;
	public static String Modified = "Modified";
}
