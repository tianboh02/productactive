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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class DeadlineSeleniumTest {
  
  @Test (priority = 0)
  public void addDeadline() {
	  
	  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");

	  WebDriver driver = new ChromeDriver();
	   
	  
	  driver.get("http://localhost:8080/maven.productactive/HomeServlet");
	  
	  driver.findElement(By.linkText ("Login")) .click();

	  driver.findElement(By.name("username")).sendKeys("a");
	  driver.findElement(By.name("password")).sendKeys("a");
	  
	  driver.findElement(By.className("loginButton")).click();
	  
	  driver.findElement(By.linkText ("Deadlines")) .click();
	  
	  driver.findElement(By.linkText ("Add New Deadlines")) .click();
	  
	  driver.findElement(By.name("title")).sendKeys("DevOps");

	  WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"deadline\"]"));

	  dateBox.sendKeys("11112022");
	  dateBox.sendKeys(Keys.TAB);
	  dateBox.sendKeys("1159PM");


	  driver.findElement(By.className("Save")).click();
	  driver.quit();

  }
  
  @Test (priority = 1)
  public void editDeadline() {
		  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		 
		  WebDriver driver = new ChromeDriver();
		   
		  
		  driver.get("http://localhost:8080/maven.productactive/HomeServlet");
		  
		  driver.findElement(By.linkText ("Login")) .click();
		  
		 
		  driver.findElement(By.name("username")).sendKeys("a");
		  driver.findElement(By.name("password")).sendKeys("a");
		  
		  driver.findElement(By.className("loginButton")).click();
		  
		  driver.findElement(By.linkText ("Deadlines")) .click();
		  

		  driver.findElement(By.xpath("//td[contains(text(),'DevOps')]/following-sibling::td//a[contains(text(),'Edit')]")).click();
		  
		  WebElement title = driver.findElement(By.name("title"));
		 
		  WebElement dateBox = driver.findElement(By.xpath("//input[@name='deadline']"));
		  
		  title.clear();
		  
		  title.sendKeys("DevOps Submission");
		  dateBox.sendKeys("11112022");
		  dateBox.sendKeys(Keys.TAB);
		  dateBox.sendKeys("0900PM");
		  
		  driver.findElement(By.className("Save")).click();
		  
		  driver.quit();

  }
  
  @Test (priority = 2)
  public void deleteDeadline() {
	  System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	   
	  
	  driver.get("http://localhost:8080/maven.productactive/HomeServlet");
	  
	  driver.findElement(By.linkText ("Login")) .click();
	  

	  driver.findElement(By.name("username")).sendKeys("a");
	  driver.findElement(By.name("password")).sendKeys("a");
	  
	  driver.findElement(By.className("loginButton")).click();
	  
	  driver.findElement(By.linkText ("Deadlines")) .click();
	  
	  driver.findElement(By.xpath("//td[contains(text(),'DevOps Submission')]/following-sibling::td//a[contains(text(),'Delete')]")).click();
	  
	  driver.quit();
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
