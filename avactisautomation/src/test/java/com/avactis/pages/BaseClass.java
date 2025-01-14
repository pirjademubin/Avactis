package com.avactis.pages;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.avactis.utilities.BrowserFactory;
import com.avactis.utilities.ConfigDataProvider;
import com.avactis.utilities.ExcelDataProvider;
import com.avactis.utilities.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass implements ITestListener{
	
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider cp;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ITestResult result;
	
	@BeforeSuite
	public void setUpSuite() {
		Reporter.log("Setting Up Reports and Test is getting ready", true);
		excel = new ExcelDataProvider();
		cp = new ConfigDataProvider();
		ExtentSparkReporter spark = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "./Reports/Avactis_"+ Helper.getCurrentDateTime() + ".html"));
		extent = new ExtentReports();
		extent.attachReporter(spark);
		Reporter.log("All configurations done, Test can be started", true);
	}
	
	@Parameters({"browser", "URL"})
	@BeforeMethod
	public void setUp(String browser, String url) {
		Reporter.log("Trying to start browser and application ready", true);
		
		//Following is without passing parameters from TestNG Test Config
		//driver = BrowserFactory.startApplication(driver, cp.getBrowser(), cp.getURL());
		
		driver = BrowserFactory.startApplication(driver, browser, url);
		Reporter.log("Browser and Application is up and running", true);
	}
	
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			Helper.captureScreenshot(driver);
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			Helper.captureScreenshot(driver);
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		extent.flush();
		Reporter.log("Closing the Application and browser", true);
		BrowserFactory.quitApplication(driver);
	}
}
