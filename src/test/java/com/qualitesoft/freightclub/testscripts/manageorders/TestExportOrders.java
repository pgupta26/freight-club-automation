package com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestExportOrders extends InitializeTest {

	@Test
	public void exportAllOrdersAndVerifyCount() throws IOException {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());
		WaitTool.sleep(7);

		if (manageOrderpage.acceptFeedbackPopupStatus() == true) {
			manageOrderpage.acceptFeedbackPopup();
		}

		if (keyword.equals("quickOrders") == true) {
			SeleniumFunction.click(manageOrderpage.openQuoteTab());
		} else if (keyword.equals("fullOrders") == true) {
			Log.info("Already in booked table");
		}

		String rowsCount = manageOrderpage.getPagiationTotalRows(keyword);
		String[] num = rowsCount.split(" ");
		String totalRecords = num[4];

		SeleniumFunction.click(manageOrderpage.clickExportOrdersBtn(keyword));


		if(Integer.parseInt(totalRecords) > 100 ) {

			String expectedMsg =
					"Export in progress Your requested export is in progress. We will send you an email when the export is available for download.";

			String actualMsg =
					WaitTool.waitForElementPresentAndDisplay(driver, By.id("toast-container"), 50)
							.getText();
			actualMsg = actualMsg.replaceAll("[\\t\\n\\r]+", " ");
			Assert.assertEquals(actualMsg, expectedMsg);

		} else {
		
			WaitTool.sleep(5);
			String sheetName = JavaFunction.currentPSTDate("yyyy-M-d");
			int numOfRows = JavaFunction.countLineBufferedReader(System.getProperty("user.dir") +File.separator+ "download" +File.separator+ testData +  sheetName + ".csv");

			Log.info("Pagination count " + totalRecords + " \n Excel rows " + numOfRows);
			Assert.assertTrue(numOfRows >= Integer.parseInt(totalRecords));
		}
		
		// WaitTool.waitForElementBoolean(driver, By.xpath("//div[text()='Export Ready for
		// Download']"), 50);

		// SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
		// By.xpath("//div[@id = 'toast-container']//a"), 10));

		// String fileName = "QuickOrders-" + JavaFunction.currentPSTDate("yyyy-M-d") + ".csv";
		// int numOfRows = JavaFunction.countLineBufferedReader("download\\" + fileName);

		// Log.info("Pagination count " + totalRecords + " \n Excel rows " + numOfRows);
		// Assert.assertTrue(numOfRows >= Integer.parseInt(totalRecords));
	}
}
