package com.healthcare.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.healthcare.utils.ConfigReader;

public class ExtentManager {

    private static ExtentReports extent;

    
    public synchronized static ExtentReports getExtentReport() {
    	
        if (extent == null) {
        	
        	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            /*String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReports/ExtentReport_" + timestamp + ".html"; */

            String historyPath =
                    System.getProperty("user.dir")
                            + "/test-output/ReportsHistory/ExtentReport_" + timestamp + ".html";

            String latestPath =
                    System.getProperty("user.dir")
                            + "/test-output/LatestReport.html";


          /*  ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath); */
            
            ExtentSparkReporter sparkHistory =
                    new ExtentSparkReporter(historyPath);

            ExtentSparkReporter sparkLatest =
                    new ExtentSparkReporter(latestPath);



            sparkHistory.config().setReportName("Healthcare Automation Report");
            sparkHistory.config().setDocumentTitle("Test Execution Report");

            sparkLatest.config().setReportName("Latest Healthcare Automation Report");
            sparkLatest.config().setDocumentTitle("Latest Execution Report");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkHistory,sparkLatest);
            
            extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", ConfigReader.getProperty("env"));
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));

        }

        return extent;
    }
}

