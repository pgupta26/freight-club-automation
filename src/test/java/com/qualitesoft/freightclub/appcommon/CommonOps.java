package com.qualitesoft.freightclub.appcommon;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.ManageProducts;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;
import com.qualitesoft.freightclub.pageobjects.Admin.ManageOrderNotQuotedTab;

import utils.ErrorMessage;

public class CommonOps extends InitializeTest {

	public void shipmentInformation(Xls_Reader xr, int rowIndex) {

		// Read data from sheet for selected row
		String shipmentType = xr.getCellData("Input", "shipmentType", rowIndex).trim();
		String serviceLevel = xr.getCellData("Input", "serviceLevel", rowIndex).trim();
		String orderReferenceID = xr.getCellData("Input", "orderReferenceID", rowIndex).trim();
		String pickUpZip = xr.getCellData("Input", "pickUpZip", rowIndex).trim();
		String pickUPLocationName = xr.getCellData("Input", "pickUpLocationName", rowIndex).trim();
		String pickUpType = xr.getCellData("Input", "pickUpType", rowIndex).trim();
		String dropOffZip = xr.getCellData("Input", "dropOffZip", rowIndex).trim();
		String dropOffLocationName =
				xr.getCellData("Input", "dropOffLocationName", rowIndex).trim();
		String dropOffType = xr.getCellData("Input", "dropOffType", rowIndex).trim();

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		try {
			if (manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}
		}catch(Exception ex) {
			Log.warn("Expand menu page object not visible");
		}

		if (loginuser == null) {
			SeleniumFunction.clickJS(driver, quickQuote.quickQuoteLink());
		}
		quickQuote.acceptPopup();
		SeleniumFunction.scrollDownByPixel(driver, "500");
		WaitTool.sleep(5);

		if (!quickQuote.shipmentType(shipmentType).getAttribute("class").contains("active"))
			SeleniumFunction.clickJS(driver, quickQuote.shipmentType(shipmentType));
		quickQuote.acceptPopup();
		SeleniumFunction.scrollUpByPixel(driver, "1500");
		SeleniumFunction.click(quickQuote.OrderDate());
		SeleniumFunction.sendKeys(quickQuote.OrderReferenceID(), orderReferenceID);

		if(!shipmentType.equalsIgnoreCase("parcel")) {
				SeleniumFunction.click(quickQuote.serviceLevel(serviceLevel));
		}

		if (!pickUPLocationName.isEmpty()) {
			SeleniumFunction.click(quickQuote.selectLocationName(pickUPLocationName));
		} else {
			SeleniumFunction.sendKeys(quickQuote.PickUpZip(), pickUpZip.trim());
		}

		if (dropOffZip.isEmpty()) {
			SeleniumFunction.click(quickQuote.selectLocationName(dropOffLocationName));
		} else {
			SeleniumFunction.sendKeys(quickQuote.DropOffZip(), dropOffZip.trim());
			quickQuote.DropOffZip().sendKeys(Keys.TAB);
		}

		WaitTool.sleep(5);
		SeleniumFunction.scrollUpByPixel(driver, "250");
		SeleniumFunction.selectByVisibleText(quickQuote.PickUpLocationType(), pickUpType);
		SeleniumFunction.selectByVisibleText(quickQuote.DropOffLocationType(), dropOffType);
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Shipment Info");

	}

	public void itemInformation(Xls_Reader xr, int rowIndex, int itemIndex, String packageType) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		// Read data from sheet for selected row
		String quantity = xr.getCellData("Input", "Quantity", rowIndex).trim();
		String Weight = xr.getCellData("Input", "Weight", rowIndex).trim();
		String DimensionL = xr.getCellData("Input", "DimensionL", rowIndex).trim();
		String DimensionW = xr.getCellData("Input", "DimensionW", rowIndex).trim();
		String DimensionH = xr.getCellData("Input", "DimensionH", rowIndex).trim();
		String category = xr.getCellData("Input", "category", rowIndex).trim();
		String DeclaredValue = xr.getCellData("Input", "DeclaredValue", rowIndex).trim();
		String Cartons = xr.getCellData("Input", "Cartons", rowIndex).trim();

