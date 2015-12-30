package com.salesforce.util;

public class TestScriptsSQLStmts {

	public static String gettestscripts(String testInformationid) {

		String sql = "SELECT Id, Name, Test_Steps__c, Script_Status__c" + " FROM Test_Script__c" + " where Test_Information__c= '"
				+ testInformationid + "' order by Id";
		System.out.println(sql);
		return sql;
	}

	public static String gettestscripts1(String scriptname) {

		String sql = "SELECT Id, Name, Test_Steps__c, Script_Status__c" + " FROM Test_Script__c" + " where Name= '" + scriptname + "'";
		System.out.println(sql);
		return sql;
	}

}
