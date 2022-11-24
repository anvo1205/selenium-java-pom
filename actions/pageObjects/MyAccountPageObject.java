package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.MyAcccountPageUI;

public class MyAccountPageObject extends BasePage{
	WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public CustomerInforPageObject navigateToCustomerInforPage() {
		waitForElementClikable(driver, MyAcccountPageUI.CUSTOMER_INFOR_LINK_MENU);
		clickElement(driver, MyAcccountPageUI.CUSTOMER_INFOR_LINK_MENU);
		return PageGeneratorManager.getCustomerInforPage(driver);
	}
	
	public AddressPageObject navigateToAddressPage() {
		waitForElementClikable(driver, MyAcccountPageUI.ADDRESS_LINK_MENU);
		clickElement(driver, MyAcccountPageUI.ADDRESS_LINK_MENU);
		return PageGeneratorManager.getAddressPage(driver);
	}
	
	public ChangePasswordPageObject navigateToChangePasswordPage() {
		waitForElementClikable(driver, MyAcccountPageUI.CHANGE_PASSWORD_LINK_MENU);
		clickElement(driver, MyAcccountPageUI.CHANGE_PASSWORD_LINK_MENU);
		return PageGeneratorManager.getChangePasswordPage(driver);
	}

}
