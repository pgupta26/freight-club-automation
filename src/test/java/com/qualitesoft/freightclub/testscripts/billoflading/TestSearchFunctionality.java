package com.qualitesoft.freightclub.testscripts.billoflading;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.BillOfLadingPage;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestSearchFunctionality extends InitializeTest{

	@Test(priority = 1)
	public void searchByWayBill(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		searchingOnBolPage(1, "WayBill", 2);
		UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 1);
	}
	
	@Test(priority = 2)
	public void searchByCustomerPo(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		searchingOnBolPage(2, "orderReferenceID", 2);
		String pagerInfo = SeleniumFunction.getText(bolPage.getPageInfo());
		String[] num = pagerInfo.split(" ");
		int totalRows = Integer.parseInt(num[4]);
		if(totalRows >= 15){
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
		}
		else{
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), totalRows);
		}
	}
	
	@Test(priority = 3)
	public void searchByOrderId(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		searchingOnBolPage(3, "OrderId", 2);
		UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 1);
	}
	
	public static void searchingOnBolPage(int index, String colName, int rowNum){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		
		Xls_Reader xr=new Xls_Reader(testData);
		String searchData = xr.getCellData("Input",colName, rowNum).trim();

		SeleniumFunction.clickJS(driver, bolPage.billOfLading());
		WaitTool.sleep(5);

		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}
		SeleniumFunction.sendKeys(bolPage.searchTextBox(index), searchData);
		bolPage.searchTextBox(index).sendKeys(Keys.ENTER);
		WaitTool.sleep(7);
		String orderStatus = SeleniumFunction.getText(bolPage.gridData(1, index));
		UseAssert.assertEquals(orderStatus, searchData);
	}
}
