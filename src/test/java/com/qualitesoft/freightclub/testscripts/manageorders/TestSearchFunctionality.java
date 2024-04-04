package com.qualitesoft.freightclub.testscripts.manageorders;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestSearchFunctionality extends InitializeTest{

	public static void searchOnManageOrders(String searchIndex, String index2, String colName,int rowNum){
		try{
			Xls_Reader xr=new Xls_Reader(testData);
			String searchData = xr.getCellData("Input",colName, rowNum).trim();

			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			SeleniumFunction.click(manageOrderpage.manageOrdersLink());
			WaitTool.sleep(7);

			manageOrderpage.ActionButton();
			if(!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
				SeleniumFunction.click(manageOrderpage.ExpandMenupage());
			}

			SeleniumFunction.sendKeys(manageOrderpage.searchFields(searchIndex), searchData);
			manageOrderpage.searchFields(searchIndex).sendKeys(Keys.ENTER);
			WaitTool.sleep(15);

			int rows = manageOrderpage.getRowsCount("fullOrders");
			UseAssert.assertEquals(rows, 1);
			UseAssert.assertEquals(manageOrderpage.getColumnData(index2), searchData);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Test(priority = 1)
	public void searchByOrderId(){
		searchOnManageOrders("1", "2", "OrderId", 2);
	}

	@Test(priority = 2)
	public void searchByWaybill(){
		searchOnManageOrders("3", "9", "WayBill", 2);
	}

	@Test(priority = 3)
	public void searchByTrackingNumber(){
		searchOnManageOrders("4", "10", "Tracking#", 2);
	}

	@Test(priority = 4)
	public void verifyFilterHeaderText(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
			SeleniumFunction.click(manageOrderpage.manageOrdersLink());
			manageOrderpage.ActionButton();
			WaitTool.sleep(5);
			ArrayList<String> headers = new ArrayList<String>();
			ArrayList<String> ex_header = new ArrayList<String>(Arrays.asList("ID", "Customer PO #", "Booked Date"
					,"Ready for Pick Up", "Status", "Cancel Requested","Type", "Way Bill #","Tracking #", "Quoted Price"));

			for(int i=0; i<10; i++){
				headers.add(i, manageOrderpage.getFilterHeader(i + 1));
			}
			Log.info("Actual Array is " + headers + "\n expected is " + ex_header);
			Assert.assertEquals(headers, ex_header);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}

