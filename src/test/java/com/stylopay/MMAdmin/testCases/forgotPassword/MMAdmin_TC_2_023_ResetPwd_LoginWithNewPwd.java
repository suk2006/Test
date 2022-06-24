package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_2_023_ResetPwd_LoginWithNewPwd extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression"})
	public void MMAdmin_TC_2_023() throws IOException {

		Reporting.startTest("MMAdmin_TC_2_023");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_023", "Email");
			String password = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_023", "New Password");

			loginpage.setUserName(email);
			logger.info("Enter email");
			
			loginpage.setPassword(password);
			logger.info("Enter newly changed password");
			
			loginpage.pressEnterAtLoginBtn();
			logger.info("Click on Login button");

			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_023", "Dashboard URL")
					.equalsIgnoreCase(driver.getCurrentUrl())) {

				logger.info("Dashboard page is opening after login with newly changed password");
				Reporting.test.log(LogStatus.PASS, "Check Login with correct email and newly changed password",
						"Dashboard page is opening after login with newly changed password");

			} else {

				logger.error("Dashboard page is not opening after login with newly changed password");
				Reporting.test.log(LogStatus.FAIL, "Check Login with correct email and newly changed password",
						"Dashboard page is not opening after login with newly changed password"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_023")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}
}
