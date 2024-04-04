package com.qualitesoft.freightclub.testscripts.managecarriers;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManageCarrierPage;

public class TestEnableCarrierDetailsOptions extends InitializeTest {
	
	@Test 
	public void testEnableCarrierDetailsOptions() {
		String[] carrierNames=carrierName.split(",");
		
		ManageCarrierPage manageCarrierPage = new ManageCarrierPage(driver);
		manageCarrierPage.manageCarriersLink();
		for(int carriersCount=0; carriersCount<carrierNames.length; carriersCount++) {
			SeleniumFunction.sendKeys(manageCarrierPage.filterCarrier(), carrierNames[carriersCount]);
			WaitTool.sleep(5);
			SeleniumFunction.click(manageCarrierPage.expandCarrier());
			SeleniumFunction.click(manageCarrierPage.clkCarriersettings());
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Options"), 30));
			String[] carrierDetailsOption=carrierDetailsOptions.split(",");
			for(int i=0; i<carrierDetailsOption.length; i++) {
				if(!manageCarrierPage.carrierDetailsOptions(carrierDetailsOption[i]).isSelected()) {
					SeleniumFunction.click(manageCarrierPage.carrierDetailsOptions(carrierDetailsOption[i]));
					WaitTool.sleep(2);
				}
			}
			WaitTool.sleep(2);
			SeleniumFunction.clickJS(driver, manageCarrierPage.updateButton());
			WaitTool.sleep(5);
			SeleniumFunction.click(manageCarrierPage.clkCloseCarrierSetting());
			WaitTool.sleep(5);
		}
			
			/* manageCarrierPage.filterCarrier(carrierNames[carriersCount]);
			WaitTool.sleep(5);
			SeleniumFunction.scrollDownByPixel(driver, "1400");
			WaitTool.sleep(5);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Options"), 30));
			String[] carrierDetailsOption=carrierDetailsOptions.split(",");
			for(int i=0; i<carrierDetailsOption.length; i++) {
				if(!manageCarrierPage.carrierDetailsOptions(carrierDetailsOption[i]).isSelected()) {
					SeleniumFunction.click(manageCarrierPage.carrierDetailsOptions(carrierDetailsOption[i]));
				}
				WaitTool.sleep(2);
			}	
			SeleniumFunction.scrollDownByPixel(driver, "500");
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Update']"), 30));
		} */
	}
}
