package com.qualitesoft.freightclub.testscripts.manageprofiles;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;

public class TestSelectUPSInsuranceParcelAndLTLBand  extends InitializeTest {
	
	@Test
	public void testSelectUPSInsuranceParcelAndLTLBand() {
		try {
			ProfileManagementPage profileManagement = new ProfileManagementPage(driver);

			SeleniumFunction.scrollUpByPixel(driver, "3000");
			WaitTool.sleep(5);
			SeleniumFunction.click(profileManagement.insuranceTab());
			WaitTool.sleep(2);
			if(!profileManagement.upsCapitalSupportedCheckbox().isSelected()) {
				SeleniumFunction.click(profileManagement.upsCapitalSupportedCheckbox());
			}
			SeleniumFunction.selectByVisibleText(profileManagement.upsCapitalBandDropdown("UPS Capital Parcel Band"), "Tier 2");
			SeleniumFunction.selectByVisibleText(profileManagement.upsCapitalBandDropdown("UPS Capital LTL Band"), "Tier 3");
			ScreenShot.takeScreenShot(driver, "Parcel and LTL band selection");
			SeleniumFunction.click(profileManagement.updateButtonInsuranceTab());
			WaitTool.sleep(15);
			
		}catch(Exception ex) {
			ex.getMessage();
			throw ex;
		}
	}

}
