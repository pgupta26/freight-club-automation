package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class BulkOrdersPage {
	
	WebDriver driver;
	
	public BulkOrdersPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement bulkOrdersLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("link-bulk-orders"), 10);
	}
	
	public WebElement selectFilesButton() {	
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@aria-label='Select files...']"), 10);
	} 
	
	public WebElement uploadButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div[2]/div/button[2]"), 50);
	}
	
	public WebElement automaticBookButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='v-switch-core']"), 10);
	} 
	
	public boolean isAutomationBookButtonPresent() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@class='v-switch-core']"));
	}
	
	public WebElement downloadLabelsButton(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/div[7]/descendant::a"), 30); 
	}
	
	public WebElement downloadBOLButton(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/div[8]/descendant::a"), 30); 

	}
	
	public WebElement refreshView() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Refresh View"), 30); 

	}
	
	public WebElement quoteAndBookButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[text()='Quote and Book']"), 30); 

	}
	
	
	
	

}
