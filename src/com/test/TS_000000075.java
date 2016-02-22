package com.test;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;


public class TS_000000075 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

@Before
public  void setUp() throws Exception {
	driver = new FirefoxDriver();

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test () throws Exception{
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
