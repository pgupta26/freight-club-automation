package com.qualitesoft.core;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot extends InitializeTest {

	static String baseScreenShotsFolder;
	static BufferedImage image = null;	
	static String popupScreenShotFolder;

	public static void takeScreenShot(WebDriver driver, String fileName) {
		try {
			WaitTool.sleep(2);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(baseScreenShotsFolder +File.separator+fileName+ ".png"));
			Log.info("Screenshot " + fileName + " successfully taken.");
		} catch (Exception e) {
			Log.warn("Not able to take " + fileName + " screen shot: " + e.getMessage());
		}
	}
	
	public static void takeFullScreenShot(String fileName) {

		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);             
		  try {                 
		  ImageIO.write(screenshot.getImage(),"PNG",new File(baseScreenShotsFolder + "/" + fileName + ".png"));
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
		
	}
	public static void takeScreenShotOnFailure(String fileName) {
		try {
			WaitTool.sleep(2);
			Screenshot scrFile=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(scrFile.getImage(),"PNG",new File(baseScreenShotsFolder + "/" +fileName + "_failed.png"));
			Log.info("Faliure screenshot " + fileName + " successfully taken.");
		} catch (Exception e) {
			Log.warn("Not able to take " + fileName + " screen shot: " + e.getMessage());
		}
	}
	public static void takeScreenShotSystemExcel(String fileName) {

		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	    	ImageIO.write(image, "png", new File(baseScreenShotsFolder + "/" + fileName + ".png"));
			Log.info("Screenshot " + fileName + " successfully taken.");
		} catch (Exception e) {
			Log.warn("Not able to take " + fileName + " screen shot: " + e.getMessage());
		}
	}
	public static String createScreenshotFolder(String testName) {
		try {
			baseScreenShotsFolder = System.getProperty("user.dir") +File.separator+"screen-shots"+File.separator;
			SimpleDateFormat sdfmth = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			baseScreenShotsFolder = baseScreenShotsFolder +InitializeTest.project+File.separator+testName+File.separator
					+ sdfmth.format(new Date());
			File file = new File(baseScreenShotsFolder);
			if(!file.exists())  {
				file.mkdirs();
			}
			
			WaitTool.sleep(1);
			return baseScreenShotsFolder;
		} catch (Exception e) {
			Log.warn("Not able to create scree-shot folder: " + e.getMessage());
			return null;
		}
	}
}
