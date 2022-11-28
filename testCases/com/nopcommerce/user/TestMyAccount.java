package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import objects.Accounts;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageUIs.HomePageUI.Top_Menu_Xpaths;
import pageUIs.MyAcccountPageUI.Left_Menu_Xpaths;
import utilities.Date_Util;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestMyAccount extends BaseTest{
	private WebDriver driver;
	private Accounts newAcc, updatedAcc;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private CustomerInforPageObject customerInforPage;
	private ChangePasswordPageObject changePasswordPage;
	
	@Parameters({"browser", "baseUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String baseUrl) {
		newAcc = Accounts.NEW_ACCOUNT;
		updatedAcc = Accounts.FULL_INFO_ACCOUNT;
		  driver = getBrowserDriver(browserName, baseUrl);
		  
		  homePage = PageGeneratorManager.getHomepage(driver);
		  
		  //Pre-condition: Register and Login Succeed
		  registerPage = (RegisterPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.REGISTER_LINK);
		  registerPage.registerAnAccount(newAcc);
	  }
	  
  @Test
  public void MyAcc_01_Update_Customer_Info() {
	  customerInforPage = (CustomerInforPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.MY_ACCOUNT_LINK);
	  customerInforPage.selectGender(updatedAcc.getGender());
	  customerInforPage.selectDOB(updatedAcc.getDob());
	  customerInforPage.inputEmail(updatedAcc.getEmail());
	  customerInforPage.inputCompanyName(updatedAcc.getCompanyName());
	  customerInforPage.clickSave();
	  
	  Assert.assertTrue(customerInforPage.isGenderSelected(updatedAcc.getGender()));
	  Assert.assertEquals(customerInforPage.getFirstName(), updatedAcc.getFirstName());
	  Assert.assertEquals(customerInforPage.getLastName(), updatedAcc.getLastName());
	  Assert.assertEquals(customerInforPage.getDOBDay(), Date_Util.getDayOfMonth(updatedAcc.getDob()));
	  Assert.assertEquals(customerInforPage.getDOBMonth().toUpperCase(), Date_Util.getMonth(updatedAcc.getDob()));
	  Assert.assertEquals(customerInforPage.getDOBYear(), Date_Util.getYear(updatedAcc.getDob()));
	  Assert.assertEquals(customerInforPage.getEmail(), updatedAcc.getEmail());
	  Assert.assertEquals(customerInforPage.getCompanyName(), updatedAcc.getCompanyName()); 
  }
  
  @Test
  public void MyAcc_02_Add_Addresss() {
  }
  
  @Test
  public void MyAcc_03_Change_Password() {
	  String newPassword = "123123";
	  changePasswordPage = (ChangePasswordPageObject) customerInforPage.NavigateToLeftMenuPage(Left_Menu_Xpaths.CHANGE_PASSWORD);
	  changePasswordPage.ChangePassword(updatedAcc.getPassword(),newPassword);
	  Assert.assertEquals(changePasswordPage.getChangePasswordSuccessMessage(), "Password was changed");
	  changePasswordPage.closeSuccessMessage();
	  homePage = (HomePageObject) changePasswordPage.clickTopMenuLink(Top_Menu_Xpaths.LOGOUT_LINK);
	  loginPage = (LoginPageObject) homePage.clickTopMenuLink(Top_Menu_Xpaths.LOGIN_LINK);
	  homePage = (HomePageObject) loginPage.login(updatedAcc.getEmail(), newPassword);
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
  }
  
  @Test
  public void MyAcc_04_My_Product_Reviews() {
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
