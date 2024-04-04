package com.qualitesoft.freightclub.testscripts.manageorders;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.OrderDetailPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestViewOrderDetails extends InitializeTest {

	public void verifyItemsInThisOrderPanel(Xls_Reader xr, int rowIndex, int panelIndex, String packageType) {
		OrderDetailPage	orderDetailsPage= new OrderDetailPage(driver);

		String quantity=xr.getCellData("Input","Quantity", rowIndex);
		String weight=xr.getCellData("Input","Weight", rowIndex).trim();
		String expectedDeclareValue=xr.getCellData("Input","DeclaredValue", rowIndex).trim();
		String expectedTotalPalletCount=xr.getCellData("Input","TotalPalletCount", rowIndex);
		String expectedCategory=xr.getCellData("Input","category", rowIndex).trim();			
		String DimensionL=xr.getCellData("Input","DimensionL", rowIndex).trim();
		String DimensionW=xr.getCellData("Input","DimensionW", rowIndex).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", rowIndex).trim();

		String expectedDimension = "L"+DimensionL+" x W"+DimensionW+" x H"+DimensionH+" in";
		expectedDeclareValue = "$"+expectedDeclareValue+".00";

		orderDetailsPage.expandItemsInThisOrderPanel(panelIndex);
		ScreenShot.takeScreenShot(driver, "Pallet info order details");
		WaitTool.sleep(5);
		UseAssert.assertEquals(orderDetailsPage.getLabel("Quantity:", panelIndex).getText(), quantity);
		UseAssert.assertEquals(orderDetailsPage.getLabel("Weight:", panelIndex).getText(), weight+"lbs");
		UseAssert.assertEquals(orderDetailsPage.getLabel("Dimensions:", panelIndex).getText(), expectedDimension);
		UseAssert.assertEquals(orderDetailsPage.getLabel("Declared Value:", panelIndex).getText(),expectedDeclareValue);

		if(packageType.equals("Non-Palletized")) {
			UseAssert.assertEquals(orderDetailsPage.getLabel("Category:", 1).getText(), expectedCategory);
		} else {
			UseAssert.assertEquals(orderDetailsPage.getLabel("Categories:", panelIndex).getText(), expectedCategory);
			UseAssert.assertEquals(orderDetailsPage.getLabel("Carton Count:", panelIndex).getText(), expectedTotalPalletCount);
		}
	}

	@Test
	public void testViewOrderDetails() {

		ManagerOrderPage manageOrderPage =new ManagerOrderPage(driver);
		OrderDetailPage	orderDetailsPage= new OrderDetailPage(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();
		String serviceLevel=xr.getCellData("Input","serviceLevel", i).trim();
		String orderReferenceID=xr.getCellData("Input","orderReferenceID", i).trim();
		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String expectedDeclareValue=xr.getCellData("Input","DeclaredValue", i).trim();
		String amount=xr.getCellData("Input","Amount", i);
		String packageType = xr.getCellData("Input","packageType", i).trim();
		String packageType2 = xr.getCellData("Input", "packageType2", i).trim();
		String carrier = xr.getCellData("Input", "Carrier", i).trim();

		String description = xr.getCellData("ShipmentInformation","SpecialHandlingInstructions", 2).trim();
		expectedDeclareValue = "$"+expectedDeclareValue+".00";


		String customer=xr.getCellData("CreateAccount","CompanyName", 2).trim();
		String email=xr.getCellData("CreateAccount","Email", 2).trim();
		String phoneNumber=xr.getCellData("CreateAccount","PhoneNumber", 2).trim();
		String extn=xr.getCellData("CreateAccount","Extension", 2).trim();

		SeleniumFunction.clickJS(driver, manageOrderPage.ViewDetail());

		try {
			SeleniumFunction.getCurrentWindow(driver);
			WaitTool.sleep(3);
			quickQuote.acceptPopup();
			quickQuote.acceptPopup();

			int panelIndex = 1;
			UseAssert.assertEquals(orderDetailsPage.getLabel("Order ID:", panelIndex).getText(), orderId);
			//UseAssert.assertEquals(orderDetailsPage.getLabel("Created Date:").getText(), pickUpDate);

			UseAssert.assertEquals(orderDetailsPage.getLabel("Shipment Type:", panelIndex).getText(), shipmentType);
			
			if(carrier.equals("Ups SurePost")) {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Service Level:", panelIndex).getText(), "SurePost");
			}else {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Service Level:", panelIndex).getText(), serviceLevel);
			}
			
			//customer PO validation
			if(orderReferenceID.length() <=25) {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer PO #:", panelIndex).getText(), orderReferenceID);
			} else {
				if(carrier.equals("FragilePAK")) {
					UseAssert.assertEquals(orderDetailsPage.getLabel("Customer PO #:", panelIndex).getText(), orderReferenceID.substring(0, 25));	
				} else {
					UseAssert.assertEquals(orderDetailsPage.getLabel("Customer PO #:", panelIndex).getText(), orderReferenceID.substring(0, 50));	
				}
			}
			
			UseAssert.assertEquals(orderDetailsPage.getLabel("Quoted Amount:", panelIndex).getText(), amount);
			

			//UseAssert.assertEquals(orderDetailsPage.getLabel("Status:").getText(), "Booked");
			try {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer:", panelIndex).getText(), customer);
			}catch(AssertionError ae) {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer:", panelIndex).getText(), "Test Automation");
			}
			UseAssert.assertEquals(orderDetailsPage.getLabel("Payment Type:", panelIndex).getText(), "OnAccount");

			//UseAssert.assertEquals(orderDetailsPage.getLabel("Booking Date:").getText(), pickUpDate);

			// try {
			// 	UseAssert.assertEquals(orderDetailsPage.getLabel("Contact Name:", panelIndex).getText(), customer);
			// }catch(AssertionError ae) {
				UseAssert.assertEquals(orderDetailsPage.getLabel("Customer:", panelIndex).getText(), customer);
			// }
			//UseAssert.assertEquals(orderDetailsPage.getLabel("Company Email:", panelIndex).getText(), email);
			//UseAssert.assertEquals(orderDetailsPage.getLabel("Phone Number:", panelIndex).getText(), phoneNumber+" ext. "+extn);
			
			//verify carrier 
			SeleniumFunction.scrollDownUptoFooter(driver);
			Assert.assertTrue(orderDetailsPage.verifyCarrier(carrier));
	
			UseAssert.assertEquals(orderDetailsPage.getLabel("Special Handling Instructions", panelIndex).getText(), description);
			ScreenShot.takeScreenShot(driver, "Order Details");
			SeleniumFunction.scrollDownUptoFooter(driver);
			if((packageType.equals("Non-Palletized") || packageType.equals("Cardboard Box") ||packageType.equals("Bagged or Unboxed Product")) && packageType2.isEmpty()) {
				this.verifyItemsInThisOrderPanel(xr, i, 1, "Non-Palletized");
			} else if((packageType.equals("Standard Pallet 1") || packageType.equals("SearchaddedProduct") ||packageType.equals("Custom Pallet (enter dimensions)")) && packageType2.isEmpty() && !shipmentType.equals("Parcel")) {
				this.verifyItemsInThisOrderPanel(xr, i, 1, "Palletized");
			} else if(packageType2.equals("Standard Pallet 1")) {
				this.verifyItemsInThisOrderPanel(xr, i, 1, "Palletized");
				WaitTool.sleep(2);
				SeleniumFunction.scrollDownByPixel(driver, "300");
				this.verifyItemsInThisOrderPanel(xr, i, 2, "Non-Palletized");
			} 
			
			//verify order details tab
			ArrayList<String> 	userOrderDetailsTab = new ArrayList<String>();
			Collections.addAll(userOrderDetailsTab, "Information","Admin");
			
			ArrayList<String> adminOrderDetailsTab = new ArrayList<String>();
			Collections.addAll(adminOrderDetailsTab, "Information","Update History", "Admin", "Contacts");
			
			ArrayList<String> actualOrderDetailsTab = new ArrayList<String>();
			List<WebElement> orderDetailsTab = orderDetailsPage.orderDetailsTab();
			
			for(WebElement orderDetail : orderDetailsTab) {
				if(orderDetail.findElement(By.xpath("./a")).getCssValue("display").equals("block")) {
					actualOrderDetailsTab.add(orderDetail.getText());
				}
			}

			if(fcusername.equals("freightclubinfo@gmail.com")) {
				Assert.assertEquals(actualOrderDetailsTab, adminOrderDetailsTab);
			} else {
				Assert.assertEquals(actualOrderDetailsTab, userOrderDetailsTab);
			}
			
			//verify admin tab - sub menu
			SeleniumFunction.scrollUpByPixel(driver, "500");
			SeleniumFunction.click(orderDetailsPage.adminTab());
			ArrayList<String> user_AdminTabSubMenu = new ArrayList<String>();
			Collections.addAll(user_AdminTabSubMenu, "Comments","Invoices");
			
			ArrayList<String> admin_adminTabSubMenu = new ArrayList<String>();
			Collections.addAll(admin_adminTabSubMenu, "Comments","Invoices", "Bills", "Custom Order");
			
			ArrayList<String> actualAdminTabSubMenu = new ArrayList<String>();
			List<WebElement> adminTabSubMenus = orderDetailsPage.adminTabSubMenu();
			
			for(WebElement adminTabSubMenu : adminTabSubMenus) {
				actualAdminTabSubMenu.add(adminTabSubMenu.getText());
			}

			if(fcusername.equals("freightclubinfo@gmail.com")) {
				Assert.assertEquals(actualAdminTabSubMenu, admin_adminTabSubMenu);
			} else {
				Assert.assertEquals(actualAdminTabSubMenu, user_AdminTabSubMenu);
			}

			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
		}catch(Exception |AssertionError ex) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw ex;
		}
	}
}
