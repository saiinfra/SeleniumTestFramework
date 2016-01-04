package com.salesforce.util;

import java.io.File;
import java.util.List;

import com.salesforce.domain.GitRepoDO;
import com.salesforce.domain.TestInfoRequest;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.excel.FilleExcelWriter;
import com.salesforce.exception.TestException;
import com.shell.ExecShellScript;

public class ExcelUtil {

	public static List<String> readMappingFile(File mappingFile,List<TestInfoResponse> initialResonseList) {
		List<String> list = null;
		try {
			list = FilleExcelWriter.readFile(mappingFile,initialResonseList);
		} catch (TestException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public static void readEmptyMappingFile(
			List<TestInfoResponse> initialTestResponseList, String fileName,
			TestInfoRequest testInfoRequest) {
		try {
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
			ExecShellScript
					.checkOutMappingFile(mappingFolderName,
							testInfoRequest.getGitRepoURL(),
							Constants.CheckoutFilePath);
			FilleExcelWriter.readFileAndUpdateMappingClass(
					initialTestResponseList, fileName);
		} catch (TestException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void createMappingFileAndCheckIn(String fileName,
			String testInfoId) {
		FilleExcelWriter.createMappingFileAndCheckIn(fileName, testInfoId);
	}

	public static void createTestCaseAndCheckIn(List<String> classList,
			String testInfoId) {
		FilleExcelWriter.createTestCaseAndCheckIn(classList, testInfoId);
	}

	public static void checkout(String gitrepoURL) {

		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);

		RepoUtil.CheckOut(gitRepoDO);

	}

}
