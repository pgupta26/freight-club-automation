package com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestEnterShipmentInformation extends InitializeTest {
	
	@Test
	public void testEnterShipmentInformation() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String packageType = xr.getCellData("Input","packageType", i).trim();
		
		commonOps.shipmentInformation(xr,i);
		commonOps.itemInformation(xr, i, 1, packageType);
		WaitTool.sleep(2);
		SeleniumFunction.scrollDownUptoFooter(driver);
		quickQuote.acceptPopup();
		SeleniumFunction.click(quickQuote.SaveButton());
		WaitTool.sleep(20);
	}
}
