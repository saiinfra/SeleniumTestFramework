package com.salesforce.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.salesforce.ds.TestInformationDAO;
import com.salesforce.ds.TestMetadataLogDAO;
import com.salesforce.ds.TestScriptsDAO;
import com.salesforce.factory.Factory;
import com.salesforce.util.Constants;
import com.salesforce.util.SFoAuthHandle;
import com.salesforce.util.SalesForceUtil;

public class SFDomainUtil {

	public SFDomainUtil() {
		super();
	}

	public static TestInformationDO getTestAppHeaderDetails(String id) {
		TestInformationDAO testInformationDAO = (TestInformationDAO) Factory.getObjectInstance("TestInformationDAO");
		List<Object> testInfoList = testInformationDAO.findById(id, SalesForceUtil.getSFHandle());
		TestInformationDO testInformationDO = null;
		if (testInfoList != null) {
			for (Iterator iterator = testInfoList.iterator(); iterator.hasNext();) {
				testInformationDO = (TestInformationDO) iterator.next();
			}
		}
		return testInformationDO;
	}

	public static List<Object> getTestScriptsDetails(String testInfoId) {
		TestScriptsDAO testScriptsDAO = (TestScriptsDAO) Factory.getObjectInstance("TestScriptsDAO");
		List<Object> testscriptlist = testScriptsDAO.findByTestInformationId(testInfoId, SalesForceUtil.getSFHandle());
		return testscriptlist;
	}

	public static List<TestInfoResponse> prepareResponseDomainObject(TestInformationDO testInformationDO,
			String testInfoId) {
		List<TestInfoResponse> responseObjList = null;
		List<Object> testScriptsList = getTestScriptsDetails(testInfoId);
		TestInfoResponse testInfoResponse = null;

		if (testScriptsList.size() > 0) {
			responseObjList = new ArrayList<TestInfoResponse>();
			for (int i = 0; i < testScriptsList.size(); i++) {
				TestScriptsDO testScriptsDO = (TestScriptsDO) testScriptsList.get(i);
				if (testInformationDO == null) {
					testInfoResponse = new TestInfoResponse(null, testScriptsDO.getId(), null, null, null,
							testScriptsDO.getTestScritId(), testScriptsDO.getTestSteps(),
							testScriptsDO.getSciptStatus(), null, testScriptsDO.getTestScritId());
				} else {
					testInfoResponse = new TestInfoResponse(testInformationDO.getId(), testScriptsDO.getId(),
							testInformationDO.getApplication(), testInformationDO.getModulename(),
							testInformationDO.getTitle(), testScriptsDO.getTestScritId(), testScriptsDO.getTestSteps(),
							testScriptsDO.getSciptStatus(), null, testScriptsDO.getTestScritId());
				}
				responseObjList.add(testInfoResponse);
			}
		} else {
			if (testInformationDO != null) {
				responseObjList = new ArrayList<TestInfoResponse>();
				testInfoResponse = new TestInfoResponse(testInformationDO.getId(), null,
						testInformationDO.getApplication(), testInformationDO.getModulename(),
						testInformationDO.getTitle(), null, null, null, null,null);
				responseObjList.add(testInfoResponse);
			}
		}
		return responseObjList;
	}

	public static List<TestInfoResponse> getMappingClassNames(TestInformationDO testInformationDO, String testInfoId,
			List<TestInfoResponse> excelTestClassNamesList) {
		List<TestInfoResponse> mappingclassNamesList = new ArrayList<TestInfoResponse>();
		for (Iterator iterator = excelTestClassNamesList.iterator(); iterator.hasNext();) {
			TestInfoResponse excelTestCaseDObj = (TestInfoResponse) iterator.next();
			if (testInformationDO.getApplication().equals(excelTestCaseDObj.getApplication())
					&& testInformationDO.getModulename().equals(excelTestCaseDObj.getModule())
					&& testInformationDO.getTitle().equals(excelTestCaseDObj.getTitle())) {

				List<Object> testScriptsList = getTestScriptsDetails(testInfoId);
				for (int i = 0; i < testScriptsList.size(); i++) {
					TestScriptsDO testScriptsDO = (TestScriptsDO) testScriptsList.get(i);
					TestInfoResponse sTestCaseDObj = new TestInfoResponse(testInformationDO.getId(),
							testScriptsDO.getId(), testInformationDO.getApplication(),
							testInformationDO.getModulename(), testInformationDO.getTitle(),
							testScriptsDO.getTestScritId(), testScriptsDO.getTestScritName(), "status", "path",
							excelTestCaseDObj.getMappingClassName());
					if (testScriptsDO.getTestScritId().equals(excelTestCaseDObj.getTestScriptId())) {
						System.out.println("excelTestCaseDObj getTestScriptId :" + excelTestCaseDObj.getTestScriptId());
						System.out.println(
								"excelTestCaseDObj getTestCase Name :" + excelTestCaseDObj.getMappingClassName());
						mappingclassNamesList.add(sTestCaseDObj);
					} else {
						// failure case
					}
				}
			} else {
				// failure case
			}
		}

		return mappingclassNamesList;
	}

	public static List<Object> findTestInformation(String testinformationid, SFoAuthHandle sfHandle) {

		TestInformationDAO testInformationDAO = new TestInformationDAO();
		List<Object> list = testInformationDAO.findById(testinformationid, sfHandle);
		return list;
	}

	public static String createEmptyMetadataLogId() {
		double val = 0.0;
		// create Metadatalog with empty data
		TestMetadataLogDO testMetadataLogDO = createTestMetadataLog("", val, val, val, val, "");
		TestMetadataLogDAO testMetadataLogDAO = (TestMetadataLogDAO) Factory.getObjectInstance("TestMetadataLogDAO");
		String metadataLogId = testMetadataLogDAO.insert(testMetadataLogDO, SalesForceUtil.getSFHandle(), "");
		return metadataLogId;
	}

	public static TestMetadataLogDO createTestMetadataLog(String metadatalog, Double fails, Double tests, Double sucess,
			Double totalTime, String testInformationId) {

		TestMetadataLogDO metadataLogDO = new TestMetadataLogDO();
		metadataLogDO.setId(metadatalog);
		metadataLogDO.setStatus(Constants.COMPLETED_STATUS);
		metadataLogDO.setTestinformation(testInformationId);
		metadataLogDO.setTotalTests(tests);
		metadataLogDO.setTotalFailures(fails);
		metadataLogDO.setTotalSuccess(sucess);
		metadataLogDO.setTotalTimes(totalTime);

		return metadataLogDO;

	}
}
