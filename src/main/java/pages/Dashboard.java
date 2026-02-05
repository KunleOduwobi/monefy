package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class Dashboard extends AndroidActions {

	public Dashboard(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	AndroidDriver driver;

	private final By CurrentMonth = By.xpath("//*[@resource-id='com.monefy.app.lite:id/pts_main']//android.widget.TextView");
	private final By BalanceAmount = By.id("com.monefy.app.lite:id/balance_amount");
	private final By IncomeAmount = By.id("com.monefy.app.lite:id/income_amount_text");
	private final By ExpenseAmount = By.id("com.monefy.app.lite:id/expense_amount_text");
	private final By AddExpenseBtn = By.id("com.monefy.app.lite:id/expense_button");
	private final By AddIncomeBtn = By.id("com.monefy.app.lite:id/income_button");

	public void verifyCurrentMonth() {
		// Verify Current Month is displayed
		WebElement monthElement = driver.findElement(CurrentMonth);
		Assert.assertTrue(monthElement.isDisplayed(), "Current month is not displayed on the dashboard.");

		// Verify that current month is correct
		String displayedMonth = monthElement.getText();
		java.time.Month currentMonth = java.time.LocalDate.now().getMonth();
		Assert.assertEquals(displayedMonth.toUpperCase(), currentMonth.toString().toUpperCase(),
				"Displayed month does not match the current month.");
	}

	public void initialBalanceVerification(String Currency) {
		// Verify Balance Amount is displayed
		WebElement balanceElement = driver.findElement(BalanceAmount);
		Assert.assertTrue(balanceElement.isDisplayed(), "Balance amount is not displayed on the dashboard.");

		// Verify initial balance is 0.00
		String balanceText = balanceElement.getText();
		Assert.assertEquals(balanceText, "Balance " + Currency + "0.00", "Initial balance is not 0.00");
	}
	
//	Verify Balance Amount
	public void balanceAmountVerification(String Currency, String expectedBalance) {
		WebElement balanceElement = driver.findElement(BalanceAmount);
		String balanceText = balanceElement.getText();
		Assert.assertEquals(balanceText, "Balance " + Currency + expectedBalance, "Balance amount does not match expected value.");
	}

	public void initialIncomeVerification(String Currency) {
		// Verify Income Amount is displayed
		WebElement incomeElement = driver.findElement(IncomeAmount);
		Assert.assertTrue(incomeElement.isDisplayed(), "Income amount is not displayed on the dashboard.");

		// Verify initial income is 0.00
		String incomeText = incomeElement.getText();
		Assert.assertEquals(incomeText, Currency + "0.00", "Initial income is not 0.00");

	}
	
//	Verify Income Amount
	public void incomeAmountVerification(String Currency, String expectedIncome) {
		WebElement incomeElement = driver.findElement(IncomeAmount);
		String incomeText = incomeElement.getText();
		Assert.assertEquals(incomeText, Currency + expectedIncome, "Income amount does not match expected value.");
	}
	
	public void initialExpenseVerification(String Currency) {
		// Verify Expense Amount is displayed
		WebElement expenseElement = driver.findElement(ExpenseAmount);
		Assert.assertTrue(expenseElement.isDisplayed(), "Expense amount is not displayed on the dashboard.");

		// Verify initial expense is 0.00
		String expenseText = expenseElement.getText();
		Assert.assertEquals(expenseText, Currency + "0.00", "Initial expense is not 0.00");

	}
	
	public void clickAddExpense() {
		WebElement addExpenseElement = driver.findElement(AddExpenseBtn);
		Assert.assertTrue(addExpenseElement.isDisplayed(), "Add Expense button is not displayed on the dashboard.");
		addExpenseElement.click();
	}
	
	public void clickAddIncome() {
		WebElement addIncomeElement = driver.findElement(AddIncomeBtn);
		Assert.assertTrue(addIncomeElement.isDisplayed(), "Add Income button is not displayed on the dashboard.");
		addIncomeElement.click();
	}

}