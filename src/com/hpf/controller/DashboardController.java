package com.hpf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpf.model.DashboardModel;

@Controller
public class DashboardController {
	
	@Autowired
	DashboardModel DashboardModel;

	@RequestMapping(value="/dashboard")
	public String dashboard(HttpServletRequest request){
		
		request.setAttribute("formInfo", DashboardModel.getFormInfoList());
			
		return "dashboard";		
	}
	
}
