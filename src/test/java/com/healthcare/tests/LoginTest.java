package com.healthcare.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.healthcare.base.BaseTest;
import com.healthcare.pages.LoginPage;
import com.healthcare.pages.AppointmentPage;
import com.healthcare.pages.HomePage;
import com.healthcare.utils.TestDataProvider;
import com.healthcare.reports.ExtentTestManager;




public class LoginTest extends BaseTest {
	
	@Test(dataProvider="loginData", dataProviderClass=TestDataProvider.class)
	public void loginTest(String username,String password,String expectedResult) throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		AppointmentPage ap = new AppointmentPage(driver);
		
		hp.clickMakeAppointment();
		ExtentTestManager.getTest().info("Clicked Make Appointment");

	
		lp.login(username, password);
		
		
		
		if (expectedResult.equalsIgnoreCase("SUCCESS")) {
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
				wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		
				boolean isAppointmentPageVisible = ap.verifyMakeAppointmentPage();
				Assert.assertTrue(isAppointmentPageVisible, "Appointment page is not visible");
				ExtentTestManager.getTest().pass("Login successful");
			}
		else {
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
				wait.until(ExpectedConditions.visibilityOfElementLocated(lp.errorLoginMessage));
		     	lp.verifyErrorMessage();
			    ExtentTestManager.getTest().pass("Login unsuccessful, ErrorMessage is displayed for invalid credentials");
		}
	}
	

	
	@Test
	public void validLoginTest()
	{
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		AppointmentPage ap = new AppointmentPage(driver);
		
		hp.clickMakeAppointment();
		lp.login("John Doe", "ThisIsNotAPassword");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		
		if(ap.verifyMakeAppointmentPage())
			ExtentTestManager.getTest().pass("Login successful");
		else
			ExtentTestManager.getTest().fail("Login unsuccessful");
		
	}
	

	
	@Test
	public void inValidLoginTest()
	{
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		
		hp.clickMakeAppointment();
		lp.login("test", "test");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.errorLoginMessage));
		
		lp.verifyErrorMessage();
		ExtentTestManager.getTest().pass("Login unsuccessful, ErrorMessage is displayed for invalid credentials");
		
	}
	
	@Test
	public void loginWithEmptyFields() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		hp.clickMakeAppointment();
		lp.login("", "");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(lp.errorLoginMessage));
		lp.verifyErrorMessage();
		ExtentTestManager.getTest().pass("Login unsuccessful, ErrorMessage is displayed for invalid credentials");
		
	}
	
	
}
