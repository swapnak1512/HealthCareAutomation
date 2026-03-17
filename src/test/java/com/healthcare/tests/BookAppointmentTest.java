package com.healthcare.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.healthcare.base.BaseTest;
import com.healthcare.pages.AppointmentPage;
import com.healthcare.pages.ConfirmationPage;
import com.healthcare.pages.HomePage;
import com.healthcare.pages.LoginPage;
import com.healthcare.reports.ExtentTestManager;
import com.healthcare.utils.TestDataProvider;


public class BookAppointmentTest extends BaseTest{


	
	@Test(dataProvider="appointmentData", dataProviderClass=TestDataProvider.class)
	public void bookAppointmentTest(String username,String password,String facility, String reAdmission,String healthcarepgm, String visitdate, String comments) throws InterruptedException, ParseException 
	{
		AppointmentPage ap = new AppointmentPage(driver);
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
		ConfirmationPage cp = new ConfirmationPage(driver);
        
        
		hp.clickMakeAppointment();
		ExtentTestManager.getTest().info("Clicked Make Appointment");

	/*	
		lp.enterUsername("John Doe");
		lp.enterPassword("ThisIsNotAPassword");
		ExtentTestManager.getTest().info("Entered credentials");

		lp.clickLogin();
		ExtentTestManager.getTest().pass("Login successful");
	*/
		lp.login(username, password);
		ExtentTestManager.getTest().pass("Login successful");
	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		
		ap.selectFacility(facility);
		ExtentTestManager.getTest().info("Selected Facility"+facility);
		
		if(reAdmission.equals("Yes"))
		{
			ap.readmission();
			ExtentTestManager.getTest().info("ReAdmission:"+reAdmission);
		}
		ap.selectHealthcarePgm(healthcarepgm);
		ExtentTestManager.getTest().info("Selected Health Care Program:"+healthcarepgm);
		
		ap.selectVisitDate(visitdate);
		ExtentTestManager.getTest().info("Selected Date:"+visitdate);
		
		ap.enterComments(comments);
		ExtentTestManager.getTest().info("Entered Comments:"+comments);
		
		
		ap.clickBookAppointment();
		ExtentTestManager.getTest().info("Clicked Booked appointment");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(cp.apptConfirmation));
		
