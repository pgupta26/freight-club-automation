package com.qualitesoft.core;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;


public class InitializeTest {

	protected static WebDriver driver = null;
	public static String browser = null;
	public static String env = null;
	public static String retryCount = null;
	public static String project = null;
	public static String URL = null;
	public static String fcusername = null;
	public static String fcpassword = null;
	public static String Row=null;
	public static String testname = null;
	public static String dataFile = null;
	public static String loginuser=null;
	public static String crorderId = null;
	public static String testData;
	public static String userType;
	public static String keyword = null;
	public static String carrierName = null;
	public static String carrierDetailsOptions = null;
	public static String searchUser = null;
	public static String xpathRow = null;
	public static String baseRateRow = null;
	public static String enableOption = null;
	public static String userPermissions = null;
	public static String Productname = null;

	
	
	@BeforeTest
	public void initialize(ITestContext context) {
		
		browser = context.getCurrentXmlTest().getParameter("browser");
		env = context.getCurrentXmlTest().getParameter("env");
		URL = context.getCurrentXmlTest().getParameter("URL");
		retryCount = context.getCurrentXmlTest().getParameter("retryCount");
		project = context.getCurrentXmlTest().getParameter("project");
		fcusername = context.getCurrentXmlTest().getParameter("fcusername");
		fcpassword = context.getCurrentXmlTest().getParameter("fcpassword");
		Row = context.getCurrentXmlTest().getParameter("Row");
		dataFile = context.getCurrentXmlTest().getParameter("dataFile");
		loginuser = context.getCurrentXmlTest().getParameter("loginuser");
		testData = context.getCurrentXmlTest().getParameter("testData");
		userType = context.getCurrentXmlTest().getParameter("userType");
		keyword= context.getCurrentXmlTest().getParameter("keyword");
		carrierName=context.getCurrentXmlTest().getParameter("CarrierName");
		carrierDetailsOptions=context.getCurrentXmlTest().getParameter("CarrierDetailsOptions");
		searchUser = context.getCurrentXmlTest().getParameter("searchUser");
		xpathRow=context.getCurrentXmlTest().getParameter("xpathRow");
		baseRateRow=context.getCurrentXmlTest().getParameter("baseRateRow");
		enableOption = context.getCurrentXmlTest().getParameter("enableOption");
		userPermissions = context.getCurrentXmlTest().getParameter("userPermissions");
		testname = context.getCurrentXmlTest().getName();

		ScreenShot.createScreenshotFolder(testname);
		
		Log.info("*****************************"+ testname + " started.");
		
	}
	
	@Parameters({ "browser", "URL" })
	@BeforeSuite
	public WebDriver setUp(String browser, String URL) throws Exception {
		
		try {
			
			FileUtils.deleteDirectory(new File(".\\download"));
		}catch(IOException e) {
			
			Log.info("Download directory not found to delete.");
		}
		
		driver = launchBrowser(browser);
		launchURL(URL);
		return driver;
		
	}

	@AfterSuite
	public void tearDown() {
		Log.info("******************Test Ended************************");
		driver.quit();
	}

	public WebDriver launchBrowser(String browser) throws Exception {
		
		try {
			
			String download =System.getProperty("user.dir")+File.separator+"download";
			File file = new File(download);
			
			if(!file.exists())
				file.mkdir();
			
			if(browser.equalsIgnoreCase("chrome")) {
				
				//set download directory
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", download);
				
				//set chrome options
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				options.setAcceptInsecureCerts(true);
				options.addArguments("--silent");
				options.addArguments("force-device-scale-factor=0.85");
				options.addArguments("high-dpi-support=0.85");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-notifications");
				options.addArguments("--start-maximized");
				options.addArguments("test-type");
				options.addArguments("--remote-allow-origins=*");
				System.setProperty("java.net.preferIPv4Stack", "true");	
				
				//Launch browser
				WebDriverManager.chromedriver().clearResolutionCache().setup();
				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20, 1));
				
			} else if(browser.equalsIgnoreCase("firefox")) {
				
				//Launch browser
				WebDriverManager.firefoxdriver().clearResolutionCache().setup();
				driver = new FirefoxDriver();
				
			} else if(browser.equalsIgnoreCase("iexplorer")) {
				
				//Launch browser
				WebDriverManager.iedriver().clearResolutionCache().setup();
				driver = new InternetExplorerDriver();
				
			} else {
				
				throw new InvalidArgumentException("Invalid browser name");
				
			}
			
			Log.info(browser + " browser launched successfully.");
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.readyState").equals("complete");
			
		} catch (Exception e) {
			
			Log.error("Not able to launch browser: " + e.getMessage());
			throw e;
			
		}
		return driver;
	}

	public void launchURL(String URL) throws Exception {
		try {
			
			driver.get(URL);
			Log.info(URL + " URL launched successfully.");
			
		} catch (Exception e) {
			Log.error("Not able to launch URL: " + e.getMessage());
			
			throw e;
		}
	}

	public static WebDriver getDriver() {
		
		return driver;
		
	}
	
}
