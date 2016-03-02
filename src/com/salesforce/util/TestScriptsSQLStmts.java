package com.salesforce.util;

public class TestScriptsSQLStmts {

	public static String gettestscripts(String testInformationid) {

		String sql = "SELECT Id, Name, Test_Steps__c, Script_Status__c,Test_Data__c,TestScriptType__c,LoginType__c"
				+ " FROM Test_Script__c"
				+ " where Test_Information__c= '"
				+ testInformationid + "' order by Id";
		System.out.println(sql);
		return sql;
	}

	public static String gettestscripts1(String scriptname) {

		String sql = "SELECT Id, Name, Test_Steps__c, Script_Status__c,Test_Data__c,TestScriptType__c,LoginType__c"
				+ " FROM Test_Script__c" + " where Name= '" + scriptname + "'";
		System.out.println(sql);
		return sql;
	}
	
	
	public static String gettestscripts2(String id) {

		String sql = "SELECT Id, Name, Test_Steps__c, Script_Status__c,Test_Data__c,TestScriptType__c,LoginType__c"
				+ " FROM Test_Script__c" + " where id= '" + id + "'";
		System.out.println(sql);
		return sql;
	}

	public static String gettestscriptsdetails(String testdetailsId) {

		String sql = "SELECT Id, Name, Test_Script_Details__c,ActionType__c,commandType__c,ComparisonData__c,ComponentName__c,Data__c,ElementLocationType__c,isAssertionCheck__c,Step__c,WebElementType__c"
				+ " FROM ActivityDetails__c"
				+ " where Test_Script_Details__c= '"
				+ testdetailsId
				+ "' order by Name";
		System.out.println(sql);
		return sql;
	}

}
