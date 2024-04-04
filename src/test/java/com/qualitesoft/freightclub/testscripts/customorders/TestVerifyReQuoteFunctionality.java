package com.qualitesoft.freightclub.testscripts.customorders;

import org.testng.*;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageOrderOpenQuotesPage;

public class TestVerifyReQuoteFunctionality extends InitializeTest {
	
	@Test
	public void verifyReQuoteFunctionFunctionality() {

		QuickQuote quickQuote = new QuickQuote(driver);
		ManageOrderOpenQuotesPage openQuotes = new ManageOrderOpenQuotesPage(driver);
		ManagerOrderPage manageOrderpage  = new ManagerOrderPage(driver);
		CommonOps commonOps = new CommonOps();
		
		SeleniumFunction.click(openQuotes.manageOrdersLink());

		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);

		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);

		Xls_Reader xr;
		String testDatasheet= testData;
		xr=new Xls_Reader(testDatasheet);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +Row);

		//filter record by order id
		String orderid=xr.getCellData("Input","OrderId", i).trim();
		commonOps.openManageOrdersPageAndSearchOrder(orderid);

		String orderReferenceID=xr.getCellData("Input","orderReferenceID", i).trim();
		
		SeleniumFunction.click(manageOrderpage.ActionButton());
		SeleniumFunction.click(manageOrderpage.reQuote());
		ScreenShot.takeScreenShot(driver, "Re Quote success message");

		WaitTool.sleep(10);
		SeleniumFunction.click(manageOrderpage.notQuoted());
		Assert.assertEquals(manageOrderpage.customerPO_NotQuoted().getText(),orderReferenceID);
	}

}
