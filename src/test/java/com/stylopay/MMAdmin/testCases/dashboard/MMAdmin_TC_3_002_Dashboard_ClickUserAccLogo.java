package com.stylopay.MMAdmin.testCases.dashboard;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.DashboardPage;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_3_002_Dashboard_ClickUserAccLogo extends BaseClass{
	
	@Test(groups = { "dashboard", "regression" })
	public void MMAdmin_TC_3_002() throws IOException {

		Reporting.startTest("MMAdmin_TC_3_002");

		try {

			LoginPage loginpage = new LoginPage(driver);
			DashboardPage dshbrdpage = new DashboardPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_002", "Login Email");
			String password = ReadTestData.readTestDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_002",
					"Login Password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.pressEnterAtLoginBtn();

			try {

				if (ReadExpectedValidationAndSuccessMsgFromXL
						.getTestReportDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_002", "User Dashboard URL")
						.equals(driver.getCurrentUrl())) {
					logger.info("User dashboard is opening");

					dshbrdpage.clkDashboardMenu();

					if (ReadExpectedValidationAndSuccessMsgFromXL
							.getTestReportDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_002", "User Dashboard URL")
							.equals(driver.getCurrentUrl())) {

						logger.info("Dashboard page is opening while clicking on user account logo");
						Reporting.test.log(LogStatus.PASS, "Click user account logo",
								"Dashboard page is opening while clicking on user account logo");
					} else {

						logger.error(
								"Dashboard page is not opening while clicking on user account logo");
						Reporting.test.log(LogStatus.PASS, "Click user account logo",
								"Dashboard page is not opening while clicking on user account logo"
										+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_3_002")));
					}

				} else {

					logger.error("Problem occurs during user login");
				}

			} catch (NoSuchElementException e) {

				logger.error("Problem in user login" + e.getMessage());
				Reporting.test.log(LogStatus.FAIL, e.getMessage());

			} catch (StaleElementReferenceException e) {

				logger.error("Problem in user login" + e.getMessage());
				Reporting.test.log(LogStatus.FAIL, e.getMessage());
			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}
	}

}
