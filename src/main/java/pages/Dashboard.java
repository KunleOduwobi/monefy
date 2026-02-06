package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
	
//	Get currently displayed amount on the dashboard without the currency symbol for easier calculations
	public String getCurrentDisplayedBalance(String Currency) {
		WebElement balanceElement = driver.findElement(BalanceAmount);
		String balanceText = balanceElement.getText();
		return balanceText.replace("Balance ", "").replace(Currency, "").trim();
	}	
//	Verify Balance Amount
	public void balanceAmountVerification(String Currency, String currentDisplayedBalance, String IncomingAmount, String AmountType) {
		
		System.out.println("Current Balance: " + currentDisplayedBalance);
		System.out.println("Incoming Amount: " + IncomingAmount);
//		Calculate expected balance based on the type of amount being added (income or expense)
		BigDecimal currentBalance = new BigDecimal(currentDisplayedBalance);
		BigDecimal incomingAmount = new BigDecimal(IncomingAmount.replaceAll(",", ""));
		BigDecimal expectedBalance;
		
		if (AmountType.equalsIgnoreCase("income")) {
			expectedBalance = currentBalance.add(incomingAmount).setScale(2, RoundingMode.HALF_UP);
		} else if (AmountType.equalsIgnoreCase("expense")) {
			expectedBalance = currentBalance.subtract(incomingAmount).setScale(2, RoundingMode.HALF_UP);
		} else {
			throw new IllegalArgumentException("Invalid AmountType. Must be 'income' or 'expense'.");
		}
		
//		If the expected balance is negative, format it with the negative sign before the currency symbol
		String expectedBalanceStr;
		if (expectedBalance.compareTo(BigDecimal.ZERO) < 0) {
			expectedBalanceStr = "-" + Currency + expectedBalance.abs().toString();
		} else {
			expectedBalanceStr = Currency + expectedBalance.toString();
		}
//		get new balance text from the dashboard and assert it matches the expected balance
		WebElement balanceElement = driver.findElement(BalanceAmount);
		String balanceText = balanceElement.getText();
		Assert.assertEquals(balanceText, "Balance " + expectedBalanceStr, "Balance amount does not match expected value.");
	}

	public void initialIncomeVerification(String Currency) {
		// Verify Income Amount is displayed
		WebElement incomeElement = driver.findElement(IncomeAmount);
		Assert.assertTrue(incomeElement.isDisplayed(), "Income amount is not displayed on the dashboard.");

		// Verify initial income is 0.00
		String incomeText = incomeElement.getText();
		Assert.assertEquals(incomeText, Currency + "0.00", "Initial income is not 0.00");

	}
	
//	Get current displayed income
	public String getCurrentDisplayedIncome(String Currency) {
		WebElement incomeElement = driver.findElement(IncomeAmount);
		String incomeText = incomeElement.getText();
		return incomeText.replace(Currency, "").trim();
	}
	
//	Verify Income Amount
	public void incomeAmountVerification(String Currency, String currentDisplayedIncome,String addedIncome) {

		// Parse amounts using BigDecimal for precision
		BigDecimal displayedAmount = new BigDecimal(currentDisplayedIncome.replaceAll(",", ""));
		BigDecimal incomingAmount = new BigDecimal(addedIncome.replaceAll(",", ""));

		// Sum previous displayed amount and incoming expectedIncome
		BigDecimal summed = displayedAmount.add(incomingAmount).setScale(2, RoundingMode.HALF_UP);

		// Format summed amount to 2 decimal places
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String summedStr = df.format(summed);

		WebElement incomeElement = driver.findElement(IncomeAmount);
		String incomeText = incomeElement.getText();
		Assert.assertEquals(incomeText, Currency + summedStr, "Income amount does not match expected value.");
	}
	
	public void initialExpenseVerification(String Currency) {
		// Verify Expense Amount is displayed
		WebElement expenseElement = driver.findElement(ExpenseAmount);
		Assert.assertTrue(expenseElement.isDisplayed(), "Expense amount is not displayed on the dashboard.");

		// Verify initial expense is 0.00
		String expenseText = expenseElement.getText();
		Assert.assertEquals(expenseText, Currency + "0.00", "Initial expense is not 0.00");

	}
	
	public String getCurrentDisplayedExpense(String Currency) {
		WebElement expenseElement = driver.findElement(ExpenseAmount);
		String expenseText = expenseElement.getText();
		return expenseText.replace(Currency, "").trim();
	}
	
	public void expenseAmountVerification(String Currency, String currentDisplayedExpense,String addedExpense) {

		// Parse amounts using BigDecimal for precision
		BigDecimal displayedAmount = new BigDecimal(currentDisplayedExpense.replaceAll(",", ""));
		BigDecimal incomingAmount = new BigDecimal(addedExpense.replaceAll(",", ""));

		// Sum previous displayed amount and incoming expectedExpense
		BigDecimal summed = displayedAmount.add(incomingAmount).setScale(2, RoundingMode.HALF_UP);

		// Format summed amount to 2 decimal places
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String summedStr = df.format(summed);

		WebElement expenseElement = driver.findElement(ExpenseAmount);
		String expenseText = expenseElement.getText();
		Assert.assertEquals(expenseText, Currency + summedStr, "Expense amount does not match expected value.");
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