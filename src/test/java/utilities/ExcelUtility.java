package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	FileInputStream fis;
	XSSFWorkbook xo;
	XSSFRow row;
	
	 XSSFSheet sheet;
	
	 XSSFCell cell;
	
	 FileOutputStream fo;
	String path=null;
	ExcelUtility(String path){
		
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		fis= new FileInputStream(path);
	    xo= new XSSFWorkbook(fis);
	    sheet = xo.getSheet(sheetName);
	    int lastRowNum = sheet.getLastRowNum();
	    return lastRowNum;
		
		
	}
public int getCellCount(String sheetName, int rownum) throws IOException {
		
	fis= new FileInputStream(path);
    xo= new XSSFWorkbook(fis);
    sheet = xo.getSheet(sheetName);
    row = sheet.getRow(rownum);
    int lastCellNum = row.getLastCellNum();
    return lastCellNum;
	}
public String getData(String sheetName,int rownum, int cellcount) throws IOException {
	fis= new FileInputStream(path);
    xo= new XSSFWorkbook(fis);
    sheet = xo.getSheet(sheetName);
    row = sheet.getRow(rownum);
   cell = row.getCell(cellcount);
   String data;
   DataFormatter formatter = new DataFormatter();
   try{
	   //try catch block

//This ensures your program doesn’t crash if something goes wrong — for example:
//
//cell is null,
//
//cell type is unsupported,
//
//or Excel file is corrupted.
//
//Instead of throwing an exception, it assigns an empty string ("") to data.
   data=formatter.formatCellValue(cell); //Returns the formatted value of a cell a
   }
   catch(Exception e)
   {
   data="";
   }
   return data;
	
}
public void setData(String sheetName,int rownum, int cellcount,String data) throws IOException {
	File xlfile=new File(path);
	if(!xlfile.exists())
	// If file not exists then create new file
	{
	xo=new XSSFWorkbook();
	fo=new FileOutputStream(path);
	xo.write(fo);
	}
	fis=new FileInputStream(path);
	xo= new XSSFWorkbook(fis);
	if(xo.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
	xo.createSheet (sheetName);
	sheet=xo.getSheet(sheetName);
	if(sheet.getRow(rownum)==null) // If row not exists then create new Row
	sheet.createRow(rownum);
	row=sheet.getRow(rownum);
	cell=row.createCell(cellcount);
	cell.setCellValue(data);
	fo=new FileOutputStream(path);
	xo.write(fo);
	xo.close();
	fis.close();
	fo.close();
}

}
