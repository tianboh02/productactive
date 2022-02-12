package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
  @Test
  public void f() {
		// define the chrome driver
		System. setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
		// nagivate the browser to this url
		driver.get ("https://www.rp.edu.sg/discover/");
		// browser look for link with text value "Why RP?"
		driver.findElement(By.linkText ("Why RP?")) .click();
		// browser look for link with text value "Experience Real-World Learning"
		driver.findElement (By.linkText ("Experience Real-World Learning")) .click();
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
