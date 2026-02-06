package pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class NewExpensePage extends AndroidActions {

	public NewExpensePage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	AndroidDriver driver;

	private final By ExpenseAmountField = By.id("com.monefy.app.lite:id/amount_text");
	private final By NoteField = By.id("com.monefy.app.lite:id/textViewNote");
	private final By ButtonKeyboard0 = By.id("com.monefy.app.lite:id/buttonKeyboard0");
	private final By ButtonKeyboard1 = By.id("com.monefy.app.lite:id/buttonKeyboard1");
	private final By ButtonKeyboard2 = By.id("com.monefy.app.lite:id/buttonKeyboard2");
	private final By ButtonKeyboard3 = By.id("com.monefy.app.lite:id/buttonKeyboard3");
	private final By ButtonKeyboard4 = By.id("com.monefy.app.lite:id/buttonKeyboard4");
	private final By ButtonKeyboard5 = By.id("com.monefy.app.lite:id/buttonKeyboard5");
	private final By ButtonKeyboard6 = By.id("com.monefy.app.lite:id/buttonKeyboard6");
	private final By ButtonKeyboard7 = By.id("com.monefy.app.lite:id/buttonKeyboard7");
	private final By ButtonKeyboard8 = By.id("com.monefy.app.lite:id/buttonKeyboard8");
	private final By ButtonKeyboard9 = By.id("com.monefy.app.lite:id/buttonKeyboard9");
	private final By ButtonKeyboardDot = By.id("com.monefy.app.lite:id/buttonKeyboardDot");
	private final By ChooseCategoryButton = By.id("com.monefy.app.lite:id/keyboard_action_button");

	public void enterExpenseAmount(String amount) {
		// Enter Expense amount using the custom keyboard
		for (char digit : amount.toCharArray()) {
			switch (digit) {
			case '0':
				driver.findElement(ButtonKeyboard0).click();
				break;
			case '1':
				driver.findElement(ButtonKeyboard1).click();
				break;
			case '2':
				driver.findElement(ButtonKeyboard2).click();
				break;
			case '3':
				driver.findElement(ButtonKeyboard3).click();
				break;
			case '4':
				driver.findElement(ButtonKeyboard4).click();
				break;
			case '5':
				driver.findElement(ButtonKeyboard5).click();
				break;
			case '6':
				driver.findElement(ButtonKeyboard6).click();
				break;
			case '7':
				driver.findElement(ButtonKeyboard7).click();
				break;
			case '8':
				driver.findElement(ButtonKeyboard8).click();
				break;
			case '9':
				driver.findElement(ButtonKeyboard9).click();
				break;
			case '.':
				driver.findElement(ButtonKeyboardDot).click();
				break;
			default:
				throw new IllegalArgumentException("Invalid character in amount: " + digit);
			}
		}
	}

	public void verifyExpenseAmount(String expectedAmount) {
		String actualAmount = driver.findElement(ExpenseAmountField).getText();
		if (!actualAmount.equals(expectedAmount)) {
			throw new AssertionError(
					"Expense amount mismatch! Expected: " + expectedAmount + ", but found: " + actualAmount);
		}
	}

	public void enterExpenseNote(String note) {
		driver.findElement(NoteField).sendKeys(note);
	}

	public void clickChooseCategory() {
		driver.findElement(ChooseCategoryButton).click();
	}

	public void selectExpenseCategory(String categoryName) {
		scrollToText(categoryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + categoryName + "']")).click();
	}

}
