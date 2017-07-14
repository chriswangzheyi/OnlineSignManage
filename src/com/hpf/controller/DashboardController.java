package com.hpf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hpf.model.DashboardModel;
import com.hpf.model.FormModel;
import com.hpf.service.ReadFormInfoService;




@Controller
public class DashboardController {
	
	@Autowired
	DashboardModel DashboardModel;
	
	@Autowired
	FormModel FormModel;
	
	@Autowired
	ReadFormInfoService ReadFormInfoService;

	@RequestMapping(value="/dashboard")
	public String dashboard(HttpServletRequest request){
		
		request.setAttribute("formInfo", DashboardModel.getFormInfoList());
		request.setAttribute("numberOfPages",FormModel.getTotalPage() );
			
		
		
		return "dashboard";		
	}
	
	
	@RequestMapping(value="/changeFormPage")
	@ResponseBody
	
	public List<Map<String, Object>> changePage(int targetPage){
			
		List<Map<String, Object>> formInfoList=ReadFormInfoService.readForm(targetPage);
	/*			for(int i = 0 ; i < test.size() ; i++) {
					  System.out.println(test.get(i));
					}*/
				 
		//jsonJSONArray.fromObject(formInfoList);
				
		return formInfoList;	
				 
	}
	
	@RequestMapping(value="/readNewPageNum")
	@ResponseBody
	public int readNewPageNum(){
		
		return ReadFormInfoService.numOfPages();
	
	}
	
}
