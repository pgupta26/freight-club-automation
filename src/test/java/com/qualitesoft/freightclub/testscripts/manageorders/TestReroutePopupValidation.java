package com.qualitesoft.freightclub.testscripts.manageorders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestReroutePopupValidation extends InitializeTest{
	CommonOps commonOps = new CommonOps();
	@Test
	public void verifyRerouteLink(){
		try{
		/*	Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Reroute Request", "OrderId", i);*/
			
			Xls_Reader xr1=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Input", "OrderId", i);
			
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			commonOps.openManageOrdersPageAndSearchOrder(orderId);

			if(manageOrderpage.acceptFeedbackPopupStatus() == true){
				manageOrderpage.acceptFeedbackPopup();
			}
			
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
	@Test
	public void verifyReroutePopupValidation(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			String popHeader = SeleniumFunction.getText(manageOrderpage.requestReroutePopup());
			Assert.assertEquals(popHeader, "Delivery Reroute Request");

			manageOrderpage.clickSendRequestBtn();
			List<String> actualList = new ArrayList<String>();
			List<String> expectedList = new ArrayList<String>(Arrays.asList("Location Type is required", 
					"Address Line 1 is required", "City is required","State is required","Zip Code is required"
					,"Country is required"));

			for(int i=0; i<6; i++){
				String validation = manageOrderpage.getFieldValidation(i+1);
				actualList.add(validation);
			}
			Assert.assertEquals(actualList, expectedList);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}
