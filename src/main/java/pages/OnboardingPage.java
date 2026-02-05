package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
		customWaitForElement(HeaderText, 20);
		String header = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header, "Say hi to your new finance tracker");
		String continueBtnText = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText, "GET STARTED");
		driver.findElement(ContinueBtn).click();
	}

//Assert second slide
		public void assertSecondSlide() {	
		customWaitForElement(HeaderText, 2);
		String header2 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header2, "Control your spend and start saving");
		String continueBtnText2 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText2, "AMAZING");
		driver.findElement(ContinueBtn).click();
	}

//	Assert third slide
	public void assertThirdSlide() {
		customWaitForElement(HeaderText, 2);
		String header3 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header3, "Great! Want us to send you small reminders?");
		String continueBtnText3 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText3, "YES, PLEASE!");
		driver.findElement(ContinueBtn).click();
	}
	
//	Allow Monefy to send notifications if alert appears
	public void allowNotifications() {
		try {
			customWaitForElement(By.id("com.android.permissioncontroller:id/permission_allow_button"), 5);
			driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
		} catch (Exception e) {
			System.out.println("No notification permission prompt displayed");
		}
	}
	
//	Assert fourth slide
	public void assertFourthSlide() {
		customWaitForElement(HeaderText, 2);
		String header4 = driver.findElement(HeaderText).getText();
		Assert.assertEquals(header4, "Together we’ll reach your financial goals");
		String continueBtnText4 = driver.findElement(ContinueBtn).getText();
		Assert.assertEquals(continueBtnText4, "I’M READY");
		driver.findElement(ContinueBtn).click();
	}


	

}