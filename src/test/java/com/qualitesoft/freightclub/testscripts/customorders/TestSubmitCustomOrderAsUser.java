package com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestSubmitCustomOrderAsUser extends InitializeTest {

	public void customOrderPopupPresent() {
		QuickQuote quickQuote = new QuickQuote(driver);
		SoftAssert as = new SoftAssert();
		as.assertEquals(quickQuote.customOrderHeader(), "Requires a Custom Quote");
		as.assertEquals(quickQuote.customOrderPopupBody(), "LTL Shipments are limited to 4 pallets per shipment.   Freight Club can provide you for a quote through our custom quote process, in which we use the information you've already provided.");
		as.assertTrue(quickQuote.customOrderReviseLTSDetailButton());
		as.assertTrue(quickQuote.customOrderCustomQuoteButton());
		ScreenShot.takeScreenShot(driver,"Custom Order Popup");
	}

	@Test
	public void testSubmitCustomOrderAsUser() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String shipmentType = xr.getCellData("Input","shipmentType", i).trim();
		String packageType = xr.getCellData("Input","packageType", i).trim();
		String packageType2 = xr.getCellData("Input", "packageType2", i).trim();	
		String packageType3 = xr.getCellData("Input", "packageType3", i).trim();
		String pickUPLocationName = xr.getCellData("Input","pickUpLocationName", i).trim();
		String dropOffLocationName = xr.getCellData("Input","dropOffLocationName", i).trim();
		String deliveryFrequency=xr.getCellData("Input","DeliveryFrequency", i).trim();
		String deliveryFrequency1=xr.getCellData("Input","DeliveryFrequency1", i).trim();
		String orderDetails=xr.getCellData("Input","OrderDetails", i).trim();
		String regulatoryDetails = xr.getCellData("Input","RegulatoryDetails", i).trim();
		String  handlingUnits = xr.getCellData("Input","HandlingUnits", i).trim();
		String  totalWeight = xr.getCellData("Input","TotalWeight", i).trim();
		String declareValue=xr.getCellData("Input","DeclaredValue", i).trim();

		//enter shipment information
		commonOps.shipmentInformation(xr,i);

		if(shipmentType.equals("Less Than Truckload")) {
			SeleniumFunction.scrollDownUptoFooter(driver);
			//add item package type information
			commonOps.itemInformation(xr, i, 1, packageType);

			if (!packageType2.isEmpty()) {
				SeleniumFunction.scrollDownUptoFooter(driver);
				SeleniumFunction.click(quickQuote.addadditionalItem());
				WaitTool.sleep(2);
				SeleniumFunction.scrollDownUptoFooter(driver);
				commonOps.itemInformation(xr, i, 2, packageType2);
			}
			if (!packageType3.isEmpty()) {
				SeleniumFunction.scrollDownUptoFooter(driver);
				SeleniumFunction.click(quickQuote.addadditionalItem());
				WaitTool.sleep(2);
				SeleniumFunction.scrollDownUptoFooter(driver);
				commonOps.itemInformation(xr, i, 1, packageType3);
			}

			//click on save button
			SeleniumFunction.click(quickQuote.SaveButton());

			//verify and accept custom order popup
			this.customOrderPopupPresent();
			SeleniumFunction.click(quickQuote.getCustomQuoteButton());
		} else {
			SeleniumFunction.select(quickQuote.handlingUnits(), handlingUnits);
			SeleniumFunction.sendKeys(quickQuote.totalWeights(), totalWeight);
			SeleniumFunction.sendKeys(quickQuote.DeclaredValue(1), declareValue);
			SeleniumFunction.selectByVisibleText(quickQuote.deliveryFrequencySelect(), deliveryFrequency);
		}		

		if(pickUPLocationName.isEmpty()) {
			SeleniumFunction.sendKeys(quickQuote.PickUpCompanyName(), "Pick Up Company");
			SeleniumFunction.sendKeys(quickQuote.PickUpAddress1(), "Address Line1");
		}

		if(dropOffLocationName.isEmpty()) {
			SeleniumFunction.sendKeys(quickQuote.dropOffCompanyName(), "Drop Off Company");
			SeleniumFunction.sendKeys(quickQuote.DropAddress4(), "Address Line2");
		}

		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(quickQuote.customOrderDetails(orderDetails));
		SeleniumFunction.sendKeys(quickQuote.regulatoryDetails(), regulatoryDetails);

		if(shipmentType.equals("Custom") && i == 2) {
			SeleniumFunction.sendKeys(quickQuote.deliveryFrequency(), "tes");
			SeleniumFunction.click(quickQuote.submitForQuote());
			Assert.assertTrue(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[text()='Minimum width must be bigger than 4 characters']")));
			ScreenShot.takeScreenShot(driver, "validation when delivery frequency length less than 4");
		}
		
		SeleniumFunction.sendKeys(quickQuote.deliveryFrequency(), deliveryFrequency1);
		ScreenShot.takeScreenShot(driver, "Shipment Details");
		SeleniumFunction.click(quickQuote.submitForQuote());

		WaitTool.sleep(30);
		quickQuote.acceptPopup();

		WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='incompleteGrid']/descendant::div[@class='ag-body-container']/descendant::div[@colid='ID']"), 20);
		crorderId=SeleniumFunction.getText(driver.findElement(By.xpath("//div[@id='incompleteGrid']/descendant::div[@class='ag-body-container']/descendant::div[@colid='ID']")));
		Log.info("crorderId:" + crorderId.trim());

		//set order id in excel
		xr.setCellData("Input","OrderId", i,crorderId.trim());
		WaitTool.sleep(5);
	}
}
