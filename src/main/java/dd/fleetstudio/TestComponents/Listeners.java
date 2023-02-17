package dd.fleetstudio.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import dd.fleetstudio.TestComponents.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Make Extent test object thread safe for parallel execution
	
	@Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("...Test Suite Execution started...");
    }
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " Started!");
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println(result.getMethod().getMethodName() + " Passed!");
		extentTest.get().pass("... Test Passed ...");
		//extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		ITestContext context = result.getTestContext();
	    driver = (WebDriver) context.getAttribute("WebDriver");
		
		System.out.println(result.getMethod().getMethodName() + " Failed!");
		
		//Attach failed test results in extent reports
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		
		//Take screenshot of failed test cases & attach screenshot in reports 
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());	//Screenshot, Attach to report
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

}
