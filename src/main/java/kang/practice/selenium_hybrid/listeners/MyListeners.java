package kang.practice.selenium_hybrid.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import kang.practice.selenium_hybrid.utils.ExtentReporter;
import kang.practice.selenium_hybrid.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest newTest;
	
	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("individual test starting... ");
		newTest = extentReport.createTest(result.getName());
		newTest.log(Status.INFO, result.getName() + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName()+" passed successfully");
		newTest.log(Status.PASS, result.getName()+" passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.destinationScreenshotPath(driver, testName);
		
		newTest.addScreenCaptureFromPath(destinationScreenshotPath);
		newTest.log(Status.INFO, result.getThrowable());
		newTest.log(Status.FAIL, testName +" failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("test has been skipped");
		newTest.log(Status.INFO, result.getThrowable());
		newTest.log(Status.SKIP, result.getName()+" is skipped.");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution Starting... ");
		
		try {
			extentReport = ExtentReporter.generateExtentReports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution Finished !!!");
		extentReport.flush();
		String pathOfExtentReport = "/Users/simronkang/eclipse-workspace/selenium_hybrid/test-output/ExtentReports/extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
