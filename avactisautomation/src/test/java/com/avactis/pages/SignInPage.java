package com.avactis.pages;

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
	}
}
