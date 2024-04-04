package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class OverageDetails {
	
	WebDriver driver;
	
	public OverageDetails(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getLabel(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following-sibling::span | //label[text()='"+label+"']/following-sibling::input"), 50);
	}
	
	public WebElement getSelect(String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following-sibling::div/descendant::div[contains(@class,'has-item')]"), 30);
	}
	
	public WebElement setSelect(String label, String value) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+label+"']/following-sibling::div/descendant::div[@class='option' and text()='"+value+"']"), 10);
	}
	
	public WebElement documents() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='k-button k-upload-button']"), 10);
	}
	
	public WebElement comments() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@placeholder='Add your comment...']"), 10);
	}
	
	public WebElement saveComment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Comment')]"), 10);
	}
	
	public WebElement saveChanges() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Changes')]"), 10);
	}
	
	public WebElement savedComment(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::tr["+rowIndex+"]/td[1]"), 10);
	}
	
	public WebElement savedUserName(int rowIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-area']/descendant::div[@class='col-xs-4']/descendant::tr["+rowIndex+"]/td[2]/strong"), 10);
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
	
	public WebElement viewable(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr["+rowIndex+"]/td[3]/a[1]"));
	}
	
	public WebElement downloadable(int rowIndex) {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr["+rowIndex+"]/td[3]/a[2]"));
	}
	
	public WebElement removable() {
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='details-area']/descendant::label[text()='Documents to support dispute:']/following-sibling::table/tbody/tr/td[3]/i"));
	}
	
	public WebElement submitForReview() {
		return WaitTool.returnWebElement(driver, By.xpath("//button[contains(text(),'Submit for Review')]"));
	}
	
	//Sent to carrier popup
	public WebElement getLabelFromModal(String modalId, String label) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::label[text()='"+label+"']/following-sibling::input | //div[@id='sentToCarrierModal']/descendant::label[text()='"+label+"']/following-sibling::textarea"), 10);
	}
	
	public WebElement sendEmail(String modalId) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::button[contains(text(),'Send Email')]"), 10);
	}
	
	//More Information Popup
	public WebElement body(String modalId) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='"+modalId+"']/descendant::textarea"), 10);
	}

	

}
