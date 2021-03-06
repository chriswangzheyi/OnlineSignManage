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

import net.sf.json.JSONArray;

@Controller
public class UserManagementController {
	
	@Autowired
	UserManagementService UserManagementService;
	
	@Autowired
	UserManagementModel UserManagementModel;

	@RequestMapping(value="/userManagement")
	public String Administration(HttpServletRequest request){
		
		//读取用户信息
		List<Map<String, Object>> userInfoList=UserManagementService.getUserInfo(1);
		request.setAttribute("userInfoList",userInfoList);
		
		//总共页面数
		request.setAttribute("numberOfPages",UserManagementModel.getTotalPageNum());
		
		return "userManagement";
	}
	
	
	//冻结账户
	@RequestMapping(value="/blockAccount")
	@ResponseBody
	public String  blcokAccount(String id){
		
		UserManagementModel.setBlockid(id);			
		return UserManagementService.blockUser(UserManagementModel);//返回success或fail
	}
	
	
	//解冻账户
	@RequestMapping(value="/unblockAccount")
	@ResponseBody
	public String  unblcokAccount(String id){
		
		UserManagementModel.setUnBlockId(id);			
		return UserManagementService.unblockUser(UserManagementModel);//返回success或fail
	}
	
	
	//删除账户
	@RequestMapping(value="/deleteAccount")
	@ResponseBody
	public String  deleteAccount(String id, int currentPage){
				
		UserManagementModel.setDeleteid(id);
		UserManagementModel.setCurrentPage(currentPage);
				
		return UserManagementService.deleteUser(UserManagementModel);//返回success或fail
	}
		
	
	//新增账户
	@RequestMapping(value="/newAccount")
	@ResponseBody
	public String  newAccount(String username, String password, String phone){
		
		UserManagementModel.setNewAccountUsername(username);
		UserManagementModel.setNewAccountPassword(password);
		UserManagementModel.setNewAccountPhone(phone);
		
				
		if(UserManagementService.isUserExisted(UserManagementModel)==false){
			UserManagementService.newUser(UserManagementModel);
			return "success";
		}
		
		return "fail";
	}
	
	
	//换页
	@RequestMapping(value="/changeUserPage")
	@ResponseBody
	public String changePage(int targetPage){
			
		List<Map<String, Object>> userInfoList=UserManagementService.getUserInfo(targetPage);
	
		return JSONArray.fromObject(userInfoList).toString();				 
	}
	
	
	//新页数
	@RequestMapping(value="/getNewUserManagePageNum")
	@ResponseBody
	public String getNewUserManagePageNum(){

		return UserManagementService.getNewPageNum();
	}
	
}
