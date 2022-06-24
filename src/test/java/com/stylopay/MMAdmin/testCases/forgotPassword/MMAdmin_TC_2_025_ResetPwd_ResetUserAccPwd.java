package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.ForgotPasswordPage;
import com.stylopay.MMAdmin.pageObjects.ResetPassword;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_2_025_ResetPwd_ResetUserAccPwd extends BaseClass {

	String email = "";
	String emailAccURL = "";
	String OTP = "";

	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_025() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_025");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_025", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			email = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_025", "Email");
			emailAccURL = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_025",
					"Email acc URL");

			OTP = resetPwd.getOTPFromUserEmail(emailAccURL, email);

			resetPwd.enterOTP(OTP);
			resetPwd.enterNewPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_025", "New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_025",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (ReadExpectedValidationAndSuccessMsgFromXL
						.getTestReportDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_025", "Login Page URL")
						.equals(driver.getCurrentUrl())) {

					logger.info(
							"Password is reseting successfully for User Account and page is successfully redirecting to Login page after reseting User Account password");
					Reporting.test.log(LogStatus.PASS, "Reset User Account Password",
							"Password is reseting successfully for User Account and page is successfully redirecting to Login page after reseting User Account password");

				} else {

					logger.error("Password is not reseting for User Account and page is not redirecting to Login page");
					Reporting.test.log(LogStatus.FAIL, "Reset User Account Password",
							"Password for User Account is not reseting"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_025")));
				}

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ResetPassword", "Sheet1",
						"MMAdmin_TC_2_025", "Reset Pwd Success Msg").equals(resetPwd.getResetPwdSuccessMsg())) {

					logger.info(
							"Proper success message is displaying at Login page after reseting password for User Account");
					Reporting.test.log(LogStatus.PASS, "Check Reset Password for User Account Success Message",
							"Proper success message is displaying at Login page after reseting password for User Account");
				} else {

					logger.error(
							"Proper success message is not displaying at Login page after reseting password for User Account");
					Reporting.test.log(LogStatus.FAIL, "Check Reset Password for User Account Success Message",
							"Proper success message is not displaying at Login page after reseting password for User Account"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_025")));
				}

			} catch (Exception e) {

				Reporting.test.log(LogStatus.FAIL, e.getMessage());

				logger.error("Error message: Password reseting feature is not working");
				Reporting.test.log(LogStatus.FAIL, "Check Reset Password feature with proper data",
						"Error message: Password reseting feature is not working"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_025")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
