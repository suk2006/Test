package com.stylopay.MMAdmin.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	WebDriver driver;
	
	public DashboardPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	
	@FindBy(xpath="//a[contains(text(),'Dashboard')]")
	@CacheLookup
	WebElement dashboardMenu;
	
	@FindBy(xpath="//section[@class='logo']/a")
	@CacheLookup
	WebElement userAccLogo;
	
	@FindBy(id="usd")
	@CacheLookup
	WebElement dshbrdWalletBal;
	
	public void clkDashboardMenu(){
		
		dashboardMenu.click();
	}
	
	public void clkUserAccLogo(){
		
		userAccLogo.click();
	}
	
	public String invokeDshbrdWalletBal(){
		
		String usdBalString = dshbrdWalletBal.getAttribute("value");
		String usdBalance = usdBalString.replaceAll("[^0-9.]", "");
		return usdBalance;
	}

}
