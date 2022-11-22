package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;

	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	  @BeforeClass
	  public void beforeClass() {
		  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		  driver = new ChromeDriver();
		  
		  homePage = new HomePageObject(driver);
		  registerPage = new RegisterPageObject(driver);
		  

		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.get("https://demo.nopcommerce.com/");
		  
		  firstName = "Automation";
		  lastName = "FC";
		  emailAddress = "afc" + generateFakeNumber() + "@mail.ca";
		  password = "123456";
	  }
	  
  @Test
  public void TC_01_Register_Empty_Data() {
	  homePage.clickRegisterLink();
	  
	  registerPage.clickRegisterButton();
	  
	  Assert.assertEquals(registerPage.getFirstNameErrorMsg(), "First name is required.");
	  Assert.assertEquals(registerPage.getLastNameErrorMsg(), "Last name is required.");
	  Assert.assertEquals(registerPage.getEmailErrorMsg(), "Email is required.");
	  Assert.assertEquals(registerPage.getPasswordErrorMsg(), "Password is required.");
	  Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "Password is required.");
  }
  
  @Test
  public void TC_02_Register_Invalid_Email() {
	  homePage.clickRegisterLink();
	  
	  registerPage.inputFirstName(firstName);
	  registerPage.inputLastName(lastName);
	  registerPage.inputEmail("123@456#%*");
	  registerPage.inputPassword(password);
	  registerPage.inputConfirmPassword(password);
	  
	  registerPage.clickRegisterButton();
	  

	  Assert.assertEquals(registerPage.getEmailErrorMsg(), "Wrong email");
  }
  
  @Test
  public void TC_03_Register_Success() {
	  homePage.clickRegisterLink();
	  
	  registerPage.inputFirstName(firstName);
	  registerPage.inputLastName(lastName);
	  registerPage.inputEmail(emailAddress);
	  registerPage.inputPassword(password);
	  registerPage.inputConfirmPassword(password);
	  
	  registerPage.clickRegisterButton(); 

	  Assert.assertEquals(registerPage.getRegisterSuccessMsg(), "Your registration completed");
	  
	  registerPage.clickLogOutLink();
  }
  
  @Test
  public void TC_04_Register_Email_Exist() {
	  homePage.clickRegisterLink();
	  
	  registerPage.inputFirstName(firstName);
	  registerPage.inputLastName(lastName);
	  registerPage.inputEmail(emailAddress);
	  registerPage.inputPassword(password);
	  registerPage.inputConfirmPassword(password);
	  
	  registerPage.clickRegisterButton(); 

	  Assert.assertEquals(registerPage.getEmailAlreadyExistErrorMsg(), "The specified email already exists");
  }
  
  @Test
  public void TC_05_Register_Password_Less_Then_6_Characters() {
	  homePage.clickRegisterLink();
	  
	  registerPage.inputFirstName(firstName);
	  registerPage.inputLastName(lastName);
	  registerPage.inputEmail(emailAddress);
	  registerPage.inputPassword("12345");
	  registerPage.inputConfirmPassword("12345");
	  
	  registerPage.clickRegisterButton(); 

	  Assert.assertEquals(registerPage.getPasswordErrorMsg(), "Password must meet the following rules:\nmust have at least 6 characters");
  } 
  
  @Test
  public void TC_05_Register_Invalid_Confirm_Password() {
	  homePage.clickRegisterLink();
	  
	  registerPage.inputFirstName(firstName);
	  registerPage.inputLastName(lastName);
	  registerPage.inputEmail(emailAddress);
	  registerPage.inputPassword(password);
	  registerPage.inputConfirmPassword("654123");
	  
	  registerPage.clickRegisterButton(); 

	  Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "The password and confirmation password do not match.");
  }


  @AfterClass
  public void afterClass() {
  }
  
  public int generateFakeNumber() {
	  return (int) (Math.random() * 1000);
  }

}
