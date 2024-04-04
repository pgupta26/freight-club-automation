package com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestAddContactInformationForUser extends InitializeTest {

	@Test
	public void testAddContactInformationForUser() {
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);
		QuickQuote quickQuote = new QuickQuote(driver);

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);
		Log.info("Row Number: "+rowIndex);	

		String contactType = xr.getCellData("ContactInfo", "ContactType", rowIndex);
		String contactName = xr.getCellData("ContactInfo", "ContactName", rowIndex);
		String phoneNumber = xr.getCellData("ContactInfo", "PhoneNumber", rowIndex);
		String emailAddress = xr.getCellData("ContactInfo", "EmailAddress", rowIndex);

		//delete all existing contacts
		SeleniumFunction.scrollDownByPixel(driver, "300");
		WaitTool.sleep(5);
		profileManagement.deleteContactsInformation();

		//Add new contact details
		quickQuote.acceptPopup();
		SeleniumFunction.click(profileManagement.addContactButton());
		SeleniumFunction.selectByVisibleText(profileManagement.contactType(), contactType);
		SeleniumFunction.sendKeys(profileManagement.contactName(), contactName);
		SeleniumFunction.sendKeys(profileManagement.phoneNumber(), phoneNumber);
		SeleniumFunction.sendKeys(profileManagement.emailAddress(), emailAddress);
		SeleniumFunction.click(profileManagement.saveContactInformation());

		//click on save profile
		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver, profileManagement.saveProfileButtonSurePost());
		ScreenShot.takeScreenShot(driver, "Profile1 saved successfully after adding contact info");
		WaitTool.sleep(20);
	}
}
