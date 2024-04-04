package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class ManageProducts {
	
	WebDriver driver;
	
	public ManageProducts(WebDriver driver) {
		this.driver=driver;
	}
	
	public void searchProductDataGrid(String productName) {
		
		ManageProducts manageProducts = new ManageProducts(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);

		SeleniumFunction.clickJS(driver, manageProducts.manageProductLink());
		quickQuote.acceptPopup();
		
		SeleniumFunction.click(manageProducts.filterGrid(7));
		SeleniumFunction.sendKeys(manageProducts.filterGrid(7), productName);
		manageProducts.filterGrid(7).sendKeys(Keys.ENTER);
		WaitTool.sleep(10);
	}
	
	public WebElement manageProductLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Product/UploadProducts')]"), 30);
	}
	
	public WebElement createproduct() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Create New Product')]"), 60);
	}
	
	public WebElement filterGrid(int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='VueTables__table table table-striped table-bordered table-hover']/thead/tr[2]/th["+columnIndex+"]/descendant::input"), 20);
	}
	
	public WebElement getGridData(int rowIndex, int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='VueTables__table table table-striped table-bordered table-hover']/tbody/tr["+rowIndex+"]/td["+columnIndex+"]"), 20);
	}
	
	public WebElement palletType(String palletType) {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-input items has-options full has-items']"), 30));
		WaitTool.sleep(3);
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(text(),'"+palletType+"')]"), 30);
	}
	
	public WebElement SKU() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='SKU']"), 30);
	}
	
	public WebElement productName() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Name']"), 30);
	}
	
	public WebElement declaredValue() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='DeclaredValue']"), 30);
	}
	
	public WebElement weight() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='product-pallets-table']/descendant::td[@class='form-inline weight']/descendant::input"), 30);
	}
	
	public WebElement length() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='product-pallets-table']/descendant:: input[@placeholder='Lenght']"), 30);
	}
	
	public WebElement width() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='product-pallets-table']/descendant:: input[@placeholder='Width']"), 30);
	}
	public WebElement height() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='product-pallets-table']/descendant:: input[@placeholder='Height']"), 30);
	}
	
	public WebElement cartoonweight(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"product-cartons-table\"]/tbody/tr/td[2]/div[1]/input)["+itemIndex+"]"), 30);
	}
	
	public WebElement cartoonlength(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"product-cartons-table\"]/tbody/tr/td[3]/div[1]/input)["+itemIndex+"]"), 30);
	}
	
	public WebElement cartoonwidth(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"product-cartons-table\"]/tbody/tr/td[3]/div[2]/input)["+itemIndex+"]"), 30);
	}
	
	public WebElement cartoonheight(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"product-cartons-table\"]/tbody/tr/td[3]/div[3]/input)["+itemIndex+"]"), 30);
	}
	
	public WebElement category(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"product-cartons-table\"]/tbody/tr/td[4]/select)["+itemIndex+"]"), 30);
	}
	
	public WebElement popupCateogory(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[text()='Got it!'])["+itemIndex+"]"), 30);
	}
	
	public WebElement addadditionalCarton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-info btn-xs'])[1]"), 30);
	}
	
	public WebElement saveproduct() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='createProductModal']/div/div/div[3]/div/div[2]/button"), 30);
	}
	
	public WebElement actions() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='VueTables__table table table-striped table-bordered table-hover']/tbody/descendant::button"), 30);
	}
	
	public WebElement editSku() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Edit SKU"), 30);
	}
	
	public WebElement deleteSku() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Delete SKU"), 30);
	}
	
	public WebElement deleteProductConfirmation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(text(),'Are you sure to delete product')]"), 30);
	}
	
	public WebElement deleteProductAccept() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[text()='Yes'][1]"), 30);
	}
	
	public WebElement noMatchingRecord() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//td[text()='No matching records']"), 30);
	} 
	
	public WebElement exportProduct() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Export Products in Excel format"), 30);
	} 
	
	public WebElement downloadProductTemplate() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Content/Template/Import_PIDs_Template_V7-R18.xlsm']"), 30);
	} 
	
	public WebElement paginatioTotalRows() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//p[contains(@class,'VuePagination__count')]"), 30);
	} 
	
	public WebElement selectFiles() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-button k-upload-button']"), 30);
	}
	
	public WebElement uploadButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='k-button k-upload-selected k-primary']"), 30);
	}
	
	public WebElement successMessage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='text-success']"), 30);
	}
	
	public WebElement OKButton() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//span[text()='OK']"), 30);
	}
	
	
	

}
