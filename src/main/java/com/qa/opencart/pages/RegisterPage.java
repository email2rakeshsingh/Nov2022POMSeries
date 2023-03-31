package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By FirstName = By.id("input-firstname");
	private By LastName = By.id("input-lastname");
	private By EMail = By.id("input-email");
	private By Telephone = By.id("input-telephone");
	private By Password = By.id("input-password");
	private By PasswordConfirm = By.id("input-confirm");

	private By SubscribeCheckBoxYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By SubscribeCheckBoxNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By checkBox = By.xpath("//input[@name='agree']");
	private By SubmitButton = By.xpath("//input[@type='submit' and @value='Continue' ]");

	private By SuccessMessage = By.xpath("//div[@id='content']/h1");

	private By LogoutLnik = By.linkText("Logout");
	private By ResgisterLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public boolean userRegister(String FirstName, String LastName, String EMail, String Telephone, String Password,
			String Subscribe) {

		eleUtil.doSendKeys(this.FirstName, FirstName);
		eleUtil.doSendKeys(this.LastName, LastName);
		eleUtil.doSendKeys(this.EMail, EMail);
		eleUtil.doSendKeys(this.Telephone, Telephone);
		eleUtil.doSendKeys(this.Password, Password);
		eleUtil.doSendKeys(this.PasswordConfirm, Password);

		if (Subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(SubscribeCheckBoxYes);

		} else {
			eleUtil.doClick(SubscribeCheckBoxNo);

		}
		eleUtil.doClick(checkBox);
		eleUtil.doClick(SubmitButton);

		String successMsg = eleUtil.waitForElementVisible(SuccessMessage, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();

		System.out.println(successMsg);
		if (successMsg.contains(Constants.REGISTER_SUCCESSFUL_MESSAGE)) {
			eleUtil.doClick(LogoutLnik);
			eleUtil.doClick(ResgisterLink);

			return true;

		} else {
			return false;
		}
	}
}
