package com.salesforce.template;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.filefilter.FileFileFilter;

import com.file.FileSearch;
import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoRequest;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestInformationDO;
import com.salesforce.util.AppUtil;
import com.salesforce.util.Constants;
import com.salesforce.util.ExcelUtil;
import com.shell.ExecShellScript;

public abstract class TestPreProcessingTemplate {

	public abstract List<TestInfoResponse> doPreProcessing(String inputTokens);

	public List<TestInfoResponse> doPreProcessing1(String inputTokens) {
		TestInfoRequest testInfoRequest = readInputTokensInto(inputTokens);
		TestInformationDO testInformationDO = SFDomainUtil
				.getTestAppHeaderDetails(testInfoRequest.getTestInfoId());
		if (testInformationDO == null) {
			testInfoRequest.setGitRepoURL(Constants.GitTestProjectURL);
		} else {
			testInfoRequest.setGitRepoURL(testInformationDO.getExecutionURL());
		}
		String mappingFileName = testInfoRequest.getOrgId()
				+ Constants.MappingFileType;

		// read xls file from checkout folder testcases
		String fileNameWithExt = testInfoRequest.getOrgId()
				+ Constants.MappingFileType;

		File mappingFileWithPath = new File(AppUtil.getCurrentPath()
				+ Constants.DirSeperator + fileNameWithExt);

		List<TestInfoResponse> initialTestResponseList = null;

		initialTestResponseList = SFDomainUtil.prepareResponseDomainObject(
				testInformationDO, testInfoRequest.getTestInfoId());

		// checkout from git to find whether file exists or not

		// ExcelUtil.checkout(testInformationDO.getExecutionURL());
		System.out.println(Constants.CheckoutFilePath + Constants.DirSeperator
				+ Constants.MappingFolderName + Constants.DirSeperator
				+ fileNameWithExt);
		String fileFound = FileSearch.getPath(fileNameWithExt);

		if (fileFound.equals("NotFound")) {
			ExcelUtil.createMappingFileAndCheckIn(testInfoRequest.getOrgId(),
					testInfoRequest.getTestInfoId());
		}

		File file = new File(Constants.CheckoutPath + Constants.DirSeperator
				+ fileNameWithExt);
		List<String> classList = ExcelUtil.readMappingFile(file,
				initialTestResponseList);

		System.out.println("Total class list" + classList.size());

		// create testcase and checkin
		ExcelUtil.createTestCaseAndCheckIn(classList,
				testInfoRequest.getTestInfoId());

		// ExcelUtil.readEmptyMappingFile(initialTestResponseList,
		// mappingFileName, testInfoRequest);
		// Do Checkout of complete source files
		// as there is no explicit way of checking out individual files

		return initialTestResponseList;
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
