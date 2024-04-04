package com.qualitesoft.freightclub.testscripts.signup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.FloatingCreateAccount;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.SignInPage;


public class TestCreateAccount extends InitializeTest {
	@Test
	public void testCreateAccount() {

		Xls_Reader xr = new Xls_Reader(testData);
		int rowIndex = Integer.parseInt(Row);

		// Read data from sheet for 2nd row
		String accountType = xr.getCellData("Input", "accountType", rowIndex).trim();
		String companyName = xr.getCellData("CreateAccount", "CompanyName", rowIndex).trim();
		String password = xr.getCellData("CreateAccount", "Password", rowIndex).trim();
		String firstName = xr.getCellData("CreateAccount", "FirstName", rowIndex).trim();
		String lastName = xr.getCellData("CreateAccount", "LastName", rowIndex).trim();
		String phoneNumber = xr.getCellData("CreateAccount", "PhoneNumber", rowIndex).trim();
		String extension = xr.getCellData("CreateAccount", "Extension", rowIndex).trim();
		FloatingCreateAccount account = new FloatingCreateAccount(driver);
		SignInPage signInPage = new SignInPage(driver);

		account.clickSignUpLink();
		SeleniumFunction.sendKeys(account.password(), password);
		SeleniumFunction.sendKeys(account.firstName(), firstName);
		SeleniumFunction.sendKeys(account.lastName(), lastName);
		SeleniumFunction.sendKeys(account.companyName(), companyName);
		SeleniumFunction.sendKeys(account.phone(), phoneNumber);
		SeleniumFunction.sendKeys(account.extension(), extension);
		account.selectAccountType(accountType)
				.selectSourceType("Referral(s)");
		SeleniumFunction.click(signInPage.loginButton());
		WaitTool.sleep(30);

		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(2);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);

		WebElement scroll = driver.findElement(By.className("terms"));
		SeleniumFunction.executeJS(driver, "arguments[0].scrollTop = arguments[0].scrollHeight",
				scroll);

		SeleniumFunction.click(account.agreeCheckbox());
		SeleniumFunction.scrollDownByPixel(driver, "500");
		SeleniumFunction.click(account.confirmAcceptance());
		UseAssert.assertEquals(SeleniumFunction.getText(account.accountCreateSuccessMessage()),
				"Thank you for accepting the updated Terms and Conditions. You can now continue using Freight Club.");
		ScreenShot.takeScreenShot(driver, "Account Created");

		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);
		SeleniumFunction.click(manageOrderpage.manageOrdersLink());
		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderpage.openQuotes());
		WaitTool.sleep(2);
		SeleniumFunction.click(manageOrderpage.ActionButton());
		WaitTool.sleep(2);
		manageOrderpage.clickBookButton();
	}
}

