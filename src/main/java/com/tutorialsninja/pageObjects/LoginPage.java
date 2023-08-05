package com.tutorialsninja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passWordField;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordNotMatchingMsg;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailAddress)
	{
		emailField.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password)
	{
		passWordField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage Login(String emailAddress,String password)
	{
		emailField.sendKeys(emailAddress);
		passWordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);	
	}
	
	public String retriveWrongEmailPasswordWarningMsg()
	{
		String waringMessage=emailPasswordNotMatchingMsg.getText();
		return waringMessage;
	}

}
