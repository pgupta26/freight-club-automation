package com.qualitesoft.freightclub.testscripts.bulkorders;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.BulkOrdersPage;
import com.qualitesoft.freightclub.pageobjects.ManageInvoices;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestUserIsAbleToBookOrder extends InitializeTest {
	
	@Test
	public void testUserIsAbleToBookOrder() {
		
		Xls_Reader xr=new Xls_Reader(testData);	
		int i=Integer.parseInt(Row);
		String fileName = xr.getCellData("Sheet1", "FileName", i);
		String finalOrderStatus = xr.getCellData("Sheet1", "FinalOrderStatus", i);
		
		ManageInvoices manageinvoices = new ManageInvoices(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		BulkOrdersPage bulkOrdersPage = new BulkOrdersPage(driver);
		
		//click on bulk orders link
		SeleniumFunction.click(bulkOrdersPage.bulkOrdersLink());

		//accept popup
		quickQuote.acceptPopup();
		
		//filter record by order file name
		SeleniumFunction.scrollDownByPixel(driver, "1000");
		SeleniumFunction.sendKeys(manageOrderpage.orderIdFilter(), fileName);
		manageOrderpage.orderIdFilter().sendKeys(Keys.ENTER);
		ScreenShot.takeScreenShot(driver, "filter order file name");
		WaitTool.sleep(5);
		
		//verify bulk order status
		UseAssert.assertEquals(manageinvoices.gridData(1, 1).getText(), finalOrderStatus);

		//click on not yet booked link
		SeleniumFunction.click(manageinvoices.gridData(1, 3).findElement(By.xpath("./div/a")));
		
		try {
			//switch to new window
			SeleniumFunction.getCurrentWindow(driver);
			
			//search order id
			if(!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
				SeleniumFunction.click(manageOrderpage.ExpandMenupage());
			}
			
			//get customer PO number to book order based on search user
			String customerPONumber;

			Xls_Reader bulkOrderUpload = new Xls_Reader("testdata/FCfiles/qa/BulkOrders/" +fileName);

			if(searchUser.contains("LTL")) {

				customerPONumber = bulkOrderUpload.getCellData("Delivery Information", "Customer PO Number *", 2);
			} else {

				customerPONumber = bulkOrderUpload.getCellData("Delivery Information", "Customer PO Number *", 8);
			}
			Log.info("Customer PO Number to search" +customerPONumber);


			//filter open quotes grid by customer PO number
			SeleniumFunction.sendKeys(manageOrderpage.customerPOGridFilter(), customerPONumber);
			manageOrderpage.customerPOGridFilter().sendKeys(Keys.ENTER);
			ScreenShot.takeScreenShot(driver, "filter customer PO number");
			WaitTool.sleep(10);
			
			//click on book button
			SeleniumFunction.click(manageOrderpage.actionButton("quickOrders"));
			manageOrderpage.clickBookButton();
			ScreenShot.takeFullScreenShot("Shipment information details");

			//Review and Book Tab
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.click(quickQuote.ReviewOrder());
			WaitTool.sleep(10);
			quickQuote.acceptPopup();
			if(quickQuote.isReRatePresent()) {
				SeleniumFunction.click(quickQuote.reRate());
				SeleniumFunction.click(quickQuote.NextButton());
				SeleniumFunction.click(quickQuote.ReviewOrder());
			}
			
			WaitTool.sleep(10);
			SeleniumFunction.clickJS(driver, quickQuote.Book());
			WaitTool.sleep(10);
			
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
			ScreenShot.takeScreenShot(driver, "order booked");

			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(5);
			
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw ex;
		}
		
		//click on refresh view
		SeleniumFunction.click(bulkOrdersPage.refreshView());
		ScreenShot.takeScreenShot(driver, "Successfully booked count after refresh view");
	}
}
