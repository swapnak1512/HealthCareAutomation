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




/*package com.healthcare.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getExtentReport() {

        if (extent == null) {

            String timestamp =
                    new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

            String historyPath =
                    System.getProperty("user.dir")
                            + "/test-output/ReportsHistory/ExtentReport_" + timestamp + ".html";

            String latestPath =
                    System.getProperty("user.dir")
                            + "/test-output/LatestReport.html";

            ExtentSparkReporter sparkHistory =
                    new ExtentSparkReporter(historyPath);

            ExtentSparkReporter sparkLatest =
                    new ExtentSparkReporter(latestPath);

            sparkHistory.config().setDocumentTitle("Automation Report");
            sparkHistory.config().setReportName("Healthcare Test Execution");

            sparkLatest.config().setDocumentTitle("Latest Execution");
            sparkLatest.config().setReportName("Healthcare Latest Run");

            extent = new ExtentReports();

            extent.attachReporter(sparkHistory, sparkLatest);

            extent.setSystemInfo("Automation Tester", "Swapna");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));

        }

        return extent;
    }
}*/