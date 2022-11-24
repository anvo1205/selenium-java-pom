package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUIs.CustomerInforPageUI;
import utilities.Date_Util;

public class CustomerInforPageObject extends MyAccountPageObject{
	
	public CustomerInforPageObject(WebDriver driver) {
		super(driver);
	}

	public void selectGender(String gender) {
		waitForElementClikable(driver, CustomerInforPageUI.getGenderXpath(gender));
		clickElement(driver, CustomerInforPageUI.getGenderXpath(gender));
		
	}

	public void selectDOB(String dob) {
		String day = Date_Util.getDayOfMonth(dob);
		String month = Date_Util.getMonth(dob);
		String year = Date_Util.getYear(dob);
		waitForElementClikable(driver, CustomerInforPageUI.DOB_DAY_DROPDOWN);
		selectValueInDropDown(driver, CustomerInforPageUI.DOB_DAY_DROPDOWN, day);
		selectValueInDropDown(driver, CustomerInforPageUI.DOB_MONTH_DROPDOWN, month.substring(0,1) + month.substring(1).toLowerCase());
		selectValueInDropDown(driver, CustomerInforPageUI.DOB_YEAR_DROPDOWN, year);	
	}

	public void inputEmail(String updatedEmailAddress) {
		waitForElementClikable(driver, CustomerInforPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, CustomerInforPageUI.EMAIL_TEXTBOX, updatedEmailAddress);
		
	}

	public void inputCompanyName(String companyName) {
		waitForElementClikable(driver, CustomerInforPageUI.COMPANY_NAME_TEXTBOX);
		sendKeysToElement(driver, CustomerInforPageUI.COMPANY_NAME_TEXTBOX, companyName);
		
	}

	public boolean isGenderSelected(String gender) {
		waitForElementVisible(driver, CustomerInforPageUI.getGenderXpath(gender));
		return isElementSelected(driver, CustomerInforPageUI.getGenderXpath(gender));
	}

	public String getFirstName() {
		waitForElementVisible(driver, CustomerInforPageUI.FIRST_NAME_TEXTBOX);
		return getAttribute(driver, CustomerInforPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastName() {
		waitForElementVisible(driver, CustomerInforPageUI.LAST_NAME_TEXTBOX);
		return getAttribute(driver, CustomerInforPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getEmail() {
		waitForElementVisible(driver, CustomerInforPageUI.EMAIL_TEXTBOX);
		return getAttribute(driver, CustomerInforPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getCompanyName() {
		waitForElementVisible(driver, CustomerInforPageUI.COMPANY_NAME_TEXTBOX);
		return getAttribute(driver, CustomerInforPageUI.COMPANY_NAME_TEXTBOX, "value");
	}

	public String getDOBDay() {
		return getSelectItemInDropDown(driver, CustomerInforPageUI.DOB_DAY_DROPDOWN);
	}

	public String getDOBMonth() {
		return getSelectItemInDropDown(driver, CustomerInforPageUI.DOB_MONTH_DROPDOWN);
	}

	public String getDOBYear() {
		return getSelectItemInDropDown(driver, CustomerInforPageUI.DOB_YEAR_DROPDOWN);
	}

	public void clickSave() {
		waitForElementClikable(driver, CustomerInforPageUI.SAVE_BUTTON);
		clickElement(driver, CustomerInforPageUI.SAVE_BUTTON);
		
	}
}
