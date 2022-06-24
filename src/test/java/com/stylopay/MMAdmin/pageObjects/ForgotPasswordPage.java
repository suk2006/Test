package com.stylopay.MMAdmin.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public ForgotPasswordPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	@FindBy(how = How.XPATH, using="//button[contains(text(),'Forgot Password?')]")
	@CacheLookup
	WebElement forgotPwd;
	
	@FindBy(xpath = "//button[contains(text(), 'Submit')]")
	@CacheLookup
	WebElement submitBtn;
	
	@FindBy(how = How.XPATH, using="//input[@type='text']")
	@CacheLookup
	WebElement enterEmailTxtBox;
	
	@FindBy(xpath="//div[@class='wrap-login100-form-btn']/child::a")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(xpath="//form[starts-with(@class, 'login100')]/child::h1")
	@CacheLookup
	WebElement invalidEmailValidation;
	
	public void clkForgotPwdBtn(){
		
		forgotPwd.click();
	}
	
	
	public void clkSubmitBtn(){
		
		submitBtn.click();
	}
	
	
	public void enterEmail(String email){
		
		enterEmailTxtBox.clear();
		enterEmailTxtBox.sendKeys(email);;
	}
	
	
	public void clkLoginBtn(){
		
		loginBtn.click();
	}
	
	public boolean checkEmailHTMLValidation(){
		
		js = (JavascriptExecutor) driver;
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", enterEmailTxtBox);
		
		return isRequired;
	}
	
	public String getInvalidEmailValidationMsg(){
		
		System.out.println("Error message is: " + invalidEmailValidation.getText());
		return invalidEmailValidation.getText();
	}

}
