package androidTests;

import utils.AndroidSetup;

public class ViewSlides extends AndroidSetup {

	boolean fast;

	@Parameters({ "BStackDeviceOS" })
	@Test(description = "Verify that a user can skip welcome tour", groups = { "SkipOnboarding.skipWelcomeTour" })
	public void skipWelcomeTour(String BStackDeviceOS) throws IOException {

		OnboardingPage onboardingPage = new OnboardingPage(driver);
		NTPPage ntpPage = new NTPPage(driver);

		Assert.assertEquals(onboardingPage.getFirstrunText(),
				"The simplest way to be @ climate-active every day while @ browsing the web");
		PromoDialog promoDialog = onboardingPage.skipTour();

		if (!TestName.equals("Promo Dialog Test")) {
			if (onboardingPage.getFloatNumber(BStackDeviceOS) < 10.0) {
				ntpPage.clickCloseTooltip();
				ntpPage.customWaitForWebElement(ntpPage.getEcosiaLogo(), 60);

			} else {
//				if (onboardingPage.getFloatNumber(BStackDeviceOS) < 13.0) { // TEMP*****
				promoDialog.customWaitForWebElement(promoDialog.getPromoDialog(), 5);
				Assert.assertEquals(promoDialog.getPromoDialog().isDisplayed(), true);
				promoDialog.clickPromoLaterBtn();
				if (!TestName.equals("Ntp Test"))
					ntpPage.clickCloseTooltip();
//				}
			}

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

			try {
				long startTime = System.currentTimeMillis();
				ntpPage.customWaitForWebElement(ntpPage.getNewsImageItem(), 5);
				long estimatedTime = System.currentTimeMillis() - startTime;
				if (estimatedTime > 5000) {
					fast = false;
				} else {
					fast = true;
				}
			} catch (Exception e) {
				fast = false;

			}
		}

	}

}
