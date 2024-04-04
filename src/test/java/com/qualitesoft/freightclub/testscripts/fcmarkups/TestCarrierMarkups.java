package com.qualitesoft.freightclub.testscripts.fcmarkups;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.HomePage;
import com.qualitesoft.freightclub.pageobjects.CarrierMarkups;

public class TestCarrierMarkups extends InitializeTest{
	
	@Test
	public void setMarkups(){
		int i=Integer.parseInt(Row);
		CarrierMarkups markups=new CarrierMarkups(driver);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		WaitTool.sleep(3);
		SeleniumFunction.clickJS(driver, markups.clickCarrierMarkups());
		SeleniumFunction.scrollDownByPixel(driver, "100");
		WaitTool.sleep(5);
		
		SeleniumFunction.selectByvalue(markups.selectShipMethod(xpathRow), xr.getCellData("Markups", "Dollar/PercentageValue", i).trim());
		SeleniumFunction.selectByvalue(markups.selectIncDec(xpathRow),  xr.getCellData("Markups", "Inc/DecValue", i).trim());
		ScreenShot.takeScreenShot(driver, "Markup Selected");
	}
	
	@Test
	public void setGroundMarkups(){
		int i=Integer.parseInt(Row);
		CarrierMarkups markups=new CarrierMarkups(driver);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		Log.info("Data Row: " +Row);
		
		SeleniumFunction.sendKeys(markups.groundMarkups(xpathRow), xr.getCellData("Markups", "Markups", i).trim());
		markups.groundMarkups(xpathRow).sendKeys(Keys.TAB);
		ScreenShot.takeScreenShot(driver, "Set Group Markup");
	}
	
	@Test
	public void setNextDayAirEarlyAMMarkups(){
		int i=Integer.parseInt(Row);
		CarrierMarkups markups=new CarrierMarkups(driver);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		Log.info("Data Row: " +Row);
		
		SeleniumFunction.sendKeys(markups.nextDayAirEarlyAM(xpathRow), xr.getCellData("Markups", "Markups", i).trim());
		markups.nextDayAirEarlyAM(xpathRow).sendKeys(Keys.TAB);
		ScreenShot.takeScreenShot(driver, "Set Next Day Air Markup");
	}
	
	@Test
	public void setStandardMarkups(){
		int i=Integer.parseInt(Row);
		CarrierMarkups markups=new CarrierMarkups(driver);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		Log.info("Data Row: " +Row);
		
		SeleniumFunction.sendKeys(markups.standard(xpathRow), xr.getCellData("Markups", "Markups", i).trim());
		markups.standard(xpathRow).sendKeys(Keys.TAB);
		ScreenShot.takeScreenShot(driver, "Set Standard Markup");
	}
	
	@Test
	public void logout(){
		CarrierMarkups markups=new CarrierMarkups(driver);
		HomePage homepage=new HomePage(driver);
		//SeleniumFunction.scrollUp(driver);
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.clickJS(driver, markups.clkUpdateBtn());
		WebElement element = WaitTool.waitForElementPresentAndDisplay(driver,By.xpath("//div[@class='toast-message']") , 30);
		if(element.isDisplayed()) {
			Assert.assertEquals(element.getText(), "All markups applied successfully.");
			ScreenShot.takeScreenShot(driver, "Success Message");
		}
		SeleniumFunction.clickJS(driver, homepage.logoff());
		WaitTool.sleep(2);
	}
}
