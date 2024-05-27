	package com.mystore.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utlits.ExtentReporter;
import com.utlits.utlities;

public class Mylisteners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest extenttest;
	

	@Override
	public void onStart(ITestContext context) {
		// System.out.println("Execution of Project Test Started");

		try {
			extentreport = ExtentReporter.GenereateExtendReporte();
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		// before each method method will start this method getting executing
		
		extenttest = extentreport.createTest(result.getName());
		extenttest.log(Status.INFO, result.getName()+ " Start Executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// regarding OnTestSuccess - when and of the test case in the class without
		// fail, successfully executing then
		// OnTestSuccess will invoked

		extenttest.log(Status.PASS, result.getName() + " Got successfully Executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// to reterive the driver onTestFailure method is invoked one of the test class
		// ( which is failed) that failed method going
		// fall in the result

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destiScreenshotPath = utlities.CaptureScreenshort(driver, result.getName());

		extenttest.addScreenCaptureFromPath(destiScreenshotPath);
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL, result.getName() + " - Got Failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.SKIP, result.getName() +" - Got Test Skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		
		String PathofExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(PathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
