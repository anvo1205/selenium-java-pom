package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUIs.CustomerInforPageUI;
import utilities.Date_Util;

public class CustomerInforPageObject extends MyAccountPageObject{
	
	public CustomerInforPageObject(WebDriver driver) {
		super(driver);
	}

	public void selectGender(String gender) {
		waitForElementClikable(CustomerInforPageUI.getGenderXpath(gender));
		clickElement(CustomerInforPageUI.getGenderXpath(gender));
		
	}

	public void selectDOB(String dob) {
		String day = Date_Util.getDayOfMonth(dob);
		String month = Date_Util.getMonth(dob);
		String year = Date_Util.getYear(dob);
		waitForElementClikable(CustomerInforPageUI.DOB_DAY_DROPDOWN);
		selectValueInDropDown(CustomerInforPageUI.DOB_DAY_DROPDOWN, day);
		selectValueInDropDown(CustomerInforPageUI.DOB_MONTH_DROPDOWN, month.substring(0,1) + month.substring(1).toLowerCase());
		selectValueInDropDown(CustomerInforPageUI.DOB_YEAR_DROPDOWN, year);	
	}

	public void inputEmail(String updatedEmailAddress) {
		waitForElementClikable(CustomerInforPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(CustomerInforPageUI.EMAIL_TEXTBOX, updatedEmailAddress);
		
	}

	public void inputCompanyName(String companyName) {
		waitForElementClikable(CustomerInforPageUI.COMPANY_NAME_TEXTBOX);
		sendKeysToElement(CustomerInforPageUI.COMPANY_NAME_TEXTBOX, companyName);
		
	}

	public boolean isGenderSelected(String gender) {
		waitForElementVisible(CustomerInforPageUI.getGenderXpath(gender));
		return isElementSelected(CustomerInforPageUI.getGenderXpath(gender));
	}

	public String getFirstName() {
		waitForElementVisible(CustomerInforPageUI.FIRST_NAME_TEXTBOX);
		return getAttribute(CustomerInforPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastName() {
		waitForElementVisible(CustomerInforPageUI.LAST_NAME_TEXTBOX);
		return getAttribute(CustomerInforPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getEmail() {
		waitForElementVisible(CustomerInforPageUI.EMAIL_TEXTBOX);
		return getAttribute(CustomerInforPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getCompanyName() {
		waitForElementVisible(CustomerInforPageUI.COMPANY_NAME_TEXTBOX);
		return getAttribute(CustomerInforPageUI.COMPANY_NAME_TEXTBOX, "value");
	}

	public String getDOBDay() {
		return getSelectItemInDropDown(CustomerInforPageUI.DOB_DAY_DROPDOWN);
	}

	public String getDOBMonth() {
		return getSelectItemInDropDown(CustomerInforPageUI.DOB_MONTH_DROPDOWN);
	}

	public String getDOBYear() {
		return getSelectItemInDropDown(CustomerInforPageUI.DOB_YEAR_DROPDOWN);
	}

	public void clickSave() {
		waitForElementClikable(CustomerInforPageUI.SAVE_BUTTON);
		clickElement(CustomerInforPageUI.SAVE_BUTTON);
		
	}
}
