package com.hpf.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.MerchantModel;

@Controller
public class DetailsController {
	
	@Autowired
	MerchantModel MerchantModel;
	
	@Autowired
	MerchantDAO MerchantDAO;
	
	@RequestMapping(value="/details")
	public String detailPage(HttpServletRequest request){
		
		String name = null;
				
		try {
			//parameter为中文需要转码
			name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		MerchantModel.setRestaurantName(name);	
		
		List <Map<String, Object>> merchantList= MerchantDAO.detailsForm(MerchantModel);
		
		
		MerchantModel.setMerchantInfo(merchantList);		
		request.setAttribute("detailform",merchantList);
				
		return "details";
	}
	
	
	@RequestMapping(value="/detailsModify")
	public String detailModifyPage(HttpServletRequest request){

		//餐厅种类
		request.setAttribute("allRestaurantType", MerchantModel.getAllRestaurantType());
		
		//表格信息
		request.setAttribute("detailform", MerchantModel.getMerchantInfo());
		
		
		return "detailsModify";
	}

}
