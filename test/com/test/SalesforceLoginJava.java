package com.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SalesforceLoginJava {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://login.salesforce.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testSalesforceLoginJava() throws Exception {
		driver.get(baseUrl + "/");
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// name=umps||2| | ]]
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("ikhan@infrascape.com");
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// name=umps||2| | ]]
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("infrascape4");
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// name=umps||2| | ]]
		driver.findElement(By.id("Login")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
		// name=umps|NA16$00Dj0000001tsUf_005j000000BV6Vf_02_9b0616ea-20bd-4954-8356-542a77f0bbac|2|
		// | ]]
		driver.findElement(By.linkText("infrasai")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}