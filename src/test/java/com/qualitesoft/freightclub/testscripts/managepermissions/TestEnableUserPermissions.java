package com.qualitesoft.freightclub.testscripts.managepermissions;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.freightclub.pageobjects.ManagePermissions;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestEnableUserPermissions extends InitializeTest {
	
	@Test
	public void testEnableUserPermissions() {
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		ManagePermissions managePermissions = new ManagePermissions(driver);
		
		//click manage permissions link
		SeleniumFunction.click(managePermissions.managePermissionsLink());
		quickQuote.acceptPopup();
		
		//search user name
		SeleniumFunction.sendKeys(managePermissions.parentAccount(), searchUser);
		WaitTool.sleep(5);
		SeleniumFunction.click(managePermissions.selectParentAccount());
		
		//select searched user
		WaitTool.sleep(10);
		SeleniumFunction.click(managePermissions.accountLogin());
		
		//select user permission
		String[] userPermission = userPermissions.split(",");
		for(int i=0; i<userPermission.length; i++) {
			System.out.println(managePermissions.userPermissions(userPermission[i]).isSelected());
			if(!managePermissions.userPermissions(userPermission[i]).isSelected())
				SeleniumFunction.click(managePermissions.userPermissions(userPermission[i]));
		}
		
		//click set permissions
		SeleniumFunction.scrollDownUptoFooter(driver);
		SeleniumFunction.click(managePermissions.setPermissions());	
	}
}
