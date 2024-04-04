package com.qualitesoft.freightclub.testscripts.quickquote;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestSelectAddProductPackageType extends InitializeTest {

	@Test
	public void selectAddedProductPackageType() {
		
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		SeleniumFunction.click(quickQuote.backBtn());

		WaitTool.sleep(2);
		SeleniumFunction.scrollDownUptoFooter(driver);
		WaitTool.sleep(2);
		SeleniumFunction.click(quickQuote.addadditionalItem());
		SeleniumFunction.scrollDownUptoFooter(driver);
		quickQuote.deleteItemInformation();
		
		SeleniumFunction.sendKeys(quickQuote.productvalue(1), Productname);
		WaitTool.sleep(5);
		quickQuote.productvalue(1).sendKeys(Keys.ENTER);
		WaitTool.sleep(2);
		
		if(testname.equals("Test loose carton popup visible - Added Product with quantity = 11")) {
			quickQuote.quantity2("11");	
		}
		
		ScreenShot.takeScreenShot(driver, "Added product package type selected");
		SeleniumFunction.click(quickQuote.SaveButton());
		WaitTool.sleep(30);
	}
}
