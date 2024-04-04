package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestPagination extends InitializeTest {

	public static void clickPaginationBtnAndVerifyRows(int btnIndex, String pageInput, String rowsNumbers,
			int totalRows) {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		String rowsCount = manageOrderpage.getPagiationTotalRows(keyword);
		String[] num = rowsCount.split(" ");

		if (Integer.parseInt(num[4]) > totalRows) {
			manageOrderpage.clickPaginationButton(keyword, btnIndex);
			WaitTool.sleep(15);
			// ************Verify rows limit on pagination********
			String paginationLimit = manageOrderpage.getPagiationTotalRows(keyword);
			UseAssert.assertEquals(paginationLimit.substring(0, 8), rowsNumbers);
			Log.info("Pagination Limit is >>>" + paginationLimit);

			// **************Verify Input field count***********
			String inputPageCount = manageOrderpage.getPageNumber(keyword);
			UseAssert.assertEquals(inputPageCount, pageInput);

			Log.info("Input page number is >>>" + inputPageCount);

			// *************Verify Number of Rows***********
			int rows = manageOrderpage.getRowsCount(keyword);
			Log.info("Total rows are >>>>>>>>>" + rows);
			UseAssert.assertEquals(rows, 10);
		}
	}

	@Test(priority = 1)
	public void testPagination() {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());

		if (manageOrderpage.acceptFeedbackPopupStatus() == true) {
			manageOrderpage.acceptFeedbackPopup();
		}

		if (keyword.equals("quickOrders") == true) {
			SeleniumFunction.click(manageOrderpage.openQuoteTab());
		} else if (keyword.equals("incompleteGrid") == true) {
			SeleniumFunction.click(manageOrderpage.customOrdersTab());
		}
		WaitTool.sleep(5);

		if (!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}

		String rowsCount = manageOrderpage.getPagiationTotalRows(keyword);
		String[] num = rowsCount.split(" ");

		int rows = manageOrderpage.getRowsCount(keyword);
		Log.info("Total rows are >>>>>>>>>" + rows);

		// ******* Verify initially rows are 10*******
		if (Integer.parseInt(num[4]) > 10) {
			UseAssert.assertEquals(rows, 10);
		} else {
			UseAssert.assertEquals(rows, Integer.parseInt(num[4]));
		}
	}

	@Test(priority = 2)
	public void testLastPageRows() {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		String rowsCount = manageOrderpage.getPagiationTotalRows(keyword);
		String[] num = rowsCount.split(" ");
		Log.info("Pagination Rows count is >>>>>>>>>" + rowsCount);

		int rows = manageOrderpage.getRowsCount(keyword);
		Log.info("Total rows are >>>>>>>>>" + rows);

		if (Integer.parseInt(num[4]) > 10) {
			manageOrderpage.clickPaginationButton(keyword, 4);
			WaitTool.sleep(10);

			char lastNum = rowsCount.charAt(rowsCount.length() - 1);
			int ex_rows = Character.getNumericValue(lastNum);
			int lastPageRows = manageOrderpage.getRowsCount(keyword);

			Log.info("Last num from pagination text >>>>>>>>>" + ex_rows);
			Log.info("Rows count>>>>>>>>>" + lastPageRows);

			if (lastNum != '0') {
				UseAssert.assertEquals(lastPageRows, ex_rows);
			} else {
				UseAssert.assertEquals(lastPageRows, 10);
			}

			String pages = manageOrderpage.getTotalPages(keyword);
			String totalPages = pages.replaceAll("[^?0-9]+", "");
			Log.info("Total pages are >>>>>>>>>" + totalPages);

			String inputPages = manageOrderpage.getPageNumber(keyword);
			UseAssert.assertEquals(inputPages, totalPages);
		} else {
			UseAssert.assertEquals(rows, Integer.parseInt(num[4]));
		}
	}

	@Test(priority = 3)
	public void testFirstPageRows() {
		clickPaginationBtnAndVerifyRows(1, "1", "1 to 10 ", 10);
	}

	@Test(priority = 4)
	public void testNextPageRows() {
		clickPaginationBtnAndVerifyRows(3, "2", "11 to 20", 20);
	}

	@Test(priority = 5)
	public void testPreviousPageRows() {
		clickPaginationBtnAndVerifyRows(2, "1", "1 to 10 ", 10);
	}

	@Test(priority = 6)
	public void verifyRowsLimit() {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		WebElement pageNumInput = manageOrderpage.setPageNumber(keyword);
		SeleniumFunction.sendKeys(pageNumInput, "2");
		pageNumInput.sendKeys(Keys.ENTER);
		WaitTool.sleep(5);
		String inputPage2 = manageOrderpage.getPageNumber(keyword);
		String nextPageRows = manageOrderpage.getPagiationTotalRows(keyword);

		/*
		 * if(keyword.equals("incompleteGrid") == true){
		 * UseAssert.assertEquals(nextPageRows.substring(0, 8).trim(), "1 to 10");
		 * UseAssert.assertEquals(inputPage2, "1"); } else {
		 */
		UseAssert.assertEquals(nextPageRows.substring(0, 8), "11 to 20");
		UseAssert.assertEquals(inputPage2, "2");
		// }
	}
}
