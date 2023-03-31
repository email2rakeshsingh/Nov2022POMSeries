package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.profuctInfoPage;
import com.qa.opencart.utils.ProductDescriptionConst;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-104: Design product Info Page for open cart page")
@Story("US--105: design product Info Page features")
public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		productInfopage = loginPage.doLoginproductinfo(prop.getProperty("username").trim(),
				prop.getProperty("password").trim());
		commPage = new CommonsPage(driver);
		productInfopage = new profuctInfoPage(driver);
	}

	@DataProvider(name = "GetProductData")
	public Object[][] GetProductData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "MacBook", "MacBook Air", 4 },
				{ "Samsung", "Samsung SyncMaster 941BW", 1 }, };
	}
	@Description("Verify product Images Cout page test")
	@Severity(SeverityLevel.NORMAL)
	
	@Test(dataProvider = "GetProductData")
	public void productImagesCout(String searchKey, String productName, int ImageCount) {
		commPage = new CommonsPage(driver);
		searchResultPage = commPage.doSearch(searchKey);
		productInfopage = searchResultPage.selectProductName(productName);
		Assert.assertEquals(productInfopage.getProductsImagesCount(), ImageCount);

	}
	@Description("Verify product Description Images Cout page test")
	@Severity(SeverityLevel.NORMAL)
	
	@Test
	public void productDescriptionTest() {
		searchResultPage = commPage.doSearch("MacBook");
		productInfopage = searchResultPage.selectProductName("MacBook Air");
		String productDesc = productInfopage.getProductDescription();
		System.out.println("product desc:" + productDesc);

		softAssert.assertTrue(productDesc != null);
		softAssert.assertFalse(productDesc.isEmpty());
		softAssert.assertTrue(productDesc.contains("MacBook Air"));
		softAssert.assertTrue(productDesc.contains(ProductDescriptionConst.MACBOOK_AIR_DESCRIPTION));
		softAssert.assertAll();
	}

	@Description("Verify product Data Images Cout page test")
	@Severity(SeverityLevel.NORMAL)
	
	@Test
	public void productDataTest() {
		searchResultPage = commPage.doSearch("MacBook");
		productInfopage = searchResultPage.selectProductName("MacBook Air");
		Map<String, String> actProductMap = productInfopage.productInfo();
		actProductMap.forEach((k, v) -> System.out.println(k + ":" + v));

		softAssert.assertEquals(actProductMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductMap.get("name"), "MacBook Air");
		softAssert.assertEquals(actProductMap.get("Reward Points"), "700");
		softAssert.assertAll();

//		Brand:Apple
//		Availability:In Stock
//		price:$1,202.00
//		name:MacBook Air
//		Product Code:Product 17
//		Reward Points:700
//		TaxPrice:Ex Tax: $1,000.00

	}

}
