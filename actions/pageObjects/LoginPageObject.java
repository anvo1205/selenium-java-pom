package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickLoginButton() {
		waitForElementClikable(driver, LoginPageUI.LOGIN_BUTTON);
		clickElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomepage(driver);
		
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MSG);
		return getText(driver, LoginPageUI.EMAIL_ERROR_MSG);
	}

	public void inputEmail(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
		
	}

	public String getUnsuccessfulLoginErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MSG);
		return getText(driver, LoginPageUI.UNSUCCESSFUL_LOGIN_ERROR_MSG);
	}

	public void inputPassword(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	
	public HomePageObject login(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		clickLoginButton();
		return PageGeneratorManager.getHomepage(driver);
	}



}
