package com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestLooseCartoonPopup  extends InitializeTest {
	
	public void looseCartoonPopupPresent() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		Assert.assertEquals(quickQuote.looseCartonPopupHeader(), "Loose Carton Warning");
		Assert.assertEquals(quickQuote.looseCartonPopupBody(), "LTL Shipments that exceed 10 loose cartons will be manually reviewed after booking and may be cancelled/refunded at the carrier's discretion. To avoid this, please consider palletizing your shipment or contacting Freight Club support at 866-740-9830 for a manual quote.");
		Assert.assertTrue(quickQuote.looseCartonChangeOrder());
		Assert.assertTrue(quickQuote.looseCartonGetQuote());
	}
	
	@Test
	public void testLooseCartoonPopup() {

			QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

			//Verify loose cartoon present when quantity = 11
			this.looseCartoonPopupPresent();
			ScreenShot.takeScreenShot(driver, "Loose cartoon popup when quantity = 11");
			SeleniumFunction.click(quickQuote.closeLooseCartonPopup());
			WaitTool.sleep(1);
			quickQuote.quantity2("1");

			//Verify loose cartoon present when quantity = 11 with multiple package type
			WaitTool.sleep(2);
			for(int itemInformationCount =2; itemInformationCount <= 11; itemInformationCount++)
				quickQuote.copyItemInformation(0);
			SeleniumFunction.executeJS(driver,"window.scrollBy(0,3000)");
			WaitTool.sleep(2);
			SeleniumFunction.click(quickQuote.SaveButton());
			this.looseCartoonPopupPresent();
			ScreenShot.takeScreenShot(driver, "Loose cartoon popup multiple package type");
			SeleniumFunction.click(quickQuote.closeLooseCartonPopup());
			WaitTool.sleep(1);
			SeleniumFunction.executeJS(driver,"window.scrollBy(0,-1700)");
			WaitTool.sleep(1);
			for(int itemInformationCount =2; itemInformationCount <= 11; itemInformationCount++)
				quickQuote.deleteItemInformation();
			WaitTool.sleep(2);
			SeleniumFunction.executeJS(driver,"window.scrollBy(0,500)");
			SeleniumFunction.click(quickQuote.SaveButton());
	}

}
