package com.qualitesoft.freightclub.testscripts.manageinvoices;

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
import com.qualitesoft.freightclub.pageobjects.ManageInvoices;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestImportSecondaries extends InitializeTest {
	
	@Test
	public void testImportSecondaries() {
		ManageInvoices  manageInvoices = new ManageInvoices(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		//Read data from sheet for selected row
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		//Click on manage invoices
		SeleniumFunction.click(manageInvoices.manageInvoices());
		
		//Accept popup
		WaitTool.sleep(10);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);

		//Click on import secondary
		SeleniumFunction.scrollUpByPixel(driver, "2000");
		WaitTool.sleep(5);
		SeleniumFunction.click(manageInvoices.importSecondaries());
		ScreenShot.takeScreenShot(driver, "File upload dialog");

		//Upload overage file
		SeleniumFunction.uploadFile("Overages\\SecondaryInvoiceTemplate.xlsx");

		//Click on upload files
		SeleniumFunction.click(manageInvoices.uploadFiles());

		//Verify upload success message
		UseAssert.assertEquals(manageInvoices.successMessage().getText(), "1 invoices were successfully uploaded");
		ScreenShot.takeScreenShot(driver, "File upload results dialog");

		//Click on ok button under upload invoice results dialog
		SeleniumFunction.click(manageInvoices.ok());

		//Verify done  button after file uploaded successfully
		UseAssert.assertEquals(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[@class='k-icon k-i-tick']")), true);		

		//Filter grid data by total billed
		String totalBilledAmount = xr.getCellData("Sec invoice Master","New Invoice Amount", i).replace("$", "");
		Log.info("totalBilledAmount: "+totalBilledAmount);
		WaitTool.sleep(5);
		SeleniumFunction.sendKeys(manageInvoices.OrderIDTextBox(), xr.getCellData("Sec invoice Master","FC Order ID", i));
		SeleniumFunction.sendKeys(manageInvoices.TotalBilledTextBox(4), totalBilledAmount);
		manageInvoices.TotalBilledTextBox(4).sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
		ScreenShot.takeScreenShot(driver, "grid filter");
		
		//Verify overage id detail in data grid
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		int rowIndex = 1;
		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 1).getText(), xr.getCellData("Sec invoice Master","FC Order ID", i).trim());

		//UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 4).getText(), xr.getCellData("Sec invoice Master","SECONDARY INV #", i).trim());

		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 2).getText(), xr1.getCellData("Sheet1","Customer PO #", i).trim());

		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 3).getText().trim(), xr1.getCellData("Sheet1","OrderDate", i).trim().replace("-", "/"));

		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 5).getText(), xr1.getCellData("Sheet1","Invoice Type", i).trim());

		//UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 6).getText(), xr1.getCellData("Sheet1","OrderDate", i).trim().replace("-", "/"));
		
		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 7).getText(), xr1.getCellData("Sheet1","Invoice Status", i).trim());

		UseAssert.assertEquals(manageInvoices.gridData(rowIndex, 8).getText().trim(), xr.getCellData("Sec invoice Master","New Invoice Amount", i).trim());
		
		//page refresh to handle popup
		InitializeTest.getDriver().navigate().refresh();
	}
}
