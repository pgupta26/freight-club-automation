package com.qualitesoft.freightclub.testscripts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.qualitesoft.core.JavaFunction;
import com.qualitesoft.core.Log;
import com.qualitesoft.core.Xls_Reader;

public class Test {

	public static void main(String[] args) throws IOException {

		String testData = "testdata/FCfiles/qa/QuickQuote/QuickQuoteTestData.xlsx";
		
		Xls_Reader xr;
		xr=new Xls_Reader(testData);
		
		xr.setCellData("Input", "OrderId", 2, "123456");
		xr.setCellData("Input", "pickUpDate", 2, "234");
		

	}

}
