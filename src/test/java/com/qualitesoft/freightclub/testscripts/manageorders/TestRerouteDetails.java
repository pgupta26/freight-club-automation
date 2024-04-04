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
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;

public class TestRerouteDetails extends InitializeTest{
	CommonOps commonOps = new CommonOps();
	@Test
	public void verifyRerouteLocationOnDetailPage(){
		try{
			/*Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			String orderId = xr1.getCellData("Reroute Request", "OrderId", i);*/
			Xls_Reader xr1=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Input", "OrderId", i);

			commonOps.openManageOrdersPageAndSearchOrder(orderId);
			
			verifyUpdatedDetailsOnDetailPage();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public static void verifyUpdatedDetailsOnDetailPage(){
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		OrderDetailPage orderDetailPage =new OrderDetailPage(driver);

		SeleniumFunction.click(manageOrderpage.ViewDetail());
		try{
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);

			Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			String getLocationType = xr1.getCellData("Reroute Request", "Location Type", i);
			String locationName = xr1.getCellData("Reroute Request", "Location Name", i);
			String address = xr1.getCellData("Reroute Request", "Address", i);
			String city =  xr1.getCellData("Reroute Request", "City", i);
			String state =  xr1.getCellData("Reroute Request", "State", i);
			String zipCode =  xr1.getCellData("Reroute Request", "Zip Code", i);

			if(getLocationType.contains("Commercial")){
				UseAssert.assertEquals(orderDetailPage.verifyRerouteLocationOnDetailPage(), 
						"Reroute Location\n" + locationName + " ("+ getLocationType +")\n" + address 
						+ "\n" + city + ", " + state + " " + zipCode + 
						"\nExtra Services\nLimited Access\nDelivery Appointment Required\nDrop Off Time\n12:00 PM - 04:00 PM");
			}
			else{
				UseAssert.assertEquals(orderDetailPage.verifyRerouteLocationOnDetailPage(), 
						"Reroute Location\n" + locationName + " ("+ getLocationType +")\n" + address + 
						"\n" + city + ", " + state + " " + zipCode +
						"\nExtra Services\nLimited Access\nDelivery Appointment Required");
			}
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception ex){
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.assertTrue(false);
		}
	}

}
