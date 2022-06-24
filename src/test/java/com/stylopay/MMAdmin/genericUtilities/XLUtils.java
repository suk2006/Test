package com.stylopay.MMAdmin.genericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class XLUtils {
	
	public static File file;
	public static FileInputStream imputstream;
	public static FileOutputStream outputstream;
	public static HSSFWorkbook workbook;
	public static HSSFSheet sheet;
	public static HSSFRow row;
	public static HSSFCell cell;
	
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException{
		
		file = new File(xlFile);
		imputstream = new FileInputStream(file);
		workbook = new HSSFWorkbook(imputstream);
		sheet = workbook.getSheet(xlSheet);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		workbook.close();
		imputstream.close();
		return rowCount;
		
	}
	
	
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException{
		
		file = new File(xlFile);
		imputstream = new FileInputStream(file);
		workbook = new HSSFWorkbook(imputstream);
		sheet = workbook.getSheet(xlSheet);
		row = sheet.getRow(rowNum);
		
		int cellCount = row.getPhysicalNumberOfCells();
		workbook.close();
		imputstream.close();
		return cellCount;
	}
	
	
	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException{
		
		file = new File(xlFile);
		imputstream = new FileInputStream(file);
		workbook = new HSSFWorkbook(imputstream);
		sheet = workbook.getSheet(xlSheet);
		
		row = sheet.getRow(rowNum);		
		cell = row.getCell(colNum);	
		String cellData;
		
		try{
			
			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(cell);
			
		}catch(Exception e){
			
			cellData="";
		}	
		
		workbook.close();
		imputstream.close();
		return cellData;
	}
	
	
	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException{
		
		file = new File(xlFile);
		imputstream = new FileInputStream(file);
		workbook = new HSSFWorkbook(imputstream);
		sheet = workbook.getSheet(xlSheet);
		
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		
		outputstream = new FileOutputStream(xlFile);
		workbook.write(outputstream);
		workbook.close();
		imputstream.close();
		outputstream.close();
	}

}
