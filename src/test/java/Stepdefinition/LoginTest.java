package Stepdefinition;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mystore.PageObject.Login_pageObject;
import com.mystore.base.BaseClass;
import com.utlits.utlities;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest extends BaseClass {
	
	public WebDriver driver;
	
	public LoginTest() throws Throwable {
		// this is login page constructor 
		//when ever the super() called in Login subclass constructor is called , it's going to call Baseclass constructor
		// so it will execute the BaseClass property file
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = BrowserLunch(prop.getProperty("browser"));
		
	}

	@Test
	public void verifyLoginValidcred() throws InterruptedException {
		Login_pageObject login_pageObject = new Login_pageObject(driver);
		
		login_pageObject.ClickOnMyAccount();
		System.out.println("Menu are cliked");
		login_pageObject.LoginLink();
		Thread.sleep(1000);
		login_pageObject.EmailAddress();
		login_pageObject.Password();
		login_pageObject.LoginButton();
		
		Assert.assertTrue(driver.findElement(By.linkText("Modify your address book entries")).isDisplayed());
		
		
//		String actula = login_pageObject.HomePageTitle();
//		String epected = props.getProperty("ExpectedResult");
//		Assert.assertEquals(actula, epected);


	}

//	@DataProvider (name="LoginCredentials")
//	public Object[][] supplyTestdata() throws Throwable{
//		Object[][] data = utlities.GenerateExcelSheet("Login");
//		return data;
//		
//	}
	
	@Test(enabled = false)
	public void invalidcredntails() throws InterruptedException {
    
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(utlities.timestamp());
		Thread.sleep(1000);
		driver.findElement(By.id("input-password")).sendKeys("12345ss6");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String expectedResult = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		System.out.println(expectedResult);
		String ActualResult = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(expectedResult, ActualResult);

		Date date = new Date();
		System.out.println(date);

		String replacedwithUNDERSCORE = date.toString().replace(" ", ":");
		System.out.println(replacedwithUNDERSCORE);
	}

	@AfterMethod
	public void browserclose() {
		driver.quit();
	}
}
