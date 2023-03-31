package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By search = By.name("search");
	private By searchIcone = By.cssSelector("div#search button");

	/**
	 * @param driver
	 */
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public searchResultPage doSearch(String ProductName) {
		WebElement searchEle = eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT);
		searchEle.clear();
		searchEle.sendKeys(ProductName);
		eleUtil.doClick(searchIcone);
		return new searchResultPage(this.driver);
	}

}
