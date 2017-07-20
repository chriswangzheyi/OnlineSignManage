package com.hpf.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.DashboardModel;
import com.hpf.model.FormModel;
import com.hpf.model.LoginModel;
import com.hpf.model.MerchantModel;
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
	
	@Autowired
	FormModel FormModel;
	
	
	@Autowired
	MerchantDAO MerchantDAO;
	
	@Autowired
	MerchantModel MerchantModel;
	
	@Autowired
	LoginModel LoginModel;
	
	@RequestMapping(value="/login")
	public String login(
			@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="password", required=false) String password,
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
			
			//设置用户名
			LoginModel.setUsername(username);
			
		
			//读取商户信息
			List<Map<String, Object>> formInfo=ReadFormInfoService.readForm(1);
			FormModel.setFormList(formInfo);
			
			//总页数
			int numberOfPages = ReadFormInfoService.numOfPages();
			
			
			DashboardModel.setFormInfoList(formInfo);	
			FormModel.setTotalPage(numberOfPages);
		
			
			request.setAttribute("formInfo", formInfo);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("authLevel",accountInfo);
			LoginModel.setAuthLevel(accountInfo);
			
			//读取地区
			List<Map<String, Object>>regionList= MerchantDAO.getRestaurantType(MerchantModel);
			MerchantModel.setAllRestaurantType(regionList);
			
			
			return "dashboard";

	}	
}
