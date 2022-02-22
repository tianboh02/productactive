package com.sddevops.maven.productactive;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UserTest {
	@Test
	public void checkDupeUsername() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
		// navigate the browser to this URL
		driver.get("http://localhost:8080/maven.productactive/");
		// browser look for link with text value "Register"
		driver.findElement(By.linkText("Register")).click();
		// browser to look at input name and enter value
		driver.findElement(By.name("username")).sendKeys("a");
		driver.findElement(By.name("password")).sendKeys("b");
		driver.findElement(By.name("firstName")).sendKeys("d");
		driver.findElement(By.name("lastName")).sendKeys("c");
		WebElement registerButton = driver.findElement(By.className("registerButton"));
		registerButton.submit();
		// Quit driver
		driver.quit();
	}
	@Test(dependsOnMethods = { "checkDupeUsername" })
	public void registerUser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
		// define the drive instance
		WebDriver driver = new ChromeDriver();
		// navigate the browser to this URL
		driver.get("http://localhost:8080/maven.productactive/");
		// browser look for link with text value "Register"
		driver.findElement(By.linkText("Register")).click();
		// browser to look at input name and enter value
		driver.findElement(By.name("username")).sendKeys("usernameTest");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("firstName")).sendKeys("user");
		driver.findElement(By.name("lastName")).sendKeys("testing");
		WebElement registerButton = driver.findElement(By.className("registerButton"));
		registerButton.submit();
		// Quit driver
		driver.quit();
	}

	@Test(dependsOnMethods = { "registerUser" })
	public void loginUser() {
		// Define the Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
		// Define the drive instance
		WebDriver driver = new ChromeDriver();
		// Navigate the browser to the URL provided
		driver.get("http://localhost:8080/maven.productactive/");
		// browser look for link with text value "Register"
		driver.findElement(By.linkText("Login")).click();
		// browser to look at input name and enter value
		driver.findElement(By.name("username")).sendKeys("usernameTest");
		driver.findElement(By.name("password")).sendKeys("password");
		// browser to submit form
		WebElement loginButton = driver.findElement(By.className("loginButton"));
		loginButton.submit();
		// Quit driver
		driver.quit();
	}

	@Test(dependsOnMethods = { "loginUser" })
	public void updateUser() {
		// Define the Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
		// Define the drive instance
		WebDriver driver = new ChromeDriver();
		// Navigate the browser to the URL provided
		driver.get("http://localhost:8080/maven.productactive/");
		// Browser to perform login function
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("username")).sendKeys("usernameTest");
		driver.findElement(By.name("password")).sendKeys("password");
		WebElement loginButton = driver.findElement(By.className("loginButton"));
		loginButton.submit();
		// Browser to edit first name and submit
		driver.findElement(By.linkText("Account")).click();
		driver.findElement(By.className("updateButton")).click();
		driver.findElement(By.name("firstName")).clear();
		driver.findElement(By.name("firstName")).sendKeys("userCHANGEDababa");
		WebElement updateUserButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
		updateUserButton.submit();
		// To check if name is updated
		driver.findElement(By.linkText("Account")).click();
		driver.findElement(By.className("updateButton")).click();
		// Quit driver
		driver.quit();
	}

	@Test(dependsOnMethods = { "updateUser" })
	public void deleteUser() {
		// Define the Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\chromedriver.exe");
		// Define the drive instance
		WebDriver driver = new ChromeDriver();
		// Navigate the browser to the URL provided
		driver.get("http://localhost:8080/maven.productactive/");
		// Browser to perform login function
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("username")).sendKeys("usernameTest");
		driver.findElement(By.name("password")).sendKeys("password");
		WebElement loginButton = driver.findElement(By.className("loginButton"));
		loginButton.submit();
		// Browser to press delete button
		driver.findElement(By.linkText("Account")).click();
		driver.findElement(By.className("deleteButton")).click();
		// Quit driver
		driver.quit();
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
