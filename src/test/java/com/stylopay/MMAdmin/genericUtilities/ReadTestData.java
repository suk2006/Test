package com.stylopay.MMAdmin.genericUtilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class ReadTestData {

	public static String readTestDataFromExcel(String testXLFileName, String sheetName, String tcName,
			String columnName) throws IOException {

		String path = "./TestData/" + testXLFileName + ".xls";

		int rowCount = XLUtils.getRowCount(path, sheetName);
		int colCount = XLUtils.getCellCount(path, sheetName, 0);
		String testData = null;			
		

		outer:
		for (int i = 1; i < rowCount; i++) {		

			if (XLUtils.getCellData(path, sheetName, i, 0).equalsIgnoreCase(tcName)) {				

				for (int j = 0; j < colCount; j++) {

					if (XLUtils.getCellData(path, sheetName, 0, j).equalsIgnoreCase(columnName)) {

						try {

							testData = XLUtils.getCellData(path, sheetName, i, j);
							break outer;

						} catch (NullPointerException e) {

							testData = "";
							break outer;
						}

					}
				}
			}
		}

		return testData;

	}

}
