package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

import models.manageclaims.CompanyPayment;


public class TestCreateCompanyPayment extends InitializeTest {

	@Test
	public void testCreateCompanyPayment() throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		CompanyPayment payment = gson.fromJson(br, CompanyPayment.class);

		Xls_Reader xr; xr=new Xls_Reader(testData); 
		int i=Integer.parseInt(Row);

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		Log.info("Order ID: "+orderId);


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

			//click payment tab
			SeleniumFunction.click(manageClaims.paymentTab());

			//create payment
			manageClaims.selectByVisibleText("Payment Type", payment.getPaymeneType());
			manageClaims.selectByVisibleText("Payment Method", payment.getPaymentMethod());
			SeleniumFunction.sendKeys(manageClaims.getField("Company Payout Amount"), payment.getCompanyPayoutAmount());
			SeleniumFunction.click(manageClaims.save());
			WaitTool.sleep(5);


			//validate payment details
			Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), payment.getPaymentMethod());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "$"+payment.getCompanyPayoutAmount());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(9).getText(), InitializeTest.fcusername);

			//close and navigate to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 

		}catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 
			throw ex; 
		}


		//navigate to manage claims page and search order ID
		commonOps.openManageClaimsPageAndSearchOrder(orderId);

		//verify claim status and internal status fields in data grid 
		Assert.assertEquals(manageOverages.gridData(1, 5).getText(), payment.getClaimStatus());
		Assert.assertEquals(manageOverages.gridData(1, 7).getText(), payment.getInternalStatus()); 
	}

}
