package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.ForgotPasswordPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_2_001_ForgotPwd_ClkForgotPwdBtn extends BaseClass {

	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression"})
	public void MMAdmin_TC_2_001() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_001");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			
			forgotPasswordPage.clkForgotPwdBtn();
			logger.info("Click on Forgot Password button in the login page");

			if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ForgotPassword", "Sheet1",
					"MMAdmin_TC_2_001", "Forgot Password Page URL").equals(driver.getCurrentUrl())) {

				logger.info("Forgot Password Page is opening successfully");
				Reporting.test.log(LogStatus.PASS, "Click Forgot Password button",
						"Forgot Password page is opening successfully");

			} else {

				logger.error("Forgot Password Page is not opening by clicking on Forgot Password button");
				Reporting.test.log(LogStatus.FAIL, "Click Forgot Password button",
						"Forgot Password page is not opening successfully"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_001")));
				
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
