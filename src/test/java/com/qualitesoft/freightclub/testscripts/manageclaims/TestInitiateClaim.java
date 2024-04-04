package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.appcommon.Messages;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;


public class TestInitiateClaim extends InitializeTest {

	@Test
	public void testInitiateClaim() {
	
			ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);
			ManageClaims manageClaims = new ManageClaims(driver);
			QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
			CommonOps commonOps = new CommonOps();

			Xls_Reader xr;
			xr=new Xls_Reader(testData);
			int i=Integer.parseInt(Row);

			String orderId=xr.getCellData("Input","OrderId", i).trim();
			String customerPO=xr.getCellData("Input","orderReferenceID", i).trim();
			String claimType=xr.getCellData("ClaimDetail","ClaimType", i).trim();
			String declaredValue=xr.getCellData("Input","DeclaredValue", i).trim(); 
			String trackingID=xr.getCellData("Input","Tracking#", i).trim();
			
			//open manage orders page and search order id
			commonOps.openManageOrdersPageAndSearchOrder(orderId);
			quickQuote.acceptPopup();

			//navigate to claim details
			SeleniumFunction.click(manageOrderPage.ActionButton());
			manageOrderPage.initiateClaim();
			SeleniumFunction.selectByVisibleText(manageOrderPage.selectClaimType(),claimType); 
			manageOrderPage.clickInitiateClaimButton();

			try {
				//switch to manage claims window
				SeleniumFunction.getCurrentWindow(driver); WaitTool.sleep(30);
				quickQuote.acceptPopup();
				
				//product amount is required field
				manageClaims.clickSubmitForReview();
				ScreenShot.takeScreenShot(driver, "Missing Field Validation"); 
				WebElement productAmount = manageClaims.requireFields("Product Amount:");
				SeleniumFunction.moveToElement(driver, productAmount);
				SeleniumFunction.scrollUpByPixel(driver, "200");
				Assert.assertTrue(productAmount.getAttribute("class").contains("form-group-error"));

				//enter product amount
				manageClaims.getField("Product Amount:").sendKeys(declaredValue);

				//claim contact email is required 
				manageClaims.clickSubmitForReview();
				ScreenShot.takeScreenShot(driver, "Missing Field Validation");

				//claims follow up email template
				SeleniumFunction.click(manageClaims.saveChanges());
				WaitTool.sleep(5);
				SeleniumFunction.click(manageClaims.confirm()); 
				WaitTool.sleep(30);

				//verify email communication send
				ScreenShot.takeScreenShot(driver, "Email Communication");
				String customerEmail=xr.getCellData("ContactInfo","EmailAddress", 2).trim();
				String carrier=xr.getCellData("ContactInfo","EmailAddress", 3).trim();

				String expectedMessage;
				expectedMessage=Messages.email_communication_user.replace("{claimType}", claimType).replace("{orderID}", orderId).replace("{customerPO}", customerPO).replace("{email}", customerEmail);
				Log.info("Expected Message: "+expectedMessage);
				manageClaims.verifyEmailCommunicationSent(expectedMessage, 1);
				expectedMessage=Messages.email_communication_carrier.replace("{orderID}", orderId).replace("{trackingID}", trackingID).replace("{email}", carrier);
				Log.info("Expected Message: "+expectedMessage);
				manageClaims.verifyEmailCommunicationSent(expectedMessage, 2);
				
				xr.setCellData("ClaimDetail", "ClaimsID", i,SeleniumFunction.getText(manageClaims.getLabel("Claim ID:")));
				xr.setCellData("ClaimDetail", "ClaimsDate", i, SeleniumFunction.getText(manageClaims.getLabel("Claim Date:")));

				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				
			}catch(Exception | AssertionError ex) {
				Log.info("Test failed with reason: "+ex.getMessage());
				SeleniumFunction.closeWindow(driver);
				SeleniumFunction.getCurrentWindow(driver);
				throw ex;
			}

			//verify claim status in manage claims grid
			xr=new Xls_Reader(testData);
			ArrayList<String> expectedGridHeader = new ArrayList<String> (); 
			Collections.addAll(expectedGridHeader,"Order ID","Claim ID","Customer PO No.","Claim Status","Claim Response",
							"Claim Date","Filed Date","Claim Age","Completed Date",
							"Approved Amount"); 
			xr=new Xls_Reader(testData);
			manageClaims.verifyManageClaimsGrid(xr, i, expectedGridHeader);
	}
}
