package com.qualitesoft.freightclub.testscripts.managelocations;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageLocations;

public class TestEditLocation extends InitializeTest {

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
	public void updateAddedLocation() {
		
		ManageLocations manageLocationsPage = new ManageLocations(driver);

		manageLocationsPage.fillLocationDataAndClickOnSaveBtn(map.get("companyName"), map.get("addressLine1"),
				map.get("addressLine2"), map.get("zipCode"), map.get("locationType"), map.get("earliestPickUp"),
				map.get("latestPickUp"), map.get("earliestDropOff"), map.get("latestDropOff"),
				map.get("contactType"), map.get("firstName"), map.get("lastName"), map.get("phone"),
				map.get("email"));
		ScreenShot.takeScreenShot(driver, "LocationUpdated");
	}

	@Test(priority = 2)
	public void verifyAddedLocationOnListing() {		
		ManageLocations manageLocationsPage = new ManageLocations(driver);

		manageLocationsPage.verifyAddedLocation(map.get("companyName"), map.get("addressLine1"),
				map.get("addressLine2"), map.get("zipCode"), map.get("locationType"), map.get("earliestPickUp"),
				map.get("latestPickUp"), map.get("earliestDropOff"), map.get("latestDropOff"), map.get("firstName"),
				map.get("lastName"), map.get("phone"), map.get("email"), "LOS ANGELES CA", "75", "156");
	}
}
