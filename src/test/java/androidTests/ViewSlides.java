package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.OnboardingPage;

import base.AndroidSetup;

public class ViewSlides extends AndroidSetup {

	boolean fast;

	@Parameters({ "BStackDeviceOS" })
	@Test(description = "Verify that a user can view welcome tour", groups = { "regression", "smoke" })
	public void viewSlides(String BStackDeviceOS) throws IOException {

		OnboardingPage onboardingPage = new OnboardingPage(driver);
		
		onboardingPage.assertFirstSlide();
		onboardingPage.assertSecondSlide();
		onboardingPage.assertThirdSlide();
		onboardingPage.allowNotifications();
		onboardingPage.assertFourthSlide();
		

	}

}
