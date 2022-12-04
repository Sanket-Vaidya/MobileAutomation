package com.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
@Listeners(ScreenShot.class)
public class SwiggyAutomation {

	public static AndroidDriver<AndroidElement> driver;
	
	public String destDir;
	public DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY_hh_mm_ss");
	
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest logger;

	@Test
	public void launchChromeBrowser() throws MalformedURLException, InterruptedException {
		
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("./TestReport/AutomationReport"+dateFormat.format(new Date())+".html");
		spark.config().setTheme(Theme.DARK);
		reports.attachReporter(spark);

		DesiredCapabilities caps = new DesiredCapabilities();
		
		logger=reports.createTest("Swiggy Automation");

		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("platformVersion", "9.0");
		caps.setCapability("noReset", "true");

		caps.setCapability("appPackage", "in.swiggy.android");
		caps.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");

		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);

		Thread.sleep(8000);

		driver.findElement(By.id("in.swiggy.android:id/location_header")).click();

		Thread.sleep(3000);
		
	

		driver.findElement(By.id("in.swiggy.android:id/location_description")).sendKeys("Pruthvi Ekdant");

		Thread.sleep(2000);

		// X:203 y:1475

		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(206, 1095)).release().perform();

		Thread.sleep(5000);

		driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"explore food deliveries\"]").click();

		driver.findElement(By.id("in.swiggy.android:id/disc_search_bar_container")).click();

		Thread.sleep(5000);

		driver.pressKey(new KeyEvent().withKey(AndroidKey.E));
		driver.pressKey(new KeyEvent().withKey(AndroidKey.G));
		driver.pressKey(new KeyEvent().withKey(AndroidKey.G));

//		x:126 y:390

		Thread.sleep(3000);

		action.press(PointOption.point(126, 378)).release().perform();

		Thread.sleep(3000);

		String ExpectedRestaurant = "The Egg Break";
		String actualReastaurant = driver.findElementByAccessibilityId("The Egg Break").getText();

		if (ExpectedRestaurant.equalsIgnoreCase(actualReastaurant)) {
			logger.pass("Restaurant name is correct");
		}

		driver.findElementById("in.swiggy.android:id/quantity_text_1").click();

		Thread.sleep(3000);

		String actualPrice = driver.findElementByAccessibilityId("price ₹100.95 rupees").getText();
		String ExpectedPrice = "₹100.95";

		if(actualPrice.equals(ExpectedPrice)) {
			logger.pass("Price is correct");
		}

		Thread.sleep(3000);

		action.press(PointOption.point(771, 2152)).release().perform();
		
		logger.pass("Item Added to cart");
		
		reports.flush();

	}
	public void takeScreenShot() {
		
		//Define the folder to save screenshot
		
		destDir="./Screenshot";
		
		//code for capturing screenshot
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//setting file name for screenshot
		
		
		
		new File(destDir).mkdirs();
		
		String destFile=dateFormat.format(new Date())+".png";
		
		try {
			
			FileUtils.copyFile(srcFile, new File(destDir+"/"+destFile));
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
				
	}

}
