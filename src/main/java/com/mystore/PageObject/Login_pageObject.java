package com.mystore.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login_pageObject {
WebDriver driver;
	

	@FindBy (xpath="//span[text()='My Account']")
	private WebElement MyAccountDropdown;
	
	@FindBy(linkText ="Login")
	private WebElement LoginDropdown;
	
	@FindBy (id ="input-email")
	private WebElement EmailAddress;
	
	@FindBy (id ="input-password")
	private WebElement Password;
	
	@FindBy(xpath ="//input[@value='Login']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement PasswordNotMatching;

	public Login_pageObject(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
		
	public void ClickOnMyAccount() {
		MyAccountDropdown.click();
	}
	
	public void LoginLink() {
		LoginDropdown.click();
	}
	
	public void LoginButton() {
		LoginButton.click();
	}
	
	public void EmailAddress() {
		EmailAddress.sendKeys("vinothwind@gmail.com");
		
	}
	public void Password() {
		Password.sendKeys("123456");
		
	}
	
	public  String HomePageTitle() {
		String expectedResult = PasswordNotMatching.getText();
		return expectedResult;
			}
}
