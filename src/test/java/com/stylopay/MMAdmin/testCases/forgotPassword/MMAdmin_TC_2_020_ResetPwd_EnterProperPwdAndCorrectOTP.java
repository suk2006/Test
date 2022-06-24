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

public class MMAdmin_TC_2_020_ResetPwd_EnterProperPwdAndCorrectOTP extends BaseClass {

	String email = "";
	String emailAccURL = "";
	String OTP = "";

	@Test(groups = { "forgotPwd","smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_020() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_020");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_020", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			email = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_020", "Email");
			emailAccURL = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_020",
					"Email acc URL");

			OTP = resetPwd.getOTPFromUserEmail(emailAccURL, email);

			resetPwd.enterOTP(OTP);
			resetPwd.enterNewPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_020", "New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_020",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();				

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ResetPassword", "Sheet1",
						"MMAdmin_TC_2_020", "Login Page URL").equals(driver.getCurrentUrl())) {

					logger.info(
							"Password is reseting successfully and page is successfully redirecting to Login page after reseting password");
					Reporting.test.log(LogStatus.PASS,
							"Check Reset Password functionlity by entering proper data at all fields",
							"Password is reseting successfully and page is successfully redirecting to Login page after reseting password");

				} else {

					logger.error("Password is not reseting and page is not redirecting to Login page");
					Reporting.test.log(LogStatus.FAIL,
							"Check Reset Password functionlity by entering proper data at all fields",
							"Password is not reseting"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_020")));
				}

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ResetPassword", "Sheet1",
						"MMAdmin_TC_2_020", "Reset Pwd Success Msg").equals(resetPwd.getResetPwdSuccessMsg())) {

					logger.info("Proper success message is displaying at Login page after reseting password");
					Reporting.test.log(LogStatus.PASS, "Check Reset Password Success Message",
							"Proper success message is displaying at Login page after reseting password");
				} else {

					logger.error("Proper success message is not displaying at Login page after reseting password");
					Reporting.test.log(LogStatus.FAIL, "Check Reset Password Success Message",
							"Proper success message is not displaying at Login page after reseting password"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_020")));
				}

			} catch (Exception e) {
				
				Reporting.test.log(LogStatus.FAIL, e.getMessage());
				
				logger.error("Error message: Password reseting feature is not working");
				Reporting.test.log(LogStatus.FAIL, "Check Reset Password feature with proper data",
						"Error message: Password reseting feature is not working"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_020")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
