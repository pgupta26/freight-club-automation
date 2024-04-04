package com.qualitesoft.freightclub.testscripts.fcmarkups;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.HomePage;
import com.qualitesoft.freightclub.pageobjects.ProfileManagementPage;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;
import com.qualitesoft.freightclub.pageobjects.SignInPage;
import com.qualitesoft.freightclub.pageobjects.CarrierMarkups;

public class RatesWithMarkups extends InitializeTest {

	@Parameters({"rownum","fcusername","fcpassword"})
	@Test(priority=1)
	public void setMarkups(int rownum, String fcusername, String fcpassword){
		try{
			Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
			HomePage homePage = new HomePage(driver);
			ProfileManagementPage profile=new ProfileManagementPage(driver);
			CarrierMarkups markups=new CarrierMarkups(driver);

			SeleniumFunction.clickJS(driver, homePage.signInLink());
			ScreenShot.takeScreenShot(driver, "SignInPage");
			SignInPage signInPage = new SignInPage(driver);

			SeleniumFunction.sendKeys(signInPage.usernameTextField(), fcusername);
			SeleniumFunction.sendKeys(signInPage.passowrdTextField(), fcpassword);
			ScreenShot.takeScreenShot(driver, "SignInPageFilled");
			SeleniumFunction.clickJS(driver, signInPage.loginButton());
			WaitTool.sleep(5);
			ScreenShot.takeScreenShot(driver, "Quick Quote page");
			WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='link-quick-quote']"), 60).click();
			//driver.findElement(By.xpath("//a[@id='link-quick-quote']")).click();
			//SeleniumFunction.click(quote.manageOrdersLink());
			WaitTool.sleep(3);
			java.util.List<WebElement> elements=WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//button[text()='OK']"), 60);
			if(elements.size()>=1){
				WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='OK']"), 60).click();
				//driver.findElement(By.xpath("//button[text()='OK']")).click();
			}
			SeleniumFunction.scrollDownByPixel(driver, "100");
			SeleniumFunction.clickJS(driver, profile.manageProfileLink());
			SeleniumFunction.sendKeys(profile.profileListfilter(), "15678");

