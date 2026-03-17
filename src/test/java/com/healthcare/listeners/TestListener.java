package com.healthcare.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.healthcare.base.BaseTest;
import com.healthcare.reports.*;
import com.healthcare.utils.*;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName(), "Test execution started");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest base = (BaseTest) result.getInstance();

        if(base.driver != null) {

            String path = ScreenshotUtil.captureScreenshot(
                    base.driver,
                    result.getMethod().getMethodName());

            ExtentTestManager.getTest().fail("Test Failed")
                    .addScreenCaptureFromPath(path);
        }

        ExtentTestManager.getTest().fail(result.getThrowable());
    }
    
        
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
    }

   
    @Override
    public void onStart(ITestContext context) {
        
    }
    @Override
    public void onFinish(ITestContext context) {
        
    }
}
