package com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import com.qualitesoft.freightclub.pageobjects.ShipmentReviewOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageOrderOpenQuotesPage;

public class TestBookCustomOrder extends InitializeTest {
	
	public void verifyCustomOrderDetails(Xls_Reader xr) {

		ShipmentReviewOrderPage reviewOrder = new ShipmentReviewOrderPage(driver);
		TestSubmitCustomQuoteAsAdmin submitCustomQuote = new TestSubmitCustomQuoteAsAdmin();
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +i);
		
		String COGS = xr.getCellData("Input","COGS", i).trim();
		String customerPONumber=xr.getCellData("Input","orderReferenceID", i).trim();
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();
		String handlingUnits = xr.getCellData("Input","HandlingUnits", i).trim();
		String totalWeight = xr.getCellData("Input","TotalWeight", i).trim();
		String declareValue=xr.getCellData("Input","DeclaredValue", i).trim();
		String updatedShipmentInformation = xr.getCellData("Input","updatedShipmentInformation", i).trim();
		String updatedRegulatoryDetails = xr.getCellData("Input","updatedRegulatoryDetails", i).trim();
		String updatedRequiredTemp=xr.getCellData("Input","UpdatedRequiredTmp", i).trim();
		String orderDetails=xr.getCellData("Input","OrderDetails", i).trim();

		
		Assert.assertEquals(SeleniumFunction.getText(reviewOrder.customerPONumber()), customerPONumber);
		float floatCogs = Float.parseFloat(COGS);
		float percentage = ((floatCogs * 6) / 100); 
		float quotedPrice = floatCogs + percentage;
		String quotePrice2 = "$"+quotedPrice;
		Assert.assertEquals(SeleniumFunction.getText(reviewOrder.totalAmount()).replaceAll(",", ""), quotePrice2);	
		
		if(shipmentType.equals("Less Than Truckload")) {
			submitCustomQuote.verifyPalletizedDetail("3");
			submitCustomQuote.verifyNonPalletizedDetail("4");
			submitCustomQuote.verifyAddedProductDetail("5");
		} else {
			Assert.assertTrue(SeleniumFunction.getText(reviewOrder.totalWeight()).contains(totalWeight));
			Assert.assertEquals(SeleniumFunction.getText(reviewOrder.declaredValue()).replace("$", "").replace(",", ""), (declareValue+".00"));
			Assert.assertEquals(SeleniumFunction.getText(reviewOrder.handlingUnits()).trim(), handlingUnits);
		}

		Assert.assertEquals(reviewOrder.shipmentInformation().getAttribute("readonly"),"true");
		Assert.assertEquals(reviewOrder.shipmentInformation().getAttribute("value"),updatedShipmentInformation);
		Assert.assertEquals(reviewOrder.regulatoryDetails().getAttribute("readonly"),"true");
		Assert.assertEquals(reviewOrder.regulatoryDetails().getAttribute("value"), updatedRegulatoryDetails);
		Assert.assertEquals(quickQuote.customOrderDetails(orderDetails).getAttribute("disabled"), "true");
		if(orderDetails.equals("Requires refrigeration")) { 
	    	Log.info("Attribute Value: "+quickQuote.requiredTemp().getAttribute("value"));
	    	Assert.assertEquals(quickQuote.requiredTemp().getAttribute("value"), updatedRequiredTemp);
			Assert.assertEquals(quickQuote.requiredTemp().getAttribute("readonly"),"true");
	    }
		ScreenShot.takeScreenShot(driver, "Review page");
	}
	
	public void verifyAndSelectCarrier(Xls_Reader xr) {
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		WaitTool.sleep(20);
		quickQuote.waitForQuotesToAppear();
		SeleniumFunction.scrollDownByPixel(driver, "3000");
		quickQuote.expandCarries();
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		int i=Integer.parseInt(Row);
		String carriers=xr.getCellData("Input","Carrier", i).trim();
		String[] carrierNames= carriers.split(",");
		
		if(testname.equals("Test Verify YRC and ESTES carrier will appear even if it is disabled - One-Time Delivery - QA-538 - CO01")) {
			Assert.assertTrue(quickQuote.verifyCarrier("YRC"));
		}
		
		for(int carrierNamesIndex=0; carrierNamesIndex < carrierNames.length; carrierNamesIndex++)
					Assert.assertTrue(quickQuote.verifyCarrier(carrierNames[carrierNamesIndex]));
		ScreenShot.takeFullScreenShot("Carrier successfully verified.");
		quickQuote.selectCarrier(carrierNames[0]);
	}

	@Test
	public void testBookCustomOrder() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +i);
		String orderid=xr.getCellData("Input","OrderId", i).trim();
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();
		String palletType1 = xr.getCellData("Input", "PalletType1", i).trim();
		
		
		ManageOrderOpenQuotesPage openQuotes = new ManageOrderOpenQuotesPage(driver);
		SeleniumFunction.click(openQuotes.manageOrdersLink());
		SeleniumFunction.click(openQuotes.openQuotes());
		quickQuote.acceptPopup();
		quickQuote.acceptPopup();

		//filter record by order id
		SeleniumFunction.sendKeys(openQuotes.orderIdFilter(), orderid);
		WaitTool.sleep(2); openQuotes.orderIdFilter().sendKeys(Keys.ENTER);
		WaitTool.sleep(10);


		//Verify Quoted Price
		String COGS = xr.getCellData("Input","COGS", i).trim();
		float floatCogs = Float.parseFloat(COGS);
		float percentage = ((floatCogs * 6) / 100); 
		float quotedPrice = floatCogs + percentage;
		String quotePrice2 = "$"+quotedPrice;

		Assert.assertEquals(SeleniumFunction.getText(openQuotes.quotedPrice()).replaceAll(",", ""), quotePrice2);

		//Verify action buttons
		ScreenShot.takeScreenShot(driver, "Quoted Price at Open Quotes page");
		SeleniumFunction.click(openQuotes.actions());
		SeleniumFunction.click(openQuotes.book());
		WaitTool.sleep(20);

		//select carrier
		this.verifyAndSelectCarrier(xr);
		
		if(shipmentType.equals("Less Than Truckload")) {
			//add pallet information
			if (!palletType1.isEmpty()) {
				commonOps.addPalletContents(xr, i, 1, palletType1);
			}
		}
		
		//enter shipment information
		commonOps.shipmentCompletion(xr, i);

		//click on review order
		SeleniumFunction.click(quickQuote.ReviewOrder());
		
		//verify custom order details
		this.verifyCustomOrderDetails(xr);
		
		//click on book order
		commonOps.bookOrder(xr, i);
	}
}
