package com.hpf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpf.model.DashboardModel;
import com.hpf.model.ExportDataModel;
import com.hpf.model.FormModel;
import com.hpf.model.LoginModel;
import com.hpf.service.ReadFormInfoService;

import net.sf.json.JSONArray;





@Controller
public class DashboardController {
	
	@Autowired
	DashboardModel DashboardModel;
	
	@Autowired
	FormModel FormModel;
	
	@Autowired
	LoginModel LoginModel;
	
	@Autowired
	ReadFormInfoService ReadFormInfoService;

	@RequestMapping(value="/dashboard")
	public String dashboard(HttpServletRequest request){
		
		request.setAttribute("formInfo", DashboardModel.getFormInfoList());
		request.setAttribute("numberOfPages",FormModel.getTotalPage() );
		request.setAttribute("authLevel",LoginModel.getAuthLevel());
		
		return "dashboard";		
	}
	
	
	@RequestMapping(value="/logout")
	public String logout (){
		
		return "logout";
	}
	
	
	@RequestMapping(value="/changeFormPage")
	@ResponseBody
	
	public String changePage(int targetPage){
			
		List<Map<String, Object>> formInfoList=ReadFormInfoService.readForm(targetPage);
	
		return JSONArray.fromObject(formInfoList).toString();
					 
	}
	
	@RequestMapping(value="/readNewPageNum")
	@ResponseBody
	public int readNewPageNum(){
		
		return ReadFormInfoService.numOfPages();
	
	}
	
	
	//设置审核人和审核状态
	@RequestMapping(value="/setExaminer")
	public String setExaminer(int id, int examineStatus, String failreason){

		
		FormModel.setExaminedRestaurantId(id);
		FormModel.setExaminedStatus(examineStatus);
		FormModel.setFailReason(failreason);
		FormModel.setExaminer(LoginModel.getUsername());
		ReadFormInfoService.updateExaminer();
		
		return null;	
	}
	
}
