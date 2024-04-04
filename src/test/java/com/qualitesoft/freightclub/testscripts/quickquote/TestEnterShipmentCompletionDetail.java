package com.qualitesoft.freightclub.testscripts.quickquote;


import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestEnterShipmentCompletionDetail extends InitializeTest {
	
	@Test
	public void testEnterCompleteInformationDetail() {
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String palletType1 = xr.getCellData("Input", "PalletType1", i).trim();

		//add pallet information
		commonOps.addPalletContents(xr, i, 1, palletType1);
		
		//enter shipment information
		commonOps.shipmentCompletion(xr, i);
		
		SeleniumFunction.click(quickQuote.ReviewOrder());
		quickQuote.acceptPopup();
		WaitTool.sleep(10);
		
	}
}
