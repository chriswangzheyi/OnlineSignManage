package com.hpf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.DAO.ReadFormInfoDAO;
import com.hpf.model.FormModel;

@Component
public class ReadFormInfoService {
	
	@Autowired
	ReadFormInfoDAO readFormInfoDAO;
	
	@Autowired
	FormModel formModel;
	
	public int numOfPages(){
		
		return readFormInfoDAO.ReadNumofPages();
	}
	
	
	
	public List<Map<String, Object>> readForm(int currentPage){
		formModel.setCurrentPage(currentPage);		
		return readFormInfoDAO.ReadFormInfo(formModel);		
	}

}
