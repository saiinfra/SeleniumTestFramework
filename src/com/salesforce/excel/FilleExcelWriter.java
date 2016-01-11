package com.salesforce.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.jgit.api.Git;

import com.salesforce.domain.GitRepoDO;
import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestInformationDO;
import com.salesforce.domain.TestResponse;
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.ds.TestInformationDAO;
import com.salesforce.exception.TestException;
import com.salesforce.util.AppUtil;
import com.salesforce.util.Constants;
import com.salesforce.util.CreateFileUtil;
import com.salesforce.util.RepoUtil;
import com.salesforce.util.SFoAuthHandle;
import com.salesforce.util.SalesForceUtil;

public class FilleExcelWriter {

	private static SFoAuthHandle sfHandle = null;
	// private static String excelFilePath =
	// "/home/infra3/eclipse_workspace/selenium/s1/SeleniumModified/tests/";
	private static String fileName;
	// private static String ext = ".xls";
	private static Workbook workbook;
	private static Sheet sheet;

	public static void main(String[] args) {
		fileName = "00D61000000fBw2";
		String testInfoId = "a0361000005aMqp";
		createMappingFileAndCheckIn(fileName, testInfoId);
	}

	public static void createMappingFileAndCheckIn(String fileName, String testInfoId) {
		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);
		String fileNameWithExt = fileName + Constants.MappingFileType;

		createTestScriptMappingFile(AppUtil.getCurrentPath(), fileNameWithExt, testInfoId);
		// String fileNameWithPathExt = AppUtil.getCurrentPath() +
		// Constants.DirSeperator+fileNameWithExt;
		String sourcePath = AppUtil.getCurrentPath();

