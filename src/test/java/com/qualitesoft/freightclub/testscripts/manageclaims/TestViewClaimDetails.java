package com.qualitesoft.freightclub.testscripts.manageclaims;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.SoftAssertion;
import com.qualitesoft.core.WaitTool;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;
import com.qualitesoft.freightclub.pageobjects.ManageClaims;
import com.qualitesoft.freightclub.pageobjects.ManageOverages;
import com.qualitesoft.freightclub.pageobjects.QuickQuoteFinal;

public class TestViewClaimDetails extends InitializeTest {
	
	@Test
	public void testViewClaimDetails() {
		
		ManageOverages manageOverages = new ManageOverages(driver);
		ManageClaims manageClaims = new ManageClaims(driver);
		QuickQuoteFinal quickQuote = new QuickQuoteFinal(driver);
		CommonOps commonOps = new CommonOps();

		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		int i=Integer.parseInt(Row);

		String orderId=xr.getCellData("Input","OrderId", i).trim();
		String orderDate=xr.getCellData("Input","pickUpDate", i).trim();

		String declaredValue=xr.getCellData("Input","DeclaredValue", i).trim(); 
		String trackingID=xr.getCellData("Input","Tracking#", i).trim();
		String customerPO=xr.getCellData("Input","orderReferenceID", i).trim(); 
		String upsInsuranceCoverage=xr.getCellData("Input","UpsInsurance", i).trim();
		String amount = xr.getCellData("Input","Amount", i).trim();
		String wayBill=xr.getCellData("Input","WayBill", i).trim();
		String insurance=xr.getCellData("Input","Insurance", i).trim();

		
		String claimStatus=xr.getCellData("ClaimDetail","ClaimStatus", i).trim();

		String companyName=xr.getCellData("CreateAccount","CompanyName", 2).trim(); 

		String pickCompanyName=xr.getCellData("ShipmentInformation","PickCompanyName", 2).trim(); 
		String pickAddressLine1=xr.getCellData("ShipmentInformation","PickUpAddressLine1", 2).trim(); 
		String pickCity=xr.getCellData("ShipmentInformation","PickCity", 2).trim(); 
		String pickState=xr.getCellData("ShipmentInformation","PickState", 2).trim(); 
		String pickzip=xr.getCellData("ShipmentInformation","PickZip", 2).trim(); 
		String pickPhone=xr.getCellData("ShipmentInformation","PickUpPhone1", 2).trim();

		String dropCompanyName=xr.getCellData("ShipmentInformation","DropCompanyName", 2).trim(); 
		String dropAddressLine1=xr.getCellData("ShipmentInformation","DropOffAddressLine1", 2).trim(); 
		String dropCity=xr.getCellData("ShipmentInformation","DropCity", 2).trim(); 
		String dropState=xr.getCellData("ShipmentInformation","DropState", 2).trim(); 
		String dropZip=xr.getCellData("ShipmentInformation","DropZip", 2).trim(); 
		String dropPhone=xr.getCellData("ShipmentInformation","DropPhone", 2).trim();

		String shipper = pickCompanyName+" "+pickAddressLine1+" "+pickCity+", "+pickState+" "+pickzip+" "+pickPhone;
		String consignee = dropCompanyName+" "+dropAddressLine1+" "+dropCity+", "+dropState+" "+dropZip+" "+dropPhone;

		//get data from claim details sheet
		String claimID=xr.getCellData("ClaimDetail","ClaimsID", i).trim();
		String claimType=xr.getCellData("ClaimDetail","ClaimType", i).trim();

		//open manage orders page then navigate to claim details
		commonOps.openManageClaimsPageAndSearchOrder(orderId);
		SeleniumFunction.click(manageOverages.viewEdit(1));	
		
		try {
			//switch to manage claims window
			SeleniumFunction.getCurrentWindow(driver); WaitTool.sleep(30);
			quickQuote.acceptPopup();
			
			//verify claim details
			Date myDate=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
			String currentDate=dateFormat.format(myDate);

			String actualShipper = manageClaims.getLabel("Shipper:").getText().replaceAll("[\\t\\n\\r]+"," ");
			Log.info("Actual Shipper Name: "+actualShipper);
			String actualConsignee = manageClaims.getLabel("Consignee:").getText().replaceAll("[\\t\\n\\r]+"," ");
			Log.info("Actual Consignee Name: "+actualConsignee);

			//Verify claim details
			SoftAssertion.assertEquals(manageClaims.getLabel("Company:").getText(), companyName);
			SoftAssertion.assertEquals(manageClaims.getLabel("Claim Date:").getText(), currentDate);
			SoftAssertion.assertEquals(manageClaims.getLabel("Order ID:").getText(), orderId);
			SoftAssertion.assertEquals(manageClaims.getLabel("Customer PO:").getText(), customerPO);
			SoftAssertion.assertEquals(manageClaims.getLabel("Declared Value:").getText(), "$"+declaredValue+".00" );
			if(userType.equals("User")) {
				SoftAssertion.assertEquals(manageClaims.getLabel("UPSC Insurance Coverage:").getText(), upsInsuranceCoverage);
			} else  {
				Select insuranceCoverage = new Select(manageClaims.upscInsuranceCoverage());
				SoftAssertion.assertEquals(insuranceCoverage.getFirstSelectedOption().getText(), upsInsuranceCoverage); 
			}
			
			SoftAssertion.assertEquals(actualShipper, shipper);
			SoftAssertion.assertEquals(actualConsignee, consignee);
			SoftAssertion.assertEquals(manageClaims.getSelect("Claim Type:").getText(), claimType);
			SoftAssertion.assertEquals(manageClaims.getField("Original Tracking #:").getAttribute("value"), trackingID);
			if(userType.equals("User")) {
				SoftAssertion.assertEquals(manageClaims.getField("Claim Status:").getAttribute("value"), claimStatus);
				Assert.assertFalse(manageClaims.getField("Claim Status:").isEnabled());
			} else  {
				SoftAssertion.assertEquals(manageClaims.getSelect("Claim Status:").getText(), claimStatus);
				Assert.assertTrue(manageClaims.getSelect("Claim Status:").isEnabled());
			}
			
			if(userType.equals("Admin")) {
				
				  if(upsInsuranceCoverage.equals("No")) {
					  SoftAssertion.assertEquals(manageClaims.getLabel("UPSC Parcel Band:").getText(), "");
					  SoftAssertion.assertEquals(manageClaims.getLabel("UPSC LTL Band:").getText(),"");  
				  } else { 
					  SoftAssertion.assertEquals(manageClaims.getLabel("UPSC Parcel Band:").getText(), "A");
					  SoftAssertion.assertEquals(manageClaims.getLabel("UPSC LTL Band:").getText(), "A");
				  }
				 
				SoftAssertion.assertEquals(manageClaims.getLabel("Order Date:").getText(), orderDate);
				SoftAssertion.assertEquals(manageClaims.getLabel("Claim ID:").getText(), claimID);
				//SoftAssertion.assertEquals(manageClaims.getLabel("Review Date:").getText(), claimID);

				Double productAmount = Double.parseDouble(declaredValue);
				Double carrierFreightAmount = Double.parseDouble(manageClaims.getField("Carrier Freight Amount:").getAttribute("value"));
				Double companyFreightAmount = Double.parseDouble(manageClaims.getField("Company Freight Amount:").getAttribute("value"));

				double totalInternalClaimAmountDouble = Math.round((productAmount + carrierFreightAmount) * 100.0) / 100.0;
				String totalInternalClaimAmount = String.valueOf(totalInternalClaimAmountDouble);
				System.out.println(totalInternalClaimAmount);
				double totalCustomerClaimAmountDouble = Math.round((productAmount + companyFreightAmount) * 100.0) / 100.0;
				String totalCustomerClaimAmount = String.valueOf(totalCustomerClaimAmountDouble);
				
				
				SoftAssertion.assertEquals(manageClaims.getLabel("Total Internal Claim Amount:").getText(), "$"+totalInternalClaimAmount);
				SoftAssertion.assertEquals(manageClaims.getLabel("Total Customer Claim Amount:").getText(), "$"+totalCustomerClaimAmount);
				SoftAssertion.assertEquals(manageClaims.getField("Waybill #:").getAttribute("value"), wayBill);
				SoftAssertion.assertEquals(manageClaims.getSelect("Carrier:").getText(), xr.getCellData("Input","Carrier", i).trim());

				SoftAssertion.assertEquals(manageClaims.getField("Product Amount:").getAttribute("value"), declaredValue);
				int deductibleAmount = (Integer.parseInt(declaredValue) * 30) / 100 ;
				
				
				
				if(claimType.equals("Loss")) {
					SoftAssertion.assertEquals(manageClaims.getField("Company Freight Amount:").getAttribute("value"), amount.replace("$", ""));
					SoftAssertion.assertEquals(manageClaims.getField("Deductible Amount:").getAttribute("value"), "0");
					SoftAssertion.assertEquals(manageClaims.getField("Premium Amount:").getAttribute("value"), "0");
				} else {
					double companyFreightAmount1 = Math.round((Double.parseDouble(amount.replace("$", "").replace(",", "")) -  Double.parseDouble(insurance.replace("$", ""))) * 100.0) / 100.0;
					SoftAssertion.assertEquals(manageClaims.getField("Company Freight Amount:").getAttribute("value"), String.valueOf(companyFreightAmount1));
					SoftAssertion.assertEquals(manageClaims.getField("Deductible Amount:").getAttribute("value"), String.valueOf(deductibleAmount));
					SoftAssertion.assertEquals(manageClaims.getField("Premium Amount:").getAttribute("value"), insurance.replace("$", ""));
				}
				
				//verify documents uploaded by user
				manageClaims.verifyUploadedDocument("Commercial and Sales Invoice:", xr.getCellData("ClaimDetail", "Commercial and Sales Invoice:", i));
				if(claimType.equals("Loss")) {
					manageClaims.verifyUploadedDocument("Signed Bill of Lading:", xr.getCellData("ClaimDetail", "Pictures:", i));
				} else {
					manageClaims.verifyUploadedDocument("Pictures:", xr.getCellData("ClaimDetail", "Pictures:", i));

				}
				manageClaims.verifyUploadedDocument("Proof of Liability (Refund, Replacement, Repair or Credit):", xr.getCellData("ClaimDetail", "Proof of Liability (Refund, Replacement, Repair or Credit):", i));
				manageClaims.verifyUploadedDocument("Additional Documents:", xr.getCellData("ClaimDetail", "Additional Documents:", i));	
			}
			
			//close current window and switch to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			
		}catch(Exception ex) {
			Log.info("Test Failure Reason: "+ex.getCause());
			//close current window and switch to parent window
			SeleniumFunction.closeWindow(driver);
			SeleniumFunction.getCurrentWindow(driver);
			throw ex;
		}
	}
}
