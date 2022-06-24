package com.stylopay.MMAdmin.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.stylopay.MMAdmin.web.WaitFunctions;

public class LoginPage {

	WebDriver driver;
	WebElement fieldElement;
	String message;
	String passwordType;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(id = "email")
	@CacheLookup
	WebElement userName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	@CacheLookup
	WebElement logoutBtn;

	@FindBy(xpath = "//div[contains(@data-validate, 'Please enter valid email ID')]")
	@CacheLookup
	WebElement emailValidationMsg;

	@FindBy(xpath = "//div[contains(@data-validate, 'Enter password')]")
	@CacheLookup
	WebElement pwdValidationMsg;

	@FindBy(xpath = "//div[@class='wrap-login100']/descendant::h1")
	@CacheLookup
	WebElement invalidLoginCredentialErr;	
	

	public void setUserName(String uname) {

		userName.clear();
		userName.sendKeys(uname);
	}

	public void setPassword(String pwd) {

		password.clear();
		password.sendKeys(pwd);
	}

	public void clickLoginBtn() {

		loginBtn.click();
	}

	public void clickLogoutBtn() {

		logoutBtn.click();
	}

	public String getEmailValidationErr() throws InterruptedException {

		new Actions(driver).moveToElement(emailValidationMsg).perform();
		WaitFunctions.threadWait();
		return emailValidationMsg.getAttribute("data-validate");
	}

	public String getPwdValidationErr() throws InterruptedException {

		new Actions(driver).moveToElement(pwdValidationMsg).perform();
		WaitFunctions.threadWait();
		return pwdValidationMsg.getAttribute("data-validate");
	}

	public String getInvalidLoginCredentialErr() {		
	
		fieldElement = WaitFunctions.FluentWait(driver, invalidLoginCredentialErr);
				
			if(fieldElement.isDisplayed()){
				
				message = invalidLoginCredentialErr.getText();
			}else{
				
				message = "";
			}
		
		
		return message;
	}
	
	public String getPasswordType(){
		
		passwordType = password.getAttribute("type");
		return passwordType;
	}
	
	public void pressEnterAtLoginBtn(){
		
		loginBtn.sendKeys(Keys.ENTER);
	}

}
