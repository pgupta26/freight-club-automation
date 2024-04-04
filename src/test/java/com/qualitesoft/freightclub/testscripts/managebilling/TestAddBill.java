package com.qualitesoft.freightclub.testscripts.managebilling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestAddBill extends InitializeTest{
	
	ManageBillingPage billingPage = new ManageBillingPage(driver);

	@Test
	public void addBill(){
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		SeleniumFunction.click(billingPage.manageBillingLink());
		SeleniumFunction.click(billingPage.newBillBtn());
		SeleniumFunction.click(billingPage.addBillBtn());
		String validation = "";
		for(WebElement el : billingPage.createBillPopupValidation() ){
			String text = SeleniumFunction.getText(el);
			validation = validation.concat(text);
		}
		UseAssert.assertEquals(validation, "Date is required.Carrier Code is required.Document Number is required.PO Number is required.Total is required.Freight is required.Type is required.");
		SeleniumFunction.click(billingPage.closeBtn());
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}
	}

	@Test(dataProvider = "billData")
	public void addBillForCeva(Map<Object, Object> mapData) throws ParseException{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			SeleniumFunction.click(billingPage.newBillBtn());
			WaitTool.sleep(5);
			try {
				
				SeleniumFunction.sendKeysAction(driver, billingPage.billDate(), mapData.get("Date").toString());
				SeleniumFunction.sendKeys(billingPage.billDate(), JavaFunction.currentDate());
				SeleniumFunction.click(billingPage.carrierCode());
				SeleniumFunction.click(billingPage.carrierCodeOptions(mapData.get("CarrierCode").toString()));
				SeleniumFunction.sendKeys(billingPage.documentNumber(), mapData.get("DocumentNumber").toString());
				SeleniumFunction.sendKeys(billingPage.poNumber(), mapData.get("PONumber").toString());
				SeleniumFunction.sendKeys(billingPage.netAmountDue(), mapData.get("Total").toString());
				SeleniumFunction.sendKeys(billingPage.freight(), mapData.get("Freight").toString());
				SeleniumFunction.click(billingPage.type());
				WaitTool.sleep(1);

				List<WebElement> listOfBillTypes = billingPage.getListOfBillTypes();
				for(WebElement billType : listOfBillTypes) {
					if(billType.getText().equals(mapData.get("Type").toString())) {
						SeleniumFunction.click(billType);
						break;
					}
				}
				//SeleniumFunction.click(billingPage.typeValue(mapData.get("Type").toString()));
				WaitTool.sleep(2);
				SeleniumFunction.clickJS(driver, billingPage.addBillBtn());
				WebElement toastMess = billingPage.toastMessage();
				String message = SeleniumFunction.getText(toastMess);
				Log.info("Billing Success Message:  "+message);
				UseAssert.assertEquals(message, mapData.get("Toast Message").toString());	
				
			}catch(Exception | AssertionError ex) {
				WaitTool.sleep(5);
				if(billingPage.closeBtnStatus())
					SeleniumFunction.click(billingPage.closeBtn());
				WaitTool.sleep(2);
				if(billingPage.closeBtnStatus())
					InitializeTest.getDriver().navigate().refresh();
				throw ex;
			}
			
			WebElement document = billingPage.documentNumTextBox();
			SeleniumFunction.sendKeys(document, mapData.get("DocumentNumber").toString());
			document.sendKeys(Keys.ENTER);
			WaitTool.sleep(20);

			CommonOps commonops = new CommonOps();
			commonops.searchDocumentIdAndVerifyListing(
					mapData.get("CarrierCode").toString(), mapData.get("Type").toString(), 
					mapData.get("File Status").toString(), mapData.get("EDI Status").toString(), mapData.get("Status").toString(),
					mapData.get("Total").toString(), mapData.get("DocumentNumber").toString());
	}

	@DataProvider(name = "billData")
	public Object[][] RegistrationPageData() throws IOException{
		
		//get order id from quick quote test data sheet
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		String orderId = xr.getCellData("Input","OrderId", 2).trim();
		
		//update order id in manage billing test data sheet
		xr=new Xls_Reader(".\\testdata\\FCfiles\\"+env+"\\ManageBilling\\ManageBilling.xlsx");
		int rowCount = xr.getRowCount("Sheet1");
		for(int i=2; i <=rowCount; i++) {
			xr.setCellData("Sheet1","PONumber", i ,orderId);
			xr.setCellData("Sheet1","DocumentNumber", i ,(orderId+"+"+i));
		}

		File file = new File(".\\testdata\\FCfiles\\"+env+"\\ManageBilling\\ManageBilling.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum() ;
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
		return  obj;
	}
}
