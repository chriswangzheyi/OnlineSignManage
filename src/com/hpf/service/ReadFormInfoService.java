package com.hpf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.DAO.ReadFormInfoDAO;
import com.hpf.model.FormModel;
import com.hpf.model.LoginModel;

@Component
public class ReadFormInfoService {
	
	@Autowired
	ReadFormInfoDAO readFormInfoDAO;
	
	@Autowired
	FormModel formModel;
	
	@Autowired
	LoginModel loginModel;
	
	public int numOfPages(){
		
		return readFormInfoDAO.ReadNumofPages();
	}
		
	//读取列表信息
	public List<Map<String, Object>> readForm(int currentPage){
		formModel.setCurrentPage(currentPage);		
		return readFormInfoDAO.ReadFormInfo(formModel);		
	}
	
	//读取列表信息加时间
	public List<Map<String, Object>> readFormWithTime(int currentPage, Date StartTime, Date EndTime){
		formModel.setCurrentPage(currentPage);		
		formModel.setFilterStartTime(StartTime);
		formModel.setFilterEndTime(EndTime);		
		return readFormInfoDAO.ReadFormInfoWithTime(formModel);		
	} 
	
	
	public String updateExaminer(){		
		return readFormInfoDAO.setExaminer(formModel,loginModel);
	}

}
