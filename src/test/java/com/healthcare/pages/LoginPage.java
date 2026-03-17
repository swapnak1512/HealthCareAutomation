package com.healthcare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public By username = By.id("txt-username");
	public By password = By.id("txt-password");
	public By loginButton = By.id("btn-login");
	public By errorLoginMessage = By.xpath("//p[contains(text(), 'Login failed! Please ensure the username and password are valid.')]");
	
	
	
	/*
	public void enterUsername(String user)
	{
		driver.findElement(username).sendKeys(user);
	}
	
	public void enterPassword(String pwd)
	{
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		driver.findElement(loginButton).click();
	}
	*/
	public void login(String user, String pwd)
	{
		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
		
	}
	
	public void verifyErrorMessage()
	{
		
		Assert.assertTrue(driver.findElement(errorLoginMessage).isDisplayed(), "The element should be displayed.");

	}
	
}
