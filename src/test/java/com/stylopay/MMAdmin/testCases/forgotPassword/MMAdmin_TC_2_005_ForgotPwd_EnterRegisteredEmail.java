package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.ForgotPasswordPage;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_2_005_ForgotPwd_EnterRegisteredEmail extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression"})
	public void MMAdmin_TC_2_005() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_005");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();	
			
			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_005", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ForgotPassword", "Sheet1",
					"MMAdmin_TC_2_005", "Reset Password Page URL")
					.equals(driver.getCurrentUrl())) {

				logger.info("Reset password page is opening");
				Reporting.test.log(LogStatus.PASS, "Enter proper email at Email field in Forgot Password page",
						"Reset Password page is opening successfully");

			} else {

				logger.error("Reset password page is not opening");
				Reporting.test.log(LogStatus.FAIL, "Enter proper email at Email field in Forgot Password page",
						"Reset Password page is not opening successfully"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_005")));

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}
}
