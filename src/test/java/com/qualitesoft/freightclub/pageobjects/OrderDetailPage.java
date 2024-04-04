package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class OrderDetailPage {
	
	WebDriver driver;

	public OrderDetailPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement viewDetail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[text()='Detail'])[1]"), 30);
	}
	
	public WebElement getLabel(String label,int panelIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='"+label+"']/following::div | //th[text()='"+label+"']/following-sibling::td)["+panelIndex+"]"), 30);
	}
	
	public WebElement pickUpAddress() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h5[text()='Pick Up Location']/following::p[@class='font-size-13'])[1]"), 30);
	}
	
	public WebElement pickUpRecipient() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h5[text()='Pick Up Location']/following::p[@class='font-size-13'])[2]"), 30);
	}
	
	public WebElement dropOffAddress() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h5[text()='Pick Up Location']/following::p[@class='font-size-13'])[3]"), 30);
	}
	
	public WebElement dropOffRecipient() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//h5[text()='Pick Up Location']/following::p[@class='font-size-13'])[4]"), 30);
	}
	
	public List<WebElement> orderDetailsTab() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//ul[@id='order-details-tab']/li"), 30);
	}
	
	public List<WebElement> adminTabSubMenu() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//ul[@id='order-admin-tab']/li/a/span"), 30);
	}
	
	public boolean verifyCarrier(String carrierName) {
		boolean isPresent = false;
		if(carrierName.equals("ABF Freight")) {
			isPresent =  WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/11.png']"));
		}else if(carrierName.equals("Ceva")){
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/44.png']"));
		}else if(carrierName.equals("Pilot Freight Services[Televisions]")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/301.png']"));
		}else if(carrierName.equals("Werner")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/302.png']"));
		}else if(carrierName.equals("FC Test Carrier")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/222.png']"));
		}else if(carrierName.equals("YRC")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/9.png']"));
		}else if(carrierName.equals("Estes")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/22.png']"));
		}else if(carrierName.equals("BTX Global Logistics")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/291.png']"));
		}else if(carrierName.equals("FragilePAK")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='carrier-panel']/descendant::img[@src='/Content/Images/Logos/280.png']"));
		}else if(carrierName.equals("Ups") || carrierName.equals("Ups SurePost")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/1.png']"));
		}
		
		Log.info("Is Carrier Present: "+isPresent);
		return isPresent;
	}
	
	
	
	public void expandItemsInThisOrderPanel(int panelIndex) {
		WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='shipment-items-panel collapsible well well-note']/div)["+panelIndex+"]"), 30);
		String isCollapsedExist = element.getAttribute("class");
		if(isCollapsedExist.contains("collapsed")) {
			SeleniumFunction.click(element);
		}
	}
	
	public boolean verifyBasicThreshold() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[contains(text() ,'Basic Threshold (No Signature Required)')]"));
	}
	
	//********Added by Shubham**************
	
	public WebElement orderId(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[text()='Order ID:']//following::div)[1]"), 10);
	}
	public String verifyRerouteWarningMessage(){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='details-info-panel']//div[contains(@class,'alert-warning')]"), 60));
	}
	
	public boolean verifyRerouteWarningMessageStatus(){
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@id='details-info-panel']//div[contains(@class,'alert-warning')]"));
	}

	public String verifyRerouteLocationOnDetailPage(){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[contains(text(),'Reroute Location')]//parent::div"), 60));
	}
	
	//******************verify bill details added by Admin on order detail page************
	
	public WebElement adminTab(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='admin-tab']"), 60);
	}
	
	public WebElement billsTab(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='bills-tab']"), 60);
	}
	
	public WebElement billDetail(String documentNum, int index){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//td[text()='"+documentNum+"']//parent::tr//td)["+index+"]"), 60);
	}
	
	public WebElement editOrder() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@class='btn btn-xs btn-primary']"), 30);
	}

}
