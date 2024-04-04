package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ManageOverages {
	
	WebDriver driver;
	
	public ManageOverages(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageOverages() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("link-manage-overages"), 10);
	}
	
	public WebElement OrderIDTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])[1]"), 30);
	}
	
	public WebElement TotalBilledTextBox(int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])["+columnIndex+"]"), 30);
	}
	
	public List<WebElement> gridHeaders() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[contains(@class,'ag-header-cell-sortable')]/descendant::span[@ref='eText']"), 10);
	}
	
	public WebElement gridData(int rowIndex, int colIndex) {
		colIndex = colIndex + 2;
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/div["+colIndex+"]"), 60);
	}
	
	public WebElement viewEdit(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/descendant::button"), 10);
	}
	
	
	
	

}
