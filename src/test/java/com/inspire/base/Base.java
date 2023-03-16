package com.inspire.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.inspire.utilities.Keywords;
import com.inspire.utilities.TryWithHWND;

public class Base extends Keywords
{
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	ExtentSparkReporter sparkReporter;
	public static ExtentHtmlReporter html;
	public static ExtentTest createTest;
	
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static WiniumDriver driver;
	

	//Method to find elements by name
	public void FindElementsByName1(String locator) throws AWTException 
	{
        WebElement window = driver.findElement(By.name("Inspire Designer 12.5 FMAP PRESALES-PARTNER - Macrosoft Inc."));
        List<org.openqa.selenium.WebElement> FlowAreas = window.findElements(By.name(locator));
        
	}
	
	public void loadConfigFile() throws IOException
	{
		//Config file is initialized
		try 
		{
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\properties\\Config.properties");
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			//OR file is loaded
			Config.load(fis);
			
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void loadORFile() throws IOException
	{
	//OR file is initialized
			try 
			{
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\properties\\OR.properties");
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{
				//OR file is loaded
				OR.load(fis);
				
			}
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	//Method is to double click the data input 
	public void doubleclickWorkFlowModuleItems(String locator) throws InterruptedException, AWTException 
	{
        System.out.println("Inside doubleclickWorkFlowModuleItems function");

        Actions act = new Actions(driver);
		WebElement window = driver.findElement(By.name("Inspire Designer 15.4 hotfix 441 PRESALES-PARTNER - Macrosoft Inc."));

		
        System.out.println("Found Inspire header");
        if (locator.endsWith("_Name")) 
        {
        	System.out.println("Locator ends with Name");
        	//	Thread.sleep(5);
        	WebElement parentelement = window.findElement(By.name("Workflow Module Data"))
                	.findElement(By.name("Group Node"));
        	System.out.println("Found WorkFLow and Group Node");
        	
        	act.moveToElement(parentelement.findElement(By.name(OR.getProperty(locator))),5,7).doubleClick().build()
                	.perform();
        	System.out.println(OR.getProperty(locator));
        	
        }
        else if (locator.endsWith("_XPATH")) 
        {
        	FindElementsByName1("Data Inputs");

        	
        	//	Thread.sleep(5);
        	WebElement parentelement = window.findElement(By.name("Workflow Module Data"))
                	.findElement(By.name("Group Node"));

        	act.moveToElement(parentelement.findElement(By.xpath(OR.getProperty(locator)))).doubleClick().build()
                	.perform();
        	

        } 
        else if (locator.endsWith("_ClassName")) 
        {
        	act.moveToElement(window.findElement(By.className(OR.getProperty(locator)))).doubleClick().build();
        }

        }
	
	
	@BeforeSuite
	public void initialSetup() throws InterruptedException, AWTException, IOException
	{
		System.out.println("Initial Setup of file loading");
		loadORFile();
		System.out.println("OR file is  loaded");
		
		DesktopOptions option = new DesktopOptions();
		option.setApplicationPath("C:\\Program Files\\Quadient\\Inspire Designer\\Inspire.exe");
		
		System.out.println("Set application Path");
		
		option.setDebugConnectToRunningApp(false);
		option.setLaunchDelay(20);
		Thread.sleep(5);
		driver = new WiniumDriver(new URL("http://localhost:9999"), option);
		Thread.sleep(5000);
		
		TryWithHWND.main();
		Thread.sleep(2000);
		Thread.sleep(2000);
	
		
		
	}
	
	//This method is to Send Text
		public void SendText(String text) throws AWTException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException 
		{
			
			
			Robot robot = new Robot();
		    StringSelection selection = new StringSelection(text);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
		   
		  /*  byte[] b = text.getBytes(StandardCharsets.UTF_8);
		    text = new String(b, StandardCharsets.UTF_8);*/
			
			//System.out.println(text);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.setAutoDelay(60);
				
		   
		}
	
	//Method will import xml file
	public void importXMLFile(String xmlFilePath) throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, AWTException
	{
		Actions act = new Actions(driver);
		
		driver.findElement(By.name("File")).click();
		
		WebElement parentElement = driver.findElement(By.name("Pop Up Menu Window"));
		act.moveToElement(parentElement.findElement(By.xpath("Import")),10,14).click().build()
		.perform();
		
		Thread.sleep(1000);
		
		driver.findElement(By.name("XML...")).click();
		
		
		SendText(xmlFilePath);
		Enter();
		Thread.sleep(1000);
		
//		try 
//		{
//			driver.findElement(By.name("OK")).click();
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		try 
//		{
//			driver.findElement(By.name("OK")).click();
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//		}
			
		
		Tab();
		Enter();
		Tab();
		Tab();
		Enter();
		//		if(driver.getPageSource().contains("Results"))
//		{
//			driver.findElement(By.name("OK")).click();
//			Thread.sleep(1000);
//			if(driver.getPageSource().contains("Image Replacement"))
//			{
//				driver.findElement(By.name("OK")).click();
//			}
//		}
		
		
		
	}
	
	//Method to doubleclick
		public void doubleclick(String locator) throws InterruptedException {

			System.out.println("Inside double click");
			Actions act = new Actions(driver);
			WebElement window = driver.findElement(By.name("Inspire Designer 15.4 hotfix 441 PRESALES-PARTNER - Macrosoft Inc."));
			if (locator.endsWith("_Name")) {
				System.out.println("found naame");

			//	Thread.sleep(5);
				WebElement parentelement = window.findElement(By.name("Work Flow"))
						.findElement(By.name("Layout - Layout1"));
				System.out.println("found layout");

				act.moveToElement(parentelement.findElement(By.name(OR.getProperty(locator))), 10, 14).doubleClick().build()
						.perform();
			} else if (locator.endsWith("_XPATH")) {

				
				//Thread.sleep(5);
				WebElement parentelement = window.findElement(By.name("Work Flow"))
						.findElement(By.name("Layout - Layout1"));
				act.moveToElement(parentelement.findElement(By.xpath(OR.getProperty(locator))), 5, 10).doubleClick().build()
						.perform();
			} else if (locator.endsWith("_ClassName")) {
				act.moveToElement(window.findElement(By.className(OR.getProperty(locator)))).doubleClick().build();
			}

		}
		
		//Method to delete default page
		public void deleteDefaultPage(String locator) throws AWTException
		{
			Actions actclick = new Actions(driver);
			WebElement window = driver.findElement(By.name("Inspire Designer 15.4 hotfix 441 PRESALES-PARTNER - Macrosoft Inc."));
			
		    actclick.moveToElement(window.findElement(By.name("Page "+locator)),5,5).doubleClick().build().perform();
		    Delete();
		}
		public String getFilePath(String FolderPath,String xmlFileName,String extension)
		{
			String file1 = FolderPath + "/" + xmlFileName.replace(".xml", "");
			
			System.out.println(file1);
			
			file1.replace("\\", "/");
			System.out.println( "new = " +file1);
			
			return file1+extension ;
			
		}
		
		//method to save inspire file
		public void SaveInspireFile(String filePath) throws AWTException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException  {
		
			System.out.println("Inside save inspire");
			
			Robot robot= new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(10);
			robot.keyPress(KeyEvent.VK_SHIFT);
			Thread.sleep(10);
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(10);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(10);

			System.out.println("Control Shift S");
			
			Thread.sleep(20);
			
			SendText(filePath);
			System.out.println("Entered Wfd file name");
			
			Enter();
			Thread.sleep(20);
			System.out.println("Pressed Enter");
			Enter();
			System.out.println("Pressed Enter");
			Enter();
			System.out.println("Pressed Enter");
			
			//------------------------------------------------
			Actions actclick = new Actions(driver);
			WebElement window = driver.findElement(By.name("Inspire Designer 15.4 hotfix 441 PRESALES-PARTNER - Macrosoft Inc."));
			System.out.println("before Pressed page");
				actclick.moveToElement(window.findElement(By.name("Page 1")),5,5).doubleClick().build().perform();
				System.out.println("Pressed page");
				
			driver.close();
		}	
		
		
	
}
