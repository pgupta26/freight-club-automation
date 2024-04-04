package com.qualitesoft.freightclub.testscripts.manageoverage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestEditOverageDetails extends InitializeTest {

	@Test
	public void testEditOverageDetails() {

		ManageOverages manageOverages = new ManageOverages(driver);
		OverageDetails overageDetails = new OverageDetails(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/Overages/ManageInvoiceTestData.xlsx");
		int i=Integer.parseInt(Row);
		
		//verify overage details fields
		SeleniumFunction.getCurrentWindow(driver);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);

		if(userType.equals("Admin")) {

			//Select overage status
			SeleniumFunction.click(overageDetails.getSelect("Overage Status:"));
			SeleniumFunction.click(overageDetails.setSelect("Overage Status:", xr1.getCellData("EditOverageDetails", "OverageStatus", i).trim()));
			driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
			WaitTool.sleep(3);

			//Select secondary category
			SeleniumFunction.click(overageDetails.getSelect("Secondary Category:"));
			SeleniumFunction.click(overageDetails.setSelect("Secondary Category:", xr1.getCellData("EditOverageDetails", "SecondaryCategory", i).trim()));
			driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
			WaitTool.sleep(3);
			
			//Select secondary reason
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Secondary Reason:']/following-sibling::div/descendant::div[contains(@class,'has-options')]"), 20));
			SeleniumFunction.click(overageDetails.setSelect("Secondary Reason:", xr1.getCellData("EditOverageDetails", "SecondaryReason", i).trim()));
			driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
			WaitTool.sleep(3);
			
			//Enter reason details
			SeleniumFunction.sendKeys(overageDetails.getLabel("Reason Details:"), xr1.getCellData("EditOverageDetails", "ReasonDetails", i).trim());

			//Type comments
			SeleniumFunction.moveToElement(driver, overageDetails.saveChanges());
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.executeJS(driver, "window.scrollBy(2000,0)");
			SeleniumFunction.sendKeys(overageDetails.comments(), xr1.getCellData("EditOverageDetails", "AdminComment", i));

			//Click on save comment
			SeleniumFunction.click(overageDetails.saveComment());

			//Type email body
			SeleniumFunction.sendKeys(overageDetails.body("moreInfoModal"), xr1.getCellData("EditOverageDetails", "EmailBody", i));
			SeleniumFunction.click(overageDetails.sendEmail("moreInfoModal"));

			//Click on backup documentation button
			WaitTool.sleep(5);
			quickQuote.acceptPopup();
			WaitTool.sleep(2);
			Actions actions = new Actions(driver);
			actions.moveToElement(overageDetails.documents()).click().build().perform();

			//Upload document
			SeleniumFunction.uploadFile("Overages\\"+xr1.getCellData("EditOverageDetails", "AdminDocumentName", i));

			//Verify uploaded document detail
			WaitTool.sleep(10);
			int documentsGridSize = overageDetails.documentsGrid().size();
			UseAssert.assertEquals(overageDetails.uploadName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "AdminDocumentName", i));
			//Assert.assertTrue(overageDetails.dateTime(documentsGridSize).getText().contains(JavaFunction.currentDate()));
			Assert.assertTrue(overageDetails.viewable(documentsGridSize).isDisplayed());
			Assert.assertTrue(overageDetails.downloadable(documentsGridSize).isDisplayed());
			
			//Verify saved comment
			UseAssert.assertEquals(overageDetails.savedComment(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "AdminComment", i));
			UseAssert.assertEquals(overageDetails.savedUserName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "AdminUserName", i));
			
			//Verify removeable button visible to admin
			Assert.assertTrue(overageDetails.removable().isDisplayed());
			
			//Click on save changes
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.click(overageDetails.saveChanges());
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}else {
			
			//Type comments
			SeleniumFunction.sendKeys(overageDetails.comments(), xr1.getCellData("EditOverageDetails", "UserComment", i));

			//Click on save comment
			SeleniumFunction.click(overageDetails.saveComment());

			//Click on backup documentation button
			WaitTool.sleep(2);
			SeleniumFunction.scrollUpByPixel(driver, "1000");
			Actions actions = new Actions(driver);
			actions.moveToElement(overageDetails.documents()).click().build().perform();

			//Upload document
			SeleniumFunction.uploadFile("Overages\\"+xr1.getCellData("EditOverageDetails", "UserDocumentName", i));

			//Verify uploaded document detail
			WaitTool.sleep(10);
			int documentsGridSize = overageDetails.documentsGrid().size();
			UseAssert.assertEquals(overageDetails.uploadName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserDocumentName", i));
			//Assert.assertTrue(overageDetails.dateTime(documentsGridSize).getText().contains(JavaFunction.currentDate()));
			Assert.assertTrue(overageDetails.viewable(documentsGridSize).isDisplayed());
			Assert.assertTrue(overageDetails.downloadable(documentsGridSize).isDisplayed());
			
			//Verify saved comment
			UseAssert.assertEquals(overageDetails.savedComment(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserComment", i));
			UseAssert.assertEquals(overageDetails.savedUserName(documentsGridSize).getText(), xr1.getCellData("EditOverageDetails", "UserName", i));
			
			//Verify removeable button will not visible to user
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr/td[3]/i")));

			//Click submit for review		
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.click(overageDetails.submitForReview());

			//Verify overage status
			WaitTool.sleep(15);
			UseAssert.assertEquals(overageDetails.getSelect("Overage Status:").getText(), "In Review");

			//Close current window and switch to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);

			//Verify grid button text
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			
			Xls_Reader xr=new Xls_Reader(testData);
			
			String orderID = xr.getCellData("Sec invoice Master","FC Order ID", i);
			String amount = xr.getCellData("Sec invoice Master","New Invoice Amount", i);
			
			//Click on  manage overages link
			SeleniumFunction.click(manageOverages.manageOverages());
			
			//Accept popup
			WaitTool.sleep(5);
			quickQuote.acceptPopup();
			WaitTool.sleep(5);
			
			manageOrderpage.ExpandMenupage();
			SeleniumFunction.sendKeys(manageOverages.OrderIDTextBox(), orderID);
			SeleniumFunction.sendKeys(manageOverages.TotalBilledTextBox(6), amount);
			manageOverages.TotalBilledTextBox(6).sendKeys(Keys.ENTER);
			WaitTool.sleep(5);
			UseAssert.assertEquals(SeleniumFunction.getText(manageOverages.viewEdit(1)), "View");
		}
	}
}
