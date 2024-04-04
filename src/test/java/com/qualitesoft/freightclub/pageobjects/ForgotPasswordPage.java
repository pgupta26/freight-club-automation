package com.qualitesoft.freightclub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.WaitTool;

public class ForgotPasswordPage {
	
	WebDriver driver;
	
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement email() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("Email"), 10);
	}

	public WebElement resetPassword() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@value='Reset Password']"), 10);
	}
	
	public WebElement forgotPasswordSuccessConfirmation() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[text()='Return to Sign In page']/preceding-sibling::p"), 10);
	}
	
	public WebElement returnToSignInPage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[text()='Return to Sign In page']"), 10);
	}
}
