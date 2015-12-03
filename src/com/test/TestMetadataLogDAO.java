package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.SObject;

/**
 * 
 * @author MetadataLogDAO is Used For Performing CRUD Operations for
 *         {@link MetadataLog__c}
 *
 */
public class TestMetadataLogDAO  {

	public TestMetadataLogDAO() {
		super();
	}


	public boolean update(Object obj, SFoAuthHandle sfHandle) {
		try {

			if (obj == null) {
				return false;
			}
			TestMetadataLogDO metadataLogDOobj = (TestMetadataLogDO) obj;
			com.sforce.soap.enterprise.sobject.MetadataLog__c metadataLog__c = new com.sforce.soap.enterprise.sobject.MetadataLog__c();

			if (metadataLogDOobj instanceof TestMetadataLogDO) {
				metadataLog__c.setId(metadataLogDOobj.getId());
				metadataLog__c.setStatus__c(metadataLogDOobj.getStatus());
				metadataLog__c.setMessage__c(metadataLogDOobj.getMessage());
				
			}

			SaveResult[] saveResults = sfHandle
					.getEnterpriseConnection()
					.update(new com.sforce.soap.enterprise.sobject.MetadataLog__c[] { metadataLog__c });
			for (SaveResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Updated MetadataLogDAO component: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {

						System.out.println(e.getMessage() + "-status code-"
								+ e.getStatusCode());
					}
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Object> findById(String metadataLogId, SFoAuthHandle sfHandle) {
		com.sforce.soap.enterprise.sobject.MetadataLog__c metadataLog__c = null;
		List<Object> list = new ArrayList<Object>();
		try {
			EnterpriseConnection conn = sfHandle.getEnterpriseConnection();
			QueryResult queryResults = conn.query(MetadataLogSQLStmts
					.gettestMetdataLogRecordQuery(metadataLogId));
			if (queryResults.getSize() > 0) {
				TestMetadataLogDO testMetadataLogDO = null;
				for (int i = 0; i < queryResults.getRecords().length; i++) {
					metadataLog__c = (com.sforce.soap.enterprise.sobject.MetadataLog__c) queryResults
							.getRecords()[i];

					testMetadataLogDO = new TestMetadataLogDO(
							metadataLog__c.getId(), metadataLog__c.getName(),
							metadataLog__c.getName__c(),
							metadataLog__c.getScript__c(),
							metadataLog__c.getAction__c(),
							metadataLog__c.getStatus__c(),
							metadataLog__c.getID__c(),"");

					System.out.println(" - Action: "
							+ metadataLog__c.getAction__c());

					System.out.println(" - Status: "
							+ metadataLog__c.getStatus__c());
					System.out.println(" - Id: " + metadataLog__c.getId());
					System.out.println(" --------------: ");
					list.add(testMetadataLogDO);
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
