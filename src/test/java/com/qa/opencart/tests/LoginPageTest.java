package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.profuctInfoPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-100: Design login page for open cart page")
@Story("US--101: design login page features")
public class LoginPageTest extends BaseTest {

	@Description("Verify login page test")
	@Severity(SeverityLevel.NORMAL)
	
	@Test(priority = 1)
	public void loginpageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("Verify login page URL")
	@Severity(SeverityLevel.NORMAL)
	
	@Test(priority = 2)
	public void getLoginpageURL() {
		String actualURL = loginPage.getLoginpageURL();
		Assert.assertTrue(actualURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Verify forgot Password Test")
	@Severity(SeverityLevel.BLOCKER)

	@Test(priority = 3)
	public void forgotPasswordTest() {
		Boolean forgotLink = loginPage.isForgotpasswordLinkExist();
		Assert.assertTrue(forgotLink);

	}
	@Description("Verify Link Exist Test")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(priority = 4)
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExit());

	}
	@Description("Verify Link Exist Test")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(priority = 5)
	public void issearchBoxExistTest() {
		Assert.assertTrue(loginPage.IsSearchBoxisExist());
	}
	@Description("Verify Link Exist Test")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(priority = 6)
	public void isCartPageisExistTest() {
		Assert.assertTrue(loginPage.IsShopingCartisExist());
	}
	@Description("Verify Link Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(priority = 7)
	public void loginTest() {
		AccountPage accPage = loginPage.doLogin(prop.getProperty("username").trim(),
				prop.getProperty("password").trim());
		String PageTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(PageTitle, Constants.ACCOUNT_PAGE_TITLE);
		Assert.assertTrue(accPage.getLogoutLinkExist());
	}

}
