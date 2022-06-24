package com.stylopay.MMAdmin.pageObjects;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stylopay.MMAdmin.web.WaitFunctions;

public class ResetPassword {

	WebDriver driver;
	JavascriptExecutor js;
	ArrayList<String> tabs;
	String[] splitEmail;
	String userName;
	String otpmessage;
	String OTP;

	String passwordPattern;
	Pattern pattern;
	Matcher matcher;

	WebDriverWait wait;

	public ResetPassword(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(how = How.NAME, using = "mfaCode")
	@CacheLookup
	WebElement otpfield;

	@FindBy(how = How.ID, using = "psw")
	@CacheLookup
	WebElement newPassword;

	@FindBy(how = How.ID, using = "confirm psw")
	@CacheLookup
	WebElement confirmPassword;

	@FindBy(xpath = "//button[@class='login100-form-btn']")
	@CacheLookup
	WebElement submitBtn;

	@FindBy(xpath = "//a[@class='login100-form-btn']")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//form[starts-with(@class, 'login100')]/descendant::h1")
	@CacheLookup
	WebElement invalidOTPValidationErr;

	@FindBy(id = "login")
	@CacheLookup
	WebElement yopmailEnterEmailField;

	@FindBy(xpath = "//i[@class='material-icons-outlined f36']")
	@CacheLookup
	WebElement yopmailEnterUserAccBtn;

	@FindBy(id = "refresh")
	@CacheLookup
	WebElement yopmailUserAccRefreshBtn;

	@FindBy(name = "ifinbox")
	@CacheLookup
	WebElement yopmailEmailInboxFrame;

	@FindBy(xpath = "//div[@class='mctn']/child::div[2]/button/div/span[2]")
	@CacheLookup
	WebElement userAccLatestEmail;

	@FindBy(id = "ifmail")
	@CacheLookup
	WebElement yopmailReadEmailPageFrame;

	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	@CacheLookup
	WebElement recaptchaFrame;

	@FindBy(xpath = "//span[@id='recaptcha-anchor']/div")
	@CacheLookup
	WebElement recaptchaBox;

	@FindBy(xpath = "//div[@id='mail']/div")
	@CacheLookup
	WebElement yopmailOTPMsg;

	@FindBy(xpath = "//h1[@style='color:green; text-align:center;']")
	@CacheLookup
	WebElement resetPwdSuccessMsg;
	
	@FindBy(id="message2")
	@CacheLookup
	WebElement unmatchedCnfrmPwdErrMsg;
	
	@FindBy(xpath="//form[starts-with(@class, 'login100')]/h1")
	@CacheLookup
	WebElement loginWithExpiredPwdValidationErr;


	public void enterOTP(String otp) {

		WaitFunctions.FluentWait(driver, otpfield);
		otpfield.clear();
		otpfield.sendKeys(otp);
	}

	public void enterNewPwd(String newPwd) {

		newPassword.clear();
		newPassword.sendKeys(newPwd);
	}

	public void enterConfirmPwd(String confirmPwd) {

		confirmPassword.clear();
		confirmPassword.sendKeys(confirmPwd);
	}

	public void clkSubmitBtn() {

		submitBtn.click();
	}

	public void clkLoginBtn() {

		loginBtn.click();
	}

	public String getOTPInputFieldType() {

		return otpfield.getAttribute("type");
	}

	public boolean checkOTPHTMLValidation() {

		js = (JavascriptExecutor) driver;
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", otpfield);

		return isRequired;
	}

	public String getInvalidOTPValidationErr() {

		return invalidOTPValidationErr.getText();
	}

	public boolean checkNewPasswordHTMLValidation() {

		js = (JavascriptExecutor) driver;
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", newPassword);

		return isRequired;
	}

	public boolean validatePasswordFormat(String password) {

		passwordPattern = newPassword.getAttribute("pattern");
		pattern = Pattern.compile(passwordPattern);
		matcher = pattern.matcher(password);

		return matcher.matches();
	}

	public String getPwdFormatValidationErrMsg() {

		return newPassword.getAttribute("title");
	}

	public String getOTPFromUserEmail(String newTabURL, String userEmail) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.open()");
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(newTabURL);
		WaitFunctions.threadWait();

		splitEmail = userEmail.split("@");
		userName = splitEmail[0];

		yopmailEnterEmailField.clear();
		yopmailEnterEmailField.sendKeys(userName);
		System.out.println("subString is: " + userName);
		WaitFunctions.threadWait();
		yopmailEnterUserAccBtn.click();
		WaitFunctions.threadWait();

		yopmailUserAccRefreshBtn.click();
		WaitFunctions.threadWait();

		driver.switchTo().frame(yopmailEmailInboxFrame);
		WaitFunctions.threadWait();

		System.out.println("latest email: " + userAccLatestEmail.getText());
		userAccLatestEmail.click();
		WaitFunctions.threadWait();

		driver.switchTo().defaultContent();
		WaitFunctions.threadWait();

		driver.switchTo().frame(yopmailReadEmailPageFrame);
		WaitFunctions.threadWait();

		wait = new WebDriverWait(driver, 120);

		try {

			if (recaptchaFrame.isDisplayed()) {

				System.out.println("recaptcha box is displaying");
				driver.switchTo().frame(recaptchaFrame);
				WaitFunctions.recaptchaThreadWait();

				driver.switchTo().frame(yopmailReadEmailPageFrame);

				otpmessage = yopmailOTPMsg.getText();
				System.out.println("otpmessage: " + otpmessage);

				OTP = otpmessage.replaceAll("[^0-9]", "");

				System.out.println("OTP is: " + OTP);

			}

		} catch (NoSuchElementException e) {			

			otpmessage = yopmailOTPMsg.getText();
			System.out.println("otpmessage: " + otpmessage);

			OTP = otpmessage.replaceAll("[^0-9]", "");

			System.out.println("OTP is: " + OTP);
		}

		driver.switchTo().window(tabs.get(0));
		WaitFunctions.threadWait();

		return OTP;
	}

	public String getResetPwdSuccessMsg() {

		return resetPwdSuccessMsg.getText();
	}
	
	public String getUnmatchedCnfrmPwdErrMsg(){
		
		return unmatchedCnfrmPwdErrMsg.getText();
	}
	
	public String getNewPwdType(){
		
		return newPassword.getAttribute("type");
	}
	
	public String getCnfrmPwdType(){
		
		return confirmPassword.getAttribute("type");
	}
	
	public String getLoginWithExpiredPwdValidationErr(){
		
		return loginWithExpiredPwdValidationErr.getText();
	}	
	
}
