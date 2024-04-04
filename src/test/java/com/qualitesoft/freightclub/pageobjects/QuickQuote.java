package com.qualitesoft.freightclub.pageobjects;


import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.UseAssert;
import com.qualitesoft.core.WaitTool;


public class QuickQuote {

	WebDriver driver;

	public QuickQuote(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement manageOrdersLink() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/QuickQuote/QuickQuote')]"), 30);
	}

	public WebElement QQLink(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@id='link-quick-quote']"), 60);
	}
	
	public boolean isPopupPresent() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//button[@data-role='end']"));
	}

	public void acceptPopup() {
		if(this.isPopupPresent()) {
			ScreenShot.takeScreenShot(driver, "Popup"+JavaFunction.getRandomNumber(10, 10000));
			SeleniumFunction.clickJS(driver,WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@data-role='end']"), 10));
		}
	}

	public WebElement ParcelShipment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section/div/section/div[1]/div[1]/label"), 30);
		//return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='shipmentType' and @value='0']"), 30);
	}

	public WebElement LTLShipment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Less-Than-Truckload (LTL)']/preceding::div[1]/parent::label/parent::div"), 30);
	}
	
	public WebElement CustomShipment() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Custom Order']/preceding::div[1]/parent::label/parent::div"), 30);
	}

	public WebElement OrderDate() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='OrderDate']"), 30);
	}
	public WebElement OrderDate1() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='datepicker-days']//td[@class='day']"), 30);
	}

	public WebElement ClickDate(String date) {

		List<WebElement> allDates= driver.findElements(By.xpath("//div[@class='datepicker-days']//td"));
		for(WebElement ele: allDates)
		{
			String dt= ele.getText();
			if(dt.equalsIgnoreCase(date))
			{
				return ele;	
			}
		}
		
		return null;

	}

	public WebElement OrderReferenceID() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='OrderReferenceID']"), 30);
	}

	public WebElement ServiceLevel() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-control form-input single']"), 30);
		//return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-input items full has-options has-items']"), 30);
	}
	public WebElement ServiceLevelWG() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='357']"), 30);
	}

	public WebElement ServiceLevelBOT() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='351']"), 30);
	}

	public WebElement ServiceLevelCUR() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='352']"), 30);
	}
	public WebElement ServiceLevelCURFloating() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-input items has-options full has-items']//div[@data-value='352']"), 30);
	}	
	public WebElement ServiceLevelTHR() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='353']"), 30);
	}

	public WebElement ServiceLevelROC() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='354']"), 30);
	}

	public WebElement ServiceLevelWGPR() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='355']"), 30);
	}	
	public WebElement ServiceLevelOptions() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='357']"), 30);
	}
	public WebElement ServiceLevelGRND() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='350']"), 30);
	}	

	public WebElement PickUpZip() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='pickupzipEntry']"), 30);
	}
	
	public WebElement PickUpLocationType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[text()='Pick Up Location ']/ancestor::div[@class='col-xs-12 col-sm-6']/descendant::select[@class='form-input form-control input-sm']"),60);
	}
	
	public WebElement DropOffLocationType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[text()='Drop Off Location ']/ancestor::div[@class='col-xs-12 col-sm-6']/descendant::select[@class='form-input form-control input-sm']"),60);
	}

	public WebElement PickUpZipLocationTypeRes() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[3]//div[1]//div[1]//div[2]//input[1]"),60);
	}

	public WebElement PickUpZipLocationTypeCom() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[3]//div[3]//input[1]"),60);
	}

	public WebElement DropOffZipLocationTypeRes() {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[2]//div[2]//div[3]//div[1]//div[1]//div[2]//input[1]"), 60);
	}

	public WebElement DropOffZipLocationTypeCom() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//section[2]//div[2]//div[3]//div[1]//div[1]//div[3]//input[1]"), 60);
	}
	public WebElement insurance() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section/div/div[3]/div[2]/div[3]/div/div[2]/div[4]/label"), 30);
	}
	
	public void selectInsurance() {
		WebElement insurance = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[4]/div[2]/div[3]/div/div[2]/div[4]/label/input"), 30);
		Log.info("Insurance Checkbox: "+insurance.isSelected());
		if(!insurance.isSelected())  {
			SeleniumFunction.click(insurance);
		}
	}

	public void deselectInsurance() {
		WebElement insurance = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[4]/div[2]/div[3]/div/div[2]/div[4]/label/input"), 30);
		Log.info("Insurance Checkbox: "+insurance.isSelected());
		if(insurance.isSelected())  {
			SeleniumFunction.click(insurance);
		}
	}
	
	public WebElement DropOffZip() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='dropoffzipEntry']"), 30);
	}
	public WebElement popupCateogory() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[text()='Got it!']"), 30);
	}	
	public WebElement popupCateogory2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[text()='Got it!'])[2]"), 30);
	}	
	public WebElement PackageType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='form-group col-sm-2']/div[@class='selectize-control form-input single']"), 90);
	}
	public WebElement PackageType2() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='form-group col-sm-2']/div[@class='selectize-control form-input single'])[2]"), 90);
	}
	public WebElement PackageTypeselection2() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section/div/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div[2]/div/div[1]/div[4]"), 90);
	}
	public WebElement PackageTypeParcel() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='form-group col-sm-4']/div[@class='selectize-control form-input single']"), 90);
	}	
	public WebElement PackageValue() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@title='Package Type']/following::input[1]"), 30);
	}
	public WebElement PackageTypeOption() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown single packaging-type form-input']//div/div"), 30);
	}
	public WebElement PackageTypeOptionParcel() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown single form-input']//div/div"), 30);
	}	

	public WebElement parcelPackageCardBoardBox(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']/div/div[text()='Cardboard Box']"), 60);
	}

	public void PackageTypeOptions(String packageType) {
		
		SeleniumFunction.executeJS(driver, "arguments[0].click();", driver.findElement
				(By.xpath("//div[@data-group='packaging']/div[contains(text(),'"+packageType+"')]")));
	}

	public void selectPackageType2(String packageType) {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='selectize-dropdown single form-input'])[3]//div/div[@data-value='854']"), 20));
	}

	public void quantity2(String quantity) {
		WebElement element = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[contains(text(),'Quantity')]/following::input[1])[1]"), 10);
		int charLength = element.getAttribute("value").length();
		for(int index = 0; index < charLength; index++){
			element.sendKeys(Keys.BACK_SPACE);
		}
		element.sendKeys(quantity);
		element.sendKeys(Keys.TAB);
	}

	public WebElement Weight() {

		//return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@class='form-inline weight']/div/input"), 30);
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@class='input-group-btn']/input[@type='number' and @class='form-control form-input input-sm']"), 30);
	}
	public WebElement Weight2() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(@class,'weight')])[2]/descendant::input[1]"), 30);
	}

	public WebElement DimensionL() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@type='number' and @placeholder='Length']"), 30);
	}

	public WebElement DimensionL2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Length'])[2]"), 30);
	}

	public WebElement DimensionW() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@type='number' and @placeholder='Width']"), 30);
	}
	public WebElement DimensionW2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Width'])[2]"), 30);
	}    
	public WebElement DimensionH() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@type='number' and @placeholder='Height']"), 30);
	}
	public WebElement DimensionH2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Height'])[2]"), 30);
	}
	public WebElement Category() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@title='Category']/parent::label/following-sibling::select"), 30);
	}
	public WebElement Category2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//select[@class='form-input form-control input-sm'])[2]"), 30);
	}
	public WebElement Categoryselect2() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section/div/div[4]/div[1]/div[2]/div/div[2]/div[1]/div[5]/div/div/ul/li[13]/label/input"), 30);
	}
	public WebElement addadditionalItem() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-info btn-xs'])[1]"), 30);
	}

	public void copyItemInformation(int index) {
		try {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(@class,'well well-white quote-item margin-right-10')]/descendant::button[1])["+(index+1)+"]"), 10));	
		}catch(Exception ex) {
			Log.info(ex.getMessage());
		}
		
	}

	public void deleteItemInformation() {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='row margin-left-0'])[1]/descendant::li[2]/button"), 10));
	}

	public void ServiceLevel(String Category) {

		List<WebElement> Categories = driver.findElements(By.xpath("//div[@class='ms-drop bottom']"));
		for (int categorytypes = 0; categorytypes <= Categories.size(); categorytypes++) {
			if (Categories.get(categorytypes).getText().trim().equalsIgnoreCase(Category.trim())) {
				SeleniumFunction.click(driver.findElement(By.
						xpath("//div[@class='ms-drop bottom']/ul/li[13]")));
				break;
			}
		}

	}
	
	public WebElement handlingUnits() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.id("ShippingUnits"), 30);
	}

	public WebElement totalWeights() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[5]/div[1]/div/div[2]/div/div[2]/div[2]/input"), 30);
	}
	
	public WebElement deliveryFrequencySelect() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[5]/div[2]/div[1]/div[2]/div/select"), 30);
	}

	public WebElement deliveryFrequency() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@title='Delivery Frequency']/following::textarea[1]"), 30);
	}
	
	public WebElement customOrderDetails(String orderDetails) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'"+orderDetails+"')]/input"), 30);
	}
	
	public WebElement requiredTemp() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[@title='Req. Temperature']/following::input"), 30);
	}
	
	public WebElement regulatoryDetails() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Regulatory Details')]/following::textarea[1]"), 30);
	}
	
	public WebElement DeclaredValue() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@type='number' and @min='0.01' and @step='0.01']"), 30);
	}

	// Edit by shubham Xpath
	public WebElement DeclaredValue2() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @min='0.01' and @step='0.01'])[2]"), 30);
	} 

	public WebElement Cartons() {

		// return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='carton-count form-group col-xs-6 col-sm-1']/input[@class='form-control form-input input-sm']"), 30);
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@id='CartonHeaviest']/input"), 30);
	}
	public WebElement Accessorials() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']"), 30);
	} 
	public WebElement selectAccessorials() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section/div/div[4]/div[1]/div[2]/div/div/div[1]/div[9]/span[1]/div/ul/li/a/label"), 30);
	} 
	// Edit by shubham Xpath
	public WebElement Cartons2() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='CartonHeaviest']/input)[2]"), 30);
	}  
	public WebElement QuickEmail() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='PublicEmail']"), 30);
	} 
	public WebElement AccountType() {

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='PublicProfileType']"), 30);
	}
	public WebElement SaveButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Get Rate')] | //button[text()='Submit for Quote']"), 30);
	}
	
	public WebElement submitForQuote() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Submit for Quote']"), 30);
	}
	
	public WebElement backBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-lg btn-default pull-left']"), 60);
	}


	public boolean Carries() {
		return WaitTool.isElementPresentAndDisplay(driver, By.id("table-quotes"));
	}
	public void ValidateCarriers(String i) {
		List <WebElement> carriers= driver.findElements(By.xpath("//div[@class='col-xs-12 ratequote']"));
		int carriercnt= carriers.size();
		String j=Integer.toString(carriercnt);
		UseAssert.assertEquals(j, i);
	}

	public void expandCarries() {
		Log.info("Is Expand Carrier: "+WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Show')]")));
		if(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Show')]"))) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Show')]"), 10));
		}
	}

	public boolean verifyCarrier(String carrierName) {
		boolean isPresent = false;
		if(carrierName.equals("ABF Freight")) {
			isPresent =  WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/11.png']"));
		}else if(carrierName.equals("Ceva")){
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']"));
		}else if(carrierName.equals("Pilot Freight Services[Televisions]")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/301.png']"));
		}else if(carrierName.equals("Werner")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/302.png']"));
		}else if(carrierName.equals("FC Test Carrier")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/222.png']"));
		}else if(carrierName.equals("YRC")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/9.png']"));
		}else if(carrierName.equals("ESTES")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/22.png']"));
		}
		
		Log.info("Is Carrier Present: "+isPresent);
		return isPresent;
	}

	public boolean verifyBasicThreshold(String carrierName) {
		boolean isPresent= false;
		if(carrierName.equals("ABF Freight")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/11.png']")));
			isPresent =  WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/11.png']/ancestor::tr/descendant::span[text()='Basic Threshold (No Signature Required)']"));
		}else if(carrierName.equals("Ceva")){
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/44.png']")));
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/descendant::span[text()='Basic Threshold (No Signature Required)']"));
		}else if(carrierName.equals("Pilot Freight Services[Televisions]")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/301.png']")));
			isPresent =  WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/33.png']/ancestor::tr/descendant::span[text()='Basic Threshold (No Signature Required)']"));
		}else if(carrierName.equals("Werner")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/302.png']")));
			isPresent =  WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/302.png']/ancestor::tr/descendant::span[text()='Basic Threshold (No Signature Required)']"));
		}
		return isPresent;
	}
	public static int orderDate(){
		Calendar cal=Calendar.getInstance();
		int val = cal.get(Calendar.DAY_OF_WEEK);
		Log.info(new DateFormatSymbols().getWeekdays()[val]);
		String weekday=new DateFormatSymbols().getWeekdays()[val];


		if(weekday.equalsIgnoreCase("Saturday")){
			int orderdte=cal.get(Calendar.DAY_OF_MONTH)+2;
			return orderdte;
		}
		else if(weekday.equalsIgnoreCase("Sunday")){
			int orderdte=cal.get(Calendar.DAY_OF_MONTH)+1;
			return orderdte;
		}
		else {
			int orderdte=cal.get(Calendar.DAY_OF_MONTH);
			return orderdte;
		}

	}  

	public WebElement SelectCarrierCheckBox() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//i[@class='fa fa-circle-o'][1]"), 60);		
	} 

	//Add new function
	public WebElement getGroundRatetext(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//tr//td/span[text()='Ground']//following::td[4]"), 60);
	}

	public WebElement getStandardRatetext(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//tr//td/span[text()='Standard']//following::td[4]"), 60);
	}
	public WebElement getNextDayAirEarlyAMRatetext(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//td/span[text()='Next Day Air Early AM']//following::td[4]"), 60);
	}

	public void selectCarrier(String carrierName) {
		if(carrierName.equals("ABF Freight")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/11.png']")));
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/11.png']/ancestor::tr/td[7]/button"), 10));
		}else if(carrierName.equals("Ceva")){
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[7]/button"), 10));
		}else if(carrierName.equals("Pilot Freight Services[Televisions]")) {
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/33.png']/ancestor::tr/td[7]/button"), 10));
		}else if(carrierName.equals("Werner")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/302.png']")));
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/302.png']/ancestor::tr/td[7]/button"), 10));
		} else if(carrierName.equals("FC Test Carrier")) {
			SeleniumFunction.scrollIntoView(driver, driver.findElement(By.xpath("//img[@src='/Content/Images/Logos/222.png']")));
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/222.png']/ancestor::tr/td[7]/button"), 10));
		}
	}

	public String looseCartonPopupHeader() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::h5"), 10));
	}

	public String looseCartonPopupBody() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-body']"), 10));
	}

	public boolean looseCartonChangeOrder() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::button[@data-dismiss='modal']"));
	}

	public boolean looseCartonGetQuote() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::button[@class='btn btn-primary']"));
	}
	
	public WebElement closeLooseCartonPopup() {
		return WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//h5[text()='Loose Carton Warning']/preceding-sibling::button"), 20);

	}

	public void clickLooseCartonChangeOrder() {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::button[@data-dismiss='modal']"),10));
	}
	
	public String customOrderHeader() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-header']/descendant::h5"), 10));
	}
	
	public String customOrderPopupBody() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-body']"), 10));
	}
	
	public boolean customOrderReviseLTSDetailButton() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-footer']/descendant::button[1]"));
	}

	public boolean customOrderCustomQuoteButton() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-footer']/descendant::button[2]"));
	}
	
	public WebElement getCustomQuoteButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-footer']/descendant::button[2]"), 30);
	}
	public WebElement clickPopupFooterButton(int index) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-footer']/descendant::button["+index+"]"), 30);
	}

	public WebElement NextButton() {		
		return WaitTool.waitForElementPresentAndDisplay(driver,By.xpath("//table[@id='table-quotes']//tbody//tr[1]//td[7]//button"), 60);
	} 
	public WebElement understoodBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@type='button']/span[text()='Understood'])[1]"), 60);
	}

	public WebElement UPSSureButton() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'SurePost')]/parent::td/following-sibling::td[4]/button"), 60);	
	}  
	public WebElement PalletDesc() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='app-content']/div/div/div/section/article/div/div[2]/div[1]/div/div[2]/textarea"), 60);	
		// return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@name='pallet-description']"), 60);
	} 
	public WebElement PalletDescParcel() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@name='text-contents']"), 60);		
	}
	public WebElement Addproduct() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()=' Add Product to Pallet']"), 20);		
	}
	public WebElement searchproduct() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/article/div/div[2]/div[1]/div/div[3]/div[1]/div/div[1]"), 60);		
	}
	public WebElement productvalue() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Select or Search...'])[1]"), 60);		
	}
	public WebElement SpecialHandling() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@placeholder='Advise about any restrictions on Pick Up or Drop Off locations, special package handling, etc.']"), 30);		
	} 
	
	public WebElement genericPallet() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.name("palletType0"), 30);
	}

	public WebElement palletDescription() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.name("pallet-description"), 30);
	}

	public WebElement LocationName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Search address book or type new label'])[1]"), 30);		
	} 

	public WebElement Address1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address1']"), 30);		
	} 
	public WebElement Address2() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address2']"), 30);		
	} 
	public WebElement FirstName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='FirstName']"), 30);		
	} 
	public WebElement LastName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='LastName']"), 30);		
	} 
	public WebElement Phone1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Phone1']"), 30);		
	} 
	public WebElement Email() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Email'])[1]"), 30);		
	} 
	public WebElement DropLocationName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Search address book or type new label'])[2]"), 30);		
	} 

	public WebElement DropAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address1'])[4]"), 30);		
	} 
	public WebElement DropAddress2() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address2'])[2]"), 30);		
	} 
	public WebElement DropFirstName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='FirstName'])[2]"), 30);		
	} 
	public WebElement DropLastName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='LastName'])[2]"), 30);		
	} 
	public WebElement DropPhone1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Phone1'])[2]"), 30);		
	} 
	public WebElement DropEmail() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Email'])[2]"), 30);		
	} 
	
	public WebElement DropEmail2() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Email'])[3]"), 30);		
	} 
	public WebElement ReviewOrder() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-lg btn-primary pull-right']"), 30);	
	} 	
	public WebElement CreditCardNumber() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='txtCreditCardNumber']"), 60);		
	}
	public WebElement CardName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='txtNameOnCard']"), 30);		
	} 
	public WebElement CVV() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='txtCVVOnCard']"), 30);		
	} 	
	public WebElement Month() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='ddlExpiryMonth']"), 30);		
	} 
	public WebElement Year() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='ddlExpiryYear']"), 30);		
	}
	public WebElement BillFirstName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='first-name']"), 30);		
	}	
	public WebElement BillLastName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='last-name']"), 30);		
	}	
	public WebElement BillCompanytName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='company-name']"), 30);		
	}	
	public WebElement BillPhone() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='phone']"), 30);		
	}	
	public WebElement BillAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='address-1']"), 30);		
	}	
	public WebElement BillAddress2() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='address-2']"), 30);		
	}
	public WebElement Billzip() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='zip-code']"), 30);		
	}
	public WebElement Billcity() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@name='city']"), 30);		
	}
	public WebElement Billcountry() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@name='country']"), 30);		
	}
	public WebElement Billstate() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@name='state']"), 30);		
	}

	public boolean verifyBasicThresholdAtReview() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//h4[text()='Basic Threshold (No Signature Required)']"));
	}
	public WebElement Book() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@type='submit' and @class='btn btn-lg btn-primary pull-right']"), 30);		
	}
	public WebElement BookandPay() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@class='btn btn-primary btn-block']"), 30);		
	}	
	public WebElement Okbutton1() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@id='btn-continue-to-orders']"), 60);
	}
	
	public WebElement Okbutton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id='paymentSummaryModal']/descendant::button[@data-dismiss='modal']"), 60);
	}
	
	//***************************************************************Added by Shubham for international**********************************************************

	public WebElement insuranceCheckbox(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'Insurance')]//input"), 60);
	}

	public WebElement internationalDesc(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='e.g. Refrigerator']"), 60);
	}

	public WebElement HSCode(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Search by code or keyword']"), 60);
	}
	public WebElement selectHSCode(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[contains(text(),'HS Code')]//following::div[@data-value='2460']//strong"), 60);
	}

	public WebElement setInternationalDeclaredValue(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='declared-value form-group col-xs-6 col-sm-1']//input[@class='form-control input-sm form-input']"), 60);
	}
	
	public WebElement setInternationalDeclaredValue2(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[contains(@class,'declared-value form-group col-xs-6 col-sm-1')]//input[@class='form-control input-sm form-input']"), 60);
	}
	public WebElement countryOfOrigin(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-input items not-full has-options']/input[@type='text']"), 60);
	}
	public WebElement selectCountryOfOrigin(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']//div[@data-value='226']"), 60);
	}
	
	public void waitForQuotesToAppear() {
		
		String displayValue ;		
		for(int i = 2; i < 40; i=i+2) {
			displayValue =  driver.findElement(By.xpath("//span[text()='Searching for quotes...']")).getCssValue("display");
			if(!displayValue.equals("none")){
				Log.info("------i-------------"+i);
				WaitTool.sleep(i);
			}
		}
	}
	
	public WebElement rates(String carriername){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//td/span[text()='"+carriername+"']//following::td[4]"), 60);
	}


	public WebElement diningChair(){

		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//img[@class='product-item-img'])[1]"), 10);
	}

	public WebElement HomeSqdiningChair(){

		//return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//img[@class='product-item-img product-click-event'])[1]"), 10);
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//a[@class='product-description list-product-title'])[1]"), 10);
	}
	
	public String productPrice() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h3[@itemprop='price']"), 10));
	}
	
	public WebElement State() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='StateProvince']"), 30);		
	} 

	public WebElement Country() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Country']"), 30);		
	} 
	public WebElement City() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='City']"), 30);		
	}  
	
	public WebElement ZipCode() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='ZipPostal']"), 30);		
	} 
	
	public WebElement PickUpCompanyName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Name'])[1]"), 30);		
	} 
	
	public WebElement dropOffCompanyName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Name'])[2]"), 30);		
	} 
	
	public WebElement PickUpAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address1'])[2]"), 30);		
	}
	
	public WebElement dropUpAddress3() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address1'])[3]"), 30);		
	}
	
	public WebElement PickUpFirstName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='FirstName'])[1]"), 30);		
	} 
	public WebElement PickUpLastName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='LastName'])[1]"), 30);		
	} 
	public WebElement PickUpPhone1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Phone1'])[2]"), 30);		
	}
}
