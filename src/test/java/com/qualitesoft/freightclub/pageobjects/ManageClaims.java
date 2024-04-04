package com.qualitesoft.freightclub.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.SoftAssertion;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;

public class ManageClaims {
	
	WebDriver driver;
	
	public ManageClaims(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement manageClaimsLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Claim/Index')]"), 30);
	}
	
	public WebElement getLabel(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'"+label+"')]/following-sibling::span | //label[text()='"+label+"']/following-sibling::div"), 30);
	}
	
	public WebElement getField(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following-sibling::input | //label[text()='"+label+"']/following::input[1]"), 10);
	}
	
	public WebElement getSelect(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following-sibling::div/descendant::div[contains(@class,'has-item')]"), 30);
	}
	
	public void selectByVisibleText(String fieldName, String value) {

		WebElement dropDown = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following-sibling::div/div"), 30);
		SeleniumFunction.moveToElement(driver, dropDown);
		SeleniumFunction.click(dropDown);
		//SeleniumFunction.clickJS(driver, dropDown);
		List<WebElement> elementList = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='"+fieldName+"']/following::div[@class='selectize-dropdown-content']/div"), 20);
		
		for(WebElement element: elementList) {
			Log.info(element.getText());
			if(element.getText().equals(value)) {
				SeleniumFunction.clickJS(driver, element);
				break;
			}
		}
		
		driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
		WaitTool.sleep(2);
	}
	
	public WebElement setSelect(String label, String value) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[text()='"+value+"']"), 10);
	}
	
	public WebElement pendingDocumentation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Pending Documentation:']/following-sibling::p"), 10);
	}
	
	public WebElement addiotionalDocumentType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@class='form-input']"), 10);
	}
	
	public WebElement comments() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@placeholder='Please provide additional details/reasons for your claim and explain the amount of your claim in the comment section']"), 10);
	}
	
	public WebElement saveComment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Comment')]"), 10);
	}
	
	public WebElement saveChanges() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Changes')]"), 10);
	}
	
	public WebElement savedComment() {
		List<WebElement> rows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::div[@class='form-group padding-top-50']/descendant::tr"), 30);
		int rowsCount = rows.size();
		int rowIndex = rowsCount; 
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::div[@class='form-group padding-top-50']/descendant::tr["+rowIndex+"]/td[1]"), 10);
	}
	
	public WebElement savedUserName() {
		List<WebElement> rows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::div[@class='form-group padding-top-50']/descendant::tr"), 30);
		int rowsCount = rows.size();
		int rowIndex = rowsCount; 
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::div[@class='form-group padding-top-50']/descendant::tr["+rowIndex+"]/td[2]/strong"), 10);
	}
	
	public java.util.List<WebElement> documentsGrid() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr"), 10);
	}
	
	public WebElement uploadName(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr["+rowIndex+"]/td[1]"), 10);
	}
	
	public WebElement dateTime(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr["+rowIndex+"]/td[2]"), 10);
	}
	
	public WebElement viewable(String label) {
		List<WebElement> rows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr | //label[text()='"+label+"']/following-sibling::table/tr"), 30);
		int rowsCount = rows.size();
		int rowIndex = rowsCount;
		return WaitTool.returnWebElement(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr["+rowIndex+"]/td[3]/a[1] | //label[text()='"+label+"']/following-sibling::table/tr["+rowIndex+"]/td[4]/a[1]"));
	}
	
	public WebElement downloadable(String label) {
		List<WebElement> rows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr | //label[text()='"+label+"']/following-sibling::table/tr"), 30);
		int rowsCount = rows.size();
		int rowIndex = rowsCount;
		return WaitTool.returnWebElement(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr["+rowIndex+"]/td[3]/a[2] |//label[text()='"+label+"']/following-sibling::table/tr["+rowIndex+"]/td[4]/a[2]"));
	}
	
	public WebElement removable(String label) {
		return WaitTool.returnWebElement(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr/td[3]/i | //label[text()='"+label+"']/following-sibling::table/tr/td[4]/i"));
	}
	
	public WebElement addiotionalDocumentSave(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//label[text()='Additional Documents:']/following-sibling::table/tr["+rowIndex+"]/td[4]/i[1]"));
	}
	
	public void clickSubmitForReview() {
		WebElement submitForReview = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Submit for Review')]"), 60);
		SeleniumFunction.moveToElement(driver, submitForReview);
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.executeJS(driver, "window.scrollBy(2000,0)");
		SeleniumFunction.clickJS(driver, submitForReview);
	}
	
	public void clickSaveChanges() {
		WebElement saveChanges = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Changes')]"), 60);
		SeleniumFunction.moveToElement(driver, saveChanges);
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.executeJS(driver, "window. scrollBy(2000,0)");
		SeleniumFunction.clickJS(driver, saveChanges);
	}
	
	public WebElement upscInsuranceCoverage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='UPSC Insurance Coverage:']/following-sibling::select"), 10);
	}
	
	//Sent to carrier popup
	public WebElement getLabelFromModal(String modalId, String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::label[text()='"+label+"']/following-sibling::input | //div[@id='sentToCarrierModal']/descendant::label[text()='"+label+"']/following-sibling::textarea"), 10);
	}
	
	public WebElement sendEmail(String modalId) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::button[contains(text(),'Send Email')]"), 10);
	}
	
	//Pending Documentation Popup
	public WebElement body(String modalId) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::textarea"), 60);
	}
	
	//Validation Message
	public WebElement toastValidation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("toast-container"), 10);
	}
	
	public WebElement requireFields(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/parent::div"), 30);
	}
	
	//Claims Contact Email popup
	public WebElement confirm() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Confirm')]"), 10);
	}
	
	//Email Communication Section
	public WebElement gridData(String label, int columnIndex) {
		List<WebElement> rows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr | //label[text()='"+label+"']/following-sibling::table/tr"), 30);
		int numberOfRows = rows.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/parent::div/following-sibling::table/tr["+numberOfRows+"]/td["+columnIndex+"] | //label[text()='"+label+"']/following-sibling::table/tr["+numberOfRows+"]/td["+columnIndex+"]"), 60);
	}
	
	public WebElement documentUploadField(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following::div[@class='k-button k-upload-button'][1]"), 10);
	}
	
	public void addComment(String commentMessage) {
		ManageClaims manageClaims = new ManageClaims(driver);

		//Type comments
		SeleniumFunction.moveToElement(driver, manageClaims.saveComment());
		SeleniumFunction.sendKeys(manageClaims.comments(), commentMessage);

		//Click on save comment
		SeleniumFunction.click(manageClaims.saveComment());
	}
	
	public void verifySavedComment(String commentMessage) {
		SeleniumFunction.moveToElement(driver, this.savedComment());
		SoftAssertion.assertEquals(savedComment().getText(), commentMessage);
		Assert.assertTrue(savedUserName().getText().contains(InitializeTest.fcusername));
	}
	
	public void verifyEmailCommunicationSent(String message, int rowIndex) {
		
		  WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.
		  xpath("//label[text()='Email Communication']/parent::div/following-sibling::table/tr["
		  +rowIndex+"]/td[1] | //label[text()='Email Communication']/following-sibling::table/tr["
		  +rowIndex+"]/td[1]"), 60); Log.info("Actual Email: "+element.getText());
		  Assert.assertTrue(element.getText().contains(message));
		  Assert.assertTrue(this.gridData("Email Communication",2).getText().contains(InitializeTest.fcusername));
		 
	}
	
	public void verifyManageClaimsGrid(Xls_Reader xr, int rowIndex, ArrayList<String> expectedGridHeader) {
		
		
		ManageOverages manageOverages = new ManageOverages(driver);
		CommonOps commonOps = new CommonOps();
		int i = rowIndex;
		
		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String claimID = xr.getCellData("ClaimDetail", "ClaimsID", i);
		String claimStatus = xr.getCellData("ClaimDetail", "ClaimStatus", i);

		//navigate to manage claims pageand search order ID 
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
				
		//verify grid headers
		ArrayList<String> actualGridHeader = new ArrayList<String> (); 
		List<WebElement> gridHeaders = manageOverages.gridHeaders(); 
		for(WebElement header: gridHeaders) {
			Log.info(header.getText());
			actualGridHeader.add(header.getText());
		}
		
		Collections.sort(actualGridHeader);
		Collections.sort(expectedGridHeader);
		Assert.assertEquals(actualGridHeader, expectedGridHeader);
		ScreenShot.takeScreenShot(driver, "Manage claims grid"+JavaFunction.getRandomNumber(1, 1000));
  
		//verify grid claim status and other fields in data grid 
		SoftAssertion.assertEquals(orderId, manageOverages.gridData(1, 0).getText());
		SoftAssertion.assertEquals(claimID, manageOverages.gridData(1, 1).getText());
		
		if(claimStatus.equals("Initiated")) {
			SoftAssertion.assertEquals(claimStatus, manageOverages.gridData(1, 3).getText());
		}else {
			SoftAssertion.assertEquals(claimStatus, manageOverages.gridData(1, 4).getText());
		}
		
	}
	
	public void verifyUploadedDocument(String fieldName, String documentName) {
		
		ManageClaims manageClaims = new ManageClaims(driver);
		
		Xls_Reader xr;
		xr=new Xls_Reader("testdata\\FCfiles\\stg\\ManageClaims\\ManageClaims.xlsx");
		int i=Integer.parseInt(InitializeTest.Row);
		
		 String documentType = xr.getCellData("ClaimDetail", "DocumentType", i);
		
		UseAssert.assertEquals(manageClaims.gridData(fieldName, 1).getText(), documentName);
		if(fieldName.equals("Additional Documents:")) {
			UseAssert.assertEquals(manageClaims.gridData(fieldName, 2).getText(), documentType);
			Assert.assertTrue(manageClaims.gridData(fieldName, 3).getText().contains(JavaFunction.currentDate()));
		} else {
			Assert.assertTrue(manageClaims.gridData(fieldName, 2).getText().contains(JavaFunction.currentDate()));
		}
		Assert.assertTrue(manageClaims.viewable(fieldName).isDisplayed());
		Assert.assertTrue(manageClaims.downloadable(fieldName).isDisplayed());
		
		if(!fieldName.equals("Claim Response Documents:")) {
			Assert.assertTrue(manageClaims.removable(fieldName).isDisplayed());
		} else {
			boolean flag=true;
			try {
				manageClaims.removable(fieldName);
			}catch(NoSuchElementException e) {
				flag=false;
			}
			Assert.assertFalse(flag);
		}
	}
	
	public WebElement paymentTab() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("payment-tab"), 30);
	}
	
	public WebElement save() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[contains(text(),'Save')])[3]"), 5);
	}
	
	public List<WebElement> paymentHistoryFields() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='Payment History']/following-sibling::table/tr[1]/th"), 5);
	}
	
	public WebElement paymentHistoryGridData(int columnNumber) {
		List<WebElement> paymentHstoryRows = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//label[text()='Payment History']/following-sibling::table/tr"), 5);
		int totalRows = paymentHstoryRows.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Payment History']/following-sibling::table/tr["+totalRows+"]/td["+columnNumber+"]"), 5);
	}
	
	public WebElement importClaimPayments() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-button k-upload-button']"), 30);
	}
	
	public WebElement reverseButton(int rowIndex, int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//label[text()='Payment History']/following-sibling::table/tr["+rowIndex+"]/td["+columnIndex+"]/button"), 20);
	}
	
	public boolean isReverseButtonPresent(int rowIndex, int columnIndex) {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//label[text()='Payment History']/following-sibling::table/tr["+rowIndex+"]/td["+columnIndex+"]/button"));
	}
	
	public WebElement adminTab() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("admin-tab"), 30);
	}
	
	public WebElement getOrderInvoicesField(int rowIndex, int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//h3[text()='Order Invoices']/following-sibling::div/table/tbody/tr["+rowIndex+"]/td["+columnIndex+"]"), 5);
	}
	
	public WebElement getClientInformationField(int rowIndex, int columnIndex) {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td["+columnIndex+"]"), 5);
	}
	
	public WebElement LTLMarkupType() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//label[contains(text(),'LTL Markup Type')]/following-sibling::input"), 5);
	}
	
	public WebElement LTLMarkup() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("(//label[contains(text(),'LTL Markup')]/following-sibling::input)[2]"), 5);
	}
	
	public WebElement groundMarkupType() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//label[contains(text(),'Ground Markup Type')]/following-sibling::input"), 5);
	}
	
	public WebElement groundMarkup() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("(//label[contains(text(),'Ground Markup')]/following-sibling::input)[2]"), 5);
	}
	
	public WebElement adminNotes() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//label[contains(text(),'Admin Notes')]/following-sibling::textarea"), 5);
	}
}
