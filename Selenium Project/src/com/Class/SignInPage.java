package com.Class;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Xls_Reader;


public class SignInPage {
	WebDriver driver;
	//defining xpath according to page factory model
	@FindBy(xpath=".//*[@id='email_create']")
	WebElement NewEmail;	
	@FindBy(xpath=".//*[@id='SubmitCreate']")
	WebElement createaccbutton;
	@FindBy(xpath=".//*[@id='create_account_error']")
	WebElement createaccerror;
	
	DateFormat df = new SimpleDateFormat("mmss"); //Creating date object
	Date dateobj = new Date();
	String FileName = df.format(dateobj);
	
	public SignInPage(WebDriver driver){	//Constructor
		this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public void Insertinavalidemail(){
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+ "\\Test Data.xlsx");
		String Invalidemail=xls.getCellData("Sheet1", 1, 2); //getting data from excel file's 2nd column and 2nd row
		NewEmail.sendKeys(Invalidemail); //putting data from excel file
		createaccbutton.click();
		}
	
	public void Insertregisteredemail(){
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+ "\\Test Data.xlsx");
		String registeredlidemail=xls.getCellData("Sheet1", 1, 3);//getting data from excel file's 2nd column and 3rd row
		NewEmail.sendKeys(registeredlidemail); //putting data from excel file
		createaccbutton.click();
		}
	
	public void GetAttributeNewEmail(){
		String FontColor=(NewEmail.getCssValue("color"));//getting the color value
			Assert.assertTrue(FontColor.contains("241"));//comparing the value with rgb 241 which is red
		}
	public void geterrormessage(){
		String errorMsg=createaccerror.getText();
		Assert.assertEquals(errorMsg, "An account using this email address has already been registered. Please enter a valid password or request a new one.");
		}	//comparing error message with provided error message
	
	public void insertnewemail(){
		NewEmail.sendKeys("noman"+FileName+"@gmail.com"); //putting random new email
		createaccbutton.click();
	}
	}

	

