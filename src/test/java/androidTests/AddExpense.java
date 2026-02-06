package androidTests;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Dashboard;
import pages.NewExpensePage;

public class AddExpense extends ViewSlides {
	
	@Parameters({ "Currency" })
	@Test(description = "Verify that a user can add Expense successfully", dependsOnGroups = {
	"ViewSlides.viewSlides" }, groups = { "AddExpense.addExpense", "regression", "smoke", "functional" })
	public void addExpense(String Currency) throws IOException {
		Dashboard dashboard = new Dashboard(driver);
		NewExpensePage expense = new NewExpensePage(driver);
		String expenseAmount = "14.22";
		
		dashboard.initialIncomeVerification(Currency);
		String currentDisplayedExpense = dashboard.getCurrentDisplayedExpense(Currency);
		String currentDisplayedBalance = dashboard.getCurrentDisplayedBalance(Currency);
		dashboard.clickAddExpense();
		expense.enterExpenseAmount(expenseAmount);
		expense.verifyExpenseAmount(expenseAmount);
		expense.enterExpenseNote("Eating out");
		expense.clickChooseCategory();
		expense.selectExpenseCategory("Eating out");
		dashboard.balanceAmountVerification(Currency, currentDisplayedBalance, expenseAmount, "expense");
		dashboard.expenseAmountVerification(Currency, currentDisplayedExpense, expenseAmount);
	}

}
