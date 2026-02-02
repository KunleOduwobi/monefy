package utils;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidActions extends AppiumUtils {

	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void longPressAction(WebElement element) {

	}

	// Custom wait for element
	public void customWaitForElement(By by, int secs) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) (by)));
	}

	public void customWaitForElementInvisibilty(By by, int secs) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By) (by)));
	}

	public void customWaitForWebElement(WebElement ele, int secs) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void customWaitForWebElementInvisibilty(WebElement ele, int secs) {
		if (Arrays.asList(ele).size() > 0) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
			wait.until(ExpectedConditions.invisibilityOf(ele));
		}
	}

//	Scroll
	public void scrollToBottom(int Height) {
		boolean canScrollMore = true;
		do {
			System.out.println(canScrollMore);
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", Height, "direction", "down", "percent", 1.0));
			System.out.println(canScrollMore);

		} while (canScrollMore);
	}

	public boolean scrollToBottom2(double Height) {
		boolean canScrollMore = true;
		do {
			System.out.println(canScrollMore);
			try {
				canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
						ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 800, "direction", "down",
								"percent", Height));
			} catch (Exception e) {
				canScrollMore = false;
			}
			System.out.println(canScrollMore);

		} while (canScrollMore);
		return canScrollMore;
	}

	public void findScrollHeight() {
		for (double Height = 1.0; Height < 10.0; Height += 1.0) {
			System.out.println("--------------" + Height);
			if (scrollToBottom2(Height) != true) {
				System.out.println("Failed height: " + Height);
			}
		}
	}

	public void scrollToText(String Text) {
		String automatorString = String.format("new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"));",
				Text);

		driver.findElement(AppiumBy.androidUIAutomator(automatorString));

	}

	public void scrollToText2(String Text) {
		String automatorString = String.format(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"%s\").instance(0));",
				Text);

		driver.findElement(AppiumBy.androidUIAutomator(automatorString));

	}

	public void scrollForward1(String Text) {

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + Text + "\").instance(0))"));

	}

	public void scrollForward2(String Text) {

		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).description(\"content-desc\"))"
						+ ".scrollIntoView(new UiSelector()" + ".textMatches(\"" + Text + "\").instance(0))"));

	}

	public void scrollForward3(String Text) {

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".setSwipeDeadZonePercentage(50).scrollIntoView(new UiSelector()" + ".textMatches(\"" + Text
				+ "\").instance(0))"));

	}

	public void clickHardBackButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void clickEnterButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	public void clickDeleteButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.DEL));
	}

	public void clickDeviceHomeButton() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
	}

	public boolean stringHasNumber(String text) {
		return text.matches(".*\\d.*");
	}

	public float getFloatNumber(String text) {
		return Float.parseFloat(text.replaceAll("[^\\d.]", ""));
	}

	public int getIntNumber(String text) {
		return Integer.parseInt(text.replaceAll("[^0-9]", ""));
	}

	public Long getLongNumber(String text) {
		return Long.parseLong(text.replaceAll("[^0-9]", ""));
	}

	public void dragAndDrop(WebElement From, WebElement To) {
		Actions builder = new Actions(driver);
		// Building a drag and drop action
		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();

		// Performing the drag and drop action
		dragAndDrop.perform();
	}

	public void dragAndDropOffset(WebElement From, int X, int Y) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(From).moveByOffset(X, Y).release().build();
		dragAndDrop.perform();
	}

	public void clickAndHold(WebElement Element) {
		Actions builder = new Actions(driver);
		// Building a drag and drop action
		Action clickAndHold = builder.clickAndHold(Element).pause(500).release(Element).build();

		// Performing the drag and drop action
		clickAndHold.perform();
	}

	public void clickAndHoldTime(WebElement Element, int ms) {
		Actions builder = new Actions(driver);
		// Building a drag and drop action
		Action clickAndHold = builder.clickAndHold(Element).pause(ms).release(Element).build();

		// Performing the drag and drop action
		clickAndHold.perform();
	}



}