package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageUIs.HomePageUI.Top_Menu_Xpaths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestLogin extends BaseTest {
	private WebDriver driver;
	private Accounts acc;
	private String invalidEmail, notExistEmail;

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters({"browser", "baseUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String baseUrl) {
		acc = Accounts.NEW_ACCOUNT;
		invalidEmail = "afc@afc#$%";
		notExistEmail = "afc@mail.com";
		
		driver = getBrowserDriver(browserName, baseUrl);
		homePage = PageGeneratorManager.getHomepage(driver);

		System.out.println("Pre-condition: Register an account successfully!");
		registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		registerPage.registerAnAccount(Accounts.NEW_ACCOUNT);
		homePage = registerPage.clickTopMenuLink(Top_Menu_Xpaths.LOGOUT_LINK);
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Registered() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(notExistEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.inputPassword(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
		loginPage.inputEmail(acc.getEmail());
		loginPage.inputPassword(acc.getPassword());
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
