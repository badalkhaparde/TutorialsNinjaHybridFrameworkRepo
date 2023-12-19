package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password) {
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPasswordFields(password);
		accountPage = loginPage.clickOnLoginButton();
		
		Assert.assertTrue(accountPage.getDisplayStatusOfAccountInformationStatus(),"Edit Your Account Information");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data=Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterEmailAddress(Utilities.genetateEmailWithTimeStamp());
		loginPage.enterPasswordFields(dataProp.getProperty("invalidPassword"));
		accountPage=loginPage.clickOnLoginButton();
		
		String actualWarningMessage =loginPage.retrieveEmailPasswordNotMatchingText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterEmailAddress(Utilities.genetateEmailWithTimeStamp());
		loginPage.enterPasswordFields(prop.getProperty("validPassword"));
		accountPage=loginPage.clickOnLoginButton();
		
		String actualWarningMessage =loginPage.retrieveEmailPasswordNotMatchingText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAdressAndInvalidPassword() {
		
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPasswordFields(dataProp.getProperty("invalidPassword"));
		accountPage=loginPage.clickOnLoginButton();
		
		String actualWarningMessage =loginPage.retrieveEmailPasswordNotMatchingText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvideCredentials() {
		
		accountPage=loginPage.clickOnLoginButton();
		
		String actualWarningMessage =loginPage.retrieveEmailPasswordNotMatchingText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
		
	}
	
	
}
