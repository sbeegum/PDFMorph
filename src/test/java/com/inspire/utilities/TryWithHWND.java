package com.inspire.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;



public class TryWithHWND {
	 // This class is to recognize the inspire window and Maximize it
	public interface User32 extends StdCallLibrary {
	    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

	    boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

	    WinDef.HWND SetFocus(WinDef.HWND hWnd);
	    
	   
	    int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
	    
	    boolean SetForegroundWindow(WinDef.HWND hWnd);

	    boolean ShowWindow(WinDef.HWND hWnd,int nCmdShow);
	    void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
	}
	 public static void controlA() {
		 final User32 customUser32 = User32.INSTANCE;
	       customUser32.keybd_event((byte) 0x11 /* VK_CONTROL*/, (byte) 0, 0, 0);
	       customUser32.keybd_event((byte) 0x41 /* 'A' */, (byte) 0, 0, 0);
	       customUser32.keybd_event((byte) 0x41 /* 'A' */, (byte) 0, 2 /* KEYEVENTF_KEYUP */, 0);
	       customUser32.keybd_event((byte) 0x11 /* VK_CONTROL*/, (byte) 0, 2 /* KEYEVENTF_KEYUP */, 0);// 'Left Control Up
	   }
	public static void main() {
		final User32 user32 = User32.INSTANCE;
	    user32.EnumWindows(new WNDENUMPROC() {
	        int count = 0;
	       
	     
			public boolean callback(HWND hWnd, Pointer arg1) {
	            byte[] windowText = new byte[512];
	            user32.GetWindowTextA(hWnd, windowText, 512);
	            String wText = Native.toString(windowText);

	            // get rid of this if block if you want all windows regardless
	            // of whether
	            // or not they have text
	            if (wText.isEmpty()) {
	                return true;
	            }

	            System.out.println("Found window with text " + hWnd
	                    + ", total " + ++count + " Text: " + wText);
	            if (wText.contains("Inspire Designer")) {
	                user32.SetForegroundWindow(hWnd);
	                user32.ShowWindow(hWnd, 9);
	                user32.ShowWindow(hWnd, 3);
	                return false;
	            }
	            return true;
	        }
	    }, null);
	    // user32.SetFocus(hWnd);
	    try {
	        Robot r = new Robot();
	        r.keyPress(KeyEvent.VK_ENTER);
	        r.keyRelease(KeyEvent.VK_ENTER);
	        r.keyPress(KeyEvent.VK_ENTER);
	        r.keyRelease(KeyEvent.VK_ENTER);
	        r.keyPress(KeyEvent.VK_ENTER);
	        r.keyRelease(KeyEvent.VK_ENTER);
	        r.keyPress(KeyEvent.VK_ENTER);
	        r.keyRelease(KeyEvent.VK_ENTER);
	    } catch (AWTException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
