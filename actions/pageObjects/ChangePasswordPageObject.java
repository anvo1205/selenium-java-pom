package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUIs.ChangePasswordPageUI;

public class ChangePasswordPageObject extends MyAccountPageObject{
	public ChangePasswordPageObject(WebDriver driver) {
		super(driver);
	}

	public void ChangePassword(String oldPass, String newPass) {
		waitForElementVisible(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX);
		sendKeysToElement(driver,  ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, oldPass);
		sendKeysToElement(driver,  ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, newPass);
		sendKeysToElement(driver,  ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX, newPass);
		clickElement(driver, ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public String getChangePasswordSuccessMessage() {
		return getText(driver, ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
	}
	
	public void closeSuccessMessage() {
		waitForElementClikable(driver, ChangePasswordPageUI.CLOSE_SUCCESS_MESSAGE_BUTTON);
		clickElement(driver, ChangePasswordPageUI.CLOSE_SUCCESS_MESSAGE_BUTTON);
	}

}
