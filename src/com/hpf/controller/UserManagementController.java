package com.hpf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpf.model.UserManagementModel;
import com.hpf.service.UserManagementService;

@Controller
public class UserManagementController {
	
	@Autowired
	UserManagementService UserManagementService;
	
	@Autowired
	UserManagementModel UserManagementModel;

	@RequestMapping(value="/userManagement")
	public String Administration(HttpServletRequest request){
		
		List<Map<String, Object>> userInfoList=UserManagementService.getUserInfo();
		request.setAttribute("userInfoList",userInfoList);
		

		return "userManagement";
	}
	
	
	//冻结账户
	@RequestMapping(value="/blockAccount")
	@ResponseBody
	public String  blcokAccount(String id){
		
		UserManagementModel.setBlockid(id);			
		return UserManagementService.blockUser(UserManagementModel);
	}
	
	
	//删除账户
	@RequestMapping(value="/deleteAccount")
	@ResponseBody
	public String  deleteAccount(String id){
				
		UserManagementModel.setDeleteid(id);
				
		return UserManagementService.deleteUser(UserManagementModel);
	}
		
	
	//新增账户
	@RequestMapping(value="/newAccount")
	@ResponseBody
	public String  newAccount(String username, String password, String phone){
		
		UserManagementModel.setNewAccountUsername(username);
		UserManagementModel.setNewAccountPassword(password);
		UserManagementModel.setNewAccountPhone(phone);
		
				
		if(UserManagementService.isUserExisted(UserManagementModel)==false){
			UserManagementService.newUser(UserManagementModel);
			return "1";
		}
		
		return "0";
	}
	
	
	
}
