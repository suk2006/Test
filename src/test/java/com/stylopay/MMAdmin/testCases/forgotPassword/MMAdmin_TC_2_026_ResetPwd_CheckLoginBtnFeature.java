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

public class MMAdmin_TC_2_026_ResetPwd_CheckLoginBtnFeature extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_026() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_026");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();

			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_026", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			ResetPassword resetPwd = new ResetPassword(driver);			

			resetPwd.clkLoginBtn();
			WaitFunctions.threadWait();

			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_026", "Login Page URL")
					.equals(driver.getCurrentUrl())) {

				logger.info("In Reset Password page, Login button feature is working properly");
				Reporting.test.log(LogStatus.PASS, "Check Login button feature in Reset Password page",
						"In Reset Password page, Login button feature is working properly");

			} else {

				logger.error("In Reset Password page, Login button feature is not working");
				Reporting.test.log(LogStatus.FAIL, "Check Login button feature in Reset Password page",
						"In Reset Password page, Login button feature is not working"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_026")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
