package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ManageInvoices {
	
	WebDriver driver;
	
	public ManageInvoices(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageInvoices() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("link-manage-invoices"), 10);
	}
	
	public WebElement importSecondaries() {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-button k-upload-button']"), 10);
	}
	
	public WebElement uploadFiles() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='k-button k-upload-selected']"), 10);
	}
	
	public WebElement done() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@class='k-icon k-i-tick']"), 10);
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
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/div["+colIndex+"]"), 30);
	}
	
	public WebElement detail(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='ag-body']/descendant::div[@class='ag-body-container']/div["+rowIndex+"]/descendant::button"), 10);
	}
	
	//Secondary Invoice Detail Popup
	public WebElement getLabel(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='"+label+"']/following::td[1]"), 10);
	}
	
	public WebElement getSelect(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='"+label+"']/following::select[1]"), 10);
	}
	
	public WebElement backupDocumentation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::div[@class='k-button k-upload-button']"), 10);
	}
	
	public WebElement comments() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::textarea[@placeholder='Add your comment']"), 10);
	}
	
	public WebElement saveComment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::span[@id='btn-save-comment']"), 10);
	}
	
	public WebElement saveChanges() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::button[contains(text(),'Save Changes')]"), 10);
	}
	
	public WebElement savedComment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='order-comments']/descendant::tr[1]/td[1]"), 10);
	}
	
	public WebElement savedUserName() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='order-comments']/descendant::tr[1]/td[2]/strong"), 10);
	}
	
	public List<WebElement> backupDocumentGridData() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr"), 10);
	}
	
	public WebElement uploadName(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td[1]"), 10);
	}
	
	public WebElement dateTime(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td[2]"), 10);
	}
	
	public WebElement viewable(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td[3]/a[1]/i"));
	}
	
	public WebElement downloadable(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td[3]/a[2]/i"));
	}
	
	public WebElement removable(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='invoiceModal']/descendant::h4[text()='Backup Documentation']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td[3]/i"));
	}
	
	public WebElement dispute() {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='invoiceModal']/descendant::button[contains(text(),'Dispute')]"));
	}
	
	//Overage contact email popup
	public WebElement contactEmail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Please provide a contact email for dispute follow-up']/following-sibling::input"), 10);
	}
	
	public WebElement confirm() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Confirm')]"), 10);
	}
	
	
	
	
	
	
	
	
	
	
	//Upload Invoice Results Dialog
	public WebElement successMessage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='text-success']"), 30);
	}
	
	public WebElement ok() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='modal fade in'  and @role='dialog']/descendant::div[@class='modal-footer']/button"), 10);
	}
	
	
	
	
	
	
	
	
}
