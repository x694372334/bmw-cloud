package com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import com.google.common.collect.Table.Cell;

public class excelPoiRead2 {

	//*************xlsx文件读取函数************************
	//excel_name为文件名，arg为需要查询的列号
	//返回二维字符串数组
	@SuppressWarnings({ "resource", "unused" })
	public ArrayList<ArrayList<String>> xlsx_reader(InputStream is,int ... args) throws IOException {
 
        //读取xlsx文件
        HSSFWorkbook xssfWorkbook = null;
        //寻找目录读取文件
//        File excelFile = new File(excel_url); 
//        InputStream is = new FileInputStream(excelFile);
        xssfWorkbook = new HSSFWorkbook(is);
      
        if(xssfWorkbook==null){
        	System.out.println("未读取到内容,请检查路径！");
        	return null;
        }
        
        
        
        ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
        //遍历xlsx中的sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) continue; 
                ArrayList<String> curarr=new ArrayList<String>();
                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                	HSSFCell cell = xssfRow.getCell(args[columnNum]);
                	
                	curarr.add( Trim_str( getValue(cell) ) );
                }
                ans.add(curarr);
            }
        }
        return ans;
    }
	
	public void inputstreamtofile(InputStream ins,File file){
		   OutputStream os;
		try {
			os = new FileOutputStream(file);
			  int bytesRead = 0;
			   byte[] buffer = new byte[8192];
			   while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			      os.write(buffer, 0, bytesRead);
			   }
			   os.close();
			   ins.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    
    //判断后缀为xlsx的excel文件的数据类
    @SuppressWarnings("deprecation")
	private static String getValue(XSSFCell xssfRow) {
    	if(xssfRow==null){
    		return "---";
    	}
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
        	double cur=xssfRow.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur)  
        	        inputValue = longVal;  
        	else  
        	        inputValue = cur; 
            return String.valueOf(inputValue);
        } else if(xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK || xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR){
        	return "---";
        }
        else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
    
    //判断后缀为xls的excel文件的数据类型
    @SuppressWarnings("deprecation")
	private static String getValue(HSSFCell hssfCell) {
    	if(hssfCell==null){
    		return "---";
    	}
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        	double cur=hssfCell.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur)  
        	        inputValue = longVal;  
        	else  
        	        inputValue = cur; 
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK || hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR){
        	return "---";
        }
        else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
  //字符串修剪  去除所有空白符号 ， 问号 ， 中文空格
    static private String Trim_str(String str){
        if(str==null)
            return null;
        return str.replaceAll("[\\s\\?]", "").replace("　", "");
    }

}
	
