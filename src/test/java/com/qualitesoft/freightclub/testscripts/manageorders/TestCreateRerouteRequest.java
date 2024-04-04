package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestCreateRerouteRequest extends InitializeTest{
	CommonOps commonOps = new CommonOps();
	@Test(priority = 1)
	public void verifyRerouteLink(){
		try{
			/*Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			String orderId = xr1.getCellData("Reroute Request", "OrderId", i);*/
			Xls_Reader xr1=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Input", "OrderId", i);
			
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			SeleniumFunction.click(manageOrderpage.manageOrdersLink());
			WaitTool.sleep(7);
			
			commonOps.openManageOrdersPageAndSearchOrder(orderId);

			SeleniumFunction.clickJS(driver, manageOrderpage.ActionButton());
			boolean status = manageOrderpage.requestRerouteIsPresent();
			Assert.assertEquals(status, true);

			manageOrderpage.clickRequestRerouteLink();
			WaitTool.sleep(2);
			ScreenShot.takeScreenShot(driver, "Reroute Request Popup");
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}
	
	@Test(priority = 2, dependsOnMethods = {"verifyRerouteLink"})
	public void fillReroutePopupFields(){
		try{
			fillRerouteRequestPopup();
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifyRerouteLink", "fillReroutePopupFields"})
	public void verifyRerouteRequestLinkNotPresent(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			SeleniumFunction.clickJS(driver, manageOrderpage.ActionButton());
			boolean status = manageOrderpage.requestRerouteIsPresent();
			Assert.assertEquals(status, false);			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public static void fillRerouteRequestPopup(){
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
		int i=Integer.parseInt(Row);

		String getLocationType = xr1.getCellData("Reroute Request", "Location Type", i);
		String locationName = xr1.getCellData("Reroute Request", "Location Name", i);
		String address = xr1.getCellData("Reroute Request", "Address", i);
		String city = xr1.getCellData("Reroute Request", "City", i);
		String state = xr1.getCellData("Reroute Request", "State", i);
		String zipcode = xr1.getCellData("Reroute Request", "Zip Code", i);
		String country = xr1.getCellData("Reroute Request", "Country", i);

		SeleniumFunction.selectByVisibleText(manageOrderpage.locationType("Location Type"), getLocationType);
		WaitTool.sleep(2);
		manageOrderpage.fillRerouteRequestForm("Location Name", locationName);
		manageOrderpage.fillRerouteRequestForm("Address", address);
		manageOrderpage.fillRerouteRequestForm("City", city);
		manageOrderpage.fillRerouteRequestForm("State", state);
		manageOrderpage.fillRerouteRequestForm("Zip Code", zipcode);
		manageOrderpage.fillRerouteRequestForm("Country", country);
		
		if(getLocationType.contains("Commercial")){
			String getEarliestDropoff  = xr1.getCellData("Reroute Request", "Earliest Drop-Off", i);
			String getLatestDropoff = xr1.getCellData("Reroute Request", "Latest Drop-Off", i);
			
			Log.info(getEarliestDropoff + " >> " + getLatestDropoff);
			SeleniumFunction.selectByVisibleText(manageOrderpage.locationType("Earliest Drop-Off"), "12:00 PM");
			SeleniumFunction.selectByVisibleText(manageOrderpage.locationType("Latest Drop-Off"), "04:00 PM");
		}
		
		if(manageOrderpage.checkboxStatus("Delivery Appointment Required").isSelected() == false){
			SeleniumFunction.click(manageOrderpage.checkboxStatus("Delivery Appointment Required"));
		}
		
		SeleniumFunction.click(manageOrderpage.checkboxStatus("Limited Access"));
		manageOrderpage.clickSendRequestBtn();
		String successMessage = manageOrderpage.verifyToastMessage();
		UseAssert.assertEquals(successMessage, "Reroute successfully requested.");
	}
}
