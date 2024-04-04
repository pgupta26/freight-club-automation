package com.qualitesoft.freightclub.testscripts.fcmarkups;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.HomePage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;

public class TestQuickQuoteRates extends InitializeTest {

	@Test
	public void rates(){
		QuickQuote QQPage=new QuickQuote(driver);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		int rowno = Integer.parseInt(Row);
		String Rates=SeleniumFunction.getText(QQPage.rates(carrierName));
		Log.info(Rates);
		xr.setCellData("Markups", "BaseRate", rowno, Rates);
	}

	@Test
	public void verifyRates() throws ParseException{
		int actualRateNum = Integer.parseInt(Row);
		int baseRateNum=Integer.parseInt(baseRateRow);
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		String rateWithoutMarkups=xr.getCellData("Markups", "BaseRate", baseRateNum);
		NumberFormat f = NumberFormat.getInstance(); 
		Double expectedResult = f.parse(rateWithoutMarkups.replace("$", "")).doubleValue();

		Double actualRate = f.parse(xr.getCellData("Markups", "BaseRate", actualRateNum).replace("$", "")).doubleValue(); 		
		Double expectedRate= expectedRates(expectedResult, actualRateNum);
		
		int dotPosition = String.valueOf(expectedRate).indexOf(".");
		String value1 = String.valueOf(expectedRate).substring(0, dotPosition+3);
		expectedRate = Double.parseDouble(value1);
		
		try {
			if(actualRateNum<72){
				Assert.assertEquals(actualRate, expectedRate);
				xr.setCellData("Markups", "Status", actualRateNum, "Pass");

			}
			else if(actualRateNum>71){
				Assert.assertEquals(actualRate, expectedResult);
				xr.setCellData("Markups", "Status", actualRateNum, "Pass");
			}
		} catch(AssertionError ae) {
			xr.setCellData("Markups", "Status", actualRateNum, "Fail : As per calculation value should be "+expectedRate+" but appearing in portal "+actualRate);
			throw ae;
		}
	}
	
	@Test
	public void logout(){
		HomePage homepage=new HomePage(driver);
		SeleniumFunction.clickJS(driver, homepage.logoff());
		WaitTool.sleep(3);
		ScreenShot.takeScreenShot(driver, "Logout");
	}
	
	public static Double expectedRates(Double ratesWithoutMarkups, int rownum ){
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		String markups=xr.getCellData("Markups", "Markups", rownum);
		BigDecimal val = new BigDecimal(markups);
		if(xr.getCellData("Markups", "Dollar/PercentageValue", rownum).contains("132")){
			if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1170")){
				BigDecimal val1 = new BigDecimal(ratesWithoutMarkups);
				return val1.add(val).doubleValue();	
				/*Double expectedRates=ratesWithoutMarkups + Double.parseDouble(markups);
				return expectedRates;*/
			}
			else if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1171")){
				BigDecimal val1 = new BigDecimal(ratesWithoutMarkups);
				return val1.subtract(val).doubleValue();
				/*Double expectedRates=ratesWithoutMarkups - Double.parseDouble(markups);
				return expectedRates;*/
			}
		}
		else if(xr.getCellData("Markups", "Dollar/PercentageValue", rownum).contains("130")){
			if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1170")){
				Double expectedRates=ratesWithoutMarkups + (ratesWithoutMarkups * Double.parseDouble(markups)/100);
				return expectedRates;
			}
			else if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1171")){
				Double expectedRates=ratesWithoutMarkups - (ratesWithoutMarkups * Double.parseDouble(markups)/100);
				return expectedRates;
			}
		}
		return null;
	}
}
