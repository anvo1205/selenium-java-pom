package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomepage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerInforPageObject getCustomerInforPage(WebDriver driver) {
		return new CustomerInforPageObject(driver);
	}

	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}
	
	public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}

}
