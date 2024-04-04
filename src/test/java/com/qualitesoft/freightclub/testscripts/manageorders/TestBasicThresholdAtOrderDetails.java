package com.qualitesoft.freightclub.testscripts.manageorders;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestBasicThresholdAtOrderDetails extends InitializeTest {

	@Test
	public void testBasicThresholdAtOrderDetails() {
		int i=Integer.parseInt(Row);
		Log.info("Data Row: "+Row);

		ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderPage.ViewDetail());

		try {
			SeleniumFunction.getCurrentWindow(driver);		
			WaitTool.sleep(3);
			OrderDetailPage orderDetailPage =new OrderDetailPage(driver);
			if(i == 4 || i == 5 || i == 6 || i == 7) {
				Assert.assertTrue(orderDetailPage.verifyBasicThreshold());
				ScreenShot.takeScreenShot(driver, "Basic threshold present at order details page");
			}else if(i == 2 || i == 3) {
				Assert.assertFalse(orderDetailPage.verifyBasicThreshold());
				ScreenShot.takeScreenShot(driver, "Basic threshold not present at order details page");
			} else {
				Log.info("Do nothing");
			}

			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.fail(ex.getMessage());
		}
	}
}
