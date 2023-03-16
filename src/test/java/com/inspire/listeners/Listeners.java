package com.inspire.listeners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.inspire.base.Base;
import com.microsoft.sqlserver.jdbc.SQLServerException;


public class Listeners extends Base implements ITestListener 
{
	public String ExecutionStatus ="2" ;

//	public static String DB_URL = "jdbc:sqlserver://EC2AMAZ-IUE1T8H:1433; databaseName=PdfMorphLive; ";
//	public static String DB_USER = "sa";
//	public static String DB_PASSWORD = "Macro!234";

	public static String DB_URL = "jdbc:sqlserver://192.168.1.211\\MSSQLSERVER2019; databaseName=PdfMorph; ";
	public static String DB_USER = "pdfuser";
	public static String DB_PASSWORD = "pdfpwd";
	private static Statement stmt;
	
	
	//Method will check if test is Started and print the log
		public void onStart(ITestContext context) {

			System.out.println("Test started");
			 	
			// TODO Auto-generated method stub
			
		}
	/*
	 *Finish method will update the database with completion status and success status and Notify flag.
	 * Copy the log file to file path mentioned in the testng xml file
	 * This method will also put the log in the file that the run is completed.
	 *
	 */
	
	
		public void onFinish(ITestContext context) 
		{
			
			ExecutionStatus = "3";
			System.out.println("ExecutionStatus = "+ExecutionStatus);
			try{
				 String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				Class.forName(dbClass).newInstance();
				Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				String ProcessId = context.getCurrentXmlTest().getParameter("ProcessId");
				System.out.println("Process ID = "+ProcessId);

				stmt = con.createStatement();
				
				String query; 
			
				 if(context.getFailedTests().size()>0 )
				{
					 System.out.println("Failed test");

					driver.close();
					 
				    query = "Update ProcessList Set ExecutionStatus = '3',SuccessStatus ='2',Notify = 1 where ProcessId ='"+ProcessId+"'";
					System.out.println(query);
	
					stmt.executeUpdate(query);

					con.close();
						
				}
	 			else if(context.getSkippedTests().size() >0 )
	 			{
	 				System.out.println("Skipped test");
	
	 				driver.close();
			
	 				query = "Update ProcessList Set ExecutionStatus = '3',SuccessStatus ='2',Notify = 1  where ProcessId ='"+ProcessId+"'";
					System.out.println(query);
					stmt.executeQuery(query);

					con.close();
						
				}
	 			
				 else
				 {
					 System.out.println("Execution successful");
					 query = "Update ProcessList Set ExecutionStatus = '3', XmlStatus = '4',SuccessStatus ='1' ,Notify = 1 where ProcessId ='"+ProcessId+"'";
					 System.out.println(query);
					 try 
					{
						stmt.executeQuery(query);
					} 
					catch (SQLServerException e) 
					{
							// TODO: handle exception
					}
					 
					con.close();
						
				 }
				
			}
				catch(Exception e)
				{
					System.out.println(e);
				}
		}


		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
			
			
}