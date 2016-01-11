package com.salesforce.template;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jgit.api.Git;

import com.file.FileSearch;
import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoRequest;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestInformationDO;
import com.salesforce.domain.TestResponse;
import com.salesforce.util.AppUtil;
import com.salesforce.util.Constants;
import com.salesforce.util.ExcelUtil;
import com.salesforce.util.RepoClass;

public abstract class TestPreProcessingTemplate {

	public abstract List<TestInfoResponse> doPreProcessing(String inputTokens, TestResponse tResponse);

	private void prepareRequest(String inputTokens, TestResponse tResponse) {
		// read input tokens
		TestInfoRequest testInfoRequest = readInputTokensInto(inputTokens);

		// prepare TestInformation Test case object
		TestInformationDO testInformationDO = SFDomainUtil.getTestAppHeaderDetails(testInfoRequest.getTestInfoId());
		if (testInformationDO == null) {
			testInfoRequest.setGitRepoURL(Constants.GitTestProjectURL);
		} else {
			testInfoRequest.setGitRepoURL(testInformationDO.getExecutionURL());
		}

		// set Org details in Response Object
		tResponse.setOrgId(testInfoRequest.getOrgId());

		// Get test script details from salesforce and prepare initial response
		// objects
		List<TestInfoResponse> initialTestResponseList = null;
		initialTestResponseList = SFDomainUtil.prepareResponseDomainObject(testInformationDO,
				testInfoRequest.getTestInfoId());
		tResponse.setTestInfoResponseList(initialTestResponseList);
		tResponse.setTestInformationDO(testInformationDO);
	}

	public Git checkOutCustomerProject(TestResponse tResponse) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);

		// checkout from git to find whether file exists or not
		Git git = ExcelUtil.checkout(tResponse.getTestInformationDO().getExecutionURL());
		return git;
	}

	private void inspectMappingFile(Git git, TestResponse tResponse) {
		// String mappingFileName = tResponse.getOrgId() +
		// Constants.MappingFileType;
		String mappingFileNameWithExt = tResponse.getOrgId() + Constants.MappingFileType;
		System.out.println(Constants.CheckoutFilePath + Constants.DirSeperator + Constants.MappingFolderName
				+ Constants.DirSeperator + mappingFileNameWithExt);
		String fileFound = FileSearch.getPath(mappingFileNameWithExt);

		if (fileFound.equals("NotFound")) {
			tResponse.setMappingFileExist(false);
			// ExcelUtil.createMappingFileAndCheckIn(testInfoRequest.getOrgId(),
			// testInfoRequest.getTestInfoId());
			ExcelUtil.createMappingFileAndCheckIn(tResponse, git);
		} else {
			// read xls file from checkout folder testcases
			// update response Objects with the data read from xls
			File file = new File(Constants.CheckoutPath + Constants.DirSeperator + mappingFileNameWithExt);
			ExcelUtil.readMappingFileAndSyncWithSF(file, tResponse);
			tResponse.setMappingFileExist(true);
			ExcelUtil.updateMappingFileAndCheckIn(tResponse, git);
		}

	}

	public List<TestInfoResponse> doPreProcessing1(String inputTokens, TestResponse tResponse) {
		prepareRequest(inputTokens, tResponse);
		Git git = checkOutCustomerProject(tResponse);
		inspectMappingFile(git, tResponse);
		if (!tResponse.isMappingFileExist()) {
			// create testcase and checkin
			ExcelUtil.createTestCaseAndCheckIn(tResponse.getTestInfoResponseList());
		} else {

		}
		return tResponse.getTestInfoResponseList();
	}

	private void init() {
		// String inputTokens = "a0361000005ZnOy~00D61000000fBw41~T-0000000001";
		// doPreProcessing1(inputTokens);
	}

	private TestInfoRequest readInputTokensInto(String inputTokens) {
		StringTokenizer st = new StringTokenizer(inputTokens, "~");
		TestInfoRequest testInfoRequest = new TestInfoRequest();

		if (st.hasMoreTokens()) {
			testInfoRequest.setTestInfoId(st.nextToken());
		}
		if (st.hasMoreTokens()) {
			testInfoRequest.setOrgId(st.nextToken());
		}
		if (st.hasMoreTokens()) {
			testInfoRequest.setTestInfoName(st.nextToken());
		}
		return testInfoRequest;
	}

}
