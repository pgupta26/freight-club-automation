package com.qualitesoft.freightclub.testscripts.managelocations;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageLocations;

public class TestAddLocation extends InitializeTest {
	private HashMap<String, String> map;
	private Xls_Reader xr;
	private int i;

	@BeforeMethod
	public void setExcelDataInMap(){
		map = new HashMap<String, String>();
		xr = new Xls_Reader(testData);
		i = Integer.parseInt(Row);

		map.put("companyName", xr.getCellData("Input", "CompanyName", i).trim());
		map.put("addressLine1", xr.getCellData("Input", "AddressLine1", i).trim());
		map.put("addressLine2", xr.getCellData("Input", "AddressLine2", i).trim());
		map.put("zipCode", xr.getCellData("Input", "ZipCode", i).trim());
		map.put("locationType", xr.getCellData("Input", "LocationType", i).trim());
		map.put("earliestPickUp", xr.getCellData("Input", "EarliestPickUp", i).trim());
		map.put("latestPickUp", xr.getCellData("Input", "LatestPickUp", i).trim());
		map.put("earliestDropOff", xr.getCellData("Input", "EarliestDropOff", i).trim());
		map.put("latestDropOff", xr.getCellData("Input", "LatestDropOff", i).trim());
		map.put("contactType", xr.getCellData("Input", "ContactType", i).trim());
		map.put("firstName", xr.getCellData("Input", "FirstName", i).trim());
		map.put("lastName", xr.getCellData("Input", "LastName", i).trim());
		map.put("phone", xr.getCellData("Input", "Phone", i).trim());
		map.put("email", xr.getCellData("Input", "Email", i).trim());
	}

	@Test(priority = 1)
	public void testAddLocation() {


		ManageLocations manageLocationsPage = new ManageLocations(driver);

		SeleniumFunction.clickJS(driver, manageLocationsPage.manageLocationsLink());

		if (manageLocationsPage.isLocationExist(map.get("companyName"))) {
			// click manage locations link
			manageLocationsPage.addNewPickupLocButton();
			ScreenShot.takeScreenShot(driver, "ManageLocationsPage");

			// click add new location button
			SeleniumFunction.clickJS(driver, manageLocationsPage.addNewPickupLocButton());

			manageLocationsPage.fillLocationDataAndClickOnSaveBtn(map.get("companyName"), map.get("addressLine1"),
					map.get("addressLine2"), map.get("zipCode"), map.get("locationType"), map.get("earliestPickUp"),
					map.get("latestPickUp"), map.get("earliestDropOff"), map.get("latestDropOff"),
					map.get("contactType"), map.get("firstName"), map.get("lastName"), map.get("phone"),
					map.get("email"));
			ScreenShot.takeScreenShot(driver, "LocationSaved");
		} else {
			Log.info("Location " + map.get("companyName") + " already exist");
			ScreenShot.takeScreenShot(driver, "LocationExist");
		}
	}

	@Test(priority = 2)
	public void verifyAddedLocationOnListing() {
		ManageLocations manageLocationsPage = new ManageLocations(driver);

		manageLocationsPage.verifyAddedLocation(map.get("companyName"), map.get("addressLine1"),
				map.get("addressLine2"), map.get("zipCode"), map.get("locationType"), map.get("earliestPickUp"),
				map.get("latestPickUp"), map.get("earliestDropOff"), map.get("latestDropOff"), map.get("firstName"),
				map.get("lastName"), map.get("phone"), map.get("email"), "NEW YORK NY", "76", "20");
	}

}
