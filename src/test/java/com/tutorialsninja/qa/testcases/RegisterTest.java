package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.clickOnRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.genetateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnPrivacyPolicy();
		AccountSuccessPage accountSuccessPage = registerPage.clickOnSubmitButton();
		
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not display");
		
	}
	
	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.genetateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterRadioButton();
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnSubmitButton();
		
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not display");
		
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterRadioButton();
		registerPage.clickOnPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnSubmitButton();
		
		String actualWarning=registerPage.retriveWarningMessageDuplicateEmailIdMessage();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Messege regarding duplicate email id");
		
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		accountSuccessPage=registerPage.clickOnSubmitButton();
		
		String actualPrivacyPolicy=registerPage.retrivePrivacyPolicyWarningMessage();
		Assert.assertTrue(actualPrivacyPolicy.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy messege is not displayed");
		
		String actualFirstWarningMessage=registerPage.retriveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstWarningMessage,dataProp.getProperty("firstNameWarning"), "First Name warning message not displayed");
		
		String actualLastNameWarningMessage=registerPage.retriveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarningMessage,dataProp.getProperty("lastNameWarning"),"Last Name warning messge not displayed");
		
		String actualEmailIdWarningMessage=registerPage.retriveEmailWarningMessage();
		Assert.assertEquals(actualEmailIdWarningMessage,dataProp.getProperty("emailWarning"),"Email warning message not displayed");
		
		String actualTelephoneWarningMessage=registerPage.retriveTelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarningMessage,dataProp.getProperty("telephoneWarning"),"Telephone warning message not displayed");
		
		String actualPasswordWarningMessage=registerPage.retrivePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarningMessage,dataProp.getProperty("passwordWarning"),"password warning message not displayed");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
