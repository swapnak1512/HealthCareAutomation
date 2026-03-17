package com.healthcare.reports;

import com.aventstack.extentreports.*;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports extent = ExtentManager.getExtentReport();

    public static synchronized ExtentTest startTest(String name, String description) {

        ExtentTest extentTest = extent.createTest(name, description);

        test.set(extentTest);

        return extentTest;
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }
}