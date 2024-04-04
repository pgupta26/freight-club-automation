package com.qualitesoft.freightclub.testscripts.manageproducts;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;

public class TestDownloadProductTemplate extends InitializeTest {
	
	@Test
	public void testDownloadProductTemplate() {
		
		//expected download file name
		String downloadFileName = "download\\Import_PIDs_Template_V7-R18.xlsm";
		File file = new File(downloadFileName);

		//delete file if exist before download
		if(file.exists()) {
			file.delete();
		}
		
		ManageProducts manageProducts = new ManageProducts(driver);
		
		//click on export products in excel
		SeleniumFunction.click(manageProducts.downloadProductTemplate());
		WaitTool.sleep(10);
		
		//verify download template file presence
		if(file.exists()) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("File does not exists");
		}
	}
}
