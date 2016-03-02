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
import com.salesforce.domain.TestScriptsDO;
import com.salesforce.ds.TestScriptsDAO;

public class CreateFileUtil {
	private static StringBuffer sb;

	public static void prepareJavaTestFile(String fileName,
			TestInfoResponse testInfoResponse,
			List<List<Object>> activityDetailsDO) {
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
			List<List<Object>> activityDetailsDO) {

		sb.append("\n\t@Test");
		sb.append("\n");
		/*
		 * sb.append("\tpublic void " + testInfoResponse.getTestScriptName() +
		 * "()" + " throws Exception{");
		 */
		sb.append("\tpublic void test () throws Exception{");
		sb.append("\n");

		if (testInfoResponse.getTestScriptType().equals("Login")
				&& testInfoResponse.getLoginType().equals(
						"Production/Developer")) {

			String URL = "https://login.salesforce.com";
			String baseUrl = "\"" + URL + "\"";

			sb.append("\t\tdriver.get(" + baseUrl + "  );");
			sb.append("\n");

			for (Iterator iterator = activityDetailsDO.iterator(); iterator
					.hasNext();) {
				List<Object> list = (List<Object>) iterator.next();

				for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
					ActivityDetailsDO object = (ActivityDetailsDO) iterator2
							.next();
					String actionType = object.getActionType();
					System.out.println("Action Type" + actionType);
					System.out.println("Test Script ID in for Loop "
							+ object.getTestScriptDetails());

					if (actionType.equals("Text")) {

						if (object.getComponentName().equalsIgnoreCase(
								"username")) {
							String userName = "username";
							String id = "\"" + userName + "\"";
							String sendkeys = "\"" + object.getData() + "\"";

							sb.append("\t\tdriver.findElement(By.id(" + id
									+ ")).clear();");
							sb.append("\n");
							sb.append("\t\tdriver.findElement(By.id(" + id
									+ ")).sendKeys(" + sendkeys + ");");
							sb.append("\n");
						}

						if (object.getComponentName().equalsIgnoreCase(
								"password")) {
							String password = "password";
							String id = "\"" + password + "\"";
							String sendkeys = "\"" + object.getData() + "\"";

							sb.append("\t\tdriver.findElement(By.id(" + id
									+ ")).clear();");
							sb.append("\n");
							sb.append("\t\tdriver.findElement(By.id(" + id
									+ ")).sendKeys(" + sendkeys + ");");
							sb.append("\n");
						}

					}

				}

			}

			String label = "Login";

			String click = "\"" + label + "\"";
			sb.append("\t\tdriver.findElement(By.id(" + click + ")).click();");
			sb.append("\n");

		}

		else if (testInfoResponse.getTestScriptType().equals("Account")) {

			String URL = "https://login.salesforce.com";
			String baseUrl = "\"" + URL + "\"";

			sb.append("\t\tdriver.get(" + baseUrl + "  );");
			sb.append("\n");

			for (Iterator iterator = activityDetailsDO.iterator(); iterator
					.hasNext();) {
				List<Object> list = (List<Object>) iterator.next();

				for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
					ActivityDetailsDO object = (ActivityDetailsDO) iterator2
							.next();
					String actionType = object.getActionType();
					System.out.println("Action Type" + actionType);

					TestScriptsDAO testScriptsDAO = new TestScriptsDAO();
					List<Object> listTestScript = testScriptsDAO
							.findByScriptId(object.getTestScriptDetails(),
									SalesForceUtil.getSFHandle());

					if (actionType.equals("Text")
							&& object.getComponentName().equalsIgnoreCase(
									"username")) {

						String userName = "username";
						String id = "\"" + userName + "\"";
						String sendkeys = "\"" + object.getData() + "\"";

						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).clear();");
						sb.append("\n");
						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).sendKeys(" + sendkeys + ");");
						sb.append("\n");
					}

					if (actionType.equals("Text")
							&& object.getComponentName().equalsIgnoreCase(
									"password")) {
						String password = "password";
						String id = "\"" + password + "\"";
						String sendkeys = "\"" + object.getData() + "\"";

						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).clear();");
						sb.append("\n");
						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).sendKeys(" + sendkeys + ");");
						sb.append("\n");
					}

					for (Iterator iterator3 = listTestScript.iterator(); iterator3
							.hasNext();) {
						TestScriptsDO testScriptsDO = (TestScriptsDO) iterator3
								.next();
						if (testScriptsDO.getTestScriptType().equals("Login")
								&& object.getComponentName().equalsIgnoreCase(
										"password")) {

							String label = "Login";

							String click = "\"" + label + "\"";
							sb.append("\t\tdriver.findElement(By.id(" + click
									+ ")).click();");
							sb.append("\n");

							String tsdLabel1 = "tsidLabel";
							String tsdLabel = "\"" + tsdLabel1 + "\"";

							String sales1 = "Sales";
							String sales = "\"" + sales1 + "\"";

							String accounts1 = "Accounts";
							String accounts = "\"" + accounts1 + "\"";

							String new1 = "new";
							String new2 = "\"" + new1 + "\"";

							sb.append("\t\tdriver.findElement(By.id("
									+ tsdLabel + ")).click();");
							sb.append("\n");
							sb.append("\t\tdriver.findElement(By.linkText("
									+ sales + ")).click();");
							sb.append("\n");
							sb.append("\t\tdriver.findElement(By.linkText("
									+ accounts + ")).click();");
							sb.append("\n");
							sb.append("\t\tdriver.findElement(By.name(" + new2
									+ ")).click();");
							sb.append("\n");

						}
					}

					if (actionType.equals("Text")
							&& object.getComponentName().equalsIgnoreCase(
									"Account Name")) {
						String acc = "acc2";
						String id = "\"" + acc + "\"";
						String sendkeys = "\"" + object.getData() + "\"";

						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).clear();");
						sb.append("\n");
						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).sendKeys(" + sendkeys + ");");
						sb.append("\n");
					}
					if (actionType.equals("Text")
							&& object.getComponentName().equalsIgnoreCase(
									"Annual Revenue")) {
						String acc = "acc8";
						String id = "\"" + acc + "\"";
						String sendkeys = "\"" + object.getData() + "\"";

						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).clear();");
						sb.append("\n");
						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).sendKeys(" + sendkeys + ");");
						sb.append("\n");
					}
					if (actionType.equals("Text")
							&& object.getComponentName().equalsIgnoreCase(
									"Employees")) {
						String acc = "acc15";
						String id = "\"" + acc + "\"";
						String sendkeys = "\"" + object.getData() + "\"";

						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).clear();");
						sb.append("\n");
						sb.append("\t\tdriver.findElement(By.id(" + id
								+ ")).sendKeys(" + sendkeys + ");");
						sb.append("\n");
					}

				}

			}

			String label = "save";

			String click = "\"" + label + "\"";
			sb.append("\t\tdriver.findElement(By.name(" + click + ")).click();");
			sb.append("\n");
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