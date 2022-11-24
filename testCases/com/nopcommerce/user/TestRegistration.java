package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestRegistration extends BaseTest {
	private WebDriver driver;
	private Accounts acc;

	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		acc = Accounts.NEW_ACCOUNT;
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomepage(driver);

	}

	@Test
	public void Register_01_Empty_Data() {
		registerPage = homePage.clickRegisterLink();
		registerPage.clickRegisterButton();
		Assert.assertEquals(registerPage.getFirstNameErrorMsg(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMsg(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMsg(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage = homePage.clickRegisterLink();
		acc.setEmail("123@456#%*");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getEmailErrorMsg(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		registerPage = homePage.clickRegisterLink();
		acc.setEmail(Accounts.generateRandomEmail());
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getRegisterSuccessMsg(), "Your registration completed");
		homePage = registerPage.clickLogOutLink();
	}

	@Test
	public void Register_04_Email_Exist() {
		registerPage = homePage.clickRegisterLink();
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getEmailAlreadyExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Then_6_Characters() {
		registerPage = homePage.clickRegisterLink();
		acc.setPassword("12345");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getPasswordErrorMsg(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		registerPage = homePage.clickRegisterLink();		
		acc.setPassword("123456");
		acc.setConfirmPassword("654123");
		registerPage.registerAnAccount(acc);
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}
