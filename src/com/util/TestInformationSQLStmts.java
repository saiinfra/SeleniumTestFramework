package com.util;

public class TestInformationSQLStmts {
	
public static String gettestInformation(String testInformationid){
		
		String sql = "SELECT Id, Name, Application__c,Description__c, Errors__c, Module_Name__c,Priority__c,Title__c "
				+ " FROM Test_Information__c" + " where Id= '" + testInformationid + "'";
		System.out.println(sql);
		return sql;
	}

}
