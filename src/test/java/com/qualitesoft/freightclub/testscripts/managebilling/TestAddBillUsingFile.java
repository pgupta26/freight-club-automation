package com.qualitesoft.freightclub.testscripts.managebilling;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestAddBillUsingFile extends InitializeTest{

	@Test
	public void addBillByFileUploading() throws IOException, ParseException{
		
		//get order id from quick quote test data sheet
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		String orderId = xr.getCellData("Input","OrderId", 3).trim();

		//update order id in manage billing test data sheet
		xr=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/Upload_Bills.xlsx");
		int rowCount = xr.getRowCount("Bill");
		for(int i=2; i <=rowCount; i++) {
			xr.setCellData("Bill","PO No", i ,orderId);
			xr.setCellData("Bill","Document #", i ,(orderId+"+"+i));
		}

		ManageBillingPage billingPage = new ManageBillingPage(driver);
		SeleniumFunction.click(billingPage.manageBillingLink());
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}
		SeleniumFunction.click(billingPage.selectFile());
		SeleniumFunction.uploadFile("ManageBilling\\Upload_Bills.xlsx");
		WaitTool.sleep(2);
		SeleniumFunction.click(billingPage.uploadBtn());
		WaitTool.sleep(5);

		String confirmationHeader = SeleniumFunction.getText(billingPage.confirmationHeader());
		String confirmationMessage = SeleniumFunction.getText(billingPage.confirmationSuccessMessage());
		SeleniumFunction.click(billingPage.okButton());

		UseAssert.assertEquals(confirmationHeader, "Import Bill Results");
		UseAssert.assertEquals(confirmationMessage, "2 bill(s) were successfully imported");

		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/Upload_Bills.xlsx");
		String documentId = xr1.getCellData("Bill", "Document #", 2);
		String carrierCode = xr1.getCellData("Bill", "Carrier Code", 2);
		String type = xr1.getCellData("Bill", "Type", 2);
		String carrierBill = xr1.getCellData("Bill", "Total", 2);

		WebElement document = billingPage.documentNumTextBox();
		SeleniumFunction.sendKeys(document, documentId);
		document.sendKeys(Keys.ENTER);
		WaitTool.sleep(5);

		CommonOps common =  new CommonOps();
		common.searchDocumentIdAndVerifyListing(carrierCode, type, "Valid", "-", "Ready", carrierBill, documentId);

	}
}
