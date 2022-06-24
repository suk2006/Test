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

public class MMAdmin_TC_2_014_ResetPwd_EnterNewPwdOfLessthan8Characters extends BaseClass {

	String password;

	@Test(groups = { "forgotPwd", "sanity" })
	public void MMAdmin_TC_2_014() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_014");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_014", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_014", "OTP"));

			password = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_014",
					"New Password");
			resetPwd.enterNewPwd(password);
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_014",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (resetPwd.validatePasswordFormat(password) == false) {

					logger.info("Password validation is working if password length is less than 8 characters");
					Reporting.test.log(LogStatus.PASS, "Enter password of less than 8 characters",
							"Password validation is working properly if password length is less than 8 characters");

				} else {

					logger.error("Password validation is not working if password length is less than 8 characters");
					Reporting.test.log(LogStatus.FAIL, "Enter password of less than 8 characters",
							"Password validation is not working if password length is less than 8 characters"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_014")));
				}

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ResetPassword", "Sheet1",
						"MMAdmin_TC_2_014", "Password Format Validation Err Msg")
						.equals(resetPwd.getPwdFormatValidationErrMsg())) {

					logger.info("Password format validation message is displaying properly");
					Reporting.test.log(LogStatus.PASS, "Check New Password Format Validaion Message",
							"Password format validation message is displaying properly");
				} else {

					logger.error("Password format validation message is not displaying properly");
					Reporting.test.log(LogStatus.FAIL, "Check New Password Format Validaion Message",
							"Password format validation message is not displaying properly"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_014")));
				}

			} catch (StaleElementReferenceException e) {

				logger.error("Error message: Password validation is not working if password is less than 8 characters");
				Reporting.test.log(LogStatus.FAIL, "Enter password of less than 8 characters",
						"Error message: Password validation is not working if password is less than 8 characters"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_014")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
