package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestFiledClaimStatus extends InitializeTest {
	
	@Test
	public void testFilesClaimStatus() {
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String orderId=xr.getCellData("Input","OrderId", i).trim();
		
		CommonOps commonOps = new CommonOps();
		ManageOverages manageOverages = new ManageOverages(driver);
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		
		//navigate to manage claims pageand search order ID 
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		
		//navigate claims details page
		SeleniumFunction.click(manageOverages.viewEdit(1));

		try {
			//switch to manage claims window
			SeleniumFunction.getCurrentWindow(driver); 
			WaitTool.sleep(30);
			quickQuote.acceptPopup();

			//select filed claim status
			manageClaims.selectByVisibleText("Claim Status:", "Filed");

			//click save changes
			manageClaims.clickSaveChanges();
			WaitTool.sleep(20);

			//verify comment and claim status field
			String comment = "Claim was filed";
			manageClaims.verifySavedComment(comment);
			UseAssert.assertEquals(manageClaims.getSelect("Claim Status:").getText(), "Filed");

			//verify email communication sent
			//String expectedMessage="Claim Successfully Filed Order ID: {orderID} / PO: LTLInsurance was sent to claimscontact@mailinator.com".replace("{orderID}", orderId);
			//manageClaims.verifyEmailCommunicationSent(expectedMessage);

			xr.setCellData("ClaimDetail", "ClaimStatus", i,"Filed");

			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.fail(ex.getMessage());
		}
		
		//verify claim status in manage claims grid
		ArrayList<String> expectedGridHeader = new ArrayList<String> (); 
		Collections.addAll(expectedGridHeader,"Order ID","Claim ID","Customer PO No.","Original Tracking #","Claim Status",
						"UPSC Status","Internal Status","Claim Response","Assigned To",
						"Follow-Up Date","Company","Carrier"); 
		xr=new Xls_Reader(testData);
		manageClaims.verifyManageClaimsGrid(xr, i, expectedGridHeader);
	}
}
