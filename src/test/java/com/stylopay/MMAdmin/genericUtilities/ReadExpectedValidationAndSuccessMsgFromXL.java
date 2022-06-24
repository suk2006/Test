package com.stylopay.MMAdmin.genericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExpectedValidationAndSuccessMsgFromXL {

	public static String getTestReportDataFromExcel(String xlFileName, String sheetName, String tcName, String columnName)
			throws IOException {

		File file = new File("./TestResultData/" + xlFileName + ".xls");
		FileInputStream fi = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fi);
		HSSFSheet sheet = wb.getSheet(sheetName);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		
		String validationMsg=null;
		
		outer:
		for(int i=1; i<rowCount; i++){
			
			
				while(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(tcName))	{
					
					for(int j =0; j<colCount; j++){
						
						while(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase(columnName)){
							
							try{
								
								validationMsg = sheet.getRow(i).getCell(j).getStringCellValue();
								System.out.println("validationMsg: " + validationMsg);
								break outer;
								
							}catch(NullPointerException e){
								
								validationMsg = "";
								System.out.println("validationMsg: " + validationMsg);
								break outer;
							}
						}
					}
				}
					
				
			}
		
		return validationMsg;		
	}
}
