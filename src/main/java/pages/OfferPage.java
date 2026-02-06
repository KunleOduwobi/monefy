package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class OfferPage extends AndroidActions {

	public OfferPage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	AndroidDriver driver;

	

	private final By Title = By.id("com.monefy.app.lite:id/textViewTitle");
	private final By CloseBtn = By.id("com.monefy.app.lite:id/buttonClose");

//	Assert offer page title
	public void assertOfferPageTitle() {
		String title = driver.findElement(Title).getText();
		Assert.assertEquals(title, "Claim your one-time welcome offer");
	}

 // close offer page
	public void closeOfferPage() {
		driver.findElement(CloseBtn).click();
	}
	

}