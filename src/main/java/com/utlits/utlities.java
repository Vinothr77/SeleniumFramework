package com.utlits;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

public class utlities {

	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGE_LOAD_TIME = 10;
	
	public static String timestamp() {
		Date date = new Date();
		String currenttime = date.toString().replace(" ", "_");
		return "vinothwind12"+currenttime+"@gmail.com";
	}
	
	
	public static Object[][] GenerateExcelSheet(String sheetname) throws Throwable {
		File excelfile = new File(System.getProperty("user.dir"+"\\testdata\\testdata.xlsx"));
		XSSFWorkbook workbook = null;
		try {
		FileInputStream fileExcel = new FileInputStream(excelfile);
		workbook = new XSSFWorkbook(fileExcel);
		}catch (Throwable e){
			e.printStackTrace();
			
		}
	    XSSFSheet sheet =  workbook.getSheet(sheetname);
	   
	   int rows = sheet.getLastRowNum();
	   int cols = sheet.getRow(0).getLastCellNum();
	   
	   Object[][] data = new Object[rows][cols];
	   for(int i=0;i<rows;i++) {
		   XSSFRow row = sheet.getRow(i+1);
		   for(int j=0;j<cols;j++) {
			   XSSFCell cell = row.getCell(j);
			   CellType cellType = cell.getCellType();
			   switch(cellType) {
			   
			   case STRING:
				   data[i][j] = cell.getStringCellValue();
				   break;
			   case NUMERIC:
				   data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				   break;
			   case BOOLEAN:
				  data[i][j] = cell.getBooleanCellValue(); 
				  break;
			   }
		   }
	   }
	  return data;
	}
	
	public static String CaptureScreenshort(WebDriver driver, String testName)	 {
		File srcScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destiScreenshotPath = System.getProperty("user.dir") + "\\screenshort\\" + testName + ".png";
		
		try {
			FileHandler.copy(srcScreen, new File(destiScreenshotPath));
		} catch (WebDriverException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		return destiScreenshotPath;
		
	}
}
