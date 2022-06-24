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

public class MMAdmin_TC_2_006_ResetPwd_OTPFieldValidationWithInvalidCharacters extends BaseClass {

	@Test(groups = { "forgotPwd" })
	public void MMAdmin_TC_2_006() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_006");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_006", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);
			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_006", "OTP"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (ReadExpectedValidationAndSuccessMsgFromXL
						.getTestReportDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_006", "OTP Type")
						.equals(resetPwd.getOTPInputFieldType())) {

					logger.info("OTP field does not allow any other characters rather than numbers");
					Reporting.test.log(LogStatus.PASS,
							"Check whether OTP fields allowes alphabetic and special characters",
							"OTP field does not allow any other characters rather than numbers");

				} else {

					logger.error("OTP field allows any other characters rather than numbers");
					Reporting.test.log(LogStatus.FAIL,
							"Check whether OTP fields allowes alphabetic and special characters",
							"OTP field allows any other characters rather than numbers"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_006")));
				}

			} catch (StaleElementReferenceException e) {
				
				logger.error("Error message: OTP field must not allow other characters rather than number");
				Reporting.test.log(LogStatus.FAIL,
						"Check whether OTP fields allowes alphabetic and special characters",
						"Error message: OTP field must not allow other characters rather than number"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_006")));

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
