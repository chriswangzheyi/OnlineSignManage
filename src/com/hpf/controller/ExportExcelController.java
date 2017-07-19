package com.hpf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpf.model.ExportDataModel;
import com.hpf.model.FormModel;
import com.hpf.service.ExcelFormatService;

@Controller
public class ExportExcelController {
	
	@Autowired
	FormModel FormModel;
	
	@Autowired
	ExcelFormatService ExcelFormatService;
	
	@Autowired
	ExportDataModel ExportDataModel;
	

		//需要引入dom4j.ar,poi.jar,poi-ooxml.jar,poi-ooxml-schemas.jar,xmlbeans.jar
		@RequestMapping("/export")
		public void ExportExcel(HttpServletRequest request,HttpServletResponse response){
		
	
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		
		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("网签数据");
		 
		//This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"餐厅名称", "所在地区", "餐厅类别", "餐厅电话","提交时间", "状态", "审核人"});
		data.put("2", new Object[] {1, "Amit", "Shukla1111"});
		data.put("3", new Object[] {2, "Lokesh", "Gupta2222"});
		data.put("4", new Object[] {3, "John", "Adwards3333"});
		data.put("5", new Object[] {4, "Brian", "Schultz4444"});
		 
		
		
		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset)
		{
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr)
		    {
		       Cell cell = row.createCell(cellnum++);
		       if(obj instanceof String)
		            cell.setCellValue((String)obj);
		        else if(obj instanceof Integer)
		            cell.setCellValue((Integer)obj);
		    }
		}
		try 
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=onlinesign.xls");
			workbook.write(response.getOutputStream()); 
		     
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
		
		
		
		
		
	}
	
		
		
		
		
		
	
		
		
		
}
