package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ResetPasswordPage {
	
	WebDriver driver;
	
	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement clickHereToOpenResetPasswordPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.linkText("here"), 10);
	}
	
	public WebElement email() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("Email"), 30);
	}

	public WebElement password() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("Password"), 30);
	}

	public WebElement confirmPassword() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("ConfirmPassword"), 30);
	}
	
	public WebElement reset() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("resetButton"), 30);
	}
	
	public WebElement resetPasswordConfirmation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//p[1]"), 30);
	}
	
}