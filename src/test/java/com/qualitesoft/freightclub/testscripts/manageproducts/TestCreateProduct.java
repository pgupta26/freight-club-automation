package com.qualitesoft.freightclub.testscripts.manageproducts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestCreateProduct extends InitializeTest {
	
	@Test
	public void testCreateProduct() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		
		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		commonOps.addProductDetail(xr, rowIndex);
		commonOps.addProductCarton(xr, rowIndex, 1);
		
		SeleniumFunction.click(manageProducts.saveproduct());
		WaitTool.sleep(1);
		ScreenShot.takeScreenShot(driver, "Saved Product");
		
		//set product name in excel
		xr.setCellData("Input","ProductName", rowIndex,InitializeTest.Productname);
		WaitTool.sleep(2);
		
		//verify created product
		xr=new Xls_Reader(testData);
		String sku=xr.getCellData("Input","Sku", rowIndex).trim();
		String productName=xr.getCellData("Input","ProductName", rowIndex).trim();
		manageProducts.searchProductDataGrid(productName);
		UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 5)), sku);
		UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 7)), productName);
	}
}
