package com.hpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.DAO.ChangePasswordDAO;
import com.hpf.model.ChangePasswordModel;
import com.hpf.model.LoginModel;



@Component
public class ChangePasswordService {
	
	
	@Autowired
	ChangePasswordDAO changePasswordDAO;
	
	@Autowired
	ChangePasswordModel changePasswordModel;
	
	@Autowired
	LoginModel loginModel;
	
	

	public String changePassword(ChangePasswordModel changePasswordModel){
	
	//检查原密码
	if(changePasswordDAO.checkOriginalPassword(changePasswordModel, loginModel)== false ){
		
		return "原密码错误";
	} 
	
	
	//修改密码
	if(changePasswordDAO.changePassword(changePasswordModel, loginModel).equals("success") ){
	return "密码修改成功";
	}
	
	return "错误";
	}

}
