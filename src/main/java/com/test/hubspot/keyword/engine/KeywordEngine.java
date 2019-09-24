package com.test.hubspot.keyword.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.hubspot.keyword.base.BaseConfiguration;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;

	public static Workbook book;
	public static Sheet sheet;
	
	public BaseConfiguration base;
	public WebElement element;
	
	public final String SCENARIOSEXCEL_PATH="D://Learning//Workspace//KeywordDrivenFrameworkTwo//src//main//java//com//test//hubspot//keyword//scenarios//ScenariosExcel.xlsx";
	
	public void startExecution(String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream ip=null;
		ip=new FileInputStream(SCENARIOSEXCEL_PATH);
		book=WorkbookFactory.create(ip);
		sheet=book.getSheet(sheetName);
		int k=0;
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			try {
			String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
			String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();
			String action=sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+4).toString().trim();
			
			switch (action) {   		//This switch is for the webbrowser tasks ie loadbrowser, loadurl, closebrowser etc
			case "openbrowser":
				base=new BaseConfiguration();
				prop=base.initialize_Properties();
				if(value.isEmpty()||value.equalsIgnoreCase("NA"))
				{
					driver=base.initialize_Webdriver(prop.getProperty("browser"));
				}else
				{
					 driver=base.initialize_Webdriver(value);
				}
				
				break;
				
			case "get" :
				if(value.isEmpty()||value.equalsIgnoreCase("NA"))
				{
					driver.get(prop.getProperty("url"));
				}else
				{
					driver.get(value);
				}
				
				break;
				
			case "closebrowser" :
				driver.quit();
				break;
			default:
				break;
			}
			
			switch (locatorType) { //this switch is for the locator elements like id classname xpath linktext etc
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.clear();	
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorType=null; //once it is done then make it null then the other cases in switch can use the same locator
				break;

			default:
				break;
			}
			}catch(Exception e) {e.printStackTrace();}  //putting the whole for loop inside the try catch block to catch the Null pointer exception that was hitting
		}
		
		
		
	}
	

}
