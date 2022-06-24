package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.ForgotPasswordPage;
import com.stylopay.MMAdmin.pageObjects.ResetPassword;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_2_018_ResetPwd_EnterProperPwdButWrongOTP extends BaseClass {

	@Test(groups = { "forgotPwd", "sanity" })
	public void MMAdmin_TC_2_018() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_018");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_018", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_018", "OTP"));
			resetPwd.enterNewPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_018",
					"New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_018",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("resetPassword", "Sheet1",
						"MMAdmin_TC_2_018", "Invalid OTP Validation Msg")
						.equals(resetPwd.getInvalidOTPValidationErr())) {

					logger.info("Wrong OTP Validation is working");
					Reporting.test.log(LogStatus.PASS, "Enter wrong OTP but proper password",
							"Wrong OTP Validation is working");

				} else {

					logger.error("Wrong OTP Validation is not working");
					Reporting.test.log(LogStatus.FAIL, "Enter wrong OTP but proper password",
							"Wrong OTP Validation is not working"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_018")));
				}

			} catch (StaleElementReferenceException e) {

				logger.error(
						"Error message: Password is reseting instead of entering wrong OTP");
				Reporting.test.log(LogStatus.FAIL, "Enter wrong OTP but proper password",
						"Error message: Password is reseting instead of entering wrong OTP"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_018")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
