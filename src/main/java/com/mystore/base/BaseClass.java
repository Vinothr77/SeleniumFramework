package com.mystore.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utlits.utlities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties props;
	
	
	// This is Baseclass constructor
	public BaseClass () throws Throwable {
	
		 prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\configuration\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	
	
	 props = new Properties();
		File files = new File(System.getProperty("user.dir")+"\\testdata\\testdata.properties");
		try {
		FileInputStream fis1 = new FileInputStream(files);
		props.load(fis1);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver BrowserLunch(String browsername) {
		System.out.println("browser cuming");
		if (browsername.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utlities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utlities.PAGE_LOAD_TIME));
		
		return driver;
	}

}
