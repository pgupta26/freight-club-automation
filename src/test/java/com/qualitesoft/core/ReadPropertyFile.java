package com.qualitesoft.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadPropertyFile {

	Properties pro;
	public ReadPropertyFile() {
		File src=new File("./binaries/CGFiles/cgdata.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		}
		catch(Exception e) {
			Log.info("Exception is " + e.getMessage());
		}	
	}
	
	public String getEmailValidation() {
		String email_validation=pro.getProperty("emailValidation");
		Log.info(email_validation);
		return email_validation;
	}
	
	public String getPasswordValidation() {
		String password_validation=pro.getProperty("passwordValidation");
		Log.info(password_validation);
		return password_validation;
	}
	
	public String getCPasswordValidation() {
		String cpassword_validation=pro.getProperty("cpasswordValidation");
		Log.info(cpassword_validation);
		return cpassword_validation;
	}
	
	public String getInvalidEmailValidation() {
		String invalidEmail_validation=pro.getProperty("invalidEmail");
		Log.info(invalidEmail_validation);
		return invalidEmail_validation;
	}
	
	public String getInvalidCredentialsValidation() {
		String incorrectCredentials_validation=pro.getProperty("incorrectCredentials");
		Log.info(incorrectCredentials_validation);
		return incorrectCredentials_validation;
	}
	
	public String getForgotPasswordValidation1() {
		String forgotPassword_validation=pro.getProperty("forgotEmailValidation1");
		Log.info(forgotPassword_validation);
		return forgotPassword_validation;
	}
	
	public String getForgotPasswordValidation2() {
		String forgotPassword_validation=pro.getProperty("forgotEmailValidation2");
		Log.info(forgotPassword_validation);
		return forgotPassword_validation;
	}
	
	public String getForgotPasswordSuccess() {
		String forgotPassword_message=pro.getProperty("fpSucessMessage");
		Log.info(forgotPassword_message);
		return forgotPassword_message;
	}
	
	public String getPasswordLimitValidation1() {
		String passwordLimit_Validation1=pro.getProperty("passwordLimitValidation1");
		Log.info(passwordLimit_Validation1);
		return passwordLimit_Validation1;
	}
	
	public String getPasswordLimitValidation2() {
		String passwordLimit_Validation2=pro.getProperty("passwordLimitValidation2");
		Log.info(passwordLimit_Validation2);
		return passwordLimit_Validation2;
	}
	
	public String getPasswordLimitValidation3() {
		String passwordLimit_Validation3=pro.getProperty("passwordLimitValidation3");
		Log.info(passwordLimit_Validation3);
		return passwordLimit_Validation3;
	}
	
	public String getCpasswordMismatch() {
		String cpassword_Validation=pro.getProperty("cpasswordLimitValidation");
		Log.info(cpassword_Validation);
		return cpassword_Validation;
	}
}
