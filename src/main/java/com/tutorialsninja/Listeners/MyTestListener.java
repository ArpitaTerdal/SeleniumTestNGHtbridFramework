package com.tutorialsninja.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExtentReporter;
import Utilities.Utils;

public class MyTestListener implements ITestListener{
	
	ExtentReports extentReporter;
	ExtentTest extentTest;
	String testName;
   
	public void onStart(ITestContext context) {
		extentReporter=ExtentReporter.generateExtentReporter();
	}

	public void onTestStart(ITestResult result) {
		testName=result.getName();
		extentTest=extentReporter.createTest(testName);
		
		extentTest.log(Status.INFO, testName+"started executing");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName+"successfully exceuted");
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException |IllegalAccessException|NoSuchFieldException|SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String destination=Utils.captureScreenShot(driver,testName);
		extentTest.addScreenCaptureFromPath(destination);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"got skipped");
	}

	public void onFinish(ITestContext context) {
		extentReporter.flush();
		String path=System.getProperty("user.dir"+"\\test-output\\ExtentReports\\extentReport.html");
		File extentreport=new File(path);
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
