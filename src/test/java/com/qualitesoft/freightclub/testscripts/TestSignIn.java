package com.qualitesoft.freightclub.testscripts;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.ScreenShot;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.SignInPage;

public class TestSignIn extends InitializeTest {

	@Test
	public void testSignIn(){
			SignInPage signInPage = new SignInPage(driver);
			SeleniumFunction.sendKeys(signInPage.usernameTextField(), fcusername);
			SeleniumFunction.sendKeys(signInPage.passowrdTextField(), fcpassword);
			WaitTool.sleep(1);
			if(!signInPage.usernameTextField().getAttribute("value").equals(fcusername)) {
				SeleniumFunction.sendKeys(signInPage.usernameTextField(), fcusername);
				SeleniumFunction.sendKeys(signInPage.passowrdTextField(), fcpassword);
			}
			ScreenShot.takeScreenShot(driver, "Login detail inserted");
			SeleniumFunction.clickJS(driver, signInPage.loginButton());
			WaitTool.sleep(5);
			ScreenShot.takeScreenShot(driver, "Login Successfully");
	}
}
