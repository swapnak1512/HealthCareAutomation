package com.healthcare.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static Workbook workbook;
	static Sheet sheet;

	public static Object[][] getTestData(String filePath,String sheetName) {

		Object data[][] = null;

		try {

			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();

			data = new Object[rows][cols];

			for(int i=1;i<=rows;i++) {
				Row row = sheet.getRow(i);
				for(int j=0;j<cols;j++) {
					Cell cell = row.getCell(j);
					data[i-1][j] = cell.toString();

				}
			}

		} catch(Exception e) {

			e.printStackTrace();

		}

		return data;

	}

}