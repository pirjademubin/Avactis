package com.avactis.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.avactis.pages.BaseClass;
import com.avactis.pages.CheckoutPage;
import com.avactis.utilities.ExcelDataProvider;

public class CheckOutTest extends BaseClass {
	
	//WebDriverWait wait;
	
	@Test(priority = 1)
	public void checkoutProducts() throws InterruptedException {
		ExcelDataProvider excel = new ExcelDataProvider();
		logger = extent.createTest("Avactis Positive Test");
		logger.info("Avactis Test Has been created successfully");
		CheckoutPage objcheckoutpage = PageFactory.initElements(driver, CheckoutPage.class);
		logger.info("Page Factory has been initialised");
		logger.info("Starting registration process...");
		objcheckoutpage.SearchProductsandCheckout(
				excel.getStringData("ProductList", 2, 1));
		String lText = excel.getStringData("ProductList", 2, 1);
		System.out.println(lText);
		Thread.sleep(5000);
		String xpath = "//a//h3[text()='"+ lText + "']";
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("driver.findElement(By.linkText(excel.getStringData(\"ProductList\", 2, 1)))")));
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='quantity_in_cart']")));
//		Select quantity = new Select(driver.findElement(By.xpath("//select[@name='quantity_in_cart']")));
//		quantity.selectByVisibleText(String.valueOf(objcheckoutpage.selectQuantity(excel.getNumericData("ProductList", 2, 2))));
//		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='Add To Cart']")).click();
		Thread.sleep(5000);
		
	}
}
