package com.inspire.Automation;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.inspire.base.Base;
import com.inspire.utilities.TryWithHWND;

public class InspireAutomation extends Base
{
	
	public static FileInputStream fis;
	
	
	@Parameters({"folderpath","ExcelFileName"})
	@Test
	public void inspireTest(String folderpath,String ExcelFileName) throws InterruptedException, AWTException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{	
		
		ExcelFileName.replace(".xml","");
		//Double click data input
		doubleclickWorkFlowModuleItems("ListItemFavorites_Name");
		
		Thread.sleep(10);

		//Double click data input
		doubleclickWorkFlowModuleItems("ListItemDataInput_Name");
		Thread.sleep(10);
	
		//Double click data processing
		doubleclickWorkFlowModuleItems("ListItemDataProcessing_Name");
		
		//This wait is introduced because of the remote execution issue which fails on the Layout item
		Thread.sleep(1000);
	
	
		doubleclickWorkFlowModuleItems("ListItemLayout_Name");
		Thread.sleep(20);
	
		//Double click Layout image
		doubleclick("ListItemImage_Name");
		Thread.sleep(20);
	
		deleteDefaultPage("1");

		//import xml file
		importXMLFile(getFilePath(folderpath,ExcelFileName,".xml"));
	
		//Save Inspire file
		SaveInspireFile(getFilePath(folderpath,ExcelFileName,".wfd"));
		
		driver.close();
		
	}
}
