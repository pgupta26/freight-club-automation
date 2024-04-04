package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qualitesoft.core.InitializeTest;
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

import models.manageclaims.CarrierPayment;
import models.manageclaims.CompanyPayment;


public class TestImportCarrierClaimPayment extends InitializeTest {
	
	@Test
	public void testImportCarrierClaimPayment() throws FileNotFoundException {
		
		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		
		ManageClaims manageClaims = new ManageClaims(driver);
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		String paymentType;

		SeleniumFunction.click(manageClaims.manageClaimsLink());
		WaitTool.sleep(10);
		
		SeleniumFunction.click(manageClaims.importClaimPayments());
		SeleniumFunction.uploadFile("ManageClaims\\"+keyword);
		WaitTool.sleep(2);
		SeleniumFunction.click(billingPage.uploadBtn());
		
		String confirmationMessage = SeleniumFunction.getText(billingPage.toastMessage());
		System.out.println("confirmation message: "+confirmationMessage);
		UseAssert.assertEquals(confirmationMessage, "Claim Payments successfully imported");
			
		Xls_Reader xr; xr=new Xls_Reader(testData); 
		int i=Integer.parseInt(Row);

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		Log.info("Order ID: "+orderId);


		CommonOps commonOps = new CommonOps(); 
		ManageOverages manageOverages = new ManageOverages(driver); 
		QuickQuote quickQuote = new QuickQuote(driver);

		//navigate to manage claims page and search order ID
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		
		//verify claim status and internal status fields in data grid
		if(i == 2) {
			CarrierPayment payment = gson.fromJson(br, CarrierPayment.class);
			Assert.assertEquals(manageOverages.gridData(1, 5).getText(), payment.getClaimStatus());
			Assert.assertEquals(manageOverages.gridData(1, 7).getText(), payment.getInternalStatus());

		} else {
			CompanyPayment payment = gson.fromJson(br, CompanyPayment.class);
			Assert.assertEquals(manageOverages.gridData(1, 5).getText(), payment.getClaimStatus());
			Assert.assertEquals(manageOverages.gridData(1, 7).getText(), payment.getInternalStatus());
		}
		
		
		//navigate claims details page 
		SeleniumFunction.clickJS(driver, manageOverages.viewEdit(1));

		try {

			//switch to manage claims window
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(30); 
			quickQuote.acceptPopup();

			//click payment tab
			SeleniumFunction.click(manageClaims.paymentTab());
			
			//validate payment history fields
			ArrayList<String> expectedPaymentHistoryFields = new ArrayList<>();
			Collections.addAll(expectedPaymentHistoryFields, "Carrier Payment Amount","Overpayment","Overpayment Type","Carrier Cheque #",
					"Payment Source","Payment Date","Payment Method","Company Payout Amount","User","Date and Time","");
			
			List<String> actualPaymentHistoryFields = new ArrayList<>();
			
			for(WebElement element : manageClaims.paymentHistoryFields()) {
				actualPaymentHistoryFields.add(element.getText());
			}
			
			Assert.assertEquals(actualPaymentHistoryFields, expectedPaymentHistoryFields);
			
			Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageClaims/"+keyword);
			paymentType = xr1.getCellData("Bulk Payment Update", "Payment Type", 2);
			
			if(paymentType.equals("Company")) {
				
				String companyPaymentAmount = xr1.getCellData("Bulk Payment Update", "Company Payment Amount", 2);
				
				//validate payment details
				Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "$0.00");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "$0.00");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), "Credit Memo");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "$"+companyPaymentAmount+"0");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(9).getText(), InitializeTest.fcusername);
				
			} else {
				
				String carrierPaymentAmount = xr1.getCellData("Bulk Payment Update", "Carrier Payment Amount", 2);
				String overpayment = xr1.getCellData("Bulk Payment Update", "Overpayment", 2);
				String overpaymentType = xr1.getCellData("Bulk Payment Update", "Overpayment Type", 2);
				String carrierChequeNumber = xr1.getCellData("Bulk Payment Update", "Carrier Cheque Number", 2);
				String paymentSource = xr1.getCellData("Bulk Payment Update", "Payment Source", 2);
				String paymentDate = xr1.getCellData("Bulk Payment Update", "Payment Date", 2);
				

				//validate payment details
				Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "$"+carrierPaymentAmount+"0");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "$"+overpayment+"0");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), overpaymentType);
				Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), carrierChequeNumber);
				Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), paymentSource);
				Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), paymentDate);
				Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "");
				Assert.assertEquals(manageClaims.paymentHistoryGridData(9).getText(), InitializeTest.fcusername);
				
			}
			
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
