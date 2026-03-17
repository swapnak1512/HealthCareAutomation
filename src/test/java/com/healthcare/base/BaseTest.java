package com.healthcare.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;

import com.healthcare.listeners.*;
import com.healthcare.reports.ExtentManager;
import com.healthcare.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)

public class BaseTest {

	public WebDriver driver;
	protected static ExtentReports extent;
	
	@BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getExtentReport();
    }


		
	@BeforeMethod
	//@Parameters("browser")
	public void setup() {

		String browser = ConfigReader.getProperty("browser");
		

        if(browser.equalsIgnoreCase("chrome")) {

        	WebDriverManager.chromedriver().setup();

        	ChromeOptions options = new ChromeOptions();

        	// Start Chrome with a fresh profile
        	options.addArguments("--guest");

		    // Disable password manager
		    Map<String, Object> prefs = new HashMap<>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
	
		    options.setExperimentalOption("prefs", prefs);
	
		    options.addArguments("--disable-notifications");
		    options.addArguments("--disable-infobars");
		    options.addArguments("--disable-extensions");
	
		    driver = new ChromeDriver(options);
	
		    driver.manage().window().maximize();
	//	    driver.get("https://katalon-demo-cura.herokuapp.com/");
		    driver.get(ConfigReader.getEnvironmentUrl());
        }
        else if(browser.equalsIgnoreCase("firefox")) {
        	driver = new FirefoxDriver();
           	driver.manage().window().maximize();
        	driver.get(ConfigReader.getEnvironmentUrl());
        }
        /*else if(browser.equalsIgnoreCase("edge"))
        {
        	driver = new EdgeDriver();
        	driver.manage().window().maximize();
        	driver.get(ConfigReader.getEnvironmentUrl());
        } */
        else
        	throw new IllegalArgumentException("Browser not supported: " + browser);

        
	}
	
	


	
	@AfterMethod
	public void teardown() {
	
		driver.quit();
	
	}
	
	@AfterSuite
    public void afterSuite() {
		extent.flush();
    }

}