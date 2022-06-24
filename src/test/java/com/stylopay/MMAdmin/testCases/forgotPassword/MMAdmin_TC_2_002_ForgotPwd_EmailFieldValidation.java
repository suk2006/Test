package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.ForgotPasswordPage;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_2_002_ForgotPwd_EmailFieldValidation extends BaseClass{
	
	@Test(groups = { "forgotPwd"})
	public void MMAdmin_TC_2_002() throws IOException, InterruptedException {

		Reporting.startTest("MMAdmin_TC_2_002");

		try {			

			ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
			forgotPasswordPage.clkForgotPwdBtn();	
			
			forgotPasswordPage.clkSubmitBtn();
			WaitFunctions.threadWait();

			if (forgotPasswordPage.checkEmailHTMLValidation()==true) {

				logger.info("HTML validation message is displaying for keeping Email field empty");
				Reporting.test.log(LogStatus.PASS, "Check Email field validation",
						"HTML validation message is displaying for keeping Email field empty");

			} else {

				logger.error("HTML validation message is not displaying for keeping Email field empty");
				Reporting.test.log(LogStatus.FAIL, "Check Email field validation",
						"HTML validation message is not displaying for keeping Email field empty"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_002")));
				
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);

		}

	}

}
