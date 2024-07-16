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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Hubsched_android_Automation {

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
		cap.setCapability("appium:ELEMENT_SCROLL_BEHAVIOR", "Enable");
		cap.setCapability("automationName", "UiAutomator2");

		cap.setCapability("appPackage", "com.nakshatratechnohub.hubsched");
		cap.setCapability("appActivity", "com.nakshatratechnohub.hubsched.ui.auth.SplashScreen");

		URL url = null;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver = new AndroidDriver<AndroidElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElementById("email_id").sendKeys("prashantpatil09887@gmail.com");
		driver.findElementById("password_id").sendKeys("123456");
		driver.findElement(By.id("login_btn")).click();
	}

	@Test(priority = 1)
	public void createExternalMeeting() {

		for (int i = 0; i < 1; i++) {
			driver.findElement(By.id("add_meeting")).click();
			driver.findElement(By.id("externalBtn")).click();

			MobileElement assignTo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_assign"));
			assignTo.click();

			List<AndroidElement> assignToCheck = driver
					.findElements(By.id("com.nakshatratechnohub.hubsched:id/checkBox"));
			if (assignToCheck.size() > 0) {
				assignToCheck.get(0).click();
			}

			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/done_btn")).click();

			MobileElement ext_location = driver.findElement(By.id("external_location"));
			ext_location.sendKeys(String.valueOf(AUtils.generateRandomLocation()));

			MobileElement meetingSubj = driver.findElement(By.id("external_subject"));
			meetingSubj.sendKeys(String.valueOf(AUtils.generateRandomSubject()));

			driver.findElement(By.id("external_date")).click();
			driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"OK\")")).click();

			// driver.findElement(By.id("external_startTime")).click();
			MobileElement dropdown1 = driver.findElement(By.id("external_startTime"));
			dropdown1.click();

			List<AndroidElement> options1 = driver.findElements(By.id("android:id/text1"));
			if (options1.size() > 0) {
				options1.get(0).click();
			} else {
				System.out.println("No option available at index 1");
			}
			MobileElement dropdown2 = driver.findElement(By.id("external_EndTime"));
			dropdown2.click();

			List<AndroidElement> options2 = driver.findElements(By.id("android:id/text1"));
			if (options2.size() > 0) {
				options2.get(0).click();
			} else {
				System.out.println("No option available at index 1");
			}

			MobileElement addguestbtn = driver.findElement(By.id("add_guest"));
			addguestbtn.click();

			driver.findElement(By.id("guest_name")).sendKeys("Prashant Patil");
			driver.findElement(By.id("guest_mobile")).sendKeys("9665505383");
			driver.findElement(By.id("guest_email")).sendKeys("pp1946285@gmail.com");
			driver.findElement(By.id("guest_info")).sendKeys("Please bring your system with us");
			driver.findElement(By.id("guest_save_btn")).click();
			AUtils.sleepNow(1000);
			driver.findElement(By.id("create_meeting_btn")).click();
		}
	}

	@Test(priority = 2)
	public void manageMeetings() {
		AUtils.sleepNow(2000);
		// TouchAction touchAction1 = new TouchAction(driver);
		// int x1 = 92; // X coordinate
		// int y1 = 155; // Y coordinate
		// touchAction1.tap(PointOption.point(x1, y1)).perform();
		AUtils.tapAtCoordinates(driver, 92, 155);

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/meetingList")).click();
		List<AndroidElement> options2 = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/meet_cancel_btn"));
		if (options2.size() > 0) {
			options2.get(0).click();
		}
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Complete\"]")).click();

		MobileElement remark = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/etRemark"));
		remark.sendKeys(
				" We're here to discuss the quarterly performance and strategize for the next quarter. Let's dive right into the agenda to make the most of our time together.");

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Add photo\"]")).click();
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 538, 2030);
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 935, 2030);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();
		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/ex_submit_btn")).click();

		MobileElement scanmsg = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/etMessage"));
		scanmsg.click();
		scanmsg.sendKeys(
				"The system generating the QR code might be experiencing technical difficulties, causing the QR code to not be generated");

		MobileElement confirmbtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		confirmbtn.click();

		cancelExtMeet();

	}

	public void cancelExtMeet() {

		AUtils.sleepNow(2000);
		List<AndroidElement> options3 = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/meet_cancel_btn"));
		if (options3.size() > 0) {
			options3.get(0).click();
		}
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Cancel\"]")).click();

		MobileElement reason = driver.findElement(By.id("add_cancel_reason"));
		reason.click();
		driver.findElement(By.id("descriptionET")).click();

		MobileElement description = driver.findElement(By.id("descriptionET"));
		description.sendKeys("Now this meeting is cancell,sorry for inconvenience...");

		MobileElement add_photo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/addImageBtn"));
		add_photo.click();

		MobileElement cancel_popup = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancel_popup.click();

		MobileElement add_photo1 = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/addImageBtn"));
		add_photo1.click();

		MobileElement camera = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/llCamera"));
		camera.click();

		AUtils.tapAtCoordinates(driver, 538, 2030);
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 935, 2030);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();
		AUtils.sleepNow(1000);
		MobileElement submitbtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/submit_btn"));
		submitbtn.click();

		deleteExtMeet();
	}

	public void deleteExtMeet() {
		List<AndroidElement> delete_option = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/meet_delete_btn"));
		if (delete_option.size() > 0) {
			delete_option.get(0).click();
		}
		MobileElement cancel_btn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancel_btn.click();

		AUtils.sleepNow(1000);

		if (delete_option.size() > 0) {
			delete_option.get(0).click();
		}

		MobileElement confirm_btn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		confirm_btn.click();
	}

	@Test(priority = 3)
	public void internalMeetingAutomation() {

		for (int j = 0; j < 2; j++) {

			driver.findElement(By.id("add_meeting")).click();
			driver.findElement(By.id("internalBtn")).click();

			driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"Conference Room 1\")")).click();

			driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"OK\")")).click();

			driver.findElement(By.id("selectStartTime")).click();

			List<AndroidElement> options1 = driver.findElements(By.id("android:id/text1"));
			if (options1.size() > 0) {
				options1.get(0).click();
			}

			driver.findElement(By.id("selectEndTime")).click();

			List<AndroidElement> options2 = driver.findElements(By.id("android:id/text1"));
			if (options2.size() > 0) {
				options2.get(0).click();
			}

			MobileElement bookslot_btn = driver
					.findElement(By.id("com.nakshatratechnohub.hubsched:id/check_availability_btn"));
			bookslot_btn.click();

			MobileElement meet_subject = driver.findElement(By.id("meeting_subject"));
			meet_subject.sendKeys(String.valueOf(AUtils.generateRandomSubject()));

			driver.findElement(By.id("select_employees")).click();

			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/clear_btn")).click();

			List<AndroidElement> checkBoxes = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/checkBox"));
			for (int i = 0; i < 5 && i < checkBoxes.size(); i++) {
				checkBoxes.get(i).click();
			}

			MobileElement donebtn = driver.findElement(By.id("done_btn"));
			donebtn.click();

