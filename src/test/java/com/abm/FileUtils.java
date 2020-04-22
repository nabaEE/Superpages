package com.abm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FileUtils 
{

String src="C:\\Users\\Prod\\Documents\\ABMdata.xlsx";

public void printDataInExcel(HashMap<String, String> excel) throws Exception
{
  File file= new File(src);

 FileInputStream fis= new FileInputStream(src);
 XSSFWorkbook wb= new XSSFWorkbook();
 XSSFSheet sh= wb.createSheet();
 int rowIndex=0;
 Cell firstData = null;
 Cell secondData=null;
    for(Map.Entry<String, String> eachitem: excel.entrySet())
    {
    	Row row=sh.createRow(rowIndex++);
    	firstData.setCellValue(eachitem.getKey());
    	secondData.setCellValue(eachitem.getValue());
     }
    FileOutputStream fos= new FileOutputStream(file);
    try {
		wb.write(fos);
		fos.close();
	} 
    catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
 
	
}
	
	
}



