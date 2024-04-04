package com.qualitesoft.freightclub.testscripts.manageoverage;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OverageDetails;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;


public class TestViewOverageDetails extends InitializeTest {

	@Test
	public void testVieewOverageDetails() {

		ManageOverages manageOverages = new ManageOverages(driver);
		OverageDetails overageDetails = new OverageDetails(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		
		//Click on  manage overages link
		SeleniumFunction.click(manageOverages.manageOverages());
		
		//Accept popup
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);
		
		//Read data from sheet for selected row
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String orderID = xr.getCellData("Sec invoice Master","FC Order ID", i);
		String carrier = xr.getCellData("Sec invoice Master","Carrier", i);
		String trackingNumber = xr.getCellData("Sec invoice Master","Tracking #", i);
		String secondaryInvoiceAmount = xr.getCellData("Sec invoice Master","New Invoice Amount", i);
		String secondaryInvoiceNumber = xr.getCellData("Sec invoice Master","SECONDARY INV #", i);
		String invoiceCreatedDate = xr.getCellData("Sec invoice Master","Date CREATED (MM/DD/YYYY)", i);
		String adminSecondaryCategory = xr.getCellData("Sec invoice Master","Secondary Category", i);
		String adminReasonDetails = xr.getCellData("Sec invoice Master","Invoice Reason", i);
		
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		String orderStatus = xr1.getCellData("Sheet1","Order Status", i);
		String company = xr1.getCellData("Sheet1","Company", i);
		String disputeAge = xr1.getCellData("Sheet1","DisputedAge", i);
		String disputeType = xr1.getCellData("Sheet1","DisputeType", i);
		String adminOverageStatus = xr1.getCellData("Sheet1","OverageStatus", i);
		String userOverageStatus = xr1.getCellData("EditOverageDetails","OverageStatus", i);
		String userSecondaryCategory = xr1.getCellData("EditOverageDetails","SecondaryCategory", i);
		String userReasonDetails = xr1.getCellData("EditOverageDetails","ReasonDetails", i);
		String adminSecondaryReason = xr1.getCellData("Sheet1","Secondary Reason", i);
		String userSecondaryReason = xr1.getCellData("EditOverageDetails","SecondaryReason", i);
		String overageID = xr1.getCellData("Sheet1","OverageID", i);

		//Filter data grid by total billed
		manageOrderpage.ExpandMenupage();
		SeleniumFunction.sendKeys(manageOverages.OrderIDTextBox(), xr.getCellData("Sec invoice Master","FC Order ID", i));
		SeleniumFunction.sendKeys(manageOverages.TotalBilledTextBox(6), xr.getCellData("Sec invoice Master","New Invoice Amount", i));
		manageOverages.TotalBilledTextBox(6).sendKeys(Keys.ENTER);
		
		//Accept popup
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);
		
		//Click on view/edit button
		WaitTool.sleep(5);
		SeleniumFunction.click(manageOverages.viewEdit(1));
		
		//verify overage details fields
		SeleniumFunction.getCurrentWindow(driver);
		WaitTool.sleep(30);
		UseAssert.assertEquals(overageDetails.getLabel("Order ID:").getText(), orderID);
		String overageId = overageDetails.getLabel("Overage ID:").getText();
		Assert.assertTrue(overageId.contains(overageID));
		
		Date myDate=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
		String currentDate=dateFormat.format(myDate);
		
		UseAssert.assertEquals(overageDetails.getLabel("Disputed Date:").getText(), currentDate);
		//UseAssert.assertEquals(overageDetails.getLabel("Company:").getText(), company);
		//UseAssert.assertEquals(overageDetails.getLabel("Carrier:").getText(), carrier);
		UseAssert.assertEquals(overageDetails.getLabel("Dispute Age:").getText(), disputeAge);
		//UseAssert.assertEquals(overageDetails.getLabel("Completed Date:").getText(), "-");
		UseAssert.assertEquals(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='Tracking Number:']/following::span)[1]"), 10).getText(), trackingNumber);
		//UseAssert.assertEquals(overageDetails.getLabel("Order Status:").getText(), orderStatus);
		UseAssert.assertEquals(overageDetails.getLabel("Secondary Invoice Amount:").getText(),secondaryInvoiceAmount);
		Assert.assertTrue(overageDetails.getLabel("Secondary Invoice Number:").getText().contains(secondaryInvoiceNumber));
		//UseAssert.assertEquals(overageDetails.getLabel("Invoice Created Date:").getText(), invoiceCreatedDate.replaceAll("/", "-"));

		//UseAssert.assertEquals(overageDetails.getSelect("Dispute Type:").getText(), disputeType);
		
		if(userType.equals("Admin")) {
			UseAssert.assertEquals(overageDetails.getSelect("Overage Status:").getText(), adminOverageStatus);
			UseAssert.assertEquals(overageDetails.getSelect("Secondary Category:").getText(), adminSecondaryCategory);
			UseAssert.assertEquals(overageDetails.getSelect("Secondary Reason:").getText(), adminSecondaryReason);
			UseAssert.assertEquals(overageDetails.getLabel("Reason Details:").getAttribute("value"), adminReasonDetails);
		}	
		
		if(userType.equals("User")) {
			
			//Verify fields edited by Admin
			UseAssert.assertEquals(overageDetails.getSelect("Overage Status:").getText(), userOverageStatus);
			UseAssert.assertEquals(overageDetails.getSelect("Secondary Category:").getText(), userSecondaryCategory);
			UseAssert.assertEquals(overageDetails.getSelect("Secondary Reason:").getText(), userSecondaryReason);
			UseAssert.assertEquals(overageDetails.getLabel("Reason Details:").getAttribute("value"), userReasonDetails);

			//Fields that will be disabled for user
			Assert.assertTrue(overageDetails.getSelect("Dispute Type:").getAttribute("class").contains("disabled "));
			Assert.assertTrue(overageDetails.getSelect("Overage Status:").getAttribute("class").contains("disabled "));
			Assert.assertTrue(overageDetails.getSelect("Secondary Category:").getAttribute("class").contains("disabled "));
			SeleniumFunction.scrollDownUptoFooter(driver);
			Assert.assertTrue(overageDetails.getSelect("Secondary Reason:").getAttribute("class").contains("disabled "));
			Assert.assertTrue(overageDetails.getLabel("Reason Details:").getAttribute("disabled").contains("true"));
			
			//Verify admin Uploaded document visible is user
			int documentsGridSize = overageDetails.documentsGrid().size();
			UseAssert.assertEquals(overageDetails.uploadName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "AdminDocumentName", i));
			//Assert.assertTrue(overageDetails.dateTime(documentsGridSize).getText().contains(JavaFunction.currentDate()));
			Assert.assertTrue(overageDetails.viewable(documentsGridSize).isDisplayed());
			Assert.assertTrue(overageDetails.downloadable(documentsGridSize).isDisplayed());
			
			//Remove documnet button will not be visible to user
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr/td[3]/i")));
			
			//Verify admin comment visible to user
			UseAssert.assertEquals(overageDetails.savedComment(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "AdminComment", i));
			UseAssert.assertEquals(overageDetails.savedUserName(documentsGridSize).getText(), xr1.getCellData("EditSecondaryInvoice", "AdminUserName", i));
		}
	}
}
