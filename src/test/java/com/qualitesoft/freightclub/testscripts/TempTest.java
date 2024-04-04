package com.qualitesoft.freightclub.testscripts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.BillOfLadingPage;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageInvoices;
import com.qualitesoft.freightclub.pageobjects.ManageLocations;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TempTest extends InitializeTest {
	
	
	
	
	@Test
	public void tempTest() {
		
		QuickQuoteFinal qq = new QuickQuoteFinal(driver);
		ManagerOrderPage manageOrderPage = new ManagerOrderPage(driver);
		BillOfLadingPage billOfLadingPage = new BillOfLadingPage(driver);
		ManageInvoices mangeInvoices = new ManageInvoices(driver);
		ManageOverages manageOverages = new ManageOverages(driver);
		
		ManageClaims manageCliams = new ManageClaims(driver);
		
		ManageLocations manageLocations = new ManageLocations(driver);
		ManageProducts manageProducts = new ManageProducts(driver);
		
		
		boolean flag = true;
		
		while(flag) {
			
			SeleniumFunction.click(qq.quickQuoteLink());
			WaitTool.sleep(90);
			
			SeleniumFunction.click(manageOrderPage.manageOrdersLink());
			WaitTool.sleep(120);
			
			SeleniumFunction.click(billOfLadingPage.billOfLading());
			WaitTool.sleep(90);
			
			SeleniumFunction.click(mangeInvoices.manageInvoices());
			WaitTool.sleep(120);
			
			SeleniumFunction.click(manageOverages.manageOverages());
			WaitTool.sleep(150);
			
			SeleniumFunction.click(manageCliams.manageClaimsLink());
			WaitTool.sleep(200);
			
			SeleniumFunction.click(manageLocations.manageLocationsLink());
			WaitTool.sleep(250);
			
			SeleniumFunction.click(manageProducts.manageProductLink());
			WaitTool.sleep(300);
			
		}
		
	}

}
