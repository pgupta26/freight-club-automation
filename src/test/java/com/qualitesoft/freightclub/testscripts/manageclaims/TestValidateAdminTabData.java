package com.qualitesoft.freightclub.testscripts.manageclaims;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestValidateAdminTabData extends InitializeTest {
	
	@Test
	public void testValidateAdminTabData() {

		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManageOverages manageOverages = new ManageOverages(driver); 
		CommonOps commonOps = new CommonOps();

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String amount=xr.getCellData("Input","Amount", i).trim();

		//open manage orders page then navigate to claim details
		commonOps.openManageClaimsPageAndSearchOrder(orderId);

		//navigate claims details page 
		SeleniumFunction.clickJS(driver, manageOverages.viewEdit(1));

		try {

			//switch to manage claims window
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(30); 
			quickQuote.acceptPopup();

			//click payment tab
			SeleniumFunction.click(manageClaims.adminTab());
			
			//validate order invoices fields
			
			Xls_Reader xr1 = new Xls_Reader(testData);
			int rowIndex = 2;
			Log.info("Row Number: "+rowIndex);	

			String contactType = xr1.getCellData("ContactInfo", "ContactType", rowIndex);
			String contactName = xr1.getCellData("ContactInfo", "ContactName", rowIndex);
			String phoneNumber = xr1.getCellData("ContactInfo", "PhoneNumber", rowIndex);
			String emailAddress = xr1.getCellData("ContactInfo", "EmailAddress", rowIndex);
			
			//Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 1).getText(), );
			//Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 2).getText(), );
			Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 3).getText(), "On Account");
			//Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 4).getText(), );
			//Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 5).getText(), );
			Assert.assertEquals(manageClaims.getOrderInvoicesField(1, 6).getText(), amount);
			
			//validate client information fields
			Assert.assertEquals(manageClaims.getClientInformationField(1, 1).getText(), contactType);
			Assert.assertEquals(manageClaims.getClientInformationField(1, 2).getText(), contactName);
			Assert.assertEquals(manageClaims.getClientInformationField(1, 3).getText(), phoneNumber);
			Assert.assertEquals(manageClaims.getClientInformationField(1, 4).getText(), "");
			Assert.assertEquals(manageClaims.getClientInformationField(1, 5).getText(), emailAddress);
			
			//validate other fields on the page
			Assert.assertEquals(manageClaims.LTLMarkupType().getAttribute("value").trim(), "InPercentage");
			Assert.assertEquals(manageClaims.LTLMarkup().getAttribute("value").trim(), "19");
			Assert.assertEquals(manageClaims.groundMarkupType().getAttribute("value").trim(), "InPercentage");
			Assert.assertEquals(manageClaims.groundMarkup().getAttribute("value").trim(), "26");
			Assert.assertEquals(manageClaims.adminNotes().getText(), "");
			
			//close and navigate to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 

		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 
			throw ex; 
		}

	}

}
