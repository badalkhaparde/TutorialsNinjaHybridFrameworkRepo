package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentText;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		extentReport =ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentText = extentReport.createTest(testName);
		extentText.log(Status.INFO,testName+" started executing ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentText.log(Status.PASS,testName+" got successfully executed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		extentText.addScreenCaptureFromPath(destinationScreenshotPath);
		extentText.log(Status.INFO,result.getThrowable());
		extentText.log(Status.FAIL,testName+" got failed ");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentText.log(Status.INFO,result.getThrowable());
		extentText.log(Status.SKIP,testName+" got skipped ");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
