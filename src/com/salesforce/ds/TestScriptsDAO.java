package com.salesforce.ds;

import java.util.ArrayList;
import java.util.List;

import com.salesforce.domain.TestScriptsDO;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.util.SFoAuthHandle;
import com.util.TestScriptsSQLStmts;

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
							test_Script__c.getName());

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
