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

public class MMAdmin_TC_2_027_ForgotPwd_CheckLoginBtnFeature extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_027() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_027");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();
			
			forgotPasswordPage.clkLoginBtn();
			WaitFunctions.threadWait();
			
			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_027", "Login Page URL")
					.equals(driver.getCurrentUrl())) {

				logger.info("In Forgot Password page, Login button feature is working properly");
				Reporting.test.log(LogStatus.PASS, "Check Login button feature in Forgot Password page",
						"In Forgot Password page, Login button feature is working properly");

			} else {

				logger.error("In Forgot Password page, Login button feature is not working");
				Reporting.test.log(LogStatus.FAIL, "Check Login button feature in Forgot Password page",
						"In Forgot Password page, Login button feature is not working"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_027")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
