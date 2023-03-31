package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-102: Design Account Page for open cart page")
@Story("US--103: design Account Page features")
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		// accPage = loginPage.doLogin(prop.getProperty("username").trim(),
		// prop.getProperty("password").trim());
		accPage = new AccountPage(driver);

	}

	@Description("Verify Page Title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("Verify Page Header Title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageHeaderTest() {
		String accPageHeader = accPage.getAccountpageHeader();
		System.out.println("Account page Header is :" + accPageHeader);
		Assert.assertEquals(accPageHeader, Constants.ACCOUNT_PAGE_HEADER);

	}

	@Description("Verify Page Section Header Title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageSectionHeaderList() {

		List<String> actualAccList = accPage.getAccountSectionHeaderList();
		System.out.println("Actual acc page section headers :" + actualAccList);
		Assert.assertEquals(actualAccList, Constants.ACCOUNT_PAGE_SECTIONS_HEADER_LIST);

	}

	@Description("Verify User Logged OutTest Title ")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isUserLoggedOutTest() {
		loginPage = accPage.clikOnLogoutLink();
		Assert.assertEquals(loginPage.getLogoutMessage(), Constants.USER_LOGOUT_MESSAGE);

	}

	@DataProvider(name = "searchQueries")
	public Object[][] searchQueries() {
		return new Object[][] { { "MacBook" }, { "Imac" }, { "Samsung" }, { "Apple" } };
	}

	@Test(dataProvider = "searchQueries")
	public void searchTest(String ProductName) {

		commPage = new CommonsPage(driver);
		searchResultPage = commPage.doSearch(ProductName);
		String ResulrPageHeader = searchResultPage.getResultspageHeader();
		Assert.assertTrue(ResulrPageHeader.contains(ProductName));

	}

	@DataProvider(name = "GetProductData")
	public Object[][] GetProductData() {
		return new Object[][] { { "MacBook", "MacBook Pro" }, { "MacBook", "MacBook Air" },
				{ "Samsung", "Samsung SyncMaster 941BW" }, };
	}

	@Description("Verify product Info Test Title ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "GetProductData")
	public void productInfoTest(String ProductName, String MainProductName) {
		commPage = new CommonsPage(driver);
		searchResultPage = commPage.doSearch(ProductName);
		String ResultPageHeader = searchResultPage.getResultspageHeader();
		productInfopage = searchResultPage.selectProductName(MainProductName);
		String mainProductNameValues = productInfopage.getMainProduuctName();
		System.out.println(mainProductNameValues);
		Assert.assertEquals(mainProductNameValues, MainProductName);

	}

}
