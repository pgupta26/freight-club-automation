package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestCloneOrders extends InitializeTest {

	@Test
	public void testCloneOrders() {

		ManageOverages manageOverage = new ManageOverages(driver);
		ManagerOrderPage manageOrderPage = new ManagerOrderPage(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr = new Xls_Reader(testData);
		int i = Integer.parseInt(Row);
		Log.info("Data row number: " + i);

		String orderReferenceID = xr.getCellData("Input", "orderReferenceID", i).trim();
		String orderId = xr.getCellData("Input", "OrderId", i).trim();
		String shipmentType = xr.getCellData("Input", "shipmentType", i).trim();
		String packageType = xr.getCellData("Input", "packageType", i).trim();
		String packageType2 = xr.getCellData("Input", "packageType2", i).trim();
		String palletType1 = xr.getCellData("Input", "PalletType1", i).trim();
		String carrier = xr.getCellData("Input", "Carrier", i).trim();

		// Navigate to manage orders page and search order id
		commonOps.openManageOrdersPageAndSearchOrder(orderId);

		// click on clone button under actions dropdown
		SeleniumFunction.click(manageOrderPage.ActionButton());
		SeleniumFunction.click(manageOrderPage.clone());

		// click on clone now button
		WaitTool.sleep(5);
		String CloneOrder = "CloneOrder" + Row;
		Log.info("Clone order customer PO: " + CloneOrder);
		SeleniumFunction.sendKeys(manageOrderPage.customerPONumber(), orderReferenceID);
		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderPage.cloneNow());
		ScreenShot.takeScreenShot(driver, "Order cloned");

		// enter order date and select carriers
		WaitTool.sleep(5);
		SeleniumFunction.scrollUpByPixel(driver, "2000");
		SeleniumFunction.click(quickQuote.OrderDate());
		quickQuote.acceptPopup();
		SeleniumFunction.scrollDownUptoFooter(driver);
		commonOps.selectCarrier();
		quickQuote.selectCarrier(carrier);

		// add pallet information
		if (palletType1.equalsIgnoreCase("Generic Pallet")) {
			SeleniumFunction.click(quickQuote.genericPallet());
		}

		// click on review order
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver, quickQuote.ReviewOrder());
		quickQuote.acceptPopup();
		WaitTool.sleep(10);
		// if(orderReferenceID.length() <=25) {
		// Assert.assertFalse(quickQuote.getCustomerPOValidationMsg().isDisplayed());
		// UseAssert.assertEquals(quickQuote.customerPONumber().getText(), orderReferenceID);
		// } else {
		// if(carrier.equals("FragilePAK")) {
		// String expectedCustomerPOValidationMsg = "The carrier has identified that the length of
		// this information is too long for them to input into their system. While we can shorten it
		// to fit, we�d like your input as this information will end up on the carrier label and
		// Bill of Lading.";
		// String actualCustomerPOValidationMsg =
		// quickQuote.getCustomerPOValidationMsg().getText().trim();
		// Log.info("Customer PO Validation Message: "+actualCustomerPOValidationMsg);
		// UseAssert.assertEquals(expectedCustomerPOValidationMsg, expectedCustomerPOValidationMsg);
		// SeleniumFunction.click(quickQuote.acceptCustomerPOValidationPopup());
		// UseAssert.assertEquals(quickQuote.customerPONumber().getText(),
		// orderReferenceID.substring(0, 25));
		// } else {
		// Assert.assertFalse(quickQuote.getCustomerPOValidationMsg().isDisplayed());
		// UseAssert.assertEquals(quickQuote.customerPONumber().getText(),
		// orderReferenceID.substring(0, 50));
		// }
		// }

		// verify pallet details
		if (!packageType.isEmpty()) {
			if (packageType.equals("Non-Palletized")) {
				commonOps.verifyPalletizedDetail(xr, i, "4", packageType);
			} else if (packageType.equals("Cardboard Box")
					|| packageType.equals("Bagged or Unboxed Product")) {
				commonOps.verifyPalletizedDetail(xr, i, "4", packageType);
			} else if (shipmentType.equals("Parcel")) {
				commonOps.verifyPalletizedDetail(xr, i, "5", packageType);
			} else {
				commonOps.verifyPalletizedDetail(xr, i, "3", packageType);
			}
		}
		if (!packageType2.isEmpty()) {
			if (shipmentType.equals("Parcel") || packageType2.equals("Non-Palletized")) {
				commonOps.verifyPalletizedDetail(xr, i, "4", packageType2);
			} else {
				commonOps.verifyPalletizedDetail(xr, i, "3", packageType2);
			}
		}
		ScreenShot.takeScreenShot(driver, "Clone order palletized detail");

		// click on book order
		SeleniumFunction.clickJS(driver, quickQuote.Book());

		if (quickQuote.acknowleadgeBtnStatus() == true) {
			String actualPopupHeader =
					SeleniumFunction.getText(quickQuote.acknowleadgeModalHeader());
			String actualPopupBody = SeleniumFunction.getText(quickQuote.acknowleadgeModalBody());
			UseAssert.assertEquals(actualPopupHeader, "A matching order has already been placed.");
			UseAssert.assertEquals(actualPopupBody,
					"We have identified that the order that you are about to place is similar to an order that has already been booked. Would you like to continue with placing a duplicate order?");
			SeleniumFunction.click(quickQuote.acknowleadgeBtn());
			WaitTool.sleep(2);
			SeleniumFunction.clickJS(driver, quickQuote.Book());
		}


		// click on ok button
		SeleniumFunction.click(quickQuote.Okbutton1());
		WaitTool.sleep(10);
		ScreenShot.takeScreenShot(driver, "Cloned order successfully booked");
		crorderId = SeleniumFunction.getText(manageOverage.gridData(1, 1));
		Log.info("Clone Order Id: " + crorderId);

		// set order id in excel
		xr.setCellData("Input", "CloneOrderId", i, crorderId.trim());
	}
}