//		MobileElement addguestbtn = driver.findElement(By.id("add_guest"));
//		addguestbtn.click();
//
//		driver.findElement(By.id("guest_name")).sendKeys("Prashant Patil");
//		driver.findElement(By.id("guest_mobile")).sendKeys("9665505383");
//		driver.findElement(By.id("guest_email")).sendKeys("pp1946285@gmail.com");
//		driver.findElement(By.id("guest_info")).sendKeys("Please bring your system with us");
//		driver.findElement(By.id("guest_save_btn")).click();
//		AUtils.sleepNow(1000);

			MobileElement description = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/etDescription"));
			description.sendKeys("Agenda of this meeting is to discuss sprint work/task");

			driver.findElement(By.id("create_meeting_btn")).click();
			AUtils.sleepNow(3000);
			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
			AUtils.sleepNow(3000);
			// driver.pressKeyCode(4);

		}
		cancelDeleteIntMeet();

	}

	public void cancelDeleteIntMeet() {
		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/meetingList")).click();
		List<AndroidElement> options2 = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/meet_cancel_btn"));
		if (options2.size() > 0) {
			options2.get(0).click();
		}
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();
		AUtils.sleepNow(1000);

		if (options2.size() > 0) {
			options2.get(0).click();
		}

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes")).click();

		deleteExtMeet();

	}

	@Test(priority = 4)
	public void manageRoomAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement manageRoom = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/hallManagement"));
		manageRoom.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_room")).click();

		MobileElement roomNo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/create_room_no"));
		roomNo.sendKeys(String.valueOf(AUtils.generateRandomRoomNo()));

		MobileElement roomName = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/create_room_name"));
		roomName.sendKeys(String.valueOf(AUtils.generateRandomRoomName()));

		MobileElement setCapacity = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/create_room_seat"));
		setCapacity.sendKeys(String.valueOf(AUtils.generateRandomSeatCapacity()));

		MobileElement floorNo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/create_room_floor"));
		floorNo.sendKeys(String.valueOf(AUtils.generateRandomFloorNo()));

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/select_facilities")).click();

		MobileElement cancelbtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancelbtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/select_facilities")).click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		List<AndroidElement> facilityCheckBoxes = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/cbOption"));
		for (int i = 0; i < 5 && i < facilityCheckBoxes.size(); i++) {
			facilityCheckBoxes.get(i).click();
		}

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvClearAll")).click();

		List<AndroidElement> facilityCheckBoxes1 = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/cbOption"));
		for (int i = 0; i < 5 && i < facilityCheckBoxes1.size(); i++) {
			facilityCheckBoxes1.get(i).click();
		}

		MobileElement confirmbtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		confirmbtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/create_room_btn")).click();

		deleteRoom();
	}

	public void deleteRoom() {
//		AUtils.sleepNow(1000);
//		AUtils.tapAtCoordinates(driver, 92, 155);
//		MobileElement manageRoom = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/hallManagement"));
//		manageRoom.click();
		AUtils.sleepNow(1000);
		List<AndroidElement> morebtn = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/view"));
		if (morebtn.size() > 0) {
			morebtn.get(2).click();
		}

		morebtn.get(2).click();

		List<AndroidElement> removeRoomBtn = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/remove_room_btn"));
		if (removeRoomBtn.size() > 0) {
			removeRoomBtn.get(2).click();
		}

		MobileElement cancelbtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancelbtn.click();
		AUtils.sleepNow(1000);
		removeRoomBtn.get(2).click();

		MobileElement confirmBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		confirmBtn.click();

	}

	@Test(priority = 5)
	public void managePantryAutomation() {
		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/managePantry")).click();

		MobileElement addPantryBtn = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_pantry_item_btn"));
		addPantryBtn.click();

		MobileElement cancelBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancelBtn.click();

		AUtils.sleepNow(1000);
		addPantryBtn.click();

		MobileElement pantryImgBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/pre_img"));
		pantryImgBtn.click();

		List<AndroidElement> deviceImg = driver.findElements(By.id("com.google.android.documentsui:id/item_root"));
		if (deviceImg.size() > 0) {
			deviceImg.get(2).click();
		}

		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		MobileElement itemName = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/item_edittext"));
		itemName.sendKeys("Tea");

		MobileElement saveBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		saveBtn.click();

		List<AndroidElement> deleteItemBtn = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/delete_item"));
		if (deleteItemBtn.size() > 0) {
			deleteItemBtn.get(3).click();
		}

		MobileElement cancelAlertBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel"));
		cancelAlertBtn.click();

		AUtils.sleepNow(1000);
		deleteItemBtn.get(3).click();

		MobileElement confirmBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		confirmBtn.click();

		MobileElement closeIcon = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/close_dialog"));
		closeIcon.click();

	}

	@Test(priority = 6)
	public void createEmployeeAutomation() {

		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/employeeList")).click();

		MobileElement addEmpBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp"));
		addEmpBtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/user_type")).click();

		MobileElement empId = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_id"));
		empId.click();
		empId.sendKeys(String.valueOf(AUtils.generateRandomId()));

		MobileElement empName = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_name"));
		empName.sendKeys(String.valueOf(AUtils.generateRandomName()));

		MobileElement email = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_email"));
		email.sendKeys("pp1946285@gmail.com");

		MobileElement mobileNo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_mobile"));
		mobileNo.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_gender")).click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_birthdate")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_birthdate")).click();
		driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"OK\")")).click();

		MobileElement department = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_department"));
		department.click();

		driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"IT\")")).click();

		MobileElement relationManager = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_relation"));
		relationManager.click();

		List<AndroidElement> checkBox = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/checkBox"));
		if (checkBox.size() > 0) {
			checkBox.get(0).click();
		}

		MobileElement clearallBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/clear_btn"));
		clearallBtn.click();

		List<AndroidElement> checkBox1 = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/checkBox"));
		if (checkBox1.size() > 0) {
			checkBox1.get(0).click();
		}

		MobileElement doneBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/done_btn"));
		doneBtn.click();

		MobileElement position = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_position"));
		position.click();

		driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"Android developer\")")).click();

		MobileElement trackableBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/scTrackable"));
		trackableBtn.click();

		MobileElement addempBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_btn"));
		addempBtn.click();

