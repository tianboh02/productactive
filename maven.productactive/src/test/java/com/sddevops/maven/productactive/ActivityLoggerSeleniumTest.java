package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
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
	
	//routes
	String home = "http://localhost:8080/maven.productactive/HomeServlet";
	String login = "http://localhost:8080/maven.productactive/LoginServlet";
	String dashboard = "http://localhost:8080/maven.productactive/ActivityLoggerServlet/dashboard";
	String addLog = "http://localhost:8080/maven.productactive/AddActivityLoggerServlet";
	String editLog;
	
	

  @Test (priority = 0)
  public void logDashboard() {
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
			   
			  
		driver.get(home);
			  
		driver.findElement(By.linkText ("Login")) .click();
			  
		//2. login page
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");
			  
		driver.findElement(By.className("loginButton")).click();
			  
		driver.findElement(By.linkText ("Activity Logger")) .click();
		
		driver.quit();
		  
  }
  
  @Test (priority = 1)
  public void logAdd() {
	  // define the chrome driver
	  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
	  // define the drive instance
	  WebDriver driver = new ChromeDriver();
	   
	  
	  driver.get(home);
	  
	  driver.findElement(By.linkText ("Login")) .click();
	  
	  //2. login page
	  driver.findElement(By.name("username")).sendKeys("Tianboh");
	  driver.findElement(By.name("password")).sendKeys("123456789");
	  
	  driver.findElement(By.className("loginButton")).click();
	  
	  driver.findElement(By.linkText ("Activity Logger")) .click();
	  
	  driver.findElement(By.linkText ("Log New Activity")) .click();
	  
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

	  driver.findElement(By.name("submit")).click();
	  driver.quit();

  }
  
  @Test (priority = 2)
  public void logEdit() {
	// define the chrome driver
		  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		  // define the drive instance
		  WebDriver driver = new ChromeDriver();
		   
		  
		  driver.get(home);
		  
		  driver.findElement(By.linkText ("Login")) .click();
		  
		  //2. login page
		  driver.findElement(By.name("username")).sendKeys("Tianboh");
		  driver.findElement(By.name("password")).sendKeys("123456789");
		  
		  driver.findElement(By.className("loginButton")).click();
		  
		  driver.findElement(By.linkText ("Activity Logger")) .click();
		  

		  driver.findElement(By.xpath("//td[contains(text(),'practice paper')]/following-sibling::td//a[contains(text(),'Edit')]")).click();
		  
		  WebElement name = driver.findElement(By.name("activityName"));
		  WebElement description = driver.findElement(By.name("activityDescription"));
		  WebElement dateBoxStart = driver.findElement(By.xpath("//input[@name='startDateTime']"));
		  WebElement dateBoxEnd = driver.findElement(By.xpath("//input[@name='endDateTime']"));
		  name.clear();
		  description.clear();
		  name.sendKeys("History");
		  description.sendKeys("actual paper");
		  dateBoxStart.sendKeys("02112022");
		  dateBoxStart.sendKeys(Keys.TAB);
		  dateBoxStart.sendKeys("0100PM");
		  dateBoxEnd.sendKeys("02112022");
		  dateBoxEnd.sendKeys(Keys.TAB);
		  dateBoxEnd.sendKeys("0250PM");
		  
		  driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		  driver.quit();

  }
  
  @Test (priority = 3)
  public void logDelete() {
	// define the chrome driver
	  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
	  // define the drive instance
	  WebDriver driver = new ChromeDriver();
	   
	  
	  driver.get(home);
	  
	  driver.findElement(By.linkText ("Login")) .click();
	  
	  //2. login page
	  driver.findElement(By.name("username")).sendKeys("Tianboh");
	  driver.findElement(By.name("password")).sendKeys("123456789");
	  
	  driver.findElement(By.className("loginButton")).click();
	  
	  driver.findElement(By.linkText ("Activity Logger")) .click();
	  
	  driver.findElement(By.xpath("//td[contains(text(),'actual paper')]/following-sibling::td//a[contains(text(),'Delete')]")).click();
	  
	  System.out.println("Log deleted");
	  driver.quit();
  }
  
 
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
