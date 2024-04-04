package com.qualitesoft.freightclub.testscripts.customorders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageOrderNotQuotedTab;


public class TestSubmitCustomQuoteAsAdmin extends InitializeTest {

	public void verifyNonPalletizedDetail(String panelIndex) {

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		String expectedPackageType = xr.getCellData("Input", "packageType", i).trim();
		String expectedWeight=xr.getCellData("Input","Weight", i).trim();
		String DimensionL=xr.getCellData("Input","DimensionL", i).trim();
		String DimensionW=xr.getCellData("Input","DimensionW", i).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", i).trim();
		String expectedCategory=xr.getCellData("Input","category", i).trim();			
		String expectedDeclareValue=xr.getCellData("Input","DeclaredValue", i).trim();

		expectedPackageType = "1 x "+expectedPackageType+" 1";
		expectedWeight = expectedWeight+"lbs";
		String expectedDimension = "L"+DimensionL+" x W"+DimensionW+" x H"+DimensionH+" inches";
		expectedDeclareValue = "$"+expectedDeclareValue+".00";


		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);

		String actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
		String actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "1").getText();
		String actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
		String actualDeclaredValue = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
		String actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "4").getText();

		Assert.assertEquals(actualPackageType, expectedPackageType);
		Assert.assertEquals(actualWeight, expectedWeight);
		Assert.assertEquals(actualDimentions, expectedDimension);
		Assert.assertEquals(actualDeclaredValue, expectedDeclareValue);
		Assert.assertEquals(actualCategory, expectedCategory);
	}

	public void verifyPalletizedDetail(String panelIndex) {

		Xls_Reader xr=new Xls_Reader(testData);
		int i=6;

		String expectedPackageType = xr.getCellData("Input", "packageType", i).trim();
		String expectedWeight=xr.getCellData("Input","Weight", i).trim();
		String DimensionL=xr.getCellData("Input","DimensionL", i).trim();
		String DimensionW=xr.getCellData("Input","DimensionW", i).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", i).trim();
		String expectedCategory=xr.getCellData("Input","category", i).trim();			
		String expectedDeclareValue=xr.getCellData("Input","DeclaredValue", i).trim();
		String expectedCartoon=xr.getCellData("Input","Cartons", i);


		expectedPackageType = "1 x "+expectedPackageType.split("Standard ")[1].toUpperCase();
		expectedWeight = expectedWeight+"lbs";
		String expectedDimension = "L"+DimensionL+" x W"+DimensionW+" x H"+DimensionH+" inches";
		expectedDeclareValue = "$"+expectedDeclareValue+".00";

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);

		String actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
		String actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "1").getText();
		String actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
		String actualDeclaredValue = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
		String actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "6").getText();
		String actualCartoon = notQuotedTab.getCellValueFromPackage(panelIndex, "5").getText();


		Assert.assertEquals(actualPackageType, expectedPackageType);
		Assert.assertEquals(actualWeight, expectedWeight);
		Assert.assertEquals(actualDimentions, expectedDimension);
		Assert.assertEquals(actualDeclaredValue, expectedDeclareValue);
		Assert.assertEquals(actualCartoon, expectedCartoon);
		Assert.assertEquals(actualCategory, expectedCategory);
	}

	public void verifyAddedProductDetail(String panelIndex) {

		Xls_Reader xr=new Xls_Reader(testData);
		int i=4;

		String expectedWeight=xr.getCellData("Input","Weight", i).trim();
		String DimensionL=xr.getCellData("Input","DimensionL", i).trim();
		String DimensionW=xr.getCellData("Input","DimensionW", i).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", i).trim();
		String expectedCategory=xr.getCellData("Input","category", i).trim();			
		String expectedCartoon=xr.getCellData("Input","Cartons", i).trim();


		expectedWeight = expectedWeight+" lbs";
		String expectedDimension = DimensionL+" x "+DimensionW+" x "+DimensionH+" in";

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);

		String actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
		String actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
		String actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
		String actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "4").getText();
		String actualCartoon = notQuotedTab.getCellValueFromPackage(panelIndex, "1").getText();

		//Assert.assertEquals(actualPackageType, "1 x "+InitializeTest.Productname);
		Assert.assertEquals(actualWeight, expectedWeight);
		Assert.assertEquals(actualDimentions, expectedDimension);
		//Assert.assertEquals(actualCategory, expectedCategory);
		//Assert.assertEquals(actualCartoon, expectedCartoon);
		 
	}

	public void testValidateRequiredFields() {
		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(notQuotedTab.submitQuote());
		ScreenShot.takeScreenShot(driver, "Custom Quote Required Fields Admin");
	}

	@Test
	public void testSubmitCustomQuoteAsAdmin() {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());

		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(10);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);
		SeleniumFunction.click(notQuotedTab.notQuoted());
		WaitTool.sleep(5);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +Row);

		String shipmentType = xr.getCellData("Input","shipmentType", i).trim();
		String serviceLevel=xr.getCellData("Input","serviceLevel", i).trim();
		String regulatoryDetails = xr.getCellData("Input","RegulatoryDetails", i).trim();
		String quoteId = xr.getCellData("Input","QuoteID", i).trim();
		String COGS = xr.getCellData("Input","COGS", i).trim();
		String deliveryFrequency=xr.getCellData("Input","DeliveryFrequency", i).trim();
		String deliveryFrequency1=xr.getCellData("Input","DeliveryFrequency1", i).trim();
		String  handlingUnits = xr.getCellData("Input","HandlingUnits", i).trim();
		String  totalWeight = xr.getCellData("Input","TotalWeight", i).trim();
		String declareValue=xr.getCellData("Input","DeclaredValue", i).trim();
		String orderDetails=xr.getCellData("Input","OrderDetails", i).trim();
		String requiredTemp=xr.getCellData("Input","RequiredTemp", i).trim();
		String updatedRequiredTemp=xr.getCellData("Input","UpdatedRequiredTmp", i).trim();
		String fulfillmentCarrier=xr.getCellData("Input","Fulfillment Carrier", i).trim();
		String fulfillmentCarrierId=xr.getCellData("Input","Fulfillment Carrier Id", i).trim();
		String fulfillmentCarrier1=xr.getCellData("Input","Fulfillment Carrier1", i).trim();
		String fulfillmentCarrierId1=xr.getCellData("Input","Fulfillment Carrier Id1", i).trim();
		String updatedShipmentInformation = xr.getCellData("Input","updatedShipmentInformation", i).trim();
		String updatedRegulatoryDetails = xr.getCellData("Input","updatedRegulatoryDetails", i).trim();
	
		
		String orderid=xr.getCellData("Input","OrderId", i).trim();
		Log.info("Order Id: "+orderid);
		
		SeleniumFunction.sendKeys(manageOrderpage.searchFieldsOnTable(keyword, "1"), orderid.trim());
		manageOrderpage.searchFieldsOnTable(keyword, "1").sendKeys(Keys.ENTER);
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Order Id filter");
		 
		SeleniumFunction.clickJS(driver, notQuotedTab.complete());
		WaitTool.sleep(10);
		
		//Verify Details
		try {
			Assert.assertEquals(serviceLevel, notQuotedTab.serviceLevel().getText());
		}catch(Exception | AssertionError ae) {
			SeleniumFunction.clickJS(driver, notQuotedTab.complete());
			WaitTool.sleep(10);
		}
		Assert.assertEquals(serviceLevel, notQuotedTab.serviceLevel().getText());
		Select delFreqDrpDown = new Select(driver.findElement(By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[3]/div/div[3]/div[1]/select")));
		Assert.assertEquals(delFreqDrpDown.getFirstSelectedOption().getText().trim(), deliveryFrequency);
		Assert.assertEquals(notQuotedTab.shipmentInformation().getAttribute("value"), deliveryFrequency1);
		Assert.assertEquals(notQuotedTab.regulatoryDetails().getAttribute("value"), regulatoryDetails);
		Assert.assertTrue(quickQuote.customOrderDetails(orderDetails).isSelected());
		if(orderDetails.equals("Requires refrigeration")) { 
			Assert.assertEquals(quickQuote.requiredTemp().getAttribute("value"), requiredTemp);
			SeleniumFunction.sendKeys(quickQuote.requiredTemp(), updatedRequiredTemp);
		}
		
		if(shipmentType.equals("Custom")) {
			this.testValidateRequiredFields();
			Assert.assertTrue(SeleniumFunction.getText(notQuotedTab.handlingUnits()).contains(handlingUnits));
			Assert.assertTrue(SeleniumFunction.getText(notQuotedTab.totalWeight()).contains(totalWeight.substring(0, 1)));
			Assert.assertTrue(SeleniumFunction.getText(notQuotedTab.declaredValue()).contains(declareValue.substring(0, 1)));
			ScreenShot.takeScreenShot(driver, "Custom quote page admin details");

		} else {
			// Verify all package type details
			this.verifyNonPalletizedDetail("5");
			this.verifyPalletizedDetail("4");
			this.verifyAddedProductDetail("6");
			ScreenShot.takeScreenShot(driver, "LTL submit quote page admin details");
		}

		//Enter new Details.
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,-800)");
		SeleniumFunction.click(notQuotedTab.fulfillmentCarrier());
	    
	    WaitTool.sleep(5);
	    quickQuote.acceptPopup();
	    WaitTool.sleep(5);
	    SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Select Carrier']"), 20), fulfillmentCarrier);
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@data-value='"+fulfillmentCarrierId+"']"), 10));
		SeleniumFunction.sendKeys(notQuotedTab.carrierQuoteID(), quoteId);
		SeleniumFunction.sendKeys(notQuotedTab.COGS(), COGS);
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//i[@class='fa fa-check-circle fa-lg']"), 10));
		
		if(!fulfillmentCarrier1.isEmpty()) {
			SeleniumFunction.click(notQuotedTab.fulfillmentCarrier());
			SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Select Carrier']"), 20), fulfillmentCarrier1);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@data-value='"+fulfillmentCarrierId1+"']"), 10));
			SeleniumFunction.sendKeys(notQuotedTab.carrierQuoteID(), "102");
			SeleniumFunction.sendKeys(notQuotedTab.COGS(), COGS);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//i[@class='fa fa-check-circle fa-lg'])[2]"), 10));
		} 
		ScreenShot.takeScreenShot(driver, "Fulfilment carrier details");
		
		if(!deliveryFrequency.equals("One Time Shipment")) {
			String formatDate;
			SeleniumFunction.executeJS(driver, "window.scrollBy(0,-400)");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, 1);
			formatDate = sdf.format(c.getTime()); 
		
			Log.info("Recurring expiry data: "+formatDate);
			xr.setCellData("Input","RecurringExpiry", i,formatDate);
			SeleniumFunction.executeJS(driver, "arguments[0].removeAttribute('readonly', 'readonly')", notQuotedTab.recurringExpiry());
			notQuotedTab.recurringExpiry().clear();
			SeleniumFunction.sendKeys(notQuotedTab.recurringExpiry(), formatDate);
			notQuotedTab.recurringExpiry().sendKeys(Keys.TAB);
			ScreenShot.takeScreenShot(driver, "Recurring expiry for"+deliveryFrequency);
		}
		
		//Update regulatory and shipment information
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,800)");
		SeleniumFunction.sendKeys(notQuotedTab.shipmentInformation(), updatedShipmentInformation);
		WaitTool.sleep(2);
		SeleniumFunction.scrollDownByPixel(driver, "400");
		SeleniumFunction.sendKeys(notQuotedTab.regulatoryDetails(), updatedRegulatoryDetails);
		ScreenShot.takeScreenShot(driver, "Updated regulatory and shipment information");

		WaitTool.sleep(5);
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,400)");
		WebElement markupPercentage = notQuotedTab.markupPercentage();
		markupPercentage.clear();
		SeleniumFunction.sendKeys(markupPercentage, "6");
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Markup percentage");

		//Submit Information
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
		SeleniumFunction.click(notQuotedTab.submitQuote());
		WaitTool.sleep(10);
		ScreenShot.takeScreenShot(driver, "Submit quote");

	}
}