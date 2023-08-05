package com.tutorialsninja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstNameField;

	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmpasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicycheckBoxField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsettler;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarningMsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolivyWarningMsg;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMsg;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMsg;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMsg;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmail(String emailText)
	{
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephone)
	{
		telephoneField.sendKeys(telephone);
	}
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword)
	{
		confirmpasswordField.sendKeys(confirmPassword);
	}
	
	public void clickOnPrivacyPolicyCheckBox()
	{
		privacyPolicycheckBoxField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewSettlerOption()
	{
		newsettler.click();
	}
	
	public String retriveDuplicateEmailWarningMessage()
	{
		String duplicateEmailMsg=duplicateEmailWarningMsg.getText();
		return duplicateEmailMsg;
	}
	
	public String retriveprivacyPolicyWarningMessage()
	{
		String privacyPolicyMsg=privacyPolivyWarningMsg.getText();
		return privacyPolicyMsg;
	}
	
	public String retriveFirstNameWarningMessage()
	{
		String firstNameMsg=firstNameWarningMsg.getText();
		return firstNameMsg;
	}
	
	public String retriveLastNameWarningMessage()
	{
		String lastNameMsg=lastNameWarningMsg.getText();
		return lastNameMsg;
	}
	
	public String retriveEmailWarningMessage()
	{
		String emailMsg=emailWarningMsg.getText();
		return emailMsg;
	}
	
	public String retriveTelephoneWarningMessage()
	{
		String telephoneMsg=telephoneWarningMsg.getText();
		return telephoneMsg;
	}
	
	public String retrivepasswordWarningMessage()
	{
		String passwordMsg=passwordWarningMsg.getText();
		return passwordMsg;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephone,String password)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmpasswordField.sendKeys(password);
		privacyPolicycheckBoxField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephone,String password)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmpasswordField.sendKeys(password);
		newsettler.click();
		privacyPolicycheckBoxField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
}
