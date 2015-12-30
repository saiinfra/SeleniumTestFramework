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

import com.salesforce.domain.SFDomainUtil;
import com.salesforce.domain.STestCaseDObj;
import com.salesforce.domain.TestInfoResponse;
import com.salesforce.domain.TestInformationDO;
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.ds.TestInformationDAO;
import com.salesforce.exception.TestException;
import com.salesforce.util.AppUtil;
import com.salesforce.util.Constants;
import com.salesforce.util.SFoAuthHandle;

public class FilleExcelWriter {

	private static SFoAuthHandle sfHandle = null;
	// private static String excelFilePath =
	// "/home/infra3/eclipse_workspace/selenium/s1/SeleniumModified/tests/";
	private static String fileName;
	// private static String ext = ".xls";
	private static Workbook workbook;
	private static Sheet sheet;

	public static void main(String[] args) {

		fileName = "00D61000000fBw4";
		init(fileName);
		/*
		 * List<STestCaseDObj> list = readXls(new File(excelFilePath + fileName
		 * + ext)); for (Iterator iterator = list.iterator();
		 * iterator.hasNext();) { STestCaseDObj sTestCaseDObj = (STestCaseDObj)
		 * iterator.next(); System.out.println(sTestCaseDObj.toString()); }
		 */

	}

	public static void init(String fileName) {
		File mappingFile = new File(Constants.CheckoutMappingFilePath + Constants.DirSeperator + fileName);
	}

	public static void writeExcel(List<Object> listBook, String excelFilePath) throws IOException {

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
		cell.setCellValue("ClassName");

		for (Object testScripts : listBook) {
			TestScriptsDO testScriptDO = (TestScriptsDO) testScripts;
			Row row = sheet.createRow(++rowCount);
			writeTescripts(testScriptDO, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	private static void writeTescripts(TestScriptsDO testScripts, Row row) {

		List<Object> testInformationlist = findTestInformation("Constants.TestInformationID", sfHandle);

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
		cell.setCellValue("new");

		cell = row.createCell(6);
		cell.setCellValue("No class Found");

		/*
		 * cell = row.createCell(3); cell.setCellValue(aBook.getPrice());
		 */
	}

	private static List<Object> findTestInformation(String testinformationid, SFoAuthHandle sfHandle) {

		TestInformationDAO testInformationDAO = new TestInformationDAO();
		List<Object> list = testInformationDAO.findById(testinformationid, sfHandle);
		return list;
	}

	public static void updateIfExists(List<TestInfoResponse> initialTestResponseList, String testScriptId, String mappingClassName) throws TestException {
		for (Iterator iterator = initialTestResponseList.iterator(); iterator.hasNext();) {
			TestInfoResponse testInfoResponse = (TestInfoResponse) iterator.next();
			if(testInfoResponse.getTestScriptId().trim().equals(testScriptId.trim())){
				testInfoResponse.setMappingClassName(mappingClassName);
			}
		}
	}

	public static void readFileAndUpdateMappingClass(List<TestInfoResponse> initialTestResponseList, String fileName)
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
			}
			else{
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

	private static void createTestScriptMappingFile(String fileName, String testInfoId) {
		File mappingFile = new File(AppUtil.getCurrentPath(), fileName + Constants.MappingFileType);
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
			writeExcel(testscriptlist, AppUtil.getCurrentPath() + fileName + Constants.MappingFileType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
