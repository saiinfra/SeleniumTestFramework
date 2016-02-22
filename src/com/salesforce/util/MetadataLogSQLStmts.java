package com.salesforce.util;

public class MetadataLogSQLStmts {

	public static String getMetdataLogRecordQuery(String metadataLogId){
		
		String sql = "SELECT Id, Name, Action__c,OrganizationId__c, Status__c, Releases__c, ReleaseEnvironment__c "
				+ " FROM MetadataLog__c" + " where Id= '" + metadataLogId + "'";
		System.out.println(sql);
		return sql;
	}
public static String gettestMetdataLogRecordQuery(String metadataLogId){
		
		String sql = "SELECT Id, Name, Action__c,ID__c, Name__c, RecordId__c, Script__c,Status__c "
				+ " FROM MetadataLog__c" + " where Id= '" + metadataLogId + "'";
		System.out.println(sql);
		return sql;
	}
}
