package kang.practice.selenium_hybrid.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME = 5;
	
	public static String generateUniqueEmail() {
		
		Date currentTimeStamp = new Date();
		return "simron" + currentTimeStamp.toString().replace(" ", "_").replace(":","_") + "@abc.com";
	}
	
	public static Object [][] getTestDataFromExcel(String sheetName) {
		
		File ExcelSheet = new File("/Users/simronkang/eclipse-workspace/selenium_hybrid/src/main/java/kang/practice/selenium_hybrid/testdata/kangPracticeTestData.xlsx");
		FileInputStream excelInput;
		XSSFWorkbook workbook = null;
		
		try {
			 excelInput = new FileInputStream(ExcelSheet);
			  workbook = new XSSFWorkbook(excelInput);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();
		
		Object [][] allCredentials = new Object [totalRows][totalCols];
		
		for(int i=0;i<totalRows;i++) {
			
			XSSFRow currentRow = sheet.getRow(i+1);
			
			for(int j=0;j<totalCols;j++) {
				
				XSSFCell currentCell = currentRow.getCell(j);
				
				CellType cellType = currentCell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					allCredentials[i][j] = currentCell.getStringCellValue();
					break;
				case NUMERIC:
					allCredentials[i][j] = Integer.toString((int) currentCell.getNumericCellValue());
					
					break;
				case BOOLEAN:
					allCredentials[i][j] = currentCell.getBooleanCellValue();
					break;
				case BLANK:
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;
				}
				
			}
		}
		
		return allCredentials;
	}
	
	public static String destinationScreenshotPath(WebDriver driver, String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationScreenshotPath = "/Users/simronkang/eclipse-workspace/selenium_hybrid/screenshots/"+testName+".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}
}
