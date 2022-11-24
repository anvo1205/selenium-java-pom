package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	
	public  HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickRegisterLink() {
		waitForElementClikable(driver, HomePageUI.REGISTER_LINK);
		clickElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}

	public LoginPageObject clickLoginLink() {
		waitForElementClikable(driver, HomePageUI.LOGIN_LINK);
		clickElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
		
	}

	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public CustomerInforPageObject clickMyAccountLink() {
		waitForElementClikable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInforPage(driver);
	}
	
	public HomePageObject clickLogoutLink() {
		waitForElementClikable(driver, HomePageUI.LOGOUT_LINK);
		clickElement(driver, HomePageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomepage(driver);
	}
	
	

}
