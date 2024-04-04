package com.qualitesoft.freightclub.testscripts.billoflading;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.BillOfLadingPage;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestPaginationOnBol extends InitializeTest{

	@Test(priority = 1)
	public void verifyRowsCount(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		SeleniumFunction.clickJS(driver, bolPage.billOfLading());
		WaitTool.sleep(5);

		if(manageOrderpage.acceptFeedbackPopupStatus() == true){
			manageOrderpage.acceptFeedbackPopup();
		}
		WaitTool.sleep(5);
		int rowsCount = bolPage.getNumberOfRows().size();
		if(rowsCount == 15){
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
			String paginationLimit= SeleniumFunction.getText(bolPage.getPageInfo());
			UseAssert.assertEquals(paginationLimit.substring(0, 7), "1 - 15 ");
		}
		else{
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), rowsCount);
		}
	}
	
	@Test(priority = 2)
	public void verifyLastPageRows(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		String pagerInfo = SeleniumFunction.getText(bolPage.getPageInfo());
		String[] num = pagerInfo.split(" ");
		SeleniumFunction.clickJS(driver,bolPage.goToLastPage());
		WaitTool.sleep(10);
		int totalRows = Integer.parseInt(num[4]);
		int rem = totalRows%15;
		if(rem == 0){
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
		}
		else{
			UseAssert.assertEquals(bolPage.getNumberOfRows().size(), rem);
		}
	}
	
	@Test(priority = 3)
	public void verifySecondLastPageRows(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		SeleniumFunction.clickJS(driver,bolPage.goToPreviousPage());
		WaitTool.sleep(10);
		UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
	}
	
	@Test(priority = 4)
	public void verifyFirstPageRows(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		SeleniumFunction.clickJS(driver,bolPage.goToFirstPage());
		WaitTool.sleep(10);
		UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
	}
	
	@Test(priority = 5)
	public void verifyBtnStatusOnSecondPage(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		SeleniumFunction.clickJS(driver, bolPage.goToNextPage());
		WaitTool.sleep(10);
		String paginationLimit= SeleniumFunction.getText(bolPage.getPageInfo());
		UseAssert.assertEquals(paginationLimit.substring(0, 7), "16 - 30");
		
	}
	
	@Test(priority = 6)
	public void verifyRowsOnRandomPage(){
		BillOfLadingPage bolPage = new BillOfLadingPage(driver);
		SeleniumFunction.clickJS(driver, bolPage.paginationBtn(5));
		WaitTool.sleep(10);
		UseAssert.assertEquals(bolPage.getNumberOfRows().size(), 15);
		String paginationLimit= SeleniumFunction.getText(bolPage.getPageInfo());
		UseAssert.assertEquals(paginationLimit.substring(0, 7), "61 - 75");
	}
}

