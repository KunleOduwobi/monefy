package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {

	public static ExtentTest test;
	ExtentReports extent = ExtentReporter.getReporterObject();
	AppiumDriver driver;
	String Env;

	public String getEnv() throws IOException {
		Properties prop = new Properties();
		// Corrected path: resources are in src/main/resources, not src/main/java/resources
				try (FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//main//resources//data.properties")) {
					prop.load(fis);
				}

		Env = System.getProperty("Env") != null ? System.getProperty("Env") : (String) prop.getProperty("Env");
		return Env;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test Passed");
		
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (getEnv().equalsIgnoreCase("Browserstack")) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript(
						"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All assertions passed for: " +result.getMethod().getMethodName()+ "\"}}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.fail(result.getThrowable());

		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		try {
//			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),
//					result.getMethod().getMethodName());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
