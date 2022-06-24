package com.stylopay.MMAdmin.testCases.dashboard;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.stylopay.MMAdmin.apiUtilities.GetPrefundBalance;
import com.stylopay.MMAdmin.genericUtilities.ReadExpectedValidationAndSuccessMsgFromXL;
import com.stylopay.MMAdmin.genericUtilities.ReadTestData;
import com.stylopay.MMAdmin.genericUtilities.Reporting;
import com.stylopay.MMAdmin.pageObjects.DashboardPage;
import com.stylopay.MMAdmin.pageObjects.LoginPage;
import com.stylopay.MMAdmin.web.BaseClass;

public class MMAdmin_TC_3_003_Dashboard_CheckPrefundBal extends BaseClass {

	@Test(groups = { "dashboard", "smoke", "sanity", "regression" })
	public void MMAdmin_TC_3_003() throws IOException {

		Reporting.startTest("MMAdmin_TC_3_003");

		try {

			LoginPage loginpage = new LoginPage(driver);
			DashboardPage dshbrdpage = new DashboardPage(driver);

			String email = ReadTestData.readTestDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_003", "Login Email");
			String password = ReadTestData.readTestDataFromExcel("Dashboard", "Sheet1", "MMAdmin_TC_3_003",
					"Login Password");

			loginpage.setUserName(email);
			loginpage.setPassword(password);
			loginpage.pressEnterAtLoginBtn();

			String getPrefundBalFromAPI = GetPrefundBalance.getDashbrdPrefundBal();
			System.out.println("getPrefundBalFromAPI: " + getPrefundBalFromAPI);

			String getPrefundBalFromDshbrd = dshbrdpage.invokeDshbrdWalletBal();
			System.out.println("getPrefundBalFromDshbrd: " + getPrefundBalFromDshbrd);

			if (getPrefundBalFromAPI.equals(getPrefundBalFromDshbrd)) {

				logger.info("Correct prefund balance is displaying in dashboard");
				Reporting.test.log(LogStatus.PASS, "Check dashboard USD prefund balance",
						"Correct prefund balance is displaying in dashboard");
			} else {

				logger.error("Wrong prefund balance is displaying in dashboard");
				Reporting.test.log(LogStatus.FAIL, "Check dashboard USD prefund balance",
						"Wrong prefund balance is displaying in dashboard"
								+ Reporting.test.addScreenCapture(captureScreen(driver, "MMAdmin_TC_3_003")));

			}

		} catch (Exception e) {

			Reporting.test.log(LogStatus.FATAL, e);
		}
	}

}
