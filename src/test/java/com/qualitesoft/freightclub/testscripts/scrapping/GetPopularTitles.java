package com.qualitesoft.freightclub.testscripts.scrapping;

import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.qualitesoft.core.InitializeTest;

public class GetPopularTitles extends InitializeTest {

	@Test
	public void testGetTitles() {

		GetPopularMovies getPopularMovies = new GetPopularMovies();

		//click on popularity menu
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/popular/']"), 20));

		// wait for some time
		WaitTool.sleep(5);

		// Store data into excel
		getPopularMovies.downloadData("binaries/TitlesData", "TitlesData.csv");

		//downloadImages
		getPopularMovies.downloadImages("binaries/TitlesData","TitlesData.csv");

	}
}
