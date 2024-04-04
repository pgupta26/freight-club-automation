package com.qualitesoft.freightclub.testscripts.managedefaults;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.freightclub.pageobjects.ManageDefaultsObjects;

import models.managedefaults.ManageDefaults;


public class TestUpdateManageDefaultsFields extends InitializeTest {
	
	@Test
	public void testUpdateManageDefaultsFields() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		ManageDefaults manageDefaults = gson.fromJson(br, ManageDefaults.class);
		
		ManageDefaultsObjects manageDefaultsObj = new ManageDefaultsObjects(driver);
		
		manageDefaultsObj.clickManageDefaults();
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default LTL Service Level", "Back Of Truck (No Liftgate)");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Pick Up Location Type", "Residential");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Parcel Pick Up Location", "company11oct");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default LTL Pick Up Location", "company11oct");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Drop Off Location Type", "Commercial");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Drop Off Location", "company11oct");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Pick Up Window", "Today");
		
		manageDefaultsObj.setDefaultSpecialInstructions("Default Special Instructions");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Landing Page", "Quick Quote");
		
		manageDefaultsObj.setSelectDropdownFieldValue("Default Label File Name", "Customer PO");
		
		manageDefaultsObj.enableDontShowTheServiceLevelCheckbox();
		
		manageDefaultsObj.enableIncludeCustomerPOBarcodeOnBillOfLadingCheckbox();
		
		manageDefaultsObj.setSelectDropdownFieldValue("Contact Phone Number", "User Supplied Number");
		
		manageDefaultsObj.setContactPhoneNumber("12345");
		
		//manageDefaults.clickSaveInfoButton();
		
	}

}
