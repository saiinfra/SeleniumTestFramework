package com.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumCITest {
    
    static WebDriver driver;
     
    @Before
    public void setup() {
         
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                
    }
     
    @Test
    public void successfulLoginLogout() {
         
        driver.get("http://parabank.parasoft.com");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
        driver.findElement(By.linkText("Log Out")).click();
    }
     
    @After
    public void teardown() {
        driver.quit();
    }   
}
