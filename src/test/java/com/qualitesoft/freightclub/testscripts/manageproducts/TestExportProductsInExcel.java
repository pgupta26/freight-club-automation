package com.qualitesoft.freightclub.testscripts.manageproducts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestExportProductsInExcel extends InitializeTest {
	
	@Test
	public void testExportProductsInExcel() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		//click on manage products
		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		//click on export products in excel
		SeleniumFunction.click(manageProducts.exportProduct());
		WaitTool.sleep(10);
		
		String sheetName = "download\\Products-"+JavaFunction.currentPSTDate("yyyy-M-d")+".xlsm";
		Xls_Reader xr;
		xr=new Xls_Reader(sheetName);
		int rowIndex=xr.getRowCount("Products") -1;
		Log.info("Total rows excel: "+(rowIndex));

		String rowsCount = SeleniumFunction.getText(manageProducts.paginatioTotalRows());
		Assert.assertTrue(rowsCount.contains(String.valueOf(rowIndex)));
				
	}
}
