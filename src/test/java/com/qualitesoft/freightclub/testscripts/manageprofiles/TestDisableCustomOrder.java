package com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestDisableCustomOrder extends InitializeTest {

	@Test
	public void testDisableCustomOrder() {
		ProfileManagementPage profileManagementPage = new ProfileManagementPage(driver);
		QuickQuote quick = new QuickQuote(driver);

		SeleniumFunction.clickJS(driver, profileManagementPage.manageProfileLink());
		WaitTool.sleep(2);
		quick.acceptPopup();
		WaitTool.sleep(2);

		SeleniumFunction.sendKeys(profileManagementPage.profileListfilter(), searchUser);
		WaitTool.sleep(15);
		ScreenShot.takeScreenShot(driver, "Search User details");
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Contact Name:']"), 30));
		WaitTool.sleep(5);
		SeleniumFunction.executeJS(driver, "window.scrollBy(0,3500)");
		profileManagementPage.disableTruckLoad(enableOption);
		ScreenShot.takeScreenShot(driver, "Custom Order Button disabled");
		WaitTool.sleep(5);
		SeleniumFunction.clickJS(driver,profileManagementPage.saveProfileButtonSurePost());
		WaitTool.sleep(5);
	}
}
