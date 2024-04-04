package com.qualitesoft.freightclub.pageobjects;

import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;

public class FloatingCreateAccount {

	WebDriver driver;

	public FloatingCreateAccount(WebDriver driver) {
		this.driver = driver;
	}

	public FloatingCreateAccount clickSignUpLink() {
		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.xpath("(//span[text()='Sign Up'])[1]")));
		return this;
	}

	public FloatingCreateAccount typeEmail(String email) {
		WaitTool.returnWebElement(driver,By.id("1-email")).clear();
		SeleniumFunction.sendKeys(WaitTool.returnWebElement(driver,By.id("1-email")), email);
		return this;
	}

	public FloatingCreateAccount selectAccountType(String accountType) {
		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.id("1-profile_type")));
		WaitTool.sleep(1);
		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.xpath("//li[text()='"+accountType+"']")));
		WaitTool.sleep(1);
		return this;
	}

	public FloatingCreateAccount selectSourceType(String sourceType) {
		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.id("1-source_type")));
		WaitTool.sleep(1);
		SeleniumFunction.click(WaitTool.returnWebElement(driver, By.xpath("//li[text()='"+sourceType+"']")));
		WaitTool.sleep(1);
		return this;
	}

	public WebElement firstName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-given_name"), 50);
	}
	public WebElement lastName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-family_name"), 30);
	}
	public WebElement companyName() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-company_name"), 30);
	}
	public WebElement password() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-password"), 30);
	}
	public WebElement confirmPassword() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//form[@class='form-horizontal']/div[2]/div/div[7]/div[2]//input"), 30);
	}
	public WebElement phone() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-phone_number"), 30);
	}
	
	public WebElement extension() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("1-phone_ext"), 30);
	}
	
	public WebElement agreeCheckbox() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("IAgree"), 30);
	}
	public WebElement createButton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Create Account')]"), 30);
	}
	
	public WebElement confirmAcceptance() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("btnAcceptTaC"), 30);
	}
	
	public WebElement accountCreateSuccessMessage() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h2[text()='Accept Terms and Conditions']/following::p"), 30);
	}
	public WebElement closeButton() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Close')]"), 30);
	}		
}
