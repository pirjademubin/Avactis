package com.avactis.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.avactis.pages.BaseClass;
import com.avactis.pages.CheckoutPage;
import com.avactis.utilities.ExcelDataProvider;

public class CheckOutTest extends BaseClass {

	// WebDriverWait wait;

//	@Test(priority = 1)
//	public void checkoutProduct1() throws InterruptedException {
//		ExcelDataProvider excel = new ExcelDataProvider();
//		logger = extent.createTest("Avactis Positive Test");
//		logger.info("Avactis Test Has been created successfully");
//		CheckoutPage objcheckoutpage = PageFactory.initElements(driver, CheckoutPage.class);
//		logger.info("Page Factory has been initialised");
//		logger.info("Starting registration process...");
//		objcheckoutpage.SearchProductsandCheckout(
//				excel.getStringData("ProductList", 2, 1));
//		String lText = excel.getStringData("ProductList", 2, 1);
//		System.out.println(lText);
//		Thread.sleep(5000);
//		String xpath = "//a//h3[text()='"+ lText + "']";
//		driver.findElement(By.xpath(xpath)).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//input[@value='Add To Cart']")).click();
//		Thread.sleep(5000);
//		
//	}

	@Test(priority = 1)
	public void checkoutProduct2() throws InterruptedException {
		ExcelDataProvider excel = new ExcelDataProvider();
		logger = extent.createTest("Avactis Positive Test");
		logger.info("Avactis Test Has been created successfully");
		CheckoutPage objcheckoutpage = PageFactory.initElements(driver, CheckoutPage.class);
		logger.info("Page Factory has been initialised");
		logger.info("Starting registration process...");
		List<Boolean> booleanList = new ArrayList<>();
		for (int i = 2; i < 5; i++) {
			objcheckoutpage.SearchProductsandCheckout(excel.getStringData("ProductList", i, 1));
			String lText = excel.getStringData("ProductList", i, 1);
			System.out.println(lText);
			Thread.sleep(5000);
			String xpath = "//a//h3[text()='" + lText + "']";
			driver.findElement(By.xpath(xpath)).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@value='Add To Cart']")).click();
			Thread.sleep(5000);
		}

//		// Locating the Main Menu (Parent element)
//		WebElement mainMenu = driver.findElement(By.xpath("//div[@class=' top-cart-info cart_preview cartpreview']//a[@class='top-cart-info-count']"));
//
//		//Instantiating Actions class
//		Actions actions = new Actions(driver);
//
//		//Hovering on main menu
//		actions.moveToElement(mainMenu).perform();
		objcheckoutpage.mouseHoverOverCart();
		for (int i = 2; i < 5; i++) {
			String lText = excel.getStringData("ProductList", i, 1);
			String cartVerifyXpath = "//div[@class='top-cart-content']//li//a[text()='" + lText + "']";
			Thread.sleep(1000);
			boolean status = driver.findElement(By.xpath(cartVerifyXpath)).isDisplayed();
			Thread.sleep(1000);
			booleanList.add(status);
			Assert.assertEquals(status, true);
		}

		int flag = 0;
		boolean firstElement = booleanList.get(0);
		for (int i = 1; i < booleanList.size(); i++) {
			if (!booleanList.get(i).equals(firstElement)) {
				flag = 1;
			}
		}

		if (flag == 0) {
			logger.pass("All items checked out are correct !!");
		}
		
		objcheckoutpage.checkoutItems(
				excel.getStringData("BillingDetails", 0, 0),
				excel.getStringData("BillingDetails", 0, 1),
				excel.getStringData("BillingDetails", 0, 2),
				excel.getStringData("BillingDetails", 0, 3),
				excel.getNumericData("BillingDetails", 0, 4),
				excel.getStringData("BillingDetails", 0, 5),
				excel.getStringData("BillingDetails", 0, 6),
				excel.getStringData("BillingDetails", 0, 7),
				excel.getStringData("BillingDetails", 0, 8),
				excel.getNumericData("BillingDetails", 0, 9)
				);
		
		objcheckoutpage.verifyItemsQuantityPrice(
				excel.getStringData("ProductList", 2, 3),
				excel.getStringData("ProductList", 3, 3),
				excel.getStringData("ProductList", 4, 3)
				);
		
		objcheckoutpage.placeOrder();
	}
}
