package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.crm.qa.base.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends TestBase {

	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[id='btnAdd']")
	WebElement btn_addUser;

	@FindBy(xpath = "//tr[@id='row_3']/td[contains(@id,'row')]")
	List<WebElement> col_Count;

	@FindBy(css = "button[id='btnDelete']")
	List<WebElement> btn_delete;

	@FindBy(css = "button[id='btnEdit']")
	List<WebElement> edit;

	@FindBy(css = "select[id='ddlCountry']")
	WebElement drpdn_country;

	@FindBy(css = "input[id='txtName']")
	WebElement txt_name;

	@FindBy(css = "input[id='txtSurname']")
	WebElement txt_surname;

	@FindBy(css = "input[id='txtAddress']")
	WebElement txt_address;

	@FindBy(css = "input[id='txtPostCode']")
	WebElement txt_postCode;

	@FindBy(css = "input[id='txtCity']")
	WebElement txt_city;

	@FindBy(css = "button[id='btnSave']")
	WebElement btn_save;

	@FindBy(css = "table[id='lisTable'] tr")
	List<WebElement> rowcount;

	@FindBy(css = "select[id='ddlCountry'] option")
	List<WebElement> drp_countryList;
	
	@FindBy(css = "[class='modal-footer'] button[class='btn btn-secondary']")
	WebElement cnfrm_Delete;
	
	/**
	 * @param driver
	 * @param expected_Details
	 * @param actual_Details
	 * @return
	 */
	public boolean verifyUserDetails(WebDriver driver, List<String> expected_Details, List<String> actual_Details) {
		
		Iterator column = col_Count.iterator();
		while (column.hasNext()) {
			int colCount = col_Count.size();
			for (int i = 0; i < colCount; i++) {
				WebElement columnData = (WebElement) column.next();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String data = columnData.getText();
				System.out.println(data);
				actual_Details.add(data);

			}

		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual_Details.remove(5);
		
		if (actual_Details.equals(expected_Details)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void deleteButon(WebDriver driver, int rowNo) throws InterruptedException {

		btn_delete.get(rowNo).click();
		log.info("User clicked on delete button in row no :" + rowNo);
		//Thread.sleep(1000);
		cnfrm_Delete.click();
		log.info("User confirmed on delete operation on user record");

	}

	
	/**
	 * @param driver
	 * @param rowno
	 */
	public void rowEdit(WebDriver driver, int rowno) {

		edit.get(rowno).click();
		log.info("User clicked on edit button in row no : " + rowno);

	}

	
	/**
	 * @param driver
	 * @param countryName
	 */
	public void countryDropdown(WebDriver driver, String countryName) {
		drpdn_country.click();
		org.openqa.selenium.support.ui.Select dropdown = new Select(drpdn_country);
		dropdown.selectByVisibleText(countryName);
	}

	
	/**
	 * @param driver
	 * @param testData
	 */
	public void addUserDetails(WebDriver driver, List<String> testData) {

		btn_addUser.click();
		log.info("User clicked on add user button");
		
		txt_name.clear();
		txt_name.sendKeys(testData.get(0));
		log.info("User entered first name is : " + testData.get(0));

		txt_surname.clear();
		txt_surname.sendKeys(testData.get(1));
		log.info("User entered surname is : " + testData.get(1));

		txt_address.clear();
		txt_address.sendKeys(testData.get(2));
		log.info("User entered address is : " + testData.get(2));

		txt_postCode.clear();
		txt_postCode.sendKeys(testData.get(3));
		log.info("User entered postcode is : " + testData.get(3));

		txt_city.clear();
		txt_city.sendKeys(testData.get(4));
		log.info("User entered city name is : " + testData.get(4));

		countryDropdown(driver, testData.get(5));
		btn_save.click();
		log.info("User entered county name is : " + testData.get(5));

	}
	
	
	/**
	 * @param driver
	 * @param testData
	 */
	public void updateUserDetails(WebDriver driver, List<String> testData) {

		//btn_addUser.click();
		//log.info("User clicked on add user button");
		
		txt_name.clear();
		txt_name.sendKeys(testData.get(0));
		log.info("User entered first name is : " + testData.get(0));

		txt_surname.clear();
		txt_surname.sendKeys(testData.get(1));
		log.info("User entered surname is : " + testData.get(1));

		txt_address.clear();
		txt_address.sendKeys(testData.get(2));
		log.info("User entered address is : " + testData.get(2));

		txt_postCode.clear();
		txt_postCode.sendKeys(testData.get(3));
		log.info("User entered postcode is : " + testData.get(3));

		txt_city.clear();
		txt_city.sendKeys(testData.get(4));
		log.info("User entered city name is : " + testData.get(4));

		countryDropdown(driver, testData.get(5));
		btn_save.click();
		log.info("User entered county name is : " + testData.get(5));

	}

	/**
	 * @param driver
	 * @return
	 */
	public int tableRowCount(WebDriver driver) {
		int count = rowcount.size();
		log.info("row count in table is" + count);
		return count;
	}

	
	
}
