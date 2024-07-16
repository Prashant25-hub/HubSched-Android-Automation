package com.nakshatratechnohub.hubsched;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class HubSched_Visitor {

	AndroidDriver<AndroidElement> driver;

	@BeforeMethod
	public void setup() {
//	
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("AUTOMATION_NAME", "Appium");
		cap.setCapability("deviceName", "vivo Y73");
		cap.setCapability("udid", "1368055620000TL");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("autoGrantPermissions", "true");
		cap.setCapability("appium:ELEMENT_SCROLL_BEHAVIOR","Enable");

		cap.setCapability("appPackage", "com.nakshatratechnohub.hubsched.visitorscanner");
		cap.setCapability("appActivity", "com.nakshatratechnohub.hubsched.visitorscanner.auth.activities.AuthActivity");

		URL url = null;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver = new AndroidDriver<AndroidElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void visitorAutomation() throws InterruptedException {
		// Thread.sleep(1000);
		driver.findElementById("email_id").sendKeys("visit@nakshatra.com");
		driver.findElementById("password_id").sendKeys("123456");
		driver.findElementById("login_btn").click();
		Thread.sleep(1000);
		personal_Details();
	}

	public void personal_Details() throws InterruptedException {

		// AUtils.sleepNow(1000);
		driver.findElementById("addMobileET").sendKeys("9665505383");
		driver.findElementById("confirmMobileET").sendKeys("9665505383");
		
		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
		
		driver.findElementById("add_emp_name").sendKeys("Prashant");
		driver.findElementById("lastNameET").sendKeys("Patil");
		driver.findElementById("emailET").sendKeys("pp1946285@gmail.com");
		
		MobileElement gender = driver.findElement(By.id("android:id/text1"));
		gender.click();
		
		List<AndroidElement> options = driver
				.findElements(By.id("android:id/text1"));
		if (options.size() > 0) {
			options.get(1).click();
		}
//		driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Male\"]")).click();
		
		driver.findElementById("addressET").sendKeys("Nashik");
		driver.findElementById("next_button").click();
		visitDetails();
	
	}
	public void visitDetails(){

		driver.findElementById("select_employee").click();
		driver.findElementById("searchView").sendKeys("Prashant");
		driver.findElementById("checkBox").click();
		driver.findElementById("done_btn").click();

		driver.findElementById("visitPurposeET").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Meeting\"]")).click();
		AUtils.sleepNow(1000);
		driver.findElementById("companyET").sendKeys("Nakshatratechnohub (India) Pvt. Ltd");
		driver.findElementById("next_button").click();

		capturephoto_Automation();
	}

	public void capturephoto_Automation() {

		AUtils.sleepNow(1000);
		driver.findElementById("captureImageButton").click();
		driver.findElementById("com.nakshatratechnohub.hubsched.visitorscanner:id/btnRetake").click();
		driver.findElementById("captureImageButton").click();
		driver.findElementById("next_button").click();
	}
}


