package com.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {
	/**
	 * @author 张珵珺
	 * @date 2018-07-02
	 * @param response 响应
	 * @param fileName 文件名
	 * @param sheetName 页签名
	 * @param headers 表头名
	 * @param rows 行
	 * @return
	 * @throws IOException 
	 */
	public static void exportExcel(HttpServletResponse response,String fileName,String sheetName,List<String> headers,
																	List<Map<String,Object>> rows) throws IOException {
		HSSFWorkbook wb=new HSSFWorkbook();
		String sheetname=sheetName!=null?sheetName:fileName;
		HSSFSheet sheet = wb.createSheet(sheetname);
		sheet.setDefaultColumnWidth(20);
		HSSFCellStyle cellstyleHeader = wb.createCellStyle();  
		HSSFCellStyle cellstyleBody = wb.createCellStyle();  
		cellstyleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellstyleBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置字体
		HSSFFont font = wb.createFont();  
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		font.setFontHeightInPoints((short) 12);
		font.setFontName("黑体");
		cellstyleHeader.setFont(font);
		//表头
		HSSFRow row1 = sheet.createRow(0);
		
		for(int i=0;i<headers.size(); i++) {
			HSSFCell cell=row1.createCell(i);
			cell.setCellValue(headers.get(i));
			cell.setCellStyle(cellstyleHeader);
			//row1.createCell(i).setCellValue(headers.get(i));
		}
		
		for(int i=1;i<=rows.size();i++) {
			HSSFRow row=sheet.createRow(i);
			int j=0;
			Iterator iterator=rows.get(i-1).entrySet().iterator();
			while(iterator.hasNext()) {
				Entry entry=(Entry) iterator.next();
				Object value=entry.getValue();
				HSSFCell cell=row.createCell(j);
				if(null==value) {
					cell.setCellValue("");
				}else {
				cell.setCellValue(value.toString());
				}
				cell.setCellStyle(cellstyleBody);
			//	row.createCell(j).setCellValue(value.toString());
				j++;
			}
		}
		response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes(),"iso-8859-1")+".xls");
		response.setContentType("application/msexcel");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.close();
	}
}
