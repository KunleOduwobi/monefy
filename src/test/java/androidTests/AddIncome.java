package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Dashboard;
import pages.NewIncomePage;

public class AddIncome extends ViewSlides {
	
	@Parameters({ "Currency" })
	@Test(description = "Verify that a user can add income successfully", dependsOnGroups = {
	"ViewSlides.viewSlides" }, groups = { "AddIncome.addIncome", "regression", "smoke", "functional" })
	public void addIncome(String Currency) throws IOException {
		Dashboard dashboard = new Dashboard(driver);
		NewIncomePage income = new NewIncomePage(driver);
		String incomeAmount = "500.50";
		
		dashboard.initialIncomeVerification(Currency);
		String currentDisplayedIncome = dashboard.getCurrentDisplayedIncome(Currency);
		dashboard.clickAddIncome();
		income.enterIncomeAmount(incomeAmount);
		income.verifyIncomeAmount(incomeAmount);
		income.enterIncomeNote("Salary for June");
		income.clickChooseCategory();
		income.selectIncomeCategory("Salary");
		dashboard.balanceAmountVerification(Currency, incomeAmount);
		dashboard.incomeAmountVerification(Currency, currentDisplayedIncome, incomeAmount);
	}

}
