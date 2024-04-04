package com.qualitesoft.freightclub.testscripts.manageoverage;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.OverageDetails;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestCompletedOverageStatus extends InitializeTest {
	
	@Test
	public void testCompletedOverageStatus() {
			
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		
		ManageOverages manageOverages = new ManageOverages(driver);
		OverageDetails overageDetails = new OverageDetails(driver);
		QuickQuote quickQuote = new QuickQuote(driver);

		//Click on  manage overages link
		SeleniumFunction.click(manageOverages.manageOverages());
		
		//Accept popup
		WaitTool.sleep(10);
		quickQuote.acceptPopup();
		WaitTool.sleep(5);
		
		//Filter data grid by total billed
		WaitTool.sleep(5);
		SeleniumFunction.sendKeys(manageOverages.OrderIDTextBox(), xr.getCellData("Sec invoice Master","FC Order ID", i));
		SeleniumFunction.sendKeys(manageOverages.TotalBilledTextBox(6), xr.getCellData("Sec invoice Master","New Invoice Amount", i));
		manageOverages.TotalBilledTextBox(6).sendKeys(Keys.ENTER);
		WaitTool.sleep(5);
		
		//Click on view/edit button
		SeleniumFunction.click(manageOverages.viewEdit(1));
		
		try {
			//Select completed overage status
			SeleniumFunction.getCurrentWindow(driver);
			SeleniumFunction.click(overageDetails.getSelect("Overage Status:"));
			SeleniumFunction.clickJS(driver, overageDetails.setSelect("Overage Status:", "Completed"));
			WaitTool.sleep(5);
			
			//Save changes
			SeleniumFunction.moveToElement(driver, overageDetails.saveChanges());
			SeleniumFunction.scrollDownUptoFooter(driver);
			SeleniumFunction.executeJS(driver, "window.scrollBy(2000,0)");
			SeleniumFunction.click(overageDetails.saveChanges());
			
			//Verify overage status
			WaitTool.sleep(20);
			ScreenShot.takeScreenShot(driver, "Completed Overage Status");
			UseAssert.assertEquals(overageDetails.getSelect("Overage Status:").getText(), "Completed");
			
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			
		}catch(Exception | AssertionError e) {
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			Assert.fail(e.getMessage());
		}
	}
}
