package com.salesforce.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.salesforce.domain.ActivityDetailsDO;
import com.salesforce.domain.TestInfoResponse;

public class CreateFileUtil {
	private static StringBuffer sb;

	public static void prepareJavaTestFile(String fileName,
			TestInfoResponse testInfoResponse, List<Object> activityDetailsDO) {
		try {
			System.out.println("Activity Details Data" + activityDetailsDO);

			File file = new File(AppUtil.getCurrentPath()
					+ Constants.DirSeperator + fileName + ".java");
			System.out.println(file.getPath());
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
			}
			writeImports();
			writeJavaFileName(fileName);
			InitialiseJavaVariables();
			setup();
			writeTestScript(testInfoResponse, activityDetailsDO);
			quit();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
			sb = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeImports() {
		sb = new StringBuffer();
		sb.append("package com.test;");
		sb.append("\n\n");

		sb.append("import java.util.concurrent.TimeUnit;");
		sb.append("\n");
		sb.append("import org.junit.Test;");
		sb.append("\n");
		sb.append("import org.junit.Before;");
		sb.append("\n");
		sb.append("import org.junit.After;");
		sb.append("\n");
		sb.append("import org.openqa.selenium.*;");
		sb.append("\n");
		sb.append("import org.openqa.selenium.firefox.*;");
		sb.append("\n");

		sb.append("\n\n");
	}

	private static void writeJavaFileName(String javaFileName) {
		sb.append("public class " + javaFileName + " {");
		sb.append("\n");
	}

	private static void InitialiseJavaVariables() {

		sb.append("\tprivate WebDriver driver;");
		sb.append("\n");

		sb.append("\tprivate String baseUrl;");
		sb.append("\n");

		sb.append("\tprivate boolean acceptNextAlert = true;");
		sb.append("\n");

		sb.append("\tprivate StringBuffer verificationErrors = new StringBuffer();");
		sb.append("\n\n");

	}

	private static void setup() {
		sb.append("@Before");
		sb.append("\n");
		sb.append("public  void setUp() throws Exception {");
		sb.append("\n");
		sb.append("\tdriver = new FirefoxDriver();");
		sb.append("\n");

		sb.append("\n");
		sb.append("\tdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);");
		sb.append("\n");
		sb.append("\t}");
		sb.append("\n");
	}

	private static void writeTestScript(TestInfoResponse testInfoResponse,
			List<Object> activityDetailsDO) {
		sb.append("\n\t@Test");
		sb.append("\n");
		/*
		 * sb.append("\tpublic void " + testInfoResponse.getTestScriptName() +
		 * "()" + " throws Exception{");
		 */
		sb.append("\tpublic void test () throws Exception{");
		sb.append("\n");

		for (Iterator iterator = activityDetailsDO.iterator(); iterator
				.hasNext();) {
			ActivityDetailsDO object = (ActivityDetailsDO) iterator.next();
			String actionType = object.getActionType();
			System.out.println("Action Type" + actionType);
			String elementLocationType = object.getElementLocationType();
			System.out.println("elementLocationType " + elementLocationType);

			if (actionType.equals("OpenURL")) {
				String baseUrl = "\"" + object.getData() + "\"";

				sb.append("\t\tdriver.get(" + baseUrl + "  );");
				sb.append("\n");
			}

			if (actionType.equals("EnterText")
					&& elementLocationType.equals("id")) {
				String id = "\"" + object.getComponentName() + "\"";
				String sendkeys = "\"" + object.getData() + "\"";

				sb.append("\t\tdriver.findElement(By.id(" + id + ")).clear();");
				sb.append("\n");
				sb.append("\t\tdriver.findElement(By.id(" + id + ")).sendKeys("
						+ sendkeys + ");");
				sb.append("\n");
			}
			if (actionType.equals("ButtonClick")
					&& elementLocationType.equals("id")) {
				String click = "\"" + object.getComponentName() + "\"";
				sb.append("\t\tdriver.findElement(By.id(" + click
						+ ")).click();");
				sb.append("\n");
			} else {
				System.out.println("Not");
			}

		}
		sb.append("\t}");
		sb.append("\n");

	}

	public static void quit() {

		sb.append("\n");
		sb.append("\t@After");
		sb.append("\n");
		sb.append("\tpublic void tearDown() throws Exception {");
		sb.append("\n");
		sb.append("\t\tdriver.quit();");
		sb.append("\n");
		sb.append("\t}");
		sb.append("\n");
		sb.append("}");
		sb.append("\n");

	}
}
