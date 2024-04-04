package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class ManagerOrderPage {
	
	WebDriver driver;

	public ManagerOrderPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement manageOrdersLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Order/Index')]"), 30);
	}
	
	public WebElement orderIdFilter() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])[1]"), 30);
	}
	
	public WebElement ExpandMenupage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@title='Click to retract or expand the page menu']"), 30);
	}
	
	public WebElement ViewDetail() {
		try {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[text()='Detail'])[1]"), 30);
		}catch(Exception ex) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[text()='View Detail'])[1]"), 30);
		}
	}
	
	public WebElement viewDetalsOpenQuotes() {
		try {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::button[text()='Detail']"), 30);
		}catch(Exception ex) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::button[text()='View Detail']"), 30);
		}
	}
	
	public WebElement viewDetalsCustomOrders() {
		try {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='incompleteGrid']/descendant::button[text()='Detail']"), 30);
		}catch(Exception ex) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='incompleteGrid']/descendant::button[text()='View Detail']"), 30);
		}
	}
	
	public void orderIDFilter(String orderid) {
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])[1]"), 60), orderid);
	}
	
	public WebElement orderIDGridValueFirstRow() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='ag-cell-no-focus ag-cell ag-cell-not-inline-editing ag-cell-value' and @colid='ID'])[1]"), 20);
	}
	
	public WebElement ActionButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-primary btn-xs dropdown-toggle'])[1]"), 30);
	}
	
	public WebElement billOfLoadingPdf() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Download Bill of Lading - PDF"), 10);
	}
	
	public boolean IsbillOfLoadingPdfPresent() {
		WaitTool.sleep(2);
		return WaitTool.isElementPresentAndDisplay(driver, By.linkText("Download Bill of Lading - PDF"));
	}
	
	public WebElement shippingLabelsPdf() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Download Shipping Label(s) - PDF"), 10);
	}
	
	public WebElement shippingLabelsZPL() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Download Shipping Label(s) - ZPL"), 10);
	}
	
	public boolean requestCancellation() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[text()='Request Cancellation']"));
	}
	
	public WebElement clone() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[text()='Clone']"), 30);
	}
	
	public WebElement customerPONumber() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("poNumber"), 30);
	}
	
	public WebElement cloneNow() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"cloneModal\"]/div/div/form/div[2]/button[2]"), 30);
	}
	
	public WebElement customerPO() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[4])[1]"), 30);
	}
	
	public WebElement reQuote() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[text()='Re-Quote']"), 30);
	}
	
	public WebElement notQuoted() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("incompleteOrders"), 30);
	}
	
	public WebElement customerPO_NotQuoted() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[3])[3]"), 30);
	}
	
	public WebElement openQuotes() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='#quickOrders']"), 30);
	}
	
	public WebElement quotedPrice() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='quickOrders']/descendant::div[@class='ag-body-container']/div/div[6]"), 30);
	}
	
	public WebElement actions() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[7]/div/button"), 30);
	}
	
	public WebElement book() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[7]/div/ul/li[1]"), 30);
	}
	
	public WebElement delete() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"center\"]/div/div[4]/div[3]/div/div/div[1]/div[7]/div/ul/li[2]"), 30);
	}
	
	public WebElement customerPOGridFilter() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='quickOrders']/descendant::input[@ref='eColumnFloatingFilter'])[2]"), 30);
	}
	
	public WebElement openQuotesActions() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='center']/div/div[4]/div[3]/div/div/div[1]/div[9]/div/button"), 30);
	}
	
	public WebElement openQuotesBook() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Book"), 30);
	}
	
	//***************************************Initiate Claim***********************************
	
	public WebElement listingPageHeader(int index){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[contains(@class,'ag-header-cell-text')])["+index+"]"), 30);
	}
	
	public void initiateClaim() {
		SeleniumFunction.clickJS(driver,WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Initiate Claim"), 10));
	}
	
	
	////values are PendingPickup and Booked
	public void selectStatus(String value){
		WebElement button = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='ms-parent ']//button)[1]"), 120);
		SeleniumFunction.click(button);
		WebElement option = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@value='"+value+"']"), 10);
		SeleniumFunction.click(option);
		WaitTool.sleep(2);
		SeleniumFunction.click(button);
		WaitTool.sleep(2);
	}
	
	public WebElement initiateClaimPopup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h4[@id='initiateClaimLabel']"), 30);
	}

	public WebElement selectClaimType(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='claimType']"), 30);
	}
	
	public void clickInitiateClaimButton() {
		SeleniumFunction.clickJS(driver,WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(@class,'input-group-btn')]//button"), 10));
	}
	
	//********************************Pagination***************
	
	public void clickPaginationButton(String tableName, int index){
		WebElement button = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@class='paging-panel pager-visible']//button)["+index+"]"), 20);
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(button);
	}
	
	public String getPagiationTotalRows(String tableName){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//span[contains(@class,'summary-panel')])[2]"), 20));
	}
	
	public WebElement setPageNumber(String tableName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@class='paging-panel pager-visible']//input[@type='number'])"), 20);
	}
	
	public String getPageNumber(String tableName){
		WebElement inputPage = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@class='paging-panel pager-visible']//input[@type='number'])"), 20);
		return SeleniumFunction.getAttribute(inputPage, "value");
	}
	
	public Integer getRowsCount(String tableName){
		List<WebElement> elements = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@class='ag-body-viewport'])//div[contains(@class,'ag-row')]"), 30);
		return elements.size();
	}
	
	public String getTotalPages(String tableName){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@class='paging-panel pager-visible']//span[contains(@class,'action-panel')])[1]"), 20));
	}
	
	//**************Searching************
	// values 1 order id, 3 Way bill, 4 tracking
	public WebElement searchFields(String index) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@ref='eColumnFloatingFilter'])["+index+"]"), 60);
	}
	
	//index 2, 9, 10
	public String getColumnData(String index){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(@class,'ag-cell-value')])["+index+"]"), 20));
	}
	
	//************Page filters header
	
	public String getFilterHeader(int index){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='ag-header-cell ag-header-cell-sortable'])["+index+"]"), 20));
	}
	
	//******************************Request Reroute*************************
	//Delivery Reroute Request

	public void clickRequestRerouteLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Request Reroute"), 10));
	}
	
	public void clickClosePopupButton() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='rerouteModal']//button[contains(@class,'close')]"), 10));
	}
	
	public void clickConfirmReroute() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Confirm Reroute"), 10));
	}
	
	public void clickDeniedReroute() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Reroute Denied"), 10));
	}
	
	public WebElement requestReroutePopup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h4[@id='rerouteLabel']"), 30);
	}
	
	public WebElement deniedReroutePopup(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='rerouteDeniedModal']//h4[@id='rerouteLabel']"), 30);
	}
	
	public void fillRerouteRequestForm(String labelName, String text){
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h4[@id='rerouteLabel']//following::label[contains(text(),'"+labelName+"')]//parent::div//input"), 60), text);
	}
	
	public String getFieldValidation(int index){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h4[@id='rerouteLabel']//following::span[@class='form-group-message'])["+index+"]"), 60));
	}
	
	public void clickSendRequestBtn(){
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h4[@id='rerouteLabel']//following::button[@class='btn btn-primary pull-right'])[1]"), 30));
	}
	
	public boolean requestRerouteIsPresent() {
		return WaitTool.isElementPresentAndDisplay(driver, By.linkText("Request Reroute"));
	}
	
	public WebElement checkboxStatus(String fieldName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'"+fieldName+"')]//input"), 60);
	}
	
	public WebElement locationType(String locationType){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='rerouteModal']//label[contains(text(),'"+locationType+"')]//parent::div//select"), 60);
	}
	
	public boolean dropOffDropdownStatus(String locationType){
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='rerouteModal']//label[contains(text(),'"+locationType+"')]//parent::div//select"));
	}
	
	public String verifyToastMessage(){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='toast toast-success']"), 180));
	}
	
	public void setRerouteDeniedReason(String text){
		SeleniumFunction.sendKeys(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@class='form-control form-input']"), 60), text);
	}
	
	public void clickProceedDenialBtn(){
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='rerouteDeniedModal']//button[contains(@class,'btn btn-primary')]"), 30));
	}
	
	//*******************admin dashboard feedback popup
	public void acceptFeedbackPopup(){
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='step-0']//button[@class='btn btn-primary']"), 15));
	}
	
	public boolean acceptFeedbackPopupStatus(){
		return WaitTool.waitForElementBoolean(driver, By.xpath("//div[@id='step-0']//button[@class='btn btn-primary']"), 15);
	}
	
	//******************Cancel Order*********
	public void clickCancelLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Cancel"), 10));
	}
	
	public void clickRequestCancelLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Request Cancellation"), 10));
	}
	
	public WebElement confirmCancelOrderBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Confirm')]//parent::button"), 10);
	}
	
	public WebElement getCancelOrderPopupHeader(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(@class,'vue-dialog-confirm modal')]//child::div[@class='modal-header']//h5"), 10);
	}
	
	public WebElement getCancelOrderPopupBody(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(@class,'vue-dialog-confirm modal')]//child::div[@class='modal-body']"), 10);
	}
	
	public void clickConfirmCancellationLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Confirm Cancellation"), 10));
	}
	
	public void clickWithdrawCancellationLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Withdraw Cancellation Request"), 10));
	}
	
	public WebElement trackingNoLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@colid='TrackingNos']//div//div//div//a[@target='_blank']"), 30);
	}
	//******************************Export CSV file***********
	
	public WebElement clickExportOrdersBtn(String tableName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+tableName+"']//a[@class='btn btn-default btn-sm']"), 10);
	}
	
	public WebElement openQuoteTab(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='quotedOrders']"), 10);
	}
	
	public WebElement customOrdersTab(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='incompleteOrders']"), 10);
	}
	
	public WebElement PagiationTotalRows (){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='paging-panel pager-visible']//span[contains(@class,'summary-panel')])[2]"), 20);
	}

	//quickOrders
	public WebElement searchFieldsOnTable(String tableName, String index) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//input[@ref='eColumnFloatingFilter'])["+index+"]"), 60);
	}

	public String getColumnDataFromTable(String tableName, String index){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[contains(@class,'ag-cell-value')])["+index+"]"), 20));
	}
	
	public WebElement getOrderId(String tableName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@row='9'])//div[@colid='ID']"), 60);
	}
	
	public WebElement getOrderIdIncompleteOrderPage(String tableName){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//div[@row='0'])//div[@colid='ID']"), 60);
	}
	
	public WebElement actionButton(String tableName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//button[@class='btn btn-primary btn-xs dropdown-toggle'])[1]"), 30);
	}
	
	public void clickDeleteLink() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("Delete"), 10));
	}
	
	public WebElement deleteButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[text()='Delete'])[1]"), 10);
	}
	
	public WebElement noRowsText(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='overlay']//span)[1]"), 10);
	}
	
	public WebElement detailBtn(String tableName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='"+tableName+"']//button[text()='Detail'])[1]"), 30);
	}

	public ManagerOrderPage clickBookButton() {

		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.linkText("Book")));
		return this;
	}
}
