package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class GreenList {
	
	WebDriver driver;

	public GreenList(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageGreenLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Profile/Greenlist']"), 30);
	}
	public WebElement downloadGreenlistLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@class='link-important']"), 30);
	}
	
	public WebElement uploadButton() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//button[@class='k-button k-upload-selected']"), 30);
	}
	
	public WebElement OKButton() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//h3[text()='Upload Product Results']/following::button[1] | //h3[text()='Upload Greenlist Results']/following::button[1]"), 30);
	}
	
	public WebElement selectFiles() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-button k-upload-button']"), 30);
	}

}
