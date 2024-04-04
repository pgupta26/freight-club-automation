package com.qualitesoft.freightclub.testscripts.greenlist;

import org.testng.annotations.Test;
import com.qualitesoft.core.InitializeTest;

import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.GreenList;
import com.qualitesoft.freightclub.pageobjects.QuickQuote;


public class TestManageGreenlist extends InitializeTest {

	@Test
	public void testManagegreenlist() {

		GreenList greenList = new GreenList(driver);
		SeleniumFunction.click(greenList.manageGreenLink());
		
		QuickQuote quickQuote = new QuickQuote(driver);
		WaitTool.sleep(5);
		quickQuote.acceptPopup();
		WaitTool.sleep(2);
		
		SeleniumFunction.click(greenList.downloadGreenlistLink());
		WaitTool.sleep(10);

		//open excel and take screenshot  
	    try {
	    	Runtime.getRuntime().exec("cmd /c start \"\" \"testdata\\FCfiles\\"+env+"\\Greenlist_Template.xlsx\"");
	    	WaitTool.sleep(20);
			ScreenShot.takeScreenShotSystemExcel("Green List downloaded");
			WaitTool.sleep(15);
			Runtime.getRuntime().exec(
			        "cmd /c taskkill /f /im excel.exe");
	    	
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
	    //click select files
	    SeleniumFunction.click(greenList.selectFiles());

		//upload greenlist
	    WaitTool.sleep(10);
	    ScreenShot.takeScreenShot(driver, "File Upload Dialog");
	    SeleniumFunction.uploadFile("Greenlist_Template.xlsx");
		WaitTool.sleep(5);
		SeleniumFunction.clickAction(driver, greenList.uploadButton());
		ScreenShot.takeScreenShot(driver, "Green List selected");
		WaitTool.sleep(5);
		SeleniumFunction.clickAction(driver, greenList.OKButton());
		WaitTool.sleep(5);
		ScreenShot.takeScreenShot(driver, "Green List uploaded");
	}
}
