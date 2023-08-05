package com.tutorialsNinja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.Base.BaseClass;
import com.tutorialsninja.pageObjects.HomePage;
import com.tutorialsninja.pageObjects.SearchPage;

public class SearchTest extends BaseClass{
	
	public WebDriver driver;
	SearchPage searchPage;
	
	/*public Search()
	{
		super();
	}*/
	
	@BeforeMethod
	public void setUp()
	{
		loadConfigFile();
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test
	public void verifySearchWithValidProduct()
	{
		HomePage homePage=new HomePage(driver);
		homePage.enterTextIntoSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage=homePage.clickOnSearchIconButton();
		
		Assert.assertTrue(searchPage.displayStatusOfHpProduct(),"valid product not displayed");
	}

	@Test
	public void verifySearchWithInValidProduct()
	{
		HomePage homePage=new HomePage(driver);
		homePage.enterTextIntoSearchBoxField(dataProp.getProperty("inValidProduct"));
	    searchPage=homePage.clickOnSearchIconButton();
		
		String Actualmsg=searchPage.retriveNoProductMessage();
	    Assert.assertEquals(Actualmsg, dataProp.getProperty("noProductMsg"),"product does not match msg not displayed");
	}
	
	@Test
	public void verifySearchWithoutEnteringProduct()
	{
		HomePage homePage=new HomePage(driver);
		searchPage=homePage.clickOnSearchIconButton();
		
		String Actualmsg=searchPage.retriveNoProductMessage();
	    Assert.assertEquals(Actualmsg, dataProp.getProperty("noProductMsg"),"product does not match msg not displayed");
	}
}
