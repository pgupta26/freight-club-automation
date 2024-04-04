package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestCancelOrderRequest extends InitializeTest{
	
	CommonOps commonOps = new CommonOps();
	@Test
	public void createCancelOrder(){
		try{			
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			ManageOverages manageOverages = new ManageOverages(driver);
			Xls_Reader xr=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
			int i=Integer.parseInt(Row);

			SeleniumFunction.click(manageOrderpage.manageOrdersLink());
			WaitTool.sleep(7);
			
			manageOrderpage.ActionButton();
			if(!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
				SeleniumFunction.click(manageOrderpage.ExpandMenupage());
			}
			
			if(manageOrderpage.acceptFeedbackPopupStatus() == true){
				manageOrderpage.acceptFeedbackPopup();
			}
			
			String orderId = xr.getCellData("BOL", "OrderId", i);
			SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderId);
			manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);;
			/*SeleniumFunction.KeyBoradEnter(driver);*/
			WaitTool.sleep(10);
			
			String orderStatus = SeleniumFunction.getText(manageOverages.gridData(1, 6));
			UseAssert.assertEquals(orderStatus, "Requested");
			
			WaitTool.sleep(5);
			SeleniumFunction.clickJS(driver, manageOrderpage.ActionButton());
			
			String shipmentType = xr.getCellData("BOL", "Order Status", i);
			if(shipmentType.contains("Confirm")){
				manageOrderpage.clickConfirmCancellationLink();
				changeCancellationRequestStatus("Are you sure you want to confirm the cancellation of the order "+xr.getCellData("BOL", "OrderId", i)+"?",
						"Order cancelled successfully! OrderId=" + orderId, "Confirmed");
			}else{
				manageOrderpage.clickWithdrawCancellationLink();
				changeCancellationRequestStatus("Are you sure you want to withdraw the cancellation for the order "+xr.getCellData("BOL", "OrderId", i)+"?", 
						"Success\nCancellation was successfully withdrawn.", "Withdrawn");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}	
	
	public static void changeCancellationRequestStatus(String ex_popupBody, String toastMess, String ex_status)
	{
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		ManageOverages manageOverages = new ManageOverages(driver);
		Xls_Reader xr=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageOrdersTestData.xlsx");
		int i=Integer.parseInt(Row);
		
		WaitTool.sleep(2);
		ScreenShot.takeScreenShot(driver, "Cancel order Popup");
		String cancelOrderPopupHeader = SeleniumFunction.getText(manageOrderpage.getCancelOrderPopupHeader());
		String cancelOrderPopupBody = SeleniumFunction.getText(manageOrderpage.getCancelOrderPopupBody());
		String orderId = xr.getCellData("BOL", "OrderId", i);
		
		UseAssert.assertEquals(cancelOrderPopupHeader, "Please, confirm this action");
		UseAssert.assertEquals(cancelOrderPopupBody, ex_popupBody);
		SeleniumFunction.click(manageOrderpage.confirmCancelOrderBtn());
		String successMessage = manageOrderpage.verifyToastMessage();
		UseAssert.assertEquals(successMessage, toastMess);
		WaitTool.sleep(5);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());
		WaitTool.sleep(7);
		
		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderId);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);;
		/*SeleniumFunction.KeyBoradEnter(driver);*/
		WaitTool.sleep(10);
		
		String updatedOrderStatus = SeleniumFunction.getText(manageOverages.gridData(1, 6));
		UseAssert.assertEquals(updatedOrderStatus, ex_status);
	}
}
