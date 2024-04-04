package com.qualitesoft.freightclub.testscripts.manageorders;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.Xls_Reader;
import com.qualitesoft.freightclub.appcommon.CommonOps;

public class TestPDFLabelText extends InitializeTest {

	@Test(priority = 0)
	public void testPDFLabelText() throws IOException {

		CommonOps commonOps = new CommonOps();

		Xls_Reader xr = new Xls_Reader(testData);
		int i = Integer.parseInt(Row);

		String orderId = xr.getCellData("Input", "OrderId", i).trim();
		String Weight = xr.getCellData("Input", "Weight", i).trim();
		String DimensionL = xr.getCellData("Input", "DimensionL", i).trim();
		String DimensionW = xr.getCellData("Input", "DimensionW", i).trim();
		String DimensionH = xr.getCellData("Input", "DimensionH", i).trim();
		int totalNumberOfLabels =
				Integer.parseInt(xr.getCellData("Input", "TotalNumberOfLabels", i).trim());

		String fileName = orderId + ".pdf";


		String expectedWeight = Weight + " lbs";
		Log.info("Expected Weights: " + expectedWeight);
		String expectedDimension =
				"L" + DimensionL + " x W" + DimensionW + " x H" + DimensionH + " in";
		Log.info("Expected Dimensions: " + expectedDimension);

		commonOps.validatePDFText(fileName, expectedWeight, 1);
		commonOps.validatePDFText(fileName, expectedDimension, 1);

		expectedWeight = String.valueOf((Integer.parseInt(Weight) - 40) / 5) + " lbs";
		Log.info("Expected Weights: " + expectedWeight);
		expectedDimension = "L" + String.valueOf(Integer.parseInt(DimensionL) / 5) + " x W"
				+ String.valueOf(Integer.parseInt(DimensionW) / 5) + " x H"
				+ String.valueOf((Integer.parseInt(DimensionW) - 5) / 5) + " in";
		Log.info("Expected Dimensions: " + expectedDimension);

		commonOps.validatePDFText(fileName, expectedWeight, 5);
		commonOps.validatePDFText(fileName, expectedDimension, 5);
	}

}
