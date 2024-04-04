package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class Mailinator {
	
	WebDriver driver;

	public Mailinator(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement searchBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Enter Public Mailinator Inbox'] | //input[@id='inbox_field']"), 30);
	}
	
	public WebElement searchBoxYopMail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("login"), 30);
	}
	
	public WebElement goButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@value='Search for public inbox for free']"), 30);
	}
	
	public WebElement go() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'GO')]"), 30);
	}
	
	public WebElement firstMail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='table-striped jambo_table']/tbody/tr"), 30);
	}
	
	public WebElement firstYopEmail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[text()='Reset Password ( QA TESTING )']"), 30);
	}
	
	public WebElement mailBody() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//body/div"), 10);
	}
	
	public WebElement backToInbox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("Back to Inbox"), 10);
	}
	
	public WebElement firstMail1() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(text(),'Your order has been booked. (QA Testing)')]"), 30);
	}
	
	public WebElement reviewedOrderMsg() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("/html/body/p[2]"), 30);
	}
	
	public WebElement customQuoteOrderMsg() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("/html/body/h2[2]/span"), 30);
	}
	
	public WebElement customQuoteOrderDetails() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("/html/body/p[1]"), 30);
	}
	
	public WebElement deleteEmail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@class,'btn-delete')]"), 30);
	}
	
	public WebElement orderConfirmationMsg() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("/html/body/p[3]"), 30);
	}
}
