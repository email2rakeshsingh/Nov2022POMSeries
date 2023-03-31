package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.profuctInfoPage;
import com.qa.opencart.pages.searchResultPage;

public class BaseTest {

	DriverFactory df;
	public WebDriver driver;
	protected Properties prop;

	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected CommonsPage commPage;
	protected searchResultPage searchResultPage;
	protected profuctInfoPage productInfopage;
	protected RegisterPage regPage;

	protected SoftAssert softAssert;

	@BeforeTest
	public void setup() {

		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void teardown() {
		// driver.quit();

	}
}
