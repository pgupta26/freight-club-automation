package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import com.qualitesoft.freightclub.pageobjects.UpdateTrackingStatusPage;

public class TestTrackingStatus extends InitializeTest {

	@Test
	public void testTrackingStatus() {

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		OrderDetailPage orderDetailsPage = new OrderDetailPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		Xls_Reader xr = new Xls_Reader(testData);
		int i = Integer.parseInt(Row);

		SeleniumFunction.clickJS(driver, manageOrderpage.manageOrdersLink());
		quickQuote.acceptPopup();

		String orderid = xr.getCellData("Input", "OrderId", i).trim();

		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderid);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		WaitTool.sleep(10);

		SeleniumFunction.click(manageOrderpage.ViewDetail());
		ScreenShot.takeScreenShot(driver, "Tracking status order details");
		WaitTool.sleep(5);

		SeleniumFunction.getCurrentWindow(driver);
		WaitTool.sleep(3);
		quickQuote.acceptPopup();

		String[] trackingStatus = {"Scheduled for Pick-up", "Scheduled for Delivery", "Picked Up",
				"In Transit", "Delivered"};

		for (int trackingIndex = 0; trackingIndex < trackingStatus.length; trackingIndex++) {

			try {
				SeleniumFunction.refreshCurrentWindow(driver);
				SeleniumFunction.scrollDownUptoFooter(driver);
				SeleniumFunction.click(orderDetailsPage.adminTab());
				SeleniumFunction.scrollDownByPixel(driver, "150");
				SeleniumFunction.click(orderDetailsPage.editOrder());

				try {
					SeleniumFunction.getCurrentWindow(driver);
					WaitTool.sleep(3);

					UpdateTrackingStatusPage updateTrackingStatusPage =
							new UpdateTrackingStatusPage(driver);
					SeleniumFunction.scrollDownUptoFooter(driver);
					WaitTool.sleep(3);
					SeleniumFunction.selectByVisibleText(WaitTool.waitForElementPresentAndDisplay(
							driver, By.id("OrderStatusType"), 20), trackingStatus[trackingIndex]);
					SeleniumFunction.sendKeys(updateTrackingStatusPage.cityTextfield(),
							"Selenium_City");
					SeleniumFunction.sendKeys(updateTrackingStatusPage.zipTextfield(), "92010");
					SeleniumFunction.sendKeys(updateTrackingStatusPage.stateTextfield(), "CA");
					SeleniumFunction.clickAction(driver,
							updateTrackingStatusPage.dateOfUpdateTextfield());
					SeleniumFunction.sendKeysAction(driver,
							updateTrackingStatusPage.dateOfUpdateTextfield(), "01-01-2023");
					ScreenShot.takeScreenShot(driver, "UpdateTrackingStatusPageFilled");
					WaitTool.sleep(4);
					try {
						getDriver()
								.findElement(
										By.xpath("//button[contains(text(),'Update Tracking')]"))
								.click();
					} catch (Exception ex) {
						Log.info("ignore failure as the element is clicked.");
					}
					WaitTool.sleep(5);
					SeleniumFunction.closeWindow(driver);
					SeleniumFunction.getCurrentWindow(driver);
				} catch (Exception ex) {
					SeleniumFunction.closeWindow(driver);
					SeleniumFunction.getCurrentWindow(driver);
					throw ex;
				}
			} catch (Exception ex) {
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				throw ex;
			}
		}

		/* TrackShipmentPage */
		// WaitTool.sleep(2);
		// SeleniumFunction.click(manageOrderpage.trackingNoLink());
		// SeleniumFunction.getCurrentWindow(driver);
		// WaitTool.sleep(2);
		// ScreenShot.takeScreenShot(driver, "TrackShipmentPage");
		// SeleniumFunction.closeWindow(driver);
		// SeleniumFunction.getCurrentWindow(driver);
	}
}
