package com.qualitesoft.freightclub.testscripts.manageproducts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestViewEditProduct extends InitializeTest {
	
	@Test
	public void testViewEditProduct() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);

		//edit product
		SeleniumFunction.click(manageProducts.actions());
		SeleniumFunction.click(manageProducts.editSku());
		ScreenShot.takeScreenShot(driver, "Before update product detail");
		
		//update product name
		Productname=JavaFunction.randomText("prod", 4);
		Log.info("Updateed Product Name: "+Productname);
		SeleniumFunction.sendKeys(manageProducts.productName(),Productname);
		
		//add new carton
		SeleniumFunction.click(quickQuote.addadditionalItem());
		commonOps.addProductCarton(xr, rowIndex, 2);
		ScreenShot.takeScreenShot(driver, "After update product detail");
		
		//click save product button
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
