package com.healthcare.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConfirmationPage {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public By apptConfirmation = By.xpath("//h2[contains(text(), 'Appointment Confirmation')]") ;
	public By facility = By.id("facility");
	public By reAdmission = By.id("hospital_readmission");
	public By pgm = By.id("program");
	public By visitDate = By.id("visit_date");
	public By comment = By.id("comment");
	
	public void appointmentConfirmation()
	{
		
		Assert.assertTrue(driver.findElement(apptConfirmation).isDisplayed(), "The element should be displayed.");

	}

	
	public List<String> verifyDetailsOnConfirmationPage()
	{
		List<String> details = new ArrayList<>();
		details.add(driver.findElement(facility).getText());
		details.add(driver.findElement(reAdmission).getText());
		details.add(driver.findElement(pgm).getText());
		details.add(driver.findElement(visitDate).getText());
		details.add(driver.findElement(comment).getText());
		return details;
		
	}

}
