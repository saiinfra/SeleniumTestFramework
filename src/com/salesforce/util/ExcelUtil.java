package com.salesforce.util;

import java.io.File;
import java.util.List;

import org.eclipse.jgit.api.Git;

import com.salesforce.domain.GitRepoDO;
import com.salesforce.domain.TestInfoRequest;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestResponse;
import com.salesforce.excel.FilleExcelWriter;
import com.salesforce.exception.TestException;
import com.shell.ExecShellScript;

public class ExcelUtil {

	public static List<List<Object>> activityDetailsDO = null;

	public static void readMappingFileAndSyncWithSF(File mappingFile,
			TestResponse tResponse) {
		try {
			FilleExcelWriter.readFile(mappingFile, tResponse);
		} catch (TestException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void readEmptyMappingFile(
			List<TestInfoResponse> initialTestResponseList, String fileName,
			TestInfoRequest testInfoRequest) {
		if (initialTestResponseList == null
				|| initialTestResponseList.isEmpty()) {
			GitRepoDO gitRepoDO = new GitRepoDO(Constants.TempRepoUserName,
					Constants.TempRepoPassword, Constants.TempRepoURL);
			String fileNameWithExt = fileName + Constants.MappingFileType;
			String sourcePath = AppUtil.getCurrentPath();

			File mappingFileWithPath = new File(AppUtil.getCurrentPath()
					+ Constants.DirSeperator + fileNameWithExt);
			// RepoUtil.CheckIn(gitRepoDO, sourcePath, mappingFileWithPath);
		}

		String mappingFolderName = Constants.MappingFolderName;
		ExecShellScript.checkOutMappingFile(mappingFolderName,
				testInfoRequest.getGitRepoURL(), Constants.CheckoutFilePath);
		FilleExcelWriter.readFileAndUpdateMappingClass(initialTestResponseList,
				fileName);
	}

	public static void createMappingFileAndCheckIn(TestResponse tResponse,
			Git git) {
		FilleExcelWriter.createMappingFileAndCheckIn(tResponse, git);
	}

	public static void createMappingFileAndCheckIn(String fileName,
			String testInfoId) {
		FilleExcelWriter.createMappingFileAndCheckIn(fileName, testInfoId);
	}

	public static void updateMappingFileAndCheckIn(TestResponse tResponse,
			Git git) {
		FilleExcelWriter.updateMappingFileAndCheckIn(tResponse, git);
	}

	public static void createTestCaseAndCheckIn(
			List<TestInfoResponse> testResponseList, String fileName) {
		FilleExcelWriter.createTestCaseAndCheckIn(testResponseList, fileName,
				activityDetailsDO);
	}

	public static Git checkout(String gitrepoURL) {

		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);

		Git git = RepoUtil.CheckOut(gitRepoDO);
		return git;
	}

}
