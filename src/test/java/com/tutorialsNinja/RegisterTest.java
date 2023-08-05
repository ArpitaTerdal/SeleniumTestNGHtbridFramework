package com.tutorialsNinja;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.Base.BaseClass;
import com.tutorialsninja.pageObjects.AccountPage;
import com.tutorialsninja.pageObjects.AccountSuccessPage;
import com.tutorialsninja.pageObjects.HomePage;
import com.tutorialsninja.pageObjects.RegisterPage;

import Utilities.Utils;


public class RegisterTest extends BaseClass{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountsuccessPage;
	
	@BeforeMethod
	public void setUp()
	{
		loadConfigFile();
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage=homePage.selectRegisterOption();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void RegisterWithMandatoryFields()
	{
		accountsuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utils.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String confirmmsg=accountsuccessPage.retriveAccountCreationMessage();
		Assert.assertEquals(confirmmsg, dataProp.getProperty("accountCreationMsg"),"Account not created");
		
}
	@Test(priority=2)
	public void RegisterEnteringAllFields()
	{
		accountsuccessPage=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utils.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String confirmmsg=accountsuccessPage.retriveAccountCreationMessage();
		Assert.assertEquals(confirmmsg, dataProp.getProperty("accountCreationMsg"),"Account not created");
		
}
	@Test(priority=3)
	public void RegisterWithExistingEmailAddress()
	{
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), dataProp.getProperty("validPassword"));
		String confirmmsg=registerPage.retriveDuplicateEmailWarningMessage();
		Assert.assertTrue(confirmmsg.contains(dataProp.getProperty("duplicateEmailWarningMsg")),"Expected msg not displayed");
		
}
	@Test(priority=4)
	public void RegisterWithoutEnteringFields()
	{
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.retriveprivacyPolicyWarningMessage().contains(dataProp.getProperty("privacyPolicyWarning")),"privacypolicy warning msg not displayed");
		
		Assert.assertTrue(registerPage.retriveFirstNameWarningMessage().contains(dataProp.getProperty("firstNameWarningMsg")),"firstname warning msg not displayed");
		
		Assert.assertTrue(registerPage.retriveLastNameWarningMessage().contains(dataProp.getProperty("lastNameWarningMsg")),"lastname warning msg not displayed");
		
		Assert.assertTrue(registerPage.retriveEmailWarningMessage().contains(dataProp.getProperty("emailWarningMsg")),"Email warning msg not displayed");
		
		Assert.assertTrue(registerPage.retriveTelephoneWarningMessage().contains(dataProp.getProperty("telephoneWarningMsg")),"Telephone warning msg not displayed");
		
		Assert.assertTrue(registerPage.retrivepasswordWarningMessage().contains(dataProp.getProperty("passwordWarningMsg")),"Password warning msg not displayed");	
}
}
