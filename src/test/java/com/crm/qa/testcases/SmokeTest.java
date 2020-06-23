package com.crm.qa.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.DashboardPage;

public class SmokeTest extends TestBase {
	
	DashboardPage dashboard;

	public static List<String> expected_Details = new ArrayList<String>();
	public static String[] array = { "chandrika", "nagandla", "kinning park", "G51 1TQ", "Glasgow", "United Kingdom" };
	public static List<String> actual_Details = new ArrayList<String>();

	@BeforeMethod(alwaysRun = false)
	public void setUp() {
		initializaton();
		expected_Details = Arrays.asList(array);
		dashboard = new DashboardPage();
	}

	
	@Test(description = "To verify user able to add new user in customer portal")
	
	//@Test(priority=1)
	public void TC01() {

		int beforeAdding = dashboard.tableRowCount(driver);

		dashboard.addUserDetails(driver, expected_Details);

		int afterAdding = dashboard.tableRowCount(driver);

		if (afterAdding > beforeAdding) {
			Assert.assertTrue(afterAdding > beforeAdding,
					"User records count increased successfully after adding new user record");
		} else {
			Assert.fail("User records count not correctly updated after adding new user");
		}
		
		driver.close();
	}

	@Test(description = "To verify user able to Edit newly added user details in customer portal")
	//@Test(priority=3)
	public void TC02() {

		dashboard.addUserDetails(driver, expected_Details);
		expected_Details.set(3, "fsrgregt");
		dashboard.rowEdit(driver, 3);
		dashboard.updateUserDetails(driver, expected_Details);

		if (dashboard.verifyUserDetails(driver, expected_Details, actual_Details))
			Assert.assertTrue(true, "User entered details correctly added");
		else
			Assert.fail("user entered details are not correctly updated");
		
		driver.close();
	}

	@Test(description = "To verify user able to delete newly added user details in customer portal")
	//@Test(priority=2)
	public void TC03() throws InterruptedException {

		dashboard.addUserDetails(driver, expected_Details);
		
		int beforeAdding = dashboard.tableRowCount(driver);

		dashboard.deleteButon(driver, 3);

		int afterAdding = dashboard.tableRowCount(driver);

		if (afterAdding < beforeAdding) {
			Assert.assertTrue(true, "User records count increased successfully after adding new user record");
		} else {
			Assert.fail("User records count not correctly updated after adding new user");
		}
		
		driver.close();

	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		// FormatResult();
		tearDownMain();
	}

}
