package com.qualitesoft.core;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumFunction {

	public static void click(WebElement element) {

		try {
			element.click();
			Log.info("WebElement clicked.");
		} catch(ElementClickInterceptedException e) {
			Log.info("Element click intercepted expception found.............");
			if(WaitTool.isElementPresentAndDisplay(InitializeTest.driver, By.xpath("//button[@data-role='end']"))) {
				ScreenShot.takeScreenShot(InitializeTest.driver, "Popup"+JavaFunction.getRandomNumber(10, 10000));
				SeleniumFunction.clickJS(InitializeTest.driver,WaitTool.waitForElementPresentAndDisplay(InitializeTest.driver, By.xpath("//button[@data-role='end']"), 10));
			}
			WaitTool.sleep(3);
			element.click();
			Log.info("WebElement clicked on retry.");
		}
		catch (Exception e) {
			try {
				Log.info("Retrying click...............");
				WaitTool.sleep(3);
				element.click();
				Log.info("WebElement clicked on retry.");
			}catch(Exception ex) {
				Log.warn("Not able to click the webelement: " + element + e.getMessage());
				throw e;
			}
		}
	}

	public static void clickAction(WebDriver driver, WebElement element) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			Log.info("WebElement clicked.");
		} catch (Exception e) {
			Log.warn("Not able to click the webelement: " + element + e.getMessage());
			throw e;
		}
	}

	public static void hoverAction(WebDriver driver, WebElement element) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			Log.warn("Not able to hover the webelement: " + element + e.getMessage());
			throw e;
		}
	}

	public static void clickJS(WebDriver driver,WebElement element) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			Log.info("WebElement clicked.");
		} catch (Exception e) {
			Log.warn("Not able to click the webelement: " + element + e.getMessage());
			throw e;
		}
	}

	public static void sendKeysJS(WebDriver driver, WebElement element, String text) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "'", element);
			Log.info("WebElement clicked.");
		} catch (Exception e) {
			Log.warn("Not able to send text to the webelement: " + element + e.getMessage());
			throw e;
		}
	}

	public static void sendKeysAction(WebDriver driver, WebElement element, CharSequence keysToSend) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).sendKeys(element, keysToSend).build().perform();
		} catch (Exception e) {
			Log.warn("Not able to send keys" + e.getMessage());
			throw e;
		}
	}

	public static void sendKeys(WebElement element, String text) {

		try {
			element.clear();
			if(!element.getAttribute("value").equals("")){
				WaitTool.sleep(3);
				executeJS(InitializeTest.driver,"arguments[0].value=''", element);
			}
			element.sendKeys(text);
			Log.info("Text send.");
		} catch (Exception e) {
			Log.warn("Not able to send text: " + element + e.getMessage());
			throw e;
		}
	}


	public static String getText(WebElement element) {

		try {
			String text = element.getText();
			return text;
		} catch (Exception e) {
			Log.warn("Not able to get text from the: " + element + e.getMessage());
			throw e;
		}
	}
	
	public static String getAttribute(WebElement element, String attribute) {

		try {
			String text = element.getAttribute(attribute);
			return text;
		} catch (Exception e) {
			Log.warn("Not able to get text from the: " + element + e.getMessage());
			throw e;
		}
	}

	public static void select(WebElement element, String text) {

		try {
			Select option = new Select(element);
			option.selectByVisibleText(text);
			Log.info("Value from the dropdown selected: " + text);
		} catch (Exception e) {
			Log.warn("Not able to select value from dropdown: " + element);
			throw e;
		}
	}

	public static void selectByIndex(WebElement element, int index) {

		try {
			Select option = new Select(element);
			option.selectByIndex(index);
			Log.info("Index from the dropdown selected: " + index);
		} catch (Exception e) {
			Log.warn("Not able to select index from dropdown: " + index);
			throw e;
		}
	}

	public static void selectByvalue(WebElement element, String value) {

		try {
			Select option = new Select(element);
			option.selectByValue(value);
			
			WaitTool.sleep(1);
			if(!option.getFirstSelectedOption().getText().equals(value)) {
				System.out.println("value from the dropdown not selected in 1st attempt.");
				option.selectByValue(value);
			} 
			Log.info("value from the dropdown selected: " + value);
		} catch (Exception e) {
			Log.warn("Not able to select value from dropdown: " + value);
			throw e;
		}
	}

	public static void selectByVisibleText(WebElement element, String value) {

		try {
			Select option = new Select(element);
			option.selectByVisibleText(value);
			Log.info("value from the dropdown selected: " + value);
		} catch (Exception e) {
			Log.warn("Not able to select value from dropdown: " + value);
			throw e;
		}
	}

	public static void executeJS(WebDriver driver, String script) {
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script);
		}catch(Exception e) {
			Log.warn("Not able to execute java script");
			throw e;
		}
	}

	public static void executeJS(WebDriver driver, String script, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script, element);
		}catch(Exception ex) {
			Log.warn("Not able to execute java script");
			throw ex;
		}
	}

	public static void selectFrame(WebDriver driver, String frameID) {
		try {
			driver.switchTo().frame(frameID);
		}catch(Exception ex) {
			Log.warn("Not able to switch to frame");
			throw ex;
		}
	}

	public static void selectParentframe(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		}catch(Exception ex) {
			Log.warn("Not able to switch to frame");
			throw ex;
		}
	}

	public static void closeWindow(WebDriver driver) {
		try {
			driver.close();
		}catch(Exception ex) {
			Log.warn("Not able to close current window");
			throw ex;
		}
	}
	public static void runAutoItScript(String scriptname, String cmd) {

		try {
			String path = System.getProperty("user.dir") + "/binaries/autoit/" + scriptname;
			Runtime.getRuntime().exec(path + " " + cmd);
		} catch (Exception e) {
			Log.error("Not able to run AutoIt script" + e.getMessage());
		}
	}
	
	public static void uploadFile(String fileName) {

		try {
			fileName = System.getProperty("user.dir") + "\\testdata\\FCfiles\\"+InitializeTest.env+"\\"+fileName;
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\autoit\\ChromeUpload.exe"+" "+fileName+" "+"Open");
			WaitTool.sleep(10);
			Log.info(fileName+" successfully uploaded.");
			ScreenShot.takeScreenShot(InitializeTest.getDriver(), "File Uploadad");
		} catch (Exception e) {
			Log.error("Not able to run AutoIt script" + e.getMessage());
		}
	} 
	
	public static void uploadImage(WebElement element, String fileName) {
		try {
			element.sendKeys(fileName);
			WaitTool.sleep(10);
			Log.info(fileName+" successfully uploaded.");
		} catch (Exception e) {
			Log.error("Not able to upload image" + e.getMessage());
		}
	} 

	public static void getCurrentWindow(WebDriver driver) {

		WaitTool.sleep(2);
		try {
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				driver.switchTo().window(window);
			}
		} catch (Exception e) {
			Log.error("Not able to get current window: " + e.getMessage());
			throw e;
		}
	}

	public static void moveToElement(WebDriver driver, WebElement element) {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			Log.error("Not able to move to element: " + e.getMessage());
		}
	}

	public static String getCurrentUrl(WebDriver driver){

		try{
			String url=driver.getCurrentUrl();
			Log.info("Current URL: "+ url);
			return url;
		}catch(Exception e){
			Log.error("Unable to Load Current URL: "+ e.getMessage());
			throw e;
		}
	}

	public static void get(WebDriver driver, String url){

		try{
			driver.get(url);
			Log.info("URL opened: "+ url);
		}catch(Exception e){
			Log.error("Unable to load new URL: "+ e.getMessage());
			throw e;
		}
	}
	
	public static void scrollUp(WebDriver driver){
		try {
			((JavascriptExecutor)driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
			Log.info("Scroll top of the page");
		}catch(Exception ex) {
			Log.error("Unable to scroll top of the page");
			throw ex;
		}
	}
	
	public static void acceptAlert(WebDriver driver, int ex_sec){
		try {
			driver.switchTo().alert().accept();
			WaitTool.sleep(ex_sec);
			Log.info("Alert accepted successfully");
		}catch(Exception ex) {
			Log.error("Unable to accept the Alert");
			throw ex;
		}
	}
	
	public static void refreshCurrentWindow(WebDriver driver) {
		try {
			driver.navigate().refresh();
		}catch(Exception ex) {
			Log.error("Unable to refresh page");
			throw ex;
		}
	}

	//*******************************************************Scroll Up/Down Shubham***********************************************

	public static void scrollDownByPixel(WebDriver driver, String pixel){
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,"+ pixel +")");
	}

	//to perform Scroll on application using  Selenium
	public static void scrollDownUptoFooter(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	//to perform Scroll on application using  Selenium
	public static void 	scrollUPUptoHeader(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(document.body.scrollHeight,0)");
	}

	public static void scrollUpByPixel(WebDriver driver, String pixel){
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-" + pixel +")");
	}

	public static void scrollIntoView(WebDriver driver) throws InterruptedException{
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void horizontalScroll(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(250,0)", "");
	}

	public static void scrollIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void open(WebDriver driver, String url) {
		try {
			driver.get(url);
		}catch(Exception ex) {
			Log.error("Unable to launch url.");
			throw ex;
		}
	}

	public static void clearAndSendKeys(WebElement element, String text) {
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(text);
	}
}
