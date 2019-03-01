package com.qa.weekfourassign;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TaskThreeTest {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Given("^Given I go to \"([^\"]*)\" website and the username is present \"([^\"]*)\"$")
	public void i_go_to_website(String arg1, String arg2) throws InterruptedException {
		driver.get(arg1);
		driver.findElement(By.cssSelector("#j_username")).sendKeys("admin");
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).click();
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).sendKeys("admin");
		driver.findElement(By.cssSelector("body > div > div > form > div:nth-child(2) > input")).submit();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#tasks > div:nth-child(4) > a.task-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#main-panel > div:nth-child(18) > a > dl")).click();
		Thread.sleep(1000);
	}

	@When("^When I click on username \"([^\"]*)\" and I click on configure link and change the current fullname to new a new fullname \"([^\"]*)\" and I click save button and I click on People link$")
	public void i_search_for(String arg1, String arg2)  {
		LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
		
	}
	
	@Then("^Then the new fullname \"([^\"]*)\" should be visible for the username \"([^\"]*)\"$")
	public void i_am_taken_to_a_list_of_data_for_that(String arg1, String arg2)  {
		assertEquals(arg1,driver.findElement(By.id("sb_form_q")).getAttribute("value"));
	}

}
