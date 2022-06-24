package com.stylopay.MMAdmin.testCases.login;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_1_006_Login_CheckPasswordMasking extends BaseClass {

	@Test(groups = {"login", "sanity", "regression"})
	public void MMAdmin_TC_1_006() {

		Reporting.startTest("MMAdmin_TC_1_006");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_006", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_006", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);		

			if (loginpage.getPasswordType().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_006", "Password Type"))) {

				logger.info("Password is in mask when admin typing password at Password field");
				Reporting.test.log(LogStatus.PASS, "Check password masking",
						"Password is in mask when admin typing password at Password field");

			} else {

				logger.error("Password is not in mask when admin typing password at Password field");
				Reporting.test.log(LogStatus.FAIL, "Check password masking",
						"Password is not in mask when admin typing password at Password field"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_006")));
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}
	}

}
