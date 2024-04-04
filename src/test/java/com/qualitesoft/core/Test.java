package com.qualitesoft.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
    public static void main(String[] args) throws IOException {

        String path = "testdata/FCfiles/qa/CustomOrder/QA_537.xlsx";

        Xls_Reader xr = new Xls_Reader(path);

		// try {
		// 	File fp = new File(path);
			
		// 	FileInputStream fpis = new FileInputStream(fp);
		
		// } catch (Exception e) {

		// 	e.printStackTrace();
		// }

    }
}
