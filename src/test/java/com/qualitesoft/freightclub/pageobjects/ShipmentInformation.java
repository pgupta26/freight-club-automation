package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ShipmentInformation {

	

	WebDriver driver;

	public ShipmentInformation(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageOrdersLink() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Order/Index')]"), 30);
	}
	
}
