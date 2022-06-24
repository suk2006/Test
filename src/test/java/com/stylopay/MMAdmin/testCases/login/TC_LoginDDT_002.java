package com.stylopay.MMAdmin.testCases.login;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.stylopay.MMAdmin.genericUtilities.XLUtils;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		
		lp.clickLoginBtn();
		Thread.sleep(2000);
		
		if(isValidationErrorPresent()==true){
			
//			driver.get(baseURL);
//			Thread.sleep(2000);
			Assert.assertTrue(false);
			
		}else{
			
			lp.clickLogoutBtn();
//			driver.get(baseURL);
//			Thread.sleep(2000);
			Assert.assertTrue(true);		
		}
	}
	
	public boolean isValidationErrorPresent(){
		
		boolean flag = false;
		
		try{
			WebElement validationError = driver.findElement(By.xpath("//h1[contains(text(), 'Incorrect Password')]"));
			if(validationError.isDisplayed())	
				
				flag = true;			
		
		}catch(Exception e){
			
			flag = false;
		}
		
		return flag;
	}

	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		
		String path = "./TestData/TestData.xls";
		
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		System.out.println("rowCount: " + rowCount);
		System.out.println("colCount: " + colCount);
		
		String loginData[][] = new String[rowCount][colCount];
		
		for(int i=1; i<rowCount; i++){
			
			for(int j=0; j<colCount; j++){				
				
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);			
				
			}
		}
		
		return loginData;
	}
}
