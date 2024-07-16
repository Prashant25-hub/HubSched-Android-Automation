package com.nakshatratechnohub.hubsched;

import java.util.Random;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class AUtils {

	static AndroidDriver<AndroidElement> driver;

	static Random random = new Random();

	static String[] meetingLocation = { "F-89 Ambad MIDC Nashik", "Pune", "F-89 Nashik", "Nakshatratechnohub Nashik" };
	static String[] meetingSubject = { "Module Discussion", "Sprint Discussion", "New Implementation",
			"Brief Discussion" };

	public static void sleepNow(int msecond) {

		try {
			Thread.sleep(msecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String generateRandomLocation() {
		Random random = new Random();
		int randomLocationIndex = random.nextInt(meetingLocation.length);

		String Location = meetingLocation[randomLocationIndex];

		return Location;
	}

	public static String generateRandomSubject() {
		Random random = new Random();
		int randomSubjectIndex = random.nextInt(meetingSubject.length);
		String subject = meetingSubject[randomSubjectIndex];
		return subject;
	}

	public static void openeSidebarMenu(AndroidDriver<AndroidElement> driver2, String menuResourceId) {
		// Click on the top app bar to open the drawer
		MobileElement topAppBar = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/topAppBar"));
		topAppBar.click();

		// Open the navigation drawer
		MobileElement drawerLayout = driver.findElement(By.id("com.nakshatratechnohub.hubsched:id/drawer_layout"));
		drawerLayout.click();

		// Click on the specific menu item in the drawer
		MobileElement menuItem = driver.findElement(By.id(menuResourceId));
		menuItem.click();

	}

	public static void tapAtCoordinates(MobileDriver driver, int x, int y) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(x, y)).perform();

	}

	public static String generateRandomRoomNo() {
		Random random = new Random();
		int roomNumber = 10 + random.nextInt(900);
		return String.valueOf(roomNumber);

	}

	public static String generateRandomRoomName() {
		String[] roomNames = { "Presentation Hall", "Conference Room 1", "Boardroom A", "Boardroom B" };

		int randomRoomNameIndex = random.nextInt(roomNames.length);
		String roomName = roomNames[randomRoomNameIndex];
		return roomName;
	}

	public static String generateRandomSeatCapacity() {
		int seatCapacity = 10 + random.nextInt(91);
		return String.valueOf(seatCapacity);
	}

	public static String generateRandomFloorNo() {
		Random random = new Random();
		int floorNumber = 1 + random.nextInt(10);
		return String.valueOf(floorNumber);
	}
	
	public static String generateRandomId() {
		int randomID = 1000 + random.nextInt(9000);
		return String.valueOf(randomID);

	}
	public static String generateRandomName() {

		String[] givenFirstNames = { "Raj", "Amit", "Sonia", "Rahul", "Priya", "Sanjay", "Neha", "Rajesh", "Anita",
				"Arun", "Anjali", "Manoj", "Kavita", "Vikram", "Pooja" };
		String[] givenLastNames = { "Sharma", "Verma", "Singh", "Gupta", "Kumar", "Chopra", "Patel", "Jain", "Mehta",
				"Pandey" };

		int randomFirstNameIndex = random.nextInt(givenFirstNames.length);
		int randomLastNameIndex = random.nextInt(givenLastNames.length);

		String randomFirstName = givenFirstNames[randomFirstNameIndex];
		String randomLastName = givenLastNames[randomLastNameIndex];
		// Concatenating first name and last name for the name field
		String name = randomFirstName + " " + randomLastName;
		return name;
	}
	
	public static String generateRandomMobileNumber() {
		
		StringBuilder mobileNumber = new StringBuilder();

		// The first digit of a phone number should not be zero
		 mobileNumber.append(6 + random.nextInt(4)); // Adding a random digit between 6 and 9 as the first digit

		// Generating the remaining 9 digits
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(random.nextInt(10)); // Adding random digits between 0 and 9
		}

		return mobileNumber.toString();
	}
	
}

//	public static void openSideBarWithMenu() {
//		TouchAction touchAction1 = new TouchAction(driver);
//		int x1 = 92; // X coordinate
//		int y1 = 155; // Y coordinate
//		touchAction1.tap(PointOption.point(x1, y1)).perform();
//        sleepNow(2000);
//        // Click on the top app bar button to open the drawer
//        MobileElement topAppBar = driver.findElement(MobileBy.id("topAppBar"));
//        topAppBar.click();
//
//        // Open the drawer layout
//        MobileElement drawerLayout = driver.findElement(MobileBy.id("drawer_layout"));
//        drawerLayout.click(); // Assuming clicking on the drawer layout opens the drawer
//
//        // Click on the specific element inside the drawer
//        MobileElement performElement = driver.findElement(MobileBy.id(""));
//        performElement.click();
//     		
//	}
