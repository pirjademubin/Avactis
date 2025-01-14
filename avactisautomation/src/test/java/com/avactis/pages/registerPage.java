package com.avactis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class registerPage extends BaseClass{
	WebDriver driver;
	
	
	public registerPage(WebDriver ldriver) {
		this.driver = ldriver;
		
	}
	
	@FindBy(xpath = "//a[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath = "//button[text()='Register']")
	WebElement registerUser;
	
	@FindBy(name = "customer_info[Customer][Email]")
	WebElement userEmail;
	
	@FindBy(name = "customer_info[Customer][Password]")
	WebElement userPass;
	
	@FindBy(name = "customer_info[Customer][RePassword]")
	WebElement userRePass;
	
	@FindBy(name = "customer_info[Customer][FirstName]")
	WebElement userFirstName;
	
	@FindBy(name = "customer_info[Customer][LastName]")
	WebElement userLastName;
	
	@FindBy(name = "customer_info[Customer][Country]")
	WebElement userCountry;
	
	@FindBy(name = "customer_info[Customer][State]")
	WebElement userState;
	
	@FindBy(name = "customer_info[Customer][ZipCode]")
	WebElement userZipCode;
	
	@FindBy(name = "customer_info[Customer][City]")
	WebElement userCity;
	
	@FindBy(name = "customer_info[Customer][Streetline1]")
	WebElement userAddress1;
	
	@FindBy(name = "customer_info[Customer][Streetline2]")
	WebElement userAddress2;
	
	@FindBy(name = "customer_info[Customer][Phone]")
	WebElement userPhone;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitButton;
	
	public void registrationProcess(
			String email,
			String pass, 
			String fname, 
			String lname, 
			String ucountry, 
			String ustate, 
			double uzip,
			String ucity,
			String uaddress1,
			String uaddress2,
			double uphone
			) {
		System.out.println("Inside function....");
		myAccount.click();
		registerUser.click();
		userEmail.sendKeys(email);
		userPass.sendKeys(pass);
		userRePass.sendKeys(pass);
		userFirstName.sendKeys(fname);
		userLastName.sendKeys(lname);
		
		Select country = new Select(userCountry);
		country.selectByVisibleText(ucountry);
		
		Select state = new Select(userState);
		state.selectByVisibleText(ustate);
		
		userZipCode.sendKeys(String.valueOf(uzip));
		userCity.sendKeys(ucity);
		userAddress1.sendKeys(uaddress1);
		userAddress2.sendKeys(uaddress2);
		userPhone.sendKeys(String.valueOf(uphone));
		submitButton.click();
	}
}
