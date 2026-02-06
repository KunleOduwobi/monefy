package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class OnboardingPage extends AndroidActions {

	public OnboardingPage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	AndroidDriver driver;

	private final By HeaderText = By.id("com.monefy.app.lite:id/textViewHeader");

	private final By ContinueBtn = By.id("com.monefy.app.lite:id/buttonContinue");

	public void assertFirstSlide() {
//		 Assert first slide
		String header = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header, "Say hi to your new finance tracker");
		String continueBtnText = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText, "GET STARTED");
		driver.findElement(ContinueBtn).click();
	}

// Assert second slide
	public void assertSecondSlide() {
//	sleep for 1 second because the slides have the same element ids 
//	and we need to wait for the second slide to load before asserting
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String header2 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header2, "Control your spend and start saving");
		String continueBtnText2 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText2, "AMAZING");
		driver.findElement(ContinueBtn).click();
	}

//	Assert third slide
	public void assertThirdSlide() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String header3 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header3, "Great! Want us to send you small reminders?");
		String continueBtnText3 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText3, "YES, PLEASE!");
		driver.findElement(ContinueBtn).click();
	}

//	Allow Monefy to send notifications if alert appears
	public void allowNotifications() {
		try {
			driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		} catch (Exception e) {
			System.out.println("No notification permission prompt displayed");
		}
	}

//	Assert fourth slide
	public void assertFourthSlide() {
		String header4 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header4, "Together we’ll reach your financial goals");
		String continueBtnText4 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText4, "I’M READY");
		driver.findElement(ContinueBtn).click();

	}

}