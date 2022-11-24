package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

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

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		acc = Accounts.NEW_ACCOUNT;
		acc.generateRandomEmail();
		invalidEmail = "afc@afc#$%";
		notExistEmail = "afc@mail.com";

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomepage(driver);

		System.out.println("Pre-condition: Register an account successfully!");
		registerPage = homePage.clickRegisterLink();
		registerPage.registerAnAccount(Accounts.NEW_ACCOUNT);
		homePage = registerPage.clickLogOutLink();
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickLoginLink();
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickLoginLink();
		loginPage.inputEmail(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Registered() {
		loginPage = homePage.clickLoginLink();
		loginPage.inputEmail(notExistEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void Login_04_Empty_Password() {
		loginPage = homePage.clickLoginLink();
		loginPage.inputEmail(acc.getEmail());
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		loginPage = homePage.clickLoginLink();
		loginPage.inputEmail(acc.getEmail());
		loginPage.inputPassword(invalidEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessfulLoginErrorMsg(),
				"Login was unsuccessful. Please correct the errors and try again.\n"
						+ "The credentials provided are incorrect");
	}

	@Test
	public void Login_06_Success() {
		loginPage = homePage.clickLoginLink();
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
