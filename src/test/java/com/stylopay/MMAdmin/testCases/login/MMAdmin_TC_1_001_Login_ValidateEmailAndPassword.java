package com.stylopay.MMAdmin.testCases.login;

import java.io.IOException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_1_001_Login_ValidateEmailAndPassword extends BaseClass {

	@Test(groups = {"login", "regression"})
	public void MMAdmin_TC_1_001() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_1_001");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_001", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_001", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.clickLoginBtn();

			if (loginpage.getEmailValidationErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_001", "Email Validation Error"))) {

				logger.info("Proper validation message is displaying for keeping Email Field empty");
				Reporting.test.log(LogStatus.PASS, "Check Login Email Validation",
						"Proper validation message is displaying for keeping Email Field empty");

			} else {

				logger.error("Proper validation message is not displaying for keeping Email Field empty");
				Reporting.test.log(LogStatus.FAIL, "Check Login Email Validation",
						"Proper validation message is not displaying for keeping Email Field empty"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_001")));

			}

			if (loginpage.getPwdValidationErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_001", "Password Validation Error"))) {

				logger.info("Proper validation message is displaying for keeping Password Field empty");
				Reporting.test.log(LogStatus.PASS, "Check Login Password Validation",
						"Proper validation message is displaying for keeping Password Field empty");

			} else {

				logger.error("Proper validation message is not displaying for keeping Password Field empty");
				Reporting.test.log(LogStatus.FAIL, "Check Login Password Validation",
						"Proper validation message is not displaying for keeping Password Field empty"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_001")));
				

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
