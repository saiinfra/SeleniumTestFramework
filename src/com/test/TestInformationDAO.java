package com.test;

import java.util.ArrayList;
import java.util.List;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;

public class TestInformationDAO {

	public List<Object> findById(String testInformationid, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.Test_Information__c test_Information__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn
					.query(TestInformationSQLStmts.gettestInformation(testInformationid));
			if (queryResults.getSize() > 0) {
				TestInformationDO testInformationDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					test_Information__c = (com.sforce.soap.enterprise.sobject.Test_Information__c) queryResults
							.getRecords()[i];

					testInformationDO = new TestInformationDO(test_Information__c.getId(),
							test_Information__c.getApplication__c(), test_Information__c.getDescription__c(),
							test_Information__c.getErrors__c(), "", test_Information__c.getModule_Name__c(),
							"", test_Information__c.getPriority__c(),
							test_Information__c.getTitle__c());

					System.out.println(" - Application Name: " + test_Information__c.getApplication__c());

					System.out.println(" - Module Name: " + test_Information__c.getModule_Name__c());
					System.out.println(" - Title: " + test_Information__c.getTitle__c());
					System.out.println(" --------------: ");
					list.add(testInformationDO);
				}
			} else {
				System.out.println(" There are no records size is - : " + queryResults.getSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
