package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import com.qualitesoft.freightclub.pageobjects.UpdateTrackingStatusPage;

import models.UpdateOrderInformation;

public class TestVerifyUserIsAbleToUpdateOrderInformation extends InitializeTest {

	@Test
	public void testVerifyUserIsAbleToUpdateOrderInformation() {

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		OrderDetailPage	orderDetailsPage= new OrderDetailPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		SeleniumFunction.clickJS(driver, manageOrderpage.manageOrdersLink());
		quickQuote.acceptPopup();

		String orderid=xr.getCellData("Input","OrderId", i).trim();
		
		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderid);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		WaitTool.sleep(10);

		SeleniumFunction.click(manageOrderpage.ViewDetail());
		WaitTool.sleep(5);

		try {
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);
			quickQuote.acceptPopup();
			quickQuote.acceptPopup();
			
			SeleniumFunction.click(orderDetailsPage.adminTab());
			SeleniumFunction.scrollDownByPixel(driver, "150");

			SeleniumFunction.click(orderDetailsPage.editOrder());
			try {
				
				SeleniumFunction.getCurrentWindow(driver);
				WaitTool.sleep(3);

				UpdateTrackingStatusPage updateTrackingStatusPage = new UpdateTrackingStatusPage(driver);
				
				//update order information
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getField("Customer PO Number:"), UpdateOrderInformation.customerPONumber);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getField("Quoted Price"), UpdateOrderInformation.quotedPrice);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getField("Address Line 1"), UpdateOrderInformation.pickUpAddressLine1);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getField("Address Line 2"), UpdateOrderInformation.pickUpAddressLine2);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getField("Zip/Postal Code"), UpdateOrderInformation.pickUpZipPostalCode);
				
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getDropOffFields("Address Line 1"), UpdateOrderInformation.dropOffAddressLine1);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getDropOffFields("Address Line 2"), UpdateOrderInformation.dropOffAddressLine2);
				SeleniumFunction.clearAndSendKeys(updateTrackingStatusPage.getDropOffFields("Zip/Postal Code"), UpdateOrderInformation.dropOffZipPostalCode);
				updateTrackingStatusPage.getDropOffFields("Zip/Postal Code").sendKeys(Keys.TAB);
				WaitTool.sleep(5);
				SeleniumFunction.clickJS(driver, updateTrackingStatusPage.saveOrder());
				
				//close window
				WaitTool.sleep(15);
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				
				//refresh order details page
				SeleniumFunction.refreshCurrentWindow(driver);
				
				//validation updated order information
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer PO #:", 1).getText(), UpdateOrderInformation.customerPONumber);
				UseAssert.assertEquals(orderDetailsPage.getLabel("Quoted Amount:", 1).getText(), "$"+UpdateOrderInformation.quotedPrice);
				System.out.println(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", ""));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpAddressLine1));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpAddressLine2));
				Assert.assertTrue(orderDetailsPage.pickUpAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.pickUpZipPostalCode));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffAddressLine1));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffAddressLine2));
				Assert.assertTrue(orderDetailsPage.dropOffAddress().getText().replaceAll("[\n,\\s]+$", "").contains(UpdateOrderInformation.dropOffZipPostalCode));

				
			}catch(Exception ex) {
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				throw ex;
			}
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw ex;
		}
		
		//refresh order details page
		SeleniumFunction.refreshCurrentWindow(driver);
				
		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderid);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
		
		UseAssert.assertEquals(manageOrderpage.getColumnData("3").trim(), UpdateOrderInformation.customerPONumber);
		UseAssert.assertEquals(manageOrderpage.getColumnData("11").trim(), "$"+UpdateOrderInformation.quotedPrice);
		
	}

}
