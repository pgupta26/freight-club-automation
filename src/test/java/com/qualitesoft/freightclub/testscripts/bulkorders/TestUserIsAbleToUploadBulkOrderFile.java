package com.qualitesoft.freightclub.testscripts.bulkorders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.BulkOrdersPage;
import com.qualitesoft.freightclub.pageobjects.ManageInvoices;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;


public class TestUserIsAbleToUploadBulkOrderFile extends InitializeTest {
	
	@Test
	public void testUserIsAbleToUploadBulkOrderFile() {
		BulkOrdersPage bulkOrdersPage = new BulkOrdersPage(driver);
		ManageInvoices manageinvoices = new ManageInvoices(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String fileName = xr.getCellData("Sheet1", "FileName", i);
		String paymentMethod = xr.getCellData("Sheet1", "PaymentMethod", i);
		String initialOrderStatus = xr.getCellData("Sheet1", "InitialOrderStatus", i);
		String automaticBook = xr.getCellData("Sheet1", "AutomaticBook", i);
		
		//update customer PO number in bulk data sheet before upload
		this.updateCustomerPONumberInBulkUploadFile(fileName);

		//click on bulk orders link
		SeleniumFunction.click(bulkOrdersPage.bulkOrdersLink());
		
		//accept popup
		quickQuote.acceptPopup();
		
		//click on select file
		SeleniumFunction.click(bulkOrdersPage.selectFilesButton());

		//upload file
		SeleniumFunction.uploadFile("BulkOrders\\"+fileName);

		//click automatic book button if payment method is on account
		if(paymentMethod.equals("Credit Card")) {
			Assert.assertFalse(bulkOrdersPage.isAutomationBookButtonPresent());
		}

		if(automaticBook.equals("Yes")) {
			SeleniumFunction.click(bulkOrdersPage.automaticBookButton());
		}

		//click on upload button
		SeleniumFunction.click(bulkOrdersPage.uploadButton());
		
		if(automaticBook.equals("Yes")) {
			SeleniumFunction.click(bulkOrdersPage.quoteAndBookButton());
		}
		
		WaitTool.sleep(20);
		ScreenShot.takeScreenShot(driver, "Bulk Orders File");
		WaitTool.sleep(5);	
		
		//verify grid headers
		ArrayList<String> expectedGridHeaders = new ArrayList<String>();
		Collections.addAll(expectedGridHeaders, "Order File Name","Order Date","Bulk Order Status",
				"Successfully Booked","Not Yet Booked","Unable to Quote", "Download Labels","Download BOLs");
		
		ArrayList<String> actualGridHeader = new ArrayList<String> (); 
		List<WebElement> gridHeaders = manageinvoices.gridHeaders(); 
		for(WebElement header: gridHeaders) {
			Log.info(header.getText());
			actualGridHeader.add(header.getText());
		}
		
		Collections.sort(actualGridHeader);
		Collections.sort(expectedGridHeaders);
		Assert.assertEquals(actualGridHeader, expectedGridHeaders);
		
		//verify uploaded data
		UseAssert.assertEquals(manageinvoices.gridData(1, -1).getText(), fileName);
		UseAssert.assertEquals(manageinvoices.gridData(1, 0).getText(), JavaFunction.currentDate());
		//UseAssert.assertEquals(manageinvoices.gridData(1, 1).getText(), initialOrderStatus);
		UseAssert.assertEquals(manageinvoices.gridData(1, 2).getText(), "-");
		UseAssert.assertEquals(manageinvoices.gridData(1, 3).getText(), "-");
		UseAssert.assertEquals(manageinvoices.gridData(1, 4).getText(), "-");
		Assert.assertTrue(bulkOrdersPage.downloadLabelsButton(1).getAttribute("class").contains("disabled"));
		Assert.assertTrue(bulkOrdersPage.downloadBOLButton(1).getAttribute("class").contains("disabled"));
		
	}


	private void updateCustomerPONumberInBulkUploadFile(String fileName) {

		Xls_Reader bulkOrderUpload = new Xls_Reader("testdata/FCfiles/qa/BulkOrders/" +fileName);

		for(int rowIndex = 2; rowIndex <= 11; rowIndex++) {
			if(rowIndex <= 6) {

				String customerPONumber = "LTL-OID" +JavaFunction.getRandomNumber(100, 999);

				bulkOrderUpload.setCellData("Delivery Information", "Customer PO Number *", rowIndex, customerPONumber);
				bulkOrderUpload.setCellData("Shipment Details", "Customer PO Number *", rowIndex, customerPONumber);

			} else {

				String customerPONumber = "Parcel-OID" +JavaFunction.getRandomNumber(100, 999);

				bulkOrderUpload.setCellData("Delivery Information", "Customer PO Number *", rowIndex, customerPONumber);
				bulkOrderUpload.setCellData("Shipment Details", "Customer PO Number *", rowIndex, customerPONumber);

			}
		}
	}
}
