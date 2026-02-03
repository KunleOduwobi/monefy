package base;

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
import utils.AppiumUtils;

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
				System.getProperty("user.dir") + "//src//main//resources//data.properties");

		prop.load(fis);
		String Device = System.getProperty("AndroidDeviceName") != null ? System.getProperty("AndroidDeviceName")
				: (String) prop.getProperty("AndroidDeviceName");
		String App = System.getProperty("AndroidAppName") != null ? System.getProperty("AndroidAppName")
				: (String) prop.getProperty("AndroidAppName");
		String Emulator = (String) prop.getProperty("EmulatorName");
		Env = System.getProperty("Env") != null ? System.getProperty("Env") : (String) prop.getProperty("Env");

		// Read host/port and package/activity from properties (allow overrides via system properties)
		String ip = System.getProperty("IpAddress") != null ? System.getProperty("IpAddress")
				: (String) prop.getProperty("IpAddress");
		String port = System.getProperty("Port") != null ? System.getProperty("Port")
				: (String) prop.getProperty("Port");
		String appPackage = System.getProperty("AppPackage") != null ? System.getProperty("AppPackage")
				: (String) prop.getProperty("AppPackage");
		String appActivity = System.getProperty("AppActivity") != null ? System.getProperty("AppActivity")
				: (String) prop.getProperty("AppActivity");

		String date = new SimpleDateFormat("d MMM yyyy - ").format(new Date());

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(Device);
		// Only set the app path if an apk name is provided. When NoReset is true, prefer using the already-installed app.
		if (App != null && !App.trim().isEmpty() && !NoReset) {
			options.setApp(System.getProperty("user.dir") + "//src//main//resources//" + App);
		}
		// Use package/activity values from properties (not file paths)
		if (appPackage != null && !appPackage.trim().isEmpty()) {
			options.setAppPackage(appPackage);
		}
		if (appActivity != null && !appActivity.trim().isEmpty()) {
			options.setAppActivity(appActivity);
		}
		options.setAutoGrantPermissions(true);
		options.setNoReset(NoReset);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Build Appium server URL from properties (fallback to localhost:4723 if missing)
		if (ip == null || ip.trim().isEmpty()) {
			ip = "127.0.0.1";
		}
		if (port == null || port.trim().isEmpty()) {
			port = "4723";
		}

		String serverUrl = "http://" + ip + ":" + port;

		driver = new AndroidDriver(new URL(serverUrl), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		TestName = iTestContext.getName();
		System.out.println(TestName + " running on: " + Env);

	}

	@AfterClass
	public void shutDown() {
		driver.quit();
	}

}