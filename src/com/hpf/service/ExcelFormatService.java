package com.hpf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.model.ExportDataModel;
import com.hpf.model.FormModel;

@Component
public class ExcelFormatService {
	
	
	@Autowired
	ExportDataModel exportDataModel;
	
	@Autowired
	FormModel formModel;

	public List<ExportDataModel> convertToExcelList(){
		
		List formList = formModel.getFormList();	
		
		return formList;	
	}
	
}
