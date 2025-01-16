package com.avactis.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelDataProvider {

	XSSFWorkbook wb;
	int startRow = 0;
	int startCol = 0;
	int endRow = 0;
	int endCol = 0;

	public ExcelDataProvider() {
		File src = new File("./TestData/TestData.xlsx");

		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to read Excel " + e.getMessage());
		}
	}

	public String getStringData(int sheetIndex, int row, int column) {
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}

	public String getStringData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}

	public double getNumericData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

//	public int getMarker(String sheetName, String marker, int sRow, int sCol) {
//		XSSFSheet sheet = wb.getSheet(sheetName);
//		int rows = sheet.getLastRowNum();
//		int col = sheet.getRow(1).getLastCellNum();
//		for (int r = 0; r <= rows; r++) {
//			XSSFRow row = sheet.getRow(r);
//			for (int c = 0; c < col; c++) {
//				XSSFCell cell = row.getCell(c);
//				switch (cell.getCellType()) {
//				case STRING:
//					if (cell.getStringCellValue().equals(marker)) {
//						startRow = cell.getRowIndex();
//						startCol = cell.getColumnIndex();
//						break;
//					}
//				default:
//					break;
//				}
//			}
//		}
//
//		endRow = startRow + 1;
//		endCol = startCol + 1;
//
//		for (int r = endRow; r <= rows; r++) {
//			XSSFRow row = sheet.getRow(r);
//			for (int c = startCol; c < col; c++) {
//				XSSFCell cell = row.getCell(c);
//				switch (cell.getCellType()) {
//				case STRING:
//					if (cell.getStringCellValue().equals(marker)) {
//						endRow = cell.getRowIndex();
//						endCol = cell.getColumnIndex();
//						break;
//					}
//				default:
//					break;
//				}
//			}
//		}
//
//		for (int i = startRow + 1; i < endRow; i++) {
//			for (int j = startCol + 1; j < endCol; j++) {
//				sheet.getRow(i).getCell(j).getStringCellValue();
//			}
//		}
//		return startRow, endRow;
//
//	}
}
