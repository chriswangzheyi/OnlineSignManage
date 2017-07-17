package com.hpf.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpf.ExcelUtil.ExcelWriter;
import com.hpf.ExcelUtil.SimpleExcelWriter;
import com.hpf.model.ExportDataModel;
import com.hpf.model.FormModel;
import com.hpf.service.ExcelFormatService;

@Controller
public class ExportExcelController {
	
	@Autowired
	FormModel FormModel;
	
	@Autowired
	ExcelFormatService ExcelFormatService;

	@RequestMapping("/export")
	public void exportPayInfo(HttpServletRequest request,HttpServletResponse response){
		
		String exportHeader[] = new String[]{"餐厅名称"};
		
		String title = "网签数据-"+System.currentTimeMillis();
		

		ExcelWriter writer = new SimpleExcelWriter();
		List <?>testlist=FormModel.getFormList();
		
		writer.fillSheet(exportHeader, testlist);
		// 设置字符集与流文件名称
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/xls;charset=utf-8");
		response.reset();
		String fName;
		try {
			fName = new String(title.getBytes("GBK"),"ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fName + ".xls");
			// 获取输出流
			OutputStream out = response.getOutputStream();
			writer.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
