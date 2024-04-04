package com.qualitesoft.freightclub.testscripts.scrapping;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.qualitesoft.core.InitializeTest;
import com.qualitesoft.core.SeleniumFunction;
import com.qualitesoft.core.WaitTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPopularMovies extends InitializeTest {

    FileWriter outputfile;
    CSVWriter writer;
    String fileName;
    List<String[]> rows;

    @Test
    public void testGetPopularMovies() {

		//click on popularity menu
		SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//a[@href='/popular/']"), 20));

		// wait for some time
		WaitTool.sleep(5);

        //select movies from dropdown
        SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver, By.xpath("//div[@class='relative flex flex-wrap z-10']/div/button"), 20));
        SeleniumFunction.click(WaitTool.waitForElementPresentAndDisplay(driver,By.xpath("(//div[@role='menu'][1]/a[2])[1]"), 20));
        WaitTool.sleep(5);

		// Store data into excel
        downloadData("binaries/MoviesData","popularMovies.csv");
        System.out.println("Popular movies data saved successfully.");
        WaitTool.sleep(5);

        //downloadImages
        downloadImages("binaries/MoviesData","popularMovies.csv");

    }

    public String downloadData(String dir, String fileName) {

        try {

            List<WebElement> popularTitles;
            List<String> imageNames = new ArrayList<>();
            rows = new ArrayList<>();
            String[] row;

            // create FileWriter object with file as parameter
            outputfile = new FileWriter(dir+File.separator+fileName);

            // create CSVWriter object filewriter object as parameter
            writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"ImageName", "ImageUrl", "TitleName", "Tag1","Tag2","Tag3","Tag4","Tag5","Tag6","Tag7","Tag8","Tag9"};
            writer.writeNext(header);

            popularTitles = driver.findElements(By.xpath("//table[@class='card-table']/tbody/tr"));

            for (WebElement popularTitle : popularTitles) {

                row = new String[12];

                //set title name
                row[2] = popularTitle.findElement(By.xpath("./td[2]/descendant::div[@class='group-hover:underline']")).getText();

                //set tags
                List<WebElement> tags = popularTitle.findElements(By.xpath("./td[2]/a/div[2]/div[2]/span"));

                int tagsCounter = 3;
                for (WebElement tag : tags) {
                    if (!tag.getText().equals("|")) {
                        row[tagsCounter] = tag.getText();
                        tagsCounter++;
                    }
                }

                //set image url
                String imageSrc = popularTitle.findElement(By.xpath("./td[2]/descendant::img")).getAttribute("src");
                row[1] = imageSrc.replace("72","350");

                //set imageName
                String imageName = row[2].replaceAll("[^a-zA-Z0-9_-]", "");
                if(imageNames.contains(imageName)) {
                    imageName = imageName + "Duplicate";
                    imageNames.add(imageName);

                } else {
                    imageNames.add(imageName);
                }

                row[0] = imageName;

                rows.add(row);
            }

            writer.writeAll(rows);
            writer.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            try {
                writer = new CSVWriter(outputfile);
                writer.writeAll(rows);
                writer.close();

            } catch (Exception e) {
                System.out.println("Error occurred while writing in file.....");
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public void downloadImages(String dir, String fileName) {


        String line = "";
        int iteration = 0;
        try {

            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(dir+File.separator+fileName);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {

                row[0] = row[0].replaceAll("[^a-zA-Z0-9_-]", "");

                java.net.URL imageUrl = new java.net.URL(row[1]);
                BufferedImage saveImage = ImageIO.read(imageUrl);

                if(row[1].endsWith(".png")) {
                    ImageIO.write(saveImage, "png", new File(dir+ File.separator + row[0] + ".png"));
                    System.out.println("Image "+row[0]+".png downloaded successfully.");
                } else {
                    ImageIO.write(saveImage, "jpg", new File(dir+ File.separator + row[0] + ".jpg"));
                    System.out.println("Image "+row[0]+".jpg downloaded successfully.");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

    }
}
