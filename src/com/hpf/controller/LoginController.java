package com.hpf.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpf.model.DashboardModel;
import com.hpf.service.LoginService;
import com.hpf.service.ReadFormInfoService;

@Controller
public class LoginController {
	
	
	@Autowired 
	private LoginService LoginService;
	
	@Autowired
	private ReadFormInfoService ReadFormInfoService;
	
	@Autowired
	DashboardModel DashboardModel;
	
	
	
	@RequestMapping(value="/login")
	public String login(
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		
		//读取用户登录及权限信息
		String accountInfo=LoginService.passowrdValidation(username, password);
			
			// -1:账号密码错误  -3:账号不存在  
			if(accountInfo.equals("-1") || accountInfo.equals("-3") ){
				request.setAttribute("loginfail","您的账号或者密码错误");
				return "login";
			}
			
			//账号被冻结
			if(accountInfo.equals("-2")){
				request.setAttribute("loginfail","您的账号已经被冻结");
				return "login";
			}
			
		
			//读取商户信息
			List<Map<String, Object>> formInfo=ReadFormInfoService.readForm();
			request.setAttribute("formInfo", formInfo);
			
			DashboardModel.setFormInfoList(formInfo);						
			
			request.setAttribute("authLevel",accountInfo);
			return "dashboard";

	}	
}
