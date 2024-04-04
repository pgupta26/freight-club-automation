package com.qualitesoft.freightclub.testscripts.signup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;;

public class TestPayment extends InitializeTest {

	@Test
	public void testPayment() {
		QuickQuote quickQuote = new QuickQuote(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		SeleniumFunction.sendKeys(quickQuote.CreditCardNumber(), "4111111145551142");
		SeleniumFunction.sendKeys(quickQuote.CardName(), "test");
		SeleniumFunction.sendKeys(quickQuote.CVV(), "737");
		SeleniumFunction.select(quickQuote.Month(), "3");
		SeleniumFunction.select(quickQuote.Year(), "2030");
		SeleniumFunction.sendKeys(quickQuote.BillFirstName(), "Testfirst");
		SeleniumFunction.sendKeys(quickQuote.BillLastName(), "Testlast");
		SeleniumFunction.sendKeys(quickQuote.BillCompanytName(), "abc");
		SeleniumFunction.sendKeys(quickQuote.BillPhone(), "1234567890");
		SeleniumFunction.sendKeys(quickQuote.BillAddress1(), "Add1");
		SeleniumFunction.sendKeys(quickQuote.BillAddress2(), "Add2");
		SeleniumFunction.sendKeys(quickQuote.Billzip(), "90001");
		SeleniumFunction.sendKeys(quickQuote.Billcity(), "TestCity");
		SeleniumFunction.select(quickQuote.Billcountry(), "United States");
		SeleniumFunction.select(quickQuote.Billstate(), "Alabama");
		ScreenShot.takeScreenShot(driver, "Payment Page");
		jse.executeScript("window.scrollBy(0,550)", "");
		SeleniumFunction.click(quickQuote.BookandPay());
		ScreenShot.takeScreenShot(driver, "Payment Confirmation");
		
		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex=Integer.parseInt(Row);
		//get order id and store it in excel
		WaitTool.sleep(10);
		String orderId = SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='table table-condensed']/tbody/tr[2]/td[2]"), 30));
		xr.setCellData("Input", "OrderId", rowIndex, orderId);
		
		SeleniumFunction.click(quickQuote.Okbutton());
		WaitTool.sleep(10);
	}
}
