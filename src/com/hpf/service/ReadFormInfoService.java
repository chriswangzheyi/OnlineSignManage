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
	
	
	public List<Map<String, Object>> readForm(){
		
		return readFormInfoDAO.ReadFormInfo(formModel);		
	}

}