//	driver.findElement(MobileBy.AndroidUIAutomator(
//            "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

	}

	@Test(priority = 7)
	public void updateEmployeeAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/employeeList")).click();

		List<AndroidElement> editEmpBtn = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/edit_emp_btn"));
		for (int i = 0; i < 2 && i < editEmpBtn.size(); i++) {
			editEmpBtn.get(i).click();

			MobileElement mobileNo = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_mobile"));
			mobileNo.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));

			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_gender")).click();
			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_birthdate")).click();

			driver.findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

			MobileElement trackableBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/scTrackable"));
			trackableBtn.click();

			MobileElement updateNowBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_btn"));
			updateNowBtn.click();
			AUtils.sleepNow(1000);
		}
		List<AndroidElement> toggleBtn = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/block_emp_btn"));
		if (toggleBtn.size() > 0) {
			toggleBtn.get(1).click();
		}

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();
		toggleBtn.get(1).click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes")).click();

	}

	@Test(priority = 8)
	public void eventBannerAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement eventBanner = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/editContent"));
		eventBanner.click();

		MobileElement addBannerBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/addBannerBtn"));
		addBannerBtn.click();

		MobileElement bannerImg = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/pre_img"));
		bannerImg.click();

		List<AndroidElement> deviceImg = driver.findElements(By.id("com.google.android.documentsui:id/item_root"));
		if (deviceImg.size() > 0) {
			deviceImg.get(0).click();
		}
		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		MobileElement bannerUrl = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/item_edittext"));
		bannerUrl.sendKeys("https://hubsched.com");

		MobileElement saveBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		saveBtn.click();

		AUtils.sleepNow(1000);
		addBannerBtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();

		List<AndroidElement> deleteBannerImg = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/delete_banner_img"));
		if (deleteBannerImg.size() > 0) {
			deleteBannerImg.get(0).click();
		}

		MobileElement yestBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes"));
		yestBtn.click();

		List<AndroidElement> deleteBannerImg1 = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/delete_banner_img"));
		if (deleteBannerImg1.size() > 0) {
			deleteBannerImg1.get(0).click();
		}

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();

		MobileElement news = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/announcement_txt"));
		news.sendKeys("Wellcome to HubSched...!!");

		MobileElement udateNews = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/uploadImgBtn"));
		udateNews.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();

	}

	@Test(priority = 9)
	public void announcementAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement announcement = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/action_announcement"));
		announcement.click();

		List<AndroidElement> cardView = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/parentCardView"));
		if (cardView.size() > 0) {
			cardView.get(0).click();
		}
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
	}

	@Test(priority = 10)
	public void voucherAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement calimExpenses = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/voucher"));
		calimExpenses.click();

		MobileElement addBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/fabActionBtn"));
		addBtn.click();

		MobileElement voucherType = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_voucher_type"));
		voucherType.sendKeys("Traveling voucher");

		MobileElement voucherAmount = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_voucher_amount"));
		voucherAmount.sendKeys("1000");

		MobileElement voucherDescription = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/descriptionET"));
		voucherDescription.sendKeys("Date of Travel: June 10, 2024 " + "Traveler's Name: John Doe"
				+ "Destination:Pune It park " + "Purpose of Travel: Business meeting with XYZ Corporation "
				+ "Travel Mode: By car " + "Accommodation Details: Hotel Name: Marriott Marquis "
				+ "Check-in Date: June 10, 2024 " + "Check-out Date: June 12, 2024 " + "Room Type: Single Deluxe Roo");

		MobileElement addPhotoBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/addImageBtn"));
		addPhotoBtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/llGallery")).click();
		List<AndroidElement> deviceImg = driver.findElements(By.id("com.google.android.documentsui:id/item_root"));
		if (deviceImg.size() > 0) {
			deviceImg.get(0).click();
		}

		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		AUtils.sleepNow(1000);
		addPhotoBtn.click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();

		MobileElement addVoucherBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/submit_btn"));
		addVoucherBtn.click();

		List<AndroidElement> voucherCardView = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/typeTV"));
		if (voucherCardView.size() > 0) {
			voucherCardView.get(0).click();
		}
		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
	}

	@Test(priority = 11)
	public void ticketAutomation() {
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement ticket = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/callLogs"));
		ticket.click();

		MobileElement addBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/fabActionBtn"));
		addBtn.click();

		MobileElement ticketType = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_call_log_type"));
		ticketType.sendKeys("Broken chair");

		MobileElement department = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/add_emp_department"));
		department.click();

		driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"IT\")")).click();

		MobileElement description = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/descriptionET"));
		description.sendKeys("Room No:2, Floor:1,Room Name:Conference room 1");

		MobileElement addPhotoBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/addImageBtn"));
		addPhotoBtn.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/llGallery")).click();
		List<AndroidElement> deviceImg = driver.findElements(By.id("com.google.android.documentsui:id/item_root"));
		if (deviceImg.size() > 0) {
			deviceImg.get(0).click();
		}

		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		AUtils.sleepNow(1000);
		addPhotoBtn.click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();

		MobileElement addVoucherBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/submit_btn"));
		addVoucherBtn.click();

		List<AndroidElement> ticketCardView = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/typeTV"));
		if (ticketCardView.size() > 0) {
			ticketCardView.get(0).click();
		}
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();

	}

	@Test(priority = 12)
	public void myAttendance() {
		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement ticket = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/item_attendance"));
		ticket.click();
		
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();

	}

	@Test(priority = 13)
	public void employeeAttendance() {
		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		MobileElement ticket = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/item_employee_attendance"));
		ticket.click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		MobileElement filterIcon = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/filter"));
		filterIcon.click();

		MobileElement selectDayBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/selectDateET"));
		selectDayBtn.click();

		driver.findElement(new MobileBy.ByAndroidUIAutomator("text(\"OK\")")).click();

		MobileElement submitBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/btnSubmitRL"));
		submitBtn.click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/filter")).click();

		MobileElement month = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/monthTV"));
		month.click();

		MobileElement spinnerMonth = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/spinnerMonth"));
		spinnerMonth.click();

		List<AndroidElement> monthList = driver
				.findElements(By.id("com.nakshatratechnohub.hubsched:id/spinner_item_text"));
		if (monthList.size() > 0) {
			monthList.get(2).click();
		}
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/btnSubmitRL")).click();

		for (int i = 0; i < 3; i++) {
			List<AndroidElement> empName = driver.findElements(By.id("com.nakshatratechnohub.hubsched:id/empNameTV"));
			if (empName.size() > 0) {
				empName.get(i).click();
			}

			driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
		}

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
	}

	@Test(priority = 14)
	public void raiseQueryAutomation() {
		AUtils.sleepNow(2000);
		AUtils.tapAtCoordinates(driver, 92, 155);

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		MobileElement feedback = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/support"));
		feedback.click();

		MobileElement havingIssueBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/having_issue_btn"));
		havingIssueBtn.click();

		MobileElement queryEditText = driver
				.findElement(By.id("com.nakshatratechnohub.hubsched:id/get_query_edit_text"));
		queryEditText.sendKeys(
				"In android device (Android 14) when I deny the location permission after login then HubSched application is crash in my device");

		MobileElement submitQueryBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/submit_query_btn"));
		submitQueryBtn.click();

		AUtils.sleepNow(2000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/having_issue_btn")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/cancel_btn")).click();

	}

	@Test(priority = 15)
	public void fragmentAutomation() {
		AUtils.sleepNow(2000);

		MobileElement history = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/meeting"));
		history.click();

		AUtils.sleepNow(3000);

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

		MobileElement notification = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/notification"));
		notification.click();

		MobileElement account = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/account"));
		account.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/user_img")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/llGallery")).click();

		List<AndroidElement> deviceImg = driver.findElements(By.id("com.google.android.documentsui:id/item_root"));
		if (deviceImg.size() > 0) {
			deviceImg.get(0).click();
		}

		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes")).click();

		AUtils.sleepNow(1000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/user_img")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/llCamera")).click();

		AUtils.tapAtCoordinates(driver, 536, 2025);
		AUtils.sleepNow(1000);
		AUtils.tapAtCoordinates(driver, 934, 2035);

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/menu_crop")).click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvYes")).click();
		
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/user_img")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/tvCancel")).click();

		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
		
		MobileElement attendance = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/mcv_attendance"));
		attendance.click();
		
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/back")).click();
		
		MobileElement vcardQR = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/vcard"));
		vcardQR.click();

		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/backQrShow")).click();

		MobileElement logoutBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/log_out_btn"));
		logoutBtn.click();

		MobileElement NoBtn = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/no_btn"));
		NoBtn.click();

		AUtils.sleepNow(2000);
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/log_out_btn")).click();
		driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/yes_btn")).click();
	}

}
