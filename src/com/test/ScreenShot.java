package com.test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ScreenShot implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		
		try {
			capturescreenShot(result,"Pass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		
		try {
			capturescreenShot(result,"Failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	
	public void capturescreenShot(ITestResult result, String status) throws IOException {
		//Define the folder to save screenshot
		
			String	destDir="./Screenshot/FailScreenShots";
			String	destPassDir="./Screenshot/PassScreenShots";
			
				//code for capturing screenshot
				
				File srcFile=((TakesScreenshot)SwiggyAutomation.driver).getScreenshotAs(OutputType.FILE);
				
				//setting file name for screenshot
				
				DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY_hh_mm_ss");
				
				new File(destDir).mkdirs();
				
				String destFile=dateFormat.format(new Date())+".png";
				
				
				if(status.equalsIgnoreCase("pass"))	{
					FileUtils.copyFile(srcFile, new File(destPassDir+"/"+destFile));
				}
				
				else {
					FileUtils.copyFile(srcFile, new File(destDir+"/"+destFile));
				}
					
				
			
	}
	
	

}
