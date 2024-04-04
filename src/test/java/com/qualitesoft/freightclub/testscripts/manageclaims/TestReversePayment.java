package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;
import com.qualitesoft.freightclub.testscripts.TestLogOff;
import com.qualitesoft.freightclub.testscripts.TestSignIn;

import models.manageclaims.CarrierPayment;
import models.manageclaims.CompanyPayment;

public class TestReversePayment extends InitializeTest {
	
	BufferedReader br;
	Gson gson;
	
	
	@Test
	public void testReversePayment() throws FileNotFoundException {
		
		br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		gson = new Gson();
		
		Xls_Reader xr; xr=new Xls_Reader(testData); 
		int i=Integer.parseInt(Row);

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		Log.info("Order ID: "+orderId);


		CommonOps commonOps = new CommonOps(); 
		ManageOverages manageOverages = new ManageOverages(driver); 
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuote quickQuote = new QuickQuote(driver);
		ManageBillingPage billingPage = new ManageBillingPage(driver);


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
			WaitTool.sleep(5);
			
			//click reverse button
			SeleniumFunction.click(manageClaims.reverseButton(2, 11));
			
			//success message verification
			String confirmationMessage = SeleniumFunction.getText(billingPage.toastMessage());
			System.out.println("confirmation message: "+confirmationMessage);
			UseAssert.assertEquals(confirmationMessage, "Payment saved");			
						
			//validate that reverse button will not be visible
			Assert.assertFalse(manageClaims.isReverseButtonPresent(2, 11));
			
			//validate payment details
			if(i == 2) {
				
				CarrierPayment  payment = gson.fromJson(br, CarrierPayment.class);
				
				Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "$-"+payment.getCarrierPaymentAmount());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "$"+payment.getOverpayment());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), payment.getOverpaymentType());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), payment.getCarrierChequeNumber());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), payment.getPaymentSource());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), JavaFunction.currentDateUSFormat().replaceAll("-", "/"));
				Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(9).getText(), "info@freightclub.com");
				
			} else {
			
				CompanyPayment payment = gson.fromJson(br, CompanyPayment.class);

				Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), payment.getPaymentMethod());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "$-"+payment.getCompanyPayoutAmount());
				Assert.assertEquals(manageClaims.paymentHistoryGridData(9).getText(), "info@freightclub.com");
			}
			
			
			//close and navigate to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 
			
		} catch(Exception ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver); 
			throw ex; 
		}
		
		TestLogOff logOff = new TestLogOff();
		logOff.testlogoff();
		
		TestSignIn signIn = new TestSignIn();
		signIn.testSignIn();
		
		//open manage orders page then navigate to claim details
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		
		
		if(i == 2) {
			
			CarrierPayment  payment = gson.fromJson(br, CarrierPayment.class);
			
			//verify claim status field in data grid 
			Assert.assertEquals(manageOverages.gridData(1, 3).getText(), payment.getClaimStatus());
			
		} else {
			
			CompanyPayment payment = gson.fromJson(br, CompanyPayment.class);
			
			//verify claim status field in data grid 
			Assert.assertEquals(manageOverages.gridData(1, 3).getText(), payment.getClaimStatus());
			
			
			Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/"+keyword);
			String companyPaymentAmount = xr1.getCellData("Bulk Payment Update", "Company Payment Amount", 2);
			Assert.assertEquals(manageOverages.gridData(1, 9).getText(), "$"+companyPaymentAmount+"0");
			
		}
	}
}
