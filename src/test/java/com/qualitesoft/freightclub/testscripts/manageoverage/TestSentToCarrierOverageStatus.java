package com.qualitesoft.freightclub.testscripts.manageoverage;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.OverageDetails;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;


public class TestSentToCarrierOverageStatus extends InitializeTest {
	
	@Test
	public void testSentToCarrierOverageStatus() {
		
		Xls_Reader xr=new Xls_Reader(testData);
		
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		int i=Integer.parseInt(Row);
		
		ManageOverages manageOverages = new ManageOverages(driver);
		OverageDetails overageDetails = new OverageDetails(driver);
		QuickQuote quickQuote = new QuickQuote(driver);

		//Click on  manage overages link
		SeleniumFunction.click(manageOverages.manageOverages());
		
		//Accept popup
		WaitTool.sleep(10);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);
		
		//Filter data grid by total billed
		WaitTool.sleep(5);
		SeleniumFunction.sendKeys(manageOverages.OrderIDTextBox(), xr.getCellData("Sec invoice Master","FC Order ID", i));
		SeleniumFunction.sendKeys(manageOverages.TotalBilledTextBox(6), xr.getCellData("Sec invoice Master","New Invoice Amount", i));
		manageOverages.TotalBilledTextBox(6).sendKeys(Keys.ENTER);
		
		//Verify overage status in data grid
		WaitTool.sleep(5);
		UseAssert.assertEquals(manageOverages.gridData(1, 5).getText(), "In Review");	
		
		//Click on view/edit button
		SeleniumFunction.click(manageOverages.viewEdit(1));
		
		try {
			//Verify overage status
			SeleniumFunction.getCurrentWindow(driver);
			UseAssert.assertEquals(overageDetails.getSelect("Overage Status:").getText(), "In Review");
			
			//Verify document uploaded by user
			WaitTool.sleep(2);
			int documentsGridSize = overageDetails.documentsGrid().size();
			UseAssert.assertEquals(overageDetails.uploadName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserDocumentName", i));
			//Assert.assertTrue(overageDetails.dateTime(documentsGridSize).getText().contains(JavaFunction.currentDate()));
			Assert.assertTrue(overageDetails.viewable(documentsGridSize).isDisplayed());
			Assert.assertTrue(overageDetails.downloadable(documentsGridSize).isDisplayed());
			
			//Verify comment made by user
			UseAssert.assertEquals(overageDetails.savedComment(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserComment", i));
			UseAssert.assertEquals(overageDetails.savedUserName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserName", i));

			//Select overage status
			SeleniumFunction.click(overageDetails.getSelect("Overage Status:"));
			SeleniumFunction.click(overageDetails.setSelect("Overage Status:", "Sent to Carrier"));
			
			//click on save changes
			SeleniumFunction.horizontalScroll(driver);
			SeleniumFunction.scrollDownUptoFooter(driver);
			WaitTool.sleep(2);
			SeleniumFunction.click(overageDetails.saveChanges());
			
			//Clear and enter customer email
			String customerEmail = SeleniumFunction.getText(overageDetails.getLabelFromModal("sentToCarrierModal", "Customer Email"));
			Log.info("Customer Email: "+customerEmail);
			SeleniumFunction.sendKeys(overageDetails.getLabelFromModal("sentToCarrierModal", "Customer Email"), xr1.getCellData("EditOverageDetails", "SentToCarrier_Customer_Email", i));
			
			//Body
			SeleniumFunction.sendKeys(overageDetails.getLabelFromModal("sentToCarrierModal", "Body"), xr1.getCellData("EditOverageDetails", "SentToCarrier_Email_Body", i));
			
			//Send email
			SeleniumFunction.click(overageDetails.sendEmail("sentToCarrierModal")); 
			WaitTool.sleep(10);
			ScreenShot.takeScreenShot(driver, "Send to carrier status selected");
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			
		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.fail(ex.getMessage());
		}
	}
}
