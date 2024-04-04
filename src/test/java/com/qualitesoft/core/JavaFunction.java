package com.qualitesoft.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.lang3.RandomStringUtils;

public class JavaFunction {

	public static String randomText(String name, int count) {

		String text = name + RandomStringUtils.randomAlphabetic(count);	
		return text;
	}

	public static int getRandomNumber(int min, int max) {
		return (min + (int) (Math.random() * ((max - min) + 1)));
	}

	public static String emailAddress() {

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String emailAddress = "Selenium_" + dateFormat.format(date).replaceAll("\\s", "_") + "@email.com";
		return emailAddress;
	}

	public static String currentDate(){
		Date myDate=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		String currentDate=dateFormat.format(myDate);
		return currentDate;
	}
	
	public static String currentPSTDate(String format) {
		Date date = new Date(); // current date time
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		String currentDate=dateFormat.format(date);
		return currentDate;
	}

	public static String currentDateUSFormat(){
		Date myDate=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
		String currentDate=dateFormat.format(myDate);
		return currentDate;
	}

	public static String currentDateFormat(String format){
		Date myDate=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		String currentDate=dateFormat.format(myDate);
		return currentDate;
	}

	public static URI appendUri(String uri, String appendQuery) {

		try{URI oldUri = new URI(uri);

		String newQuery = oldUri.getQuery();
		if (newQuery == null) {
			newQuery = appendQuery;
		} else {
			newQuery += "&" + appendQuery;  
		}

		URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
				oldUri.getPath(), newQuery, oldUri.getFragment());
		Log.info(newUri.toString());   
		return newUri;

		}catch(Exception e)
		{
			Log.error(e.getMessage());
			return null;
		}

	}

	public static int countLineBufferedReader(String fileName) throws IOException {

		int lines = 0;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		while (reader.readLine() != null) 
			lines++;
		reader.close();
		return lines;
	}
}
