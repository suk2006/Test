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

public class MMAdmin_TC_2_004_ForgotPwd_EnterUnregisteredEmail extends BaseClass{
	
	@Test(groups = { "forgotPwd", "smoke"})
	public void MMAdmin_TC_2_004() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_004");

		try {

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();	
			
			forgotPasswordPage.enterEmail(
					ReadTestData.readTestDataFromExcel("ForgotPassword", "Sheet1", "MMAdmin_TC_2_004", "email"));
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			if (ReadExpectedValidationAndSuccessMsgFromXL.getTestReportDataFromExcel("ForgotPassword", "Sheet1",
					"MMAdmin_TC_2_004", "Invalid Email Validation Msg")
					.equals(forgotPasswordPage.getInvalidEmailValidationMsg())) {

				logger.info("Validation message is displaying for entering unregistered Email at Email field");
				Reporting.test.log(LogStatus.PASS, "Check unregistered email validation",
						"Validation message is displaying for entering unregistered Email at Email field");

			} else {

				logger.error("Validation message is not displaying for entering unregistered Email at Email field");
				Reporting.test.log(LogStatus.FAIL, "Check unregistered email validation",
						"Validation message is not displaying for entering unregistered Email at Email field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_004")));

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}
}
