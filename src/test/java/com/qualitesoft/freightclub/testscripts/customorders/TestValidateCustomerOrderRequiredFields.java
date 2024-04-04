package com.qualitesoft.freightclub.testscripts.customorders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestValidateCustomerOrderRequiredFields extends InitializeTest {
	
	public void validateRequiredFields() {
		ArrayList<String> expectedRequiredFields = new ArrayList<String>();
		Collections.addAll(expectedRequiredFields, "Pickup date is required", "Customer PO Number is required"
				, "Please select a Service Level", "Pick Up location is required","Company name is required" 
				, "Address is required", "Drop Off location is required", "Address is required","You must select a handling unit", "Value is required"
				, "Value is required", "Supply the details of the shipment.");
		ArrayList<String> actualRequiredFields  = new ArrayList<String>();
		List<WebElement> validationFields = driver.findElements(By.xpath("//span[@class='form-group-message']"));
		for(WebElement validationField :validationFields) {
			Log.info(validationField.getCssValue("display"));
			if(validationField.getCssValue("display").equals("block")){
				actualRequiredFields.add(validationField.getText());
			}
		}
		
		Log.info("Actual Required Fields:"+actualRequiredFields);
		Assert.assertEquals(actualRequiredFields, expectedRequiredFields);
	}

	
	@Test
	public void testValidateCustomerOrderRequiredFields() {

		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		String shipmentType=xr.getCellData("Input","shipmentType", i).trim();
		
		if(loginuser ==  null ||  !loginuser.equals("new")) {
			SeleniumFunction.clickJS(driver, quickQuote.quickQuoteLink());
		}
		quickQuote.acceptPopup();
		SeleniumFunction.scrollUpByPixel(driver, "1500");
		WaitTool.sleep(5);

		if(!quickQuote.shipmentType(shipmentType).getAttribute("class").contains("active"))
			SeleniumFunction.clickJS(driver, quickQuote.shipmentType(shipmentType));
		quickQuote.acceptPopup();
		
		SeleniumFunction.click(quickQuote.submitForQuote());
		this.validateRequiredFields();
		WaitTool.sleep(2);
		ScreenShot.takeScreenShot(driver, "Required Fields Custom Order");
	}
}
