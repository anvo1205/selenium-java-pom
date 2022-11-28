package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUIs.ChangePasswordPageUI;

public class ChangePasswordPageObject extends MyAccountPageObject{
	public ChangePasswordPageObject(WebDriver driver) {
		super(driver);
	}

	public void ChangePassword(String oldPass, String newPass) {
		waitForElementVisible(ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX);
		sendKeysToElement( ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, oldPass);
		sendKeysToElement( ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, newPass);
		sendKeysToElement( ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX, newPass);
		clickElement(ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
	
	public String getChangePasswordSuccessMessage() {
		return getText(ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESS_MESSAGE);
	}
	
	public void closeSuccessMessage() {
		waitForElementClikable(ChangePasswordPageUI.CLOSE_SUCCESS_MESSAGE_BUTTON);
		clickElement(ChangePasswordPageUI.CLOSE_SUCCESS_MESSAGE_BUTTON);
	}

}
