package com.qualitesoft.freightclub.testscripts.managelocations;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageLocations;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestDeleteLocation extends InitializeTest {
	
	@Test
	public void deleteAddedLocation(){
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String companyName = xr.getCellData("Input","CompanyName", i).trim();
		
		ManageLocations manageLocationsPage = new ManageLocations(driver);
		ManagerOrderPage manageOrderPage = new ManagerOrderPage(driver);
		QuickQuote quickQuotePage = new QuickQuote(driver);
		
		SeleniumFunction.sendKeys(manageLocationsPage.searchLocation(), companyName);
		SeleniumFunction.click(manageLocationsPage.addedLocationOnListing());
		if(manageOrderPage.acceptFeedbackPopupStatus() == true){
			manageOrderPage.acceptFeedbackPopup();
		}
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(manageLocationsPage.deleteBtn());
		
		String modalHeader = quickQuotePage.customOrderHeader(	);
		String modalBody = quickQuotePage.customOrderPopupBody();
		UseAssert.assertEquals(modalHeader, "Please, confirm this action");
		UseAssert.assertEquals(modalBody, "Are you sure you want to delete this location?");
		SeleniumFunction.click(quickQuotePage.clickPopupFooterButton(2));
		
		UseAssert.assertEquals(manageLocationsPage.isLocationExist(companyName), true);
		ScreenShot.takeScreenShot(driver, "LocationDeleted");
	}
}
