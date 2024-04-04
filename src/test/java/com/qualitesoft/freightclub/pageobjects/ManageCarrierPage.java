package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class ManageCarrierPage {

	public By managecarrier=By.xpath("//a[contains(text(),'Manage Carriers')]");
	public By settings=By.xpath("//a[contains(text(),'Settings')]");
	public By upscheckbox=By.xpath("//td[contains(text(),'UPS Freight')]//preceding::td[2]//label");	

	WebDriver driver;

	public ManageCarrierPage(WebDriver driver) {
		this.driver = driver;
	}

	public void manageCarriersLink() {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//a[@href='/Admin/ManageCarriers']"), 30));
	}

	public boolean isClassicViewPresent() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[@href='/Admin/ManageCarriers#/carriers']"));
	}

	public WebElement classicView() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Admin/ManageCarriers#/carriers']"), 50);
	}

	public WebElement filterCarrier() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Search brokers and carriers']"), 10);
	}

	public WebElement clkCarriersettings() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//img[@src='/Content/Images/settings-icon.svg'])[2]"), 10);
	}

	public WebElement clkCloseCarrierSetting() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//img[@src='/Content/Images/close-icon.svg'])[2]"), 10);
	}

	public void filterCarrier(String carrierName) {
		if(carrierName.equals("Pilot Freight Services[Televisions]")) {
			SeleniumFunction.sendKeys(WaitTool.returnWebElement(driver, By.id("carriers-list-filter")), "Pilot");
			driver.findElement(By.id("carriers-list-filter")).sendKeys(Keys.ENTER);
			WaitTool.sleep(5);
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[contains(text(),'Pilot Freight Services  [Televisions]')]"), 20));
		}else {
			SeleniumFunction.sendKeys(WaitTool.returnWebElement(driver, By.id("carriers-list-filter")), carrierName);
			driver.findElement(By.id("carriers-list-filter")).sendKeys(Keys.ENTER);	
		}
	}

	public WebElement carrierDetailsOptions(String optionName) {
		if(optionName.equals("Residential Pickup Supported")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[1]/div/input"), 10);
		}else if(optionName.equals("Loose carton accepted")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[2]/div/input"), 10);
		}else if(optionName.equals("Fulfilled By Amazon Approved")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[3]/div/input"), 10);
		}else if(optionName.equals("Requires email address to book")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[4]/div/input"), 10);		
		}else if(optionName.equals("Full Truck Load Supported")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[5]/div/input"), 10);
		}else if(optionName.equals("Custom Quotes Supported")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[6]/div/input"), 10);	
		}else if(optionName.equals("Send Inside Delivery")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[8]/div/input"), 10);	
		}else if(optionName.equals("Residential Delivery Supported")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='optionsTab']/div[9]/div/input"), 10);	
		}else {
			return null;
		}
	}

	public void deleteContactsInformation() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int count = contacts.size();
		Log.info("Total contacts available: "+count);

		if(contacts != null && count > 0) {
			for(int i = 1; i <= count; i++) {
				SeleniumFunction.click(driver.findElement(By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr/td[6]/i[2]")));
				WaitTool.sleep(2);
			}
			ScreenShot.takeScreenShot(driver, "Existing contacts deleted");
		}
	}

	public WebElement addContactButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/following-sibling::i"), 10);
	}

	public WebElement contactType() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr["+rowIndex+"]/td[1]/div[2]/select"), 10);
	}

	public WebElement contactName() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr["+rowIndex+"]/td[2]/div[2]/input"), 10);
	}

	public WebElement phoneNumber() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr["+rowIndex+"]/td[3]/input"), 10);
	}

	public WebElement emailAddress() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr["+rowIndex+"]/td[4]/input"), 10);
	}

	public WebElement saveContactInformation() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[@id='carrier_contact_info']/descendant::table/tbody/tr["+rowIndex+"]/td[6]/i"), 10);
	}

	public void selectResidentialPickUp() {
		WebElement residentialPickUP = WaitTool.returnWebElement(driver, By.xpath("//*[@id='optionsTab']/div[1]/div/input"));
		if(!residentialPickUP.isSelected()) {
			SeleniumFunction.click(residentialPickUP);
		}
	}

	public void unselectResidentialPickUp() {
		WebElement residentialPickUP = WaitTool.returnWebElement(driver, By.xpath("//*[@id='optionsTab']/div[1]/div/input"));
		if(residentialPickUP.isSelected()) {
			SeleniumFunction.click(residentialPickUP);
		}
	}

	public void selectLooseCartoon() {
		WebElement looseCartoon = WaitTool.returnWebElement(driver, By.xpath("//*[@id='optionsTab']/div[2]/div/input"));
		if(!looseCartoon.isSelected()) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//*[@id='optionsTab']/div[2]/div/input"), 10));
		}
	}

	public void unselectLooseCartoon() {
		WebElement looseCartoon = WaitTool.returnWebElement(driver, By.xpath("//*[@id='optionsTab']/div[2]/div/input"));
		if(looseCartoon.isSelected()) {
			SeleniumFunction.click(looseCartoon);
		}
	}

	public WebElement clkManageCarrier(){
		return WaitTool.waitForElementPresentAndDisplay(driver, managecarrier, 60);
	}

	public WebElement clkSetting(){
		return WaitTool.waitForElementPresentAndDisplay(driver, settings, 60);
	}

	public WebElement UPSToggleBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, upscheckbox, 60);
	}

	public WebElement updateButton(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Update']"), 30);
	}

	public WebElement expandCarrier() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//td[4]"), 30);
	}
 }
