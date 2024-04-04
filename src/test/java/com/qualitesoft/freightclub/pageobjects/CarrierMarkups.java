package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class CarrierMarkups {

	public By carrierMarkups=By.xpath("//a[contains(text(),'Carrier Markups')]");
	public By groung1to5=By.xpath("//div[@id='markupsTab']//tr[1]//td[1]//input[1]");
	public By groung6to10=By.xpath("//div[@id='markupsTab']//tr[1]//td[2]//input[1]");
	public By groung11to20=By.xpath("//div[@id='markupsTab']//tr[1]//td[3]//input[1]");
	public By groung21to30=By.xpath("//div[@id='markupsTab']//tr[1]//td[4]//input[1]");
	public By groung31to150=By.xpath("//div[@id='markupsTab']//tr[1]//td[5]//input[1]");
	public By ground150plus=By.xpath("//div[@id='markupsTab']//tr[1]//td[6]//input[1]");
	
	public By largePackage=By.xpath("//div[@id='markupsTab']//tr[7]//td[7]//input[1]");
	public By additionalHandling=By.xpath("//div[@id='markupsTab']//tr[7]//td[8]//input[1]");
	public By cylinderHandling=By.xpath("//div[@id='markupsTab']//tr[7]//td[9]//input[1]");
	public By residentialPickup=By.xpath("//div[@id='markupsTab']//tr[7]//td[10]//input[1]");
	public By beyondRate=By.xpath("//div[@id='markupsTab']//tr[7]//td[11]//input[1]");
	public By usToCanada=By.xpath("//div[@id='markupsTab']//tr[8]//td[12]//input[1]");
	public By residentialDelivery=By.xpath("//div[@id='markupsTab']//tr[1]//td[13]//input[1]");
	public By additionalFee=By.xpath("//div[@id='markupsTab']//tr[1]//td[14]//input[1]");
	
	public By ground31to150International=By.xpath("//div[@id='markupsTab']//tr[8]//td[5]//input[1]");
	public By cylinderHandlingInternational=By.xpath("//div[@id='markupsTab']//tr[8]//td[9]//input[1]");
	public By residentialPickupInternational=By.xpath("//div[@id='markupsTab']//tr[8]//td[10]//input[1]");

	
	public By dollar1to5=By.xpath("//th[contains(text(),'1 to 5 lbs')]//select[1]");
	public By incdec1to5=By.xpath("//th[contains(text(),'1 to 5 lbs')]//select[2]");
	public By dollar6to10=By.xpath("//th[contains(text(),'6 to 10 lbs')]//select[1]");
	public By incdec6to10=By.xpath("//th[contains(text(),'6 to 10 lbs')]//select[2]");
	public By dollar11to20=By.xpath("//th[contains(text(),'11 to 20 lbs')]//select[1]");
	public By incdec11to20=By.xpath("//th[contains(text(),'11 to 20 lbs')]//select[2]");
	public By dollar21to30=By.xpath("//th[contains(text(),'21 to 30 lbs')]//select[1]");
	public By incdec21to30=By.xpath("//th[contains(text(),'21 to 30 lbs')]//select[2]");
	public By dollar31to150=By.xpath("//th[contains(text(),'31 to 150 Lbs')]//select[1]");
	public By incdec31to150=By.xpath("//th[contains(text(),'31 to 150 Lbs')]//select[2]");
	public By dollar150plus=By.xpath("//th[contains(text(),'150+ Lbs')]//select[1]");
	public By incdec150plus=By.xpath("//th[contains(text(),'150+ Lbs')]//select[2]");
	
	public By dollarlargepackage=By.xpath("//th[contains(text(),'Large Package')]//select[1]");
	public By incdeclargepackage=By.xpath("//th[contains(text(),'Large Package')]//select[2]");
	public By dollaradditionalhandling=By.xpath("//th[contains(text(),'Additional Handling')]//select[1]");
	public By incdecadditionalhandling=By.xpath("//th[contains(text(),'Additional Handling')]//select[2]");
	public By dollarcylinderhandling=By.xpath("//th[contains(text(),'Cylinder Handling')]//select[1]");
	public By incdeccylinderhandling=By.xpath("//th[contains(text(),'Cylinder Handling')]//select[2]");
	public By dollarresidentialpickup=By.xpath("//th[contains(text(),'Residential Pickup')]//select[1]");
	public By incdecresidentialpickup=By.xpath("//th[contains(text(),'Residential Pickup')]//select[2]");
	public By dollarbeyondrate=By.xpath("//th[contains(text(),'Beyond Rate')]//select[1]");
	public By incdecbeyondrate=By.xpath("//th[contains(text(),'Beyond Rate')]//select[2]");
	public By dollarustocanada=By.xpath("//th[contains(text(),'US to Canada')]//select[1]");
	public By incdecustocanada=By.xpath("//th[contains(text(),'US to Canada')]//select[2]");
	public By dollarresidentialdelivery=By.xpath("//th[contains(text(),'Residential Delivery')]//select[1]");
	public By incdecresidentialdelivery=By.xpath("//th[contains(text(),'Residential Delivery')]//select[2]");
	public By dollaradditionalfee=By.xpath("//th[contains(text(),'Additional Fee')]//select[1]");
	public By incdecadditionalfee=By.xpath("//th[contains(text(),'Additional Fee')]//select[2]");
	public By updatebtn=By.xpath("//button[contains(text(),'Update')]");

	WebDriver driver;
	public CarrierMarkups(WebDriver driver){
		this.driver=driver;
	}
	public WebElement clickCarrierMarkups(){
		return WaitTool.waitForElementPresentAndDisplay(driver, carrierMarkups, 60);
	}
	public WebElement setValFor1to5(){
		return WaitTool.waitForElementPresentAndDisplay(driver, groung1to5, 60);
	}
	public WebElement setValFor6to10(){
		return WaitTool.waitForElementPresentAndDisplay(driver, groung6to10, 60);
	}
	public WebElement setValFor11to20(){
		return WaitTool.waitForElementPresentAndDisplay(driver, groung11to20, 60);
	}
	public WebElement setValFor21to30(){
		return WaitTool.waitForElementPresentAndDisplay(driver, groung21to30, 60);
	}
	public WebElement setValFor31to150(){
		return WaitTool.waitForElementPresentAndDisplay(driver, groung31to150, 60);
	}
	public WebElement setValForLargePackage(){
		return WaitTool.waitForElementPresentAndDisplay(driver, largePackage, 60);
	}
	public WebElement setValForAdditionalHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, additionalHandling, 60);
	}
	public WebElement setValForCylinderHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, cylinderHandling, 60);
	}
	public WebElement setValForResidentialPickup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, residentialPickup, 60);
	}
	public WebElement setValForBeyondRate(){
		return WaitTool.waitForElementPresentAndDisplay(driver, beyondRate, 60);
	}
	public WebElement setValForUSToCanada(){
		return WaitTool.waitForElementPresentAndDisplay(driver, usToCanada, 60);
	}
	public WebElement setValForResidentialDelivery(){
		return WaitTool.waitForElementPresentAndDisplay(driver, residentialDelivery, 60);
	}
	public WebElement setValForAdditionalFee(){
		return WaitTool.waitForElementPresentAndDisplay(driver, additionalFee, 60);
	}
	public WebElement setValFor31to150International(){
		return WaitTool.waitForElementPresentAndDisplay(driver, ground31to150International, 60);
	}
	public WebElement setValForCylinderInternational(){
		return WaitTool.waitForElementPresentAndDisplay(driver, cylinderHandlingInternational, 60);
	}
	public WebElement setValForResiPickInternational(){
		return WaitTool.waitForElementPresentAndDisplay(driver, residentialPickupInternational, 60);
	}	
	
	//**********************************************************************************************
	public WebElement setdollarFor1to5(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar1to5, 60);
	}
	public WebElement setInc_DecFor1to5(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec1to5, 60);
	}
	public WebElement setdollarFor6to10(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar6to10, 60);
	}
	public WebElement setInc_DecFor6to10(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec6to10, 60);
	}public WebElement setdollarFor11to20(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar11to20, 60);
	}
	public WebElement setInc_DecFor11to20(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec11to20, 60);
	}public WebElement setdollarFor21to30(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar21to30, 60);
	}
	public WebElement setInc_DecFor21to30(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec21to30, 60);
	}public WebElement setdollarFor31to150(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar31to150, 60);
	}
	public WebElement setInc_DecFor31to150(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec31to150, 60);
	}
	
	public WebElement setdollarFor150Plus(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollar150plus, 60);
	}
	public WebElement setInc_DecFor150Plus(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdec150plus, 60);
	}public WebElement setdollarLargePackage(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarlargepackage, 60);
	}
	public WebElement setInc_DecLargePackage(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdeclargepackage, 60);
	}public WebElement setdollarAdditionalHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollaradditionalhandling, 60);
	}
	public WebElement setInc_DecAdditionalHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecadditionalhandling, 60);
	}public WebElement setdollarCylinderHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarcylinderhandling, 60);
	}
	public WebElement setInc_DecCylinderHandling(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdeccylinderhandling, 60);
	}public WebElement setdollarResidentialPickup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarresidentialpickup, 60);
	}
	public WebElement setInc_DecResidentialPickup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecresidentialpickup, 60);
	}public WebElement setdollarBeyondRate(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarbeyondrate, 60);
	}
	public WebElement setInc_DecBeyondRate(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecbeyondrate, 60);
	}public WebElement setdollarUSToCanada(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarustocanada, 60);
	}
	public WebElement setInc_DecUSToCanada(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecustocanada, 60);
	}public WebElement setdollarResidentialDelivery(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollarresidentialdelivery, 60);
	}
	public WebElement setInc_DecResidentialDelivery(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecresidentialdelivery, 60);
	}public WebElement setdollarForAdditionalFee(){
		return WaitTool.waitForElementPresentAndDisplay(driver, dollaradditionalfee, 60);
	}
	public WebElement setInc_DecForAdditionalFee(){
		return WaitTool.waitForElementPresentAndDisplay(driver, incdecadditionalfee, 60);
	}
	
	public WebElement selectShipMethod(String xpathRow){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//th[text()='Ship Method']//following::th["+xpathRow+"]//select[1]"), 60);
	}

	public WebElement selectIncDec(String xpathRow){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//th[text()='Ship Method']//following::th["+xpathRow+"]//select[2]"), 60);
	}
	
	public WebElement groundMarkups(String xpathRow){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='markupsTab']//tr[1]//td["+xpathRow+"]//input[1]"), 60);
	}
	public WebElement nextDayAirEarlyAM(String xpathRow){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='markupsTab']//tr[7]//td["+xpathRow+"]//input[1]"), 60);
	}
	public WebElement standard(String xpathRow){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='markupsTab']//tr[8]//td["+xpathRow+"]//input[1]"), 60);
	}
	
	public WebElement clkUpdateBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Update')]"), 60);
	}
}
