package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public abstract class AppiumUtils {

	public AppiumDriverLocalService service;

//	start Appium server
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port, String appiumVersion) {


		AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
		appiumServiceBuilder.usingPort(port).withIPAddress(ipAddress)
				.withAppiumJS(new File(System.getProperty("user.home")
						+ "//.nvm//versions//node//v24.13.0//lib//node_modules//appium//build//lib//appium.js"))
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withLogFile(new File(System.getProperty("user.dir")
						+ "/target/resources/appium_server_logs" + Thread.currentThread().getId()));
		service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
		service.start();
		return service;
	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		System.out.println("SCREENSHOT SAVED AS: " + testCaseName);
		FileUtils.copyFile(source, new File(destinationFile));
		System.out.println("FOLDER: " + System.getProperty("user.dir") + "/reports");
		return destinationFile;
	}
	
	public float getFloatNumber(String text) {
		return Float.parseFloat(text.replaceAll("[^\\d.]", ""));
	}

}
