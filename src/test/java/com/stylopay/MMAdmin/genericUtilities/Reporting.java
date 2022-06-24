package com.stylopay.MMAdmin.genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IExecutionListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Reporting implements IExecutionListener{

	static ExtentReports report;
	public static ExtentTest test;	
	

	public void onExecutionStart() {
		
		System.out.println("------- MM Admin Portal Testcase Execution Start --------");
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
		String reportName = "MMAdmin-Test-Report-" + timeStamp + ".html";
		
		report = new ExtentReports(System.getProperty("user.dir")+"/Report/" + reportName);
		
	}

	@BeforeSuite(alwaysRun=true)
	public static void initialiseReport(){
		
		System.out.println("Suite starts");		
		
	}
	
	public static void startTest(String tcName) {
		test = report.startTest(tcName);
	}
	
	
	@AfterSuite(alwaysRun=true)
	public static void endTest()
	{
		System.out.println("Suite end");
	}
	
	
	public void onExecutionFinish() {
		
		System.out.println("------- MM Admin Portal Testcase Execution Finish --------");
		report.flush();
	}
	
}
