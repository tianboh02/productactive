package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class HomePageSeleniumTest {
  @Test
  public void f() {
	  
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
				
		//1. home page
		//2. login
		//3. home page
		//4. activity logger dashboard
		//5. home page
		//6. notepad dashboard
		//7. home page
		//8. deadline dashboard
		//9. home page
		//10. account page
		//11. home page
		//12. log out
		//13. home page
		
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
