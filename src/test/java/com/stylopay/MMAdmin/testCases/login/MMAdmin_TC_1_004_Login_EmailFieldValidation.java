package com.stylopay.MMAdmin.testCases.login;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_1_004_Login_EmailFieldValidation extends BaseClass {

	@Test(groups = {"login", "regression"})
	public void MMAdmin_TC_1_004() {

		Reporting.startTest("MMAdmin_TC_1_004");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_004", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_004", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.clickLoginBtn();			

			if (loginpage.getEmailValidationErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_004", "Email Validation Error"))) {

				logger.info("Proper validation message is displaying for keeping Email Field empty");
				Reporting.test.log(LogStatus.PASS, "Check Login Email Validation",
						"Proper validation message is displaying for keeping Email Field empty");

			} else {

				logger.error("Proper validation message is not displaying for keeping Email Field empty");
				Reporting.test.log(LogStatus.FAIL, "Check Login Email Validation",
						"Proper validation message is not displaying for keeping Email Field empty"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_004")));
				
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
