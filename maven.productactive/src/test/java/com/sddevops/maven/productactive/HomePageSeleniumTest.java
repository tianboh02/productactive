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
  @Test
  public void f() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
				
			
		//routes
		String home = "http://localhost:8080/maven.productactive/HomeServlet";
		String login = "http://localhost:8080/maven.productactive/LoginServlet";
		String activityLogger = "http://localhost:8080/maven.productactive/ActivityLoggerServlet/dashboard";
		String notePad = "http://localhost:8080/maven.productactive/NotepadManagement/dashboard";
		String deadlines = "http://localhost:8080/maven.productactive/DeadlineServlet/dashboard";
		String account = "http://localhost:8080/maven.productactive/AccountServlet/userPage";

		  
		String currenturl;
		  
		//1. home page
		driver.get(home);
		  
		currenturl = driver.getCurrentUrl();
		if(currenturl == home) {
			System.out.println("1. Redirected to home page (pass)");
		}
		else {
			System.out.println("1. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Login")) .click();
		
		
		//2. login
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
		if(driver.getCurrentUrl() == activityLogger) {
			System.out.println("4. Redirected to activity logger dashboard page (pass)");
		}
		else {
			System.out.println("4. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Home")) .click();
		
		
		//5. home page
		if(driver.getCurrentUrl() == home) {
			System.out.println("5. Redirected to home page (pass)");
		}
		else {
			System.out.println("5. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Note Pad")) .click();
		
		
		//6. notepad dashboard
		if(driver.getCurrentUrl() == notePad) {
			System.out.println("6. Redirected to note pad dashboard page (pass)");
		}
		else {
			System.out.println("6. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Home")) .click();
		
		
		//7. home page
		if(driver.getCurrentUrl() == home) {
			System.out.println("7. Redirected to home page (pass)");
		}
		else {
			System.out.println("7. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Note Pad")) .click();
		
		
		//8. deadline dashboard
		if(driver.getCurrentUrl() == deadlines) {
			System.out.println("8. Redirected to deadlines dashboard page (pass)");
		}
		else {
			System.out.println("8. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Home")) .click();
		
		
		//9. home page
		if(driver.getCurrentUrl() == home) {
			System.out.println("9. Redirected to home page (pass)");
		}
		else {
			System.out.println("9. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Note Pad")) .click();
		
		
		//10. account page
		if(driver.getCurrentUrl() == account) {
			System.out.println("10. Redirected to account page (pass)");
		}
		else {
			System.out.println("10. Page is not redirected correctly (fail)");
		}
		  
		driver.findElement(By.linkText ("Home")) .click();
		
		
		//11. home page
		if(driver.getCurrentUrl() == home) {
			System.out.println("11. Redirected to home page (pass)");
		}
		else {
			System.out.println("11. Page is not redirected correctly (fail)");
		}
		  
		
		//12. log out
		driver.findElement(By.linkText ("Logout")) .click();
		System.out.println("12. logout triggered");
		
		
		//13. home page
		if(driver.getCurrentUrl() == home) {
			System.out.println("13. Redirected to home page (pass)");
		}
		else {
			System.out.println("13. Page is not redirected correctly (fail)");
		}
			
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
