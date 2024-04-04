package com.qualitesoft.freightclub.testscripts.manageproducts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestUserIsAbleToCreateNonPalletizedSku extends InitializeTest {
	
	@Test
	public void testUserIsAbleToCreateNonPalletizedSku() {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr=new Xls_Reader("testdata/FCfiles/"+env+"/ManageProducts/ManageProducts.xlsx");
		
		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		
		commonOps.addProductDetail(xr, 2);
		commonOps.addProductCarton(xr, 2, 1);
		
		SeleniumFunction.click(manageProducts.saveproduct());
		
		//set product name in excel
		xr.setCellData("Input","ProductName", 2,InitializeTest.Productname);
		WaitTool.sleep(2);

	}
}
