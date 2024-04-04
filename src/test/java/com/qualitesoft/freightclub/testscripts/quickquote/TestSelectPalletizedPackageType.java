package com.qualitesoft.freightclub.testscripts.quickquote;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestSelectPalletizedPackageType extends InitializeTest {

	@Test
	public void selectPalletizedPackageType() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		
		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		String packageType = xr.getCellData("Input","packageType", i).trim();
		String DimensionH=xr.getCellData("Input","DimensionH", i).trim();
		String Cartons=xr.getCellData("Input","Cartons", i).trim();

		SeleniumFunction.click(quickQuote.backBtn());
		WaitTool.sleep(2);
		SeleniumFunction.scrollDownByPixel(driver, "2000");
		WaitTool.sleep(1);
		
		SeleniumFunction.click(quickQuote.PackageType());			
		quickQuote.PackageTypeOptions(packageType);
		SeleniumFunction.sendKeys(quickQuote.DimensionH(1), DimensionH);
		SeleniumFunction.sendKeys(quickQuote.Cartons(1), Cartons);
		WaitTool.sleep(2);
		ScreenShot.takeScreenShot(driver, "Palletized Package Type Selected");
		SeleniumFunction.click(quickQuote.SaveButton());
		WaitTool.sleep(30);
	}
}
