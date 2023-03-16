package com.inspire.utilities;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Point;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.winium.WiniumDriver;

import com.inspire.base.Base;



public class Keywords  {
	public static WiniumDriver driver;
// This method is to create flow area
	public void Rectangle (int xofLayoutview) throws AWTException
	{
		Robot robot = new Robot();
		java.awt.Dimension i = Toolkit.getDefaultToolkit().getScreenSize();
		xofLayoutview = xofLayoutview +20;
			//	int x = (i.width-xofLayoutview);//880
		int x = xofLayoutview;//880
				int y = (i.height-300);//300
				//System.out.println(i.getWidth()+"Width"+"lllll"+x+"mmmm"+y);
				robot.mouseMove(x, y);
				robot.setAutoDelay(150);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

				robot.setAutoDelay(100);

				robot.mouseMove(x+50,y+50);

				robot.setAutoDelay(100);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	// This method is to do a mouse click
	public void  MouseClick(int x, int y) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	
	// This method is to Tab
	public void Tab() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
	}
	
	// This method is to do Backspace
	public void Backspace() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
	}
	
	// This method is to click Select By Text
	public void ClickSelectByText() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_X);
	}
	
	// This method is to click enter
	public void Enter() throws AWTException, InterruptedException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(20);
	}

	// This method is to click cntl s
	public void CntlS() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_S);
	}
	
	// This method is to click cntl shift s
		public void CntlShiftS() throws AWTException {
			
			 Actions a = new Actions(driver);
			 a.keyDown(Keys.SHIFT)
			    .keyDown(Keys.CONTROL)
			    .sendKeys("s")
			    .build()
			    .perform();
			 a.keyUp(Keys.CONTROL)
			    .keyUp(Keys.SHIFT)
			    .build()
			    .perform();
		}
	
	//This method is to click Cntl T
	public void CntlT() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
	}
	
	//This method is to click Cntl A
	public void CntlA() throws AWTException {
		Robot robot= new Robot();
		 robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_A); 
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyRelease(KeyEvent.VK_A);
		
	}

	//This method is to click Cntl N
	public void CntlN() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	//This method is to click Cntl B
	public void CntlB() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_B);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	//This method is to click Cntl I
	public void CntlI() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	//This method is to click Cntl U
	public void CntlU() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_U);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	//This method is to click F2
	public void F2() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_F2);
	}
	
	//This method is to click Alt F4
	public void AltF4() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_F4);
	}
	
	//This method is to click Key down
	public void KeyDown()  throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}

	//This method is to click Key up
	public void  KeyUp() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
	}
	
	//This method is to click Key left
	public void  Keyleft() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}
	
	//This method is to click Key right
	public void  KeyRight() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}

	//This method is to click Key side
	public void KeySide()throws AWTException {
		Robot robot= new Robot();
		
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}
	
	//This method is to click Space
	
	public void Space() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_SPACE);
	}
	
	//This method is to click Key up
	public void  Delete() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_DELETE);
		robot.keyRelease(KeyEvent.VK_DELETE);
	}
	//This method is to Enter One Character
public void EnterOneCharacter(String st) throws AWTException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException
{
	Robot robot = new Robot();
	String upperCase1 = st.trim().toUpperCase();
	String code1 = "VK_" + upperCase1;
	Field f1 = KeyEvent.class.getField(code1);
	int keyEvent = f1.getInt(null);
	robot.keyPress(keyEvent);
	  robot.keyRelease(keyEvent);
	Thread.sleep(5);
}
//This method is to Enter Keys
	public void EnterKeys(String st) throws AWTException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException {
		Robot robot = new Robot();
	
		String upperCase = st.trim().toUpperCase();
		//System.out.println(upperCase);
		char[] cArray= upperCase.toCharArray();
		int lengthcArray = cArray.length;
		for(int k =0; k<lengthcArray; k++)
		{
			String letter = Character.toString(cArray[k]);
			if(letter.equalsIgnoreCase("."))
			{
				letter = "DECIMAL";
			}
			String code = "VK_" + letter;
			//System.out.println(code);
			Field f = KeyEvent.class.getField(code);
			int keyEvent = f.getInt(null);
			if((k+1)==lengthcArray)
			{
				robot.keyPress(keyEvent);
				break;
			}
			 if(cArray[k]==cArray[k+1])
			{
				
				robot.keyPress(keyEvent);
				Thread.sleep(100);
			}
			else
			{
				robot.keyPress(keyEvent);
			}
		}
		
	}
	
	//This method is to Send Text
	public void SendText(String text) throws AWTException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException {
	
	
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
    robot.setAutoDelay(20);
		
   
	}
	
	

}
