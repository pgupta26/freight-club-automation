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
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

import models.manageclaims.CarrierPayment;


public class TestCreateCarrierPayment extends InitializeTest {

	@Test
	public void testCreateCarrierPayment() throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader("./jsondata/"+dataFile));
		Gson gson = new Gson();
		CarrierPayment payment = gson.fromJson(br, CarrierPayment.class);
		payment.setPaymentDate(JavaFunction.currentDateUSFormat());

	
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
			manageClaims.selectByVisibleText("Payment Source", payment.getPaymentSource());
			SeleniumFunction.sendKeys(manageClaims.getField("Carrier Payment Amount"), payment.getCarrierPaymentAmount());
			manageClaims.selectByVisibleText("Overpayment Type", payment.getOverpaymentType());
			SeleniumFunction.sendKeys(manageClaims.getField("Overpayment"), payment.getOverpayment());
			SeleniumFunction.sendKeys(manageClaims.getField("Carrier Cheque Number"), payment.getCarrierChequeNumber()); 
			SeleniumFunction.sendKeys(manageClaims.getField("Payment Date"), payment.getPaymentDate());
			SeleniumFunction.scrollDownByPixel(driver, "200");
			SeleniumFunction.click(manageClaims.save());
			WaitTool.sleep(5);
			
			
			//validate payment history fields
			ArrayList<String> expectedPaymentHistoryFields = new ArrayList<>();
			Collections.addAll(expectedPaymentHistoryFields, "Carrier Payment Amount","Overpayment","Overpayment Type","Carrier Cheque #",
					"Payment Source","Payment Date","Payment Method","Company Payout Amount","User","Date and Time","");
			
			List<String> actualPaymentHistoryFields = new ArrayList<>();
			
			for(WebElement element : manageClaims.paymentHistoryFields()) {
				actualPaymentHistoryFields.add(element.getText());
			}
			
			Assert.assertEquals(actualPaymentHistoryFields, expectedPaymentHistoryFields);
			

			//validate payment details
			Assert.assertEquals(manageClaims.paymentHistoryGridData(1).getText(), "$"+payment.getCarrierPaymentAmount());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(2).getText(), "$"+payment.getOverpayment());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(3).getText(), payment.getOverpaymentType());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(4).getText(), payment.getCarrierChequeNumber());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(5).getText(), payment.getPaymentSource());
			Assert.assertEquals(manageClaims.paymentHistoryGridData(6).getText(), payment.getPaymentDate().replaceAll("-", "/"));
			Assert.assertEquals(manageClaims.paymentHistoryGridData(7).getText(), "");
			Assert.assertEquals(manageClaims.paymentHistoryGridData(8).getText(), "");
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
