package com.salesforce.util;

import java.util.List;

import com.salesforce.domain.TestInfoResponse;
import com.salesforce.excel.FilleExcelWriter;
import com.salesforce.exception.TestException;

public class ExcelUtil {

	public static void readMappingFile(List<TestInfoResponse> initialTestResponseList, String fileName) {
		try {
			FilleExcelWriter.readFileAndUpdateMappingClass(initialTestResponseList,fileName);
		} catch (TestException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
