package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ManagePermissions {
	
	WebDriver driver;
	
	public ManagePermissions(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement managePermissionsLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/Manage/Permissions')]"), 30);
	}
	
	public WebElement parentAccount() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h4[text()='Parent account']/following::input[@placeholder='Search by username']"), 30);
	}
	
	public WebElement selectParentAccount() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@class='highlight']"), 30);
	}
	
	public WebElement accountLogin() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//table[@class='table']/descendant::tr[2]/td[1]"), 30);
	}
	
	public WebElement userPermissions(String permissionName) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'"+permissionName+"')]/input"), 30);
	}
	
	public WebElement setPermissions() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Set Permissions']"), 30);
	}

}
