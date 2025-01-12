package com.avactis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

public class registerPage extends BaseClass{
	WebDriver driver;
	
	
	public registerPage(WebDriver ldriver) {
		this.driver = ldriver;
		//PageFactory.initElements(this.driver, this);
		
	}
	
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
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
	
//	@FindBy(xpath = "//div[@class='note note-danger']")
//	WebElement dangerNote;
//	
//	@FindBy(xpath = "//div[@class='note note-success']")
//	WebElement successNote;
	
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
		//wait.until(ExpectedConditions.visibilityOf(myAccount));
		myAccount.click();
//		wait.until(ExpectedConditions.visibilityOf(registerUser));
		registerUser.click();
//		wait.until(ExpectedConditions.visibilityOf(userEmail));
		userEmail.sendKeys(email);
		userPass.sendKeys(pass);
		userRePass.sendKeys(pass);
		userFirstName.sendKeys(fname);
		userLastName.sendKeys(lname);
		
		Select country = new Select(userCountry);
		country.selectByVisibleText(ucountry);
		
		//wait.until(ExpectedConditions.visibilityOf(userState));
		Select state = new Select(userState);
		state.selectByVisibleText(ustate);
		
		userZipCode.sendKeys(String.valueOf(uzip));
		userCity.sendKeys(ucity);
		userAddress1.sendKeys(uaddress1);
		userAddress2.sendKeys(uaddress2);
		userPhone.sendKeys(String.valueOf(uphone));
		submitButton.click();
	}
	
	public int checkRegistrationStatus() {
//		WebElement element = driver.findElement(By.xpath("//div[@class='note note-danger']"));
//		boolean elementExists = !element.isEmpty();
		System.out.println("Inside Method");
		boolean dangerStatus = false;
		boolean successStatus = false;
		int flag = 0;
		try {
			dangerStatus = driver.findElement(By.xpath("//div[@class='note note-danger']")).isDisplayed();
		}
		catch(Exception e) {
			System.out.println("Got Exception" + e.getMessage());
		}
		//boolean dangerStatus = driver.findElement(By.xpath("//div[@class='note note-danger']")).isDisplayed();
		//System.out.println("Danger");
		//System.out.println(dangerStatus);
		//boolean successStatus = driver.findElement(By.xpath("//div[contains(text(),'Account')]")).isDisplayed();
		//System.out.println(successStatus);
		if(dangerStatus == true) {
			System.out.println("Inside If");
			//logger.fail("Sorry, user is already registered !!");
			//result.setStatus(ITestResult.FAILURE);
			flag = 1;
		}
		
		try {
			successStatus = driver.findElement(By.xpath("//div[contains(text(),'Account')]")).isDisplayed();
		}
		catch(Exception e) {
			System.out.println("Got Exception" + e.getMessage());
		}
//		else if(successStatus) {
//			logger.pass("New user has been registered successfully !!!");
//			result.setStatus(ITestResult.SUCCESS);
//		}
		if(successStatus == true) {
			System.out.println("Inside else");
			//logger.info("Check the test case");
			//logger.pass("New user has been registered successfully !!!");
			//result.setStatus(ITestResult.SUCCESS);
			flag = 2;
		}
//		try {
//			boolean status = driver.findElement(By.xpath("//div[@class='note note-danger']")).isDisplayed();
//			System.out.println(status);
//			logger.fail("Sorry, user is already registered !!");
//			//Assert.assertEquals(false, status);
//			result.setStatus(ITestResult.FAILURE);
//		} catch (Exception e) {
//			System.out.println("NoSuchElementException" + e.getMessage());
//			logger.pass("New user has been registered successfully !!!");
//		}
//		
		
//		if(driver.findElement(By.xpath("//div[@class='note note-danger']")).isDisplayed()) {
//			try {
//				String dangerMsg = driver.findElement(By.xpath("//div[@class='note note-danger']")).getText();
//				String actualMsg = "This account name is already taken. Please choose a different account name.";
//				assertEquals(dangerMsg, actualMsg);
//				if(actualMsg.equals(dangerMsg)) {
//					System.out.println("Sorry, user is already registered !!");
//					logger.fail("Sorry, user is already registered !!");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		else if(driver.findElement(By.xpath("//div[contains(text(),'Account')]")).isDisplayed()) {
//			String successMsg = driver.findElement(By.xpath("//div[contains(text(),'Account')]")).getText();
//			String actualMsg = "Account created successfully. You are now registered.";
//			assertEquals(successMsg, actualMsg);
//			if(actualMsg.equals(successMsg)) {
//				System.out.println("New user has been registered successfully !!!");
//				logger.pass("New user has been registered successfully !!!");
//			}
//		}
//		else {
//			System.out.println("Please check the test");
//		}
		return flag;
	}
}
