package com.avactis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
	
	
	public void SearchProductsandCheckout(String productName) {
		searchIcon.click();
		searchInput.sendKeys(productName);
		searchButton.click();
	}
	
	public double selectQuantity(double uquantity) {
		quantity.sendKeys(String.valueOf(uquantity));
		return uquantity;
	}
}
