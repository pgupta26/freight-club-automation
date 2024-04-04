package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.SoftAssertion;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.CarrierMarkups;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

import models.manageclaims.CarrierPayment;
import models.manageclaims.CompanyPayment;

public class TestValidateClaimPaymentAsUser extends InitializeTest {
	
	@Test
	public void testValidationClaimPaymentAsUser() throws FileNotFoundException {
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManageOverages manageOverages = new ManageOverages(driver); 
		CommonOps commonOps = new CommonOps();

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		
		//open manage orders page then navigate to claim details
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		
		try {
			
			if(i == 3) {
				
				CompanyPayment payment = gson.fromJson(br, CompanyPayment.class);

				//verify claim status and internal status fields in data grid 
				Assert.assertEquals(manageOverages.gridData(1, 3).getText(), payment.getClaimStatus());
				
				Assert.assertEquals(manageOverages.gridData(1, 9).getText(), "$"+payment.getCompanyPayoutAmount());
				
				//navigate claims details page 
				SeleniumFunction.clickJS(driver, manageOverages.viewEdit(1));
				
				//switch to manage claims window
				SeleniumFunction.getCurrentWindow(driver);
				WaitTool.sleep(30); 
				quickQuote.acceptPopup();
				
				SoftAssertion.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), payment.getClaimStatus());
				Assert.assertFalse(manageClaims.getField("Claim Status:").isEnabled());
				
			} else  {
				
				CarrierPayment payment = gson.fromJson(br, CarrierPayment.class);
				
				//verify claim status field in data grid 
				Assert.assertEquals(manageOverages.gridData(1, 3).getText(), payment.getClaimStatus());
								
				//navigate claims details page 
				SeleniumFunction.clickJS(driver, manageOverages.viewEdit(1));
				
				//switch to manage claims window
				SeleniumFunction.getCurrentWindow(driver);
				WaitTool.sleep(30); 
				quickQuote.acceptPopup();
				
				SoftAssertion.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), payment.getClaimStatus());
				Assert.assertFalse(manageClaims.getField("Claim Status:").isEnabled());
			}
		} catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 
			throw ex; 
		}
		
		//close and navigate to parent window
		SeleniumFunction.closeWindow(driver);
		SeleniumFunction.getCurrentWindow(driver);
	}
}
