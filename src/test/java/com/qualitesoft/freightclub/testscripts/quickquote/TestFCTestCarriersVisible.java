package com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestFCTestCarriersVisible extends InitializeTest {
	
	@Test
	public void testFCTestCarriersVisible() {
			QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
			
			quickQuote.waitForQuotesToAppear();
			SeleniumFunction.scrollDownUptoFooter(driver);
			quickQuote.expandCarries();
			WaitTool.sleep(5);
			quickQuote.acceptPopup();
			Assert.assertTrue(quickQuote.verifyCarrier(carrierName));
			ScreenShot.takeFullScreenShot("FC Test Carrier Present");
	}
}
