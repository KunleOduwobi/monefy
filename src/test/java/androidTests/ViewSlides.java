package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.OfferPage;
import pages.OnboardingPage;

import base.AndroidSetup;

public class ViewSlides extends AndroidSetup {

	@Parameters({ "BStackDeviceOS" })
	@Test(description = "Verify that a user can view welcome tour", groups = { "ViewSlides.viewSlides", "regression", "smoke" })
	public void viewSlides(String BStackDeviceOS) throws IOException {

		OnboardingPage onboardingPage = new OnboardingPage(driver);
		OfferPage offerPage = new OfferPage(driver);
		onboardingPage.assertFirstSlide();
		onboardingPage.assertSecondSlide();
		onboardingPage.assertThirdSlide();
		onboardingPage.allowNotifications();
		onboardingPage.assertFourthSlide();
		offerPage.assertOfferPageTitle();
		offerPage.closeOfferPage();
	}

}
