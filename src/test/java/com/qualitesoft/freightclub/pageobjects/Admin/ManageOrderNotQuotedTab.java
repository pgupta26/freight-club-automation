package com.qualitesoft.freightclub.pageobjects.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class 	ManageOrderNotQuotedTab {
	
	WebDriver driver;

	public ManageOrderNotQuotedTab(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement notQuoted() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("incompleteOrders"), 30);
	}
	
	public WebElement orderIDFilter() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"center\"]/div/div[1]/div[3]/div/div[2]/div[2]/div[1]/div/input)[2]"), 30);
	}
	
	public WebElement complete() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[7]/div/button[1]"), 30);
	}
	
	public WebElement fulfillmentCarrier() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("addCustomQuote"), 30);
	}
	
	public WebElement serviceLevel() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[3]/div/div[1]/div[1]/div/div[1]/div/strong"), 180);
	}
	
	public WebElement carrierQuoteID() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("CarrierQuote"), 30);
	}
	
	public WebElement COGS() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[3]/div/div[2]/div/table/tbody/tr/td[4]/div/div/input"), 30);
	}
	
	public WebElement recurringExpiry() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("FrequencyExpiry"), 30);
	}
	
	public WebElement markupPercentage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[3]/div/div[1]/div[2]/div/div/input"), 30);
	}
	
	public WebElement deliveryFrequency() {
		WebElement test = null;
		try{
			test =  WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='shipment-review']/div[1]/div[3]/div/div[3]/div[1]/select"), 30);
		}catch(Exception ex) {
			ex.printStackTrace();
		}	
		return test;
	}

	public WebElement packageTypeHeading(String tableIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='shipment-review']/div/div["+tableIndex+"]/descendant::div[@class='panel-heading']/strong"), 30);
	}
	
	public WebElement getCellValueFromPackage(String tableIndex, String columnIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='shipment-review']/div/div["+tableIndex+"]/descendant::tbody/tr/td["+columnIndex+"]"), 30);
	}
	
	public WebElement packageTypeHeading1(String tableIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='shipment-items-panel well well-note']/div["+tableIndex+"]/descendant::div[contains(@class,'panel-header')]/h3"), 30);
	}
	
	public WebElement getCellValueFromPackage1(String tableIndex, String columnIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='shipment-items-panel well well-note']/div["+tableIndex+"]/descendant::div[contains(@class,'panel-header')]/descendant::tbody/tr["+columnIndex+"]/td"), 30);
	}
	
	public WebElement declaredValueCloneOrder() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Declared Value per Product:']/parent::div"), 30);
	}
	
	public WebElement totalWeight() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[7]/div/div/div/div/table/tbody/tr/td[1]"), 30);
	}
	
	public WebElement declaredValue() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[7]/div/div/div/div/table/tbody/tr/td[2]"), 30);
	}
	
	public WebElement handlingUnits() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[7]/div/div/div/div/table/tbody/tr/td[3]"), 30);
	}
	
	public WebElement condition() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[6]/div/div/div/div/table/tbody/tr/td[4]"), 30);
	}
	
	public WebElement shipmentInformation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Shipment Information']/following::textarea[1]"), 30);
	}
	
	public WebElement regulatoryDetails() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Regulatory Details')]/following::textarea"), 30);
	}
	
	public WebElement submitQuote() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Submit Quote']"), 30);
	}
	
	public WebElement unableToFulfil() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Unable to Fulfill']"), 30);
	}
	
	public WebElement gridStatus() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[5])[3]"), 60);

	}

	public WebElement getTotalCartoonCount() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Total Carton Count:']/following-sibling::span"), 60);

	}
}
