package com.qualitesoft.freightclub.testscripts.managebilling;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageBillingPage;

public class TestPagination extends InitializeTest{

	@Test(priority = 1)
	public void verifyPaginationBtnStatusOnFirstPage(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			ManageOverages overagesPage = new ManageOverages(driver);
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

			SeleniumFunction.click(billingPage.manageBillingLink());
			overagesPage.gridData(1, 1);
			
			if(manageOrderpage.acceptFeedbackPopupStatus() == true){
				manageOrderpage.acceptFeedbackPopup();
			}
			SeleniumFunction.scrollDownUptoFooter(driver);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords());
			String paginationText = SeleniumFunction.getText(billingPage.pagiationSummaryText());

			if(Integer.parseInt(totalRecords.replace(",", "")) > 10){
				getPaginationBtnStatus(false, false, true, true);
				UseAssert.assertEquals(paginationText, "1 to 10 of " + totalRecords);
			}else{
				getPaginationBtnStatus(false, false, false, false);
				UseAssert.assertEquals(paginationText, "1 to "+ totalRecords +" of " + totalRecords);
			}
			
			
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void verifyRowsCountOnFirstPage(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			ManagerOrderPage orderPage = new ManagerOrderPage(driver);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords()).replace(",", "");
			int rows = orderPage.getRowsCount("borderLayout_eRootPanel");
			Log.info("Actual number of rows are " + rows);
			
			if(Integer.parseInt(totalRecords) > 10){
				UseAssert.assertEquals(rows, 10);
			}else{
				UseAssert.assertEquals(rows, Integer.parseInt(totalRecords));
			}
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 3)
	public void verifyPaginationBtnStatusOnSecondPage(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords());
			Log.info("Total records are + " + totalRecords);
			
			if(Integer.parseInt(totalRecords.replace(",", "")) > 10){
				SeleniumFunction.click(billingPage.paginationButton(3));
				WaitTool.sleep(10);
				String paginationText = SeleniumFunction.getText(billingPage.pagiationSummaryText());
				getPaginationBtnStatus(true, true, true, true);
				UseAssert.assertEquals(paginationText, "11 to 20 of " + totalRecords);
			}else{
				getPaginationBtnStatus(false, false, false, false);
			}
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 4)
	public void verifyPaginationBtnStatusOnLastPage(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords()).replace(",", "");
			if(Integer.parseInt(totalRecords) > 10){
				SeleniumFunction.click(billingPage.paginationButton(4));
				WaitTool.sleep(10);
				getPaginationBtnStatus(true, true, false, false);
			}else{
				getPaginationBtnStatus(false, false, false, false);
			}
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 5)
	public void verifyRowsCountOnLastPage(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			ManagerOrderPage orderPage = new ManagerOrderPage(driver);
			
			SeleniumFunction.click(billingPage.paginationButton(4));
			WaitTool.sleep(7);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords()).replace(",", "");
			int rows = orderPage.getRowsCount("borderLayout_eRootPanel");
			int totalCount = Integer.parseInt(totalRecords);
			Log.info("Actual number of rows on last page are + " + rows);
			
			if(totalCount > 10){
				if(totalCount%10 == 0){
					UseAssert.assertEquals(rows, 10);
				}else{
					UseAssert.assertEquals(rows, totalCount%10);
				}
			}else{
				UseAssert.assertEquals(rows, totalCount);
			}
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 6)
	public void verifyTotalPagesCount(){
		try{
			ManageBillingPage billingPage = new ManageBillingPage(driver);
			
			String totalRecords = SeleniumFunction.getText(billingPage.getTotalRecords()).replace(",", "");
			int totalCount = Integer.parseInt(totalRecords);
			String totalPages = SeleniumFunction.getText(billingPage.totalPages()).replace(",", "");
			int actualPages = Integer.parseInt(totalPages);
			int expectedPages = totalCount/10;
			Log.info("Actual number of pages are " + actualPages);
			
			if(totalCount > 10){
				if(totalCount%10 >=1){
					UseAssert.assertEquals(actualPages, expectedPages + 1);
				}else{
					UseAssert.assertEquals(actualPages, expectedPages);
				}
			}else{
				UseAssert.assertEquals(actualPages, 1);
			}
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	public static void getPaginationBtnStatus(boolean ex_status1, boolean ex_status2, boolean ex_status3, boolean ex_status4){
		ManageBillingPage billingPage = new ManageBillingPage(driver);
		
		boolean firstBtnStatus = billingPage.paginationButton(1).isEnabled();
		boolean previousBtnStatus = billingPage.paginationButton(2).isEnabled();
		boolean nextBtnStatus = billingPage.paginationButton(3).isEnabled();
		boolean lastBtnStatus = billingPage.paginationButton(4).isEnabled();
		
		UseAssert.assertEquals(firstBtnStatus, ex_status1);
		UseAssert.assertEquals(previousBtnStatus, ex_status2);
		UseAssert.assertEquals(nextBtnStatus, ex_status3);
		UseAssert.assertEquals(lastBtnStatus, ex_status4);
	}
}
