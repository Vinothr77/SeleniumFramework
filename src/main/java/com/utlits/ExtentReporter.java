package com.utlits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports GenereateExtendReporte() throws Throwable {
		
		
		ExtentReports extentReporter = new ExtentReports();
		
		File extend = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extend);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("MyStore Test Case Result");
		sparkReporter.config().setDocumentTitle("TestNG FrameWork Reports");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReporter.attachReporter(sparkReporter);

		// adding additional project info into extend report
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir") + "\\configuration\\config.properties");
		try {
			FileInputStream conFis = new FileInputStream(configPropFile);
			configProp.load(conFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		extentReporter.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReporter.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReporter.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReporter.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReporter.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReporter;
	}

}
