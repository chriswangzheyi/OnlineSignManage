package com.hpf.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpf.ExcelUtil.ExcelWriter;
import com.hpf.ExcelUtil.SimpleExcelWriter;
import com.hpf.model.FormModel;

@Controller
public class ExportExcelController {
	
	@Autowired
	FormModel FormModel;

	@RequestMapping("/export")
	public void exportPayInfo(HttpServletRequest request,HttpServletResponse response){
		
		String exportHeader[] = new String[]{"餐厅名称","所在地区","餐厅类别","餐厅电话","提交时间","状态","审核人"};
		
		String title = "导出诗句-"+System.currentTimeMillis();
		

		ExcelWriter writer = new SimpleExcelWriter();
		
		writer.fillSheet(exportHeader, FormModel.getFormList());
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
