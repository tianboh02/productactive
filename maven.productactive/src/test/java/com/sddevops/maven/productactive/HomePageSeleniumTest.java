package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class HomePageSeleniumTest {
	//routes
	String home = "http://localhost:8080/maven.productactive/HomeServlet";
	
  @Test
  public void HomeToActivityLog() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
	
		driver.get(home);
		driver.findElement(By.linkText ("Login")) .click();
		  
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");		  
		driver.findElement(By.className("loginButton")).click();
		  
		driver.findElement(By.linkText ("Activity Logger")) .click();
		driver.quit();
			
  }
  
  @Test
  public void HomeToNotepad() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
	
		driver.get(home);
		driver.findElement(By.linkText ("Login")) .click();
		  
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");		  
		driver.findElement(By.className("loginButton")).click();
		
		driver.findElement(By.linkText ("Note Pad")) .click();
		driver.quit();
			
  }
  
  @Test
  public void HomeToDeadlines() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
	
		driver.get(home);
		driver.findElement(By.linkText ("Login")) .click();
		  
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");		  
		driver.findElement(By.className("loginButton")).click();

		driver.findElement(By.linkText ("Deadlines")) .click();	
		driver.quit();
		
  }
  
  @Test
  public void HomeToAccount() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
	
		driver.get(home);
		driver.findElement(By.linkText ("Login")) .click();
		
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");		  
		driver.findElement(By.className("loginButton")).click();
		
		driver.findElement(By.linkText ("Account")) .click();	
		driver.quit();
			
  }	
  
  @Test
  public void HomeAndLogout() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
	
		driver.get(home);
		driver.findElement(By.linkText ("Login")) .click();
		
		driver.findElement(By.name("username")).sendKeys("Tianboh");
		driver.findElement(By.name("password")).sendKeys("123456789");		  
		driver.findElement(By.className("loginButton")).click();
		  
		driver.findElement(By.linkText ("Logout")) .click();	
		driver.quit();
			
  }
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
