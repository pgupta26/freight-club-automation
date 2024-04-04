package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement contactUsLink() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(.,'CONTACT US')]"), 30);
	}

	public WebElement signInLink() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Account/Login')]"), 30);
	}
	public WebElement signInLinkProd() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='https://app.freightclub.com/QuickQuote/QuickQuote']"), 30);
	}
	public WebElement GetFreeQuoteLink() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='https://app.freightclub.com/Home/QuickQuote']"), 30);
	}	
	public WebElement logoff() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"top-bar\"]/ul[2]/li[4]/a"), 30);
	}	
	
	public WebElement trackShipmentButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@class,'track-btn track-one')]"), 30);
	}

}