			if(profile.selectGround().isSelected()){
				Log.info("Ground and surepost checkboxes are already selected");
			}
			else{
				SeleniumFunction.click(profile.selectGround());		
				SeleniumFunction.click(profile.selectUPSSurepost());	
				SeleniumFunction.scrollDownByPixel(driver, "1000");;
				SeleniumFunction.clickJS(driver, profile.saveProfileBtn());
				Assert.assertTrue(markups.clickCarrierMarkups().isDisplayed());
				SeleniumFunction.scrollUpByPixel(driver, "500");
			}
			SeleniumFunction.clickJS(driver, markups.clickCarrierMarkups());
			SeleniumFunction.scrollDownByPixel(driver, "100");
			WaitTool.sleep(5);
			if(xr.getCellData("Markups", "Dollar/PercentageValue", rownum).contains("132")){
				if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1170")){
					setValue(rownum, xr.getCellData("Markups", "Ship Method", rownum));
					WaitTool.sleep(5);
				}
				else if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1171")){
					setValue(rownum, xr.getCellData("Markups", "Ship Method", rownum));	
				}
			}
			else if(xr.getCellData("Markups", "Dollar/PercentageValue", rownum).contains("130")){
				if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1170")){
					setValue(rownum, xr.getCellData("Markups", "Ship Method", rownum));	
					WaitTool.sleep(5);
				}
				else if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1171")){
					setValue(rownum, xr.getCellData("Markups", "Ship Method", rownum));	
				}
			}
			
			if(xr.getCellData("Markups", "Ship Method", rownum).contains("1 to 5 lbs")){
				SeleniumFunction.sendKeys(markups.setValFor1to5(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("6 to 10 lbs")){
				SeleniumFunction.sendKeys(markups.setValFor6to10(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("11 to 20 lbs")){
				SeleniumFunction.sendKeys(markups.setValFor11to20(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("21 to 30 lbs")){
				SeleniumFunction.sendKeys(markups.setValFor21to30(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("31 to 150 Lbs")){
				SeleniumFunction.sendKeys(markups.setValFor31to150(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Residential Dropoff")){
				SeleniumFunction.horizontalScroll(driver);
				SeleniumFunction.sendKeys(markups.setValForResidentialDelivery(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("International")){
				SeleniumFunction.sendKeys(markups.setValForUSToCanada(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Beyond Fee")){
				SeleniumFunction.sendKeys(markups.setValForBeyondRate(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Residential Pickup")){
				SeleniumFunction.sendKeys(markups.setValForResidentialPickup(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Non Box/Cylinder")){
				SeleniumFunction.sendKeys(markups.setValForCylinderHandling(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Additional Handling")){
				SeleniumFunction.sendKeys(markups.setValForAdditionalHandling(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Large Package")){
				SeleniumFunction.sendKeys(markups.setValForLargePackage(), xr.getCellData("Markups", "Markups", rownum));
			}
			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("Additional Fee")){
				SeleniumFunction.sendKeys(markups.setValForAdditionalFee(), xr.getCellData("Markups", "Markups", rownum));
			}

			else if(xr.getCellData("Markups", "Ship Method", rownum).contains("US to Canada with others")){
				SeleniumFunction.sendKeys(markups.setValFor31to150International(), xr.getCellData("Markups", "Markups", rownum));
				SeleniumFunction.sendKeys(markups.setValForResiPickInternational(), xr.getCellData("Markups", "Markups", rownum));
				SeleniumFunction.sendKeys(markups.setValForCylinderInternational(), xr.getCellData("Markups", "Markups", rownum));
				SeleniumFunction.sendKeys(markups.setValForUSToCanada(), xr.getCellData("Markups", "Markups", rownum));
			}
			WaitTool.sleep(5);
			SeleniumFunction.scrollUp(driver);
			SeleniumFunction.clickJS(driver, markups.clkUpdateBtn());
			WaitTool.sleep(5);
			SeleniumFunction.clickJS(driver, homePage.logoff());
		}
		finally {
			HomePage homePage = new HomePage(driver);
			SeleniumFunction.clickJS(driver, homePage.logoff());
		}
	}
	@Parameters({"useremail", "userpassword", "rowNum"})
	@Test(priority=2)
	public void getRatesWithMarkups(String useremail, String userpassword, String rowNum){
		try{
			int rowno = Integer.parseInt(rowNum);
			Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
			HomePage homePage = new HomePage(driver);
			QuickQuote quote = new QuickQuote(driver);
			SeleniumFunction.clickJS(driver, homePage.signInLink());
			SignInPage signInPage = new SignInPage(driver);
			String shipMethod=xr.getCellData("Markups", "Ship Method", rowno);

			SeleniumFunction.sendKeys(signInPage.usernameTextField(), useremail);
			SeleniumFunction.sendKeys(signInPage.passowrdTextField(), userpassword);
			SeleniumFunction.clickJS(driver, signInPage.loginButton());
			WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='link-quick-quote']"), 60).click();
			//driver.findElement(By.xpath("//a[@id='link-quick-quote']")).click();
			WaitTool.sleep(5);
			SeleniumFunction.clickJS(driver, quote.ParcelShipment());
			WaitTool.sleep(5);
			/*driver.findElement(By.xpath("//input[@id='OrderDate']")).click();
			driver.findElement(By.xpath("//*[@id='logged-app']/div/div/table/tbody/tr/td[@class='today day']")).click();
			 */
			 SeleniumFunction.click(quote.OrderDate());
			 SeleniumFunction.clickJS(driver, quote.OrderDate1());
			 SeleniumFunction.sendKeys(quote.OrderReferenceID(), xr.getCellData("Markups", "orderReferenceID", rowno));
			 java.util.List<WebElement> elements=WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//button[text()='OK']"), 60);
			 if(elements.size()>=1){
				 WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='OK']"), 60).click();
				// driver.findElement(By.xpath("//button[text()='OK']")).click();
			 }
			 SeleniumFunction.click(quote.ServiceLevel());
			 SeleniumFunction.click(quote.ServiceLevelGRND());

			 SeleniumFunction.sendKeys(quote.PickUpZip(), xr.getCellData("Markups", "pickUpZip", rowno));
			 SeleniumFunction.sendKeys(quote.DropOffZip(),xr.getCellData("Markups", "dropOffZip", rowno));
			 JavascriptExecutor jse=((JavascriptExecutor)driver);
			 jse.executeScript("window.scrollBy(0,250)", "");
			 WaitTool.sleep(3);
			 
			 SeleniumFunction.selectByVisibleText(quote.PickUpLocationType(), xr.getCellData("Markups", "pickUpType", rowno));
			 SeleniumFunction.selectByVisibleText(quote.DropOffLocationType(), xr.getCellData("Markups", "dropOffType", rowno));
			/* if(xr.getCellData("Markups", "pickUpType", rowno).contains("Commercial") && xr.getCellData("Markups", "dropOffType", rowno).contains("Commercial")){
				 SeleniumFunction.click(quote.PickUpZipLocationTypeCom());
				 SeleniumFunction.click(quote.DropOffZipLocationTypeCom());
			 }
			 else if(xr.getCellData("Markups", "pickUpType", rowno).contains("Residential")&& xr.getCellData("Markups", "dropOffType", rowno).contains("Commercial")){
				 SeleniumFunction.click(quote.PickUpZipLocationTypeRes());
				 SeleniumFunction.click(quote.DropOffZipLocationTypeCom());
			 }
			 else if(xr.getCellData("Markups", "pickUpType", rowno).contains("Commercial")&& xr.getCellData("Markups", "dropOffType", rowno).contains("Residential")){
				 SeleniumFunction.click(quote.PickUpZipLocationTypeCom());
				 SeleniumFunction.click(quote.DropOffZipLocationTypeRes());
			 }*/
			 SeleniumFunction.scrollDownByPixel(driver, "100");
			 SeleniumFunction.click(quote.PackageTypeParcel());
			 SeleniumFunction.clickJS(driver, quote.parcelPackageCardBoardBox());
			 SeleniumFunction.sendKeys(quote.Weight(), xr.getCellData("Markups", "Weight", rowno));
			 if(xr.getCellData("Markups", "Weight", rowno).contains("150")){
				 SeleniumFunction.clickJS(driver, quote.understoodBtn());
			 }
			 SeleniumFunction.sendKeys(quote.DimensionL(), xr.getCellData("Markups", "DimensionL", rowno));
			 if(xr.getCellData("Markups", "DimensionL", rowno).contains("97")){
				 SeleniumFunction.clickJS(driver, quote.understoodBtn());
			 }
			 SeleniumFunction.sendKeys(quote.DimensionW(), xr.getCellData("Markups", "DimensionW", rowno));
			 SeleniumFunction.sendKeys(quote.DimensionH(), xr.getCellData("Markups", "DimensionH", rowno));
			 WaitTool.sleep(3);
			 // List<WebElement> understood=driver.findElements(By.xpath("(//button[@type='button']/span[text()='Understood'])[1]"));
			 SeleniumFunction.selectByvalue(quote.Category(), xr.getCellData("Markups", "Category", rowno));
			 WaitTool.sleep(5);
			 if(xr.getCellData("Markups", "Category", rowno).contains("347")){
				 WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-primary'])[3]"), 60).click();
			 }
			 SeleniumFunction.sendKeys(quote.DeclaredValue(), xr.getCellData("Markups", "DeclaredValue", rowno));
			 WaitTool.sleep(3);

			 if(xr.getCellData("Markups", "Ship Method", rowno).contains("International") || xr.getCellData("Markups", "Ship Method", rowno).contains("US to Canada with others")){
				 jse.executeScript("window.scrollBy(0,250)", "");
				 SeleniumFunction.sendKeys(quote.internationalDesc(),"Table"/* xr.getCellData("Marrkups", "Description", rowno)*/);
				 SeleniumFunction.sendKeys(quote.HSCode(), "1001");
				 SeleniumFunction.clickJS(driver, quote.selectHSCode());
				 SeleniumFunction.sendKeys(quote.setInternationalDeclaredValue(), xr.getCellData("Markups", "DeclaredValue", rowno));
				 SeleniumFunction.sendKeys(quote.countryOfOrigin(), xr.getCellData("Markups", "CountryOfOrigin", rowno));
				 SeleniumFunction.click(quote.selectCountryOfOrigin());
			 } 
			 SeleniumFunction.clickJS(driver, quote.SaveButton());
			 Log.info(shipMethod);
			 if(shipMethod.contains("International")|| shipMethod.contains("US to Canada with others")){
				 String Rates=SeleniumFunction.getText(quote.getStandardRatetext()).replace("$", "");
				 Log.info(Rates);
				 xr.setCellData("Markups", "BaseRate", rowno, Rates);
			 }
			 else if(shipMethod.contains("Beyond Fee") ||
					 shipMethod.contains("Residential Pickup") ||
					 shipMethod.contains("Non Box/Cylinder") ||
					 shipMethod.contains("Additional Handling") ||
					 shipMethod.contains("Large Package")){
				 String Rates=SeleniumFunction.getText(quote.getNextDayAirEarlyAMRatetext()).replace("$", "");
				 Log.info(Rates);
				 xr.setCellData("Markups", "BaseRate", rowno, Rates);
			 }
			 else{
				 String Rates=SeleniumFunction.getText(quote.getGroundRatetext()).replace("$", "");
				 Log.info(Rates);
				 xr.setCellData("Markups", "BaseRate", rowno, Rates);
			 }
			 if(shipMethod.contains("1 to 5 lbs")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 2);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("6 to 10 lbs")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 7);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("11 to 20 lbs")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 12);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("21 to 30 lbs")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 17);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("31 to 150 Lbs")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 22);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Residential Dropoff")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 27);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("International")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 32);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 } else if(shipMethod.contains("Beyond Fee")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 37);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Residential Pickup")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 42);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Non Box/Cylinder")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 47);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Additional Handling")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 52);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Large Package")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 57);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("Additional Fee")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 62);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }
			 else if(shipMethod.contains("US to Canada with others")&& xr.getCellData("Markups", "Markups", rowno).contains("0")){
				 String ratesWithoutMarkups=xr.getCellData("Markups", "Markups", 67);
				 Double expectedRate= expectedRates(Double.parseDouble(ratesWithoutMarkups), rowno);
				 Double actualRate=Double.parseDouble(xr.getCellData("Markups", "BaseRate", rowno));
				 Assert.assertEquals(actualRate, expectedRate, 0.01);
			 }

			 SeleniumFunction.click(homePage.logoff());
		}
		finally {
			HomePage homePage = new HomePage(driver);
			SeleniumFunction.clickJS(driver, homePage.logoff());
		}
	}

	public static void setValue(int rownum, String value){
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		CarrierMarkups markups=new CarrierMarkups(driver);

		if(value.contains("1 to 5 lbs")){
			SeleniumFunction.selectByvalue(markups.setdollarFor1to5(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor1to5(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("6 to 10 lbs")){
			SeleniumFunction.selectByvalue(markups.setdollarFor6to10(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor6to10(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("11 to 20 lbs")){
			SeleniumFunction.selectByvalue(markups.setdollarFor11to20(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor11to20(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("21 to 30 lbs")){
			SeleniumFunction.selectByvalue(markups.setdollarFor21to30(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor21to30(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("31 to 150 Lbs")){
			SeleniumFunction.selectByvalue(markups.setdollarFor31to150(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor31to150(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Residential Dropoff")){
			WaitTool.sleep(2);
			SeleniumFunction.horizontalScroll(driver);
			SeleniumFunction.selectByvalue(markups.setdollarResidentialDelivery(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecResidentialDelivery(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("International")){
			SeleniumFunction.horizontalScroll(driver);
			SeleniumFunction.scrollDownByPixel(driver, "500");
			SeleniumFunction.selectByvalue(markups.setdollarUSToCanada(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecUSToCanada(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Beyond Fee")){
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarBeyondRate(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecBeyondRate(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Residential Pickup")){
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarResidentialPickup(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecResidentialPickup(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Non Box/Cylinder")){
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarCylinderHandling(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecCylinderHandling(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Additional Handling")){
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarAdditionalHandling(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecAdditionalHandling(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Large Package")){
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarLargePackage(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecLargePackage(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("Additional Fee")){
			WaitTool.sleep(2);
			SeleniumFunction.horizontalScroll(driver);
			SeleniumFunction.selectByvalue(markups.setdollarForAdditionalFee(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecForAdditionalFee(), xr.getCellData("Markups", "Inc/DecValue", rownum));
		}
		else if(value.contains("US to Canada with others")){
			SeleniumFunction.selectByvalue(markups.setdollarFor31to150(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecFor31to150(), xr.getCellData("Markups", "Inc/DecValue", rownum));
			WaitTool.sleep(2);
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarCylinderHandling(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecCylinderHandling(), xr.getCellData("Markups", "Inc/DecValue", rownum));
			SeleniumFunction.horizontalScroll(driver);
			SeleniumFunction.scrollDownByPixel(driver, "500");
			SeleniumFunction.selectByvalue(markups.setdollarUSToCanada(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecUSToCanada(), xr.getCellData("Markups", "Inc/DecValue", rownum));
			SeleniumFunction.scrollDownByPixel(driver, "400");
			SeleniumFunction.selectByvalue(markups.setdollarResidentialPickup(), xr.getCellData("Markups", "Dollar/PercentageValue", rownum));
			SeleniumFunction.selectByvalue(markups.setInc_DecResidentialPickup(), xr.getCellData("Markups", "Inc/DecValue", rownum));

		}


	}

	public static Double expectedRates(Double ratesWithoutMarkups, int rownum ){
		Xls_Reader xr=new Xls_Reader(".\\binaries\\FCfiles\\FCMarkups.xlsx");
		String markups=xr.getCellData("Markups", "Markups", rownum);
		if(xr.getCellData("Markups", "Dollar/PercentageValue", rownum).contains("132")){
			if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1170")){
				Double expectedRates=ratesWithoutMarkups + Double.parseDouble(markups);
				return expectedRates;
			}
			else if(xr.getCellData("Markups", "Inc/DecValue", rownum).contains("1171")){
				Double expectedRates=ratesWithoutMarkups - Double.parseDouble(markups);
				return expectedRates;
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
