package com.hpf.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.DetailsModifyModel;
import com.hpf.model.MerchantModel;
import com.hpf.service.DetailsModifyService;
import com.hpf.util.UUIDGenerator;

@Controller
public class DetailsController {
	
	@Autowired
	MerchantModel MerchantModel;
	
	@Autowired
	MerchantDAO MerchantDAO;
	
	@Autowired
	DetailsModifyModel DetailsModifyModel;
	
	@Autowired
DetailsModifyService DetailsModifyService;
	
	@RequestMapping(value="/details")
	public String detailPage(HttpServletRequest request){
		
		String id =null;
				
		try {
			//parameter为中文需要转码
			id = new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
			DetailsModifyModel.setId(Integer.parseInt(id));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		MerchantModel.setId(Integer.parseInt(id));
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
	
	

    @RequestMapping(value="/UupdateSubmit")
    public String detailsUpdate(
    		@RequestParam(value = "viewfiles", required = false) MultipartFile[] viewfiles,
    	    @RequestParam(value = "licensefile", required = false) MultipartFile licensefile,
    	    @RequestParam(value = "contractfile", required = false) MultipartFile contractfile,
    	    @RequestParam(value = "attorneyfile", required = false) MultipartFile attorneyfile,
    		HttpServletRequest request,  		
    		@RequestParam("restaurant_name") String restaurantName, 
    		@RequestParam("restaurant_province") String restaurantProvince, 
    		@RequestParam("restaurant_city") String restaurantCity,
    		@RequestParam("restaurant_district") String restaurantDistrict,
    		@RequestParam("restaurant_street") String restaurantStreet,
    		@RequestParam("restaurant_type") String restaurantType,
    		@RequestParam("restaurant_address") String restaurantAddress,
    		@RequestParam("restaurant_tel") String restaurantTel,
    		@RequestParam("restaurant_opentime") String restaurantOpentime,
    		@RequestParam("restaurant_closetime") String restaurantClosetime,
    		@RequestParam("restaurant_indroduction") String restaurantIndroduction,
    		@RequestParam("manager_phone") String managerPhone,   		
    		@RequestParam("boss_phone") String bossPhone,
    		@RequestParam("bankaccount_name") String bankaccountName,
    		@RequestParam("bankaccount_bank") String bankaccountBank,
    		@RequestParam("bankaccount_account") String bankaccountAccount  
    		 		
    		) {
    	
	
    	//截取地址内容
    	restaurantProvince=restaurantProvince.substring(restaurantProvince.lastIndexOf(",")+1);
    	restaurantCity=restaurantCity.substring(restaurantCity.lastIndexOf(",")+1);
    	restaurantDistrict=restaurantDistrict.substring(restaurantDistrict.lastIndexOf(",")+1);
    	restaurantStreet=restaurantStreet.substring(restaurantStreet.lastIndexOf(",")+1);
    	
    	
    	//设置参数
    	DetailsModifyModel.setRestaurantName(restaurantName);
    	DetailsModifyModel.setRestaurantProvince(restaurantProvince);
    	DetailsModifyModel.setRestaurantCity(restaurantCity);
    	DetailsModifyModel.setRestaurantDistrict(restaurantDistrict);
    	DetailsModifyModel.setRestaurantStreet(restaurantStreet);
    	DetailsModifyModel.setRestaurantAddress(restaurantAddress);
    	DetailsModifyModel.setRestaurantType(restaurantType);
    	DetailsModifyModel.setRestaurantTel(restaurantTel);
    	DetailsModifyModel.setRestaurantOpentime(restaurantOpentime);	
    	DetailsModifyModel.setRestaurantClosetime(restaurantClosetime);
    	DetailsModifyModel.setRestaurantIndroduction(restaurantIndroduction);
    	DetailsModifyModel.setManagerPhone(managerPhone);
    	DetailsModifyModel.setBossPhone(bossPhone);
    	DetailsModifyModel.setBankaccountName(bankaccountName);
    	DetailsModifyModel.setBankaccountAccount(bankaccountAccount);
    	DetailsModifyModel.setBankaccountAccount(bankaccountAccount);
    	  
    	
    	//修改内容
    	DetailsModifyService.updateDetails();
    	
		return null; 	
    } 
	
	
	

}
