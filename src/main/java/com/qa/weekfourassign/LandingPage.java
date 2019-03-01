package com.qa.weekfourassign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage {
	
	@FindBy(id = "people")
	WebElement webeleuser;
	
	public String userAdded(String user) {
		WebElement webeleTable = webeleuser.findElement(By.tagName("tbody"));
		List<WebElement> webeleList = webeleTable.findElements(By.tagName("tr"));
		for (int i = 1; i < webeleList.size(); i++) {
			WebElement webeleData = webeleList.get(i);
			List<WebElement> webeleRowData = webeleData.findElements(By.tagName("td"));
			WebElement webeleUserData = webeleRowData.get(1);
			WebElement result = webeleUserData.findElement(By.tagName("a"));
			if (result.getText().equals(user)) {
				return result.getText();
			}
		}
		return "";
	}

}
