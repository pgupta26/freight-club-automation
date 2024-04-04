package com.qualitesoft.core;

import java.util.List;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class Main {

	public static void main(String[] args) {
		try {
			TestNG testng = new TestNG();
			List<String> suites = Lists.newArrayList();
			suites.add("xml-config-files\\freightclub\\demo2.xml");
			testng.setTestSuites(suites);
			testng.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
