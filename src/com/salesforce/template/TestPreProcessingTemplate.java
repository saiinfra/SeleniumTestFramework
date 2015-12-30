package com.salesforce.template;

import java.util.List;
import java.util.StringTokenizer;

import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoRequest;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestInformationDO;
import com.salesforce.util.Constants;
import com.salesforce.util.ExcelUtil;
import com.shell.ExecShellScript;

public abstract class TestPreProcessingTemplate {

	public abstract List<TestInfoResponse> doPreProcessing(String inputTokens);

	public List<TestInfoResponse> doPreProcessing1(String inputTokens) {
		TestInfoRequest testInfoRequest = readInputTokensInto(inputTokens);
		TestInformationDO testInformationDO = SFDomainUtil.getTestAppHeaderDetails(testInfoRequest.getTestInfoId());
		testInfoRequest.setGitRepoURL(testInformationDO.getExecutionURL());
		String mappingFolderName = Constants.MappingFolderName;
		List<TestInfoResponse> initialTestResponseList=null;
		List<Object> list = SFDomainUtil.getTestScriptsDetails(testInfoRequest.getTestInfoId());
		if (list.isEmpty() || (list == null)) {
			initialTestResponseList = SFDomainUtil.prepareResponseDomainObject(testInformationDO,
					testInfoRequest.getTestInfoId());
		} else {
			initialTestResponseList = SFDomainUtil.prepareResponseDomainObject(testInformationDO,
					testInfoRequest.getTestInfoId());

			String mappingFileName = testInfoRequest.getOrgId() + Constants.MappingFileType;
			ExecShellScript.checkOutMappingFile(mappingFolderName, testInfoRequest.getGitRepoURL(),
					Constants.CheckoutFilePath);

			ExcelUtil.readMappingFile(initialTestResponseList, mappingFileName);
			// Do Checkout of complete source files
			// as there is no explicit way of checking out individual files
			ExecShellScript.checkOutSrc(testInformationDO.getExecutionURL());
		}
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
