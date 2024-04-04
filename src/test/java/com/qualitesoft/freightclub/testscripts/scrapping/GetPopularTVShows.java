package com.qualitesoft.freightclub.testscripts.scrapping;

import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GetPopularTVShows extends InitializeTest {

    @Test
    public void testPopularTVShows() {

        GetPopularMovies getPopularMovies = new GetPopularMovies();

        //click on popularity menu
        SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/popular/']"), 20));

        // wait for some time
        WaitTool.sleep(5);

        //select movies from dropdown
        SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='relative flex flex-wrap z-10']/div/button"), 20));
        SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,By.xpath("(//div[@role='menu'][1]/a[3])[1]"), 20));
        WaitTool.sleep(5);

        // Store data into excel
        getPopularMovies.downloadData("binaries/TVShowsData","popularTVShows.csv");
        System.out.println("Popular movies data saved successfully.");
        WaitTool.sleep(5);

        //downloadImages
        getPopularMovies.downloadImages("binaries/TVShowsData","popularTVShows.csv");

    }
}
