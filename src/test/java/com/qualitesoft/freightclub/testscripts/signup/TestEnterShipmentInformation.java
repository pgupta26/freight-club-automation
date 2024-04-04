package com.qualitesoft.freightclub.testscripts.signup;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestEnterShipmentInformation extends InitializeTest {
	
	@Test
	public void testNewUserIsAbleToSignUp() {
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();
		
		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		String accountType = xr.getCellData("Input","accountType", rowIndex).trim();
		String packageType = xr.getCellData("Input","packageType", rowIndex).trim();
		
		commonOps.shipmentInformation(xr, rowIndex);
		String emailAddress = JavaFunction.randomText("Selenium", 4) + "@mailinator.com";
		Log.info("Email Address: "+emailAddress);
		SeleniumFunction.sendKeys(quickQuote.QuickEmail(),emailAddress);
		SeleniumFunction.selectByVisibleText(quickQuote.AccountType(), accountType);
		
		commonOps.itemInformation(xr, rowIndex, 1, packageType);
		commonOps.selectCarrier();

		//select carrier
		ScreenShot.takeScreenShot(driver, "Rates page");
		SeleniumFunction.clickJS(driver, quickQuote.NextButton());
		
		//Update email address in test data sheet
		xr.setCellData("CreateAccount", "Email", rowIndex, emailAddress);
	}
}
