package com.qualitesoft.freightclub.testscripts.signup;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.WaitTool;


public class TestLaunchQuickQuote extends InitializeTest {
	@Test
	public void testLaunchQuickQuote() {
		driver.get("https://qa.freightclub.com/Home/QuickQuote#/shipmentInformation");
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Quick Quote page");
	}

}

