package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class ActivityLoggerSeleniumTest {
  @Test
  public void f() {
	  // define the chrome driver
	  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
	  // define the drive instance
	  WebDriver driver = new ChromeDriver();
	  
	  //routes
	  String home = "http://localhost:8080/maven.productactive/HomeServlet";
	  String login = "http://localhost:8080/maven.productactive/LoginServlet";
	  String dashboard = "http://localhost:8080/maven.productactive/ActivityLoggerServlet/dashboard";
	  String addLog = "http://localhost:8080/maven.productactive/AddActivityLoggerServlet";
	  String editLog;
	  
	  String currenturl;
	  
	  // 1. Home page
	  driver.get(home);
	  
	  currenturl = driver.getCurrentUrl();
	  if(currenturl == home) {
		  System.out.println("1. Redirected to home page (pass)");
	  }
	  else {
		  System.out.println("1. Page is not redirected correctly (fail)");
	  }
	  
	  driver.findElement(By.linkText ("Login")) .click();
	  
	  //2. login page
	  if(driver.getCurrentUrl() == login) {
		  System.out.println("2. Redirected to login page (pass)");
	  }
	  else {
		  System.out.println("2. Page is not redirected correctly (fail)");
	  }
	  
	  driver.findElement(By.name("username")).sendKeys("Tianboh");
	  driver.findElement(By.name("password")).sendKeys("123456789");
	  System.out.println("User information filled in.");
	  
	  driver.findElement(By.className("loginButton")).click();
	  System.out.println("Login Submit");
	  
	  //3. home page
	  if(driver.getCurrentUrl() == home) {
		  System.out.println("3. Redirected to home page (pass)");
	  }
	  else {
		  System.out.println("3. Page is not redirected correctly (fail)");
	  }
	  
	  driver.findElement(By.linkText ("Activity Logger")) .click();
	  
	  //4. activity logger dashboard
	  if(driver.getCurrentUrl() == dashboard) {
		  System.out.println("4. Redirected to activity logger dashboard page (pass)");
	  }
	  else {
		  System.out.println("4. Page is not redirected correctly (fail)");
	  }
	  
	  driver.findElement(By.linkText ("Log New Activity")) .click();
	  
	  //5. add new log
	  if(driver.getCurrentUrl() == addLog) {
		  System.out.println("5. Redirected to add activity logger page (pass)");
	  }
	  else {
		  System.out.println("5. Page is not redirected correctly (fail) ");
	  }
	  driver.findElement(By.name("activity_name")).sendKeys("History");
	  driver.findElement(By.name("activity_description")).sendKeys("practice paper");
	  
	  WebElement dateBoxStart = driver.findElement(By.xpath("//*[@id=\"activity_start\"]"));
	  WebElement dateBoxEnd = driver.findElement(By.xpath("//*[@id=\"activity_end\"]"));
	  dateBoxStart.sendKeys("01112022");
	  dateBoxStart.sendKeys(Keys.TAB);
	  dateBoxStart.sendKeys("0950PM");
	  dateBoxEnd.sendKeys("01112022");
	  dateBoxEnd.sendKeys(Keys.TAB);
	  dateBoxEnd.sendKeys("1050PM");

	  System.out.println("Log information filled in.");
	  driver.findElement(By.name("submit")).click();
	  System.out.println("Log Submit");
	  

  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
