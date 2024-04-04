package com.qualitesoft.freightclub.testscripts.customorders;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestVerifyCustomOrderPopupWithWeight251 extends InitializeTest {

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
	public void testVerifyCustomOrderPopupWithWeight251() {
		QuickQuoteFinal	 quickQuote = new QuickQuoteFinal(driver);
		int i=Integer.parseInt(Row);
		if(i == 4) {
			this.customOrderPopupPresent();
			SeleniumFunction.click(quickQuote.getCustomQuoteButton());
			SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
			ScreenShot.takeScreenShot(driver, "Custom Order Present Verified.");
		} else {
			SeleniumFunction.sendKeys(quickQuote.Weight(1), "251");
			SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
			SeleniumFunction.click(quickQuote.SaveButton());
			this.customOrderPopupPresent();

			SeleniumFunction.click(quickQuote.getCustomQuoteButton());
			SeleniumFunction.executeJS(driver, "window.scrollBy(0,2500)");
			ScreenShot.takeScreenShot(driver, "Custom Order Present Verified.");
		}
	}
}
