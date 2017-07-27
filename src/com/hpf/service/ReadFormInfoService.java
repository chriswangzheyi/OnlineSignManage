package com.hpf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	//读取列表信息带参数
	public List<Map<String, Object>> readFormWithParameter(
			int currentPage, String StartTime, String EndTime, String keyword, 
			String province, String city, String district, String status){
		formModel.setCurrentPage(currentPage);		
		formModel.setFilterStartTime(StartTime);
		formModel.setFilterEndTime(EndTime);
		formModel.setFilterKeyword(keyword);
		formModel.setFilterProvince(province);
		formModel.setFilterCity(city);
		formModel.setFilterDistrict(district);
		formModel.setFilterExaminedStatus(status);
		return readFormInfoDAO.ReadFormInfoWithParameter(formModel);		
	} 
	
	
	public String updateExaminer(){		
		return readFormInfoDAO.setExaminer(formModel,loginModel);
	}
	
	
	
	//读取列表信息带参数
	public int readPageNumWithParameter(
			String StartTime, String EndTime, String keyword, 
			String province, String city, String district, String status){	
		formModel.setFilterStartTime(StartTime);
		formModel.setFilterEndTime(EndTime);
		formModel.setFilterKeyword(keyword);
		formModel.setFilterProvince(province);
		formModel.setFilterCity(city);
		formModel.setFilterDistrict(district);
		formModel.setFilterExaminedStatus(status);
		return readFormInfoDAO.ReadNumOfPageWithParameter(formModel);		
	}
	
	//更新地区信息
	public String updateRegion(String currentPath){
		return readFormInfoDAO.updateRegion(currentPath);
	}
	

}
