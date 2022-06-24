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

public class MMAdmin_TC_2_022_ResetPwd_CheckCnfrmPwdFldPwdMasking extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_022() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_022");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_022", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);

			resetPwd.enterConfirmPwd(
					ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_022", "New Password"));

			resetPwd.clkSubmitBtn();
			WaitFunctions.threadWait();

			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("resetPassword", "Sheet1", "MMAdmin_TC_2_022", "Password Type")
					.equals(resetPwd.getCnfrmPwdType())) {

				logger.info("Password masking feature is working for Confirm Password field");
				Reporting.test.log(LogStatus.PASS, "Confirm Password field masking feature",
						"Password masking feature is working for Confirm Password field");

			} else {

				logger.error("Password masking feature is not working for Confirm Password field");
				Reporting.test.log(LogStatus.FAIL, "Confirm Password field masking feature",
						"Password masking feature is not working for Confirm Password field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_022")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
