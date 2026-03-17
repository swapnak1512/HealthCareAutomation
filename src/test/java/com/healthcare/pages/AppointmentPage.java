package com.healthcare.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.By;

public class AppointmentPage {
	
	WebDriver driver;
	public AppointmentPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public By makeApptHeader = By.xpath("//h2[contains(text(),'Make Appointment')]");
	public By facility = By.id("combo_facility");
	public By readmissionCheckbox = By.id("chk_hospotal_readmission");
	public By medicareRadioButton = By.id("radio_program_medicare");
	public By medicaidRadioButton = By.id("radio_program_medicaid");
	public By noneRadioButton = By.id("radio_program_none");
	public By visitDate = By.id("txt_visit_date");
	public By comment = By.id("txt_comment");
	public By bookApptBtn = By.id("btn-book-appointment");
	
    
	
	public boolean verifyMakeAppointmentPage()
	{
		if(driver.findElement(makeApptHeader).isDisplayed())
			return true;
		else
			return false;
	}
	public void selectFacility(String facilityName)
	{
		Select facilityDropDown = new Select(driver.findElement(facility));
		facilityDropDown.selectByVisibleText(facilityName);
		
	}
	
	public void readmission()
	{
		driver.findElement(readmissionCheckbox).click();
		
	}
	
	public void selectHealthcarePgm(String pgm)
	{
		if(pgm.equals("Medicare"))
		{
			driver.findElement(medicareRadioButton).click();
			
		}
		
		if(pgm.equals("Medicaid"))
		{
			driver.findElement(medicaidRadioButton).click();
		}
		
		if(pgm.equals("None"))
		{
			driver.findElement(noneRadioButton).click();
		}
		
	}
	
	public void selectVisitDate(String date)
	{
		driver.findElement(visitDate).sendKeys(date);
		
	}
	
	public void enterComments(String text)
	{
		driver.findElement(comment).sendKeys(text);
		
	}
	
	public void clickBookAppointment()
	{
		driver.findElement(bookApptBtn).click();
	}
	
		
}