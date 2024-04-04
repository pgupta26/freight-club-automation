package com.qualitesoft.freightclub.testscripts.managebilling;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;

public class TestBillsDetails extends InitializeTest{

	@Test
	public void verifyAddedBillsOnOrderDetailPage(){
		try{
			ManagerOrderPage orderPage = new ManagerOrderPage(driver);
			OrderDetailPage detailPage = new OrderDetailPage(driver);
			Xls_Reader xr1=new Xls_Reader("testdata/FCfiles/"+ env +"/ManageBilling/Upload_Bills.xlsx");

			String orderId = xr1.getCellData("Bill", "PO No", 2);
			String documentId = xr1.getCellData("Bill", "Document #", 2);
			String type = xr1.getCellData("Bill", "Type", 2);

			SeleniumFunction.click(orderPage.manageOrdersLink());
			SeleniumFunction.sendKeys(orderPage.orderIdFilter(), orderId);
			orderPage.orderIdFilter().sendKeys(Keys.ENTER);
			WaitTool.sleep(7);

			SeleniumFunction.click(orderPage.ViewDetail());

			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);

			SeleniumFunction.scrollDownByPixel(driver, "300");;
			SeleniumFunction.click(detailPage.adminTab());
			SeleniumFunction.click(detailPage.billsTab());

			String poNum = SeleniumFunction.getText(detailPage.billDetail(documentId, 1));
			String documentNum = SeleniumFunction.getText(detailPage.billDetail(documentId, 2));
			String carrierId = SeleniumFunction.getText(detailPage.billDetail(documentId, 4));
			String billType = SeleniumFunction.getText(detailPage.billDetail(documentId, 10));
			String gpBillId = SeleniumFunction.getText(detailPage.billDetail(documentId, 12));

			UseAssert.assertEquals(poNum, orderId);
			UseAssert.assertEquals(documentNum, documentId);
			UseAssert.assertEquals(carrierId, "44");
			UseAssert.assertEquals(billType, type);
			UseAssert.assertEquals(gpBillId, "00000012547896");
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);

		}catch(Exception | AssertionError e){
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw e;
		}
	}
}
