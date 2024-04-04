package com.qualitesoft.freightclub.testscripts.manageorders;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestDeleteOrder extends InitializeTest{
	
	@Test
	public void deleteOpenQuoteOrIncompleteOrder(){
		try{
			ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
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
			
			if(manageOrderpage.acceptFeedbackPopupStatus() == true){
				manageOrderpage.acceptFeedbackPopup();
			}

			if(keyword.equals("quickOrders") == true){
				SeleniumFunction.click(manageOrderpage.actionButton(keyword));
				manageOrderpage.clickDeleteLink();
			}else if(keyword.equals("incompleteGrid") == true){
				SeleniumFunction.click(manageOrderpage.deleteButton());
			}
			
			String dialogHeader = SeleniumFunction.getText(manageOrderpage.getCancelOrderPopupHeader());
			String dialogDesc = SeleniumFunction.getText(manageOrderpage.getCancelOrderPopupBody());
			
			UseAssert.assertEquals(dialogHeader, "Please, confirm this action");
			UseAssert.assertEquals(dialogDesc, "Are you sure you want to delete the order "+ orderId + "?");
			SeleniumFunction.click(manageOrderpage.confirmCancelOrderBtn());
			
			String successMessage = manageOrderpage.verifyToastMessage();
			UseAssert.assertEquals(successMessage, "Success\nSuccessfully removed order.");
			WaitTool.sleep(5);
			
			String noRows = SeleniumFunction.getText(manageOrderpage.noRowsText());
			UseAssert.assertEquals(noRows, "No Rows To Show");
		}catch(Exception e){
			Assert.fail(e.getMessage());
		}
	}
}
