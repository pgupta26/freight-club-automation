package com.qualitesoft.freightclub.testscripts.bulkorders;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.freightclub.pageobjects.BulkOrdersPage;

public class TestDownloadLabelsAndBOL extends InitializeTest {
	
	@Test
	public void testDownloadLabelsAndBOL() {
		
		BulkOrdersPage bulkOrdersPage = new BulkOrdersPage(driver);
		
		//click download labels button
		SeleniumFunction.click(bulkOrdersPage.downloadLabelsButton(1));
		
		//click download BOL button
		SeleniumFunction.click(bulkOrdersPage.downloadBOLButton(1));
		
		//verify downloaded files
		ScreenShot.takeScreenShot(driver, "Labels and BOLs downloaded");
		
	}
}
