package Stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;

public class productSearchTest extends BaseClass {
	public WebDriver driver;

	public productSearchTest() throws Throwable {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = BrowserLunch(prop.getProperty("browser"));
	}

	@Test
	public void verifyproductSearch() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='HP LP3065']")).isDisplayed());
	}

	@Test
	public void verifyinvalidproduct() {
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		String ExpectedResult = driver.findElement(By.xpath("//p[2][text()='There is no product that matches the search criterias.']")).getText();
		System.out.println(ExpectedResult);
		// String ActualResult = props.getProperty("InvalidProduct");
		// Assert.assertEquals(ExpectedResult, ActualResult);

	}

	@AfterMethod

	public void browserclose() {
		driver.quit();
	}
}
