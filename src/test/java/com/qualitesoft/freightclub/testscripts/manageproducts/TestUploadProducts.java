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
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestUploadProducts extends InitializeTest {
	
	@Test
	public void testUploadProducts() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		//Read data from sheet for selected row
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		
		String productName=JavaFunction.randomText("prod", 4);
		Log.info("Product Name: "+productName);
		
		//set product name in excel
		xr.setCellData("Products","Product Name", rowIndex,productName);
		WaitTool.sleep(2);
	    
	    //click select files
	    SeleniumFunction.click(manageProducts.selectFiles());

		//upload greenlist
	    WaitTool.sleep(5);
	    ScreenShot.takeScreenShot(driver, "File Upload Dialog");
	    SeleniumFunction.uploadFile("ManageProducts\\Import_PIDs_Template_V7-R18.xlsm");
		
		//click on upload button
		SeleniumFunction.clickAction(driver, manageProducts.uploadButton());
		WaitTool.sleep(15);
		ScreenShot.takeScreenShot(driver, "File upload results dialog");
		
		//Verify upload success message
		UseAssert.assertEquals(manageProducts.successMessage().getText(), "1 products were successfully uploaded");
		
		SeleniumFunction.click(manageProducts.OKButton());
		WaitTool.sleep(5);

		//verify created product
		String sku=xr.getCellData("Products","Product SKU", rowIndex).trim();
		manageProducts.searchProductDataGrid(productName);
		UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 5)), sku);
		UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 7)), productName);
	}
}
