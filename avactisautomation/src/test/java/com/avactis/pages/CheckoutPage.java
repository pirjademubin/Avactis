package com.avactis.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutPage extends BaseClass {
	WebDriver driver;
	//public static String orderIDresult = "";
	
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
	
	@FindBy(xpath = "//table[@class='order_items without_images']//tr")
	WebElement tableRows;
	
	@FindBy(xpath = "//table[@class='order_items without_images']//th")
	WebElement tableColumns;
	
	@FindBy(xpath = "//div[@class='note note-success note-bordered']")
	WebElement orderID;
	
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
	}
	
	public void verifyItemsPrice(String item1Price, String item2Price, String item3Price) {
		List<String> checkedItemsPriceList = new ArrayList<>();
		int rowCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//tr")).size();
		//int columnCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//th")).size();
		for(int i=1; i<rowCount; i++) {
				String actVal = driver.findElement(By.xpath("//table[@class='order_items without_images']//tbody//tr["+(i+1)+"]//td[3]")).getText();
				checkedItemsPriceList.add(actVal);		
		}
		System.out.println("*****************************");
		System.out.println(checkedItemsPriceList.get(0));
		if(checkedItemsPriceList.get(0).equals(item1Price) && checkedItemsPriceList.get(1).equals(item2Price) && checkedItemsPriceList.get(2).equals(item3Price)) {
			logger.info("Observed details from webpage "+ checkedItemsPriceList.get(0)+" , "+ checkedItemsPriceList.get(1)+" , "+ checkedItemsPriceList.get(2) + " verified with Test Data "+ item1Price+" , "+item2Price +" , "+item3Price );
			logger.pass("Verified, Data is correct !!");
		}
		
	}
	
	public void verifyItemNames(String item1Name, String item2Name, String item3Name) {
		List<String> checkedItemsList = new ArrayList<>();
		int rowCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//tr")).size();
		//int columnCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//th")).size();
		for(int i=1; i<rowCount; i++) {
				String actVal = driver.findElement(By.xpath("//table[@class='order_items without_images']//tbody//tr["+(i+1)+"]//td[1]")).getText();
				checkedItemsList.add(actVal);		
		}
		System.out.println("*****************************");
		System.out.println(checkedItemsList.get(0));
		if(checkedItemsList.get(0).equals(item1Name) && checkedItemsList.get(1).equals(item2Name) && checkedItemsList.get(2).equals(item3Name)) {
			logger.info("Observed details from webpage "+ checkedItemsList.get(0)+" , "+ checkedItemsList.get(1)+" , "+ checkedItemsList.get(2) + " verified with Test Data "+ item1Name+" , "+item2Name +" , "+item3Name );
			logger.pass("Verified, Data is correct !!");
		}
		
	}
	
	public void verifyItemQuantity(double item1Quan, double item2Quan, double item3Quan) {
		List<Double> checkedItemsQuanList = new ArrayList<>();
		int rowCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//tr")).size();
		//int columnCount = driver.findElements(By.xpath("//table[@class='order_items without_images']//th")).size();
		for(int i=1; i<rowCount; i++) {
				String actVal = driver.findElement(By.xpath("//table[@class='order_items without_images']//tbody//tr["+(i+1)+"]//td[2]")).getText();
				checkedItemsQuanList.add(Double.valueOf(actVal));
				System.out.println(Double.valueOf(actVal));
		}
		System.out.println("*****************************");
		System.out.println(checkedItemsQuanList.get(0));
		if(checkedItemsQuanList.get(0) == item1Quan && checkedItemsQuanList.get(1) == item2Quan && checkedItemsQuanList.get(2) == item3Quan) {
			logger.info("Observed details from webpage "+ checkedItemsQuanList.get(0)+" , "+ checkedItemsQuanList.get(1)+" , "+ checkedItemsQuanList.get(2) + " verified with Test Data "+ item1Quan+" , "+item2Quan +" , "+item3Quan );
			logger.pass("Verified, Data is correct !!");
		}
		
	}
	
	public void placeOrder() throws InterruptedException {
		placeOrderButton.click();
		Thread.sleep(10000);
	}
	
	public String recordOrderID() throws InterruptedException {
		Thread.sleep(5000);
		boolean orderStatus = false;
		try {
			orderStatus = orderID.isDisplayed();
		} catch (Exception e) {
			System.out.println("WebElement can't be located !!");			
		}
		Thread.sleep(2000);
		String orderId = orderID.getText();
		int start = orderId.indexOf(":") + 2; // Find the start of the name
	    int end = orderId.indexOf("\n", start); // Find the end of the name
	    String name = orderId.substring(start, end); // Extracts "John"
        String orderIDresult = name.substring(name.indexOf('#') + 1);
        System.out.println(orderIDresult); // Output: 00012
		if(orderStatus == true) {
			logger.info("Order ID: " + name);
			logger.pass("Order has been placed successfully");
		}
		return orderIDresult;
	}	
}




