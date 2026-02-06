package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Dashboard;

public class DashboardValidation extends ViewSlides {


	@Parameters({ "Currency" })
	@Test(description = "Verify that dashboard content is correct", dependsOnGroups = {
	"ViewSlides.viewSlides" }, groups = { "regression", "smoke" })
	public void dashboardValidation(String Currency) throws IOException {
		Dashboard dashboard = new Dashboard(driver);
		dashboard.verifyCurrentMonth();
		dashboard.initialBalanceVerification(Currency);
		dashboard.initialIncomeVerification(Currency);
		dashboard.initialExpenseVerification(Currency);
		
	}

}
