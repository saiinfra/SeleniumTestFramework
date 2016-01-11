package com.test;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SalesforceLoginJava {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		baseUrl = "http://login.salesforce.com/";
		driver.get(baseUrl);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
