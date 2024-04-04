package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;

public class ManageLocations {

	WebDriver driver;

	public ManageLocations(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement manageLocationsLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Profile/Locations']"), 30);
	}

	public WebElement addNewPickupLocButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver,
				By.xpath("//button[@class='btn btn-primary btn-sm margin-bottom-20 width-100-pct']"), 30);
	}

	public WebElement searchLocation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("locationsListFilter"), 30);
	}

	public boolean isLocationExist(String locationName) {
		SeleniumFunction.sendKeys(this.searchLocation(), locationName);
		WaitTool.sleep(2);
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[text()='No items to display']"));
	}

	public WebElement companyNameTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Name']"), 30);
	}

	public WebElement address1Textfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address1']"), 30);
	}

	public WebElement address2Textfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address2']"), 30);
	}

	public WebElement postalCodeTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='PostalCode']"), 30);
	}

	public WebElement locationTypeField() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("LocationType"), 30);
	}

	public WebElement earliestPickUpField() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("earliestPickup"), 30);
	}

	public WebElement latestPickUpField() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("latestPickup-select"), 30);
	}

	public WebElement earliestDropOffField() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("earliestDropOff"), 30);
	}

	public WebElement latestDropOffField() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("latestDropOff-select"), 30);
	}

	public WebElement contactType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("ContactType"), 30);
	}

	public WebElement firstNameTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='CFirstName']"), 30);
	}

	public WebElement lastNameTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='CLastName']"), 30);
	}

	public WebElement phonenoTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='CPrimaryPhone']"), 30);
	}

	public WebElement emailTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='CEmailAddress']"), 30);
	}

	public WebElement saveButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@type='submit']"), 30);
	}

	public WebElement addedLocationOnListing() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//ul[@id='locationsListView']//li)[1]"), 60);
	}

	public WebElement deleteBtn() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@id='btnDeleteLocation']"), 30);
	}

	public void fillLocationDataAndClickOnSaveBtn(String companyName, String addressLine1, String addressLine2,
			String zipCode, String locationType, String earliestPickUp, String latestPickUp, String earliestDropOff,
			String latestDropOff, String contactType, String firstName, String lastName, String phone, String email) {

		ManagerOrderPage manageOrderPage = new ManagerOrderPage(driver);

		// enter location details
		this.companyNameTextfield().clear();
		SeleniumFunction.sendKeys(this.companyNameTextfield(), companyName);
		
		this.address1Textfield().clear();
		SeleniumFunction.sendKeys(this.address1Textfield(), addressLine1);
		
		this.address2Textfield().clear();
		SeleniumFunction.sendKeys(this.address2Textfield(), addressLine2);
		
		this.postalCodeTextfield().clear();
		SeleniumFunction.sendKeys(this.postalCodeTextfield(), zipCode);
		
		SeleniumFunction.selectByVisibleText(this.locationTypeField(), locationType);

		if (locationType.equals("Commercial")) {
			SeleniumFunction.selectByVisibleText(this.earliestPickUpField(), earliestPickUp);
			
			SeleniumFunction.selectByVisibleText(this.latestPickUpField(), latestPickUp);
			
			SeleniumFunction.selectByVisibleText(this.earliestDropOffField(), earliestDropOff);
			
			SeleniumFunction.selectByVisibleText(this.latestDropOffField(), latestDropOff);
		}
		
		SeleniumFunction.selectByVisibleText(this.contactType(), contactType);
		
		this.firstNameTextfield().clear();
		SeleniumFunction.sendKeys(this.firstNameTextfield(), firstName);
		
		this.lastNameTextfield().clear();
		SeleniumFunction.sendKeys(this.lastNameTextfield(), lastName);
		
		this.phonenoTextfield().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		SeleniumFunction.sendKeys(this.phonenoTextfield(), phone);
		
		this.emailTextfield().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		SeleniumFunction.sendKeys(this.emailTextfield(), email);
		
		if (manageOrderPage.acceptFeedbackPopupStatus() == true) {
			manageOrderPage.acceptFeedbackPopup();
		}
		ScreenShot.takeScreenShot(driver, "ManageLocationsDetails");
		SeleniumFunction.clickJS(driver, this.saveButton());
		WaitTool.sleep(5);
	}

	public void verifyAddedLocation(String companyName, String addressLine1, String addressLine2, String zipCode,
			String locationType, String earliestPickUp, String latestPickUp, String earliestDropOff,
			String latestDropOff, String firstName, String lastName, String phone, String email,
			String location, String exLocation, String exContractType) {

		SeleniumFunction.sendKeys(this.searchLocation(), companyName);
		SeleniumFunction.click(this.addedLocationOnListing());
		WaitTool.sleep(5);
		String addedLocation = SeleniumFunction.getText(this.addedLocationOnListing());
		UseAssert.assertEquals(addedLocation, companyName + "\n" + addressLine1 + " " + location + " " + zipCode);

		String actualCompanyName = SeleniumFunction.getAttribute(this.companyNameTextfield(), "value");
		String actualAddressLine1 = SeleniumFunction.getAttribute(this.address1Textfield(), "value");
		String actualAddressLine2 = SeleniumFunction.getAttribute(this.address2Textfield(), "value");
		String actualZip = SeleniumFunction.getAttribute(this.postalCodeTextfield(), "value");
		String actualLocation = SeleniumFunction.getAttribute(this.locationTypeField(), "value");

		if (locationType.equals("Commercial")) {
			String actualEarliestPickUp = SeleniumFunction.getAttribute(this.earliestPickUpField(),
					"value");
			String actualLatestPickUp = SeleniumFunction.getAttribute(this.latestPickUpField(), "value");
			String actualEarliestDropOff = SeleniumFunction.getAttribute(this.earliestDropOffField(),
					"value");
			String actualLatestDropOff = SeleniumFunction.getAttribute(this.latestDropOffField(),
					"value");

			UseAssert.assertEquals(actualEarliestPickUp, earliestPickUp);
			UseAssert.assertEquals(actualLatestPickUp, latestPickUp);
			UseAssert.assertEquals(actualEarliestDropOff, earliestDropOff);
			UseAssert.assertEquals(actualLatestDropOff, latestDropOff);
		}

		String actualContactType = SeleniumFunction.getAttribute(this.contactType(), "value");
		String actualFname = SeleniumFunction.getAttribute(this.firstNameTextfield(), "value");
		String actualLname = SeleniumFunction.getAttribute(this.lastNameTextfield(), "value");
		String actualNumber = SeleniumFunction.getAttribute(this.phonenoTextfield(), "value");
		String actualEmail = SeleniumFunction.getAttribute(this.emailTextfield(), "value");

		UseAssert.assertEquals(actualCompanyName, companyName);
		UseAssert.assertEquals(actualAddressLine1, addressLine1);
		UseAssert.assertEquals(actualAddressLine2, addressLine2);
		UseAssert.assertEquals(actualZip, zipCode);
		UseAssert.assertEquals(actualLocation, exLocation);
		UseAssert.assertEquals(actualContactType, exContractType);
		UseAssert.assertEquals(actualFname, firstName);
		UseAssert.assertEquals(actualLname, lastName);
		UseAssert.assertEquals(actualNumber, phone);
		UseAssert.assertEquals(actualEmail, email);
	}
}
