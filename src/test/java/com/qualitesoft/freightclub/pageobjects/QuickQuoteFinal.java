package com.qualitesoft.freightclub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;


public class QuickQuoteFinal {

	WebDriver driver;

	public QuickQuoteFinal(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement quickQuoteLink() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[contains(@href,'/QuickQuote/QuickQuote')]/span[@class='link-title']"), 30);
	}

	public void acceptPopup() {
		WaitTool.sleep(5);
		if(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//button[@data-role='end']"))) {
			ScreenShot.takeScreenShot(driver, "Popup"+JavaFunction.getRandomNumber(10, 10000));
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@data-role='end']"), 10));
		}
		WaitTool.sleep(2);
	}

	public WebElement shipmentType(String shipmentType) {
		if(shipmentType.equals("Parcel")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Parcel']/preceding::div[1]/parent::label"), 30);
		}else if(shipmentType.equals("Less Than Truckload")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Less-Than-Truckload (LTL)']/preceding::div[1]/parent::label"), 30);
		}else if(shipmentType.equals("Custom")) {
			return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='Custom Order']/preceding::div[1]/parent::label"), 30);
		}else {
			return null;
		}
	}

	public WebElement OrderDate() {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='OrderDate']"), 30));
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='datepicker-days']//td[@class='today day']) | (//div[@class='datepicker-days']//td[@class='day'])"), 10);
	}

	public WebElement OrderReferenceID() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='OrderReferenceID']"), 30);
	}

	public WebElement serviceLevel(String serviceLevel) {
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@placeholder='Please select an option']"), 30));
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='selectize-dropdown-content']/descendant::strong[contains(text(),'"+serviceLevel+"')]"), 30);
	}

	public WebElement PickUpZip() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='pickupzipEntry']"), 30);
	}
	
	public String getPickUpZip() {
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='pickupzipEntry']"), 30));
	}
	
	public WebElement selectLocationName(String locationName) {
		WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Select location or enter ZIP/Postal code'])[1]"), 30).sendKeys(Keys.chord(locationName.substring(0, 6)));
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//strong[text()='"+locationName.substring(6, locationName.length())+"']"), 10);
	}
	
	public String getPickUpLocation() {
		
		WaitTool.sleep(1);
		WebElement element = driver.findElement(By.xpath("(//select[@class='form-control input-sm form-input selectized'])[1]"));

		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("arguments[0].style.display='block';", element);

		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//select[@class='form-control input-sm form-input selectized']/option)[1]"), 10)); 

	}
	
	public String getDropOffLocation() {
		
		WaitTool.sleep(1);
		WebElement element = driver.findElement(By.xpath("(//select[@class='form-control input-sm form-input selectized'])[2]"));

		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("arguments[0].style.display='block';", element);

		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//select[@class='form-control input-sm form-input selectized']/option)[2]"), 10)); 

	}

	public WebElement DropOffZip() {
		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='dropoffzipEntry']"), 30);
	}
	
	public String getDropOffZip() {
		
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='dropoffzipEntry']"), 30));
	}
	

	public WebElement PickUpLocationType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[text()='Pick Up Location ']/ancestor::div[@class='col-xs-12 col-sm-6']/descendant::select[@class='form-input form-control input-sm']"),60);
	}
	
	public String getPickUpLocationType() {
		Select sc = new Select(this.PickUpLocationType());
		return sc.getFirstSelectedOption().getText();
	}

	public WebElement DropOffLocationType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//h5[text()='Drop Off Location ']/ancestor::div[@class='col-xs-12 col-sm-6']/descendant::select[@class='form-input form-control input-sm']"),60);
	}
	
	public String getDropOffLocationType() {
		Select sc = new Select(this.DropOffLocationType());
		return sc.getFirstSelectedOption().getText();
	}
	
	public void deselectInsurance() {
		SeleniumFunction.scrollDownByPixel(driver, "200");
		WaitTool.sleep(2);
		WebElement insurance = WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//*[@id=\"app-content\"]/div/div/div/section/section[2]/div/div[4]/div[2]/div[3]/div/div[2]/div[4]/label/input"), 30);
		Log.info("Insurance Checkbox: "+insurance.isSelected());
		if(insurance.isSelected())  {
			SeleniumFunction.click(insurance);
		}
	}
	
	public WebElement QuickEmail() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='PublicEmail']"), 30);
	} 
	public WebElement AccountType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//select[@id='PublicProfileType']"), 30);
	}

	public WebElement PackageType(String packageType, int itemIndex) {
		if(InitializeTest.loginuser ==  null ||  !InitializeTest.loginuser.equals("new")) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Select or Search...'])['"+itemIndex+"']"), 30));
		} else {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Select packaging'])['"+itemIndex+"']"), 30));
		}
		WaitTool.sleep(3);
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@data-group='packaging']/div[contains(text(),'"+packageType+"')])["+itemIndex+"]"), 30);
	}
	
	public WebElement PackageType() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='form-group col-sm-2']/div[@class='form-input selectize-control single']"), 90);
	}
	
	public void PackageTypeOptions(String packageType) {
		SeleniumFunction.executeJS(driver, "arguments[0].click();", driver.findElement
				(By.xpath("//div[@data-group='packaging']/div[contains(text(),'"+packageType+"')]")));
	}
	
	public WebElement addProduct(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@data-group='packaging']/following-sibling::div/div[contains(text(),'New Product')])["+itemIndex+"]"), 30);
	}
	
	public WebElement productvalue(int itemIndex) {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Select or Search...'])["+itemIndex+"]"), 30);		
	}
	
	public WebElement quantity(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[contains(text(),'Quantity')]/following::input[1])["+itemIndex+"]"), 30);
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


	public WebElement Weight(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(@class,'weight')])["+itemIndex+"]/descendant::input[1]"), 30);
	}

	public WebElement DimensionL(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Length'])["+itemIndex+"]"), 30);
	}

	public WebElement DimensionW(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Width'])["+itemIndex+"]"), 30);
	}

	public WebElement DimensionH(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @placeholder='Height'])["+itemIndex+"]"), 30);
	}

	public WebElement Category(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[@title='Category']/parent::label/following-sibling::select)["+itemIndex+"]"), 30);
	}

	public WebElement popupCateogory(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//span[text()='Got it!'])["+itemIndex+"]"), 30);
	}

	public WebElement DeclaredValue(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@type='number' and @min='0.01' and @step='0.01'])["+itemIndex+"]"), 30);
	}

	public WebElement Cartons(int itemIndex) {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@id='CartonHeaviest']/input)["+itemIndex+"]"), 30);
	}

	public WebElement addadditionalItem() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//button[@class='btn btn-info btn-xs'])[1]"), 30);
	}

	public void copyItemInformation(int index) {
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[contains(@class,'well well-white quote-item margin-right-10')]/descendant::button[1])["+(index+1)+"]"), 10));	
	}

	public void deleteItemInformation() {
		SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//div[@class='row margin-left-0'])[1]/descendant::li[2]/button"), 10));
	}
	
	public WebElement SaveButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[contains(text(),'Get Rate')] | //button[text()='Submit for Quote']"), 30);
	}

	public void waitForQuotesToAppear() {
		List<WebElement> searchForQuotes = driver.findElements(By.xpath("//span[text()='Searching for quotes...']"));
		
		if(searchForQuotes.size() > 0) {
			String displayValue ;		
			for(int i = 2; i < 40; i=i+2) {
				displayValue =  driver.findElement(By.xpath("//span[text()='Searching for quotes...']")).getCssValue("display");
				if(!displayValue.equals("none")){
					Log.info("------i-------------"+i);
					WaitTool.sleep(i);
				}
			}
		}
	}
	
	public void expandCarries() {
		boolean flag = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Show')]"));
		if(flag) {
			SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[contains(text(),'Show')]"), 10));
		}
	}
	
	public int totalQuotesCount() {
		return WaitTool.waitForElementsPresentAndDisplay(driver, By.cssSelector("#table-quotes tbody tr"), 10).size();
	}
	
	public WebElement NextButton() {		
		return WaitTool.waitForElementPresentAndDisplay(driver,By.xpath("//table[@id='table-quotes']//tbody//tr[1]//td[7]//button"), 60);
	} 
	
	public void selectCarrier(String carrierName) {
		try {
			if(carrierName.equals("ABF Freight")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/11.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("Ceva")){
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/44.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("Pilot Freight Services[Televisions]")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/33.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("Werner")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/302.png']/ancestor::tr/td[7]/button"), 10));
			} else if(carrierName.equals("FC Test Carrier")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/222.png']/ancestor::tr/td[7]/button"), 10));
			} else if(carrierName.equals("Ups")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/1.png']/ancestor::tr/td[7]/button"), 10));
			} else if(carrierName.equals("Ups SurePost")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//span[contains(text(),'SurePost')]/parent::td/following-sibling::td[4]/button"), 10));
			}else if(carrierName.equals("YRC")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/9.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("Estes")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/22.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("BTX Global Logistics")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/291.png']/ancestor::tr/td[7]/button"), 10));
			}else if(carrierName.equals("FragilePAK")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/280.png']/ancestor::tr/td[7]/button"), 10));
			}
			
		}catch(Exception ex) {
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/11.png']/ancestor::tr/td[7]/button"), 10));
		}
	}

	//Shipment Completion Tab
	public WebElement nonPalletDescription() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@placeholder='It is highly recommended to detail what is inside the box for claims and insurance purposes. E.g. game console, microwave ']"), 60);	
	} 
	
	public WebElement palletDescription() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.name("pallet-description"), 30);
	}
	
	public WebElement totalCartonCount() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Total Carton Count:']/following-sibling::span"), 30);
	}
	public WebElement genericPallet() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.name("palletType0"), 30);
	}
	public WebElement numberOfCartoons() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//label[text()='Number of cartons on pallet']/following::input"), 30);
	}
	
	public WebElement SpecialHandling() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//textarea[@placeholder='Advise about any restrictions on Pick Up or Drop Off locations, special package handling, etc.']"), 30);		
	} 

	public WebElement LocationName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@placeholder='Search address book or type new label'])[1]"), 30);		
	}
	
	public WebElement pickUpAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address1']"), 30);		
	} 
	
	public WebElement PickUpAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address1'])[2]"), 30);		
	}
	
	public WebElement pickUpAddress2() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Address2']"), 30);		
	} 
	
	public WebElement pickUpFirstName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='FirstName']"), 30);		
	} 
	public WebElement pickUpLastName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='LastName']"), 30);		
	} 
	public WebElement pickUpPhone1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//input[@id='Phone1']"), 30);		
	} 
	
	public WebElement DropAddress1() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Address1'])[3]"), 30);		
	} 
	
	public WebElement DropAddress4() {		
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
	
	public WebElement ReviewOrder() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-lg btn-primary pull-right']"), 30);	
	}
	
	public WebElement getCustomerPOValidationMsg() { 
		return WaitTool.returnWebElement(driver, By.xpath("//div[@id='fragilePakOrderReferenceModal']/descendant::div[@class='modal-body']/div"));
	}
	
	public WebElement acceptCustomerPOValidationPopup() {
		return WaitTool.returnWebElement(driver, By.xpath("//button[text()='Submit']"));
	}
	
	public WebElement customerPONumber() {
			return WaitTool.returnWebElement(driver, By.xpath("//strong[text()='Customer PO Number']/following::div[1]"));
	}
	
	public WebElement Book() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@type='submit' and @class='btn btn-lg btn-primary pull-right']"), 30);		
	}
	public WebElement Okbutton1() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@id='btn-continue-to-orders']"), 300);
	}

	public boolean acknowleadgeBtnStatus(){
		return WaitTool.waitForElementBoolean(driver, By.xpath("//span[text()='Acknowledge']//parent::button"), 60);
	}
	
	public WebElement acknowleadgeBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[text()='Acknowledge']//parent::button"), 10);
	}
	
	public WebElement acknowleadgeModalHeader(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']//h5"), 10);
	}
	
	public WebElement acknowleadgeModalBody(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']//div[@class='modal-body']"), 10);
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
		}else if(carrierName.equals("Estes")) {
			isPresent = WaitTool.isElementPresentAndDisplay(driver, By.xpath("//img[@src='/Content/Images/Logos/22.png']"));
		}else if(carrierName.equals("BTX Global Logistics")) {
			SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/291.png']"), 10));
		}else if(carrierName.equals("Ups") || carrierName.equals("Ups SurePost")) {
				SeleniumFunction.clickJS(driver, WaitTool.waitForElementPresentAndDisplaySoft(driver, By.xpath("//img[@src='/Content/Images/Logos/1.png']"), 10));
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
	
	public boolean verifyBasicThresholdAtReview() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//h4[text()='Basic Threshold (No Signature Required)']"));
	}
	
	public WebElement backBtn(){
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[@class='btn btn-lg btn-default pull-left']"), 60);
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
	
	public WebElement getCustomQuoteButton() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='vue-dialog-confirm modal fade in']/descendant::div[@class='modal-footer']/descendant::button[2]"), 30);
	}
	
	public WebElement PickUpCompanyName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Name'])[1]"), 30);		
	} 
	
	public WebElement dropOffCompanyName() {		
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("(//input[@id='Name'])[2]"), 30);		
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
	
	public WebElement submitForQuote() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//button[text()='Submit for Quote']"), 30);
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
	
	public boolean isReRatePresent() {
		return WaitTool.isElementPresentAndDisplay(driver, By.xpath("//span[text()='Re-rate with the new weight']"));
	}
	
	public WebElement reRate() {
		return WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//span[text()='Re-rate with the new weight']"), 10);
	}
	
	public String verifyToastMessage(){
		return SeleniumFunction.getText(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='toast toast-success']"), 180));
	}
	
}
