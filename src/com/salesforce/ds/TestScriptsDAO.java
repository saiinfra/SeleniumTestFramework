package com.salesforce.ds;

import java.util.ArrayList;
import java.util.List;

import com.salesforce.domain.TestScriptsDO;
import com.salesforce.util.SFoAuthHandle;
import com.salesforce.util.TestScriptsSQLStmts;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;

public class TestScriptsDAO {

	public List<Object> findByTestInformationId(String testInformationid,
			SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.Test_Script__c test_Script__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(TestScriptsSQLStmts
					.gettestscripts(testInformationid));
			if (queryResults.getSize() > 0) {

				TestScriptsDO testScriptsDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					test_Script__c = (com.sforce.soap.enterprise.sobject.Test_Script__c) queryResults
							.getRecords()[i];

					testScriptsDO = new TestScriptsDO(test_Script__c.getId(),
							test_Script__c.getName(), test_Script__c.getTest_Steps__c(), test_Script__c.getScript_Status__c());

					System.out.println(" - Name: " + test_Script__c.getName());

					list.add(testScriptsDO);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Object> findByScriptName(String scriptname,
			SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.Test_Script__c test_Script__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(TestScriptsSQLStmts
					.gettestscripts1(scriptname));
			if (queryResults.getSize() > 0) {

				TestScriptsDO testScriptsDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					test_Script__c = (com.sforce.soap.enterprise.sobject.Test_Script__c) queryResults
							.getRecords()[i];

					testScriptsDO = new TestScriptsDO(test_Script__c.getId(),
							test_Script__c.getName(), test_Script__c.getTest_Steps__c(), test_Script__c.getScript_Status__c());

					System.out.println(" - Name: " + test_Script__c.getName());

					list.add(testScriptsDO);
				}
			} else {
				System.out.println(" There are no records size is - : "
						+ queryResults.getSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
