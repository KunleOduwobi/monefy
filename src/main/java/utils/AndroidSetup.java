package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.thoughtworks.qdox.parser.ParseException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.net.URL;

public class AndroidSetup extends AppiumUtils{
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public String Env;
	public String TestName;

	@Parameters({ "NoReset", "BStackDevice", "BStackDeviceOS", "geoLocation", "networkProfile", "deviceOrientation",
			"locale", "language" })
	@BeforeClass
	public void ConfigureAppium(Boolean NoReset, String DeviceName, String DeviceOS, String GeoLocation,
			String NetworkProfile, String DeviceOrientation, String Locale, String Language, ITestContext iTestContext)
			throws IOException, InterruptedException, ParseException {

//		Local Config
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//data.properties");

		prop.load(fis);
		String Device = System.getProperty("AndroidDeviceName") != null ? System.getProperty("AndroidDeviceName")
				: (String) prop.getProperty("AndroidDeviceName");
		String App = System.getProperty("AndroidAppName") != null ? System.getProperty("AndroidAppName")
				: (String) prop.getProperty("AndroidAppName");
		String Emulator = (String) prop.getProperty("EmulatorName");
		Env = System.getProperty("Env") != null ? System.getProperty("Env") : (String) prop.getProperty("Env");

		String date = new SimpleDateFormat("d MMM yyyy - ").format(new Date());

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(Device);
		options.setApp(System.getProperty("user.dir") + "//src//main//java//resources//" + App);
		options.setAppPackage(System.getProperty("user.dir") + "//src//main//java//resources//AppPackage");
		options.setAppActivity(System.getProperty("user.dir") + "//src//main//java//resources//AppActivity");
		options.setAutoGrantPermissions(true);
		options.setNoReset(NoReset);

		DesiredCapabilities capabilities = new DesiredCapabilities();


			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		TestName = iTestContext.getName();
		System.out.println(TestName + " running on: " + Env);

	}

	@AfterClass
	public void shutDown() {
		driver.quit();
	}

}
