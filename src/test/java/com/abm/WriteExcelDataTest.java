package com.abm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelDataTest 
{
	
	public static void main(String[] args) throws Exception
	{
		writeData();
	   String path="C:\\Users\\Prod\\Documents\\ABMdata.xlsx";
	   
	   
	}
	public static void writeData()throws Exception
	{
		String path="C:\\Users\\Prod\\Documents\\Data.xlsx";
		File file= new File(path);
		XSSFWorkbook wb= new XSSFWorkbook();
		XSSFSheet sh= wb.createSheet("Sheet1");
		sh.createRow(5);
		sh.createRow(0).createCell(0).setCellValue("Nandi");
		sh.createRow(1).createCell(0).setCellValue("Naba");
		sh.createRow(0).createCell(1).setCellValue("78965412");
	    sh.createRow(1).createCell(1).setCellValue("985622");
		FileOutputStream fos= new FileOutputStream(path);
	    wb.write(fos);
	    wb.close();
	    fos.close();
		 System.out.println("Data has entered successfully");
				
	}

	
	

}