		cp.appointmentConfirmation();
		ExtentTestManager.getTest().pass("Received Confirmation page");
		
		
		List<String> details = cp.verifyDetailsOnConfirmationPage();
		String facilityOnConfirmationPage = details.get(0);
		String reAdmissionOnConfirmationPage = details.get(1);
		String pgmOnConfirmationPage = details.get(2);
		String visitDateOnConfirmationPage = details.get(3);
		String commentOnConfirmationPage = details.get(4);
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = inputFormat.parse(visitdate);
		String convertedVisitDate = outputFormat.format(date);
		
        
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(facility, facilityOnConfirmationPage, "Facility mismatch");
		softassert.assertEquals(reAdmission, reAdmissionOnConfirmationPage, "Readmission mismatch");
		softassert.assertEquals(healthcarepgm, pgmOnConfirmationPage, "Healthcare pgm mismatch");
		softassert.assertEquals(convertedVisitDate, visitDateOnConfirmationPage, "Visit date mismatch");
		softassert.assertEquals(comments, commentOnConfirmationPage, "Comments mismatch");
		softassert.assertAll();
		
	}


	
	
	@Test
	public void bookMedicareAppointmentTest() throws InterruptedException 
	{
		AppointmentPage ap = new AppointmentPage(driver);
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        ConfirmationPage cp = new ConfirmationPage(driver);
		
		hp.clickMakeAppointment();
		ExtentTestManager.getTest().info("Clicked Make Appointment");

/*		
		lp.enterUsername("John Doe");
		lp.enterPassword("ThisIsNotAPassword");
		ExtentTestManager.getTest().info("Entered credentials");

		lp.clickLogin();
		ExtentTestManager.getTest().pass("Login successful");
	*/
		lp.login("John Doe", "ThisIsNotAPassword");
		ExtentTestManager.getTest().pass("Login successful");
	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		
		ap.selectFacility("Tokyo CURA Healthcare Center");
		ExtentTestManager.getTest().info("Selected Facility");
		
		ap.readmission();
		Assert.assertTrue(driver.findElement(ap.readmissionCheckbox).isEnabled(), "The readmission checkbox should be selected but it is not");
		
		ap.selectHealthcarePgm("Medicare");
		Assert.assertTrue(driver.findElement(ap.medicareRadioButton).isSelected(), "The medicare radio button should be selected but it is not");
		ExtentTestManager.getTest().info("Selected Health Care Program");

		ap.selectVisitDate("3/13/2026");
		ExtentTestManager.getTest().info("Selected Date");
		
		ap.enterComments("Followup appointment");
		ExtentTestManager.getTest().info("Entered Comments");
		
		ap.clickBookAppointment();
		ExtentTestManager.getTest().info("Clicked Booked appointment");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(cp.apptConfirmation));
		
		cp.appointmentConfirmation();
		ExtentTestManager.getTest().pass("Received Confirmation page");
				
	}

	@Test
	public void bookMedicaidAppointmentTest() throws InterruptedException 
	{
		AppointmentPage ap = new AppointmentPage(driver);
        LoginPage lp = new LoginPage(driver);
		ConfirmationPage cp = new ConfirmationPage(driver);
        HomePage hp = new HomePage(driver);
		
		hp.clickMakeAppointment();

		ExtentTestManager.getTest().info("Clicked Make Appointment");

/*		
		lp.enterUsername("John Doe");
		lp.enterPassword("ThisIsNotAPassword");
		ExtentTestManager.getTest().info("Entered credentials");

		lp.clickLogin();
		ExtentTestManager.getTest().pass("Login successful");
	*/
		lp.login("John Doe", "ThisIsNotAPassword");
		ExtentTestManager.getTest().pass("Login successful");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		//wait.until(ExpectedConditions.elementToBeSelected(ap.facility));
		
		ap.selectFacility("Tokyo CURA Healthcare Center");
		ExtentTestManager.getTest().info("Selected Facility");
		
		//ap.readmission();
		Assert.assertFalse(driver.findElement(ap.readmissionCheckbox).isSelected(),"Redamission checkbox should not be checked but its checked");
		
		ap.selectHealthcarePgm("Medicaid");
		
		Assert.assertTrue(driver.findElement(ap.medicaidRadioButton).isSelected(), "The medicaid radio button should be selected but it is not");
        
		ExtentTestManager.getTest().info("Selected Medicaid Health Care Program");
		
		ap.selectVisitDate("3/15/2026");
		ExtentTestManager.getTest().info("Selected Date");
		
		ap.enterComments("New appointment");
		ExtentTestManager.getTest().info("Entered Comments");
				
		ap.clickBookAppointment();
		ExtentTestManager.getTest().info("Clicked Booked appointment");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(cp.apptConfirmation));
		cp.appointmentConfirmation();
		ExtentTestManager.getTest().pass("Received Confirmation page");
		

	}
	
	@Test
	public void bookNoneAppointmentTest() throws InterruptedException 
	{
		AppointmentPage ap = new AppointmentPage(driver);
        LoginPage lp = new LoginPage(driver);
		ConfirmationPage cp = new ConfirmationPage(driver);
        HomePage hp = new HomePage(driver);
		
		hp.clickMakeAppointment();

		ExtentTestManager.getTest().info("Clicked Make Appointment");

	/*		
		lp.enterUsername("John Doe");
		lp.enterPassword("ThisIsNotAPassword");
		ExtentTestManager.getTest().info("Entered credentials");

		lp.clickLogin();
		ExtentTestManager.getTest().pass("Login successful");
	*/
		lp.login("John Doe", "ThisIsNotAPassword");
		ExtentTestManager.getTest().pass("Login successful");
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ap.makeApptHeader));
		//wait.until(ExpectedConditions.elementToBeSelected(ap.facility));
		
		ap.selectFacility("Tokyo CURA Healthcare Center");
		ExtentTestManager.getTest().info("Selected Facility");
		
		ap.readmission();
		Assert.assertTrue(driver.findElement(ap.readmissionCheckbox).isEnabled());
		
		ap.selectHealthcarePgm("None");
		
		Assert.assertTrue(driver.findElement(ap.noneRadioButton).isSelected(), "The None radios button should be selected but it is not");
        
		ExtentTestManager.getTest().info("Selected None Health Care Program");
		
		ap.selectVisitDate("3/15/2026");
		ExtentTestManager.getTest().info("Selected Date");
		
		ap.enterComments("Followup appointment");
		ExtentTestManager.getTest().info("Entered Comments");
		
		ap.clickBookAppointment();
		ExtentTestManager.getTest().info("Clicked Booked appointment");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(cp.apptConfirmation));
		cp.appointmentConfirmation();
		ExtentTestManager.getTest().pass("Received Confirmation page");
		
	}

}
