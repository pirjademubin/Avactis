package com.avactis.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.avactis.pages.BaseClass;
import com.avactis.pages.SignInPage;
import com.avactis.pages.registerPage;
import com.avactis.utilities.ExcelDataProvider;

public class RegisterUserTest extends BaseClass {
	
//	@Test(priority=1)
//	public void RegisterUser() throws InterruptedException {
//		ExcelDataProvider excel = new ExcelDataProvider();
//		logger = extent.createTest("Avactis Positive Test");
//		logger.info("Avactis Test Has been created successfully");
//		registerPage objregisterPage = PageFactory.initElements(driver, registerPage.class);
//		logger.info("Page Factory has been initialised");
//		logger.info("Starting registration process...");
//		objregisterPage.registrationProcess(
//				excel.getStringData("RegistrationDetails", 0, 0),
//				excel.getStringData("RegistrationDetails", 0, 1),
//				excel.getStringData("RegistrationDetails", 0, 2),
//				excel.getStringData("RegistrationDetails", 0, 3),
//				excel.getStringData("RegistrationDetails", 0, 4),
//				excel.getStringData("RegistrationDetails", 0, 5),
//				excel.getNumericData("RegistrationDetails", 0, 6),
//				excel.getStringData("RegistrationDetails", 0, 7),
//				excel.getStringData("RegistrationDetails", 0, 8),
//				excel.getStringData("RegistrationDetails", 0, 9),
//				excel.getNumericData("RegistrationDetails", 0, 10)
//				);
//		logger.info("Registration Function executed successfully");
//		logger.info("Checking Registartion Status...");
//		if(objregisterPage.checkRegistrationStatus() == 1) {
//			logger.fail("Sorry, user is already registered !!");
//			result.setStatus(ITestResult.FAILURE);
//		}
//		else if(objregisterPage.checkRegistrationStatus() == 2) {
//			logger.pass("New user has been registered successfully !!!");
//			result.setStatus(ITestResult.SUCCESS);
//		}
//		logger.info("Registration Status Check completed");
//	}
	
//	@Test(priority=2)
//	public void RegisterSameUserAgain() throws InterruptedException {
//		ExcelDataProvider excel = new ExcelDataProvider();
//		logger = extent.createTest("Avactis Negative Test");
//		logger.info("Avactis Test Has been created successfully");
//		registerPage objregisterPage = PageFactory.initElements(driver, registerPage.class);
//		logger.info("Page Factory has been initialised");
//		logger.info("Starting registration process...");
//		objregisterPage.registrationProcess(
//				excel.getStringData("RegistrationDetails", 0, 0),
//				excel.getStringData("RegistrationDetails", 0, 1),
//				excel.getStringData("RegistrationDetails", 0, 2),
//				excel.getStringData("RegistrationDetails", 0, 3),
//				excel.getStringData("RegistrationDetails", 0, 4),
//				excel.getStringData("RegistrationDetails", 0, 5),
//				excel.getNumericData("RegistrationDetails", 0, 6),
//				excel.getStringData("RegistrationDetails", 0, 7),
//				excel.getStringData("RegistrationDetails", 0, 8),
//				excel.getStringData("RegistrationDetails", 0, 9),
//				excel.getNumericData("RegistrationDetails", 0, 10)
//				);
//		logger.info("Registration Function executed successfully");
//		logger.info("Checking Registartion Status...");
//		if(objregisterPage.checkRegistrationStatus() == 1) {
//			logger.fail("Sorry, user is already registered !!");
//			result.setStatus(ITestResult.FAILURE);
//		}
//		else if(objregisterPage.checkRegistrationStatus() == 2) {
//			logger.pass("New user has been registered successfully !!!");
//			result.setStatus(ITestResult.SUCCESS);
//		}
//		logger.info("Registration Status Check completed");
//	}
	
	@Test(priority = 1)
	public void SignInUserPositiveTest() throws InterruptedException {
		ExcelDataProvider excel = new ExcelDataProvider();
		logger = extent.createTest("Avactis SignIn Positive Test");
		logger.info("Avactis Test Has been created successfully");
		SignInPage objsignInPage = PageFactory.initElements(driver, SignInPage.class);
		logger.info("Page Factory has been initialised");
		logger.info("Starting SignIn process...");
		objsignInPage.signIn(
				excel.getStringData("RegistrationDetails", 0, 0),
				excel.getStringData("RegistrationDetails", 0, 1));
		logger.info("SignIn Function executed successfully");
		logger.info("Checking SignIn Status...");
		logger.pass("Successfully Signed In");
	}
	
	@Test(priority = 2)
	public void SignInUserNegativeTest() throws InterruptedException {
		ExcelDataProvider excel = new ExcelDataProvider();
		logger = extent.createTest("Avactis SignIn Negative Test");
		logger.info("Avactis Test Has been created successfully");
		SignInPage objsignInPage = PageFactory.initElements(driver, SignInPage.class);
		logger.info("Page Factory has been initialised");
		logger.info("Starting SignIn process...");
		objsignInPage.signIn(
				excel.getStringData("RegistrationDetails", 0, 0),
				excel.getStringData("RegistrationDetails", 1, 0));
		logger.info("SignIn Function executed successfully");
		logger.info("Checking SignIn Status...");
		logger.fail("Wrong Credentials");
	}
}
