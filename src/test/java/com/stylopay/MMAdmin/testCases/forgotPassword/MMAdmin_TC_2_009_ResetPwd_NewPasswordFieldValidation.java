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

public class MMAdmin_TC_2_009_ResetPwd_NewPasswordFieldValidation extends BaseClass {

	@Test(groups = { "forgotPwd" })
	public void MMAdmin_TC_2_009() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_009");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_009", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterOTP(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_009", "OTP"));
			resetPwd.enterNewPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_009", "New Password"));
			resetPwd.enterConfirmPwd(ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_009",
					"Confirm Password"));

			try {
				resetPwd.clkSubmitBtn();
				WaitFunctions.threadWait();

				if (resetPwd.checkNewPasswordHTMLValidation() == true) {

					logger.info("HTML validation message is displaying for keeping NewPassword field empty");
					Reporting.test.log(LogStatus.PASS, "NewPassword field validation",
							"HTML validation message is displaying for keeping NewPassword field empty");

				} else {

					logger.error("HTML validation message is not displaying for keeping NewPassword field empty");
					Reporting.test.log(LogStatus.FAIL, "NewPassword field validation",
							"HTML validation message is not displaying for keeping NewPassword field empty"
									+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_009")));
				}

			} catch (StaleElementReferenceException e) {

				logger.error("Error message: HTML validation is not working for NewPassword field");
				Reporting.test.log(LogStatus.FAIL, "NewPassword field validation",
						"Error message: HTML validation is not working for NewPassword field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_009")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}
}
