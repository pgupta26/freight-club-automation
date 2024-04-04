package com.qualitesoft.freightclub.testscripts.managecarriers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageCarrierPage;

public class TestEnableResidentialPickup extends InitializeTest {

	@Test
	public void testEnableResidentialPickup() {

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);
		Log.info("Row Number: "+rowIndex);

		String carrierName = xr.getCellData("Input", "Carrier", rowIndex);
		String[] carrierNames=carrierName.split(",");
		ManageCarrierPage manageCarrierPage = new ManageCarrierPage(driver);
		manageCarrierPage.manageCarriersLink();
		SeleniumFunction.click(manageCarrierPage.classicView());
		for(int carriersCount=0; carriersCount<carrierNames.length; carriersCount++) {
			WaitTool.sleep(5);
			manageCarrierPage.filterCarrier(carrierNames[carriersCount]);
			WaitTool.sleep(5);
			SeleniumFunction.scrollIntoView(driver, manageCarrierPage.addContactButton());
			WaitTool.sleep(2);
			WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Options"), 30);
			SeleniumFunction.click(element);
			SeleniumFunction.scrollDownByPixel(driver, "400");
			manageCarrierPage.selectResidentialPickUp();
			manageCarrierPage.selectLooseCartoon();
			WaitTool.sleep(5);
			SeleniumFunction.click(manageCarrierPage.updateButton());
			Log.info("Carrier "+carrierNames[carriersCount]+" enabled sucessfully.");
		}
		WaitTool.sleep(2);
	}
}
