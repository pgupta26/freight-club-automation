package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class UpdateTrackingStatusPage {

	WebDriver driver;

	public UpdateTrackingStatusPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getField(String fieldName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following-sibling::input"), 30);
	}
	
	public WebElement getDropOffFields(String fieldName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='"+fieldName+"']/following-sibling::input)[2]"), 30);
	}
	
	public WebElement saveOrder() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Save Order']"), 20);
	}

	public WebElement dateOfUpdateTextfield() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[1]/div[1]/input[@id='UpdateDate']"), 30);
	}
	public WebElement trackingId() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[2]/div[1]/select[@id='OrderStatusType']"), 30);
	}
	public void trackingIdDropdown(String status) {
		if(status.equalsIgnoreCase("PickedUp")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[2]/div[1]/select[@id='OrderStatusType']/option[@value='PickedUp']")));
		}
		else if(status.equalsIgnoreCase("InTransit")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[2]/div[1]/select[@id='OrderStatusType']/option[@value='InTransit']")));
		}
		else if(status.equalsIgnoreCase("Delivered")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[2]/div[1]/select[@id='OrderStatusType']/option[@value='Delivered']")));
		}
		else if(status.equalsIgnoreCase("Refused")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//div[@id='statusAccordionPanel']/div[@class='panel-body']/div[@class='row']/div[@class='col-xs-12']/div[1]/div[2]/div[1]/select[@id='OrderStatusType']/option[@value='Refused']")));
		}		
	}
	public WebElement cityTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='City']"), 30);
	}

	public WebElement zipTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='ZipCode']"), 30);
	}

	public WebElement stateTextfield() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='ProvinceState']"), 30);
	}

	public WebElement submitButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Update Tracking')]"), 30);
	}
}
