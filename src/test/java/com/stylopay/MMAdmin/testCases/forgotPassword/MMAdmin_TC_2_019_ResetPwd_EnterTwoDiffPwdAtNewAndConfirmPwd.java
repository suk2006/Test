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

public class MMAdmin_TC_2_019_ResetPwd_EnterTwoDiffPwdAtNewAndConfirmPwd extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_019() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_019");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_019", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_019", "OTP"));
			resetPwd.enterNewPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_019",
					"New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_019",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("resetPassword", "Sheet1",
						"MMAdmin_TC_2_019", "Unmtched Confirm Password Err Msg")
						.equals(resetPwd.getUnmatchedCnfrmPwdErrMsg())) {

					logger.info("Validation is working if Password and Confirm Password are not same");
					Reporting.test.log(LogStatus.PASS, "Check if Password and Confirm Password does not match",
							"Validation is working if Password and Confirm Password are not same");

				} else {

					logger.error("Validation is not working if Password and Confirm Password are not same");
					Reporting.test.log(LogStatus.FAIL, "Check if Password and Confirm Password does not match",
							"Validation is not working if Password and Confirm Password are not same"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_019")));
				}

			} catch (StaleElementReferenceException e) {

				logger.error(
						"Error message: Validation is not working if Password and Confirm Password are not same");
				Reporting.test.log(LogStatus.FAIL, "Check if Password and Confirm Password does not match",
						"Error message: Validation is not working if Password and Confirm Password are not same"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_019")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
