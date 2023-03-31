package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-106: Register Account Page for open cart page")
@Story("US--107: Register Account Page features")

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();

	}

	public String getRandomEmailid() {
		Random random = new Random();
		String email = "novautomation" + random.nextInt(1000) + "@gmail.com";
		return email;

	}

	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Description("Verify Registion Page Test Images Cout page test")
	@Severity(SeverityLevel.NORMAL)

	@Test(dataProvider = "getRegisterTestData")

	public void userRegistionPageTest(String FirstName, String LastName, String Telephone, String Password,
			String Subscribe)

	{
		Assert.assertTrue(
				regPage.userRegister(FirstName, LastName, getRandomEmailid(), Telephone, Password, Subscribe));

	}

}
