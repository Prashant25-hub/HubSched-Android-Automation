package com.nakshatratechnohub.hubsched;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class Calculator_auto {

	static AndroidDriver<WebElement> driver;

	public static void main(String[] args) {
		try {
			Calculator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Calculator() throws Exception {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "vivo Y73");
		cap.setCapability("udid", "1368055620000TL");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");

		// cap.setCapability(MobileCapabilityType.APP, "D:\\Prashant Patil\\Java
		// Projects\\HubSched_Test\\src\\main\\resources\\app-release.apk ");
//			cap.setCapability("appPackage", "com.nakshatratechnohub.hubsched");
//		    cap.setCapability("appActivity", "com.nakshatratechnohub.hubsched.ui.auth.LoginActivity");
		cap.setCapability("appPackage", "com.vivo.calculator");
		cap.setCapability("appActivity", "com.vivo.calculator.Calculator");

		@SuppressWarnings("deprecation")
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<WebElement>(url, cap);
		Thread.sleep(5000);
		driver.findElementById("com.vivo.calculator:id/digit5").click();
		driver.findElementById("com.vivo.calculator:id/plus").click();
		driver.findElementById("com.vivo.calculator:id/digit6").click();
		driver.findElementById("com.vivo.calculator:id/equal").click();

		String result = driver.findElementById("com.vivo.calculator:id/input_edit").getText();

		if (result.equals("11")) {
			System.out.println("Test passed...");
		} else {
			System.out.println("Test Failed...");
		}

		Thread.sleep(5000);

	}

}
//   System.out.println("Application started.....");
