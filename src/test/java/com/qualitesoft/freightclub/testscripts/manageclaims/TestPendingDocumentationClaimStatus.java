package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.util.ArrayList;
import java.util.Collections;

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

public class TestPendingDocumentationClaimStatus extends InitializeTest {
	
	@Test
	public void testPendingDocumentationClaimStatus() {
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String pendingDocumentsEmailBody=xr.getCellData("ClaimDetail","PendingDocumentsEmailBody", i).trim();

		
		CommonOps commonOps = new CommonOps();
		ManageOverages manageOverages = new ManageOverages(driver);
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		
		//navigate to manage claims pageand search order ID 
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		
		//navigate claims details page
		SeleniumFunction.click(manageOverages.viewEdit(1));
		
		//switch to manage claims window
		SeleniumFunction.getCurrentWindow(driver); 
		WaitTool.sleep(30);
		quickQuote.acceptPopup();
		
		//select pending documentation claim status
		manageClaims.selectByVisibleText("Claim Status:", "Pending Documentation");

		//select value from pending documentation dropdown
		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(2);
		manageClaims.selectByVisibleText("Pending Documentation:", "Original Invoice");
		manageClaims.selectByVisibleText("Pending Documentation:", "Other");

		//upload claim response document
		SeleniumFunction.moveToElement(driver, manageClaims.documentUploadField("Claim Response Documents:"));
		SeleniumFunction.click(manageClaims.documentUploadField("Claim Response Documents:"));
		SeleniumFunction.uploadFile("ManageClaims\\"+xr.getCellData("ClaimDetail", "ClaimResponseDocument", 2));
		WaitTool.sleep(15);

		//add comment
		manageClaims.addComment(xr.getCellData("ClaimDetail","AdminComment", i).trim());

		//type pending documentation email body
		SeleniumFunction.sendKeys(manageClaims.body("filledModal"), pendingDocumentsEmailBody);
		SeleniumFunction.click(manageClaims.sendEmail("filledModal"));
		
		//verify comment and claim status field
		String comment = "Pending Documentation Email Sent, the documentation needed {emailBody}.".replace("{emailBody}", pendingDocumentsEmailBody);
		manageClaims.verifySavedComment(comment);
		UseAssert.assertEquals(manageClaims.getSelect("Claim Status:").getText(), "Pending Documentation");
		
		//verify saved comment
		//manageClaims.verifySavedComment(xr.getCellData("ClaimDetail","AdminComment", i).trim());
		
		xr.setCellData("ClaimDetail", "ClaimStatus", i,"Pending Documentation");

		SeleniumFunction.closeWindow(driver);
		SeleniumFunction.getCurrentWindow(driver);	
		
		//verify claim status in manage claims grid
		ArrayList<String> expectedGridHeader = new ArrayList<String> (); 
		Collections.addAll(expectedGridHeader,"Order ID","Claim ID","Customer PO No.","Original Tracking #","Claim Status",
						"UPSC Status","Internal Status","Claim Response","Assigned To",
						"Follow-Up Date","Company","Carrier"); 
		xr=new Xls_Reader(testData);
		manageClaims.verifyManageClaimsGrid(xr, i, expectedGridHeader);
	}
}
