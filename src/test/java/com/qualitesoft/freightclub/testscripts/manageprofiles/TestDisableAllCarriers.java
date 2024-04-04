package com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestDisableAllCarriers extends InitializeTest {

	@Test
	public void testDisableCarrier() throws Exception {
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);
		SeleniumFunction.scrollUpByPixel(driver, "3000");
		WaitTool.sleep(5);
		SeleniumFunction.click(profileManagement.manageCarrierTab());
		WaitTool.sleep(5);
		profileManagement.disableAllCarriers();
		ScreenShot.takeScreenShot(driver, "Carriers Disables");
	}

}
