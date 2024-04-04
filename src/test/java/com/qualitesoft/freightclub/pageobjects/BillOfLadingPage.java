package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class BillOfLadingPage {

WebDriver driver;
	
	public BillOfLadingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement billOfLading() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("link-bol"), 10);
	}
	
	public WebElement wayBillTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@data-text-field='WaybillNo']"), 10);
	}
	
	public WebElement customerPoTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@data-text-field='ClientRefNo']"), 10);
	}
	
	public WebElement orderIdTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@data-text-field='OrderId']"), 10);
	}
	
	public WebElement searchTextBox(int index) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//thead[@role='rowgroup']//tr[2]//th["+index+"]//input)[1]"), 10);
	}
	
	public List<WebElement> getNumberOfRows(){
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='bill-of-lading-grid']//tbody//tr"), 20);
	}
	
	public WebElement gridData(int rowIndex, int colIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//tbody[@role='rowgroup']//tr["+rowIndex+"]/td["+colIndex+"]"), 10);
	}
	
	//*******************************Pagination**************************
	
	
	public WebElement goToFirstPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@title='Go to the first page']"), 10);
	}
	
	public WebElement goToLastPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@title='Go to the last page']"), 10);
	}
	
	public WebElement goToNextPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@title='Go to the next page']"), 10);
	}
	
	public WebElement goToPreviousPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@title='Go to the previous page']"), 10);
	}
	
	public WebElement paginationBtn(int pageNum) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@data-page='"+pageNum+"']"), 10);
	}
	
	public WebElement getPageInfo() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(@class,'k-pager-info k-label')]"), 10);
	}
	
}
