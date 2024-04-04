package com.qualitesoft.freightclub.testscripts.managebilling;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestBillOnListing extends InitializeTest{

	@Test(dataProvider = "billData")
	public void addBillForCeva(Map<Object, Object> mapData) throws ParseException{
		ManageBillingPage billingPage = new ManageBillingPage(driver);

		SeleniumFunction.click(billingPage.manageBillingLink());
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}

		WebElement document = billingPage.documentNumTextBox();
		SeleniumFunction.sendKeys(document, mapData.get("DocumentNumber").toString());
		document.sendKeys(Keys.ENTER);
		WaitTool.sleep(5);

		CommonOps commonops = new CommonOps();
		commonops.searchDocumentIdAndVerifyListing(
				mapData.get("CarrierCode").toString(), mapData.get("Type").toString(), 
				mapData.get("File Status2").toString(), mapData.get("EDI Status").toString(),
				mapData.get("Status2").toString(),
				mapData.get("Total").toString(), mapData.get("DocumentNumber").toString());
	}

	@DataProvider(name = "billData")
	public Object[][] RegistrationPageData() throws IOException{
		File file = new File(".\\binaries\\FCfiles\\ManageBilling\\ManageBilling.xlsx");
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
