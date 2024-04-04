package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestReroutePopupFields extends InitializeTest{

	@Test
	public void validateFieldsForLocationType(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			validateRerouteRequestPopupFields("Residential", true, false, false, false);
			validateRerouteRequestPopupFields("Commercial", false, false, true, true);
			validateRerouteRequestPopupFields("Amazon FBA Warehouse", false, false, false, false);
			manageOrderpage.clickClosePopupButton();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	

	public static void validateRerouteRequestPopupFields(String locationType, boolean value1,boolean value2,boolean value3, boolean value4){
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		
		SeleniumFunction.selectByVisibleText(manageOrderpage.locationType("Location Type"), locationType);
		WaitTool.sleep(4);
		UseAssert.assertEquals(manageOrderpage.dropOffDropdownStatus("Earliest Drop-Off"), value3);
		UseAssert.assertEquals(manageOrderpage.dropOffDropdownStatus("Latest Drop-Off"), value4);
		UseAssert.assertEquals(manageOrderpage.checkboxStatus("Delivery Appointment Required").isSelected(), value1);
		UseAssert.assertEquals(manageOrderpage.checkboxStatus("Limited Access").isSelected(), value2);
	}
}
