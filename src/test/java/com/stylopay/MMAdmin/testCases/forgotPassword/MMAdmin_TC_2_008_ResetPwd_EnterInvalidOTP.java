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

public class MMAdmin_TC_2_008_ResetPwd_EnterInvalidOTP extends BaseClass {

	@Test(groups = { "forgotPwd" })
	public void MMAdmin_TC_2_008() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_008");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_008", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_008", "OTP"));
			resetPwd.enterNewPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_008", "New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_008",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ResetPassword", "Sheet1",
						"MMAdmin_TC_2_008", "Invalid OTP Validation Msg")
						.equals(resetPwd.getInvalidOTPValidationErr())) {

					logger.info("Validation message is displaying for entering invalid OTP at OTP field");
					Reporting.test.log(LogStatus.PASS, "OTP field validation with invalid OTP",
							"Validation message is displaying for entering invalid OTP at OTP field");

				} else {

					logger.error("Validation message is not displaying for entering invalid OTP at OTP field");
					Reporting.test.log(LogStatus.FAIL, "OTP field validation with invalid OTP",
							"Validation message is not displaying for entering invalid OTP at OTP field"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_008")));
				}

			} catch (StaleElementReferenceException e) {

				logger.error("Error message: Invalid OTP validation is not working");
				Reporting.test.log(LogStatus.FAIL, "OTP field validation with invalid OTP",
						"Error message: Invalid OTP validation is not working"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_008")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
