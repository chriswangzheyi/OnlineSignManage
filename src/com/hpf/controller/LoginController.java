package com.hpf.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpf.service.LoginService;

@Controller
public class LoginController {
	
	
	@Autowired 
	private LoginService LoginService;

	
	@RequestMapping(value="/login")
	public String login(
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		//账号密码匹配
		if(LoginService.passowrdValidation(username, password)==true){			
			return "dashboard";
		}
		
		
		//账号密码不匹配
		request.setAttribute("wrong_password","您的账号或者密码错误");
		return "home";
	}
	
}
