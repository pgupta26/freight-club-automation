package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestDeniedRerouteRequest extends InitializeTest{
	CommonOps commonOps = new CommonOps();
	@Test(priority = 1)
	public void deniedRequestByAdmin(){
		try{
			/*Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			String orderId = xr1.getCellData("Reroute Request", "OrderId", i);*/

			Xls_Reader xr1=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Input", "OrderId", i);
			
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

			if(manageOrderpage.acceptFeedbackPopupStatus() == true){
				manageOrderpage.acceptFeedbackPopup();
			}
			commonOps.openManageOrdersPageAndSearchOrder(orderId);
			SeleniumFunction.clickJS(driver, manageOrderpage.ActionButton());
			manageOrderpage.clickDeniedReroute();

			String rerouteDeniedPopup = SeleniumFunction.getText(manageOrderpage.deniedReroutePopup());
			UseAssert.assertEquals(rerouteDeniedPopup, "Reroute Denied");
			manageOrderpage.setRerouteDeniedReason("Incorrect address reroute denied reason");
			manageOrderpage.clickProceedDenialBtn();
			String successMessage = manageOrderpage.verifyToastMessage();
			UseAssert.assertEquals(successMessage, "Reroute successfully denied.");

		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	@Test(priority = 2)
	public void verifyRerouteRequestLinkIsPresent(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			WaitTool.sleep(3);
			SeleniumFunction.clickJS(driver, manageOrderpage.ActionButton());
			boolean status = manageOrderpage.requestRerouteIsPresent();
			Assert.assertEquals(status, true);			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}
