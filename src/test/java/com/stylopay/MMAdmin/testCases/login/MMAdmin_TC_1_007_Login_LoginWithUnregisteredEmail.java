package com.stylopay.MMAdmin.testCases.login;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_1_007_Login_LoginWithUnregisteredEmail extends BaseClass {

	@Test(groups = {"login", "sanity", "regression"})
	public void MMAdmin_TC_1_007() throws IOException {

		Reporting.startTest("MMAdmin_TC_1_007");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_007", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_007", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.clickLoginBtn();
			WaitFunctions.threadWait();

			if (loginpage.getInvalidLoginCredentialErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_007", "Invalid Login Credential Err"))) {

				logger.info("Proper validation message is displaying for entering unregistered email at Email Field");
				Reporting.test.log(LogStatus.PASS, "Check unregistered email validation",
						"Proper validation message is displaying for entering unregistered email at Email Field");

			} else {

				logger.error(
						"Proper validation message is not displaying for entering unregistered email at Email Field");
				Reporting.test.log(LogStatus.FAIL, "Check unregistered email validation",
						"Proper validation message is not displaying for entering unregistered email at Email Field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_007")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
