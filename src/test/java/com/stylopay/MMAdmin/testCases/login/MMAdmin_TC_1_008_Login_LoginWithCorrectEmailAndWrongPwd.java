package com.stylopay.MMAdmin.testCases.login;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;
import com.stylopay.MMAdmin.web.WaitFunctions;

public class MMAdmin_TC_1_008_Login_LoginWithCorrectEmailAndWrongPwd extends BaseClass {

	@Test(groups = {"login", "sanity", "regression"})
	public void MMAdmin_TC_008() throws IOException {

		Reporting.startTest("MMAdmin_TC_1_008");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_008", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_008", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.clickLoginBtn();
			WaitFunctions.threadWait();

			if (loginpage.getInvalidLoginCredentialErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_008", "Invalid Login Credential Err"))) {

				logger.info(
						"Proper validation message is displaying for entering correct email at Email Field but wrong password at Password field");
				Reporting.test.log(LogStatus.PASS, "Login with correct email and wrong password",
						"Proper validation message is displaying for entering correct email at Email Field but wrong password at Password field");

			} else {

				logger.error(
						"Proper validation message is not displaying for entering correct email at Email Field but wrong password at Password field");
				Reporting.test.log(LogStatus.FAIL, "Login with correct email and wrong password",
						"Proper validation message is not displaying for entering correct email at Email Field but wrong password at Password field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_008")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
