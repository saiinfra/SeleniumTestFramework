package com.salesforce.ds;

import java.util.ArrayList;
import java.util.List;

import com.salesforce.domain.ActivityDetailsDO;
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.util.SFoAuthHandle;
import com.salesforce.util.TestScriptsSQLStmts;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;

public class ActivityDetailsDAO {

	public List<Object> findByTestdetailsId(String testdetailsId,
			SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.ActivityDetails__c activityDetails__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(TestScriptsSQLStmts
					.gettestscriptsdetails(testdetailsId));
			if (queryResults.getSize() > 0) {

				ActivityDetailsDO activityDetailsDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					activityDetails__c = (com.sforce.soap.enterprise.sobject.ActivityDetails__c) queryResults
							.getRecords()[i];
					activityDetailsDO = new ActivityDetailsDO(
							activityDetails__c.getId(),
							activityDetails__c.getActionType__c(),
							activityDetails__c.getCommandType__c(),
							activityDetails__c.getComparisonData__c(),
							activityDetails__c.getComponentName__c(),
							activityDetails__c.getData__c(),
							activityDetails__c.getElementLocationType__c(),
							activityDetails__c.getIsAssertionCheck__c(),
							activityDetails__c.getStep__c(),
							activityDetails__c.getTest_Script_Details__c(),
							activityDetails__c.getWebElementType__c());

					System.out.println(" - Activity Name: "
							+ activityDetails__c.getName());

					list.add(activityDetailsDO);
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
