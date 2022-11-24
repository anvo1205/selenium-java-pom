package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import objects.Accounts;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegisterButton() {
		waitForElementClikable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}

	public String getFirstNameErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
		return getText(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
	}

	public String getLastNameErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
		return getText(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
		return getText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
	}

	public String getPasswordErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
		return getText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
	}

	public String getConfirmPasswordErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
		return getText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
	}

	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void inputLastName(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
		
	}

	public void inputEmail(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
		
	}

	public void inputPassword(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputConfirmPassword(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
		
	}

	public String getRegisterSuccessMsg() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MSG);
		return getText(driver, RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	public HomePageObject clickLogOutLink() {
		waitForElementClikable(driver, RegisterPageUI.LOG_OUT_LINK);
		clickElement(driver, RegisterPageUI.LOG_OUT_LINK);
		return PageGeneratorManager.getHomepage(driver);
		
	}

	public String getEmailAlreadyExistErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ALREADY_EXIST_ERROR_MSG);
		return getText(driver, RegisterPageUI.EMAIL_ALREADY_EXIST_ERROR_MSG);
	}
	
	public void registerAnAccount(Accounts acc) {
		inputFirstName(acc.getFirstName());
		inputLastName(acc.getLastName());
		inputEmail(acc.getEmail());
		inputPassword(acc.getPassword());
		inputConfirmPassword(acc.getPassword());  
		clickRegisterButton();
		
	}

}
