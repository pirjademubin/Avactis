package com.avactis.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutPage extends BaseClass {
	WebDriver driver;
	
	public CheckoutPage(WebDriver ldriver) {
		this.driver = ldriver;
		
	}
	
	@FindBy(xpath = "//i[@class='fa fa-search search-btn']")
	WebElement searchIcon;
	
	@FindBy(xpath = "//input[@name='search_pattern']")
	WebElement searchInput;
	
	@FindBy(xpath = "//button[text()='Search']")
	WebElement searchButton;
	
	@FindBy(linkText = "//select[@name='quantity_in_cart']")
	WebElement quantity;
	
	@FindBy(xpath = "//div[@class=' top-cart-info cart_preview cartpreview']//a[@class='top-cart-info-count']")
	WebElement cartView;
	
	@FindBy(xpath = "//div[@class='top-cart-content']//a[text()='Checkout']")
	WebElement checkoutButton;
	
	@FindBy(xpath = "//input[@name='billingInfo[Firstname]']")
	WebElement billingInfoFirstName;
	
	@FindBy(xpath = "//input[@name='billingInfo[Lastname]']")
	WebElement billingInfoLastName;
	
	@FindBy(xpath = "//input[@name='billingInfo[Email]']")
	WebElement billingInfoEmail;
	
	@FindBy(xpath = "//select[@name='billingInfo[Country]']")
	WebElement billingInfoCountry;
	
	@FindBy(xpath = "//input[@name='billingInfo[Postcode]']")
	WebElement billingInfoZipCode;
	
	@FindBy(xpath = "//select[@name='billingInfo[Statemenu]']")
	WebElement billingInfoState;
	
	@FindBy(xpath = "//input[@name='billingInfo[City]']")
	WebElement billingInfoCity;
	
	@FindBy(xpath = "//input[@name='billingInfo[Streetline1]']")
	WebElement billingInfoAline1;
	
	@FindBy(xpath = "//input[@name='billingInfo[Streetline2]']")
	WebElement billingInfoAline2;
	
	@FindBy(xpath = "//input[@name='billingInfo[Phone]']")
	WebElement billingInfoPhone;
	
	@FindBy(xpath = "//input[@name='checkbox_shipping_same_as_billing']")
	WebElement billingInfoCheckbox;
	
	@FindBy(xpath = "//input[@onclick='submitStep(1);']")
	WebElement continueCheckOut1;
	
	@FindBy(xpath = "//input[@onclick='submitStep(2);']")
	WebElement continueCheckOut2;
	
	@FindBy(xpath = "//div[@class='shipping_form col-lg-6']//label[2]")
	WebElement verifyAline1;
	
	@FindBy(xpath = "//label[text()=' Ground Shipping']//input[1]")
	WebElement shippingMethod;
	
	@FindBy(xpath = "//input[@value='Place Order']")
	WebElement placeOrderButton;
	
	public void SearchProductsandCheckout(String productName) {
		searchIcon.click();
		searchInput.sendKeys(productName);
		searchButton.click();
	}
	
	public double selectQuantity(double uquantity) {
		quantity.sendKeys(String.valueOf(uquantity));
		return uquantity;
	}
	
	public void mouseHoverOverCart() {
		//Instantiating Actions class
		Actions actions = new Actions(driver);

		//Hovering on main menu
		actions.moveToElement(cartView).perform();
	}
	
	public void checkoutItems(
			String fname, 
			String lname, 
			String email,
			String ucountry,
			double zipcode,
			String ustate,
			String city,
			String aline1,
			String aline2,
			double phone
			)throws InterruptedException {
		checkoutButton.click();
		Thread.sleep(2000);
		billingInfoFirstName.sendKeys("fname");
		billingInfoLastName.sendKeys(lname);
		billingInfoEmail.sendKeys(email);
		
		Select country = new Select(billingInfoCountry);
		country.selectByVisibleText(ucountry);
		
		billingInfoZipCode.sendKeys(String.valueOf(zipcode));
		
		Select state = new Select(billingInfoState);
		state.selectByVisibleText(ustate);
		
		billingInfoCity.sendKeys(city);
		billingInfoAline1.sendKeys(aline1);
		billingInfoAline2.sendKeys(aline2);
		billingInfoPhone.sendKeys(String.valueOf(phone));
		billingInfoCheckbox.click();
		logger.info("Continueing checkout after filling billing details...");
		continueCheckOut1.click();
		Thread.sleep(5000);
		
		logger.info("Verifying Shipping Address...");
		String aline1verify = verifyAline1.getText();
		Assert.assertEquals(aline1verify, aline1);
		if(aline1verify.equals(aline1)) {
			System.out.println(aline1verify + " is matching with "+ aline1);
			logger.pass("Shipping Address is correct !!");
		}
		
		
		shippingMethod.click();
		continueCheckOut2.click();
		Thread.sleep(5000);
		
		placeOrderButton.click();
		Thread.sleep(5000);
	}
}
