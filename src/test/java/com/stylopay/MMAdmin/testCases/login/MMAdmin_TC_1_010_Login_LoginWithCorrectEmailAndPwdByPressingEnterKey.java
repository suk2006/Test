package com.stylopay.MMAdmin.testCases.login;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_1_010_Login_LoginWithCorrectEmailAndPwdByPressingEnterKey extends BaseClass {

	@Test(groups = { "login", "regression"})
	public void MMAdmin_TC_010() throws IOException {

		Reporting.startTest("MMAdmin_TC_1_010");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_010", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_010", "password");

			loginpage.setUserName(email);
			logger.info("Enter email");
			
			loginpage.setPassword(password);
			logger.info("Enter password");
			
			loginpage.pressEnterAtLoginBtn();
			logger.info("Click on Login button");

			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_010", "Dashboard URL")
					.equalsIgnoreCase(driver.getCurrentUrl())) {

				logger.info("Dashboard page is opening after successful login");
				Reporting.test.log(LogStatus.PASS, "Check Login with correct email and password",
						"Dashboard page is opening after successful login");

			} else {

				logger.error("Dashboard page is not opening after login");
				Reporting.test.log(LogStatus.FAIL, "Check Login with correct email and password",
						"Dashboard page is not opening after login"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_010")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