		SeleniumFunction.scrollDownByPixel(driver, "1000");
		if (packageType.equals("SearchaddedProduct")) {
			SeleniumFunction.sendKeys(quickQuote.productvalue(itemIndex), Productname);
			WaitTool.sleep(5);
			quickQuote.productvalue(itemIndex).sendKeys(Keys.ENTER);
			WaitTool.sleep(2);

		} else {
			if (category.equalsIgnoreCase("Other")) {
				SeleniumFunction.selectByvalue(quickQuote.Category(itemIndex), "347");
				SeleniumFunction.click(quickQuote.popupCateogory(itemIndex));
				WaitTool.sleep(2);
			} else {
				SeleniumFunction.selectByVisibleText(quickQuote.Category(itemIndex), category);
			}

			SeleniumFunction.sendKeys(quickQuote.quantity(itemIndex), quantity);
			SeleniumFunction.clickJS(driver, quickQuote.PackageType(packageType, itemIndex));
			SeleniumFunction.sendKeys(quickQuote.Weight(itemIndex), Weight);
			WaitTool.sleep(3);
			SeleniumFunction.sendKeys(quickQuote.DimensionL(itemIndex), DimensionL);
			SeleniumFunction.sendKeys(quickQuote.DimensionW(itemIndex), DimensionW);
			SeleniumFunction.sendKeys(quickQuote.DimensionH(itemIndex), DimensionH);
			SeleniumFunction.sendKeys(quickQuote.DeclaredValue(itemIndex), DeclaredValue);
			if (packageType.contains("Standard Pallet")
					|| packageType.equalsIgnoreCase("Custom Pallet (enter dimensions)")) {
				SeleniumFunction.sendKeys(quickQuote.Cartons(itemIndex), Cartons);
			}
		}

		boolean pickUpDateValidationMessage = WaitTool.isElementPresentAndDisplay(driver,
				By.xpath("//span[text()='Pickup date is required']"));
		Log.info("Is pickup date validation message present: " + pickUpDateValidationMessage);

