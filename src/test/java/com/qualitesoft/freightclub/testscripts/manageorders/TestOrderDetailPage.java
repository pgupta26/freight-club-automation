package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;

public class TestOrderDetailPage extends InitializeTest{

	@Test
	public void verifyOrderIdOnDetailPage(){

		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		OrderDetailPage orderDetailPage = new OrderDetailPage(driver);

		SeleniumFunction.click(manageOrderpage.manageOrdersLink());
		WaitTool.sleep(7);

		if(keyword.equals("quickOrders") == true){
			SeleniumFunction.click(manageOrderpage.openQuoteTab());
		}else if(keyword.equals("incompleteGrid") == true){
			SeleniumFunction.click(manageOrderpage.customOrdersTab());
		}

		String orderId;
		if(keyword.equals("incompleteGrid") == true){
			orderId = SeleniumFunction.getText(manageOrderpage.getOrderIdIncompleteOrderPage(keyword));
		} else {
			orderId = SeleniumFunction.getText(manageOrderpage.getOrderId(keyword));
		}
		SeleniumFunction.sendKeys(manageOrderpage.searchFieldsOnTable(keyword, "1"), orderId.trim());
		manageOrderpage.searchFieldsOnTable(keyword, "1").sendKeys(Keys.ENTER);
		WaitTool.sleep(5);

		if(keyword.equals("fullOrders")){
			SeleniumFunction.clickJS(driver, manageOrderpage.ViewDetail());
		}else if(keyword.equals("quickOrders")){
			SeleniumFunction.click(manageOrderpage.viewDetalsOpenQuotes());
		}else if(keyword.equals("incompleteGrid")){
			SeleniumFunction.click(manageOrderpage.viewDetalsCustomOrders());
		}
		
		try{
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);

			String actualOrderId = SeleniumFunction.getText(orderDetailPage.orderId());
			UseAssert.assertEquals(actualOrderId, orderId);
			ScreenShot.takeScreenShot(driver, "ViewDetailsPageOfOpenQuoteOrders");

			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception e){
			e.getMessage();
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.fail(e.getMessage());
		}
	}
}
