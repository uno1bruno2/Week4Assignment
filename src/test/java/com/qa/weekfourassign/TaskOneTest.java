package com.qa.weekfourassign;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskOneTest {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://35.189.74.144:8080/");
		driver.findElement(By.cssSelector("#j_username")).sendKeys("admin");
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).click();
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).sendKeys("admin");
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).submit();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Ignore	
	@Test
	public void addItemTest() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > a.task-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#name")).sendKeys("AutomatedItem");
		driver.findElement(By.cssSelector("#j-add-item-type-standalone-projects > ul > li.hudson_model_FreeStyleProject")).click();
		driver.findElement(By.cssSelector("#ok-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#yui-gen38 > span")).click();
		driver.findElement(By.cssSelector("#tasks > div:nth-child(1) > a.task-link")).click();
		assertEquals("AutomatedItem wasn't inserted correctly",true,driver.findElement(By.cssSelector("#job_AutomatedItem")).isDisplayed());
	}

}
