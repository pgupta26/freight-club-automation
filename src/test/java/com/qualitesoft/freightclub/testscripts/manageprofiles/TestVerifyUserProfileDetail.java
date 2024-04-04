package com.qualitesoft.freightclub.testscripts.manageprofiles;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;


public class TestVerifyUserProfileDetail extends InitializeTest {
	
	@Test
	public void testVerifyUserProfileDetail() {
		
		
		ProfileManagementPage profileManagementPage = new ProfileManagementPage(driver);
		SeleniumFunction.clickJS(driver, profileManagementPage.manageProfileLink());
		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(2);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);
		
		Xls_Reader xr = new Xls_Reader("testdata/FCfiles/"+ env +"/ManageProfile.xlsx");
		int rowsCount = xr.getRowCount("Input");
		Log.info("Manage Profile Total Rows Counts: "+rowsCount);
		
		Xls_Reader singUp = new Xls_Reader(testData);
		int totalRows = xr.getRowCount("Input");
		Log.info("Sign Up Total Rows Counts: "+totalRows);
		
		String profileName = "";
		for(int rowIndex=2; rowIndex<=6; rowIndex++) {
			
			//get default profile carrier
			profileName = xr.getCellData("Input","ProfileName", rowIndex).trim();
			
			SeleniumFunction.sendKeys(profileManagementPage.profileListfilter(), profileName);
			WaitTool.sleep(10);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//ul[@id='profilesListView']/li"), 30));
			WaitTool.sleep(10);
			SeleniumFunction.click(profileManagementPage.manageCarrierTab());
			
			List<String> defaultProfileCarrier  = profileManagementPage.getListOfEnableCarriers();
			
			//user profile carriers
			profileName = singUp.getCellData("CreateAccount","Email", rowIndex).trim();
			
			SeleniumFunction.sendKeys(profileManagementPage.profileListfilter(), profileName);
			WaitTool.sleep(10);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//ul[@id='profilesListView']/li"), 30));
			WaitTool.sleep(10);
			SeleniumFunction.click(profileManagementPage.manageCarrierTab());
			
			List<String> userProfileCarrier  = profileManagementPage.getListOfEnableCarriers();
			
			Assert.assertEquals(defaultProfileCarrier, userProfileCarrier);
						
		}
	}
}
