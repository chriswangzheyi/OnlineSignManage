package com.hpf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpf.model.ChangePasswordModel;
import com.hpf.service.ChangePasswordService;

@Controller
public class ChangePasswordController {
	
	@Autowired
	ChangePasswordService changePasswordService;	
	
	@Autowired
	ChangePasswordModel changePasswordModel;

	@RequestMapping(value="/changePassword")
	public String forgetPassword(){
		
		return "changePassword";
	}
	
	
	
	@RequestMapping(value="/changePasswordSubmit")
	public String changePasswordsubmit(
			@RequestParam("originalPwdTyped") String originalPwdTyped,
			@RequestParam("newPwdTyped") String newPwdTyped,
			@RequestParam("newPwdConfirmed") String newPwdConfirmed,
			HttpServletRequest request
			){
		
		if(!newPwdTyped.equals(newPwdConfirmed)){
			
			return "您两次输入的新密码不同";
			}
		
		changePasswordModel.setOriginalPassword(originalPwdTyped);
		changePasswordModel.setNewPassword(newPwdTyped);
		
		
		
		return changePasswordService.changePassword(changePasswordModel);
	}
	
}
