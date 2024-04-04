package com.qualitesoft.freightclub.pageobjects.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ManageOrderOpenQuotesPage {
	
	WebDriver driver;

	public ManageOrderOpenQuotesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageOrdersLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Order/Index')]"), 30);
	}
	
	public WebElement openQuotes() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='#quickOrders']"), 30);
	}
	
	public WebElement orderIdFilter() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[1]/div[3]/div/div[2]/div[2]/div[1]/div/input"), 30);
	}
	
	public WebElement quotedPrice() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::div[@class='ag-body-container']/div/div[9]"), 30);
	}
	
	public WebElement actions() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::div[@class='ag-body-container']/div/div[10]/div/button"), 30);
	}
	
	public WebElement book() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::div[@class='ag-body-container']/div/div[10]/div/ul/li[1]"), 30);
	}
	
	public WebElement delete() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[10]/div/ul/li[2]"), 30);
	}
}
