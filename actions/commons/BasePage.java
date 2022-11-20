package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private WebDriverWait explicitWait;
	private Select select;
	private JavascriptExecutor js;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;

	/*
	 * ************************************************************************
	 * ************************************************************************ 
	 * This section contains common methods that driver interacts with Browsers
	 * ************************************************************************
	 * ************************************************************************
	 */

	/**
	 * Go to a specified Url
	 * 
	 * @param driver
	 * @param url
	 */
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * Get current window title
	 * 
	 * @param driver
	 * @return String page title
	 */
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * Get current url
	 * 
	 * @param driver
	 * @return String
	 */
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * Go back to previous page
	 * 
	 * @param driver
	 */
	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * Refresh the page
	 * 
	 * @param driver
	 */
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Go forward
	 * 
	 * @param driver
	 */
	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * Accept browser alert
	 * 
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss the browser alert
	 * 
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * Type a string to browser alert
	 * 
	 * @param driver
	 * @param value
	 */
	public void sendKeysToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/**
	 * Get a string from browser alert
	 * 
	 * @param driver
	 * @return String
	 */
	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	/**
	 * Wait for browser alert presence
	 * 
	 * @param driver
	 */
	public void waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Switch to another window by ID
	 * 
	 * @param driver
	 * @param parentID
	 */
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
			}
		}
	}

	/**
	 * Switch to another window by window title
	 * 
	 * @param driver
	 * @param title
	 */
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	/**
	 * This method will close all windows except the parents. Then, it check if
	 * parent window still opens at the end
	 * 
	 * @param driver
	 * @param parentID The ID of parent window you want to keep open
	 */
	public boolean areAllWindowsClosedButParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}

		else {
			return false;
		}
	}

	/*
	 * ************************************************************************
	 * ************************************************************************ 
	 * This section contains common methods that driver interacts with web elements
	 * ************************************************************************
	 * ************************************************************************
	 */

	/**
	 * Capture element by xpath and return By object
	 * 
	 * @param xpath
	 * @return By
	 */
	public By byXpath(String xpath) {
		return By.xpath(xpath);
	}

	/**
	 * Capture element by xpath and return WebElement object
	 * 
	 * @param driver
	 * @param xpath
	 * @return WebElement
	 */
	public WebElement findElementByXpath(WebDriver driver, String xpath) {
		try {
			return driver.findElement(byXpath(xpath));
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found!");
			return null;
		}

	}

	/**
	 * Capture web elements by xpath and return list of WebElement objects
	 * 
	 * @param driver
	 * @param xpath
	 * @return List<WebElement> A list of web elements having the same xpath
	 */
	public List<WebElement> findElementsByXpath(WebDriver driver, String xpath) {
		return driver.findElements(byXpath(xpath));
	}

	/**
	 * Click on a web element after waiting for it visible
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElement(WebDriver driver, String xpath) {
		waitForElementVisible(driver, xpath);
		findElementByXpath(driver, xpath).click();
	}

	/**
	 * Input value to a textbox after clearing the old value
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysToElement(WebDriver driver, String xpath, String value) {
		waitForElementVisible(driver, xpath);
		element = findElementByXpath(driver, xpath);
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Get text from a web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getText(WebDriver driver, String xpath) {
		return findElementByXpath(driver, xpath).getText().trim();
	}

	/**
	 * Get attribute value of a web element
	 * 
	 * @param driver
	 * @param xpath
	 * @return String
	 */
	public String getAttribute(WebDriver driver, String xpath, String attribute) {
		return findElementByXpath(driver, xpath).getAttribute(attribute);
	}

	/**
	 * Select an item from drop-down using Select library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void selectValueInDropDown(WebDriver driver, String xpath, String value) {
		select = new Select(findElementByXpath(driver, xpath));
		select.selectByVisibleText(value);
	}

	/**
	 * Get text of 1st selected item in a drop-down
	 * 
	 * @param driver
	 * @param xpath  xPath of the item in drop-down
	 * @return String Text of 1st selected item in dropdown
	 */
	public String getSelectItemInDropDown(WebDriver driver, String xpath) {
		select = new Select(findElementByXpath(driver, xpath));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Select an item from a drop-down that the Select method does not support It
	 * will click on the drop-down, wait until items visible, scroll to the item and
	 * click on the item using js click
	 * 
	 * @param driver
	 * @param parentsXpath  xPath of the drop-down
	 * @param childrenXpath xPath of the items in the drop-down
	 */
	public void selectItemFromCustomDropdown(WebDriver driver, String parentsXpath, String childrenXpath,
			String selectedItem) {
		element = findElementByXpath(driver, parentsXpath);
		js = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
		js.executeScript("arguments[0].click();", element);
		waitInSeconds(1);
		elements = findElementsByXpath(driver, childrenXpath);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childrenXpath)));
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(selectedItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					waitInSeconds(1);
					js.executeScript("arguments[0].click();", element);
				}
				waitInSeconds(1);
				break;
			}
		}

	}

	/**
	 * Counts number of elements having the same xPath
	 * 
	 * @param driver
	 * @param xpath
	 * @return int Number of elements
	 */
	public int countElements(WebDriver driver, String xpath) {
		return findElementsByXpath(driver, xpath).size();
	}

	/**
	 * Change status of a checkbox, check the current status before setting
	 * 
	 * @param driver
	 * @param xpath
	 * @param status true or false
	 */
	public void setCheckbox(WebDriver driver, String xpath, boolean status) {
		element = findElementByXpath(driver, xpath);
		if (element.isSelected() != status)// status is expected status, if current != expected -> click to change
											// status
		{
			element.click();
		}
	}

	/**
	 * Check if a web element is displayed in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementDisplayed(WebDriver driver, String xpath) {
		try {
			return findElementByXpath(driver, xpath).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed!");
			return false;
		}
	}

	/**
	 * Check if a web element is displayed in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementEnabled(WebDriver driver, String xpath) {
		return findElementByXpath(driver, xpath).isEnabled();
	}

	/**
	 * Check if a web element is displayed & enabled in the DOM & UI
	 * 
	 * @param driver
	 * @param xpath
	 * @return Boolean return true if element is displayed & enabled in DOM & UI
	 */
	public boolean isElementSelected(WebDriver driver, String xpath) {
		return findElementByXpath(driver, xpath).isSelected();
	}

	/**
	 * Switch to iFrame by xpath
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void switchToFrameOrIframe(WebDriver driver, String xpath) {
		driver.switchTo().frame(findElementByXpath(driver, xpath));
	}

	/**
	 * Switch to the main window from iFrame
	 * 
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Move mouse to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void hoverMouseToElement(WebDriver driver, String xpath) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, xpath)).perform();
	}

	/**
	 * Double click on element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void doubleClickOnElement(WebDriver driver, String xpath) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, xpath)).perform();
	}

	/**
	 * Right click on element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void rightClickOnElement(WebDriver driver, String xpath) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, xpath)).perform();
	}

	/**
	 * Send specific key from keyboard to element using Actions library
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeyboardToElement(WebDriver driver, String xpath, String value) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, xpath), value).perform();
	}

	/**
	 * Execute javascript using Javascript Executor
	 * 
	 * @param driver
	 * @param javaScript
	 * @return Object depends on the returned value from javascript
	 */
	public Object executeJavaScript(WebDriver driver, String javaScript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaScript);
	}

	/**
	 * Click on element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void clickElementByJS(WebDriver driver, String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", findElementByXpath(driver, xpath));
	}

	/**
	 * Scroll to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void ScrollToElement(WebDriver driver, String xpath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, xpath));
	}

	/**
	 * Send keys to element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 */
	public void sendKeysByJS(WebDriver driver, String xpath, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver, xpath));
	}

	/**
	 * Remove an attribute of web element using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param attributeName
	 */
	public void removeAttributeByJS(WebDriver driver, String xpath, String attributeName) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", findElementByXpath(driver, xpath));
	}

	/**
	 * Verify text in inner text using Javascript Executor
	 * 
	 * @param driver
	 * @param xpath
	 * @param expectedText
	 * @return boolean return true if text matches with expectedText
	 */
	public boolean verifyTextInInnerText(WebDriver driver, String xpath, String expectedText) {
		js = (JavascriptExecutor) driver;
		String actualText = (String) js
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return actualText.equals(expectedText);
	}

	/**
	 * Scroll to bottom page using Javascript Executor
	 * 
	 * @param driver
	 */
	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * Wait until element visible in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementVisible(WebDriver driver, String xpath) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpath)));
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is not visible!");
		}

	}

	/**
	 * Wait until element invisible in UI but still present in DOM
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementInvisible(WebDriver driver, String xpath) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpath)));
	}

	/**
	 * Wait until element clickable in the UI
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementClikable(WebDriver driver, String xpath) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpath)));
	}

	/**
	 * Wait until element present in DOM
	 * 
	 * @param driver
	 * @param xpath
	 */
	public void waitForElementPresence(WebDriver driver, String xpath) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(xpath)));
	}

	/**
	 * Hard wait in seconds
	 * 
	 * @param seconds
	 */
	public void waitInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/******************************************************************************************
	 * Functions using rest parameter
	 ******************************************************************************************/

	/**
	 * Parse rest parameters to a string
	 * 
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public String castToObject(String xpath, String... restParams) {
		return String.format(xpath, (Object[]) restParams);
	}

	/**
	 * Click on an element identified by rest parameters
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public void clickElement(WebDriver driver, String xpath, String... restParams) {
		waitForElementVisible(driver, castToObject(xpath, restParams));
		findElementByXpath(driver, castToObject(xpath, restParams)).click();
	}

	/**
	 * Input value to a textbox after clearing the old value Use Rest Parameters in
	 * the element
	 * 
	 * @param driver
	 * @param xpath
	 * @param value
	 * @param restParams parameters used to identify the element
	 */
	public void sendKeysToElement(WebDriver driver, String xpath, String value, String... restParams) {
		waitForElementVisible(driver, castToObject(xpath, restParams));
		element = findElementByXpath(driver, castToObject(xpath, restParams));
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Check if a web element identified by rest paramters is displayed in the DOM &
	 * UI
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 * @return Boolean return true if element is displayed in DOM & UI
	 */
	public boolean isElementDisplayed(WebDriver driver, String xpath, String... restParams) {
		try {
			return findElementByXpath(driver, castToObject(xpath, restParams)).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed!");
			return false;
		}
	}
	
	/**
	 * Wait until element visible identified by rest parameters in UI, if not throw exception
	 * 
	 * @param driver
	 * @param xpath
	 * @param restParams parameters used to identify the element
	 */
	public void waitForElementVisible(WebDriver driver, String xpath, String... restParams) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.MAX_TIMEOUT);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(xpath, restParams))));
		} catch (ElementNotVisibleException e) {
			System.out.println("Element is not visible!");
		}

	}
}
