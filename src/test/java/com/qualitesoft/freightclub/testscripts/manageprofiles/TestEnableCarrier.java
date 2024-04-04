package com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestEnableCarrier extends InitializeTest {

	@Test
	public void testEnableCarrier() throws Exception {
		ProfileManagementPage profileManagement = new ProfileManagementPage(driver);

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);
		Log.info("Row Number: "+rowIndex);

		SeleniumFunction.scrollUpByPixel(driver, "3000");
		WaitTool.sleep(5);
		String carrierName = xr.getCellData("Input", "Carrier", rowIndex);
		String[] carrierNames=carrierName.split(",");
		SeleniumFunction.click(profileManagement.manageCarrierTab());
		WaitTool.sleep(5);
		profileManagement.enableCarrier(carrierNames);
	}
}
