package com.tutorialsninja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement MyAccountDropMenu;

	@FindBy(linkText="Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchIconButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount()
	{
		MyAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption()
	{
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption()
	{
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterTextIntoSearchBoxField(String productNameText)
	{
		searchBoxField.sendKeys(productNameText);
	}
	
	public SearchPage clickOnSearchIconButton()
	{
		searchIconButton.click();
		return new SearchPage(driver);
	}
}
