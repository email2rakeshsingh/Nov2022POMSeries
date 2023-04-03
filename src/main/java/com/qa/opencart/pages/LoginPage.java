package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private by locators
	private By EMailId = By.id("input-email");
	private By Password = By.id("input-password");
	private By FPassword = By.linkText("Forgotten Password");
	private By LoginButton = By.xpath("//input[@value='Login']");
	private By RegisterLink = By.linkText("Register");
	private By SearchBox = By.xpath(" //input[@placeholder='Search']");
	private By CartBox = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
	private By AccountLogoutLink = By.xpath("//h1[normalize-space()='Account Logout']");
	
	private By GitTest= By.xpath("Git testing ");
	
	private By GitTesting = By.xpath("Git testing2nd");

	// 2. public page const....

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	// 3. public page action/method
	@Step("getting login page title...")

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContains(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is :" + title);
		return title;

	}

	@Step("getting login page URL...")
	public String getLoginpageURL() {
		String url = eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page URL:" + url);
		return url;

	}

	public String getLoginPagesource() {
		String pagesource = driver.getPageSource();
		System.out.println("Get page source :" + pagesource);
		return pagesource;
	}

	public boolean isForgotpasswordLinkExist() {
		return eleUtil.waitForElementVisible(FPassword, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public boolean isRegisterLinkExit() {
		return eleUtil.waitForElementVisible(RegisterLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public boolean IsSearchBoxisExist() {
		return eleUtil.waitForElementVisible(SearchBox, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public boolean IsShopingCartisExist() {
		return eleUtil.waitForElementVisible(CartBox, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public AccountPage doLogin(String username, String pwd) {
		System.out.println(username + "" + pwd);
		eleUtil.waitForElementVisible(EMailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(Password, pwd);
		eleUtil.doClick(LoginButton);

		return new AccountPage(driver);

	}

	public String getLogoutMessage() {
		String logoutMessg = eleUtil.waitForElementVisible(AccountLogoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT)
				.getText();
		System.out.println("Logout succesfully mesg=:" + logoutMessg);
		return logoutMessg;

	}

	public profuctInfoPage doLoginproductinfo(String username, String pwd) {
		eleUtil.waitForElementVisible(EMailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(Password, pwd);
		eleUtil.doClick(LoginButton);

		return new profuctInfoPage(driver);

	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(RegisterLink);
		return new RegisterPage(driver);

	}

}
