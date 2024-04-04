package com.qualitesoft.freightclub.testscripts.managebilling;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestExportBills extends InitializeTest {

	@Test
	public void exportAllBillsAndVerifyCount() throws IOException{
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		SeleniumFunction.click(billingPage.manageBillingLink());

		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}

		
		String totalRecords =  SeleniumFunction.getText(billingPage.totalPages()).trim().replaceAll(",", "");  
		
		if(Integer.parseInt(totalRecords) > 300 ) {
			String expectedMsg = "Report in Progress We’re busy putting together your export. As soon as it is ready it will be sent to: info@freightclub.com";

			SeleniumFunction.click(billingPage.exportCsvBtn());
			String toastMessage = WaitTool.waitForElementPresentAndDisplay(driver, By.id("toast-container"), 50).getText();
			toastMessage = toastMessage.replaceAll("[\\t\\n\\r]+"," ");
			System.out.println(toastMessage);
			Assert.assertEquals(toastMessage, expectedMsg);
		} else {
			SeleniumFunction.click(billingPage.exportCsvBtn());
			WaitTool.sleep(10);

			String sheetName = JavaFunction.currentDateFormat("yyyy-M-d");
			int numOfRows = JavaFunction.countLineBufferedReader(System.getProperty("user.dir") +File.separator+"download\\" + "Bill" +  sheetName + ".csv");

			UseAssert.assertEquals(Integer.parseInt(totalRecords)+1, numOfRows);
		}
	}

	@Test
	public void exportSearchedBillsAndVerifyCount() throws IOException{
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		SeleniumFunction.click(billingPage.manageBillingLink());
		Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/ManageBilling.xlsx");
		String poNum = xr1.getCellData("Sheet1", "PONumber", 2);

		WebElement orderId = billingPage.orderNumTextBox();
		SeleniumFunction.sendKeys(orderId, poNum);
		orderId.sendKeys(Keys.ENTER);
		WaitTool.sleep(15);

		SeleniumFunction.click(billingPage.exportCsvBtn());
		WaitTool.sleep(10);

		String sheetName = JavaFunction.currentDateFormat("yyyy-M-d");
		int numOfRows = JavaFunction.countLineBufferedReader(System.getProperty("user.dir") +File.separator+"download\\" + "Bill" +  sheetName + " (1).csv");

		String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords()).replace(",", "");

		UseAssert.assertEquals(Integer.parseInt(totalRecords)+1, numOfRows);

		SeleniumFunction.click(billingPage.importFileLink());
		WaitTool.sleep(5);
	}

}
