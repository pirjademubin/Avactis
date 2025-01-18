package com.avactis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AdminPage extends BaseClass{
	WebDriver driver;
	
	public AdminPage(WebDriver ldriver) {
		this.driver = ldriver;		
	}
	
	@FindBy(xpath = "//input[@name='AdminEmail']")
	WebElement adminUName;
	
	@FindBy(xpath = "//input[@name='Password']")
	WebElement adminUPass;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement adminSignIn;
	
	@FindBy(xpath = "//li[@id='menu-orders']//a//span[text()='Orders']")
	WebElement ordersButton;
	
	@FindBy(xpath = "//table[@id='datatable_orders']//tbody//tr[1]//td[2]")
	WebElement newOrdersTable;
	
	CheckoutPage checkPage = new CheckoutPage(driver); 
	
	public static WebDriver startAdminApplication(WebDriver driver) throws InterruptedException {
		driver.manage().window().maximize();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://localhost/avactis/avactis-system/admin/signin.php");
		Thread.sleep(5000);
		return driver;
	}
	
	public void signInAdmin(String uname, String pass) throws InterruptedException {
		adminUName.sendKeys(uname);
		adminUPass.sendKeys(pass);
		adminSignIn.click();
		Thread.sleep(5000);
	}
	
	public void verifyOrder() throws InterruptedException {
		ordersButton.click();
		Thread.sleep(5000);
		String orderId = newOrdersTable.getText();
		System.out.println("OrderID from Admin" + orderId);
		System.out.println(checkPage.recordOrderID());
		Assert.assertEquals(orderId, checkPage.recordOrderID());
		if(orderId.equals(checkPage.recordOrderID())) {
			logger.pass("Order ID is matching");
		}
	}
	
	
}
