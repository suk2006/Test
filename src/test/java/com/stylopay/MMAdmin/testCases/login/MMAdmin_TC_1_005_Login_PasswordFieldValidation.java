package com.stylopay.MMAdmin.testCases.login;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_1_005_Login_PasswordFieldValidation extends BaseClass {

	@Test(groups = {"login", "regression"})
	public void MMAdmin_TC_005() {

		Reporting.startTest("MMAdmin_TC_1_005");

		try {

			LoginPage loginpage = new LoginPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_005", "email");
			String password = ReadTestData.readTestDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_005", "password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.clickLoginBtn();

			if (loginpage.getPwdValidationErr().equals(ReadExpectedValidationAndSuccessMsgFromXL
					.getTestReportDataFromExcel("Login", "Sheet1", "MMAdmin_TC_1_005", "Password Validation Error"))) {

				logger.info("Proper validation message is displaying for keeping Password Field empty");
				Reporting.test.log(LogStatus.PASS, "Check Login Password Validation",
						"Proper validation message is displaying for keeping Password Field empty");

			} else {

				logger.error("Proper validation message is not displaying for keeping Password Field empty");
				Reporting.test.log(LogStatus.FAIL, "Check Login Password Validation",
						"Proper validation message is not displaying for keeping Password Field empty"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_1_005")));

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}

	}

}
