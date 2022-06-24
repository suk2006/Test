package com.stylopay.MMAdmin.testCases.forgotPassword;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.pageObjects.ResetPassword;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_2_024_ResetPwd_LoginWithExpiredPwd extends BaseClass {

	@Test(groups = { "forgotPwd", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_2_024() throws IOException {

		Reporting.startTest("MMAdmin_TC_2_024");

		try {

			LoginPage loginpage = new LoginPage(driver);
			ResetPassword resetPwd = new ResetPassword(driver);

			String email = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_024", "Email");
			String password = ReadTestData.readTestDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_024",
					"Old Password");

			loginpage.setUserName(email);
			logger.info("Enter email");

			loginpage.setPassword(password);
			logger.info("Enter expired password");

			loginpage.pressEnterAtLoginBtn();
			logger.info("Click on Login button");

			if (ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("ResetPassword", "Sheet1", "MMAdmin_TC_2_024",
							"Login With Expired Pwd Err")
					.equalsIgnoreCase(resetPwd.getLoginWithExpiredPwdValidationErr())) {

				logger.info("Validation is working when user is login with expired password");
				Reporting.test.log(LogStatus.PASS, "Check Login with correct email and expired password",
						"Validation is working when user is login with expired password");

			} else {

				logger.error(
						"Either Validation is not working or proper validation message is not displaying when user is login with expired password");
				Reporting.test.log(LogStatus.FAIL, "Check Login with correct email and expired password",
						"Either Validation is not working or pproper validation message is not displaying when user is login with expired password"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_2_024")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}
}
