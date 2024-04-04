package com.qualitesoft.freightclub.testscripts.managecarriers;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageCarrierPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

import org.testng.annotations.Test;


public class TestAddContactInformationForCarrier extends InitializeTest {
	
	@Test
	public void testAddContactInformationForCarrier() {
		try {
			ManageCarrierPage manageCarrierPage = new ManageCarrierPage(driver);
			QuickQuote quickQuote = new QuickQuote(driver);

			Xls_Reader xr = new Xls_Reader(testData);
			int rowIndex = Integer.parseInt(Row);
			Log.info("Row Number: "+rowIndex);
			
			String contactType = xr.getCellData("ContactInfo", "ContactType", rowIndex);
			String contactName = xr.getCellData("ContactInfo", "ContactName", rowIndex);
			String phoneNumber = xr.getCellData("ContactInfo", "PhoneNumber", rowIndex);
			String emailAddress = xr.getCellData("ContactInfo", "EmailAddress", rowIndex);
			
			String carrierName = xr.getCellData("Input", "Carrier", rowIndex);
			//click on manager carrier link
			manageCarrierPage.manageCarriersLink();
			SeleniumFunction.click(manageCarrierPage.classicView());
			WaitTool.sleep(2);
			quickQuote.acceptPopup();
			WaitTool.sleep(2);

			//search carrier
			manageCarrierPage.filterCarrier(carrierName);
			WaitTool.sleep(5);
			
			//Add new contact details
			SeleniumFunction.scrollDownByPixel(driver, "1000");
			WaitTool.sleep(5);
			manageCarrierPage.deleteContactsInformation();

			SeleniumFunction.click(manageCarrierPage.addContactButton());
			SeleniumFunction.selectByVisibleText(manageCarrierPage.contactType(), contactType);
			SeleniumFunction.sendKeys(manageCarrierPage.contactName(), contactName);
			SeleniumFunction.sendKeys(manageCarrierPage.phoneNumber(), phoneNumber);
			SeleniumFunction.sendKeys(manageCarrierPage.emailAddress(), emailAddress);
			SeleniumFunction.click(manageCarrierPage.saveContactInformation());
			
			//click on update button
			SeleniumFunction.scrollDownUptoFooter(driver);
			WaitTool.sleep(2);
			SeleniumFunction.click(manageCarrierPage.updateButton());
			WaitTool.sleep(10);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
