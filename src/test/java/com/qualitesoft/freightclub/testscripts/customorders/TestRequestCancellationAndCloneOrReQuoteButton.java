package com.qualitesoft.freightclub.testscripts.customorders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.pageobjects.ManagerOrderPage;

public class TestRequestCancellationAndCloneOrReQuoteButton extends InitializeTest {
	
	public Long getDuration() {

		String startTimeStr = "12:30:00";

		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();
		String endTimeStr = df.format(dateobj);

		LocalDate today = LocalDate.now();
		String startTimeStrT = today + " " + startTimeStr;
		String endTimeStrT = today + " " + endTimeStr;

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime startTime = LocalDateTime.parse(startTimeStrT,
				formatter);
		LocalDateTime endTime = LocalDateTime.parse(endTimeStrT, formatter);

		Duration d = Duration.between(startTime, endTime);
		Long l = d.getSeconds();

		if (d.getSeconds() == 0)
			Log.info("Both Start time and End Time are equal");
		else if (d.getSeconds() > 0)
			Log.info("Start time is less than end time");
		else
			Log.info("Start time is greater than end time");

		return l;
	}
	
	@Test
	public void testRequestCancellationAndCloneOrReQuoteButton() {

		ManagerOrderPage manageOrderpage = new ManagerOrderPage(driver);

		Xls_Reader xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);
		Log.info("Data Row: " +Row);
		String orderid=xr.getCellData("Input","OrderId", i).trim();

		manageOrderpage.orderIDFilter(orderid);
		manageOrderpage.orderIdFilter().sendKeys(Keys.ENTER);
		WaitTool.sleep(5);

		if(!manageOrderpage.ExpandMenupage().getAttribute("class").equals("active")) {
			SeleniumFunction.click(manageOrderpage.ExpandMenupage());
		}
		SeleniumFunction.clickJS(driver,manageOrderpage.ActionButton());

		if(i==5) {
			Assert.assertFalse(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[text()='Request Cancellation']")));
		}

		if(i == 3) {
			Long d = getDuration();
			if (d == 0) {
				Log.info("Both Start time and End Time are equal");
			} else if (d > 0) {
				Log.info("Start time is less than end time");
				if(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[text()='Re-Quote']"))) {
					Assert.assertTrue(true);
				} else {
					Assert.assertFalse(true);
				} 
				ScreenShot.takeScreenShot(driver, "Request Cancellation and Re-Quote button presence");
			} else {
				Log.info("Start time is greater than end time");
				if(WaitTool.isElementPresentAndDisplay(driver, By.xpath("//a[text()='Clone']"))) {
					Assert.assertTrue(true);
				} else {
					Assert.assertFalse(true);
				} 
				ScreenShot.takeScreenShot(driver, "Request Cancellation and Clone button presence");
			}
		}

		ScreenShot.takeScreenShot(driver, "Request Cancellation button presence.");
	}
}
