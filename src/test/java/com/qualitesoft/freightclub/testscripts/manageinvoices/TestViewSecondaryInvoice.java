package com.qualitesoft.freightclub.testscripts.manageinvoices;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageInvoices;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestViewSecondaryInvoice extends InitializeTest {

	@Test
	public void testViewSecondaryInvoice() {

		// Read data from sheet for selected row
		Xls_Reader xr = new Xls_Reader(testData);
		int i = Integer.parseInt(Row);

		ManageInvoices manageInvoices = new ManageInvoices(driver);
		QuickQuote quickQuote = new QuickQuote(driver);

		// Click on manage invoices
		SeleniumFunction.click(manageInvoices.manageInvoices());

		// Accept popup
		WaitTool.sleep(10);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);

		// if (userType.equals("User")) {
		// 	// Filter data grid by total billed
		// 	SeleniumFunction.sendKeys(manageInvoices.TotalBilledTextBox(4),
		// 			xr.getCellData("Sec invoice Master", "New Invoice Amount", i).replace("$", ""));
		// 	manageInvoices.TotalBilledTextBox(4).sendKeys(Keys.ENTER);
		// } else {
		// 	// Filter grid data by total billed
		// 	SeleniumFunction.sendKeys(manageInvoices.OrderIDTextBox(),
		// 			xr.getCellData("Sec invoice Master", "FC Order ID", i));
		// 	SeleniumFunction.sendKeys(manageInvoices.TotalBilledTextBox(4),
		// 			xr.getCellData("Sec invoice Master", "New Invoice Amount", i).replace("$", ""));
		// 	manageInvoices.TotalBilledTextBox(4).sendKeys(Keys.ENTER);
		// }

		// Filter grid data by total billed
		SeleniumFunction.sendKeys(manageInvoices.OrderIDTextBox(),
				xr.getCellData("Sec invoice Master", "FC Order ID", i));
		SeleniumFunction.sendKeys(manageInvoices.TotalBilledTextBox(4),
				xr.getCellData("Sec invoice Master", "New Invoice Amount", i).replace("$", ""));
		manageInvoices.TotalBilledTextBox(4).sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
		ScreenShot.takeScreenShot(driver, "grid filter");

		// Click detail
		SeleniumFunction.click(manageInvoices.detail(1));

		// Verify invoice detail
		UseAssert.assertEquals(manageInvoices.getLabel("Order ID").getText(),
				xr.getCellData("Sec invoice Master", "FC Order ID", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Invoice Created Date").getText(),
				xr.getCellData("Sec invoice Master", "Date CREATED (MM/DD/YYYY)", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Tracking Number").getText(),
				xr.getCellData("Sec invoice Master", "Tracking #", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Carrier").getText(),
				xr.getCellData("Sec invoice Master", "Carrier", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Quoted Amount").getText(),
				xr.getCellData("Sec invoice Master", "New Invoice Amount", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Actual Amount").getText(),
				xr.getCellData("Sec invoice Master", "New Invoice Amount", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Secondary Invoice Number").getText(),
				xr.getCellData("Sec invoice Master", "SECONDARY INV #", i));
		UseAssert.assertEquals(manageInvoices.getLabel("Invoice Reason").getText(),
				xr.getCellData("Sec invoice Master", "Invoice Reason", i));

		Xls_Reader xr1 =
				new Xls_Reader("testdata/FCfiles/" + env + "/Overages/ManageInvoiceTestData.xlsx");
		UseAssert.assertEquals(manageInvoices.getLabel("Invoice Type").getText(),
				xr1.getCellData("Sheet1", "Invoice Type", i).trim());
		UseAssert.assertEquals(manageInvoices.getLabel("Company Name").getText(),
				xr1.getCellData("Sheet1", "Company", i).trim());
		// UseAssert.assertEquals(manageInvoices.getLabel("Order Status").getText(), "Booked");

		if (userType.equals("Admin")) {

			// Fields that will visible to admin only
			Select invoiceStatusDrpdown = new Select(manageInvoices.getSelect("Invoice Status"));
			UseAssert.assertEquals(invoiceStatusDrpdown.getFirstSelectedOption().getText().trim(),
					xr1.getCellData("Sheet1", "Invoice Status", i).trim());

			Select secondaryCategoryDrdown =
					new Select(manageInvoices.getSelect("Secondary Category"));
			UseAssert.assertEquals(
					secondaryCategoryDrdown.getFirstSelectedOption().getText().trim(),
					xr.getCellData("Sec invoice Master", "Secondary Category", i));

			Select secondaryReasonDrpdown =
					new Select(manageInvoices.getSelect("Secondary Reason"));
			UseAssert.assertEquals(secondaryReasonDrpdown.getFirstSelectedOption().getText().trim(),
					xr1.getCellData("Sheet1", "Secondary Reason", i).trim());

			Select paymentStatusDrpdown = new Select(manageInvoices.getSelect("Payment Status"));
			UseAssert.assertEquals(paymentStatusDrpdown.getAllSelectedOptions().size(), 0);

			Assert.assertTrue(manageInvoices.comments().isDisplayed());
			Assert.assertTrue(manageInvoices.saveComment().isDisplayed());
			Assert.assertTrue(manageInvoices.backupDocumentation().isDisplayed());

		} else if (userType.equals("User")) {

			// Fields that will visible to user only
			UseAssert.assertEquals(manageInvoices.getLabel("Invoice Status").getText(),
					xr1.getCellData("Sheet1", "User Invoice Status", i).trim());

			UseAssert.assertEquals(manageInvoices.getLabel("Secondary Category").getText(),
					xr.getCellData("Sec invoice Master", "Secondary Category", i));
			UseAssert.assertEquals(manageInvoices.getLabel("Secondary Reason").getText(),
					xr1.getCellData("Sheet1", "Secondary Reason", i).trim());

			// Fields that will not visible to user
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver,
					By.xpath("//strong[text()='Payment Status']/following::select[1]")));
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath(
					"//div[@id='invoiceModal']/descendant::textarea[@placeholder='Add your comment']")));
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By
					.xpath("//div[@id='invoiceModal']/descendant::span[@id='btn-save-comment']")));
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath(
					"//div[@id='invoiceModal']/descendant::div[@class='k-button k-upload-button']")));
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath(
					"//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr/td[3]/i")));

			// Uploaded document visible is user
			int backupDocumemtGridDataSize = manageInvoices.backupDocumentGridData().size();
			UseAssert.assertEquals(manageInvoices.uploadName(backupDocumemtGridDataSize).getText(),
					xr1.getCellData("EditSecondaryInvoice", "AdminDocumentName", i));
			// Assert.assertTrue(manageInvoices.dateTime(backupDocumemtGridDataSize).getText().contains(JavaFunction.currentDate()));
			Assert.assertTrue(manageInvoices.viewable(backupDocumemtGridDataSize).isDisplayed());
			Assert.assertTrue(
					manageInvoices.downloadable(backupDocumemtGridDataSize).isDisplayed());
		}
		ScreenShot.takeScreenShot(driver, "invoice detail");
	}
}
