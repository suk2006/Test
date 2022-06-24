package com.stylopay.MMAdmin.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.stylopay.MMAdmin.genericUtilities.ReadConfig;
import com.stylopay.MMAdmin.genericUtilities.Reporting;

@Listeners(Reporting.class)
public class BaseClass{

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationURL();
	public String loginUserName = readconfig.getLoginUserName();
	public String loginPassword = readconfig.getLoginPassword();

	public static WebDriver driver;
	public static Logger logger;
	public static SoftAssert softAssertion;

	@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void startBrowser(String browser) {

		if (browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
			driver = new FirefoxDriver();

		} else if (browser.equals("edge")) {

			System.setProperty("webdriver.edge.driver", readconfig.getEdgepath());
			driver = new EdgeDriver();
		}

		driver.get(baseURL);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		logger  = Logger.getLogger("MMADminPortalLogs");
		PropertyConfigurator.configure("log4j.properties");
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {

		driver.quit();
	}

	public static String captureScreen(WebDriver driver, String testCaseName) throws IOException {

		String dateName = new SimpleDateFormat("yyyy-mm-dd").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Report/FailedTestScreenshots/" + testCaseName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	public String randomString(){
		
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}
	
	public static String randomNum(){
		
		String generatedString = RandomStringUtils.randomNumeric(4);
		return generatedString;
	}

}
