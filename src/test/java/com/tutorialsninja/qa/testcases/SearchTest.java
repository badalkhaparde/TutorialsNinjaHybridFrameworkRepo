package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	public WebDriver driver;
	SearchPage searchPage;
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchBoxFields(dataProp.getProperty("validProduct"));
		SearchPage searchPage = homePage.clickOnButton();
		
		Assert.assertTrue(searchPage.displayStatusOfHPProduct());
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchBoxFields(dataProp.getProperty("invalidProduct"));
		searchPage=homePage.clickOnButton();
		
		String actualStringText=searchPage.retriveNoProductText();
		Assert.assertEquals(actualStringText,dataProp.getProperty("noProductMatches"),"No Product message in serach result is not displayed");
	}
	
	@Test(priority = 3,dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage=new HomePage(driver);
		searchPage=homePage.clickOnButton();
		
		String actualStringText=searchPage.retriveNoProductText();
		Assert.assertEquals(actualStringText,dataProp.getProperty("noProductMatches"),"No Product message in serach result is not displayed");
		
	}

}
