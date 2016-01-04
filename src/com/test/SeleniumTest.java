package com.test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	/*public static void main(String[] args) {

		// Create a new instance of Firefox Browser
		WebDriver driver = new FirefoxDriver();

		// Open the URL in firefox browser
		driver.get("https://www.google.co.in/");

		// Maximize the Browser window
		driver.manage().window().maximize();

		// Get the current page URL and store the value in variable 'str'
		String str = driver.getCurrentUrl();

		// Print the value of variable in the console
		System.out.println("The current URL is " + str);
		driver.quit();
	}
*/
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.google.co.in/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
