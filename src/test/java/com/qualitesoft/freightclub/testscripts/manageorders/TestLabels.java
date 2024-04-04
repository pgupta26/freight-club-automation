package com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestLabels extends InitializeTest {
	
	@Test(priority=0)
	public void testLabels() throws IOException {

		ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String orderId=xr.getCellData("Input","OrderId", i).trim();

		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderPage.ActionButton());
		SeleniumFunction.click(manageOrderPage.shippingLabelsPdf());
		WaitTool.sleep(10); 
		ScreenShot.takeScreenShot(driver, "Shipping Label PDF for order id "+orderId);

		int totalNumberOfLabels = Integer.parseInt(xr.getCellData("Input","TotalNumberOfLabels", i).trim());
		String fileName = orderId+".pdf";
		commonOps.checkPdfFilePresenceAndPageCount(fileName, totalNumberOfLabels);
	}
}
