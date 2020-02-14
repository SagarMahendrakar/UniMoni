package com.unimoni.setup;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import com.unimoni.Reporting.ExtentListener;

public class BaseSelenium {

	protected WebDriver driver;

	protected String classname;

	Assertion asert = new Assertion();

	protected BaseSelenium() {
		this.driver = DriverManager.getDriver();
	}

	protected void openUrl(String url) {

		driver.get(url);
		ExtentListener.testReport.get().info("Opening Url -" + url);

	}

	protected void waitExplicitlyForElementVisibility(String locator, int timeOutInSeconds) {

		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSeconds);

		explicitWait.until(ExpectedConditions.visibilityOf(getLocator(locator)));

	}

	protected void waitExplicitlyTillElementclickable(String locator, int timeOutInSeconds) {

		WebDriverWait explicitWait = new WebDriverWait(driver, timeOutInSeconds);

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

	}

	protected String getText(String locator) {

		return getLocator(locator).getText();

	}

	protected void assertTextFromLocator(String locator, String expectedText) {

		String actualText = getLocator(locator).getText();
		asert.assertEquals(actualText, expectedText);
		ExtentListener.testReport.get().info("Opening page" + "");

	}

	protected void validate(Object actual, Object expected) {

		asert.assertEquals(actual, expected);
		ExtentListener.testReport.get().info("Actual and Expected values are matching" + "==Acual value =" + actual
				+ " and Expected value =" + expected);

	}

	protected void javaScriptExecorScrollByWebElement(String locator, String expectedText) {

		JavascriptExecutor executeScript = (JavascriptExecutor) driver;

		executeScript.executeScript("arguments[0].scrollIntoView(true);", getLocator(locator));

	}

	protected void javaScriptExecorScrollByPositiont(int x, int y) {

		JavascriptExecutor executeScript = (JavascriptExecutor) driver;

		executeScript.executeScript("javascript:window.scrollBy(250,350)");

	}

	protected void verifyTextFromLocator(String locator, String expectedText) {

		try {

			String actualText = getLocator(locator).getText();
			asert.assertEquals(actualText, expectedText);
			ExtentListener.testReport.get().info("Opening page" + "");

		} catch (Throwable e) {

			ExtentListener.testReport.get().info("Opening page" + "");
		}

	}

	protected void verifyElementisPresent(String locator, String message) throws Exception {

		try {

			getLocator(locator);

			ExtentListener.testReport.get().info(message + "-Passed");

		} catch (NoSuchElementException e) {

			ExtentListener.testReport.get().info(message + "-Failed");

			// Assert.fail("Element is not present");

			throw new Exception(message + "-Failed");

		}

	}

	protected void verifyElementisPresent(String locator) throws Exception {

		try {

			getLocator(locator);

		} catch (NoSuchElementException e) {

			ExtentListener.testReport.get().info(locator + "-is Not Present");

			// Assert.fail("Element is not present");

			throw new Exception("Edit link is Not Present");

		}

	}

	protected void verifyElementisNotPresent(String locator, String message) throws Exception {

		try {

			Thread.sleep(3000);

			getLocator(locator);

			throw new Exception("Element is presents--Locator Details-" + locator);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			ExtentListener.testReport.get().info(message + "Passed");
		} catch (Exception e) {
			System.out.println(e.getMessage());

			if (getLocator(locator).isDisplayed()) {

				ExtentListener.testReport.get().info(message + "failed");
				throw new Exception(e.getMessage());
			} else {
				ExtentListener.testReport.get().info(message + "Passed");

			}
		}

	}

	protected void click(String locator) {

		getLocator(locator).click();

		ExtentListener.testReport.get().info("Clicking on locator :>>>" + getText(locator) + " on page " + classname);

	}

	protected void switchToWindow(String windowId) {

		driver.switchTo().window(windowId);

	}

	protected Set<String> getAllWindows(String windowId) {

		return driver.getWindowHandles();

	}

	protected void enterText(String locator, String text, String textboxNname) {

		getLocator(locator).sendKeys(text);

		ExtentListener.testReport.get()
				.info("Entering '" + text + "' to textbox" + textboxNname + " on page " + classname);

	}

	protected void clearText(String locator) {

		getLocator(locator).clear();

	}

	protected void isElementDisplayed(String locator, String message) throws Exception {

		if (getLocator(locator).isDisplayed()) {

			ExtentListener.testReport.get().info(message + " -Passed");

		} else {

			ExtentListener.testReport.get().info(message + "-Failed");

			// Assert.fail("Element is not present");

			throw new Exception(message + "-Failed");

		}

	}

	protected void isElementEnabled(String locator) {

		getLocator(locator).isEnabled();

	}

	protected void isElementSelected(String locator) {

		getLocator(locator).isSelected();

	}

	protected void selectByVisibleText(String locator, String visibleText) {

		Select select = new Select(getLocator(locator));

		select.selectByVisibleText(visibleText);

	}

	protected void selectByValue(String locator, String value) {

		Select select = new Select(getLocator(locator));

		select.selectByVisibleText(value);

	}

	protected void selectByIndex(String locator, int index) {

		Select select = new Select(getLocator(locator));

		select.selectByIndex(index);

	}

	protected void mouseHover(String locator) {
		
		
		//String locatorName=getText(locator);

		// System.out.println(classname);

		Actions action = new Actions(driver);

		action.moveToElement(getLocator(locator)).build().perform();

		// ExtentListener.testReport.get().info("Hovering on locator>>> '" +
		// getText(locator) + " on page " + classname);

	//	ExtentListener.testReport.get().info("Hovering on locator>>> '" + locatorName);

	}

	protected void mouseHoverAndClick(String locator, String elementTobeclicked) {

		Actions action = new Actions(driver);

		action.moveToElement(getLocator(locator)).click(getLocator(locator)).build().perform();

	}

	protected WebElement getLocator(String locator) {

		if (locator.startsWith("//") || locator.startsWith("(//")) {

			return driver.findElement(By.xpath(locator));

		} else if (locator.startsWith("#")) {

			return driver.findElement(By.cssSelector(locator));

		} else if ((locator).toLowerCase().contains("link=")) {

			return driver.findElement(By.linkText(locator.substring(5)));

		} else if ((locator).toLowerCase().contains("partiallink=")) {

			return driver.findElement(By.linkText(locator.substring(5)));

		}

		throw new NoSuchElementException("Not able to identify the given locator >>>> " + locator);

	}

	protected List<WebElement> getLocators(String locator) {

		if (locator.startsWith("//")) {

			return driver.findElements(By.xpath(locator));

		} else if (locator.startsWith("#")) {

			return driver.findElements(By.cssSelector(locator));

		} else if ((locator).toLowerCase().contains("link=")) {

			return driver.findElements(By.linkText(locator.substring(5)));

		} else if ((locator).toLowerCase().contains("partiallink=")) {

			return driver.findElements(By.linkText(locator.substring(5)));

		}

		throw new NoSuchElementException("Not able to identify the given locator >>>> " + locator);

	}
}
