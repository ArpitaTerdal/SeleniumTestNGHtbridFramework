package com.tutorialsninja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//p[text()='There is no product that matches the search criteria.']")
	private WebElement NoProductMsg;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfHpProduct()
	{
		boolean displayStatus=validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retriveNoProductMessage()
	{
		String invalidMsg=NoProductMsg.getText();
		return invalidMsg;
	}

}
