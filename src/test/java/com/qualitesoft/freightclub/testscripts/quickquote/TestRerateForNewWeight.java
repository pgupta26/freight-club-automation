package com.qualitesoft.freightclub.testscripts.quickquote;

import org.openqa.selenium.By;
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
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestRerateForNewWeight extends InitializeTest {

	@Test(priority = 0)
	public void verifyWithSameQuantity() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String carrier = xr.getCellData("Input","Carrier", i).trim();
		System.out.println(carrier);
		String packageType = xr.getCellData("Input","packageType", i).trim();
		String packageType2 = xr.getCellData("Input", "packageType2", i).trim();	

		//enter shipment information
		commonOps.shipmentInformation(xr,i);

		//add item package type information
		commonOps.itemInformation(xr, i, 1, packageType);
		if (!packageType2.isEmpty()) {
			SeleniumFunction.click(quickQuote.addadditionalItem());
			WaitTool.sleep(2);
			SeleniumFunction.scrollDownUptoFooter(driver);
			commonOps.itemInformation(xr, i, 2, packageType2);
		}

		//select carrier
		commonOps.selectCarrier();
		quickQuote.selectCarrier(carrier);

		// fill pallet details
		fillPalletDetails(1, 1, "40");
		SeleniumFunction.scrollIntoView(driver, quickQuote.Weight(1));
		fillPalletDetails(3, 2, "39.99");
		ScreenShot.takeFullScreenShot("Same quantity with same weight");
		reviewOrder(true, 1);
	}

	@Test(priority = 1)
	public void verifyWithMultipleQuantity() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		quickQuote.quantity2("2");
		SeleniumFunction.sendKeys(quickQuote.Weight(1), "20");
		ScreenShot.takeFullScreenShot("Multiple quantity with same weight");
		reviewOrder(true, 1);
	}

	@Test(priority = 2)
	public void verifyWithMultipleItem() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		quickQuote.quantity2("1");
		SeleniumFunction.click(quickQuote.addadditionalItem());
		fillPalletDetails(2, 2, "20");
		ScreenShot.takeFullScreenShot("Multiple item with same weight");
		reviewOrder(true, 1);
	}
	
	@Test(priority = 3)
	public void verifyWithItemAndProduct() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		SeleniumFunction.scrollUpByPixel(driver, "2000");
		quickQuote.deleteItemInformation();
		SeleniumFunction.click(quickQuote.addadditionalItem());
		fillAddProductDetails();
		ScreenShot.takeFullScreenShot("Item and Product with same weight");
		reviewOrder(true, 1);
	}

	@Test(priority = 4)
	public void verifyBookingWithLessWeight() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		SeleniumFunction.sendKeys(quickQuote.Weight(1), "19.99");
		ScreenShot.takeFullScreenShot("Item and Product with less weight");
		reviewOrder(false, 1);
		WaitTool.sleep(15);
		SeleniumFunction.clickJS(driver, quickQuote.Book());
		WaitTool.sleep(3);
		if(quickQuote.acknowleadgeBtnStatus() == true){
			String actualPopupHeader = SeleniumFunction.getText(quickQuote.acknowleadgeModalHeader());
			String actualPopupBody = SeleniumFunction.getText(quickQuote.acknowleadgeModalBody());
			UseAssert.assertEquals(actualPopupHeader, "A matching order has already been placed.");
			UseAssert.assertEquals(actualPopupBody, "We have identified that the order that you are about to place is similar to an order that has already been booked. Would you like to continue with placing a duplicate order?");
			WaitTool.sleep(2);
			SeleniumFunction.click(quickQuote.acknowleadgeBtn());
			WaitTool.sleep(2);
			SeleniumFunction.clickJS(driver, quickQuote.Book());
		}
		ScreenShot.takeFullScreenShot("Booked page");
		SeleniumFunction.clickJS(driver, quickQuote.Okbutton1());
		WaitTool.sleep(10);
		ScreenShot.takeScreenShot(driver, "Order Booked");
	}





	public static void fillPalletDetails(int rowIndex, int itemIndex, String weight) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		SeleniumFunction.clickJS(driver, quickQuote.PackageType("Non-Palletized", itemIndex));
		SeleniumFunction.sendKeys(quickQuote.Weight(itemIndex), weight);
		SeleniumFunction.sendKeys(quickQuote.DimensionL(rowIndex), "40");
		SeleniumFunction.sendKeys(quickQuote.DimensionW(rowIndex), "50");
		SeleniumFunction.sendKeys(quickQuote.DimensionH(rowIndex), "60");
		SeleniumFunction.selectByvalue(quickQuote.Category(itemIndex), "347");
		SeleniumFunction.click(quickQuote.popupCateogory(itemIndex));
		WaitTool.sleep(2);
		SeleniumFunction.sendKeys(quickQuote.DeclaredValue(itemIndex), "100");
		ScreenShot.takeScreenShot(driver, "Pallet Info");
	}

	public static void fillAddProductDetails() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManageProducts manageProducts = new ManageProducts(driver);
		String Productname=JavaFunction.randomText("prod", 4);
		Log.info("Product Name: "+Productname);

		SeleniumFunction.click(quickQuote.productvalue(1));
		SeleniumFunction.click(quickQuote.addProduct(2));
		SeleniumFunction.sendKeys(manageProducts.SKU(),Productname);
		SeleniumFunction.sendKeys(manageProducts.productName(),Productname);
		SeleniumFunction.sendKeys(manageProducts.declaredValue(),"100");

		SeleniumFunction.selectByvalue(manageProducts.category(1), "347");
		WaitTool.sleep(2);
		SeleniumFunction.click(manageProducts.popupCateogory(2));
		WaitTool.sleep(2);
		SeleniumFunction.sendKeys(manageProducts.cartoonweight(1),"20");
		SeleniumFunction.sendKeys(manageProducts.cartoonlength(1), "40");
		SeleniumFunction.sendKeys(manageProducts.cartoonwidth(1), "50");
		SeleniumFunction.sendKeys(manageProducts.cartoonheight(1), "60");

		SeleniumFunction.click(manageProducts.saveproduct());
		Log.info(quickQuote.verifyToastMessage());
		Assert.assertEquals(quickQuote.verifyToastMessage(), "Success\nThe product was successfully saved");
	}

	public void reviewOrder(boolean rerateValidation, int index) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		QuickQuote quickQuote1 = new QuickQuote(driver);

		SeleniumFunction.click(quickQuote.ReviewOrder());
		if(rerateValidation == true){
			Assert.assertEquals(quickQuote1.customOrderHeader(), "Re-rate with new weight");
			Assert.assertEquals(quickQuote1.customOrderPopupBody(), "The total weight of the cartons you have added to the pallet, is more than what you have indicated for the pallet at the time of quoting. Would you like to re-rate the shipment with the new estimated weight?");
			SeleniumFunction.click(quickQuote1.clickPopupFooterButton(index));
		}
	}
}
