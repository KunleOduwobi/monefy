package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.Onboarding;

import base.AndroidSetup;

public class ViewSlides extends AndroidSetup {

	boolean fast;

	@Parameters({ "BStackDeviceOS" })
	@Test(description = "Verify that a user can view welcome tour", groups = { "ViewSlides.viewSlides" })
	public void viewSlides(String BStackDeviceOS) throws IOException {

		Onboarding onboardingPage = new Onboarding(driver);
//		NTPPage ntpPage = new NTPPage(driver);

//		Assert.assertEquals(onboardingPage.getFirstrunText(),
//				"The simplest way to be @ climate-active every day while @ browsing the web");
//		PromoDialog promoDialog = onboardingPage.skipTour();

		

//			if (onboardingPage.getFloatNumber(BStackDeviceOS) > 12.0) {
//				try {
//					onboardingPage.customWaitForWebElement(onboardingPage.getNewNotificationPermissionDialog(), 30);
//					onboardingPage.clickNewNotificationPermissionDialogContinueBtn();
//
//					onboardingPage.customWaitForWebElement(onboardingPage.getNotificationPermissionDialog(), 5);
//					onboardingPage.clickNotificationPermissionAllow();
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//				// TEMP*****
//				if (!TestName.equals("Ntp Test"))
//					ntpPage.clickCloseTooltip();
//			}

//			try {
//				long startTime = System.currentTimeMillis();
//				ntpPage.customWaitForWebElement(ntpPage.getNewsImageItem(), 5);
//				long estimatedTime = System.currentTimeMillis() - startTime;
//				if (estimatedTime > 5000) {
//					fast = false;
//				} else {
//					fast = true;
//				}
//			} catch (Exception e) {
//				fast = false;
//
//			}
//		}

	}

}
