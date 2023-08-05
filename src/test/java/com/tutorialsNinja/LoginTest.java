package com.tutorialsNinja;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.Base.BaseClass;
import com.tutorialsninja.pageObjects.AccountPage;
import com.tutorialsninja.pageObjects.HomePage;
import com.tutorialsninja.pageObjects.LoginPage;

import Utilities.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends BaseClass{
	
	 public WebDriver driver;
	 
	 LoginPage loginpage;
	 
	 /*public Login()
	 {
		 super();
	 }*/
	 
	 @BeforeMethod
	 public void setUp()
	 {
		    loadConfigFile();
		    driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		    HomePage homePage=new HomePage(driver);
		    homePage.clickOnMyAccount();
		   loginpage = homePage.selectLoginOption();
	 }
	 
	 @AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsdata")
	public void verifyLoginWithValidCredentials(String Email,String Password)
	{
		AccountPage accountPage=loginpage.Login(Email, Password);
		Assert.assertTrue(accountPage.verifyEditAccountInformationDisplayed(),"Edit your account information is not displayed");
			
	}
	
	@DataProvider(name="validCredentialsdata")
	public Object[][] supplyTestData()
	{
		Object[][] data= Utils.getTestDataFromExcel("Login");
		
		return data;
	}
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		loginpage.Login(Utils.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		String ActualWarningmsg=loginpage.retriveWrongEmailPasswordWarningMsg();
		String Expectedmsg=dataProp.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(ActualWarningmsg.contains(Expectedmsg),"Expected msg not displayed");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidEmailInvalidPasswordCredentials()
	{
		loginpage.Login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String ActualWarningmsg=loginpage.retriveWrongEmailPasswordWarningMsg();
		String Expectedmsg=dataProp.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(ActualWarningmsg.contains(Expectedmsg),"Expected msg not displayed");

}
	@Test(priority=4)
	public void verifyLoginWithInValidEmailvalidPasswordCredentials()
	{
		loginpage.Login(Utils.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		String ActualWarningmsg=loginpage.retriveWrongEmailPasswordWarningMsg();
		String Expectedmsg=dataProp.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(ActualWarningmsg.contains(Expectedmsg),"Expected msg not displayed");
		

}
	@Test(priority=5)
	public void verifyLoginWithoutCredentials()
	{
		loginpage.clickOnLoginButton();
		String ActualWarningmsg=loginpage.retriveWrongEmailPasswordWarningMsg();
		String Expectedmsg=dataProp.getProperty("invalidEmailPasswordWarningMsg");
		Assert.assertTrue(ActualWarningmsg.contains(Expectedmsg),"Expected msg not displayed");
}
}
