package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ShipmentReviewOrderPage {
	
	WebDriver driver;

	public ShipmentReviewOrderPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement customerPONumber() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[2]/div[1]/div/div[1]/div/div[2]"), 30);
	}
	
	public WebElement totalAmount() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[2]/div[2]/div/h1"), 30);
	}
	
	public WebElement totalWeight() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[6]/div/div/div/div/table/tbody/tr/td[1]"), 30);
	}
	
	public WebElement declaredValue() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[6]/div/div/div/div/table/tbody/tr/td[2]"), 30);
	}
	
	public WebElement handlingUnits() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[6]/div/div/div/div/table/tbody/tr/td[3]"), 30);
	}
	
	public WebElement shipmentInformation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Shipment Information']/following::textarea[1]"), 30);
	}
	
	public WebElement regulatoryDetails() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Regulatory Details')]/following::textarea"), 30);
	}
	
	public WebElement book() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Submit Quote']"), 30);
	}
	
	public WebElement book1() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"shipment-review\"]/div[1]/div[9]/div[2]/button"), 30);
	}
}
