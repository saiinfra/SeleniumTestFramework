package com.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SalesforceTest {
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
  public void testEee() throws Exception {
    driver.get(baseUrl + "/");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("sri.server@gmail.com");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("infrascape3");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.id("Login")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.linkText("Releases")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.name("new")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.id("Name")).clear();
    driver.findElement(By.id("Name")).sendKeys("dasdasda");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.cssSelector("#bottomButtonRow > input[name=\"save\"]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=umps|AP2$00D280000015PQN_00528000001JmlN_02_f71c18c4-f35f-47cb-92a3-27e00a282324|4| | ]]
    driver.findElement(By.name("asa__activate")).click();
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