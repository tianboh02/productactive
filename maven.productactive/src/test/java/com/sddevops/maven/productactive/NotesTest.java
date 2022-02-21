package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NotesTest {
  @Test
  public void f() throws InterruptedException {
	// define the chrome driver
	System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
	// define the drive instance
	WebDriver driver = new ChromeDriver();
	Dimension d = new Dimension(1382,744); 
	//Resize the current window to the given dimension
	driver.manage().window().setSize(d); 
	
	driver.get ("http://localhost:8090/maven.productactive/HomeServlet");
	
	driver.findElement(By.linkText ("Login")) .click();
	Thread.sleep(2000);

	driver.findElement(By.name("username")).sendKeys("a");
	driver.findElement(By.name("password")).sendKeys("a");
	driver.findElement(By.className("loginButton")).submit();
	Thread.sleep(2000);
	
	driver.findElement(By.linkText ("Note Pad")) .click();
	Thread.sleep(2000);
	
	driver.findElement(By.linkText ("Add New Note")) .click();
	driver.findElement(By.name("title")).sendKeys("Automation Test");
	driver.findElement(By.name("content")).sendKeys("Automation Test Content");
	driver.findElement(By.className("btn-success")).submit();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//a[@href='edit?id=6']")).click();
	driver.findElement(By.name("title")).sendKeys(" (Edited)");
	driver.findElement(By.name("content")).sendKeys(" (Edited)");
	driver.findElement(By.className("btn-success")).submit();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//a[@href='delete?id=6']")).click();
	
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
}
