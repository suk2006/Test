package com.stylopay.MMAdmin.web;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitFunctions {

	public static WebElement FluentWait(WebDriver driver, final WebElement fieldElement){
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(30L))
			       .pollingEvery(Duration.ofSeconds(2L))
			       .ignoring(NoSuchElementException.class);

			   WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       WebElement waitElement = fieldElement;
			       
			       if(waitElement != null){
			    	   
			    	   System.out.println("Element Found");
			    	   
			       }
			       
			       return waitElement;
					   
			     }
			   });
			   
		   return element;
	}
	
	public static void explicitWaitForElementVisibility(WebDriver driver, WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void explicitWaitForElementInvisibility(WebDriver driver, WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public static void explicitWaitForElementToBeClickable(WebDriver driver, WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static void explicitWaitForElementToBeSElected(WebDriver driver, WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}
	
	public static void threadWait() throws InterruptedException{
		
		Thread.sleep(500);
	}
	
	public static void recaptchaThreadWait() throws InterruptedException{
		
		Thread.sleep(30000);
	}

}