		File mappingFileWithPath = new File(AppUtil.getCurrentPath() + Constants.DirSeperator + fileNameWithExt);
		RepoUtil.CheckIn(gitRepoDO, sourcePath, mappingFileWithPath);
	}

	public static void createMappingFileAndCheckIn(TestResponse tResponse, Git git) {
		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);
		String fileNameWithExt = fileName + Constants.MappingFileType;
		try {
			writeExcelHeader(tResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeSciptsSteps(tResponse);
		// String fileNameWithPathExt = AppUtil.getCurrentPath() +
		// Constants.DirSeperator+fileNameWithExt;
		String sourcePath = AppUtil.getCurrentPath();

		File mappingFileWithPath = new File(AppUtil.getCurrentPath() + Constants.DirSeperator + fileNameWithExt);
		RepoUtil.CheckIn(git, gitRepoDO);
	}

	public static void updateMappingFileAndCheckIn(TestResponse tResponse, Git git) {
		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";
		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);
		writeExtraRows(tResponse);
		RepoUtil.CheckInChechputFolder(git, gitRepoDO);
	}

	public static void createTestCaseAndCheckIn(List<TestInfoResponse> testResponseList) {
		String userName = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		GitRepoDO gitRepoDO = new GitRepoDO(userName, password, url);
		File mappingFileWithPath = null;

		for (Iterator<TestInfoResponse> iterator = testResponseList.iterator(); iterator.hasNext();) {
			TestInfoResponse testResponse = (TestInfoResponse) iterator.next();
			String className = testResponse.getMappingClassName();
			String ext = ".java";
			if (!testResponse.isExcelRecordExists()) {
				createTestCaseFile(AppUtil.getCurrentPath(), className);
				String sourcePath = AppUtil.getCurrentPath();
				mappingFileWithPath = new File(AppUtil.getCurrentPath() + Constants.DirSeperator + className + ext);
				RepoUtil.CheckInSrc(gitRepoDO, sourcePath, mappingFileWithPath);
			}
		}
	}

	public static void writeExcel(List<Object> listBook, String excelFilePath, String testInfoId) throws IOException {

		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet();

		int rowCount = 0;
		if (listBook != null) {
			for (Object testScripts : listBook) {
				TestScriptsDO testScriptDO = (TestScriptsDO) testScripts;
				Row row = sheet.createRow(++rowCount);
				writeTescripts(testScriptDO, row, testInfoId);
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	public static void writeExtraRows(TestResponse tResponse) {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;

		int rows = 0;
		File file = new File(
				Constants.MappingFilePath + Constants.DirSeperator + tResponse.getOrgId() + Constants.MappingFileType);

		try {
			// Get the workbook instance for XLS file
			workbook = new HSSFWorkbook(new FileInputStream(file));
			// Get first sheet from the workbook
			sheet = workbook.getSheetAt(0);
			rows = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Row row1 = sheet.createRow(rows);
		List<TestInfoResponse> testInfoResponseList = tResponse.getTestInfoResponseList();

		int currPointer = 0;

		for (Iterator iterator = testInfoResponseList.iterator(); iterator.hasNext();) {
			currPointer++;

			TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();

			Cell cell = row1.createCell(0);
			cell.setCellValue(testInfoResponse.getApplication());
			cell = row1.createCell(1);
			cell.setCellValue(testInfoResponse.getModule());
			cell = row1.createCell(2);
			cell.setCellValue(testInfoResponse.getTitle());
			cell = row1.createCell(3);
			cell.setCellValue(testInfoResponse.getTestScriptId());
			cell = row1.createCell(4);
			cell.setCellValue(testInfoResponse.getTestScriptName());
			cell = row1.createCell(5);
			cell.setCellValue(testInfoResponse.getStatus());
			cell = row1.createCell(6);
			cell.setCellValue(testInfoResponse.getPath());
			cell = row1.createCell(7);
			cell.setCellValue(testInfoResponse.getMappingClassName());
			String path = Constants.MappingFilePath + Constants.DirSeperator + tResponse.getOrgId()
					+ Constants.MappingFileType;

			try (FileOutputStream outputStream = new FileOutputStream(path)) {
				workbook.write(outputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void writeTescripts(TestResponse tResponse) {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet();
		int rows = 0;
		File file = new File(
				Constants.MappingFilePath + Constants.DirSeperator + tResponse.getOrgId() + Constants.MappingFileType);

		try {
			// Get the workbook instance for XLS file
			workbook = new HSSFWorkbook(new FileInputStream(file));
			// Get first sheet from the workbook
			sheet = workbook.getSheetAt(0);
			rows = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Row row1 = sheet.createRow(rows);

		int rowCount = 1;
		for (Iterator iterator = tResponse.getTestInfoResponseList().iterator(); iterator.hasNext();) {
			TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
			Cell cell = row1.createCell(0);
			cell.setCellValue(testInfoResponse.getApplication());
			cell = row1.createCell(1);
			cell.setCellValue(testInfoResponse.getModule());
			cell = row1.createCell(2);
			cell.setCellValue(testInfoResponse.getTitle());

			cell = row1.createCell(3);
			cell.setCellValue(testInfoResponse.getTestScriptId());

			cell = row1.createCell(4);
			cell.setCellValue(testInfoResponse.getTestScriptName());

			cell = row1.createCell(5);
			cell.setCellValue(testInfoResponse.getStatus());

			cell = row1.createCell(6);
			cell.setCellValue("com.test");

			cell = row1.createCell(7);
			cell.setCellValue(testInfoResponse.getMappingClassName());

			String path = Constants.MappingFilePath + Constants.DirSeperator + tResponse.getOrgId()
					+ Constants.MappingFileType;

			try (FileOutputStream outputStream = new FileOutputStream(path)) {
				workbook.write(outputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void writeExcelHeader(TestResponse tResponse) throws IOException {

		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet();

		int rowCount = 0;

		// this row for heading
		Row row1 = sheet.createRow(rowCount);
		Cell cell = row1.createCell(0);
		cell.setCellValue("Application");
		cell = row1.createCell(1);
		cell.setCellValue("Module");
		cell = row1.createCell(2);
		cell.setCellValue("Title");
		cell = row1.createCell(3);
		cell.setCellValue("TestScriptId");
		cell = row1.createCell(4);
		cell.setCellValue("TestScriptName");
		cell = row1.createCell(5);
		cell.setCellValue("Status");
		cell = row1.createCell(6);
		cell.setCellValue("ClassPath");
		cell = row1.createCell(7);
		cell.setCellValue("ClassName");

		String path = Constants.MappingFilePath + Constants.DirSeperator + tResponse.getOrgId()
				+ Constants.MappingFileType;

		try (FileOutputStream outputStream = new FileOutputStream(path)) {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeTescripts(TestScriptsDO testScripts, Row row, String testInfoId) {

		List<Object> testInformationlist = null;

		testInformationlist = findTestInformation(testInfoId, SalesForceUtil.getSFHandle());

		for (int i = 0; i < testInformationlist.size(); i++) {

			TestInformationDO t = (TestInformationDO) testInformationlist.get(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(t.getApplication());
			cell = row.createCell(1);
			cell.setCellValue(t.getModulename());
			cell = row.createCell(2);
			cell.setCellValue(t.getTitle());

		}

		Cell cell = row.createCell(3);
		cell.setCellValue(testScripts.getTestScritId());

		cell = row.createCell(4);
		cell.setCellValue(testScripts.getTestSteps());

		cell = row.createCell(5);
		cell.setCellValue(testScripts.getSciptStatus());

		cell = row.createCell(6);
		cell.setCellValue("com.test");

		cell = row.createCell(7);
		cell.setCellValue(testScripts.getTestScritName());

		/*
		 * cell = row.createCell(3); cell.setCellValue(aBook.getPrice());
		 */
	}

	private static List<Object> findTestInformation(String testinformationid, SFoAuthHandle sfHandle) {

		TestInformationDAO testInformationDAO = new TestInformationDAO();
		List<Object> list = testInformationDAO.findById(testinformationid, sfHandle);
		return list;
	}

	public static void updateIfExists(List<TestInfoResponse> initialTestResponseList, String testScriptId,
			String mappingClassName) throws TestException {
		for (Iterator iterator = initialTestResponseList.iterator(); iterator.hasNext();) {
			TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
			String str = testInfoResponse.getTestScriptId();
			if (testInfoResponse.getTestScriptId().trim().equals(testScriptId.trim())) {
				testInfoResponse.setMappingClassName(mappingClassName);
			}
		}
	}

	public static boolean doesTestCaseExists(List<TestInfoResponse> initialTestResponseList, String testScriptId)
			throws TestException {
		for (Iterator iterator = initialTestResponseList.iterator(); iterator.hasNext();) {
			TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
			String str = testInfoResponse.getTestScriptId();
			if (testInfoResponse.getTestScriptId().trim().equals(testScriptId.trim())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static void doesScriptTestCaseExist(List<TestInfoResponse> initialTestResponseList, String fileName)
			throws TestException {
		// List<TestInfoResponse> dObjList = null;
		File file = new File(Constants.CheckoutPath + Constants.DirSeperator + fileName);

		try {
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell;
			Row row;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			TestInfoResponse dObj;
			if (rowIterator.hasNext()) {
				while (rowIterator.hasNext()) {
					row = rowIterator.next();

					if (row.getRowNum() == 0) {
						continue; // just skip the rows if row number is 0
					}
					dObj = new TestInfoResponse();
					// application
					cell = row.getCell(0);
					if (cell == null) {
						// do nothing
					}
					String application = cell.getStringCellValue();

					cell = row.getCell(1);
					String module = cell.getStringCellValue();

					cell = row.getCell(2);
					String title = cell.getStringCellValue();

					cell = row.getCell(3);
					String testScriptId = cell.getStringCellValue();

					cell = row.getCell(4);
					String scriptStepName = cell.getStringCellValue();

					cell = row.getCell(5);
					String status = cell.getStringCellValue();

					cell = row.getCell(6);
					String path = cell.getStringCellValue();

					cell = row.getCell(7);
					String mappingClassName = cell.getStringCellValue();
					updateIfExists(initialTestResponseList, testScriptId, mappingClassName);
				}
			} else {
				// no rows in excel
			}
		} catch (FileNotFoundException e) {
			try {
				throw new TestException("FileNotFound");
			} catch (TestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e) {
			try {
				throw new TestException("IOException");
			} catch (TestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public static List<TestInfoResponse> readFile(File mappingFile, TestResponse tResponse) throws TestException {
		List<String> dObjList = new ArrayList<String>();
		List<TestInfoResponse> tInfoResList = new ArrayList<TestInfoResponse>();
		List<TestInfoResponse> intialResonseList = tResponse.getTestInfoResponseList();
		int count = 1;
		try {
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(mappingFile));
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell;
			Row row;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			TestInfoResponse dObj;
			if (rowIterator.hasNext()) {
				while (rowIterator.hasNext()) {
					count++;
					row = rowIterator.next();

					if (row.getRowNum() == 0) {
						continue; // just skip the rows if row number is 0
					}
					dObj = new TestInfoResponse();
					// application
					cell = row.getCell(0);
					if (cell == null) {
						// do nothing
					}
					String application = cell.getStringCellValue();
					dObj.setApplication(application);

					cell = row.getCell(1);
					String module = cell.getStringCellValue();
					dObj.setModule(module);

					cell = row.getCell(2);
					String title = cell.getStringCellValue();
					dObj.setTitle(title);

					cell = row.getCell(3);
					String testScriptId = cell.getStringCellValue();
					dObj.setTestScriptId(testScriptId);

					cell = row.getCell(4);
					String scriptStepName = cell.getStringCellValue();
					dObj.setTestScriptName(scriptStepName);

					cell = row.getCell(5);
					String status = cell.getStringCellValue();
					dObj.setStatus(status);

					cell = row.getCell(6);
					String path = cell.getStringCellValue();
					dObj.setPath(path);

					cell = row.getCell(7);
					String mappingClassName = cell.getStringCellValue();
					dObj.setMappingClassName(mappingClassName);
					tInfoResList.add(dObj);
					for (Iterator iterator = intialResonseList.iterator(); iterator.hasNext();) {
						TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
						if ((testInfoResponse.getApplication().equals(application))
								&& (testInfoResponse.getModule().equals(module))
								&& (testInfoResponse.getTitle().equals(title))
								&& (testInfoResponse.getTestScriptId().equals(testScriptId))) {
							testInfoResponse.setPath(path);
							testInfoResponse.setMappingClassName(mappingClassName);
							testInfoResponse.setStatus(Constants.ReadyForExecution);
							testInfoResponse.setExcelRecordExists(true);
						}

					}

					dObjList.add(mappingClassName);

				}
			} else {
				// no rows in excel
			}
			if (count > 1) {
				if (tResponse.getTestInfoResponseList().size() > count) {

				}
			}
		} catch (FileNotFoundException e) {
			try {
				throw new TestException("FileNotFound");
			} catch (TestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e) {
			try {
				throw new TestException("IOException");
			} catch (TestException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return tInfoResList;
	}

	private static void writeSciptsSteps(TestResponse tResponse) {
		File mappingFile = new File(Constants.MappingFilePath, tResponse.getOrgId() + Constants.MappingFileType);
		if (!mappingFile.exists()) {
			try {
				mappingFile.createNewFile();
				System.out.println("created");
				writeTescripts(tResponse);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			writeTescripts(tResponse);
		}

	}

	private static void createTestScriptMappingFile(String targetPath, String fileName, String testInfoId) {
		File mappingFile = new File(targetPath, fileName);
		if (!mappingFile.exists()) {
			try {
				mappingFile.createNewFile();
				System.out.println("created");
				// checkin into git repo

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// get testscript names
		List<Object> testscriptlist = SFDomainUtil.getTestScriptsDetails(testInfoId);

		// Write Into Excel
		try {
			writeExcel(testscriptlist, targetPath + Constants.DirSeperator + fileName, testInfoId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createTestCaseFile(String targetPath, String className) {
		File mappingFile = new File(targetPath, className);
		if (!mappingFile.exists()) {
			try {
				CreateFileUtil.prepareJavaTestFile(className);
				System.out.println(className + " java file created");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void createTestCaseFile(String targetPath, String className, String testInfoId) {

		File mappingFile = new File(targetPath, className);
		if (!mappingFile.exists()) {
			try {
				CreateFileUtil.prepareJavaTestFile(className);

				// mappingFile.createNewFile();
				System.out.println("created");
				// checkin into git repo

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// get testscript names
		// List<Object> testscriptlist =
		// SFDomainUtil.getTestScriptsDetails(testInfoId);

		// Write Into Java Test case
		// to be done

	}

	public static void readFileAndUpdateMappingClass(List<TestInfoResponse> initialTestResponseList, String fileName2) {
		// TODO Auto-generated method stub

	}
}