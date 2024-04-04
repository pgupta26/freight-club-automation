package com.qualitesoft.freightclub.testscripts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.HomePage;

public class TestLogOff extends InitializeTest{
	
	@Test
	public void testlogoff(){
		WaitTool.sleep(5);
		HomePage homePage = new HomePage(driver);
		try {
			SeleniumFunction.click(homePage.logoff());
		}catch(Exception ex) {
			try {
				Log.info("Log Off Retry...............");
				InitializeTest.getDriver().navigate().refresh();
				WaitTool.sleep(5);
				SeleniumFunction.click(homePage.logoff());
			}catch(Exception e) {
				throw e;
			}
		}
		WaitTool.sleep(10);
		String title = driver.getTitle();
		
		if(!title.equals("Sign In with Auth0"))
			SeleniumFunction.clickJS(driver, homePage.logoff());
		ScreenShot.takeScreenShot(driver, "Logout");		
	}
}
