package com.util;


public class TestScriptsSQLStmts {
	
public static String gettestscripts(String testInformationid){
		
		String sql = "SELECT Id, Name"
				+ " FROM Test_Script__c" + " where Test_Information__c= '" + testInformationid + "'";
		System.out.println(sql);
		return sql;
	}

}

