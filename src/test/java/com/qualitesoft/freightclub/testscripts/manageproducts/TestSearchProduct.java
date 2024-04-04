package com.qualitesoft.freightclub.testscripts.manageproducts;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestSearchProduct extends InitializeTest {

	@Test 
	public void testSearchProduct() {

		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		String sku=xr.getCellData("Input","Sku", rowIndex).trim();
		String productName=xr.getCellData("Input","ProductName", rowIndex).trim();

		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		SeleniumFunction.click(manageProducts.filterGrid(7));
		SeleniumFunction.sendKeys(manageProducts.filterGrid(7), productName);
		manageProducts.filterGrid(7).sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
		//UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 5)), sku);
		UseAssert.assertEquals(SeleniumFunction.getText(manageProducts.getGridData(1, 7)), productName);
	}
}
