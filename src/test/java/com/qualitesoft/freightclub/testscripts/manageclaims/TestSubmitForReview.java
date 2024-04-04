package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestSubmitForReview extends InitializeTest {

	public void validationSupportDocuments(Xls_Reader xr, String fieldName) {
		ManageClaims manageClaims = new ManageClaims(driver);
		int i=Integer.parseInt(Row);

		SeleniumFunction.moveToElement(driver, manageClaims.documentUploadField(fieldName));
		SeleniumFunction.scrollDownByPixel(driver, "200");
		Actions ac = new Actions(driver);
		ac.moveToElement(manageClaims.documentUploadField(fieldName)).moveByOffset(7, -15).click().build().perform();
		
		SeleniumFunction.uploadFile("ManageClaims\\"+xr.getCellData("ClaimDetail", fieldName, i));
		WaitTool.sleep(15);
		ScreenShot.takeScreenShot(driver, "File Uploaded");

		//Verify uploaded document detail
		SeleniumFunction.scrollDownByPixel(driver, "300");
		if(fieldName.equals("Additional Documents:")) {
			SeleniumFunction.selectByVisibleText(manageClaims.addiotionalDocumentType(),  xr.getCellData("ClaimDetail", "DocumentType", i));
			SeleniumFunction.click(manageClaims.addiotionalDocumentSave(1));
			WaitTool.sleep(4);
		}
		
		manageClaims.verifyUploadedDocument(fieldName, xr.getCellData("ClaimDetail", fieldName, i));
		
		if(fieldName.equals("Signed Bill of Lading:")) {
			manageClaims.verifyUploadedDocument("Additional Documents:", xr.getCellData("ClaimDetail", fieldName, i));
		}
		
		SeleniumFunction.clickJS(driver,manageClaims.removable(fieldName));
		WaitTool.sleep(2);
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::span[text()='Yes']"), 10));
		WaitTool.sleep(10);
		boolean flag=true;
		try {
			manageClaims.removable(fieldName);
		}catch(NoSuchElementException e) {
			flag=false;
		}
		Assert.assertFalse(flag);
		WaitTool.sleep(3);
		
		//Upload document
		SeleniumFunction.moveToElement(driver, manageClaims.documentUploadField(fieldName));
		SeleniumFunction.scrollDownByPixel(driver, "2000");
		ac.moveToElement(manageClaims.documentUploadField(fieldName)).moveByOffset(7, -15).click().build().perform();		
		SeleniumFunction.uploadFile("ManageClaims\\"+xr.getCellData("ClaimDetail", fieldName, i));
		WaitTool.sleep(10);
		
		if(fieldName.equals("Additional Documents:")) {
			SeleniumFunction.selectByVisibleText(manageClaims.addiotionalDocumentType(),  xr.getCellData("ClaimDetail", "DocumentType", i));
			SeleniumFunction.click(manageClaims.addiotionalDocumentSave(1));
			WaitTool.sleep(10);
		}
	}
	
	@Test
	public void testUserIsAbleToSubmitClaimForReview() {

		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		Log.info("Order ID: "+orderId);
		String claimStatus = xr.getCellData("ClaimDetail","ClaimStatus", i).trim();
		String claimType=xr.getCellData("ClaimDetail","ClaimType", i).trim();


		CommonOps commonOps = new CommonOps();
		ManageOverages manageOverages = new ManageOverages(driver);
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		
		 //navigate to manage claims page and search order ID 
		commonOps.openManageClaimsPageAndSearchOrder(orderId);

		//navigate claims details page
		SeleniumFunction.clickJS(driver, manageOverages.viewEdit(1));
		
		try {
			//switch to manage claims window
			SeleniumFunction.getCurrentWindow(driver); 
			WaitTool.sleep(30);
			quickQuote.acceptPopup();

			if(claimStatus.equalsIgnoreCase("Initiated")) {
				
				//verify commercial and sales invoice
				manageClaims.clickSubmitForReview();
				String commercialColor = manageClaims.getLabel("Commercial and Sales Invoice:").getCssValue("color");
				String commercialHexColor = Color.fromString(commercialColor).asHex();
				UseAssert.assertEquals(commercialHexColor, "#e75300");
				
				//verify pictures field will not appear when claim type loss
				List<WebElement> pictures = driver.findElements(By.xpath("//label[contains(text(),'Pictures:')]"));
				
				//verify picture is required when claim type is damage and signed bill of lading is required when claim type is loss
				String pictureColor;
				if(claimType.equals("Loss")) {
					Assert.assertEquals(pictures.size(), 0);
					pictureColor = manageClaims.getLabel("Signed Bill of Lading:").getCssValue("color");
				}else  {
					pictureColor = manageClaims.getLabel("Pictures:").getCssValue("color");
				}
				String pictureHexColor = Color.fromString(pictureColor).asHex();		
				UseAssert.assertEquals(pictureHexColor, "#e75300");

				//verify commercial and sales invoice is required when location is carrier
				SeleniumFunction.scrollUpByPixel(driver, "600");
				manageClaims.selectByVisibleText("Location:", "Carrier");
				String commercialColor1 = manageClaims.getLabel("Commercial and Sales Invoice:").getCssValue("color");
				String commercialHexColor1 = Color.fromString(commercialColor1).asHex();
				UseAssert.assertEquals(commercialHexColor1, "#e75300");
				
				//verify pictures is optional when location is carrier and signed bill of lading is required when location is carrier in case of loss claim type
				if(claimType.equals("Loss")) {
					String signedBillOfLading = (WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Signed Bill of Lading:']"), 10)).getCssValue("color");
					String signedBillOfLading1 = Color.fromString(signedBillOfLading).asHex();		
					UseAssert.assertEquals(signedBillOfLading1, "#e75300");
				} else {
					String pictureColor1 = (WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Pictures:']"), 10)).getCssValue("color");
					String pictureHexColor1 = Color.fromString(pictureColor1).asHex();		
					Assert.assertNotEquals(pictureHexColor1, "#e75300");
				}
				
				//verify commercial and sales invoice when location is consignee
				manageClaims.selectByVisibleText("Location:", "Consignee");
				String commercialColor2 = manageClaims.getLabel("Commercial and Sales Invoice:").getCssValue("color");
				String commercialHexColor2 = Color.fromString(commercialColor2).asHex();
				UseAssert.assertEquals(commercialHexColor2, "#e75300");
				
				//verify pictures is required when claim type damage and bills of lading is required when claim type is loss in case of consignee location
				if(claimType.equals("Loss")) {
					String signedBillOfLading = (WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Signed Bill of Lading:']"), 10)).getCssValue("color");
					String signedBillOfLading1 = Color.fromString(signedBillOfLading).asHex();		
					UseAssert.assertEquals(signedBillOfLading1, "#e75300");
				} else {
					String pictureColor2 = manageClaims.getLabel("Pictures:").getCssValue("color");
					String pictureHexColor2 = Color.fromString(pictureColor2).asHex();		
					UseAssert.assertEquals(pictureHexColor2, "#e75300");
				}
				
				//Upload Commercial and Sales Invoice: document and verify its fields
				validationSupportDocuments(xr,"Commercial and Sales Invoice:");

				//Upload Pictures: document and verify its fields
				if(claimType.equals("Loss")) {
					validationSupportDocuments(xr,"Signed Bill of Lading:");
				} else {
					validationSupportDocuments(xr,"Pictures:");
				}

				//Upload Proof of Liability (Refund, Replacement, Repair or Credit): document and verify its fields
				validationSupportDocuments(xr,"Proof of Liability (Refund, Replacement, Repair or Credit):");

				//Upload Additional Documents: and verify its fields in case of damage only
				if(claimType.equals("Damage")) {
					SeleniumFunction.scrollUpByPixel(driver, "400");
					validationSupportDocuments(xr,"Additional Documents:");
				}
				
				//User is able to comment
				manageClaims.addComment(xr.getCellData("ClaimDetail","UserComment", i).trim());

				//verify user comment
				WaitTool.sleep(5);
				manageClaims.verifySavedComment(xr.getCellData("ClaimDetail","UserComment", i).trim());

				//Submit for review
				manageClaims.clickSubmitForReview();
				WaitTool.sleep(30);
				
			} else if(claimStatus.equalsIgnoreCase("Filed - Additional Docs Needed")) {
				//verify claim status field
				UseAssert.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), claimStatus);
				Assert.assertFalse(manageClaims.getField("Claim Status:").isEnabled());
				
				//submit for review
				manageClaims.clickSubmitForReview();
				WaitTool.sleep(20);

				//Verify claim status after submit for review
				UseAssert.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), "Filed - Review");
				
			} else if(claimStatus.equalsIgnoreCase("Pending Documentation")) {
				
				//verify claim status field
				UseAssert.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), claimStatus);
				Assert.assertFalse(manageClaims.getField("Claim Status:").isEnabled());
				
				//verify pending documentation requested by admin
				UseAssert.assertEquals(manageClaims.pendingDocumentation().getText(), "Original Invoice, Other");
				
				//verify claim response document  uploaded by admin
				//manageClaims.verifyUploadedDocument("Claim Response Documents:", xr.getCellData("ClaimDetail", "ClaimResponseDocument", i));
				
				//verify comment made by admin not visible to user as it is internal
				String comment = "Pending Documentation Email Sent, the documentation needed {emailBody}.".replace("{emailBody}", xr.getCellData("ClaimDetail","PendingDocumentsEmailBody", i).trim());
				Assert.assertNotEquals(manageClaims.savedComment().getText(), comment);
				Assert.assertFalse(manageClaims.savedUserName().getText().contains(InitializeTest.fcusername));
				
				//submit for review
				manageClaims.clickSubmitForReview();
				WaitTool.sleep(20);
				
				//Verify claim status after submit for review
				UseAssert.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), "Review");
			}
			
			
			xr.setCellData("ClaimDetail", "ClaimStatus", i, manageClaims.getField("Claim Status:").getAttribute("value"));
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);

		}catch(Exception | AssertionError ex) {
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
		manageClaims.verifyManageClaimsGrid(xr, i, expectedGridHeader);
		
	}
}
