package com.avactis.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BaseClass {
	WebDriver driver;
	
	public SignInPage(WebDriver ldriver) {
		this.driver = ldriver;		
	}
	
	@FindBy(xpath = "//a[text()='Sign In']")
	WebElement signIn;
	
	@FindBy(xpath = "//input[@id='account_sign_in_form_email_id']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@id='account_sign_in_form_passwd_id']")
	WebElement userPass;
	
	@FindBy(xpath = "//input[@value='Sign In']")
	WebElement signInButton;
	
	public void signIn(String uname, String pass) throws InterruptedException {
		signIn.click();
		userName.sendKeys(uname);
		userPass.sendKeys(pass);
		signInButton.click();
		Thread.sleep(2000);
//		String ExpectedPageHeader = "MANAGE ACCOUNT AND VIEW ORDERS";
//		String ActualPageHeader = null;
//		try {
//			ActualPageHeader = driver.findElement(By.xpath("//h3[text()='Manage Account and View Orders']")).getText();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.info("Wrong Xpath" + e.getMessage());
//			//e.printStackTrace();
//		}
//		assertEquals(ExpectedPageHeader, ActualPageHeader );
	}
}
