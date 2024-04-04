package com.qualitesoft.freightclub.testscripts.managebilling;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestDeleteBill extends InitializeTest{

	@Test(priority=1)
	public void verifyDeleteBtnDisabled(){
		try{

			ManageBillingPage billingPage = new ManageBillingPage(driver);
			searchDocumentId(2);
			
			String deleteBtnStatus = SeleniumFunction.getAttribute(billingPage.deleteButton(), "class");
			if(deleteBtnStatus.contains("disabled")){
				Assert.assertTrue(true, "Delete button is not disabled");
			}else{
				Assert.assertFalse(false, "Delete button is disabled");
			}
		}catch(Exception e){
			throw e;
		}
	}

	@Test(priority=2, dependsOnMethods = {"verifyDeleteBtnDisabled"})
	public void deleteBill(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			ManagerOrderPage orderPage = new ManagerOrderPage(driver);
			
			searchDocumentId(3);
			SeleniumFunction.click(billingPage.deleteButton());
			String header = SeleniumFunction.getText(orderPage.getCancelOrderPopupHeader());
			SeleniumFunction.getText(orderPage.getCancelOrderPopupBody());
			SeleniumFunction.click(orderPage.confirmCancelOrderBtn());
			
			UseAssert.assertEquals(header, "Please, confirm this action");
			String deleteBillSuccessMessage = SeleniumFunction.getText(billingPage.toastMessage());
			UseAssert.assertEquals(deleteBillSuccessMessage, "Successfully removed bill.");
		}catch(Exception e){
			throw e;
		}
	}
	
	public static void searchDocumentId(int row){
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/Upload_Bills.xlsx");
		String documentId = xr1.getCellData("Bill", "Document #", row);
		
		SeleniumFunction.click(billingPage.manageBillingLink());
		WebElement document = billingPage.documentNumTextBox();
		SeleniumFunction.sendKeys(document, documentId);
		document.sendKeys(Keys.ENTER);
		WaitTool.sleep(15);
	}
}
