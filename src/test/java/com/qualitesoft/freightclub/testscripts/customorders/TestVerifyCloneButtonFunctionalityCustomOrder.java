package com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import com.qualitesoft.freightclub.pageobjects.ShipmentReviewOrderPage;

public class TestVerifyCloneButtonFunctionalityCustomOrder extends InitializeTest {
	
	@Test
	public void testVerifyCloneButtonFunctionalityCustomOrder() {
		
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		SeleniumFunction.click(manageOrderpage.ActionButton());
		Assert.assertTrue(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[text()='Clone']")));
		ScreenShot.takeScreenShot(driver, "Clone button presence");

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +Row);
		String orderReferenceID=xr.getCellData("Input","orderReferenceID", i).trim();

		orderReferenceID = "Clone_" + orderReferenceID;
		Log.info("Cloned Customer PO number: "+orderReferenceID);
		SeleniumFunction.click(manageOrderpage.clone());
		SeleniumFunction.sendKeys(manageOrderpage.customerPONumber(), orderReferenceID);
		SeleniumFunction.click(manageOrderpage.cloneNow());


		SeleniumFunction.executeJS(driver, "window.scrollBy(0, 2000)");
		ShipmentReviewOrderPage reviewOrder = new ShipmentReviewOrderPage(driver);
		WaitTool.sleep(2);
		SeleniumFunction.click(reviewOrder.book1());
		WaitTool.sleep(20);
		
		if(quickQuote.acknowleadgeBtnStatus() == true){
			String actualPopupHeader = SeleniumFunction.getText(quickQuote.acknowleadgeModalHeader());
			String actualPopupBody = SeleniumFunction.getText(quickQuote.acknowleadgeModalBody());
			UseAssert.assertEquals(actualPopupHeader, "A matching order has already been placed.");
			UseAssert.assertEquals(actualPopupBody, "We have identified that the order that you are about to place is similar to an order that has already been booked. Would you like to continue with placing a duplicate order?");
			SeleniumFunction.click(quickQuote.acknowleadgeBtn());
			SeleniumFunction.clickJS(driver, quickQuote.Book());
		}
		

		SeleniumFunction.click(quickQuote.Okbutton1());
		WaitTool.sleep(20);

		Assert.assertEquals(manageOrderpage.customerPO().getText(), orderReferenceID);
		
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(1);
		crorderId=SeleniumFunction.getText(manageOrderpage.orderIDGridValueFirstRow());
		Log.info("crorderId:" + crorderId.trim());
		
		//set order id in excel
		xr.setCellData("Input","CloneOrderId", i,crorderId.trim());
		WaitTool.sleep(5);
	}
}
