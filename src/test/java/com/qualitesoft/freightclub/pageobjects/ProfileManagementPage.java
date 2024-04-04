package com.qualitesoft.freightclub.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class ProfileManagementPage {

	WebDriver driver;

	public static String manageCarriers = "//a[text()='Manage Carriers']";

	public ProfileManagementPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement manageProfileLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/Admin/Index']"), 30);
	}
	
	public WebElement manageCarrierTab() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='#carriersTab']"), 10);
	}
	
	public WebElement insuranceTab() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='#insuranceTab']"), 10);
	}
	
	public WebElement upsCapitalBandDropdown(String dropDownFieldName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='"+dropDownFieldName+"']/following-sibling::select"), 10);
	}
	
	public WebElement upsCapitalSupportedCheckbox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[@for='UPSCapitalSupported']/input"), 30);
	}
	
	public WebElement updateButtonInsuranceTab() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Update')]"), 10);
	}
	
	public WebElement accountTypeDropdown() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"profileTab\"]/div[5]/div[3]/div/select"), 30);
	}
	
	public List<String> getListOfEnableCarriers() {
		List<String>  enableCarriers = new ArrayList<String>();

		int  rowIndex = 0;
		List<WebElement> carriers = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='table-carrier']/tbody/tr/td[4]/descendant::span"), 10);
		for(WebElement carrier : carriers) {
			rowIndex++;
			if(carrier.getText().equals("Active")) {
				WebElement carrierName = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='table-carrier']/tbody/tr["+rowIndex+"]/td[2]"), 10);
				enableCarriers.add(carrierName.getText());
			}
		}

		Log.info("List of enable carrier1s: "+enableCarriers);
		return enableCarriers; 
	}

	public WebElement companyNameLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[contains(.,'Brand ID: 10534')]"), 30);
	}
	public WebElement profileListfilter() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Filter profiles']"), 30);
	}	
	public WebElement paymentTermTextbox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='InvoicingCycleDays']"), 30);
	}

	public WebElement lockoutCheckbox() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='LockOutEnabled']"), 30);
	}

	public WebElement saveProfileButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-primary pull-right']"), 30);
	}

	public WebElement saveProfileButtonSurePost() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Save Profile')]"), 30);
	}

	public WebElement quickQuoteBookOrderError() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath(".//*[@class='pull-right text-danger']/strong"), 30);
	}
	public WebElement paymentType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Payment Type']/following-sibling::select[@class='form-control']"), 30);
	}
	
	public WebElement selectParcel() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"profileManagement\"]/div[2]/div/div/div/div[2]/div[19]/div/div[3]/input[2]"), 30);
	}
	
	public void paymentTypeOptions(String packageType) {
		if(packageType.equalsIgnoreCase("CreditCard")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//div[@class='col-xs-12 col-lg-9']/div[8]/div[@class='col-xs-12 col-lg-6']//select[@class='form-control']/option[@value='96']")));
		}
		else if(packageType.equalsIgnoreCase("OnAccount")){
			SeleniumFunction.click(driver.findElement
					(By.xpath("//label[text()='Payment Type']/following-sibling::select/option[@value='95']")));
		}				
	}	
	public void quickQuoteBookOrderValidation() {
		quickQuoteBookOrderError();
		if (quickQuoteBookOrderError() != null) {
			if (driver.findElement(By.xpath(".//*[@class='pull-right text-danger']/strong"))
					.getText() != "duplicate") {
				Log.error(driver.findElement(By.xpath(".//*[@class='pull-right text-danger']/strong")).getText());				
			}
		}
	}	
	
	public WebElement upsSetting(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='#upsTab']"), 60);	
	}

	//Add by Shubham
	public WebElement selectGround(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(text(),'Ground')]/input[1]"), 60);	
	}
	
	public WebElement selectUPSSurepost(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(text(),'Ground')]/input[2]"), 60);	
	}

	public WebElement saveProfileBtn(){
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//*[@id=\"upsTab\"]/div[3]/div[7]/div/button"), 60);
	}
	
	public WebElement selectInternational(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'Can Book International Orders')]/input"), 60);
	}

	public void enableCarrier(String[] carrierNames) throws InterruptedException {
		for(String carrierName :carrierNames) {
			if(carrierName.equals("Pilot Freight Services[Televisions]")) {
				SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//span[contains(text(),'[Televisions]')]/parent::td/following-sibling::td[2]/descendant::span")));
				String carrierStatus = SeleniumFunction.getText(driver.findElement(By.xpath("//span[contains(text(),'[Televisions]')]/parent::td/following-sibling::td[2]/descendant::span")));
				if(!carrierStatus.equals("Active")) {
					SeleniumFunction.clickJS(driver,driver.findElement(By.xpath("//span[contains(text(),'[Televisions]')]/parent::td/following-sibling::td[2]/label")));
				}
			} else if(carrierName.trim().equals("FragilePAK")) {
				SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("(//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span)[1]")));
				String carrierStatus = SeleniumFunction.getText(driver.findElement(By.xpath("(//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span)[1]")));
				if(!carrierStatus.equals("Active")) {
					SeleniumFunction.clickJS(driver,driver.findElement(By.xpath("(//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/label)[1]")));
					ScreenShot.takeFullScreenShot("Carrier "+carrierName+ " enabled");
				}
			} else {
				SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
				String carrierStatus = SeleniumFunction.getText(driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
				if(!carrierStatus.equals("Active")) {
					SeleniumFunction.clickJS(driver,driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/label")));
					ScreenShot.takeFullScreenShot("Carrier "+carrierName+ " enabled");
				}
			}
		}
	}

	public void disableCarrier(String[] carrierNames) throws InterruptedException {
		for(String carrierName :carrierNames) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
			String carrierStatus = SeleniumFunction.getText(driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
			if(carrierStatus.equals("Active")) {
				SeleniumFunction.scrollUpByPixel(driver, "400");
				WaitTool.sleep(2);
				try {
					SeleniumFunction.click(driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
				}catch(Exception ex) {
					SeleniumFunction.scrollDownByPixel(driver, "800");
					SeleniumFunction.click(driver.findElement(By.xpath("//td[contains(text(),'"+carrierName+"')]/following-sibling::td[2]/descendant::span")));
				}
			}
		}
	}
	
	public void disableAllCarriers() {
		List<WebElement> elements = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='table-carrier']/tbody/tr/td[4]/descendant::span"), 30);
		for(int i=0; i< elements.size(); i++) {
			SeleniumFunction.scrollIntoView(driver, elements.get(i));
			String carrierStatus = elements.get(i).getText();
			if(carrierStatus.equals("Active")) {
				SeleniumFunction.scrollUpByPixel(driver, "400");
				WaitTool.sleep(2);
				try {
					SeleniumFunction.click(elements.get(i));
				}catch(Exception ex) {
					SeleniumFunction.scrollDownByPixel(driver, "800");
					SeleniumFunction.click(elements.get(i));
				}
			}
		}
	}
	
	public void enableTruckLoad(String truckLoad) {
		WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[contains(text(),'Truckload')])[2]/following::div[1]/input[3]"), 30);
		Log.info("Is element selected: "+element.isSelected());
		 if(!element.isSelected()) {
			 SeleniumFunction.clickJS(driver, element);
		 }
	}
	
	public void disableTruckLoad(String truckLoad) {
		 WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//label[contains(text(),'Truckload')])[2]/following::div[1]/input[3]"), 30);
		 Log.info("Is element selected: "+element.isSelected());
		 if(element.isSelected()) {
			 SeleniumFunction.clickJS(driver, element);
		 }
	}
	
	public WebElement save() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"upsTab\"]/div[5]/div[4]/div/button"), 10);
	}
	
	public void deleteContactsInformation() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);

		if(contacts != null) {
			int count = contacts.size();
			Log.info("Total contacts available: "+count);
			if(count > 0) {
				for(int i = 1; i <= count; i++) {
					SeleniumFunction.click(driver.findElement(By.xpath("//table[@id='profileInfo']/tbody/tr/td[6]/i[2]")));
					WaitTool.sleep(2);
				}
			}
		}
		ScreenShot.takeScreenShot(driver, "Existing contacts deleted");
	}
	
	public WebElement addContactButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("addContactInfo"), 10);
	}
	
	public WebElement contactType() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td[1]/div[2]/select"), 10);
	}
	
	public WebElement contactName() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td[2]/div[3]/input"), 10);
	}
	
	public WebElement phoneNumber() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td[3]/div[2]/input"), 10);
	}
	
	public WebElement emailAddress() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td[5]/input"), 10);
	}
	
	public WebElement saveContactInformation() {
		List<WebElement> contacts = WaitTool.waitForElementsPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr"), 30);
		int rowIndex = contacts.size();
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@id='profileInfo']/tbody/tr["+rowIndex+"]/td[6]/i"), 10);
	}
}
