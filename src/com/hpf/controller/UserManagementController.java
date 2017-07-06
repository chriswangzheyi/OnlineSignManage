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
	
	
	@RequestMapping(value="/blockAccount")
	@ResponseBody
	public String  blcokAccount(String id){
		
		UserManagementModel.setBlockid(id);
				
		return UserManagementService.blockUser(UserManagementModel);
	}
	
	
	@RequestMapping(value="/deleteAccount")
	@ResponseBody
	public String  deleteAccount(){
				
		return null;
	}
	
	
}
