package com.qualitesoft.freightclub.pageobjects.Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ManageBillingPage {

	WebDriver driver;

	public ManageBillingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<WebElement> createBillPopupValidation(){
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='createBillModal']//span[@class='form-group-message']"), 60);
	}
	
	public WebElement toastMessage(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='toast-message']"), 60);
	}
	
	public WebElement manageBillingLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Billing/Index')]"), 30);
	}
	
	public WebElement newBillBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-primary btn-sm']"), 60);
	}
	
	public WebElement billDate(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='BillDate']"), 60);
	}
	
	public WebElement carrierCode(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='Carrier Code']//following::div[contains(@class,'selectize-input')])[1]"), 60);
	}
	
	public WebElement carrierCodeOptions(String carrierName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@data-value='"+carrierName+"']"), 60);
	}
	
	public WebElement documentNumber(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='DocumentNumber']"), 60);
	}
	
	public WebElement poNumber(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='PONumber']"), 60);
	}
	
	public WebElement netAmountDue(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='NetAmountDue']"), 60);
	}
	
	public WebElement freight(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Freight']"), 60);
	}
	
	public WebElement type(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='Type']//following::div[contains(@class,'selectize-input')])[1]"), 60);
	}
	
	public List<WebElement> getListOfBillTypes(){

		List<WebElement> listOfBillTypes = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='createBillModal']//label[text()='Type']/following::div[@class='selectize-dropdown-content']/div"), 30);
		return listOfBillTypes;
	}
	
	public WebElement closeBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='createBillModal']//button[@class='btn btn-default']"), 60);
	}
	
	public boolean closeBtnStatus(){
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='createBillModal']//button[@class='btn btn-default']"));
	}
	
	public WebElement addBillBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='createBillModal']//button[@class='btn btn-primary']"), 60);
	}
	
	public WebElement documentNumTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])[3]"), 30);
	}
	
	public WebElement orderNumTextBox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])[1]"), 30);
	}
	
	public WebElement importFileLink(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@class='link-important']"), 60);
	}
	
	public WebElement uploadFile(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-dropzone']//div//input[@type='file']"), 60);
	}
	
	public WebElement selectFile(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-dropzone']//div"), 60);
	}
	
	public WebElement uploadBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='k-button k-upload-selected']"), 60);
	}
	
	//*********************Import Bill confirmation popup***************

	public WebElement confirmationHeader(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='importBillssConfirmation']//h3"), 60);
	}
	
	public WebElement confirmationSuccessMessage(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='importBillssConfirmation']//div[@class='text-success']"), 60);
	}
	
	public WebElement okButton(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='importBillssConfirmation']//button[@class='btn btn-primary']"), 60);
	}
	
	//**************View button and popup fields***************
	
	public WebElement viewBillButton(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(@class,'btn btn-xs')]"), 60);
	}
	
	public WebElement modalPopupHeader(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//h4"), 60);
	}
	
	public WebElement getBillDetail(int rowNum){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//table//tr["+rowNum+"]//td[2]"), 60);
	}
	
	public WebElement setGpBillId(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//table//tr[10]//td[2]//input"), 60);
	}
	
	public WebElement setFileStatus(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='fileStatus']"), 60);//option[contains(text(),'Processed')]
	}
	
	public WebElement setBillStatus(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='billStatus']"), 60);////option[contains(text(),'SentToGP')]
	}
	
	public WebElement setComment(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//textarea"), 60);
	}
	
	public WebElement updateComment(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='order-comments']//tr//td[1]//textarea"), 60);
	}
	
	public WebElement saveCommentBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@id='btn-save-comment']"), 60);
	}
	
	public WebElement saveStatusChangeBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//button[@class='btn btn-primary']"), 60);
	}
	
	public WebElement closeModalBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//button[@class='btn btn-default pull-left']"), 60);
	}
	
	public boolean closeModalBtnStatus(){
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='billModal']//button[@class='btn btn-default pull-left']"));
	}
	
	public WebElement getAddedCommentText(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='order-comments']//tr//td[1]"), 60);
	}
	
	public WebElement iconsOnAddedComment(int dataNum, int index){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='order-comments']//tr//td["+dataNum+"]//a["+index+"]"), 60);
	}
	
	public WebElement deleteButton(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Delete')]"), 60);
	}
	
	//*****************************ExportCSV Btn***************
	
	public WebElement exportCsvBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-default btn-sm']"), 60);
	}
	
	public WebElement getTotalRecords(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@ref='lbRecordCount']"), 60);
	}
	
	//************************Pagination*************************
	
	public WebElement paginationButton(int index){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='south']//button)["+index+"]"), 60);
	}
	
	public WebElement paginationSummary(int index){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[@ref='eSummaryPanel']//span)["+index+"]"), 60);
	}
	
	public WebElement pagiationSummaryText(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@ref='eSummaryPanel']"), 60);
	}
	
	public WebElement totalPages(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@ref='lbTotal']"), 60);
	}
}
