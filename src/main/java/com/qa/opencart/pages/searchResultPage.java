package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class searchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By resultPageHeader = By.cssSelector("div #content h1");

	public searchResultPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getResultspageHeader() {
		return eleUtil.doGetElementText(resultPageHeader);

	}
	public profuctInfoPage selectProductName(String mainProductName) {
		
		WebElement mailProductEle= eleUtil.waitForElementPresent(By.linkText(mainProductName), Constants.DEFAULT_ELEMENT_TIME_OUT);
		mailProductEle.click();
		return new profuctInfoPage(this.driver);
		
	}

}
