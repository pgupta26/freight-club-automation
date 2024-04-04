package com.qualitesoft.freightclub.testscripts.customorders;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestVerifyCustomOrderPopupWithMultilePackageType extends InitializeTest {

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
	public void testVerifyCustomOrderPopupWithMultilePackageType() {
		int i=Integer.parseInt(Row);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,-2500)");
		if(!quickQuote.shipmentType("Less Than Truckload").getAttribute("class").contains("active"))
			SeleniumFunction.clickJS(driver, quickQuote.shipmentType("Less Than Truckload"));
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,500)");
		for(int itemInformationCount =0; itemInformationCount <= 3; itemInformationCount++) {
			if(i == 4) {
				SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[5]/div[1]/div[2]/div/div/table/tr/td[2]/div/ul/li/button[1]"), 30));
			} else {
				quickQuote.copyItemInformation(itemInformationCount);
				SeleniumFunction.scrollDownUptoFooter(driver);
				WaitTool.sleep(1);
			}
		}
		WaitTool.sleep(2);

		SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
		SeleniumFunction.click(quickQuote.SaveButton());
		this.customOrderPopupPresent();

		SeleniumFunction.click(quickQuote.getCustomQuoteButton());
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
		ScreenShot.takeScreenShot(driver, "Custom Order Present Verified.");
	}

}
