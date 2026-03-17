package com.healthcare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public By makeAppointment = By.id("btn-make-appointment");
	
	public void clickMakeAppointment()
	{
		driver.findElement(makeAppointment).click();
	}

}
