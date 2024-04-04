package com.qualitesoft.freightclub.testscripts.quickquote;

import com.qualitesoft.core.Xls_Reader;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class TestQuickQuoteStg extends InitializeTest {
	
	@Test
	public void testGetQuote() {

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		//clear existing data
		xr.setCellData("Input","OrderId", i,"");

		//Read data from sheet for selected row
		String orderReferenceID=xr.getCellData("Input","orderReferenceID", i).trim();
		String packageType = xr.getCellData("Input", "packageType", i).trim();
		String carrier = xr.getCellData("Input","Carrier", i).trim();
		String insurance=xr.getCellData("Input","UpsInsurance", i).trim();
		String packageType2 = xr.getCellData("Input", "packageType2", i).trim();
		String palletType1 = xr.getCellData("Input", "PalletType1", i).trim();
		String palletType2 = xr.getCellData("Input", "PalletType2", i).trim();
					

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		// enter shipment information
		commonOps.shipmentInformation(xr, i);

		// add item package type information
		commonOps.itemInformation(xr, i, 1, packageType);
		
		//uncheck insurance checkbox
		if(insurance.equals("No"))
			quickQuote.deselectInsurance();
		
		//move to carriers page
		commonOps.selectCarrier();
				
		String insuranceValue = null;
		if(insurance.equals("Yes")) {
			insuranceValue = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[5]/ul/li"), 10).getText();
			Log.info("Insurance Value: "+insuranceValue);
			insuranceValue = insuranceValue.split("\\(")[1].split("\\)")[0];
		}
		String amount = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[6]/span"), 10).getText().trim();
		quickQuote.selectCarrier(carrier);
		
		// add pallet information
		if (!palletType1.isEmpty()) {
			commonOps.addPalletContents(xr, i, 1, palletType1);
		}

		// enter shipment information
		commonOps.shipmentCompletion(xr, i);
		
		SeleniumFunction.click(quickQuote.ReviewOrder());
		quickQuote.acceptPopup();
		WaitTool.sleep(10);
		commonOps.bookOrder(xr, i);
		
		ManageOverages manageOverage = new ManageOverages(driver);

		String orderID = manageOverage.gridData(1, 1).getText();
		Log.info("Order ID: "+orderID);
		String orderDate = manageOverage.gridData(1, 3).getText();
		Log.info("Order Date: "+orderDate);
		String wayBill = manageOverage.gridData(1, 8).getText();
		Log.info("WayBill: "+wayBill);
		String tracking = manageOverage.gridData(1, 9).getText();
		Log.info("Tracking: "+tracking);
		
		//set order id in excel
		xr.setCellData("Input","OrderId", i, orderID);
		xr.setCellData("Input","pickUpDate", i, orderDate.replace("/", "-"));
		xr.setCellData("Input","Amount", i, amount);
		xr.setCellData("Input","Tracking#", i, tracking);
		xr.setCellData("Input","WayBill", i, wayBill);
		xr.setCellData("Input","Insurance", i, insuranceValue);
		xr.setCellData("ClaimDetail","ClaimStatus", i, "Initiated");
		// xr.setCellData("ShipmentInformation","PickCompanyName", 2, pickLocName);
		// xr.setCellData("ShipmentInformation","DropCompanyName", 2, dropLocName);
		
		//update test data for manage invoices
		Xls_Reader manageInvoiceTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		manageInvoiceTestData.setCellData("Sheet1", "OrderDate", 2, orderDate);
		manageInvoiceTestData.setCellData("Sheet1", "Customer PO #", 2, orderReferenceID);
		manageInvoiceTestData.setCellData("Sheet1", "OverageID", 2, orderID);

		Xls_Reader secondaryInvoice = new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/SecondaryInvoiceTemplate.xlsx");
		secondaryInvoice.setCellData("Sec invoice Master", "FC Order ID", 2, orderID);
		secondaryInvoice.setCellData("Sec invoice Master", "Date CREATED (MM/DD/YYYY)", 2, orderDate);
		secondaryInvoice.setCellData("Sec invoice Master", "Tracking #", 2, tracking);
		secondaryInvoice.setCellData("Sec invoice Master", "SECONDARY INV #", 2, orderID+"+1");
		
		// //random new invoice amount generation
		// boolean flag=true;
		// String newInvoiceAmount = null;
		// int lastDigit;
		
		// while(flag) {
		// 	 newInvoiceAmount = String.valueOf(JavaFunction.getRandomNumber(1, 999))+"."+String.valueOf(JavaFunction.getRandomNumber(1, 99));
		// 	 lastDigit = Integer.parseInt(String.valueOf(newInvoiceAmount.charAt(newInvoiceAmount.length()-1)));
		// 	 if(lastDigit > 0) {
		// 		flag = false; 
		// 	 }
		// }
		// Log.info("New Invoice Amount Generated:  "+newInvoiceAmount);
		// secondaryInvoice.setCellData("Sec invoice Master", "New Invoice Amount", 2, "$"+newInvoiceAmount);
		
		//update test data for manage claims carrier payment
		Xls_Reader manageClaimsTestData;
		if(i == 2) {
			
			manageClaimsTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/Carrier_Payment_Template.xlsx");
			manageClaimsTestData.setCellData("Bulk Payment Update", "Claim ID", 2, orderID);
			manageClaimsTestData.setCellData("Bulk Payment Update", "Payment Date", 2, JavaFunction.currentDateUSFormat().replaceAll("-", "/"));
		
		} else {
			
			//update test data for manage claims carrier payment
			manageClaimsTestData = new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/Company_Payment_Template.xlsx");
			manageClaimsTestData.setCellData("Bulk Payment Update", "Claim ID", 2, orderID);
		
		}
		
		WaitTool.sleep(5);
	}
}
