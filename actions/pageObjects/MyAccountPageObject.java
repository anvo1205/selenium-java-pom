package pageObjects;

import org.openqa.selenium.WebDriver;

import pageUIs.MyAcccountPageUI.Left_Menu_Xpaths;

public class MyAccountPageObject extends HomePageObject {
	public MyAccountPageObject(WebDriver driver) {
		super(driver);
	}

	public MyAccountPageObject NavigateToLeftMenuPage(Left_Menu_Xpaths pageName) {
		waitForElementClikable(pageName.getPageNameXpath());
		clickElement(pageName.getPageNameXpath());
		switch (pageName) {
		case CUSTOMER_INFO:
			return PageGeneratorManager.getCustomerInforPage(driver);
		case ADDRESSES:
			return PageGeneratorManager.getAddressPage(driver);
		case CHANGE_PASSWORD:
			return PageGeneratorManager.getChangePasswordPage(driver);
		default:
			throw new RuntimeException("INvalid page name!");
		}
	}
}
