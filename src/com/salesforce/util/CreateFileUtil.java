package com.salesforce.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileUtil {
	private static StringBuffer sb = null;

	public static void prepareJavaTestFile(String fileName) {
		try {
			sb = new StringBuffer();
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
			writeEmptyJavaTemplateClass();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeJavaFileName(String javaFileName) {
		sb.append("public class " + javaFileName + " {");
	}

	private static void writeImports() {
		sb.append("package com.test;");
		sb.append("\n\n");
		sb.append("import java.util.concurrent.TimeUnit;");
		sb.append("\n");
		sb.append("import org.junit.After;");
		sb.append("\n");
		sb.append("import org.junit.Before;");
		sb.append("\n");
		sb.append("import org.junit.Test;");
		sb.append("\n");
		sb.append("import org.openqa.selenium.WebDriver;");
		sb.append("\n");
		sb.append("import org.openqa.selenium.firefox.FirefoxDriver;");
		sb.append("\n\n");
	}

	private static void writeEmptyJavaTemplateClass() {
		sb.append("\n");
		sb.append("\tprivate WebDriver driver;");
		sb.append("\n");
		sb.append("\tprivate String baseUrl;");
		sb.append("\n");

		sb.append("\n\t@Before");
		sb.append("\n");
		sb.append("\tpublic void setUp() throws Exception {");
		sb.append("\n");
		sb.append("\t}");
		sb.append("\n");

		sb.append("\n\t@Test");
		sb.append("\n");
		sb.append("\tpublic void test() throws Exception{");
		sb.append("\n");
		sb.append("\t\tdriver = new FirefoxDriver();");
		sb.append("\n");
		sb.append("\t\tdriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);");
		sb.append("\n");
		sb.append("\t\tbaseUrl = \"http://login.salesforce.com/\";");
		sb.append("\n");
		sb.append("\t\tdriver.get(baseUrl);");
		sb.append("\n");
		sb.append("\t}");
		sb.append("\n");

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

	public static void main(String[] args) {
		prepareJavaTestFile("TestFile");
	}
}