		if (pickUpDateValidationMessage) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
					By.xpath("//input[@id='OrderDate']"), 30));
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
					By.xpath("(//div[@class='datepicker-days']//td[@class='day'])[2]"), 10));
			SeleniumFunction.clickJS(driver, quickQuote.SaveButton());
		}

		ScreenShot.takeScreenShot(driver, "Item Info");
	}

	public void selectCarrier() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		WaitTool.sleep(2);
		quickQuote.acceptPopup();
		SeleniumFunction.scrollUpByPixel(driver, "250");
		// SeleniumFunction.click(quickQuote.SaveButton());
		SeleniumFunction.clickJS(driver, quickQuote.SaveButton());
		WaitTool.sleep(30);
		quickQuote.waitForQuotesToAppear();
		SeleniumFunction.scrollDownByPixel(driver, "3000");
		quickQuote.expandCarries();
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Carriers Page");
		quickQuote.acceptPopup();
	}

	public void addPalletContents(Xls_Reader xr, int rowIndex, int itemIndex, String palletType) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		String Weight = xr.getCellData("Input", "PalletWeight", rowIndex).trim();
		String DimensionL = xr.getCellData("Input", "DimensionL", rowIndex).trim();
		String DimensionW = xr.getCellData("Input", "DimensionW", rowIndex).trim();
		String DimensionH = xr.getCellData("Input", "DimensionH", rowIndex).trim();
		String category = xr.getCellData("Input", "category", rowIndex).trim();
		String DeclaredValue = xr.getCellData("Input", "DeclaredValue", rowIndex).trim();
		String cartoonQuantity = xr.getCellData("Input", "CartoonQuantity", rowIndex).trim();

		String description = xr.getCellData("ShipmentInformation", "Description", 2).trim();


		if (palletType.equals("Non-Palletized")) {
			SeleniumFunction.scrollUpByPixel(driver, "300");
			SeleniumFunction.sendKeys(quickQuote.nonPalletDescription(), description);
			ScreenShot.takeScreenShot(driver, "Pallet Info");
		} else if (palletType.equalsIgnoreCase("Generic Pallet")) {
			SeleniumFunction.scrollUpByPixel(driver, "500");
			SeleniumFunction.click(quickQuote.genericPallet());
			SeleniumFunction.sendKeys(quickQuote.palletDescription(), description);
			SeleniumFunction.sendKeys(quickQuote.numberOfCartoons(), cartoonQuantity);
			ScreenShot.takeScreenShot(driver, "Pallet Info");
		} else if (palletType.equalsIgnoreCase("Non-Palletized-step3")) {
			SeleniumFunction.scrollUpByPixel(driver, "200");
			SeleniumFunction.sendKeys(quickQuote.palletDescription(), description);
			if (category.equalsIgnoreCase("Other")) {
				SeleniumFunction.selectByvalue(quickQuote.Category(itemIndex), "347");
				SeleniumFunction.click(quickQuote.popupCateogory(itemIndex));
				WaitTool.sleep(2);
			} else {
				SeleniumFunction.selectByvalue(quickQuote.Category(itemIndex), "346");
			}

			SeleniumFunction.sendKeys(quickQuote.quantity(itemIndex), cartoonQuantity);
			SeleniumFunction.clickJS(driver, quickQuote.PackageType("Non-Palletized", itemIndex));
			SeleniumFunction.sendKeys(quickQuote.Weight(itemIndex), Weight);
			SeleniumFunction.sendKeys(quickQuote.DimensionL(itemIndex), DimensionL);
			SeleniumFunction.sendKeys(quickQuote.DimensionW(itemIndex), DimensionW);
			SeleniumFunction.sendKeys(quickQuote.DimensionH(itemIndex), DimensionH);
			SeleniumFunction.sendKeys(quickQuote.DeclaredValue(itemIndex), DeclaredValue);
			ScreenShot.takeScreenShot(driver, "Pallet Info");

		} else if (palletType.equals("New Product")) {
			SeleniumFunction.scrollUpByPixel(driver, "300");
			ManageProducts manageProducts = new ManageProducts(driver);
			String Productname = JavaFunction.randomText("prod", 4);
			Log.info("Product Name: " + Productname);

			SeleniumFunction.sendKeys(quickQuote.palletDescription(), description);
			SeleniumFunction.click(quickQuote.productvalue(itemIndex));
			SeleniumFunction.click(quickQuote.addProduct(itemIndex + 1));
			SeleniumFunction.sendKeys(manageProducts.SKU(), Productname);
			SeleniumFunction.sendKeys(manageProducts.productName(), Productname);
			SeleniumFunction.sendKeys(manageProducts.declaredValue(), DeclaredValue);

			xr = new Xls_Reader("testdata/FCfiles/" + env + "/ManageProducts/ManageProducts.xlsx");
			this.addProductCarton(xr, 4, 1);

			SeleniumFunction.click(manageProducts.saveproduct());
			ScreenShot.takeScreenShot(driver, "Pallet Info");

		} else {
			WaitTool.sleep(2);
			SeleniumFunction.sendKeys(quickQuote.palletDescription(), description);
			SeleniumFunction.sendKeys(quickQuote.productvalue(itemIndex), Productname);
			WaitTool.sleep(5);
			quickQuote.productvalue(itemIndex).sendKeys(Keys.ENTER);
			WaitTool.sleep(2);
		}
	}

	public void shipmentCompletion(Xls_Reader xr, int rowIndex) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		String pickUPLocationName = xr.getCellData("Input", "pickUpLocationName", rowIndex).trim();
		String specialHandlingIns =
				xr.getCellData("ShipmentInformation", "SpecialHandlingInstructions", 2).trim();
		String pickUpType = xr.getCellData("Input", "pickUpType", rowIndex).trim();
		String dropOffType = xr.getCellData("Input", "dropOffType", rowIndex).trim();
		String description = xr.getCellData("ShipmentInformation", "Description", 2).trim();

		SeleniumFunction.scrollDownByPixel(driver, "300");
		//SeleniumFunction.sendKeys(quickQuote.nonPalletDescription(), description);
		SeleniumFunction.sendKeys(quickQuote.SpecialHandling(), specialHandlingIns);

		if (pickUPLocationName.isEmpty()) {
			SeleniumFunction.scrollDownByPixel(driver, "300");
			quickQuote.LocationName().sendKeys(Keys.chord("Auto"));
			if (pickUpType.equals("Residential")) {
				SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
						By.xpath("//strong[text()='Res90001']"), 10));
			} else {
				SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
						By.xpath("//strong[text()='Com90001']"), 10));
			}

			quickQuote.LocationName().sendKeys(Keys.chord("Auto"));
			if (dropOffType.equals("Residential")) {
				SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
						By.xpath("//strong[text()='Res10011']"), 10));
			} else {
				SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,
						By.xpath("//strong[text()='Com10011']"), 10));
			}
			SeleniumFunction.scrollUpByPixel(driver, "400");
		}

		ScreenShot.takeScreenShot(driver, "Shipment Completion");
	}

	public void verifyPalletizedDetail(Xls_Reader xr, int rowIndex, String panelIndex,
			String packageType) {

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);

		String expectedPackageType = null;
		String expectedWeight = xr.getCellData("Input", "Weight", rowIndex).trim();
		String DimensionL = xr.getCellData("Input", "DimensionL", rowIndex).trim();
		String DimensionW = xr.getCellData("Input", "DimensionW", rowIndex).trim();
		String DimensionH = xr.getCellData("Input", "DimensionH", rowIndex).trim();
		String expectedCategory = xr.getCellData("Input", "category", rowIndex).trim();
		String expectedDeclareValue = xr.getCellData("Input", "DeclaredValue", rowIndex).trim();
		String expectedNumberOfCartoons = xr.getCellData("Input", "TotalPalletCount", rowIndex);
		String shipmentType = xr.getCellData("Input", "shipmentType", rowIndex).trim();

		String actualCategory = null;
		String actualCartoon = null;

		if (packageType.equals("Non-Palletized")) {

			expectedPackageType = "1 x " + packageType + " 1";
			Log.info("expected Package Type :" + expectedPackageType);
			actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "4").getText();
		} else if (shipmentType.equals("Parcel") && packageType.contains("SearchaddedProduct")) {

			expectedPackageType = "1 x " + Productname;
			actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "4").getText();
		} else if (shipmentType.equals("Less Than Truckload")
				&& !packageType.equals("Non-Palletized")) {

			expectedPackageType = "1 x PALLET 1";
			actualCartoon = notQuotedTab.getCellValueFromPackage(panelIndex, "5").getText();
			actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "6").getText();
			Assert.assertEquals(actualCartoon, expectedNumberOfCartoons);
		} else {
			expectedPackageType = "1 x Box 1";
			actualCategory = notQuotedTab.getCellValueFromPackage(panelIndex, "4").getText();
		}

		String actualPackageType, actualWeight, actualDimentions, actualDeclaredValue;
		String expectedDimension;

		if (shipmentType.equals("Parcel") && packageType.contains("SearchaddedProduct")) {
			expectedWeight = expectedWeight + " lbs";
			expectedDimension = DimensionL + " x " + DimensionW + " x " + DimensionH + " in";
			expectedDeclareValue = "$" + expectedDeclareValue + ".00";

			actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
			actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
			actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
			actualDeclaredValue =
					notQuotedTab.declaredValueCloneOrder().getText().split(":")[1].trim();
		} else {
			expectedWeight = expectedWeight + "lbs";
			expectedDimension =
					"L" + DimensionL + " x W" + DimensionW + " x H" + DimensionH + " inches";
			expectedDeclareValue = "$" + expectedDeclareValue + ".00";

			actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
			Log.info("Actual package type: " + actualPackageType);
			actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "1").getText();
			actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
			actualDeclaredValue = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
		}

		Assert.assertEquals(actualPackageType, expectedPackageType, String.format(
				ErrorMessage.PACKAGE_TYPE_IS_MATCHING, expectedPackageType, actualPackageType));

		Assert.assertEquals(actualWeight, expectedWeight, String
				.format(ErrorMessage.PACKAGE_WEIGHT_IS_MATCHING, expectedWeight, actualWeight));

		Assert.assertEquals(actualDimentions, expectedDimension, String.format(
				ErrorMessage.PACKAGE_DIMENTIONS_IS_MATCHING, expectedDimension, actualDimentions));

		Assert.assertEquals(actualDeclaredValue, expectedDeclareValue,
				String.format(ErrorMessage.PACKAGE_DECLARED_VALUE_IS_MATCHING, expectedDeclareValue,
						actualDeclaredValue));

		Assert.assertEquals(actualCategory, expectedCategory, String.format(
				ErrorMessage.PACKAGE_CATEOGY_IS_MATCHING, expectedCategory, actualCategory));

		ScreenShot.takeScreenShot(driver, "Pallet Details");
	}

	public void verifyPalletizedDetailCloneOrder(Xls_Reader xr, int rowIndex, String panelIndex,
			String packageType) {

		ManageOrderNotQuotedTab notQuotedTab = new ManageOrderNotQuotedTab(driver);

		String expectedPackageType = null;
		String shipmentType = xr.getCellData("Input", "shipmentType", rowIndex).trim();
		String expectedWeight = xr.getCellData("Input", "Weight", rowIndex).trim();
		String DimensionL = xr.getCellData("Input", "DimensionL", rowIndex).trim();
		String DimensionW = xr.getCellData("Input", "DimensionW", rowIndex).trim();
		String DimensionH = xr.getCellData("Input", "DimensionH", rowIndex).trim();
		String expectedCategory = xr.getCellData("Input", "category", rowIndex).trim();
		String expectedDeclareValue = xr.getCellData("Input", "DeclaredValue", rowIndex).trim();
		String expectedNumberOfCartoons = xr.getCellData("Input", "TotalPalletCount", rowIndex);

		String actualCategory = null;
		String actualCartoon = null;
		String actualPackageType, actualWeight, actualDimentions, actualDeclaredValue;

		if (packageType.equals("Non-Palletized")) {
			expectedPackageType = "1 x " + packageType + " 1";
			actualCategory = notQuotedTab.getCellValueFromPackage1(panelIndex, "4").getText();
		} else if (packageType.equals("Cardboard Box")
				|| packageType.equals("Bagged or Unboxed Product")) {
			expectedPackageType = "1 x Box 1";
			actualCategory = notQuotedTab.getCellValueFromPackage1(panelIndex, "4").getText();
		} else if (shipmentType.equals("Parcel") && packageType.contains("SearchaddedProduct")) {
			expectedPackageType = "1 x " + Productname;
			actualCategory = notQuotedTab.getCellValueFromPackage1(panelIndex, "4").getText();
		} else {
			expectedPackageType = "1 x Pallet 1";
			actualCartoon = notQuotedTab.getCellValueFromPackage1(panelIndex, "5").getText();
			actualCategory = notQuotedTab.getCellValueFromPackage1(panelIndex, "6").getText();
			Assert.assertEquals(actualCartoon, expectedNumberOfCartoons);
		}

		String expectedDimension = null;
		if (shipmentType.equals("Parcel")) {
			expectedWeight = expectedWeight + " lbs";
			expectedDimension = DimensionL + " x " + DimensionW + " x " + DimensionH + " in";
			expectedDeclareValue = "$" + expectedDeclareValue + ".00";
			actualPackageType = notQuotedTab.packageTypeHeading(panelIndex).getText();
			actualWeight = notQuotedTab.getCellValueFromPackage(panelIndex, "2").getText();
			actualDimentions = notQuotedTab.getCellValueFromPackage(panelIndex, "3").getText();
			actualDeclaredValue =
					notQuotedTab.declaredValueCloneOrder().getText().split(":")[1].trim();
		} else {
			expectedWeight = expectedWeight + "lbs";
			expectedDimension =
					"L" + DimensionL + " x W" + DimensionW + " x H" + DimensionH + " in";
			expectedDeclareValue = "$" + expectedDeclareValue + ".00";
			actualPackageType = notQuotedTab.packageTypeHeading1(panelIndex).getText();
			actualWeight = notQuotedTab.getCellValueFromPackage1(panelIndex, "1").getText();
			actualDimentions = notQuotedTab.getCellValueFromPackage1(panelIndex, "2").getText();
			actualDeclaredValue = notQuotedTab.getCellValueFromPackage1(panelIndex, "3").getText();
		}


		Assert.assertEquals(actualPackageType, expectedPackageType);
		Assert.assertEquals(actualWeight, expectedWeight);
		Assert.assertEquals(actualDimentions, expectedDimension);
		Assert.assertEquals(actualDeclaredValue, expectedDeclareValue);
		Assert.assertEquals(actualCategory, expectedCategory);
		ScreenShot.takeScreenShot(driver, "Pallet Details Clone Order");
	}

	public void bookOrder(Xls_Reader xr, int rowIndex) {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManageOverages manageOverage = new ManageOverages(driver);

		SeleniumFunction.clickJS(driver, quickQuote.Book());

		if (quickQuote.acknowleadgeBtnStatus() == true) {
			String actualPopupHeader =
					SeleniumFunction.getText(quickQuote.acknowleadgeModalHeader());
			String actualPopupBody = SeleniumFunction.getText(quickQuote.acknowleadgeModalBody());
			UseAssert.assertEquals(actualPopupHeader, "A matching order has already been placed.");
			UseAssert.assertEquals(actualPopupBody,
					"We have identified that the order that you are about to place is similar to an order that has already been booked. Would you like to continue with placing a duplicate order?");
			WaitTool.sleep(2);
			SeleniumFunction.click(quickQuote.acknowleadgeBtn());
			WaitTool.sleep(2);
			SeleniumFunction.clickJS(driver, quickQuote.Book());
		}

		SeleniumFunction.clickJS(driver, quickQuote.Okbutton1());
		WaitTool.sleep(10);
		crorderId = SeleniumFunction.getText(manageOverage.gridData(1, 1));
		Log.info("Order Id Generated: " + crorderId);
		String orderDate = SeleniumFunction.getText(manageOverage.gridData(1, 3));
		orderDate = orderDate.replace("/", "-");
		Log.info("Pick up date: " + orderDate);
		String amount = SeleniumFunction.getText(manageOverage.gridData(1, 10));
		Log.info("Amount: " + amount);
		String wayBill = manageOverage.gridData(1, 8).getText();
		Log.info("WayBill: " + wayBill);
		String tracking = manageOverage.gridData(1, 9).getText();
		Log.info("Tracking: " + tracking);

		// set order id in excel
		xr.setCellData("Input", "OrderId", rowIndex, crorderId.trim());
		xr.setCellData("Input", "pickUpDate", rowIndex, orderDate.trim());
		xr.setCellData("Input", "Amount", rowIndex, amount);
		xr.setCellData("Input", "Tracking#", rowIndex, tracking);
		xr.setCellData("Input", "WayBill", rowIndex, wayBill);
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Order Booked");
	}

	public void addProductDetail(Xls_Reader xr, int rowIndex) {
		ManageProducts manageProducts = new ManageProducts(driver);

		Productname = JavaFunction.randomText("prod", 4);
		Log.info("Product Name: " + Productname);

		String sku = xr.getCellData("Input", "Sku", rowIndex).trim();
		String declaredValue = xr.getCellData("Input", "DeclaredProductValue", rowIndex).trim();
		String palletType = xr.getCellData("Input", "PalletType", rowIndex).trim();
		String weight = xr.getCellData("Input", "PalletWeight", rowIndex).trim();
		String length = xr.getCellData("Input", "PalletLength", rowIndex).trim();
		String width = xr.getCellData("Input", "PalletWidth", rowIndex).trim();
		String height = xr.getCellData("Input", "PalletHeight", rowIndex).trim();

		SeleniumFunction.click(manageProducts.createproduct());
		ScreenShot.takeScreenShot(driver, "Create Product Popup");

		SeleniumFunction.clickJS(driver, manageProducts.palletType(palletType));
		SeleniumFunction.sendKeys(manageProducts.SKU(), sku);
		SeleniumFunction.sendKeys(manageProducts.productName(), Productname);
		SeleniumFunction.sendKeys(manageProducts.declaredValue(), declaredValue);

		if (!palletType.equalsIgnoreCase("No Pallet Required")) {
			SeleniumFunction.sendKeys(manageProducts.weight(), weight);
			SeleniumFunction.sendKeys(manageProducts.length(), length);
			SeleniumFunction.sendKeys(manageProducts.width(), width);
			SeleniumFunction.sendKeys(manageProducts.height(), height);
		}
	}

	public void addProductCarton(Xls_Reader xr, int rowIndex, int itemIndex) {
		ManageProducts manageProduct = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		String cartonWeight = xr.getCellData("Input", "CartonWeight", rowIndex).trim();
		String cartonLength = xr.getCellData("Input", "CartonLength", rowIndex).trim();
		String cartonWidth = xr.getCellData("Input", "CartonWidth", rowIndex).trim();
		String cartonHeight = xr.getCellData("Input", "CartonHeight", rowIndex).trim();
		String category = xr.getCellData("Input", "Category", rowIndex).trim();


		String cartonWeight2 = xr.getCellData("Input", "CartonWeight2", rowIndex).trim();
		String cartonLength2 = xr.getCellData("Input", "CartonLength2", rowIndex).trim();
		String cartonWidth2 = xr.getCellData("Input", "CartonWidth2", rowIndex).trim();
		String cartonHeight2 = xr.getCellData("Input", "CartonHeight2", rowIndex).trim();
		String category2 = xr.getCellData("Input", "Category2", rowIndex).trim();

		if (category.equalsIgnoreCase("Other")) {
			SeleniumFunction.selectByvalue(manageProduct.category(itemIndex), "347");
			WaitTool.sleep(2);
			SeleniumFunction.click(manageProduct.popupCateogory(1));
			WaitTool.sleep(2);
		} else {
			SeleniumFunction.selectByvalue(manageProduct.category(itemIndex), "1183");
		}

		if (testname.contains("Weight greater than 250 - LTL  Added-Product")) {
			SeleniumFunction.sendKeys(manageProduct.cartoonweight(itemIndex), "251");
		} else {
			SeleniumFunction.sendKeys(manageProduct.cartoonweight(itemIndex), cartonWeight);
		}
		SeleniumFunction.sendKeys(manageProduct.cartoonlength(itemIndex), cartonLength);
		SeleniumFunction.sendKeys(manageProduct.cartoonwidth(itemIndex), cartonWidth);
		SeleniumFunction.sendKeys(manageProduct.cartoonheight(itemIndex), cartonHeight);

		if (!cartonWeight2.isEmpty()) {
			SeleniumFunction.click(quickQuote.addadditionalItem());

			if (category2.equalsIgnoreCase("Other")) {
				SeleniumFunction.selectByvalue(manageProduct.category(itemIndex + 1), "347");
				WaitTool.sleep(2);
				SeleniumFunction.click(manageProduct.popupCateogory(itemIndex + 1));
				WaitTool.sleep(2);
			} else {
				SeleniumFunction.selectByvalue(manageProduct.category(itemIndex + 1), "1183");
			}

			if (testname.contains("Weight greater than 250 - LTL  Added-Product")) {
				SeleniumFunction.sendKeys(manageProduct.cartoonweight(itemIndex + 1), "251");
			} else {
				SeleniumFunction.sendKeys(manageProduct.cartoonweight(itemIndex + 1),
						cartonWeight2);
			}
			SeleniumFunction.sendKeys(manageProduct.cartoonlength(itemIndex + 1), cartonLength2);
			SeleniumFunction.sendKeys(manageProduct.cartoonwidth(itemIndex + 1), cartonWidth2);
			SeleniumFunction.sendKeys(manageProduct.cartoonheight(itemIndex + 1), cartonHeight2);
		}
		ScreenShot.takeScreenShot(driver, "Product Details");
	}

	/*-------------------------------Manage Orders Page --------------------------------------*/

	public void openManageOrdersPageAndSearchOrder(String orderId) {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.clickJS(driver, manageOrderpage.manageOrdersLink());
		ScreenShot.takeScreenShot(driver, "Manage Order Page");

		if (!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}

		if (manageOrderpage.acceptFeedbackPopupStatus() == true) {
			manageOrderpage.acceptFeedbackPopup();
		}

		SeleniumFunction.sendKeys(manageOrderpage.searchFields("1"), orderId);
		manageOrderpage.searchFields("1").sendKeys(Keys.ENTER);
		/* manageOrderpage.orderIdFilter().sendKeys(Keys.ENTER); */
		WaitTool.sleep(10);
	}

	public void openManageClaimsPageAndSearchOrder(String orderId) {
		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		ManageClaims manageClaims = new ManageClaims(driver);

		if (manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}

		SeleniumFunction.click(manageClaims.manageClaimsLink());
		ScreenShot.takeScreenShot(driver, "Manage Claims Page");

		if (!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}

		WaitTool.sleep(5);
		manageOrderpage.orderIDFilter(orderId);
		manageOrderpage.orderIdFilter().sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
	}

	// ************************Manage billing search document id and verify listing***************

	public void searchDocumentIdAndVerifyListing(String ex_carrierCode, String ex_type,
			String ex_fileStatus, String ex_editStatus, String ex_status, String ex_carrierBill,
			String ex_documentNum) throws ParseException {
		ManageOverages overagesPage = new ManageOverages(driver);
		NumberFormat f = NumberFormat.getInstance();

		WebElement carrierName = overagesPage.gridData(1, 1);
		WebElement billType = overagesPage.gridData(1, 2);
		WebElement fileStatus = overagesPage.gridData(1, 3);
		WebElement ediStatus = overagesPage.gridData(1, 4);
		WebElement status = overagesPage.gridData(1, 5);
		WebElement documentNum = overagesPage.gridData(1, 8);
		WebElement noMarkupQuote = overagesPage.gridData(1, 9);
		WebElement getvariance = overagesPage.gridData(1, 11);

		String markupQuote = SeleniumFunction.getText(noMarkupQuote).trim().replaceAll(",", "");
		double markupQuoteAmount =
				Double.parseDouble(markupQuote.substring(1, markupQuote.length()));
		double variance =
				((Double.parseDouble(ex_carrierBill) - markupQuoteAmount) / markupQuoteAmount)
						* 100;
		int vari = f.parse(SeleniumFunction.getText(getvariance).replace("$", "")).intValue();

		UseAssert.assertEquals(SeleniumFunction.getText(carrierName), ex_carrierCode);
		UseAssert.assertEquals(SeleniumFunction.getText(billType), ex_type);
		UseAssert.assertEquals(SeleniumFunction.getText(fileStatus), ex_fileStatus);
		UseAssert.assertEquals(SeleniumFunction.getText(ediStatus), ex_editStatus);
		UseAssert.assertEquals(SeleniumFunction.getText(status), ex_status);
		UseAssert.assertEquals(SeleniumFunction.getText(documentNum), ex_documentNum);
		UseAssert.assertEquals(vari, (int) variance);
	}

	public void checkPdfFilePresenceAndPageCount(String fileName, int expectedPageCount)
			throws IOException {

		String downloadDir =
				System.getProperty("user.dir") + File.separator + "download" + File.separator;
		File file = new File(downloadDir + fileName);

		if (file.exists()) {
			PDDocument doc = Loader.loadPDF(file);
			int count = doc.getNumberOfPages();
			UseAssert.assertEquals(count, expectedPageCount);
		} else {
			WaitTool.sleep(30);
			if (file.exists()) {
				PDDocument doc = Loader.loadPDF(file);
				int count = doc.getNumberOfPages();
				UseAssert.assertEquals(count, expectedPageCount);
			} else {
				Assert.fail("File doesn't exists");
			}
		}
	}

	public void validatePDFText(String fileName, String expectedText, int expectedTextCount)
			throws IOException {

		String downloadDir =
				System.getProperty("user.dir") + File.separator + "download" + File.separator;
		File file = new File(downloadDir + fileName);
		PDDocument doc = Loader.loadPDF(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String actualText = pdfStripper.getText(doc);
		System.out.println("actualText: " + actualText);

		System.out.println("Expected Text: " + expectedText);
		Assert.assertTrue(actualText.contains(expectedText));

		int actualCount = StringUtils.countMatches(actualText, expectedText);
		Assert.assertEquals(actualCount, expectedTextCount);
	}
}


