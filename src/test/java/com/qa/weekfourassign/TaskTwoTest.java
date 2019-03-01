package com.qa.weekfourassign;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(Parameterized.class)
public class TaskTwoTest {
	
	
	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\Week3Assignment\\AssessmentFriday.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows()-1][5];

		for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
			XSSFCell cell0 = sheet.getRow(row).getCell(0);
			if(sheet.getRow(row).getCell(0) == null) {
				cell0 = sheet.getRow(row).createCell(0);
			}
			ob[row-1][0] = cell0.getStringCellValue();
			
			XSSFCell cell1 = sheet.getRow(row).getCell(1);
			if(sheet.getRow(row).getCell(1) == null) {
				cell1 = sheet.getRow(row).createCell(1);
			}
			ob[row-1][1] = cell1.getStringCellValue();
			
			XSSFCell cell2 = sheet.getRow(row).getCell(2);
			if(sheet.getRow(row).getCell(2) == null) {
				cell2 = sheet.getRow(row).createCell(2);
			}
			ob[row-1][2] = cell2.getStringCellValue();
			
			XSSFCell cell3= sheet.getRow(row).getCell(3);
			if(sheet.getRow(row).getCell(3) == null) {
				cell3 = sheet.getRow(row).createCell(3);
			}
			ob[row-1][3] = cell3.getStringCellValue();
			
			XSSFCell cell4 = sheet.getRow(row).getCell(4);
			if(sheet.getRow(row).getCell(4) == null) {
				cell4 = sheet.getRow(row).createCell(4);
			}
			ob[row-1][4] = cell4.getStringCellValue();
//			
//			for (int column = 0; column < 2; column++) {
//				
//				if(sheet.getRow(row).getCell(column) == null) {
//					XSSFCell cell = sheet.getRow(row).getCell(column);
//					cell = sheet.getRow(row).createCell(column);
//				}
//				ob[row-1][column] = cell.getStringCellValue();
//			}
		}
		return Arrays.asList(ob);
	}

	private String user;
	private String name;
	private String pass;
	private String confirmpass;
	private String email;
	
//	
	
	public TaskTwoTest(String user, String name, String pass, String confirmpass, String email) {
		this.user = user;
		this.name = name;
		this.pass = pass;
		this.confirmpass = confirmpass;
		this.email = email;
	}
	
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
	
	@Test
	public void addUserTest() throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#tasks > div:nth-child(4) > a.task-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#main-panel > div:nth-child(18) > a > dl")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#tasks > div:nth-child(3) > a.task-link")).click();
		driver.findElement(By.cssSelector("#username")).sendKeys(user);
		
		driver.findElement(By.cssSelector("#main-panel > form > div:nth-child(2) > table > "
				+ "tbody > tr:nth-child(2) > td:nth-child(2) > input[type=\"password\"]")).sendKeys(pass);
		
		driver.findElement(By.cssSelector("#main-panel > form > div:nth-child(2) > table > "
				+ "tbody > tr:nth-child(3) > td:nth-child(2) > input[type=\"password\"]")).sendKeys(confirmpass);
		
		driver.findElement(By.cssSelector("#main-panel > form > div:nth-child(2) > table > "
				+ "tbody > tr:nth-child(4) > td:nth-child(2) > input[type=\"text\"]")).sendKeys(name);
		
		driver.findElement(By.cssSelector("#main-panel > form > div:nth-child(2) > table > "
				+ "tbody > tr:nth-child(5) > td:nth-child(2) > input[type=\"text\"]")).sendKeys(email);
		driver.findElement(By.cssSelector("#yui-gen1 > span")).click();
		Thread.sleep(2000);
		
		LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Desktop\\Week3Assignment\\AssessmentFriday.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {
			if (user.equals(landingPage.userAdded(user))) {
				sheet.getRow(row).getCell(5).setCellValue("true");
			} else {
				sheet.getRow(row).getCell(5).setCellValue("false");
			}
			
		}
		assertEquals("User wasn't added correctly",user,landingPage.userAdded(user));
	}
	

}
