package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;

public class TestRerouteWarningMessage extends InitializeTest{
	CommonOps commonOps = new CommonOps();
	@Test
	public void verifyRerouteRequestOnDetailPage(){
		try{
			/*Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			String orderId = xr1.getCellData("Reroute Request", "OrderId", i);*/
			
			Xls_Reader xr1=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);
			
			String orderId = xr1.getCellData("Input", "OrderId", i);

			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			OrderDetailPage orderDetailPage =new OrderDetailPage(driver);

			commonOps.openManageOrdersPageAndSearchOrder(orderId);

			SeleniumFunction.click(manageOrderpage.ViewDetail());
			try{
				SeleniumFunction.getCurrentWindow(driver);	
				WaitTool.sleep(3);

				if(enableOption.contains("No")){
					boolean warningMessage = orderDetailPage.verifyRerouteWarningMessageStatus();
					UseAssert.assertEquals(warningMessage, false);
				} else{
					String warningMessage = orderDetailPage.verifyRerouteWarningMessage();
					UseAssert.assertEquals(warningMessage, "Order Pending Reroute. Reroute was requested on " + JavaFunction.currentDateUSFormat());
				}

				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
			}catch(Exception ex){
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				ex.printStackTrace();
				throw ex;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}

