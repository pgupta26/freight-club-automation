package com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestBOL extends InitializeTest {

	@Test
	public void testBOL() throws IOException {

		ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);
		CommonOps commonOps = new CommonOps();
		Xls_Reader xr=new Xls_Reader(testData); int
		i=Integer.parseInt(Row);
		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String bolCount=xr.getCellData("Input","BOLCount", i).trim();
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();

		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderPage.ActionButton());
		if(shipmentType.equals("Parcel")) {
			Assert.assertFalse(manageOrderPage.IsbillOfLoadingPdfPresent());
			ScreenShot.takeScreenShot(driver, "Download BOL not visible");
			SeleniumFunction.click(manageOrderPage.ActionButton());
		} else {
			SeleniumFunction.click(manageOrderPage.billOfLoadingPdf());
			WaitTool.sleep(10); 
			ScreenShot.takeScreenShot(driver, "BillofLadingPDF for order id "+orderId);
			String fileName = orderId+"BOL.pdf";
			commonOps.checkPdfFilePresenceAndPageCount(fileName, Integer.parseInt(bolCount));
		}	
	}
}
