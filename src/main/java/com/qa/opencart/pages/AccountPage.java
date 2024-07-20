package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.xpath("(//img[@title='naveenopencart'])[1]");
	private By accountLink = By.xpath("//a[@class='list-group-item'][normalize-space()='My Account']");
	private By accountSectionHeader = By.cssSelector("div#content h2");
	private By logOutLink = By.linkText("Logout");;
	private By softwareLink = By.xpath("//a[normalize-space()='Software']");

	/**
	 * @param driver
	 */
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	
	}

	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_FRACTION_URL, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountpageHeader() {
		return eleUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}

	public boolean getAccountPageLink() {
		return eleUtil.waitForElementPresent(accountLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public List<String> getAccountSectionHeaderList() {
		List<WebElement> accSectionList = eleUtil.waitForElementsVisible(accountSectionHeader,
				Constants.DEFAULT_ELEMENT_TIME_OUT);
		List<String> accSecValList = new ArrayList<String>();
		for (WebElement e : accSectionList) {
			String text = e.getText();
			accSecValList.add(text);

		}
		return accSecValList;

	}

	public boolean getLogoutLinkExist() {
		return eleUtil.waitForElementPresent(logOutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public LoginPage clikOnLogoutLink() {
		if (getLogoutLinkExist()) {
			eleUtil.doClick(logOutLink);
			return new LoginPage(driver);

		}
		return null;
	}

	public boolean getSoftwareLink() {
		return eleUtil.doIsDisplayed(softwareLink);
	}

}
