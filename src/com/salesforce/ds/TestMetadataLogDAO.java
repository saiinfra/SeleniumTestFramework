package com.salesforce.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.salesforce.domain.TestMetadataLogDO;
import com.salesforce.util.Constants;
import com.salesforce.util.MetadataLogSQLStmts;
import com.salesforce.util.SFoAuthHandle;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.MetadataLog__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.Test_Script_Result__c;

/**
 * 
 * @author MetadataLogDAO is Used For Performing CRUD Operations for
 *         {@link MetadataLog__c}
 *
 */
public class TestMetadataLogDAO {

	public TestMetadataLogDAO() {
		super();
	}

	public String insert(Object obj, SFoAuthHandle sfHandle,String testInformationId) {
		// create the records
		TestMetadataLogDO testMetadataLogDO = (TestMetadataLogDO) obj;

		MetadataLog__c[] record = new MetadataLog__c[1];

		// Get the name of the sObject
		MetadataLog__c a = new MetadataLog__c();

		// a.setMessage__c(testMetadataLogDO.getMessage());
		a.setStatus__c(testMetadataLogDO.getStatus());
		a.setTest_Information__c(testInformationId);
		a.setTests__c(testMetadataLogDO.getTotalTests());
		a.setFailures__c(testMetadataLogDO.getTotalFailures());
		a.setTimes_s__c(testMetadataLogDO.getTotalTimes());
		// a.setName__c(testMetadataLogDO.getName());

		record[0] = a;
		String metadatLog=commit1(record, sfHandle);

		return metadatLog;
	}

	public boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle) {
		try {
			com.sforce.soap.enterprise.UpsertResult[] saveResults = sfHandle
					.getEnterpriseConnection().upsert("Id", sobjects);

			for (UpsertResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created TestMetadata  record - Id: "
							+ r.getId());
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						throw new Exception(e.getMessage() + "-status code-"
								+ e.getStatusCode());
					}
					return false;
				}
			}
			System.out.println("saving TestResults :");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public String  commit1(SObject[] sobjects, SFoAuthHandle sfHandle) {
		String metadataLogId="";
		try {
			com.sforce.soap.enterprise.UpsertResult[] saveResults = sfHandle
					.getEnterpriseConnection().upsert("Id", sobjects);

			for (UpsertResult r : saveResults) {
				if (r.isSuccess()) {
					System.out.println("Created TestMetadata  record - Id: "
							+ r.getId());
					metadataLogId=r.getId();
				} else {
					for (com.sforce.soap.enterprise.Error e : r.getErrors()) {
						throw new Exception(e.getMessage() + "-status code-"
								+ e.getStatusCode());
					}
					return metadataLogId;
				}
			}
			System.out.println("saving TestResults :");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metadataLogId;
	}

	public boolean update(Object obj, SFoAuthHandle sfHandle,String testInformationId) {
		try {

			if (obj == null) {
				return false;
			}
			TestMetadataLogDO metadataLogDOobj = (TestMetadataLogDO) obj;
			com.sforce.soap.enterprise.sobject.MetadataLog__c metadataLog__c = new com.sforce.soap.enterprise.sobject.MetadataLog__c();

			if (metadataLogDOobj instanceof TestMetadataLogDO) {
				metadataLog__c.setId(metadataLogDOobj.getId());
				metadataLog__c.setStatus__c(metadataLogDOobj.getStatus());
				metadataLog__c.setTest_Information__c(testInformationId);
				metadataLog__c.setTests__c(metadataLogDOobj.getTotalTests());
				metadataLog__c.setFailures__c(metadataLogDOobj.getTotalFailures());
				metadataLog__c.setTimes_s__c(metadataLogDOobj.getTotalTimes());

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

				/*	testMetadataLogDO = new TestMetadataLogDO(
							metadataLog__c.getId(),
							metadataLog__c.getName__c(),
							metadataLog__c.getStatus__c(),
							metadataLog__c.getMessage__c(),
							metadataLog__c.getTest_Information__c(),
							metadataLog__c.getTests__c(),0.0,
							metadataLog__c.getFailures__c(),
							metadataLog__c.getTimes_s__c());*/

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
