package com.Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {
	WebDriver driver;
	//defining xpath according to page factory model
	@FindBy(xpath=".//*[@id='id_gender1']")
	WebElement Prefix;
	@FindBy(xpath=".//*[@id='customer_firstname']")
	WebElement FirstName;
	@FindBy(xpath=".//*[@id='customer_lastname']")
	WebElement LastName;
	@FindBy(xpath=".//*[@id='email']")
	WebElement Email;
	@FindBy(xpath=".//*[@id='passwd']")
	WebElement Password;
	@FindBy(xpath=".//*[@id='days']")
	WebElement Day;
	@FindBy(xpath=".//*[@id='months']")
	WebElement Month;
	@FindBy(xpath=".//*[@id='years']")
	WebElement Year;
	@FindBy(xpath=".//*[@id='submitAccount']") 
	WebElement Register;
	@FindBy(xpath=".//*[@id='center_column']/p[1]")
	WebElement NewAccConfirmation;
	
	
	DateFormat df = new SimpleDateFormat("HH");
	Date dateobj = new Date();
	String FileName = df.format(dateobj); //creating dateobject to put in password along with name
	
	
	
public  CreateAccountPage(WebDriver driver){ //constructor
	this.driver=driver;
	PageFactory.initElements(driver, this);
}	
	
public void Register(){
	String alphabet= "abcdefghijklmnopqrstuvwxyz";
    String s = "";
    Random random = new Random();
    int randomLen = 2+random.nextInt(3); //creating 2 or 3 random letters to add after names
    for (int i = 0; i < randomLen; i++) {
        char c = alphabet.charAt(random.nextInt(26));
        s+=c;
    }
     	
	try{
	Prefix.click();
	FirstName.sendKeys("Abdullah"+s); 		//putting first name
	LastName.sendKeys("Noman"+s);			//putting last name
	Password.sendKeys("Nomanium"+FileName); //putting password
	Select dropdown1 = new Select(Day); 	
	dropdown1.selectByValue("10");			//selecting date 10
	Select dropdown2 = new Select(Month);
	dropdown2.selectByIndex(4);				//selecting month by index which is April
	Select dropdown3 = new Select(Year);
	dropdown3.selectByValue("1989");		//selecting year
	Register.click();
	}catch(Exception e){
	}}
	
public void confirmation(){
	String Actual=NewAccConfirmation.getText();	//getting confirmation message
	Assert.assertEquals("Your account has been created.", Actual);	//comparing message with predefined message.
	
}	
}	



