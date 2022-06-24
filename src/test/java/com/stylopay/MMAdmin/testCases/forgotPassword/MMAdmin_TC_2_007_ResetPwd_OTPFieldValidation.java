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

public class MMAdmin_TC_2_007_ResetPwd_OTPFieldValidation extends BaseClass {

	@Test(groups = { "forgotPwd" })
	public void MMAdmin_TC_2_007() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_007");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_007", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_007", "OTP"));
			resetPwd.enterNewPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_007", "New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_007",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (resetPwd.checkOTPHTMLValidation() == true) {

					logger.info("HTML validation message is displaying for keeping OTP field empty");
					Reporting.test.log(LogStatus.PASS, "Check OTP field validation",
							"HTML validation message is displaying for keeping OTP field empty");

				} else {

					logger.error("HTML validation message is not displaying for keeping OTP field empty");
					Reporting.test.log(LogStatus.FAIL, "Check OTP field validation",
							"HTML validation message is not displaying for keeping OTP field empty"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_007")));
				}

			} catch (Exception e) {
				
				logger.error("HTML validation message is not working OTP field");
				Reporting.test.log(LogStatus.FAIL, "Check OTP field validation",
						"HTML validation message is not working OTP field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_007")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
