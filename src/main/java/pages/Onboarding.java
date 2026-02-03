package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class Onboarding extends AndroidActions {

	public Onboarding(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	AndroidDriver driver;

	

	private final By FirstrunContainer = By.id("com.ecosia.android:id/ecosia_firstrun_pager_container");
	private final By FirstrunLogo = By.id("com.ecosia.android:id/firstrun_logo");
	private final By FirstrunText = By.id("com.ecosia.android:id/firstrun_text");
	private final By GetStartedBtn = By.id("com.ecosia.android:id/firstrun_continue_button");
	private final By SkipTourBtn = By.id("com.ecosia.android:id/firstrun_skip_button");
	private final By Headline = By.id("com.ecosia.android:id/ecosia_firstrun_page_headline");
	private final By PageText = By.id("com.ecosia.android:id/ecosia_firstrun_page_text");
	private final By Pg1Card1 = By.id("com.ecosia.android:id/ecosia_firstrun_proof_1_card_a");
	private final By Pg1Card2 = By.id("com.ecosia.android:id/ecosia_firstrun_proof_1_card_b");
	private final By Pg2ProofCard = By.id("com.ecosia.android:id/ecosia_firstrun_fragment_proof_before_after");
	private final By Pg3ProofCardA = By.id("com.ecosia.android:id/ecosia_firstrun_proof_3_card_a");
	private final By Pg3ProofCardB = By.id("com.ecosia.android:id/ecosia_firstrun_proof_3_card_b");
	private final By Pg3ProofCardCImage = By.id("com.ecosia.android:id/onboarding_proof_3_card_c_image");
	private final By Pg4Image = By.id("com.ecosia.android:id/ecosia_firstrun_page_content_bg");

	private final By SkipBtn = By.id("com.ecosia.android:id/ecosia_firstrun_skip_button");
	private final By ContinueBtn = By.id("com.ecosia.android:id/ecosia_firstrun_continue_button");

	public WebElement getFirstrunContainer() {
		return driver.findElement(FirstrunContainer);
	}

	public String getFirstrunText() {
		customWaitForElement(FirstrunLogo, 50);
		return driver.findElement(FirstrunText).getText();
	}

	public void startTour() {
		driver.findElement(GetStartedBtn).click();
	}

	

//	method to perform an action an return another page
//	public PromoDialog skipTour() {
//		driver.findElement(SkipTourBtn).click();
//		return new PromoDialog(driver);
//	}

	

}