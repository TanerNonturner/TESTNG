package testbase;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.CommonMethods;

public class Listener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// printing logs to console
		System.out.println("Test Started " + result.getName());
		// creating test logs in ExtentReport
		BaseClass.test = BaseClass.report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// output to the console
		System.out.println("Test Passed " + result.getName());
		// output to the report
		BaseClass.test.pass("Test Case Pass " + result.getName());

		try {
			BaseClass.test.addScreenCaptureFromPath(CommonMethods.takesScreenshot("\\passed\\" + result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		//output to console
		System.out.println("Test Failed "+result.getName());
		//output to extent report
		BaseClass.test.fail("Test Case failed "+result.getName());
		
		try {
			BaseClass.test.addScreenCaptureFromPath(CommonMethods.takesScreenshot("\\failed\\"+result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	
}
