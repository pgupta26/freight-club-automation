package com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestFCTestCarriersNotVisible extends InitializeTest {
	
	@Test
	public void testFCTestCarriersNotVisible() {
			QuickQuote quickQuote = new QuickQuote(driver);
			
			Assert.assertFalse(quickQuote.verifyCarrier(carrierName));
			ScreenShot.takeFullScreenShot("FC Test Carrier not Present");
			WaitTool.sleep(20);
	}
}
