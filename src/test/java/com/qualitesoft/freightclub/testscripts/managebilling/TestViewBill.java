package com.qualitesoft.freightclub.testscripts.managebilling;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestViewBill extends InitializeTest{

	@Test(priority = 1)
	public void verifyDetailsOnViewPopup(){
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			ManageOverages overagesPage = new ManageOverages(driver);
			Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/Upload_Bills.xlsx");

			String documentId = xr1.getCellData("Bill", "Document #", 2);
			String carrierCode = xr1.getCellData("Bill", "Carrier Code", 2);
			String type = xr1.getCellData("Bill", "Type", 2);
			String poNum = xr1.getCellData("Bill", "PO No", 2);

			SeleniumFunction.click(billingPage.manageBillingLink());

			WebElement document = billingPage.documentNumTextBox();
			SeleniumFunction.sendKeys(document, documentId);
			document.sendKeys(Keys.ENTER);
			WaitTool.sleep(20);

			WebElement getMarkupQuote = overagesPage.gridData(1, 9);
			WebElement getCarrierBill = overagesPage.gridData(1, 10);
			WebElement getvariance = overagesPage.gridData(1, 11);

			String ex_carrierbill = SeleniumFunction.getText(getCarrierBill);
			String ex_markupQuote = SeleniumFunction.getText(getMarkupQuote);
			String ex_variance = SeleniumFunction.getText(getvariance);

			SeleniumFunction.click(billingPage.viewBillButton());
			
			try {
				String documentID = SeleniumFunction.getText(billingPage.modalPopupHeader());
				String carrierName = SeleniumFunction.getText(billingPage.getBillDetail(1));
				String orderId = SeleniumFunction.getText(billingPage.getBillDetail(2));
				String carrierbill = SeleniumFunction.getText(billingPage.getBillDetail(3));
				String markupQuote = SeleniumFunction.getText(billingPage.getBillDetail(4));
				String appliedMarkup = SeleniumFunction.getText(billingPage.getBillDetail(5));
				String variance = SeleniumFunction.getText(billingPage.getBillDetail(6));
				String billSource = SeleniumFunction.getText(billingPage.getBillDetail(7));
				String billType = SeleniumFunction.getText(billingPage.getBillDetail(8));
				String gpBillId = SeleniumFunction.getText(billingPage.getBillDetail(10));

				Log.info(appliedMarkup);
				
				UseAssert.assertEquals(documentID, "Order " + documentId);
				UseAssert.assertEquals(carrierName, carrierCode);
				UseAssert.assertEquals(orderId, poNum);
				UseAssert.assertEquals(carrierbill, ex_carrierbill);
				UseAssert.assertEquals(markupQuote, ex_markupQuote);
				UseAssert.assertEquals(variance, ex_variance);
				UseAssert.assertEquals(billSource, "Manual Entry");
				UseAssert.assertEquals(billType, type);
				UseAssert.assertEquals(gpBillId, "");

				SeleniumFunction.click(billingPage.closeModalBtn());
				WaitTool.sleep(3);
				
			}catch(Exception | AssertionError ex) {
				WaitTool.sleep(5);
				if(billingPage.closeModalBtnStatus())
					SeleniumFunction.click(billingPage.closeModalBtn());
				WaitTool.sleep(2);
				if(billingPage.closeModalBtnStatus())
					InitializeTest.getDriver().navigate().refresh();
				throw ex;
			}
	}

	@Test(priority = 2, dependsOnMethods = {"verifyDetailsOnViewPopup"})
	public void setCommentAndChangeTheStatus(){
		ManageBillingPage billingPage = new ManageBillingPage(driver);

		try{
			SeleniumFunction.click(billingPage.viewBillButton());

			SeleniumFunction.sendKeys(billingPage.setComment(), "Test Automation Comment");
			SeleniumFunction.click(billingPage.saveCommentBtn());
			ScreenShot.takeScreenShot(driver, "Successfully saved comment ");
			/*
			 * String successMessage = SeleniumFunction.getText(billingPage.toastMessage());
			 * //Successfully saved comment UseAssert.assertEquals(successMessage,
			 * "Successfully saved comment");
			 */

			String addedComment = SeleniumFunction.getText(billingPage.getAddedCommentText());
			UseAssert.assertEquals(addedComment, "Test Automation Comment");
			WaitTool.sleep(5);

			SeleniumFunction.click(billingPage.iconsOnAddedComment(3, 1));
			SeleniumFunction.sendKeys(billingPage.updateComment(), "QA Automation Comment");
			SeleniumFunction.click(billingPage.iconsOnAddedComment(3, 2));

			/*
			 * WebElement toastMess = billingPage.toastMessage(); String
			 * updateSuccessMessage = SeleniumFunction.getText(toastMess); //Updated saved
			 * comment UseAssert.assertEquals(updateSuccessMessage,
			 * "Updated saved comment");
			 */
			ScreenShot.takeScreenShot(driver, "Updated saved comment ");
			WaitTool.sleep(5);

			String updatedComment = SeleniumFunction.getText(billingPage.getAddedCommentText());
			UseAssert.assertEquals(updatedComment , "QA Automation Comment");

			SeleniumFunction.click(billingPage.iconsOnAddedComment(4, 1));
			ScreenShot.takeScreenShot(driver, "Deleted saved comment ");
			/*
			 * WebElement deleteMess = billingPage.toastMessage(); String deleteSuccessMess
			 * = SeleniumFunction.getText(deleteMess); //Updated saved comment
			 * UseAssert.assertEquals(deleteSuccessMess, "Deleted saved comment");
			 * WaitTool.sleep(5);
			 */

			SeleniumFunction.select(billingPage.setFileStatus(), "Processed");
			SeleniumFunction.select(billingPage.setBillStatus(), "SentToGP");
			SeleniumFunction.sendKeys(billingPage.setGpBillId(), "00000012547896");
			SeleniumFunction.click(billingPage.saveStatusChangeBtn());

			/*
			 * WebElement updateBill = billingPage.toastMessage(); String updateBillMessage
			 * = SeleniumFunction.getText(updateBill); //Updated saved comment
			 * UseAssert.assertEquals(updateBillMessage , "Successfully updated bill");
			 */
			ScreenShot.takeScreenShot(driver, "Successfully updated bill");
			SeleniumFunction.click(billingPage.closeModalBtn());
			WaitTool.sleep(3);

		}catch(Exception | AssertionError ex) {
			WaitTool.sleep(5);
			if(billingPage.closeModalBtnStatus())
				SeleniumFunction.click(billingPage.closeModalBtn());
			WaitTool.sleep(2);
			if(billingPage.closeModalBtnStatus())
				InitializeTest.getDriver().navigate().refresh();
			Assert.fail(ex.getMessage());
		}
	}

	@Test(priority = 3, dependsOnMethods = {"verifyDetailsOnViewPopup", "setCommentAndChangeTheStatus"})
	public void verifyGpNumber(){
		ManageBillingPage billingPage = new ManageBillingPage(driver);

		try{
			SeleniumFunction.click(billingPage.viewBillButton());
			String gpBillId = SeleniumFunction.getText(billingPage.getBillDetail(10));
			UseAssert.assertEquals(gpBillId, "00000012547896");
			SeleniumFunction.click(billingPage.closeModalBtn());
			WaitTool.sleep(3);
		}catch(Exception | AssertionError ex) {
			WaitTool.sleep(5);
			if(billingPage.closeModalBtnStatus())
				SeleniumFunction.click(billingPage.closeModalBtn());
			WaitTool.sleep(2);
			if(billingPage.closeModalBtnStatus())
				InitializeTest.getDriver().navigate().refresh();
			Assert.fail(ex.getMessage());
		}
	}
}

