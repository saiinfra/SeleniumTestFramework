package com.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RDAppService {

	// make the constructor private so that this class cannot be
	// instantiated
	private RDAppService() {
		super();
	}

	// create an object of SingleObject
	private static RDAppService instance = null;

	// Get the only object available
	public static RDAppService getInstance() {
		if (instance == null) {
			instance = new RDAppService();
		}
		return instance;
	}

	public static TestMetadataLogDO findTestMetadataLog(String metadataLogId,
			SFoAuthHandle sfHandle) {
		TestMetadataLogDO testMetadataLogDO = null;
		TestMetadataLogDAO metadataLogDAO = new TestMetadataLogDAO();
		try {
			// System.out.println("AOuth Token : "+
			// sfHandle.getEnterpriseConnection().getUserInfo().getProfileId());
			List metadataLogList = metadataLogDAO.findById(metadataLogId,
					sfHandle);

			for (Iterator iterator = metadataLogList.iterator(); iterator
					.hasNext();) {
				testMetadataLogDO = (TestMetadataLogDO) iterator.next();
			}
		} catch (Exception e) {
			throw e;
		} /*
		 * catch (ConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); try { throw e; } catch (ConnectionException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } }
		 */
		return testMetadataLogDO;
	}

	public static void updateTestMetadataLogStatus(
			TestMetadataLogDO testMetadataLogDO, String status, String message,
			SFoAuthHandle sfHandle) {
		TestMetadataLogDAO metadataLogDAO = new TestMetadataLogDAO();
		testMetadataLogDO.setStatus(status);
		testMetadataLogDO.setMessage(message);
		metadataLogDAO.update(testMetadataLogDO, sfHandle);
	}

}
