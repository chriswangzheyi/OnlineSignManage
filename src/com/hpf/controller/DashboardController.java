package com.hpf.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpf.model.DashboardModel;
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
	
	
	@RequestMapping(value="/changeFormWithParameter")
	@ResponseBody
	public String changeFormWithParameter(
			int targetPage, String startTime, String endTime,String keyword,String province,
			String city, String district, String status){
		
		
		//截取地区
		province=province.substring(province.lastIndexOf(",")+1);
		city=city.substring(city.lastIndexOf(",")+1);
		district=district.substring(district.lastIndexOf(",")+1);
		
		List<Map<String, Object>> formInfoList=ReadFormInfoService.readFormWithParameter(
				targetPage, startTime, endTime,keyword,province,city,district,status);
		return JSONArray.fromObject(formInfoList).toString();			
		
	}
	
	
	@RequestMapping(value="/readNewPageNum")
	@ResponseBody
	public int readNewPageNum(){
		
		return ReadFormInfoService.numOfPages();
	
	}
	
	//读取搜索条件下的页面数量
	@RequestMapping(value="/readNewPageNumWithParameter")
	@ResponseBody
	public int readNewPageNumWithParameter(String startTime, String endTime,String keyword,String province,
			String city, String district, String status){
		
		return ReadFormInfoService.readPageNumWithParameter(startTime, endTime, keyword, province, city, district, status);
	
	}
	
	
	//设置审核人和审核状态
	@RequestMapping(value="/setExaminer")
	@ResponseBody
	public String setExaminer(int id, int examineStatus, String failreason){
		
		
		FormModel.setExaminedRestaurantId(id);
		FormModel.setExaminedStatus(examineStatus);
		FormModel.setFailReason(failreason);
		FormModel.setExaminer(LoginModel.getUsername());
		
		//添加审核信息
		return ReadFormInfoService.updateExaminer();	//返回success或者fail
	}
	
	
	//更新地区信息
	@RequestMapping(value="/updateRegion")
	@ResponseBody
	public String updateRegion(HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/"+"resources/data");
        return ReadFormInfoService.updateRegion(path); //返回success或者fail
    }
	
	
}
